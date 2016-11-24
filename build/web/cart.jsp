<%-- 
    Document   : cart
    Created on : 2016/11/21, 14:34:30
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
        <title>カートの中身</title>
        <!-- BootstrapのCSS読み込み -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- jQuery読み込み -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- BootstrapのJS読み込み -->
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <header style="background-color:#eee">買い物かご</header>
        <%if(request.getAttribute("cartEmpty") == null){
            for(int i = 0; i < cdb.getSize(); i++){%>
            <h4><a href="Item?code=<%= cdb.getCode(i)%>"><%= cdb.getName(i)%></a><br>価格：<%= cdb.getPrice(i)%>円</h4>
            <img src="<%= cdb.getImage(i)%>" class="img-thumbnail"/>
                <a href="CartRemove?code=<%= cdb.getCode(i)%>">カートから削除</a>
                <hr>
            <%}%>
            <h4>総額：<%= cdb.total()%>円</h4>
            <form action="Buyconfirm" method="POST">
                <input type="submit" value="購入する" class="btn btn-primary">
            </form>
            <br>
            <form action="Cart" method="POST">
                <input type="submit" value="カートを空にする" class="btn btn-default btn-sm">
                <input type="hidden" name="cartDelete" value="0">
            </form>
        <%}else{%>
            カートに商品はありません
        <%}%>
        <br>
        <%= JumsHelper.getInstance().home()%>
    </body>
</html>
