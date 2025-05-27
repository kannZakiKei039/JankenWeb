<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="game7.model.*" %>
  
    <%
    User user=(User)session.getAttribute("user");
    if(user == null){
    	response.sendRedirect("login.jsp");
    	return;
    }
    String username = user.getName();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホーム</title>
</head>
<body>
<h2>ようこそ、<%= username %>さん！</h2>
<p>ログイン成功です。</p><br>
<ul>
<li><a href="GameServlet">ジャンケンをする</a></li>
<li><a href="result.jsp">過去の対戦結果を見る</a></li>
<li><a href="LogoutServlet">ログアウト</a></li>


</ul>
</body>
</html>