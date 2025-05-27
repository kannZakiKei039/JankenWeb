<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ユーザー登録</title>
</head>
<body>
    <h2>新規ユーザー登録</h2>

    <form action="RegisterServlet" method="post">
        <label>ユーザー名: <input type="text" name="username" required></label><br><br>
        <label>ログインID: <input type="text" name="login_id" required></label><br><br>
        <label>パスワード: <input type="password" name="password" required></label><br><br>
        <input type="submit" value="登録">
    </form>

    <p><a href="login.jsp">ログイン画面へ戻る</a></p>
</body>
</html>