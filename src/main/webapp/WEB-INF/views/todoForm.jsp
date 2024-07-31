<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 할일 추가</title>
</head>
<body>
	<h1> ToDo Page </h1>
	<%-- http://localhost:8080/mvc/todo/add --%>
	<form action="add" method="post">
		<label for ="title">제목 : </label>
		<input type="text" id="title" name="title"value="코딩연습 무한반복">
		
		<label for ="description">설명 : </label>
		<textarea rows="50" cols="50"id="description" name="description">
			성공하면 높은 연봉은 기본 
		</textarea>
		
		<label for ="dueDate">마감기한 : </label>
		<input type="date" id="dueDate" name="dueDate"value="2024-07-11">
		
		
		<label for ="completed">마감기한 : </label>
		<input type="checkbox" id="completed" name="completed">
		<button type="submit"> 추가 </button>
	</form>
	<br><br>
	<a href ="list"> 목록으로 돌아가기 </a>
</body>
</html>