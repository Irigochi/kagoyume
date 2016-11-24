/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import base.DBManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * ユーザー情報を格納するテーブルに対しての操作処理を包括する
 * DB接続系はDBManagerクラスに一任
 * @author 
 */
public class UserDataDAO {
    
    //インスタンスオブジェクトを返却させてコードの簡略化
    public static UserDataDAO getInstance(){
        return new UserDataDAO();
    }
    
    /**
     * データの挿入処理を行う。現在時刻は挿入直前に生成
     * @param udd 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     */
    public void insert(UserDataDTO udd) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("INSERT INTO user_t(name,password,mail,address,newDate) VALUES(?,?,?,?,?)");
            st.setString(1, udd.getName());
            //st.setDate(2, new java.sql.Date(ud.getBirthday().getTime()));//指定のタイムスタンプ値からSQL格納用のDATE型に変更
            st.setString(2, udd.getPass());
            st.setString(3, udd.getMail());
            st.setString(4, udd.getAddress());
            st.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            System.out.println("insert completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    
    /**
     * 
     * @param name 入力されたユーザー名
     * @param pass 入力されたパスワード
     * @return 
     * @throws java.sql.SQLException 
     */
    public UserDataDTO login(String name, String pass) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        UserDataDTO udd = new UserDataDTO();
        try{
            con = DBManager.getConnection();
            st = con.prepareStatement("SELECT * FROM user_t WHERE name = ? AND password = ?");
            st.setString(1, name);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();
        
            if(rs.next()){
                udd.setID(rs.getInt("userID"));
                udd.setName(rs.getString("name"));
                udd.setPass(rs.getString("password"));
                udd.setMail(rs.getString("mail"));
                udd.setAddress(rs.getString("address"));
                udd.setTotal(rs.getInt("total"));
                udd.setDate(rs.getDate("newDate"));
                udd.setDeletef(rs.getInt("deleteFlg"));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
        return udd;
    }
    
    
    /**
     * データの検索処理を行う。
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     * @return 検索結果
     *
    public UserDataList search(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        UserDataList udl = new UserDataList();
        try{
            con = DBManager.getConnection();           
        
            String sql = "SELECT * FROM user_t";           

            boolean flag = false;
            int pattern = 0;
            //変数(pattern)に0を代入しておく。以降、SELECT文のパターンごとに異なる数値を代入する。
            if (!ud.getName().equals("")) {
                sql += " WHERE name like ?";
                flag = true;
                pattern = 1;
            }
            if (ud.getBirthday()!= null) {
                if(!flag){
                    sql += " WHERE birthday like ?";
                    flag = true;
                    pattern = 2;
                }else{
                    sql += " AND birthday like ?";
                    pattern = 3;
                }
            }
            if (ud.getType()!=0) {
                if(!flag){
                    sql += " WHERE type like ?";
                    pattern = 4;
                }else{
                    sql += " AND type like ?";
                    switch(pattern){
                        case 1:
                            pattern = 5;
                            break;
                        case 2: 
                            pattern = 6;
                            break;
                        case 3: 
                            pattern = 7;
                            break;
                    }
                }
            }            
            st =  con.prepareStatement(sql);
            //switchを用いてパターンごとに分岐
            switch(pattern){
                case 0:
                    break;
                case 1:
                    st.setString(1, "%"+ud.getName()+"%");
                    break;
                case 2:
                    st.setString(1, "%"+ new SimpleDateFormat("yyyy").format(ud.getBirthday())+"%");
                    break;
                case 3:
                    st.setString(1, "%"+ud.getName()+"%");
                    st.setString(2, "%"+ new SimpleDateFormat("yyyy").format(ud.getBirthday())+"%");
                    break;
                case 4:
                    st.setInt(1, ud.getType());
                    break;
                case 5:
                    st.setString(1, "%"+ud.getName()+"%");
                    st.setInt(2, ud.getType());
                    break;
                case 6:
                    st.setString(1, "%"+ new SimpleDateFormat("yyyy").format(ud.getBirthday())+"%");
                    st.setInt(2, ud.getType());
                    break;
                case 7:
                    st.setString(1, "%"+ud.getName()+"%");
                    st.setString(2, "%"+ new SimpleDateFormat("yyyy").format(ud.getBirthday())+"%");
                    st.setInt(3, ud.getType());
                    break;
            }
            
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                udl.addIdList(rs.getInt(1));
                udl.addNameList(rs.getString(2));
                udl.addBirthdayList(rs.getDate(3));
                udl.addTellList(rs.getString(4));
                udl.addTypeList(rs.getInt(5));
                udl.addCommentList(rs.getString(6));
                udl.addNewDateList(rs.getTimestamp(7));
            }
            System.out.println("search completed");
            return udl;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    /**
     * ユーザーIDによる1件のデータの検索処理を行う。
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     * @return 検索結果
     *
    public UserDataDTO searchByID(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            
            String sql = "SELECT * FROM user_t WHERE userID = ?";
            
            st =  con.prepareStatement(sql);
            st.setInt(1, ud.getUserID());
            
            ResultSet rs = st.executeQuery();
            rs.next();
            UserDataDTO resultUd = new UserDataDTO();
            resultUd.setUserID(rs.getInt(1));
            resultUd.setName(rs.getString(2));
            resultUd.setBirthday(rs.getDate(3));
            resultUd.setTell(rs.getString(4));
            resultUd.setType(rs.getInt(5));
            resultUd.setComment(rs.getString(6));
            resultUd.setNewDate(rs.getTimestamp(7));
            
            System.out.println("searchByID completed");

            return resultUd;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }*/
    
    /**
     * データの上書きを行う
     * @param udd 対応したデータを保持しているJavaBeans
     * @throws java.sql.SQLException 呼び出し元にcatchさせるためにスロー
     */
    
    public void update(UserDataDTO udd) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            
            String sql = "UPDATE user_t SET name = ?, password = ?, mail = ?, address = ?"
                    + "WHERE userID = ?";
            
            st = con.prepareStatement(sql);
            st.setString(1, udd.getName());
            st.setString(2, udd.getPass());
            st.setString(3, udd.getMail());
            st.setString(4, udd.getAddress());
            st.setInt(5, udd.getID());
            st.executeUpdate();
            
            System.out.println("update completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    public void userDelete(UserDataDTO udd) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            String sql = "UPDATE user_t SET deleteFlg = 1 WHERE userID = ?";
            
            st = con.prepareStatement(sql);
            st.setInt(1, udd.getID());
            st.executeUpdate();
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    public void buyInsert(int id, CartDataBeans cdb, int type) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            
            for(int i = 0; i < cdb.getSize(); i++){
                st = con.prepareStatement("INSERT INTO buy_t(userID,itemCode,type,buyDate) VALUES(?,?,?,?)");
                st.setInt(1, id);
                st.setString(2, cdb.getCode(i));
                st.setInt(3, type);
                st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
                st.executeUpdate();
            }
            System.out.println("insert completed");
            }catch(SQLException e){
                System.out.println(e.getMessage());
                throw new SQLException(e);
            }finally{
                if(con != null){
                    con.close();
                }
            }
    }
    
    public void updateTotal(int total, int id) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st = con.prepareStatement("UPDATE user_t SET total = ? WHERE userID = ?");
            st.setInt(1, total);
            st.setInt(2, id);
            st.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    
    public HistoryData getHistory(int id) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        HistoryData hd = new HistoryData();
        try{
            con = DBManager.getConnection();
            st = con.prepareStatement("SELECT * FROM buy_t WHERE userID = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                hd.addItemCode(rs.getString("itemCode"));
                hd.addType(rs.getInt("type"));
                hd.addBuyDate(rs.getDate("buyDate"));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
        return hd;
    }
    /**
     * 一件のデータの削除を行う
     * @param id 検索で指定されたユーザーのid
     * @throws java.sql.SQLException 呼び出し元にcatchさせるためにスロー
     *
    public void delete(int id) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            
            String sql = "DELETE FROM user_t WHERE userID = ?";
            
            st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            
            System.out.println("delete completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }*/
}