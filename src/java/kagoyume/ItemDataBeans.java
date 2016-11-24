/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Kouta
 */
public class ItemDataBeans {
    
    private final String appid = "dj0zaiZpPXZ4aEJwNHpFZUNqcSZzPWNvbnN1bWVyc2VjcmV0Jng9YzU-";
    
    private String code;
    private String name;
    private String price;
    private String des;
    private String image;
    private String head;
    
    public ItemDataBeans(){
        
    }
    
    public String getCode(){
        return this.code;
    }
    public void setCode(String s){
        this.code = s;
    }
    
    public String getName(){
        return this.name;
    }
    public void setName(String s){
        this.name = s;
    }
    
    public String getPrice(){
        return this.price;
    }
    public void setPrice(String s){
        this.price = s;
    }
    
    public String getDes(){
        return this.des;
    }
    public void setDes(String s){
        this.des = s;
    }
    
    public String getImage(){
        return this.image;
    }
    public void setImage(String s){
        this.image = s;
    }
    
    public String getHead(){
        return this.head;
    }
    public void setHead(String s){
        this.head = s;
    }
    
    public String getAppid(){
        return this.appid;
    }
    
    
    /**
     * 商品コードでAPI用のURLを作成し、それを元に商品情報を取得するメソッド
     * @param code 商品詳細に遷移する際にGETで受け取る商品コード
     * @return 商品の詳細情報を持ったItemDataBeans
     * @throws java.lang.Exception 呼び出し元でキャッチさせるのでスルー
     */
    public ItemDataBeans getItemByCode(String code) throws Exception{
        this.code = code;
        URL url = new URL("http://shopping.yahooapis.jp/ShoppingWebService/V1/itemLookup?appid="+this.appid+
                    "&responsegroup=medium&itemcode="+code);
            
        HttpURLConnection urlcon = (HttpURLConnection)url.openConnection();
        urlcon.setRequestMethod("GET");
        urlcon.setInstanceFollowRedirects(false);
        urlcon.setRequestProperty("Accept-Language", "ja;q=0.7,en;q=0.3");
        urlcon.connect();
                
        Document doc = null;
                
        DocumentBuilder docbuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        doc = docbuilder.parse(urlcon.getInputStream());
        
        //ルートを取得
        Element root = doc.getDocumentElement();
        //HitのNodeListを取得
        NodeList nl = root.getElementsByTagName("Hit");
            
        //getChildrenメソッドを用いて各タグ名の要素を取得
        Element e = (Element)nl.item(0);
        this.name = getChildren(e, "Name");
        this.head = getChildren(e, "Headline");
        this.des = getChildren(e, "Description");
        this.price = getChildren(e, "Price");
        
        NodeList imageNl = e.getElementsByTagName("Image");
        Element imageE = (Element)imageNl.item(0);
        this.image = getChildren(imageE, "Medium");
        
        return this;
    }
    
    /**
     * 指定されたエレメントとタグ名の子要素の内容を取得するメソッド
     * @param e 指定エレメント
     * @param tag 指定タグ名
     * @return 取得した内容
     */
    public static String getChildren(Element e, String tag){
        NodeList nl = e.getElementsByTagName(tag);
        Element cElement = (Element)nl.item(0);
        if(cElement.hasChildNodes()){
            return cElement.getFirstChild().getNodeValue();
        }else{
            return "";
        }
    }
}
