<%-- 
    Document   : myupdate
    Created on : 2016/11/21, 16:55:40
    Author     : Kouta
--%>
<%@page import="kagoyume.JumsHelper"%>
<%@page import="kagoyume.UserData"%>
<%
    UserData ud = (UserData)session.getAttribute("ud");
    boolean reinput = false;
    UserData updateUd = null;
    if(request.getParameter("mode") != null && request.getParameter("mode").equals("reinput")){
        reinput = true;
        updateUd = (UserData)session.getAttribute("updateUd");
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>会員情報変更</title>
        <!-- BootstrapのCSS読み込み -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- jQuery読み込み -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- BootstrapのJS読み込み -->
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <header style="background-color:#eee">会員情報</header>
        <h3>会員情報変更</h3>
        <form action="MyupdateConfirm" method="post">
            ユーザー名：<input type="text" name="userName" size="12"
                        value="<%if(reinput){out.print(updateUd.getName());}else{out.print(ud.getName());}%>"><br>
            パスワード：<input type="text" name="password" size="12"
                        value="<%if(reinput){out.print(updateUd.getPass());}else{out.print(ud.getPass());}%>"><br>
            メール：<input type="text" name="mail" size="30" 
                          value="<%if(reinput){out.print(updateUd.getMail());}else{out.print(ud.getMail());}%>"><br>
            住所：<input type="text" name="address" size="30"
                     value="<%if(reinput){out.print(updateUd.getAddress());}else{out.print(ud.getAddress());}%>"><br>
            <br>
            <input type="submit" name="btn" value="変更" class="btn btn-warning btn-sm">
            <input type="hidden" name="ac" value="<%= session.getAttribute("ac")%>">
            <input type="reset" value="リセット" class="btn btn-default btn-sm">
            <br><br>
            <a href="Mydata">マイページへ</a><br>
            <%= JumsHelper.getInstance().home()%>
        </form>
    </body>
</html>
