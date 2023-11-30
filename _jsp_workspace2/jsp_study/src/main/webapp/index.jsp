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
<h1>게시판</h1>
<hr>
<c:if test="${ses.id eq null }">
<form action="/memb/login" method="post">
ID: <input type="text" name="id" placeholder="ID">
PWD: <input type="password" name="pwd" placeholder="pwd">
<button type="submit">login</button>
</form>
</c:if>

<div>
<c:if test="${ses.id ne null }">
${ses.id }님이 로그인 하셨습니다. <br>
계정생성일 : ${ses.regdate } <br>
마지막접속 : ${ses.lastlogin }<br>
<hr>
<a href="/brd/list"> <button>게시판 보기</button> </a>
<a href="/brd/myBoard"> <button>내가 쓴 글 보기</button> </a>
<a href="/memb/detail"> <button>회원정보수정</button> </a>
<a href="/memb/list"> <button>회원리스트</button> </a>
<a href="/brd/register"><button>글쓰기 페이지로 이동</button></a>
<a href="/memb/logout"> <button>로그아웃</button> </a>
</c:if>
</div>

<br>
<hr>
<c:if test="${ses.id eq null }">
<a href="/memb/join"> <button>회원가입</button> </a>
<a href="/brd/register"> <button>글쓰기</button></a>
</c:if>

<script type="text/javascript">
const msg_reg = `<c:out value="${msg_reg}"/>`;
if(msg_reg==-1){
	alert("회원가입 성공")
}
const msg_log = `<c:out value="${msg_log}"/>`;
if(msg_log==-1){
	alert("로그인 정보 없음")
}
const msg_logout = `<c:out value="${msg_logout}"/>`;
if(msg_log==-1){
	alert("로그아웃")
}
</script>
</body>
</html>