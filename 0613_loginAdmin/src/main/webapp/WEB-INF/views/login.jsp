<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>LOGIN</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/insert.css" />
</head>
<body>
	<h1>로그인</h1>
    <form action="login_action" method="post">
        <input type="text" name="id" placeholder="ID 입력" />
        <input type="password" name="pwd" placeholder="PASSWORD 입력" />

        <button type="submit">확인</button>
    </form>
</body>
</html>