$(document).ready(function(){
	ajaxGet2();
	function ajaxGet2(){
		$.ajax({
			type: "GET",		
			url: "http://localhost:8080/api/category/get-all-wp",
			success: function(result){
				$.each(result, function(i, category){
					var content = '<li><a href="/laptopshop/store?brand='+category.name+'"><span style=" font-size: 16px; font-weight: 900; ">'+category.name+'</span></a></li>';
					var content2 ='<li><a  style="padding-right: 100px" href="' + `${contextPath}` + '/store?brand='+category.name+'">'+category.name+'</a></li>'
					$('#category').append(content);		
					$('#category2').append(content2);	
				})					
			}	
		});
			
	}
})
