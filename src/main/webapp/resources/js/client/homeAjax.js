
function addToCart(id)
{
	$.ajax({
		type: "GET",		
		url: "http://localhost:8080/api/cart/"+id,
		success: function(result){
			if(result.status == "false")
			{
				window.alert("Sản phẩm đang hết hàng, quý khách vui lòng quay lại sau");	
			}else
			{
				window.alert("Đã thêm sản phẩm vào giỏ hàng");
			}		
		},
		error : function(e){
			alert("Error: ",e);
			console.log("Error" , e );
		}
	});

	
}


$(document).ready(function()
{
	ajaxGet();

	function ajaxGet()
	{
		$.ajax({
			type: "GET",		
			url: "http://localhost:8080/api/product/latest?category=laptop",
			success: function(result){
				var content;
				var section = '<div class="section group">';
				var endsection = '</div>'+'<br>';
				$.each(result, function(i, product){
					
					if(i != result.length-1)
					{
						if(i%4==0)
						{
							content = '';
							content = '<div class="grid_1_of_4 images_1_of_4 products-info"><a href="laptop/'+product.id+'">' +
									'<img style="width: 300px; height: 238px" src="/img/'+product.id+'.jpg">' +
									'<h3 style="font-weight: bold;">'+product.laptop.name+'</h3></a>' +
									'<h3>'+accounting.formatMoney(product.price)+' VND</h3>'+
									'<button onClick="addToCart('+product.id+')" class="btn btn-warning"><span class= "glyphicon glyphicon-shopping-cart pull-center"></span> Cart</button>'+
									'<h3></h3>'+
									'</div>';
						}else 
						{
							content = content+'<div class="grid_1_of_4 images_1_of_4 products-info"><a href="laptop/'+product.id+'">' +
									'<img style="width: 300px; height: 238px" src="/img/'+product.id+'.jpg">' +
									'<h3 style="font-weight: bold;">'+product.laptop.name+'</h3><a/>' +
									'<h3>'+accounting.formatMoney(product.price)+' VND</h3>'+
									'<button onClick="addToCart('+product.id+')" class="btn btn-warning"><span class= "glyphicon glyphicon-shopping-cart pull-center"></span> Cart</button>'+
									'<h3></h3>'+
									'</div>';
							if(i%4==3)
							{
								content = section + content + endsection;
								$('.content-grids').append(content);
							}				
						}
					}else
					{
						if(i%4==0)
						{
							content = '';
							content = '<div class="grid_1_of_4 images_1_of_4 products-info"><a href="laptop/'+product.id+'">' +
									'<img style="width: 300px; height: 238px" src="/img/'+product.id+'.jpg">' +
									'<h3 style="font-weight: bold;">'+product.laptop.name+'</h3>></a>' +
									'<h3>'+accounting.formatMoney(product.price)+' VND</h3>'+
									'<button onClick="addToCart('+product.id+')" class="btn btn-warning"><span class= "glyphicon glyphicon-shopping-cart pull-center"></span> Cart</button>'+
									'<h3></h3>'+
									'</div>';
							content = section + content + endsection;
							$('.content-grids').append(content);
						}else
						{
							content = content+'<div class="grid_1_of_4 images_1_of_4 products-info"><a href="laptop/'+product.id+'">' +
									'<img style="width: 300px; height: 238px" src="/img/'+product.id+'.jpg">' +
									'<h3 style="font-weight: bold;">'+product.laptop.name+'</h3></a>' +
									'<h3>'+accounting.formatMoney(product.price)+' VND</h3>'+
									'<button  onClick="addToCart('+product.id+')" class="btn btn-warning"><span class= "glyphicon glyphicon-shopping-cart pull-center"></span> Cart</button>'+
									'<h3></h3>'+
									'</div>';
							content = section + content + endsection;
							$('.content-grids').append(content);
						}
					}
				});
			},
			error : function(e){
				alert("Error: ",e);
				console.log("Error" , e );
			}
		});
	}
	
	
	
})
