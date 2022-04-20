<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show Book</title>
</head>
<body>
	<div class="container">
		<div class="header">
			<h1>${book.title }</h1>
			<a href="/dashboard">Back to the Shelves</a>
		</div>
		<div class="showBook">
			<h4>${book.reader.name } read ${book.title } by ${book.author}</h4>
			<h5>Here are ${book.reader.name }'s thoughts:</h5>
			<p class="thoughts">${book.thoughts }</p>
		</div>
		<c:if test="${user_id == book.reader.id}"><a href="/books/${book.id }/edit">Edit</a></c:if>
	</div>
</body>
</html>