/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 一つの商品の詳細情報を持ちまわるBeans
 *
 * @author Kouta
 */
public class ItemSearchForm extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        
        try{
            request.setCharacterEncoding("UTF-8");
            
            if(session.getAttribute("udd") == null){
                UserDataDTO udd = new UserDataDTO();
                session.setAttribute("udd", udd);
            }
            
            //sdbのインスタンスを生成
            SearchDataBeans sdb = new SearchDataBeans();
            
            //フォームに入力した情報を一時的に変数に代入
            String q = request.getParameter("query");
            String s = request.getParameter("sort");
            String n = request.getParameter("category_id");
            
            //フォームに値があれば各変数に代入
            if(q != null){
                sdb.setQuery(q);
            }
            if(s != null && sdb.getSortOrder().containsKey(s)){
                sdb.setSort(s);
            }    
            if(sdb.isNumber(n) && sdb.getCate().containsKey(n)){
                sdb.setCategory_id(Integer.parseInt(n));
            }
            
            //query(検索キーワード)が空でないとき、URLでXMLにアクセス
            if(!(sdb.getQuery().equals(""))){
                String query4url = URLEncoder.encode(sdb.getQuery());
                String sort4url = URLEncoder.encode(sdb.getSort());
                URL url = new URL("http://shopping.yahooapis.jp/ShoppingWebService/V1/itemSearch?appid="+sdb.getAppid()
                                +"&query="+query4url+"&category_id="+sdb.getCategory_id()+"&sort="+sort4url);                
                
                
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
                String totalHit = root.getAttribute("totalResultsAvailable");
                //HitのNodeListを取得
                NodeList nl = root.getElementsByTagName("Hit");
                //HitのNodeListの数を代入
                sdb.setTotal(nl.getLength());
                
                for(int i = 0; i < nl.getLength(); i++){
                    Element e = (Element)nl.item(i);
                    sdb.setItemName(getChildren(e, "Name"));
                    sdb.setItemPrice(getChildren(e, "Price"));
                    sdb.setItemCode(getChildren(e, "Code"));
                    sdb.setItemHead(getChildren(e, "Headline"));
                    
                    NodeList imageNl = e.getElementsByTagName("Image");
                    Element imageE = (Element)imageNl.item(0);
                    sdb.setItemImage(getChildren(imageE, "Small"));
                }
                
                urlcon.disconnect();
                
                request.setAttribute("totalHit", totalHit);
                request.setAttribute("sdb", sdb);
                request.getRequestDispatcher("/ItemSearchForm.jsp").forward(request, response);
            }else{
                request.setAttribute("formEmpty", 0);
                request.getRequestDispatcher("/ItemSearchForm.jsp").forward(request, response);
            }
        }catch(IOException | NumberFormatException | ServletException | ParserConfigurationException | SAXException e){
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        
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


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
