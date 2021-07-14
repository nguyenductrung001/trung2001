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
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.10.2.js"></script>
<script type="text/javascript">


//nó là hai hàm js này đọc dễ hiểu vkl.
function selectAllHandle(){
	document.getElementsByName("PcId").forEach(// duyệt tất cả các ô id kia. với mỗi ô thi set cho cái giá trị checked của nó 
			//bằng với giá trị của ô checkboxall
// 			cho vào cái onchange ở ô checkboxall khi đổi là nó sẽ auto thay đổi các ô bên dưới.
			(item)=>{
				item.checked = document.getElementById("checkBoxAll").checked;
					});
	
	}
	
	
	// ok phần tự check nhé :D oke 
	//cái này thì làm ngược lại một chút
	//là khi mà một trong số các ô bên dưới thay đổi thì check các ô còn lại có cùng check hoặc cùng bbor check hay không 
	// rồi set lại ô bên trên theo là cùng check hoặc bỏ check.
function selectOneHandle(){
	let isSame = true;
	let isSel = document.getElementsByName("PcId").item(0).checked;
	const list = document.getElementsByName("PcId");
	for(i = 0;i <list.length;i++){
        let row = list.item(i);
		if(row.checked != isSel){
			isSame=false;
            break;
		}
    }
			
	if (isSame)
		document.getElementById("checkBoxAll").checked=isSel;
	}
	
	// cái này là để confirm xóa với set cái tham số đầu vào.
function deleteAllHandle(){
	if(confirm("Xác nhận xóa")){
		const list = [];
		// đầu tiên là cho một list rỗng để chứa những dòng đư
		document.getElementsByName("PcId").forEach(e=>{ // với mỗi dòng thì ta làm như sau
			// kiểm tra xem là dòng đó có chọn hay không.
			//nếu có thì add cái id nó vào list. ở đây mình lấy cái id từ value ra mình đã gắn sẵn bên dưới. chỗ đó.
			if(e.checked)list.push(e.value)
			});
		// sau khi xong thì mình sẽ được một list các id đã check rồi giờ gán vào param  của link thôi cái đó.
		document.getElementById("deleteAll").href="/pc/delete?ids="+list;
		return true;
	}else{
		return false;
	}
}
	//quên chưa gắn id cho nó
</script>
</head>
<body>
	<jsp:include page="/header" />
	<h2 style="text-align: center">List of Pc</h2>
	<!-- <a href="/pc/new">New</a>  -->
		 

	<form>
		<select name="selectedType" onchange="submit()"
			class="form-control col-2">
			<option value="">All</option>
			<c:forEach items="${pcTypes }" var="type">
				<option value="${type }" ${type==selectedType?"selected":"" }>${type }</option>
			</c:forEach>
		</select>
		<a  class="btn btn-danger" id="deleteAll" onclick="return deleteAllHandle() ">Delete All</a>
	</form>
	<table class="table table-bordered">
		<thead>

			<tr>
				<td><input type="checkbox" class="chkCheckBoxId"
					value="${pc.pcId }" name="PcId" onchange=" selectOneHandle()" /></td>
				<th>Id</th>
				<th>PcType</th>
				<th>Name</th>
				<th>Author</th>
				<th>Image</th>
				<th>Date</th>
				<th>Quantity</th>
				<th>Price</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pcs.getContent()}" var="pc">
				<tr>
					<td><input type="checkbox" class="chkCheckBoxId"
						value="${pc.pcId }" name="PcId" onchange=" selectOneHandle()" /></td>
					<td>${pc.pcId}</td>
					<td>${pc.type}</td>
					<td>${pc.name}</td>
					<td>${pc.author}</td>
					<td> <img alt="" src="${pc.image}" width="50px" height="50px"> </td>
					<td><fmt:formatDate var="date" value="${pc.date}"
							pattern="dd/MM/yyyy" /> ${date}</td>
					<td>${pc.quantity}</td>
					<td>${pc.price}</td>
					<td>
						
							<a href="/pc/${pc.pcId}" type="button" class="btn btn-info" onclick="return confirm('Bạn có muốn update sản phẩm này không?')">Detail</a>
						
					</td>
					<td>

						
							<a  type="button" class="btn btn-danger" href="/pc/delete/${pc.pcId}" onclick="return confirm('xac nhan xoa')" >Delete</a>
						
					</td>
				</tr>
			</c:forEach>
		</tbody>


	</table>
	<div class="btn-group">
		<c:if test="${pcs.getNumber() > 0}">

			<a class="btn btn-primary"
				href="/pc/pagination?currentPage=${pcs.getNumber()}&selectedType=${selectedType}">Back</a>

		</c:if>
		<span class="btn btn-secondary " disabled> Page:
			${pcs.getNumber() + 1}/${pcs.getTotalPages()} </span>

		<c:if test="${pcs.getNumber() + 1 <pcs.getTotalPages() }">

			<a class="btn btn-primary"
				href="/pc/pagination?currentPage=${pcs.getNumber() + 2}&selectedType=${selectedType}">
				Next</a>
		</c:if>
	</div>
	<jsp:include page="/footer" />
</body>
</html>
