<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>회원 정보 수정</h1>
<form action="">
id: <input type="text" name="id" value="${ses.id }(수정불가)" readonly="readonly" style="background-color: gray;" > <br>
pwd: <input type="password" name="pwd" value="${ses.pwd }" > <br>
email: <input type="text" name="email" value="${ses.email }"> <br>
age: <input type="text" name="age" value="${ses.age }"> <br>
<button type="submit">수정</button>
</form>
<a href="/memb/remove"> <button>회원탈퇴</button>  </a>
</body>
</html>