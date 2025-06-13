<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>회원가입</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/insert.css" />
</head>
<body>
	<h1>회원가입</h1>
    <form action="join_action" method="post">
        <input type="text" name="name" placeholder="이름 입력" />
        <input type="text" name="id" placeholder="ID 입력" />
        <input type="password" name="pwd" placeholder="PASSWORD 입력" />
        <input type="password" name="pwd2" placeholder="PASSWORD 확인값 입력" />
        <input type="text" name="phone" placeholder="연락처" />
        <input type="text" name="address" placeholder="주소" />

        <button type="submit">확인</button>
    </form>
</body>
</html>