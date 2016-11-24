<%-- 
    Document   : mydata
    Created on : 2016/11/21, 15:39:57
    Author     : Kouta
--%>
<%@page import="kagoyume.UserDataDTO"%>
<%@page import="kagoyume.JumsHelper"%>
<%@page import="kagoyume.UserData"%>
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
        <title>マイページ</title>
        <!-- BootstrapのCSS読み込み -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- jQuery読み込み -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- BootstrapのJS読み込み -->
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <header style="background-color:#eee">会員情報</header>
        <br>
        ユーザー名：<%= ud.getName()%><br>
        パスワード：<%= ud.getPass()%><br>
        メールアドレス：<%= ud.getMail()%><br>
        住所：<%= ud.getAddress()%><br>
        登録日：<%= ud.getDate()%><br>
        <a href="Myhistory?ac=<%= session.getAttribute("ac")%>">購入履歴</a>
        <hr>
        <form action="Myupdate" method="POST">
            <input type="submit" value="登録情報の変更" class="btn btn-default btn-sm">
            <input type="hidden" name="ac" value="<%= session.getAttribute("ac")%>">
        </form>
        <form action="Mydelete" method="POST">
            <input type="submit" value="登録の削除" class="btn btn-default btn-sm">
            <input type="hidden" name="ac" value="<%= session.getAttribute("ac")%>">
        </form>
        <br>
        <%= JumsHelper.getInstance().home()%>
    </body>
</html>
