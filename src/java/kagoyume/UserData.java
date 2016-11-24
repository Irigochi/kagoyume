/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Kouta
 */
public class UserData {
    private int id;
    private String name;
    private String pass;
    private String mail;
    private String address;
    private int total;
    private Date newDate;
    
    public UserData(){
        name = "";
        pass = "";
        mail = "";
        address = "";
    }
    
    public String getName(){
        return this.name;
    }
    public void setName(String s){
        this.name = s;
    }
    
    public String getPass(){
        return this.pass;
    }
    public void setPass(String s){
        this.pass = s;
    }
    
    public String getMail(){
        return this.mail;
    }
    public void setMail(String s){
        this.mail = s;
    }
    
    public String getAddress(){
        return this.address;
    }
    public void setAddress(String s){
        this.address = s;
    }
    
    public int getTotal(){
        return this.total;
    }
    
    public Date getDate(){
        return this.newDate;
    }
    
    //入力に不備がないかを確認するメソッド
    public ArrayList<String> chkproperties(){
        ArrayList<String> chkList = new ArrayList<>();
        if(this.name.equals("")){
            chkList.add("name");
        }
        if(this.pass.equals("")){
            chkList.add("pass");
        }
        if(this.mail.equals("")){
            chkList.add("mail");
        }
        if(this.address.equals("")){
            chkList.add("address");
        }
        return chkList;
    }
    
    //UserDataDTOからUserDataに変換するメソッド
    public void DTO2UDMapping(UserDataDTO udd){
        this.name = udd.getName();
        this.mail = udd.getMail();
        this.pass = udd.getPass();
        this.address = udd.getAddress();
        this.total = udd.getTotal();
        this.newDate = udd.getDate();
    }
    
    //UserDataからUserDataDTOに変換するメソッド
    public void UD2DTOMapping(UserDataDTO udd){
        udd.setName(this.name);
        udd.setPass(this.pass);
        udd.setMail(this.mail);
        udd.setAddress(this.address);
    }
}

