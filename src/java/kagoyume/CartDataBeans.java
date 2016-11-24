/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * ItemDataBeansと似ているが、こちらはカート用に複数の商品情報をArrayListで持ちまわる
 * @author Kouta
 */
public class CartDataBeans {
    private final String appid = "dj0zaiZpPXZ4aEJwNHpFZUNqcSZzPWNvbnN1bWVyc2VjcmV0Jng9YzU-";
    
    private final ArrayList<String> codeList;
    private final ArrayList<String> nameList;
    private final ArrayList<String> priceList;
    private final ArrayList<String> imageList;
    
    public CartDataBeans(){
        nameList = new ArrayList();
        priceList = new ArrayList();
        imageList = new ArrayList();
        codeList = new ArrayList();
    }
    
    public String getCode(int i){
        return this.codeList.get(i);
    }
    public void addCode(String s){
        this.codeList.add(s);
    }
    
    public String getName(int i){
        return this.nameList.get(i);
    }
    public void addName(String s){
        this.nameList.add(s);
    }
    
    public String getPrice(int i){
        return this.priceList.get(i);
    }
    public void addPrice(String s){
        this.priceList.add(s);
    }
    
    public String getImage(int i){
        return this.imageList.get(i);
    }
    public void addImage(String s){
        this.imageList.add(s);
    }
    
    //要素数を返すメソッド
    public int getSize(){
        return this.codeList.size();
    }
    
    //総額を返すメソッド
    public int total(){
        int total = 0;
        for(int i = 0; i < this.priceList.size(); i++){
            total = total + Integer.parseInt(this.priceList.get(i));
        }
        return total;
    }
    
    /**
     * 
     * @param code 商品コード
     * @return 情報を追加したCartDataBeans
     * @throws Exception 呼び出し元でキャッチさせる
     */
    public CartDataBeans getItemByCode(String code) throws Exception{
        this.codeList.add(code);
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
            
        //getChildrenメソッドを用いて各タグ名の要素をリストに追加
        Element e = (Element)nl.item(0);
        this.nameList.add(getChildren(e, "Name"));
        this.priceList.add(getChildren(e, "Price"));
            
        NodeList imageNl = e.getElementsByTagName("Image");
        Element imageE = (Element)imageNl.item(0);
        this.imageList.add(getChildren(imageE, "Small"));
        
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
