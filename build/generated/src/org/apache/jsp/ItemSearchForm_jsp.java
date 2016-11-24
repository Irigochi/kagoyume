package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import kagoyume.UserData;
import kagoyume.SearchDataBeans;
import java.util.Map;
import java.util.HashMap;
import java.net.URLEncoder;

public final class ItemSearchForm_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

    SearchDataBeans sdb;
    if(request.getAttribute("sdb") != null){
        sdb = (SearchDataBeans)request.getAttribute("sdb");
    }else{
        sdb = new SearchDataBeans();
    } 

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>かごいっぱいのゆめ TOP</title>\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"../css/prototype.css\"/>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1><a href=\"ItemSearchForm.jsp\">かごいっぱいのゆめ - 商品を検索する</a></h1>\n");
      out.write("        <h3>\n");
      out.write("        ");
if(session.getAttribute("login") != null){
      out.write("\n");
      out.write("            ようこそ");
      out.print( session.getAttribute("user"));
      out.write("さん\n");
      out.write("            <form action=\"Mydata\" method=\"POST\">\n");
      out.write("                <input type=\"submit\" value=\"マイページへ\">\n");
      out.write("            </form>\n");
      out.write("        ");
}else{
      out.write("\n");
      out.write("            ようこそゲストさん\n");
      out.write("        ");
}
      out.write("\n");
      out.write("        </h3>\n");
      out.write("        <form action=\"Cart\" method=\"POST\">\n");
      out.write("            <input type=\"submit\" value=\"カート\">\n");
      out.write("        </form>\n");
      out.write("        <form action=\"Login\" method=\"POST\">\n");
      out.write("            <input type=\"submit\" ");
if(session.getAttribute("login") != null){
      out.write("value=\"ログアウト\"");
}else{
      out.write("value=\"ログイン\"");
}
      out.write(">\n");
      out.write("            <input type=\"hidden\" name=\"page\" value=\"/ItemSearchForm.jsp\">\n");
      out.write("        </form>\n");
      out.write("        <form action=\"ItemSearchForm\" class=\"Search\">\n");
      out.write("        表示順序:\n");
      out.write("        <select name=\"sort\">\n");
      out.write("        ");

            for(Object obj : sdb.getSortOrder().entrySet()){
                Map.Entry entry = (Map.Entry)obj;
                String key = sdb.h((String)entry.getKey());
                String value = sdb.h((String)entry.getValue());
        
      out.write("\n");
      out.write("        <option value=\"");
      out.print( key);
      out.write('"');
      out.write(' ');
if(sdb.getSort().equals(key)){
      out.write(" selected ");
}
      out.write('>');
      out.print( value);
      out.write("</option>\n");
      out.write("        ");
 } 
      out.write("\n");
      out.write("        </select>\n");
      out.write("        キーワード検索：\n");
      out.write("        <select name=\"category_id\">\n");
      out.write("        ");

            for(Object obj : sdb.getCate().entrySet()){
                Map.Entry entry = (Map.Entry)obj;
                String key = sdb.h((String)entry.getKey());
                String value = sdb.h((String)entry.getValue());
        
      out.write("\n");
      out.write("        <option value=\"");
      out.print( key);
      out.write('"');
      out.write(' ');
if(sdb.getCategory_id() == Integer.parseInt(key)){
      out.write(" selected ");
}
      out.write('>');
      out.print( value);
      out.write("</option>\n");
      out.write("        ");
 } 
      out.write("\n");
      out.write("        </select>\n");
      out.write("        <input type=\"text\" name=\"query\" value=\"");
      out.print( sdb.h(sdb.getQuery()));
      out.write("\"/>\n");
      out.write("        <input type=\"submit\" value=\"Yahooショッピングで検索\"/>\n");
      out.write("        </form>\n");
      out.write("        ");

            for(int i = 0; i < sdb.getTotal(); i++){
        
      out.write("\n");
      out.write("        <div class=\"Item\">\n");
      out.write("            <h3><a href=\"Item?code=");
      out.print( sdb.getItemCode(i));
      out.write('"');
      out.write('>');
      out.print( sdb.getItemName(i));
      out.write("</a></h3>\n");
      out.write("            <p><a href=\"Item?code=");
      out.print( sdb.getItemCode(i));
      out.write("\"><img src=\"");
      out.print( sdb.getItemImage(i));
      out.write("\" /></a>");
      out.print( sdb.getItemPrice(i));
      out.write("円</p>\n");
      out.write("        </div>\n");
      out.write("        ");
 } 
      out.write("\n");
      out.write("        ");
if(request.getAttribute("formEmpty") != null){
      out.write("<p>キーワードを入力してください</p>");
}
      out.write("\n");
      out.write("<!-- Begin Yahoo! JAPAN Web Services Attribution Snippet -->\n");
      out.write("<a href=\"http://developer.yahoo.co.jp/about\">\n");
      out.write("<img src=\"http://i.yimg.jp/images/yjdn/yjdn_attbtn2_105_17.gif\" width=\"105\" height=\"17\" title=\"Webサービス by Yahoo! JAPAN\" alt=\"Webサービス by Yahoo! JAPAN\" border=\"0\" style=\"margin:15px 15px 15px 15px\"></a>\n");
      out.write("<!-- End Yahoo! JAPAN Web Services Attribution Snippet -->\n");
      out.write("    </body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
