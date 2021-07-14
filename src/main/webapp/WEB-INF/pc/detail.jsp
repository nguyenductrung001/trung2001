<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Pc Detail</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
</head>
<body>
<jsp:include page="/header"/>
	<!-- <a href="/pc">Back to List</a> -->
	<h2 style ="text-align:center">Detail of Computer - ${pcModel.pcId}</h2>
	<div class="row">

		<div class="col-12">
			<div class="col-6 offset-3">
				<form:form   action="/pc/update" modelAttribute="pcModel" method="PUT">
					<form:input type="hidden" path="pcId" value="${pcModel.pcId}" />
					<div class="mt-3">
						<form:label class="form-label" path="name">Name</form:label>
						<form:input path="name" class="form-control"
							value="${pcModel.name}" />
					</div>
					<div class="mt-3">
						<form:label class="form-label" path="author">Author</form:label>

						<form:input path="author" class="form-control"
							value="${pcModel.author}" />
					</div>
					<div class="mt-3">
						<form:label class="form-label" path="image">Image</form:label>

						<form:input path="image" class="form-control"
							value="${pcModel.image}" />
					</div>
					<div class="mt-3">
						<form:label class="form-label" path="type">Type</form:label>
						<form:select path="type" class="form-control" items="${pcTypes}" />
					</div>
					
					<div class="mt-3">
						<form:label class="form-label" path="date"> Date</form:label>
						<fmt:formatDate var="date" value="${pcModel.date}"
							pattern="yyyy-MM-dd" />
						<form:input path="date" class="form-control" type="date"
							value="${date}" />
					</div>
					<div class="mt-3">
						<form:label class="form-label" path="quantity">Quantity</form:label>
						<form:input path="quantity" class="form-control" type="number"
							value="${pcModel.quantity}" />
					</div>
					<div class="mt-3">
						<form:label class="form-label" path="price">Price</form:label>
						<form:input path="price" class="form-control" type="number"
							value="${pcModel.price}" />
					</div>
					<div class="mt-3">
						<input type="submit" value="Update" />
					</div>
				</form:form>
			</div>

		</div>

	</div>
	<jsp:include page="/footer"/>
</body>
</html>
