<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>じゃんけんゲーム</title>
</head>
<body>
<h1>じゃんけんゲーム</h1>
<form action="GameServlet" method="post">
<p>あなたの手を選んでください：</p>
<input type ="radio" name="hand" value="0" required>グー<br>
<input type="radio" name="hand" value="1"> チョキ<br>
        <input type="radio" name="hand" value="2"> パー<br>
        <br>
        <input type="submit" value="勝負！">
    </form>
 
</body>
</html>