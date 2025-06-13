<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 목록</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/list.css">
</head>
<body>
    <h2>회원 목록</h2>
    <table>
        <thead>
            <tr>
                <th>번호</th>
                <th>회원구분</th>
                <th>아이디</th>
                <th>이름</th>
                <th>전화번호</th>
                <th>주소</th>
                <th>가입일</th>
                <th>최종수정일</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${userList}" var="user">
                <tr>
                    <td>${user.idx}</td>
                    <td>${user.userType}</td>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.phone}</td>
                    <td>${user.address}</td>
                    <td>${user.created}</td>
                    <td>${user.lastUpdated}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p><a href="/">홈으로</a></p>
</body>
</html>