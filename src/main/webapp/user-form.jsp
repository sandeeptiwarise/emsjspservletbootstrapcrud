<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Admin-EMS</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				<a href="#" class="navbar-brand"> Dashboard - EMS Version 1.0</a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Show All Records</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${employee != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${employee == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${employee != null}">
            			Edit User
            		</c:if>
						<c:if test="${employee == null}">
            			Add New User
            		</c:if>
					</h2>
				</caption>

				<c:if test="${employee != null}">
					<input type="hidden" name="txtemail" value="<c:out value='${employee.empemail}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Employee Email</label> <input type="text"
						value="<c:out value='${employee.empemail}' />" class="form-control"
						name="txtemail" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Address</label> <input type="text"
						value="<c:out value='${employee.empaddress}' />" class="form-control"
						name="txtaddress">
				</fieldset>

				<fieldset class="form-group">
					<label>Designation</label> <input type="text"
						value="<c:out value='${employee.empdesig}' />" class="form-control"
						name="txtdesig">
				</fieldset>
				
				
				<fieldset class="form-group">
					<label>Salary</label> <input type="text"
						value="<c:out value='${employee.empsalary}' />" class="form-control"
						name="txtsalary">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>