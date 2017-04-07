<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome page</title>
</head>
<body>
	<form action="MainServlet" method="GET">
		<input name="action" type="hidden" value="logout" /> <input
			value="logout" type="submit" />
	</form>

	<c:forEach items="${books}" var="i">
		<div class="form-group">
			<label for="title">Title:</label>
			<c:out value="${i.getTitle()}" />
		</div>
		<div class="form-group">
			<label for="author">Author:</label>
			<c:out value="${i.getAuthor()}" />
		</div>
		<div class="form-group">
			<label for="price">Price:</label>
			<c:out value="${i.getPrice()}" />
		</div>
		<!-- <h1>The last added books are:</h1> -->
		<br>
	</c:forEach>
</body>
</html>