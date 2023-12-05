<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Check out</title>
</head>

<script type="text/javascript">	  
	$(document).ready(function() { 
		 $(".mytable .donGia .check").each(function() {
			  var value = accounting.formatMoney(Number($(this).text()))+ ' VND';
			  $(this).html(value);
			});
	  
		 $(".mytable .total").each(function() {
			  var value = accounting.formatMoney(Number($(this).text())) + ' VND ';
			  $(this).html(value);
			});
	  });
	</script>

<body>

<div class="container">
	<div class = "row">
	<br><br>
		<div class="col-md-3" >
			<br>
			<p class="border-p" style="line-height:1.5;"><b>Client info</b></p>
			
			<p style="line-height:2;">Client Name </p>
			<input size="27" value="${loggedInUser.fullName}" disabled>
			
			<p style="line-height:2;">Email Address</p>
			<input size="27" value="${loggedInUser.email}" disabled>
			
			<p style="line-height:2;">Phone Number </p>
			<input size="27" value="${loggedInUser.phoneNumber}" disabled>
			
			<p style="line-height:2;">Address(house number, street, state) </p>
			<textarea rows="5" cols="29" disabled>${loggedInUser.address}</textarea>
						
			<br><br>
		</div>

		<div class="col-md-3">
			<br>
			<p class="border-p" style="line-height:1.5;"><b>Order Info</b></p>
		
			<p style="line-height:2;" >Receiver Name *</p>
			<input size="27" name="Receiver Name" required>
			
			<p style="line-height:2;">Receiver Phone Number *</p>
			<input size="27" name="Receiver Phone Number" required>
			
			
			<p style="line-height:2;">Address(house number, street, state) *</p>
			<textarea rows="5" cols="29" name="Address" required></textarea>

			<br><br>
			<input type="hidden" id="totalCost" name="total">
		</div>
		
		<div class="col-md-6">
			<br>
			<p class="border-p" style="line-height:1.5;"><b>Cart</b></p>
			<br>
			
				<table class="table-cart-checkout mytable">
					 <tr>
						    <th>Image</th>
						    <th>Product Name</th>
						    <th>Price</th>
						    <th>Total</th>
					</tr>
					<c:forEach items="${products}" var="product" varStatus="loop">

                        <c:if test = "${product.category.name == 'laptop'}">	
                            <tr style="text-align: center;">
                                <td>
                                    <img src="/img/${product.id}.jpg" alt="not found img" class="img-reponsive fix-size-img">
                                </td>
                                <td style="color:green">
                                ${product.laptop.name}
                                </td>
                                
                                <td class="donGia" >
                                    <div class="check " style ="display: inline-block; ">${product.price}</div> 
                                    <div style="display: inline-block; "> x ${quantities[product.id]}</div>
                                </td>
                                
                                <td>
                                    <div class="total" real-value="${product.price*quantities[product.id]}">${product.price*quantities[product.id]}</div> 
                                </td>
                            </tr>
                        </c:if>
					</c:forEach>
					
				</table>
			
			
			<br>
			<p>Total Cost:     <b id="ordertotal"> </b></p>
			<br>
			 &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp;
			 
			<div>
				<div id="paypal-container"></div>
				<a href="<%=request.getContextPath()%>/cart" class="btn btn-primary">Back To Cart</a>
			</div>
		
			<br><br>
		
		</div>
	
	</div>
</div>
<script src="<c:url value='/js/client/checkoutAjax.js'/>" ></script>
</body>
</html>

