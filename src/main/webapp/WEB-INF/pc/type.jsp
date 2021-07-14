<%@ taglib prefix="form" uri ="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<title>List of Pc</title>
</head>
<body>
	<h2 style ="text-align:center">List of Pc</h2>
	<!-- <a href="/pc/new">New</a>  -->
	
	<button type="button" class="btn btn-warning"><a href="/pc/new">New</a></button>
	<table class="table table-dark" border=1>
		<thead>
		<tr>
			<th>
				<form>
					<select type = "submit" name ="typeName" onchange="submit()">
						<option value ="">
							all
						</option>
					<c:forEach items="${pcType}" var="item">
						<option value="${item}" ${item == curType?"selected":""}>
							${item}
						</option>
					</c:forEach>
					</select>
				</form>
			</th>
		</tr>
			<tr>
				<th>Id</th>
				<th>PcType</th>
				<th>Name</th>
				<th>Author</th>
				<th>Date</th>
				<th>Quantity</th>
				<th>Price</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pcs}" var="pc">
				<tr>
					<td>${pc.id}</td>
					<td>${pc.type}</td>
					<td>${pc.name}</td>
					<td>${pc.author}</td>
					<td><fmt:formatDate var="date" value="${pc.date}"
							pattern="dd/MM/yyyy" /> ${date}</td>
					<td>${pc.quantity}</td>
					<td>${pc.price}</td>
					<td> <button type="button" class="btn btn-danger"><a href="/pc/${pc.id}" style="color:white">Detail</a> </button> 
					 <button type="button" class="btn btn-secondary"><a href="/pc/delete/${pc.id}" style="color:white">Delete</a></button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
