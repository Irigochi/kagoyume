<%-- 
    Document   : myupdateresult
    Created on : 2016/11/21, 18:08:42
    Author     : Kouta
--%>
<%@page import="kagoyume.UserData"%>
<%@page import="kagoyume.JumsHelper"%>
<%@page import="kagoyume.UserDataDTO"%>
<%
    UserData ud = (UserData)session.getAttribute("ud");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>変更完了</title>
        <!-- BootstrapのCSS読み込み -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- jQuery読み込み -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- BootstrapのJS読み込み -->
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <header style="background-color:#eee">会員情報</header>
        <h3>会員情報を更新しました</h3>
        ユーザー名：<%= ud.getName()%><br>
        パスワード：<%= ud.getPass()%><br>
        メールアドレス：<%= ud.getMail()%><br>
        住所：<%= ud.getAddress()%><br>
        <br>
        <a href="Mydata">マイページへ</a><br>
        <%= JumsHelper.getInstance().home()%>
    </body>
</html>
