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
	<h1>Modify Page</h1>
	<br>
	<img alt="" src="/_fileUpload/${bvo.imageFile}">
	<form action="/brd/edit" method="post" enctype="multipart/form-data">
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
				<td>${bvo.readCount }</td>
			</tr>
			<tr>
				<th>content</th>
				<td><textarea rows="10" cols="30" name="content">${bvo.content }</textarea>
				</td>
			</tr>
			<tr>
			<th>image_file</th>
			<td>
			<input type="hidden" name="image_file" value="${bvo.imageFile }">
			<input type="file" name="new_file">
			</td>
			</tr>
		</table>

		<button type="submit">수정</button>

	</form>

		<a href="/brd/remove?bno=${bvo.bno }">
			<button>삭제</button>
		</a>

	<a href="/brd/list">
		<button>list로 이동</button>
	</a>

</body>
</html>