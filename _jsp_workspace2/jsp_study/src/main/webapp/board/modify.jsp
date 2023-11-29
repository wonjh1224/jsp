<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Detail Page</h1>
	<form action="/brd/edit" method="post">
		<table border="1">
			<tr>
				<th>bno</th>
				<td><input type="text" name="bno" value="${bvo.bno }"
					readonly="readonly"></td>
			</tr>
			<tr>
				<th>title</th>
				<td><input type="text" name="title" value="${bvo.title }">
				</td>
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
				<td>${bvo.readcount }</td>
			</tr>
			<tr>
				<th>content</th>
				<td><textarea rows="10" cols="30" name="content">${bvo.content }</textarea>
				</td>
			</tr>
		</table>

		<button type="submit">edit</button>

	</form>



	<a href="/brd/list">
		<button>list로 이동</button>
	</a>

</body>
</html>