<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container" style="margin-top: 100px;">
		<h1>List Page</h1>

		<!-- search line -->
		<div>
			<form action="/brd/list" method="get">
				<c:set value="${ph.pvo.type }" var="typed"></c:set>
				<select name="type">
					<option ${typed == null ? 'selected' : ''}>Choose</option>
					<option value="t" ${typed == 't' ? 'selected' : ''}>제목</option>
					<option value="w" ${typed == 'w' ? 'selected' : ''}>작성자</option>
					<option value="c" ${typed == 'c' ? 'selected' : ''}>내용</option>
					<option value="tc" ${typed == 'tc' ? 'selected' : ''}>제목&내용</option>
					<option value="tw" ${typed == 'tw' ? 'selected' : ''}>제목&작성자</option>
					<option value="wc" ${typed == 'wc' ? 'selected' : ''}>작성자&내용</option>
					<option value="twc" ${typed == 'twc' ? 'selected' : ''}>제목&작성자&내용</option>
				</select> <input type="text" name="keyword" placeholder="search"
					value="${ph.pvo.keyword }"> <input type="hidden"
					name="pageNo" value="1"> <input type="hidden" name="qty"
					value="${ph.pvo.qty }">
				<button type="submit">Search</button>
				<span>검색결과 : ${ph.totalCount } 건</span>
			</form>
		</div>

		<div>
			<table class="table table-hover">
				<tr>
					<th>bno</th>
					<th>title</th>
					<th>writer</th>
					<th>content</th>
					<th>readCount</th>
				</tr>
				<!-- DB에서 가져온 리스트를 c:forEach로 반복 -->
				<c:forEach items="${list }" var="bvo">
					<tr>
						<td><a href="/brd/detail?bno=${bvo.bno}">${bvo.bno }</a></td>
						<td><a href="/brd/detail?bno=${bvo.bno}"><img alt="" src="/_fileUpload/thum_${bvo.imageFile}">${bvo.title }</a></td>
						<td>${bvo.writer }</td>
						<td>${bvo.regdate }</td>
						<td>${bvo.readCount }</td>
					</tr>
				</c:forEach>
			</table>
		</div>

		<!-- 페이지네이션 표시 구역 -->
		<div>
			<div style="display:flex; justify-content:center;">
				<!-- prev -->
				<c:if test="${ph.prev }">	
					<a
						href="/brd/list?pageNo=${ph.startPage-1 }&qty=${ph.pvo.qty}&type=${ph.pvo.type}&keyword=${ph.pvo.keyword}">
						◁ | </a>
				</c:if>

				<!-- paging -->
				<c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
					<a
						href="/brd/list?pageNo=${i }&qty=${ph.pvo.qty}&type=${ph.pvo.type}&keyword=${ph.pvo.keyword}">
						${i} </a>
				</c:forEach>

				<!-- next -->
				<c:if test="${ph.next }">
					<a
						href="/brd/list?pageNo=${ph.endPage+1 }&qty=${ph.pvo.qty}&type=${ph.pvo.type}&keyword=${ph.pvo.keyword}">
						| ▷ </a>
				</c:if>
			</div>

			<div style="display:flex; justify-content:center;"	>
				<a href="/brd/register" ><button>글쓰기</button></a> <a
					href="/index.jsp"><button>index</button></a>
			</div>

		</div>
	</div>

</body>
</html>