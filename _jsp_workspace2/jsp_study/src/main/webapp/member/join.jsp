<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>회원가입 페이지</h1>
<form action="/memb/register" method="post">
ID: <input type="text" name="id"> <br>
PW: <input type="password" name="pwd"> <br>
E-mail: <input type="text" name="email"> <br>
age: <input type="text" name="age"> <br>
<button type="submit">회원가입 하기</button>
</form>
</body>
</html>