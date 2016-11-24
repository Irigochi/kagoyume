<%-- 
    Document   : registrationconfirm
    Created on : 2016/11/20, 15:29:23
    Author     : Kouta
--%>
<%@page import="kagoyume.JumsHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kagoyume.UserData"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    UserData ud = (UserData)session.getAttribute("ud");
    ArrayList<String> chkList = ud.chkproperties();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>新規会員登録</title>
        <!-- BootstrapのCSS読み込み -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- jQuery読み込み -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- BootstrapのJS読み込み -->
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <header style="background-color:#eee">会員登録</header>
    <%if(chkList.size() == 0){%>
        <h3>下記の情報で会員登録します</h3>
        <p>ユーザー名:<%= ud.getName()%></p>
        <p>パスワード:<%= ud.getPass()%></p>
        <p>メールアドレス:<%= ud.getMail()%></p>
        <p>住所:<%= ud.getAddress()%></p>
        <hr>
        <form action="RegistrationComplete" method="POST">
            <input type="submit" value="会員登録" class="btn btn-primary btn-sm">
            <input type="hidden" name="ac" value="<%= session.getAttribute("ac")%>">
        </form>
    <%}else{%>
        <h3>入力が不完全です</h3>
        <%= jh.chkinput(chkList)%>
    <%}%>
    <br>
        <form action="Registration" method="POST">
            <input type="submit" value="入力画面に戻る" class="btn btn-default btn-sm">
            <input type="hidden" name="ac" value="<%= session.getAttribute("ac")%>">
            <input type="hidden" name="mode" value="reinput">
        </form>
            <br>
            <%= jh.home()%>
    </body>
</html>
