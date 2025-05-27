<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
<h2>ログイン</h2>
<form action="LoginServlet" method="post">
	ログインID：<input type="text" name="login_id" required><br>
	パスワード:<input type="password" name="password" required><br>
<input type="submit" value="ログイン"><br>
</form>
<p><a href="register.jsp">新規登録はこちら</a></p>
<%
	String error = request.getParameter("error");
	if("1".equals(error)){
%>
<p style="color:red;">ログインに失敗しました。IDまたはパスワードが間違っています。</p>
<% 
	}
%>
</body>
</html>