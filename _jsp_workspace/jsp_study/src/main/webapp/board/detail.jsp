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
	<h1>Detail Page</h1>
	<table border="1">
		<tr>
			<th>bno</th>
			<td>${bvo.bno }</td>
		</tr>

		<tr>
			<th>title</th>
			<td>${bvo.title }</td>
		</tr>
		<tr>
			<th>writer</th>
			<td>${bvo.writer }</td>
		</tr>
		<tr>
			<th>regdate</th>
			<td>${bvo.regdate }</td>
		</tr>
		<tr>
			<th>moddate</th>
			<td>${bvo.moddate }</td>
		</tr>
		<tr>
			<th>readCount</th>
			<td>${bvo.readCount }</td>
		</tr>
		<tr>
			<th>content</th>
			<td>${bvo.content }</td>
		</tr>
	</table>

	<c:if test="${session.id==bvo.writer }">
		<a href="/brd/modify?bno=${bvo.bno }">
			<button>수정</button>
		</a>
		<a href="/brd/remove?bno=${bvo.bno }">
			<button>삭제</button>
		</a>
	</c:if>
	<a>
		<button onclick="history.back()">뒤로 가기</button>
	</a>
	<a href="/brd/list">
		<button>list로 이동</button>
	</a>

	<!-- comment line (댓글 등록) -->
	<hr>
	<div>
		Comment Line <br>
		 <input type="text" id="cmtWriter"value="${session.id }" readonly="readonly"> <br> 
		 <input type="text" id="cmtText" placeholder="Add Comment...">
		<button type="button" id="cmtAddBtn">댓글 등록</button>
	</div>
	<hr>
	

	<!-- 댓글 표시 라인 -->
	<div id="commentLine">
		<div>cno, bno, writer, regdate</div> <hr>
		<div>
	
			<button>수정</button>
			<button>삭제</button>
	
			<input value="content">
		</div>
	<hr>
	</div>

	<script type="text/javascript">
		const bnoVal = `<c:out value="${bvo.bno}"/>`;
		const logId = `<c:out value="${session.id}"/>`;
		console.log(bnoVal);
	</script>
	
	<script src="/resource/board_detail.js"></script>
	
	<script type="text/javascript">
	printCommentList(bnoVal);
	</script>

</body>
</html>