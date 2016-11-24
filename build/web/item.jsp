<%-- 
    Document   : item
    Created on : 2016/11/18, 15:59:59
    Author     : Kouta
--%>
<%@page import="kagoyume.JumsHelper"%>
<%@page import="kagoyume.ItemDataBeans"%>
<%
    ItemDataBeans idb = (ItemDataBeans)session.getAttribute("idb");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>商品詳細</title>
        <!-- BootstrapのCSS読み込み -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- jQuery読み込み -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- BootstrapのJS読み込み -->
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <header style="background-color:#eee">商品詳細</header>
        <form action="Login" method="POST">
            <input type="submit" <%if(session.getAttribute("login") != null){%>
                   value="ログアウト" class="btn btn-default btn-sm"<%}else{%>value="ログイン" class="btn btn-primary btn-sm"<%}%> >
            <input type="hidden" name="page" value="/item.jsp">
            <input type="hidden" name="code" value="<%= idb.getCode()%>">
            <input type="hidden" name="ac" value="<%= session.getAttribute("ac")%>">
        </form>
        <h2><%= idb.getName()%></h2>
        <hr>
        <h3><%= idb.getHead()%></h3>
        <hr>
        <img src="<%= idb.getImage()%>"/>
        <hr>
        <h5>-商品概要-</h5>
        <p><%= idb.getDes()%></p>
        <hr>
        <h4>価格：<%= idb.getPrice()%>円</h4>
        <hr>
        <form action="Add" method="POST">
            <input type="submit" value="カートに追加" class="btn btn-primary btn-sm">
            <input type="hidden" name="code" value="<%= idb.getCode()%>">
            <input type="hidden" name="ac" value="<%= session.getAttribute("ac")%>">
        </form>
            <%= JumsHelper.getInstance().home()%>
    </body>
</html>
