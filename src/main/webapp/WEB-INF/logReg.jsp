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
<title>Login and Register</title>
</head>
<body>
	<div class="container">
		<h1>Book Club</h1>
		<p>A place for friends to share thoughts on books</p>
		<div class="column">
			<div class="register">
				<h2>Register</h2>
				<form:form action="/register" method="post" modelAttribute="newUser">
					<div class="user">
						<form:label path="name">Name:</form:label>
						<form:input path="name"/>
						<form:errors path="name"/>
					</div>
					<div class="email">
						<form:label path="email">Email:</form:label>
						<form:input path="email"/>
						<form:errors path="email"/>
					</div>
					<div class="password">
						<form:label path="password">Password:</form:label>
						<form:input path="password" type="password"/>
						<form:errors path="password"/>
					</div>
					<div class="confirm">
						<form:label path="confirm">Confirm Password:</form:label>
						<form:input path="confirm" type="password"/>
						<form:errors path="confirm"/>
					</div>
					<button class="submit-reg">Register</button>
				</form:form>
			</div>
			<div class="login">
				<h2>Login</h2>
				<form:form action="/login" method="post" modelAttribute="newLogin">
					<div class="logEmail">
						<form:label path="email">Email:</form:label>
						<form:input path="email"/>
						<form:errors path="email"/>
					</div>
					<div class="logPassword">
						<form:label path="password">Password:</form:label>
						<form:input path="password" type="password"/>
						<form:errors path="password"/>
					</div>
					<button class="submit-log">Login</button>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>