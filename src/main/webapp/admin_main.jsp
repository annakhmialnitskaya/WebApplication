<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin page</title>
</head>
<body>
	<c:if test="${not empty MESSAGE}">
		<c:out value="${MESSAGE}" />

	</c:if>
	<!-- 
<c:forEach items="${books}" var="i">
		<h1>The last added books are:</h1>
		<br>
			<span>Book</span>
			<h3>title: </h3> <c:out value="${i.getTitle()}" />
		<br>
	</c:forEach>
	-->
	<form action="MainServlet" method="GET">
		<input name="action" type="hidden" value="logout" /> <input
			value="Logout" type="submit" />
	</form>
	<form action="MainServlet" method="GET">
		<input name="action" type="hidden" value="addBook" />
		<div class="form-group">
			<label for="title">Title:</label> <input name="title" type="text" />
		</div>
		<div class="form-group">
			<label for="author">Author:</label> <input name="author" type="text" />
		</div>
		<div class="form-group">
			<label for="price">Price:</label> <input name="price" type="text" />
		</div>
		<input name="submit" value="Add book" type="submit" />
	</form>

</body>
</html>