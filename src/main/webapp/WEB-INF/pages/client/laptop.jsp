<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <link href="https://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet" />
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.1/jquery.js"></script>
  <script src="https://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
  <script src='https://unpkg.com/spritespin@x.x.x/release/spritespin.js' type='text/javascript'></script>
  <style>
	.carousel {
    margin-top: 20px;
	}
	.item .thumb {
	  width: 25%;
	  cursor: pointer;
	  float: left;
	}
	.item .thumb img {
	  width: 100%;
	  margin: 2px;
	}
	.item img {
	  width: 100%;  
	}
  </style>
  
  <script>
	$(document).ready(function (params) 
	{
		var index = `${product.id}`;
		var carousel_inner2_item_index = 0;
		var folder = "/img/product" + index + "/";
		var folder_360 = "/img/product" + index + "_360/"
		var sources = [];
		
		$.ajax({
			url : folder,
			success: function (data) {
				$(data).find("a").attr("href", function (i, val) {
					if( val.match(/\.(jpe?g|png|gif)$/) ) 
					{ 
						var item =
						'<div class="item">' + 
							'<img src="' + folder + val +'" style="width:705;height:400">' +
						'</div>';
						$("#carousel-inner1").append(item);
						
						if (i % 4 == 0)
						{
							carousel_inner2_item_index++;
							console.log('ok');
							var item2 = '<div class="item" id="' + 'item' + carousel_inner2_item_index + '"></div>';
							$("#carousel-inner2").append(item2);
						}
						var item_child = 
						'<div data-target="#carousel" data-slide-to="' + i + '" class="thumb"><img src="' + folder + val
						+ '"></div>';
						$('#item'+carousel_inner2_item_index).append(item_child);
					} 
				});
				
				$("#carousel-inner1 div").first().addClass('active');
				$("#carousel-inner2 div").first().addClass('active');
			}
		});
		
		$.ajax({
			url : folder_360,
			success: function (data) 
			{
				$(data).find("a").attr("href", function (i, val) {
					if( val.match(/\.(jpe?g|png|gif)$/) ) 
					{ 
						var item = folder_360 + val;
						sources.push(item);
					} 
				});

				sources = sources.sort(function(a, b) 
				{
					return Number(a.match(/(\d+)/g)[2]) - Number(b.match(/(\d+)/g)[2]);
				});

				console.log(sources);
				$("#mySpriteSpin").spritespin({
				// path to the source images.
				source: sources,
				width   : 480,  // width in pixels of the window/frame
				height  : 327,  // height in pixels of the window/frame
				frames  : sources.length,
				frame   : 0
				});
			}
		});	

		var formatter = new Intl.NumberFormat('vi-VN', 
		{
			style: 'currency',
			currency: 'VND'
		});

		$('#product-price').text(formatter.format(Number(`${product.price}`)));

		if (`${product.embedded3DModel}` != null || `${product.embedded3DModel}` != '')
		{
			$('#product-embedded-3d-model').append(`${product.embedded3DModel}`);
		}
	});
	
  </script>
</head>
      <div class="container">
  <div class="row">
    <div class="col-sm-6">
	
        <div id="carousel" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner" id="carousel-inner1">
                
            </div>
        </div> 
    <div class="clearfix">
        <div id="thumbcarousel" class="carousel slide" data-interval="false">
            <div class="carousel-inner" id="carousel-inner2">
            </div>
            <a class="left carousel-control" href="#thumbcarousel" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
            </a>
            <a class="right carousel-control" href="#thumbcarousel" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right"></span>
            </a>
        </div> 
    </div>
    </div>
    <div class="col-sm-6">
        <h2>${product.laptop.name}</h2>
        <h3 id="product-price"></h3>
        <p>${product.intro}</p>
		<p>${product.laptop.display.size} inch, ${product.laptop.display.resolution} Pixels, ${product.laptop.display.displayType}, ${product.laptop.display.refreshRate} Hz, ${product.laptop.display.technology}, ${product.laptop.display.panelType}</p>
        <p>${product.laptop.cpus[0].model}: ${product.laptop.cpus[0].core} Core, ${product.laptop.cpus[0].thread} Thread, ${product.laptop.cpus[0].processorSpeed} GHz</p>
		<p>${product.laptop.rams[0].size} GB (1 ram 8 GB), ${product.laptop.rams[0].ramType}, ${product.laptop.rams[0].bus} MHz</p>
		<p>${product.laptop.drives[0].driveType} ${product.laptop.drives[0].size} GB</p>
		<p>${product.laptop.graphicCards[0].model}</p>
    </div>
  </div>
  <div class="row">
	<div class="col-lg-7" id="product-embedded-3d-model">
		
	</div>
	<div class="col-lg-5" id='mySpriteSpin'></div>
  </div>
</div>
</html>