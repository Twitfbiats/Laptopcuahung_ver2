<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Manufacturer Manager</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<c:url value='/Frontend/css/quite_good2.css' />"/>
</head>

<body>
	<jsp:include page="template/header.jsp"></jsp:include>
	<jsp:include page="template/sidebar.jsp"></jsp:include>
	
	<div id="leaves">
		<i></i>
		<i></i>
		<i></i>
		<i></i>
		<i></i>
		<i></i>
		<i></i>
		<i></i>
		<i></i>
		<i></i>
		<i></i>
		<i></i>
		<i></i>
		<i></i>
		<i></i> 
	</div>

	<div class="col-md-9 animated bounce" id="TableManufacturerFather">
		<h3 class="page-header">Manufacturer Management</h3>

		<button class="btn btn-success btnAddManufacturer">Add Manufacturer</button>

		<hr/>

		<table class="table table-hover manufacturerTable">
			<thead>
				<tr>
					<th>ID</th>
					<th>Manufacturer Name</th>
					<th></th>
				</tr>
			</thead>

			<tbody>

			</tbody>
		</table>

		<div class="testT">
			<ul class="pagination"></ul>
		</div>
	</div>

	<div class="row col-md-6">
		<form class="manufacturerForm" id="form">
			<div>
				<div class="modal fade" id="manufacturerModal" tabindex="-1"
					role="dialog" aria-labelledby="exampleModalLabel"
					aria-hidden="true" data-backdrop="static" data-keyboard="false">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">Create New/Update Manufacturer</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>

							<div class="modal-body">
								<div class="form-group" id="form-group1">
									<label for="id">ID:</label> <input type="text"
										class="form-control" id="id" readonly value=""/>
								</div>

								<div class="form-group">
									<label for="name">Manufacturer Name:</label> <input type="text"
										class="form-control" id="nameOfManufacturer" placeholder="Enter Manufacturer Name" required />
								</div>
								
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">Cancel</button>
									<input class="btn btn-primary" id="btnSubmit" type="button"
										value="Confirm"/>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>

	<jsp:include page="template/footer.jsp"></jsp:include>
	<script src="<c:url value='/js/ManufacturerAjax.js'/>"></script>
</body>

</html>