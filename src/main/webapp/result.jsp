<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ジャンケン結果</title>
</head>
<body>
<h1>ジャンケンの結果</h1>
<%
String resultMessage =(String)request.getAttribute("resultMessage");
int[] resultArray =(int[])request.getAttribute("resultArray");//勝敗履歴

if(resultMessage != null){
%>
<p><%= resultMessage %></p>
<%}
if(resultArray != null){
%>
<h2>【総合勝敗表】（あなた）</h2>
<table border="1">
  <tr><th>引き分け</th><th>負け</th><th>勝ち</th></tr>
  <tr>
    <td><%= resultArray[0] %></td>
    <td><%= resultArray[1] %></td>
    <td><%= resultArray[2] %></td>
  </tr>
</table>
<%
}
%>
    <br>
    <a href="GameServlet">もう一度勝負する</a><br>
    <a href="index.jsp">戻る</a>
</body>
</html>