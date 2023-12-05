var formatter = new Intl.NumberFormat('vi-VN', 
{
	style: 'currency',
	currency: 'VND'
});

function convertMoneyBackToNumber(money)
{
	return money.replace(/\./g, "").replace(/ ₫/, '')
}

function calculateOrder()
{
	var element = document.getElementsByClassName("total");
	var res = 0;
	for (i = 0; i < element.length; i++) {
		res = res + parseInt(convertMoneyBackToNumber(element[i].textContent));
	}
	var element2 = document.getElementById("ordertotal");
	resConvert = formatter.format(Number(res));
	element2.innerHTML = resConvert;
}

$(".table-convert-price .covertPriceProduct").each(function() 
{
	var value = formatter.format(Number($(this).text()));
	$(this).html(value);
});

$(".table-convert-price .total").each(function() 
{
	var value = formatter.format(Number($(this).text()));
	$(this).html(value);
});

calculateOrder()

function changeQuantity(id,value,price)
{
	fetch(`http://localhost:8080/cart/change-quantity?product=${id}&quantity=${value}`, {method: 'POST'})
	.then(response => response.json())
	.then(json => 
	{
		if (json.requestStatus == 'anonymous')
		{
			changeCartCookie(id, value)
		}
		else if (json.requestStatus == 'ok') console.log("OK")
		else window.alert(json.requestStatus)
	})

	calculatePrice(id, value, price)
	calculateOrder()
}

function getCookie(cookie)
{
	return document.cookie.split('; ').find((cookieString) => cookieString.includes(cookie))
}

function changeCartCookie(product, quantity)
{
	var cookieString = getCookie('cart')
	var infoString = cookieString.split('=')[1]
	var infos = infoString.split('|')
	var infoToChange = infos.find((info) => info.includes(product + ':'))
	infos[infos.indexOf(infoToChange)] = product + ':' + quantity
	
	cookieString = 'cart='+infos.join('|')
	document.cookie = cookieString
}

function calculatePrice(id,value,price)
{
	var element = document.getElementById("item"+id+"_total");

	element.innerHTML = formatter.format(Number(value * price));
}

// function deleteFromCart(id)
	// {
	// 	$.ajax({
	// 		type: "GET",		
	// 		url: "http://localhost:8080/laptopshop/api/gio-hang/deleteFromCart?id="+id,
	// 		success: function(result){
	// 			var element = document.getElementById("item"+id);
	// 			element.parentNode.removeChild(element);
	// 			calculateOrder();
	// 		},
	// 		error : function(e){
	// 			alert("Error: ",e);
	// 			console.log("Error" , e );
	// 		}
	// 	});
	// }


