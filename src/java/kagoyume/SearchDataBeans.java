/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 検索内容とその結果のデータを持ちまわるBeans
 * 
 * @author Kouta
 */
public class SearchDataBeans {
    
    //取得したappid
    private final String appid = "dj0zaiZpPXZ4aEJwNHpFZUNqcSZzPWNvbnN1bWVyc2VjcmV0Jng9YzU-";
    
    //検索条件のインスタンス変数
    private String query;
    private String sort;
    private int category_id;
    
    //検索結果のデータを入れておくためのArrayList
    private final ArrayList<String> nameList;
    private final ArrayList<String> imageList;
    private final ArrayList<String> headList;
    private final ArrayList<String> priceList;
    private final ArrayList<String> codeList;
    
    //total件/20件
    private int total;
    
    //カテゴリとソートの種類をまとめるコレクション
    private final HashMap<String,String> cate = new HashMap<>();
    private final HashMap<String,String> sortOrder = new HashMap<>();
    
    public SearchDataBeans(){
        
        //変数に初期値を代入
        query = "";
        sort = "-score";
        category_id = 1;
        
        nameList = new ArrayList();
        imageList = new ArrayList();
        headList = new ArrayList();
        priceList = new ArrayList();
        codeList = new ArrayList();
        
        //カテゴリ一覧
        cate.put("1", "すべてのカテゴリから");
        cate.put("13457", "ファッション");
        cate.put("2498", "食品");
        cate.put("2500", "ダイエット、健康");
        cate.put("2501", "コスメ、香水");
        cate.put("2502", "パソコン、周辺機器");
        cate.put("2504", "AV機器、カメラ");
        cate.put("2505", "家電");
        cate.put("2506", "家具、インテリア");
        cate.put("2507", "花、ガーデニング");
        cate.put("2508", "キッチン、生活雑貨、日用品");
        cate.put("2503", "DIY、工具、文具");
        cate.put("2509", "ペット用品、生き物");
        cate.put("2510", "楽器、趣味、学習");
        cate.put("2511", "ゲーム、おもちゃ");
        cate.put("2497", "ベビー、キッズ、マタニティ");
        cate.put("2512", "スポーツ");
        cate.put("2513", "レジャー、アウトドア");
        cate.put("2514", "自転車、車、バイク用品");
        cate.put("2516", "CD、音楽ソフト");
        cate.put("2517", "DVD、映像ソフト");
        cate.put("10002", "本、雑誌、コミック");
        
        //ソートの一覧
        sortOrder.put("-score", "おすすめ順");
        sortOrder.put("+price", "商品価格が安い順");
        sortOrder.put("-price", "商品価格が高い順");
        sortOrder.put("+name", "ストア名昇順");
        sortOrder.put("-name", "ストア名降順");
        sortOrder.put("-sold", "売れ筋順");
    }
    
    
    //想定外の文字を変換してコードに影響が出ないようにするメソッド
    public String h(String str){
        
        String ret_val = str;

        String[] escape = {"&", "<", ">", "\"", "\'", "\n", "\t"};
        String[] replace = {"&amp;", "&lt;", "&gt;", "&quot;", "&#39;", "<br>", "&#x0009;"};

        for ( int i=0; i < escape.length; i++ ){
            ret_val = ret_val.replace(escape[i], replace[i]);
        }
        return ret_val;
    }
    
    
    //各変数・Listのセッターとゲッター
    public String getQuery(){
        return this.query;
    }
    public void setQuery(String s){
        this.query = s;
    }
    
    public String getSort(){
        return this.sort;
    }
    public void setSort(String s){
        this.sort = s;
    }
    
    public int getCategory_id(){
        return this.category_id;
    }
    public void setCategory_id(int i){
        this.category_id = i;
    }
    
    public String getItemName(int i){
        return this.nameList.get(i);
    }
    public void setItemName(String s){
        this.nameList.add(s);
    }
    
    public String getItemImage(int i){
        return this.imageList.get(i);
    }
    public void setItemImage(String s){
        this.imageList.add(s);
    }
    
    public String getItemHead(int i){
        return this.headList.get(i);
    }
    public void setItemHead(String s){
        this.headList.add(s);
    }
    
    public String getItemPrice(int i){
        return this.priceList.get(i);
    }
    public void setItemPrice(String s){
        this.priceList.add(s);
    }
    
    public String getItemCode(int i){
        return this.codeList.get(i);
    }
    public void setItemCode(String s){
        this.codeList.add(s);
    }
    
    public int getTotal(){
        return this.total;
    }
    public void setTotal(int i){
        this.total = i;
    }
       
    public String getAppid(){
        return this.appid;
    }
    
    //コレクションのゲッター
    public HashMap getSortOrder(){
        return this.sortOrder;
    }
    public HashMap getCate(){
        return this.cate;
    }
    
    
    //引数が整数か判断して真偽値を返すメソッド
    public boolean isNumber(String num){
        try{
            Integer.parseInt(num);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    
    
}
