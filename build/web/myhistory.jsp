<%-- 
    Document   : myhistory
    Created on : 2016/11/23, 17:13:52
    Author     : Kouta
--%>
<%@page import="kagoyume.JumsHelper"%>
<%@page import="kagoyume.CartDataBeans"%>
<%@page import="kagoyume.HistoryData"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    CartDataBeans buyHistory = (CartDataBeans)request.getAttribute("buyHistory");
    HistoryData hd = (HistoryData)request.getAttribute("hd");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>購入履歴</title>
        <!-- BootstrapのCSS読み込み -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- jQuery読み込み -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- BootstrapのJS読み込み -->
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <header style="background-color:#eee">会員情報</header>
        <h5>購入履歴</h5>
        <%if(request.getAttribute("historyEmpty") == null){%>
        <table border="3">
            <tr>
                <th>購入した商品</th>
                <th>価格</th>
                <th>配送方法</th>
                <th>購入日</th>
            </tr>
            <%for(int i = 0; i < buyHistory.getSize(); i ++){%>
            <tr>
                <td><%= buyHistory.getName(i)%></td>
                <td><%= buyHistory.getPrice(i)%>円</td>
                <td><%= jh.exTypenum(hd.getType(i))%></td>
                <td><%= hd.getBuyDate(i)%></td>
            </tr>
            <%}%>
        </table>
        <%}else{%>
        <h3>これまで購入した商品はありません。</h3>
        <%}%>
        <br>
        <a href="Mydata">マイページへ</a><br>
        <%= jh.home()%>
    </body>
</html>
