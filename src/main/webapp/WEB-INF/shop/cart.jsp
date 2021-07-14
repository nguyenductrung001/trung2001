<%@page import="org.springframework.ui.Model"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Cart Info</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
	<jsp:include page="/header" />
	<h2 style="text-align:center;">Cart Info</h2>
	<a type="button"  class="btn btn-primary" href="/pc/home">back</a>


	<table class="table table-bordered">
		<thead class="thead-dark">
			<tr>
				<th>Pc Id</th>
				<th>Quantity</th>
				<th>Name</th>
				<th>Price</th>
				<th>Image</th>

			</tr>
		</thead>
		<tbody>

			<c:forEach items="${cartDetails}" var="cartDetail">
				<tr>
					<td>${cartDetail.key}</td>
					<td><input name="soluong" type="number"
						value="${cartDetail.value.quantity}" /></td>
					<td>${cartDetail.value.name}</td>
					<td>${cartDetail.value.price}</td>
					<td>  <img alt="" src="${cartDetail.value.image}" width="50px" height="50px"> </td>
				</tr>
			</c:forEach>

		</tbody>
	</table>

	<a class="btn btn-success" href="/shop/buy"
		onclick="return confirm('Bạn có chăc chắn muốn mua?')">Buy</a>
	<jsp:include page="/footer" />
</body>
</html>