<%-- 
    Document   : buyconfirm
    Created on : 2016/11/22, 18:05:58
    Author     : Kouta
--%>
<%@page import="kagoyume.JumsHelper"%>
<%@page import="kagoyume.CartDataBeans"%>
<%
    CartDataBeans cdb = (CartDataBeans)session.getAttribute("cdb");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>購入確認</title>
        <!-- BootstrapのCSS読み込み -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- jQuery読み込み -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- BootstrapのJS読み込み -->
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <header style="background-color:#eee">購入手続き</header>
        <h3>カート商品一覧</h3>
        <%for(int i = 0; i < cdb.getSize(); i++){%>
        <%= cdb.getName(i)%><%= cdb.getPrice(i)%>
        <%}%>
        <h3>総額</h3>
        <%= cdb.total()%>円
        <h3>配送方法</h3>
        <form action="Buycomplete" method="POST">
            通常配送<input type="radio" name="delivery" value="1"><br>
            お急ぎ便<input type="radio" name="delivery" value="2"><br>
            <br>
            <input type="submit" value="上記の内容で購入する" class="btn btn-primary">
            <input type="hidden" name="ac" value="<%= session.getAttribute("ac")%>">
        </form>        
        <br>
        <a href="Cart">カートへ戻る</a>
        <br>
        <%= JumsHelper.getInstance().home()%>
    </body>
</html>
