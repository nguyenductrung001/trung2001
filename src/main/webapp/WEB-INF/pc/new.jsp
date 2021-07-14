<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ include file="/src/webapp/WEB-INF/pc/home.jsp" %> --%>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<title>New Pc</title>
</head>
<body>
<jsp:include page="/header"/>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	</nav>

	<!-- 	<a href="/pc" type="button" class="btn btn-primary">Back to List</a> -->

	<h2 style="text-align: center">Add a new Pc</h2>
	<div class="row">

		<div class="col-12">
			<div class="col-6 offset-3">
				<form:form method="POST" action="/pc/insert"
					modelAttribute="pcModel">
					<div class="mt-3">
						<form:label for="title" class="form-label" path="name">Name</form:label>
						<form:input type="text" class="form-control" path="name" />
					</div>
					<div class="mt-3">
						<form:label for="poster" class="form-label" path="author">Author</form:label>
						<form:input type="text" class="form-control" path="author" />
					</div>
					<div class="mt-3">
						<form:label for="image" class="form-label" path="image">Image</form:label>
						<form:input type="text" class="form-control" path="image" />
					</div>
					<div class="mt-3">
						<form:label class="form-label" path="type">Type</form:label>
						<form:select class="form-control" path="type" items="${pcTypes}" />
					</div>
					<div class="mt-3">
						<form:label for="description" class="form-label" path="date">Date</form:label>
						<form:input type="date" class="form-control" path="date" />
					</div>
					<div class="mt-3">
						<form:label for="description" class="form-label" path="quantity">Quantity</form:label>
						<form:input type="number" class="form-control" path="quantity" />
					</div>
					<div class="mt-3">
						<form:label for="description" class="form-label" path="price">Price</form:label>
						<form:input type="number" class="form-control" path="price" />
					</div>
					<div class="mt-3">
						<input type="submit" class="btn btn-success" value="Insert" />
					</div>

				</form:form>

			</div>

		</div>

	</div>
	<jsp:include page="/footer"/>
</body>
</html>
