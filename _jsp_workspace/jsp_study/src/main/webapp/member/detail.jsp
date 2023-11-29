<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Detail Page</h1>
<form action="/memb/modify" method="post">
id: <input type="text" name="id" value="${session.id }" readonly="readonly"><br>
pw: <input type="text" name="pwd" value="${session.pwd }"><br>
email: <input type="text" name="email" value="${session.email }"><br>
age: <input type="text" name="age" value="${session.age }"><br>
<button type="submit">수정</button>
</form>
<a href="/memb/remove">
<button>회원탈퇴</button>
</a>
</body>
</html>