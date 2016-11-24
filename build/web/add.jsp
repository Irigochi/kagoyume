<%-- 
    Document   : add
    Created on : 2016/11/18, 16:19:49
    Author     : Kouta
--%>

<%@page import="kagoyume.JumsHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>カートに追加完了</title>
        <!-- BootstrapのCSS読み込み -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- jQuery読み込み -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- BootstrapのJS読み込み -->
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <header style="background-color:#eee">商品詳細</header>
        <h3>カートに追加しました</h3>
        <form action="Cart" method="POST">
            <input type="submit" value="カートへ" class="btn btn-default btn-sm">
            <input type="hidden" name="ac" value="<%= session.getAttribute("ac")%>">
        </form>
        <br>
        <%= JumsHelper.getInstance().home()%>
    </body>
</html>
