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
<title>Edit Book</title>
</head>
<body>
	<div class="header">
		<h1>Change your Entry</h1>
		<a href="/logout">logout</a>
		<a href="/dashboard">Back to the Shelves</a>
	</div>
	<div class="container">
		<form:form action="/books/${book.id }/edit" method="post" modelAttribute="book">
		<input type="hidden" name="_method" value="put"/>
		<form:hidden path="reader" value="${book.reader.id }"/>
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
			<button class="subBookEdit">Submit</button>
		</form:form>
		
			<form action="/books/${book.id}/delete" method="post">
			<input type="hidden" name="_method" value="delete"/>
			<button>Delete</button>
			</form>
		
	</div>
</body>
</html>