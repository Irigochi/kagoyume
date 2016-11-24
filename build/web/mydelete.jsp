<%-- 
    Document   : mydelete
    Created on : 2016/11/21, 19:13:06
    Author     : Kouta
--%>
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
        <title>ユーザー登録解除</title>
        <!-- BootstrapのCSS読み込み -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- jQuery読み込み -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- BootstrapのJS読み込み -->
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <header style="background-color:#eee">会員情報</header>
        <h3>下記ユーザーの登録を解除します。よろしいですか？</h3>
        ユーザー名：<%= ud.getName()%><br>
        パスワード：<%= ud.getPass()%><br>
        メールアドレス：<%= ud.getMail()%><br>
        住所：<%= ud.getAddress()%><br>
        <hr>
        <form action="MydeleteResult" method="POST">
            <input type="submit" value="登録解除する" class="btn btn-danger btn-sm">
            <input type="hidden" name="ac" value="<%= session.getAttribute("ac")%>">
        </form>
        <br>
        <form action="ItemSearchForm" method="POST">
            <input type="submit" value="キャンセル" class="btn btn-default btn-sm">
        </form>
        <br>
        <a href="Mydata">マイページへ</a><br>
    </body>
</html>
