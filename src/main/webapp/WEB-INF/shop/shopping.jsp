  
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Book Store</h2>
	<a href="/pc/new">Go to Cart</a>
	<table border=1>
		<thead>
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
			<c:forEach items="${page.getContent()}" var="book">
				<tr>
					<td>${book.pcId}</td>
					<td>${book.type}</td>
					<td>${book.name}</td>
					<td>${book.author}</td>
					<td><fmt:formatDate var="publishedDate"
							value="${pc.date}" pattern="dd/MM/yyyy" />
						${publishedDate}</td>
					<td>${pc.quantity}</td>
					<td>${pc.price}</td>
					<td><a href="/shop/addToCart/${pc.pcId}">Add to Cart</a> </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<p>
		<c:if test="${page.getNumber() > 0 }">
			<a href="/pc/pagination?currentPage=${page.getNumber() - 1}">Prev</a>
		</c:if>
		Page: ${page.getNumber() + 1} / ${page.getTotalPages()}
		<c:if test="${page.getNumber() < page.getTotalPages() - 1 }">
			<a href="/pc/pagination?currentPage=${page.getNumber() + 1}">Next</a>
		</c:if>
	</p>
</body>
</html>