<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<title>Laptop Shop - Đăng ký</title>
<link rel="stylesheet" href="Frontend/css/login.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body>


	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<div class="login-page">
		<div class="form">
			<form:form method="POST" action='register' modelAttribute="newUser">
				<h2 class="form-signin-heading" style="text-align: center">LaptopShop - Register</h2>
				<hr/>
				<div class="form-group">
					<form:input type="email" path="email" class="form-control"
						placeholder="Email" autofocus="true" required="required"></form:input>
				</div>

				<div class="form-group">
					<form:input type="password" path="password" class="form-control"
						required="required" placeholder="Password"></form:input>
				</div>

				<div class="form-group">
					<form:input type="password" path="confirmPassword"
						class="form-control" placeholder="Retype password"
						required="required"></form:input>
				</div>

				<div class="form-group">
					<form:input type="text" path="fullName" class="form-control"
						placeholder="Full name" required="required"></form:input>
				</div>

				<div class="form-group">
					<form:input type="number" path="phoneNumber" class="form-control"
						placeholder="Phone number" required="required"></form:input>
				</div>

				<div class="form-group">
					<form:input type="text" path="address" class="form-control"
						placeholder="Address" required="required"></form:input>
				</div>
                <div class="error-message">
                    <c:forEach items="${errors}" var="error">
                        <div class="alert alert-danger" role="alert">
                            ${error}
                        </div>
                    </c:forEach>
                </div>
				<input id="submit" type="submit" value="Register">
				<p class="message" style="font-size: 18; padding-top:10px"> Already have account? <a href="<c:url value='/login'/> ">Sign In</a></p>
			</form:form>
		</div>
	</div>
	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

</body>
</html>