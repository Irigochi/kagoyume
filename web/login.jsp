<%-- 
    Document   : login
    Created on : 2016/11/20, 16:44:05
    Author     : Kouta
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>ログイン</title>
        <!-- BootstrapのCSS読み込み -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- jQuery読み込み -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- BootstrapのJS読み込み -->
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <h2>ログイン</h2>
        <form action="LoginCheck" method="POST">
            <p>
                ユーザー名：<input type="text" name="name">
            </p>
            <p>
                パスワード：<input type="text" name="pass">
            </p>
            <p>
                <%if(request.getAttribute("delete") != null){%>削除されたユーザーです<%}%>
                <%if(request.getAttribute("user") != null && request.getAttribute("user").equals("")){%>ユーザー名かパスワードが間違っています<%}%>
            </p>
            <input type="submit" value="ログイン" class="btn btn-default">
            <input type="hidden" name="ac" value="<%= session.getAttribute("ac")%>">
        </form>
        <br>
        <form action="Registration" method="POST">
            <input type="submit" value="新規会員登録" class="btn btn-default">
        </form>
    </body>
</html>
