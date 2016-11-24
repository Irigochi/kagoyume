<%-- 
    Document   : ItemSearchForm
    Created on : 2016/11/18, 15:06:15
    Author     : Kouta
--%>

<%@page import="kagoyume.UserData"%>
<%@page import="kagoyume.SearchDataBeans"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap" %>
<%@page import="java.net.URLEncoder"%>
<%
    SearchDataBeans sdb;
    String totalHit = "0";
    if(request.getAttribute("sdb") != null){
        sdb = (SearchDataBeans)request.getAttribute("sdb");
    }else{
        sdb = new SearchDataBeans();
    }
    if(request.getAttribute("totalHit") != null){
        totalHit = (String)request.getAttribute("totalHit");
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>検索ページ</title>
        <!-- BootstrapのCSS読み込み -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- jQuery読み込み -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- BootstrapのJS読み込み -->
        <script src="js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="../css/prototype.css"/>
    </head>
    <body>
        <header style="background-color:#eee">トップページ</header>        
        <h1><a href="ItemSearchForm.jsp">かごいっぱいのゆめ</a></h1>
        
        <form action="Login" method="POST">
            <input type="submit" <%if(session.getAttribute("login") != null){%>
                   value="ログアウト" class="btn btn-default btn-sm"<%}else{%>value="ログイン" class="btn btn-primary btn-sm"<%}%>>
            <input type="hidden" name="page" value="/ItemSearchForm.jsp">
        </form>
                   
        <%if(session.getAttribute("login") != null){%>
        <h4>ようこそ<%= session.getAttribute("user")%>さん</h4>
            <form action="Mydata" method="POST">
                <input type="submit" value="マイページへ" class="btn btn-default btn-sm">
            </form>
        <%}else{%>
        <h4>ようこそゲストさん</h4>
        <%}%>
        
        <form action="Cart" method="POST">
            <input type="submit" value="カート" class="btn btn-default btn-sm">
        </form>
            <hr>
        <form action="ItemSearchForm" class="Search">
        表示順序:
        <select name="sort">
        <%
            for(Object obj : sdb.getSortOrder().entrySet()){
                Map.Entry entry = (Map.Entry)obj;
                String key = sdb.h((String)entry.getKey());
                String value = sdb.h((String)entry.getValue());
        %>
        <option value="<%= key%>" <%if(sdb.getSort().equals(key)){%> selected <%}%>><%= value%></option>
        <% } %>
        </select>
        カテゴリ検索:
        <select name="category_id">
        <%
            for(Object obj : sdb.getCate().entrySet()){
                Map.Entry entry = (Map.Entry)obj;
                String key = sdb.h((String)entry.getKey());
                String value = sdb.h((String)entry.getValue());
        %>
        <option value="<%= key%>" <%if(sdb.getCategory_id() == Integer.parseInt(key)){%> selected <%}%>><%= value%></option>
        <% } %>
        </select>
        キーワード:<input type="text" name="query" value="<%= sdb.h(sdb.getQuery())%>"/>
        <input type="submit" value="Yahooショッピングで検索" class="btn btn-default btn-sm"/>
        </form>
        <%if(request.getAttribute("formEmpty") != null){%><p>検索キーワードを入力してください</p><%}else{%>
        <p>検索ヒット数<%= totalHit%>件の内、20件表示</p>
        <%}%>
        <%
            for(int i = 0; i < sdb.getTotal(); i++){
        %>
        <div class="Item" align="left">
            <hr>
            <h4><a href="Item?code=<%= sdb.getItemCode(i)%>"><%= sdb.getItemName(i)%></a><br>価格：<%= sdb.getItemPrice(i)%>円</h4>           
            <a href="Item?code=<%= sdb.getItemCode(i)%>"><img src="<%= sdb.getItemImage(i)%>" class="img-thumbnail" /></a>
            <%= sdb.getItemHead(i)%>            
        </div>
        <% } %>
        <hr>
<!-- Begin Yahoo! JAPAN Web Services Attribution Snippet -->
<a href="http://developer.yahoo.co.jp/about">
<img src="http://i.yimg.jp/images/yjdn/yjdn_attbtn2_105_17.gif" width="105" height="17" title="Webサービス by Yahoo! JAPAN" alt="Webサービス by Yahoo! JAPAN" border="0" style="margin:15px 15px 15px 15px"></a>
<!-- End Yahoo! JAPAN Web Services Attribution Snippet -->
    </body>
</html>