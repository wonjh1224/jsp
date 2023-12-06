<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container">
<h1>Hello my First JSP World!</h1>

<c:if test="${session.id eq null }">
<form action="/memb/login" method="post">
ID: <input type="text" name="id" placeholder="ID">
PWD : <input type="password" name="pwd" placeholder="pwd">
<button type="submit">login</button>
</form>
</c:if>

<div>
<c:if test="${session.id ne null }">
${session.id }님이 로그인 하셨습니다. <br>
계정생성일 : ${session.regdate } <br>
마지막접속 : ${session.lastlogin }<br>
<a href="memb/detail"> <button>회원정보수정</button> </a>
<c:if test="${session.id == 'ezen' }">
<a href="memb/list"> <button>회원리스트</button> </a>
</c:if>
<a href="/brd/register"><button>글쓰기 페이지로 이동</button></a>
<a href="memb/logout"> <button>로그아웃</button> </a>
</c:if>
</div>

<br>
<hr>
<br>

<a href="/memb/join"> <button>회원가입</button> </a>
<a href="/brd/list"><button>게시판 리스트로 이동</button></a>
</div>	
<script type="text/javascript">
const msg_login = `<c:out value="${msg_login}"/>`;
console.log(msg_login)
if(msg_login == '-1'){
	alert("로그인 정보가 일치하지 않습니다.")
}
const msg_modify = `<c:out value="${msg_modify}"/>`;
if(msg_modify==1){
	alert("수정성공")
}
</script>
</body>
</html>