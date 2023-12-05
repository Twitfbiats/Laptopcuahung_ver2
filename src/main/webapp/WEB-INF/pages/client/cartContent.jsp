<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="Frontend/css/cartTable.css">
<style>
html
{
    font-size: 100%;
}
</style>
<script>
	console.log("Why not work ??");
	console.log(${quantity})
	console.log(`${principal}`)
</script>
</head>

<body>
<br>
<br>
<div class="container">
  <div class="row">
  <h2 style="">  Manage Your Cart</h2> <br>
  <div class="col-10">
  <table class="table-cart-checkout mytable table-convert-price" >
 <tr>
		<td>No</td>
		<td>Image</td>
		<td>Product Name</td>
		<td>Price</td>
		<td>Quantity</td>
		<td>Total</td>
		<td>Delete</td>
</tr>
<c:forEach var="product" items="${cart}" varStatus="loop">		
<c:if test = "${product.category.name == 'laptop'}">	
	<tr class="cart_line" id="item${product.id}">
		<div>
		<td>${loop.index+1}</td>
		<td><img src="/img/${product.id}.jpg" style="width: 150px" class="cart-img"></td>
		<td style="text-align: center; mergin-top: -52px;">
			<p class="cart_name"><a href="<c:url value="/laptop/${product.id}" />">${product.laptop.name}</a></p>
			<p class="cart_productcode">Product Code : <span>${product.id}</span></p>
			<p class="">Warranty : ${product.warranty}</p>
		</td>
		<td class="covertPriceProduct">${product.price}</td>
		<td>
			<input class="input_num_cart" type="number" value="${quantity[product.id]}" min="1" onChange="changeQuantity(${product.id},this.value,${product.price})">
		</td>
		<td><b><span class="total" id="item${product.id}_total" name="total_price">${product.price*quantity[product.id]}</span></b></td>
		<td class="cart-img">
			<a class="btn btn-danger" onClick="deleteFromCart(${product.id})"><span class="glyphicon glyphicon-trash"></span></a>
		</td>
		</div>
	
	</tr>
</c:if>

</c:forEach>
<tr>
		<c:if test="${checkEmpty != 0 }">
		<td colspan="3">
		<a class="btn btn-primary" href="<%=request.getContextPath()%>/"><span class="glyphicon glyphicon-hand-left"></span> Buy More</a>
		&nbsp;  &nbsp;  &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
		<a class="btn btn-warning " href="<%=request.getContextPath()%>/checkout"><span class="glyphicon glyphicon-check"></span> Purchase</a>
		</td>
		</c:if>
		
		<c:if test="${checkEmpty == 0 }">
		<td colspan="3">
		<a class="btn btn-primary" href="<%=request.getContextPath()%>/">Buy More</a>
		</td>
		</c:if>
		
		
		<td colspan="4">
			<p class="cart_ordertotal">Order total value : <span id="ordertotal"></span></p>
		</td>
</tr>

</table>
  
</div>  
  
  <div class="col-xs-1">
  
  </div>
  
  </div>
</div>

<script src="<c:url value='/js/client/cartAjax.js'/>" ></script>	 

</body>
</html>