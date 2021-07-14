<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
	<h2 style="text-align: center">Đơn hàng số : ${order.orderId}</h2>
	<%-- 	<div class="text-nowrap" style="width: 8rem;">
		${order.createdDate}
		</div> --%>

	<a class="btn btn-primary" href="/pc/home">back</a>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>Pc Id</th>
				<th >Quantity</th>
				<th>Price</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${order.orderDetails}" var="orderDetail">
				<tr>
					<td>${orderDetail.pc.pcId}</td>
					<td> ${orderDetail.quantity}</td>
					<td>${orderDetail.price}</td>

				</tr>
			</c:forEach>
		</tbody>
		</table>
					<h2 style="text-align: center" > Hóa Đơn Thanh Toán</h2>
		<table class="table table-bordered">
			<thead>
				<th>Sum price</th>
				<th>Purchase date</th>
			</thead>
			<tbody>
				<td>${sumPrice }</td>
				<td> <fmt:formatDate value="${order.createdDate}" var="date_format" pattern="dd-MM-YYYY"/> ${date_format}</td>
			</tbody>
		</table>

	
























	<%-- <p>Sum price: ${sumPrice }</p> --%>
</body>
</html>