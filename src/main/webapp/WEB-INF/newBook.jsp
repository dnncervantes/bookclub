<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New Book</title>
</head>
<body>
	<div class="header">
		<h1>Add a Book to Your Shelf</h1>
		<a href="/logout">logout</a>
		<a href="/dashboard">Back to the Shelves</a>
	</div>
	<div class="container">
		<form:form action="/books/new" method="post" modelAttribute="book">
		<form:hidden path="reader" value="${userId }"/>
			<div class="title">
				<form:label path="title">Title:</form:label>
				<form:input path="title"/>
				<form:errors path="title"/>
			</div>
			<div class="author">
				<form:label path="author">Author:</form:label>
				<form:input path="author"/>
				<form:errors path="author"/>
			</div>
			<div class="thoughts">
				<form:label path="thoughts">My thoughts:</form:label>
				<form:textarea path="thoughts" cols="40" rows="10"/>
				<form:errors path="thoughts"/>
			</div>
			<button class="subNewBook">Submit</button>
		</form:form>
		
	</div>
</body>
</html>