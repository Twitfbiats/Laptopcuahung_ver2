<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Manage</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.modal-open {
	overflow: scroll;
}
</style>
</head>
<body>
	<jsp:include page="template/header.jsp"></jsp:include>
	<jsp:include page="template/sidebar.jsp"></jsp:include>
	<link rel="stylesheet" href="<c:url value='/Frontend/css/simplePagination.css' />" />

	<div class="col-md-9 animated bounce">
		<h3 class="page-header">Product Management</h3>

		<div class="form-group form-inline">
			<label for="sel1">ADD PRODUCT BY CATEGORY: </label> 
			<!-- <select id="categoryDropdown" class="form-control">
				<c:forEach var="category" items="${categories}">
					<option value="${category.id }">${category.name}</option>
				</c:forEach>
			</select> -->

			<button type="button" class="btn btn-primary" id="add-laptop">
			Laptop	
			</button>
		</div>
		<hr>

		<form class="form-inline" id="searchForm" name="searchObject">
			<div class="form-group">
				<select class="form-control" name="categoryId" id="category">
					<c:forEach var="category" items="${categories}">
						<option value="${category.id}">${category.name}</option>
					</c:forEach>
				</select>
			</div>

			<div class="form-group">
				<select class="form-control" name="manufacturerId" id="manufacturer">
					<option value="">All Manufacturer</option>
					<c:forEach var="manufacturer" items="${manufacturers}">
						<option value="${manufacturer.id}">${manufacturer.name}</option>
					</c:forEach>
				</select>
			</div>

			<div class="form-group">
				<select class="form-control" name="donGia" id="price">
					<option value="">All Price</option>
					<option value="Under 2 millions">Under 2 millions</option>
					<option value="2 to 4 millions">2 to 4 millions</option>
					<option value="4 to 6 millions">4 to 6 millions</option>
					<option value="6 to 10 millions">6 to 10 millions</option>
					<option value="Above 10 millions">Above 10 millions</option>
				</select>
			</div>

			<div class="form-group">
				<select class="form-control" name="sortByPrice">
					<option value="asc">Price Up</option>
					<option value="desc">Price Down</option>
				</select>
			</div>

			&nbsp;&nbsp;
			<button type="button" class="btn btn-primary" id="btnSearchProduct">Search Product</button>
		</form>

		<div class="form-group form-inline"
			style="float: right; margin-right: 10px; top: -33px; position: relative;"">
			<input class="form-control" type="text" id="searchById"
				placeholder="Find By Id"> <span
				class="glyphicon glyphicon-search" aria-hidden="true"
				style="left: -30px; top: 4px;"></span>
		</div>

		<hr />
		<table class="table table-hover productTable "
			style="text-align: center;">
			<thead>
				<tr>
					<th>Image</th>
					<th>Product Name</th>
					<th>Category</th>
					<th>Manufacturer</th>
					<th>Price</th>
					<th>Quantity</th>
				</tr>
			</thead>
			<tbody>
			</tbody>

		</table>

		<div id="light-pagination" class="pagination"></div>
	</div>

	<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLongTitle">ADD OR UPDATE LAPTOP</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
				  <span aria-hidden="true">&times;</span>
				</button>
				</div>
				<div class="modal-body">
					<h1>Laptop</h1>
					<div class="container">
						<div class="row" hidden="hidden">
						<div class="col-md-2 col-lg-2"><label for="productId">ID</label></div>
						<div class="col-md-5 col-lg-5"><input type="text" id="productId" class="tooltip-test" title="OK"/></div>
						<div class="col-md-5 col-lg-5"></div>
						</div>
						<div class="row">
						<div class="col-md-2 col-lg-2"><label for="id">ID</label></div>
						<div class="col-md-5 col-lg-5"><input type="text" id="id" class="tooltip-test" title="OK" disabled/></div>
						<div class="col-md-5 col-lg-5"></div>
						</div>
						<div class="row">
						<div class="col-md-2 col-lg-2"><label for="name">Name</label></div>
						<div class="col-md-5 col-lg-5"><input type="text" id="name" /></div>
						<div class="col-md-5 col-lg-5"></div>
						</div>
						<div class="row">
						<div class="col-md-2 col-lg-2"><label for="size">Size</label></div>
						<div class="col-md-5 col-lg-5"><input type="text" id="size" /></div>
						<div class="col-md-5 col-lg-5"></div>
						</div>
						<div class="row">
						<div class="col-md-2 col-lg-2"><label for="weight">Weight</label></div>
						<div class="col-md-5 col-lg-5"><input type="text" id="weight" /></div>
						<div class="col-md-5 col-lg-5"></div>
						</div>
						<div class="row">
						<div class="col-md-2 col-lg-2"><label for="ramSlot">Ram Slot</label></div>
						<div class="col-md-5 col-lg-5"><input type="text" id="ramSlot" /></div>
						<div class="col-md-5 col-lg-5"></div>
						</div>
						<div class="row">
						<div class="col-md-2 col-lg-2"><label for="driveSlot">Drive Slot</label></div>
						<div class="col-md-5 col-lg-5"><input type="text" id="driveSlot"/></div>
						<div class="col-md-5 col-lg-5"></div>
						</div>
						<div class="row">
						<div class="col-md-2 col-lg-2"><label for="color">Color</label></div>
						<div class="col-md-5 col-lg-5"><input type="text" id="color" disabled/><select class="custom-select" id="color_select"><option class="select-an-option" disabled selected> -- select an option -- </option></select></div>
						</div>
						<div class="row">
						<div class="col-md-2 col-lg-2"><label for="material">Material</label></div>
						<div class="col-md-5 col-lg-5"><input type="text" id="material" disabled/><select class="custom-select" id="material_select"><option class="select-an-option" disabled selected> -- select an option -- </option></select></div>
						</div>
						<div class="row">
						<div class="col-md-2 col-lg-2"><label for="price">Price</label></div>
						<div class="col-md-5 col-lg-5"><input type="text" id="price" placeholder="1000000000" /></div>
						<div class="col-md-5 col-lg-5"></div>
						</div>
						<div class="row">
						<div class="col-md-2 col-lg-2"><label for="sold">Sold</label></div>
						<div class="col-md-5 col-lg-5"><input type="text" id="sold" value="100" /></div>
						<div class="col-md-5 col-lg-5"></div>
						</div>
						<div class="row">
						<div class="col-md-2 col-lg-2"><label for="stock">Stock</label></div>
						<div class="col-md-5 col-lg-5"><input type="text" id="stock" value="150" /></div>
						<div class="col-md-5 col-lg-5"></div>
						</div>
						<div class="row">
						<div class="col-md-2 col-lg-2"><label for="warranty">Warranty</label></div>
						<div class="col-md-5 col-lg-5"><input type="text" id="warranty" value="1 years" /></div>
						<div class="col-md-5 col-lg-5"></div>
						</div>
						<div class="row">
						<div class="col-md-2 col-lg-2"><label for="portInfo">Port Info</label></div>
						<div class="col-md-5 col-lg-5"><textarea type="text" id="portInfo" placeholder="1 HDMI &#10;1 VGA&#10; 2 USB"></textarea></div>
						<div class="col-md-5 col-lg-5"></div>
						</div>
						<div class="row">
						<div class="col-md-12 col-lg-12">
						<iframe name="images-iframe" hidden></iframe>
						<form action="http://localhost:8080/api/product/upload-images/" method="POST" enctype="multipart/form-data" id="laptop-images" target="images-iframe">
						<label for="img">Select Images For Product:</label>
						<input type="file" id="img" name="images" accept="image/*" multiple>
						</form>
						</div>
						</div>
						
						<div class="row header"><h2>CPUs</h2></div>
						<div id="cpu">
							<ul class="nav nav-pills nav-fill" id="cpu-nav">
								<li class="nav-item">
								<a class="nav-link" aria-current="page" id="create-new">Create New</a>
								</li>
								<li class="nav-item">
								<a class="nav-link" id="add-existing">Add Existing</a>
								</li>
							</ul>

							<div class="row">
							<div class="col-md-3 col-lg-3"></div>
							<div class="col-md-3 col-lg-3"><select name="cpu" id="cpu-select" hidden><option class="select-an-option" disabled selected> -- select an option -- </option></select></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="cpu-id">ID</label></div>
							<div class="col-md-2 col-lg-2"><input type="text" id="cpu-id" disabled/></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="cpu-model">Model</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="cpu-model" /></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="cpu-core">Core</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="cpu-core" /></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="cpu-thread">Thread</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="cpu-thread" /></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="cpu-processorSpeed">Processor Speed</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="cpu-processorSpeed" /></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="cpu-maxProcessorSpeed">Max Processor Speed</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="cpu-maxProcessorSpeed" /></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="cpu-additionalInfo">Additional Info</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="cpu-additionalInfo" value="" /></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
							<div class="row" hidden>
							<div class="col-md-2 col-lg-2"><label for="cpu-manufacturer-id">Manufacturer ID</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="cpu-manufacturer-id" disabled/></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="cpu-manufacturer">Manufacturer</label></div>
							<div class="col-md-5 col-lg-5" id="cpu-manufacturer">
								<input type="text" id="cpu-manufacturer-input" hidden/>
								<select name="manufacturer" id="cpu-manufacturer-select"><option class="select-an-option" disabled selected> -- select an option -- </option></select>
								<div id="cpu-manufacturer-radio-btn">
									<input type="radio" id="create-new-cpu-manufacturer" name="fav_language" value="create-new">
									<label for="create-new-cpu-manufacturer">Create New</label><br>
									<input type="radio" id="add-existing-cpu-manufacturer" name="fav_language" value="add-existing">
									<label for="add-existing-cpu-manufacturer">Choose exsiting</label><br>
								</div>
							</div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
						</div>
						
						<div class="row header"><h2>Display</h2></div>
						<div id="display">
							<ul class="nav nav-pills nav-fill" id="display-nav">
								<li class="nav-item">
								<a class="nav-link" aria-current="page" id="create-new">Create New</a>
								</li>
								<li class="nav-item">
								<a class="nav-link" id="add-existing">Add Existing</a>
								</li>
							</ul>

							<div class="row">
							<div class="col-md-3 col-lg-3"></div>
							<div class="col-md-3 col-lg-3"><select name="display" id="display-select" hidden><option class="select-an-option" disabled selected> -- select an option -- </option></select></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="display-id">ID</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="display-id" disabled/></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="display-size">Size</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="display-size" /></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="display-technology">Technology</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="display-technology" /></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="display-resolution">Resolution</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="display-resolution" /></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="display-displayType">Display Type</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="display-displayType" disabled/><select class="custom-select" id="display-type-select"><option class="select-an-option" disabled selected> -- select an option -- </option></select></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="display-refreshRate">Refresh Rate</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="display-refreshRate" /></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="display-panelType">Panel Type</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="display-panelType" disabled/><select class="custom-select" id="panel-type-select"><option class="select-an-option" disabled selected> -- select an option -- </option></select></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
						</div>

						<div class="row header"><h2>RAMs</h2></div>
						<div id="ram">
							<ul class="nav nav-pills nav-fill" id="ram-nav">
								<li class="nav-item">
								<a class="nav-link" aria-current="page" id="create-new">Create New</a>
								</li>
								<li class="nav-item">
								<a class="nav-link" id="add-existing">Add Existing</a>
								</li>
							</ul>

							<div class="row">
							<div class="col-md-3 col-lg-3"></div>
							<div class="col-md-3 col-lg-3"><select name="ram" id="ram-select" hidden><option class="select-an-option" disabled selected> -- select an option -- </option></select></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="ram-id">ID</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="ram-id" disabled/></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="ram-model">Model</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="ram-model" /></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="ram-size">Size</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="ram-size" /></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="ram-ramType">RAM Type</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="ram-ramType" disabled/><select class="custom-select" id="ram-type-select"><option class="select-an-option" disabled selected> -- select an option -- </option></select></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="ram-bus">Bus</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="ram-bus" /></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
							<div class="row" hidden>
							<div class="col-md-2 col-lg-2"><label for="ram-manufacturer-id">Manufacturer ID</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="ram-manufacturer-id" disabled/></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="ram-manufacturer">Manufacturer</label></div>
							<div class="col-md-5 col-lg-5" id="ram-manufacturer">
								<input type="text" id="ram-manufacturer-input" hidden/>
								<select name="manufacturer" id="ram-manufacturer-select"><option class="select-an-option" disabled selected> -- select an option -- </option></select>
								<div id="ram-manufacturer-radio-btn">
									<input type="radio" id="create-new-ram-manufacturer" name="fav_language" value="create-new">
									<label for="create-new-ram-manufacturer">Create New</label><br>
									<input type="radio" id="add-existing-ram-manufacturer" name="fav_language" value="add-existing">
									<label for="add-existing-ram-manufacturer">Choose exsiting</label><br>
								</div>
							</div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
						</div>

						<div class="row header"><h2>Drives</h2></div>
						<div id="drive">
							<ul class="nav nav-pills nav-fill" id="drive-nav">
								<li class="nav-item">
								<a class="nav-link" aria-current="page" id="create-new">Create New</a>
								</li>
								<li class="nav-item">
								<a class="nav-link" id="add-existing">Add Existing</a>
								</li>
							</ul>

							<div class="row">
							<div class="col-md-3 col-lg-3"></div>
							<div class="col-md-3 col-lg-3"><select name="drive" id="drive-select" hidden><option class="select-an-option" disabled selected> -- select an option -- </option></select></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="drive-id">ID</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="drive-id" disabled/></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="drive-model">Model</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="drive-model" /></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="drive-size">Size</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="drive-size" /></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="drive-driveType">Drive Type</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="drive-driveType" disabled/><select class="custom-select" id="drive-type-select"><option class="select-an-option" disabled selected> -- select an option -- </option></select></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="drive-maxWrite">Max Write</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="drive-maxWrite" /></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="drive-maxRead">Max Read</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="drive-maxRead"/></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
							<div class="row" hidden>
							<div class="col-md-2 col-lg-2"><label for="drive-manufacturer-id">Manufacturer ID</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="drive-manufacturer-id" disabled/></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="drive-manufacturer">Manufacturer</label></div>
							<div class="col-md-5 col-lg-5" id="drive-manufacturer">
								<input type="text" id="drive-manufacturer-input" hidden/>
								<select name="manufacturer" id="drive-manufacturer-select"><option class="select-an-option" disabled selected> -- select an option -- </option></select>
								<div id="drive-manufacturer-radio-btn">
									<input type="radio" id="create-new-drive-manufacturer" name="fav_language" value="create-new">
									<label for="create-new-drive-manufacturer">Create New</label><br>
									<input type="radio" id="add-existing-drive-manufacturer" name="fav_language" value="add-existing">
									<label for="add-existing-drive-manufacturer">Choose exsiting</label><br>
								</div>
							</div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
						</div>
						
						<div class="row header"><h2>Graphic Cards</h2></div>
						<div id="graphic-card">
							<ul class="nav nav-pills nav-fill" id="graphic-card-nav">
								<li class="nav-item">
								<a class="nav-link" aria-current="page" id="create-new">Create New</a>
								</li>
								<li class="nav-item">
								<a class="nav-link" id="add-existing">Add Existing</a>
								</li>
							</ul>

							<div class="row">
							<div class="col-md-3 col-lg-3"></div>
							<div class="col-md-3 col-lg-3"><select name="graphic-card" id="graphic-card-select" hidden><option class="select-an-option" disabled selected> -- select an option -- </option></select></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="graphic-card-id">ID</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="graphic-card-id" disabled/></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="graphic-card-model">Model</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="graphic-card-model" /></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="graphic-card-memory">Memory</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="graphic-card-memory" /></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="graphic-card-type">Graphic Card Type</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="graphic-card-type" disabled/><select class="custom-select" id="graphic-card-type-select"><option class="select-an-option" disabled selected> -- select an option -- </option></select></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
							<div class="row" hidden>
							<div class="col-md-2 col-lg-2"><label for="graphic-card-manufacturer-id">Manufacturer ID</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="graphic-card-manufacturer-id" disabled/></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="graphic-card-manufacturer">Manufacturer</label></div>
							<div class="col-md-5 col-lg-5" id="graphic-card-manufacturer">
								<input type="text" id="graphic-card-manufacturer-input" hidden/>
								<select name="manufacturer" id="graphic-card-manufacturer-select"><option class="select-an-option" disabled selected> -- select an option -- </option></select>
								<div id="graphic-card-manufacturer-radio-btn">
									<input type="radio" id="create-new-graphic-card-manufacturer" name="fav_language" value="create-new">
									<label for="create-new-graphic-card-manufacturer">Create New</label><br>
									<input type="radio" id="add-existing-graphic-card-manufacturer" name="fav_language" value="add-existing">
									<label for="add-existing-graphic-card-manufacturer">Choose exsiting</label><br>
								</div>
							</div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
						</div>
						
						<div class="row header"><h2>Operating System</h2></div>
						<div id="operating-system">
							<ul class="nav nav-pills nav-fill" id="operating-system-nav">
								<li class="nav-item">
								<a class="nav-link" aria-current="page" id="create-new">Create New</a>
								</li>
								<li class="nav-item">
								<a class="nav-link" id="add-existing">Add Existing</a>
								</li>
							</ul>

							<div class="row">
							<div class="col-md-3 col-lg-3"></div>
							<div class="col-md-3 col-lg-3"><select name="operating-system" id="operating-system-select" hidden><option class="select-an-option" disabled selected> -- select an option -- </option></select></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="operating-system-id">ID</label></div>
							<div class="col-md-4 col-lg-4"><input type="text" id="operating-system-id" disabled/></div>
							<div class="col-md-4 col-lg-4"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="operating-system-name">Name</label></div>
							<div class="col-md-4 col-lg-4"><input type="text" id="operating-system-name" /></div>
							<div class="col-md-4 col-lg-4"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="operating-system-type">Type</label></div>
							<div class="col-md-4 col-lg-4"><input type="text" id="operating-system-type" /></div>
							<div class="col-md-4 col-lg-4"></div>
							</div>
						</div>

						<div class="row header"><h2>Battery</h2></div>
						<div id="battery">
							<ul class="nav nav-pills nav-fill" id="battery-nav">
								<li class="nav-item">
								<a class="nav-link" aria-current="page" id="create-new">Create New</a>
								</li>
								<li class="nav-item">
								<a class="nav-link" id="add-existing">Add Existing</a>
								</li>
							</ul>

							<div class="row">
							<div class="col-md-3 col-lg-3"></div>
							<div class="col-md-3 col-lg-3"><select name="battery" id="battery-select" hidden><option class="select-an-option" disabled selected> -- select an option -- </option></select></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="battery-id">ID</label></div>
							<div class="col-md-4 col-lg-4"><input type="text" id="battery-id" disabled/></div>
							<div class="col-md-4 col-lg-4"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="battery-model">Model</label></div>
							<div class="col-md-4 col-lg-4"><input type="text" id="battery-model" /></div>
							<div class="col-md-4 col-lg-4"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="battery-voltages">Voltages</label></div>
							<div class="col-md-4 col-lg-4"><input type="text" id="battery-voltages" /></div>
							<div class="col-md-4 col-lg-4"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="battery-estimatedWorkingTime">Estimated Working Time</label></div>
							<div class="col-md-4 col-lg-4"><input type="text" id="battery-estimatedWorkingTime" /></div>
							<div class="col-md-4 col-lg-4"></div>
							</div>
						</div>

						<div class="row header"><h2>Manufacturer</h2></div>
						<div id="manufacturer">
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="manufacturer-id">ID</label></div>
							<div class="col-md-4 col-lg-4"><input type="text" id="manufacturer-id" disabled/></div>
							<div class="col-md-4 col-lg-4"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="product-manufacturer">Manufacturer</label></div>
							<div class="col-md-5 col-lg-5" id="product-manufacturer">
								<input type="text" id="manufacturer-input" hidden/>
								<select name="manufacturer" id="manufacturer-select"><option class="select-an-option" disabled selected> -- select an option -- </option></select>
								<div id="manufacturer-radio-btn">
									<input type="radio" id="create-new-manufacturer" name="fav_language" value="create-new">
									<label for="create-new-manufacturer">Create New</label><br>
									<input type="radio" id="add-existing-manufacturer" name="fav_language" value="add-existing">
									<label for="add-existing-manufacturer">Choose exsiting</label><br>
								</div>
							</div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
						</div>
						
						<div class="row header"><h2>Category</h2></div>
						<div id="category">
							<div class="row" >
							<div class="col-md-2 col-lg-2"><label for="category-id">ID</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="category-id" value="1" class="tooltip-test" title="OK" disabled/></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
							<div class="row">
							<div class="col-md-2 col-lg-2"><label for="category-name">category-name</label></div>
							<div class="col-md-5 col-lg-5"><input type="text" id="category-name" value="laptop" class="tooltip-test" title="OK" disabled/></div>
							<div class="col-md-5 col-lg-5"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary btn-save-or-update">Save changes</button>
				</div>
			</div>
		</div>
	</div>


	<div class="row col-md-6">
		<form class="otherForm" id="otherForm">
			<div class="modal fade otherModal" tabindex="-1" role="dialog">
				<div class="modal-dialog modal-lg" role="document"
					style="width: 60%;">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel"></h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<div class="row">
								<div>
									<input type="hidden" class="form-control" name='danhMucId'
										id="idDanhMucKhac">
								</div>
								<div>
									<input type="hidden" class="form-control" name="id"
										id="idSanPhamKhac" readonly="true">
								</div>
								<div class="form-group col-md-12">
									<label for="inputPassword4">Tên sản phẩm</label> <input
										type="text" class="form-control" name="tenSanPham">
								</div>
							</div>

							<div class="row">
								<div class="form-group col-md-6">
									<label for="inputState">Hãng Sản Xuất</label> <select
										name="nhaSXId" id="nhaSXIdKhac" class="form-control">
										<c:forEach var="nhanHieu" items="${listNhanHieu }">
											<option value="${nhanHieu.id }">${nhanHieu.tenHangSanXuat}
											</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group col-md-6">
									<label for="inputPassword4">Đơn giá</label> <input
										type="number" class="form-control" name="donGia" min="0"
										value="0" id="donGiaKhac">
								</div>
							</div>

							<div class="row">
								<div class="form-group col-md-12">
									<label for="inputPassword4">Mô tả chung</label>
									<textarea class="form-control" id="inputPassword4"
										placeholder="" rows="2" name="thongTinChung"></textarea>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-md-6">
									<label for="inputEmail4">Thông tin bảo hành</label> <input
										type="text" class="form-control" id="inputEmail4"
										name="thongTinBaoHanh">
								</div>

								<div class="form-group col-md-6">
									<label for="inputPassword4">Số lượng</label> <input
										type="number" class="form-control" name="donViKho" min="0"
										value="0">
								</div>
							</div>
							<div>
								<label for="inputEmail4">Hình ảnh</label> <input type="file"
									class="form-control" name="hinhAnh">
							</div>
						</div>

						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Hủy</button>
							<button class="btn btn-primary" id="btnSubmit" type="submit">Xác
								nhận</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
		</form>
	</div>

	<div class="row col-md-10">
		<form class="chiTietForm">
			<div class="modal fade" id="chiTietModal" tabindex="-1" role="dialog"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog " role="document" style="width: 60%">
					<div class="modal-content">
						<div class="modal-header">
							<h3 class="title" style="text-align: center; font-weight: bolder;">Chi tiết sản phẩm</h3>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
								<div class="card">
									<div class="container-fliud">
										<div class="wrapper row">
											<div class="preview col-md-6">

												<div class="preview-pic tab-content">
													<div class="tab-pane active" id="pic-1">
														<img style="width: 350px; height: 350px" class="hinhAnh" />
													</div>
												</div>
											</div>
											<div class="details col-md-6" style="font-size: 16px">
												<p class="maSanPham"></p>
												<p class="tenSanPham"></p>
												<p class="hangSangXuat"></p>
												<p class="cpu"></p>
												<p class="ram"></p>
												<p class="thietKe"></p>
												<p class="heDieuHanh"></p>
												<p class="manHinh"></p>
												<p class="dungLuongPin"></p>
												<p class="thongTinChung"></p>
												<p class="baoHanh"></p>
												<p class="donGia"></p>
												<p class="donViKho"></p>
												<p class="donViBan"></p>
											</div>
										</div>
									</div>
								</div>
							</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Đóng</button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	</div>


	<jsp:include page="template/footer.jsp"></jsp:include>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.serializeJSON/2.9.0/jquery.serializejson.js"></script>
	<script src="<c:url value='/Frontend/js/jquery.simplePagination.js'/>"></script>
	<script src="<c:url value='/js/ProductManage.js'/>"></script>
</body>
</html>