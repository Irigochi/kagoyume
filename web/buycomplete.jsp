<%-- 
    Document   : buycomplete
    Created on : 2016/11/22, 18:38:05
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
        <title>購入手続き完了</title>
        <!-- BootstrapのCSS読み込み -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- jQuery読み込み -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- BootstrapのJS読み込み -->
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <header style="background-color:#eee">購入手続き</header>
        <h3>ご購入ありがとうございました</h3>
        <%= JumsHelper.getInstance().home()%>
    </body>
</html>
