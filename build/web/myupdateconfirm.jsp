<%-- 
    Document   : myupdateconfirm
    Created on : 2016/11/21, 17:36:33
    Author     : Kouta
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="kagoyume.JumsHelper"%>
<%@page import="kagoyume.UserData"%>
<%
    UserData ud = (UserData)session.getAttribute("ud");
    UserData updateUd = (UserData)session.getAttribute("updateUd");
    JumsHelper jh = JumsHelper.getInstance();
    ArrayList<String> chkList = updateUd.chkproperties();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>変更確認</title>
        <!-- BootstrapのCSS読み込み -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- jQuery読み込み -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- BootstrapのJS読み込み -->
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <header style="background-color:#eee">会員情報</header>
    <%if(chkList.size() == 0){%>
        <h3>下記の情報に変更します</h3>
        <h4>変更前</h4>
        ユーザー名：<%= ud.getName()%><br>
        パスワード：<%= ud.getPass()%><br>
        メールアドレス：<%= ud.getMail()%><br>
        住所：<%= ud.getAddress()%><br>
        <hr>
        <h4>変更後</h4>
        ユーザー名：<%= updateUd.getName()%><br>
        パスワード：<%= updateUd.getPass()%><br>
        メールアドレス：<%= updateUd.getMail()%><br>
        住所：<%= updateUd.getAddress()%><br>
        <br>
        <form action="MyupdateResult" method="POST">
            <input type="submit" value="この情報で変更する" class="btn btn-warning btn-sm">
            <input type="hidden" name="ac" value="<%= session.getAttribute("ac")%>">
        </form>
    <%}else{%>
        <h3>入力が不完全です</h3>
        <%= jh.chkinput(chkList)%>
    <%}%>
    <br>
        <form action="Myupdate" method="POST">
            <input type="submit" value="入力画面に戻る" class="btn btn-default btn-sm">
            <input type="hidden" name="mode" value="reinput">
            <input type="hidden" name="ac" value="<%= session.getAttribute("ac")%>">
        </form>           
    </body>
</html>
