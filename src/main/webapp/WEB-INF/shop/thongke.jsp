<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
</head>
<body>
	<a class="btn btn-primary" href="/pc/home">back</a>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>order Id</th>
				<th>pc Id</th>
				<th>Price</th>
				<th>Quantity</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${order.orderDetails}" var="orderDetail">
				<tr>
					<td>${orderDetail.pc.order_id}</td>
					<td>${orderDetail.pc.pcId}</td>
					<td>${orderDetail.price}</td>
					<td>${orderDetail.quantity}</td>
					

				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>