$(document).ready(function() 
{
    displayPage(1);
    resetPagination();
    var cpus;

    $('#add-laptop').on('click', function()
    {
        $('#exampleModalLong').modal({keyboard: true, backdrop: true});
        populateSelectBoxes();
        addRadioButtonsHandler();
        addNavHandler();
    });

    function displayPage(_page)
    {
        fetch('http://localhost:8080/api/product/get-all?page=' + _page)
        // Handle success
        .then(response => response.json())  // convert to json
        .then(json => 
        {
            console.log(json);
            products = json.content;
            for (var i=0;i<products.length;i++)
            {
                category = products[i].category.name;
                var productRow = '<tr>' +
                                  '<td>' + '<img src="/img/'+products[i].id+'.png" class="img-responsive" style="height: 50px; width: 50px" />'+'</td>' +
                                  '<td>' + products[i].laptop.name + '</td>' +
                                  '<td>' + category + '</td>' +
                                  '<td>' + products[i].laptop.manufacturer.name + '</td>' +
                                  '<td>' + products[i].price + '</td>' +
                                  '<td>' + products[i].stock + '</td>' +
                                  '<td width="0%">'+'<input type="hidden" id="productId" value=' + products[i].id + '>'+ '</td>' + 
                                  '<td> <button class="btn btn-warning btnDetail" style="margin-right: 6px">Detail</button>' ;
                
                
                productRow += '<button class="btn btn-primary btnUpdate" >Update</button>';
                productRow += '  <button class="btn btn-danger btnDelete">Delete</button></td>'+'</tr>';
                $('.productTable tbody').append(productRow);
            }
            addUpdateButtonHandler();
        })    
        .catch(err => console.log('Request Failed', err)); // Catch errors
    }

    function populateSelectBoxes()
    {
        // handler color selection
        $('#color_select').empty();
        fetch('http://localhost:8080/api/laptop/color')
        // Handle success
        .then(response => response.json())  // convert to json
        .then(json => 
        {
            for (var i=0;i<json.length;i++)
			{
				var option = '<option value="' + json[i] + '">' + json[i] + '</option>'
				$('#color_select').append(option);
			}
        })    //print data to console
        .catch(err => console.log('Request Failed', err)); // Catch errors

        // handler material selection
        $('#material_select').empty();
        fetch('http://localhost:8080/api/laptop/material')
        // Handle success
        .then(response => response.json())  // convert to json
        .then(json => 
        {
            for (var i=0;i<json.length;i++)
			{
				var option = '<option value="' + json[i] + '">' + json[i] + '</option>'
				$('#material_select').append(option);
			}
        })    //print data to console
        .catch(err => console.log('Request Failed', err)); // Catch errors

        // handler cpu manufacturer selection
        $('#cpu-manufacturer-select').empty();
        fetch('http://localhost:8080/api/manufacturer/get-all-wp')
        // Handle success
        .then(response => response.json())  // convert to json
        .then(json => 
        {
            for (var i=0;i<json.length;i++)
			{
				var option = '<option value="' + json[i].name + '">' + json[i].name + '</option>'
				$('#cpu-manufacturer-select').append(option);
			}
        })    //print data to console
        .catch(err => console.log('Request Failed', err)); // Catch errors

        // handler cpu selection
        $('#cpu-select').empty();
        fetch('http://localhost:8080/api/cpu/all')
        // Handle success
        .then(response => response.json())  // convert to json
        .then(json => 
        {
            for (var i=0;i<json.length;i++)
			{
				var option = '<option value="' + json[i].id + '">' + json[i].model + " |core:" + json[i].core + "|thread:" + json[i].thread
                + "|speed:" + json[i].processorSpeed + "|max_speed:" + json[i].maxProcessorSpeed
                + '</option>';
				$('#cpu-select').append(option);
			}
            cpus = json;

            $('#cpu-select').change(function()
            {
                $("#cpu input[id!='create-new-cpu-manufacturer'][id!='add-existing-cpu-manufacturer']").val('');
                var option = $('#cpu-select option:selected');
                cpu = cpus.find(cpu => 
                {
                    return cpu.id == option.val();
                });
                $('#cpu #cpu-id').val(cpu.id + "");
                $('#cpu #cpu-model').val(cpu.model);
                $('#cpu #cpu-core').val(cpu.core);
                $('#cpu #cpu-thread').val(cpu.thread);
                $('#cpu #cpu-processorSpeed').val(cpu.processorSpeed);
                $('#cpu #cpu-maxProcessorSpeed').val(cpu.maxProcessorSpeed);
                $('#cpu #cpu-additionalInfo').val(cpu.additionalInfo);
                $('#cpu #cpu-manufacturer-id').val(cpu.manufacturer == null ? '' : cpu.manufacturer.id);
                $('#cpu #cpu-manufacturer-input').val(cpu.manufacturer == null ? '' : cpu.manufacturer.name);
            });
        })    //print data to console
        .catch(err => console.log('Request Failed', err)); // Catch errors
    }

    function addRadioButtonsHandler()
    {
        //handle cpu-manufacturer button events
        $("#cpu-manufacturer-radio-btn [type='radio']").change(function(event)
		{
			var btn = $(this);
			if (btn.attr('value') == 'create-new')
			{
				$('#cpu-manufacturer select').attr('hidden', 'hidden');
				$('#cpu-manufacturer-input').removeAttr('hidden');
			}
			
			if (btn.attr('value') == 'add-existing')
			{
				$('#cpu-manufacturer-input').attr('hidden', 'hidden');
				$('#cpu-manufacturer select').removeAttr('hidden');
			}
		});
    }

    function addNavHandler()
    {
        //handle cpu create new | add existing button events
        $("#cpu-nav a").click(function(event)
        {
            var nav = $(event.target);
            $("#cpu-nav a").removeClass('active');
            nav.addClass('active');
            
            if(nav.attr('id') == 'create-new')
            {
                $("#cpu input[id!='cpu-id']").removeAttr('disabled');
                $("#cpu input[id!='create-new-cpu-manufacturer'][id!='add-existing-cpu-manufacturer']").val('');
                $('#cpu-select').attr('hidden', 'hidden');
            }
            if(nav.attr('id') == 'add-existing')
            {
                $('#cpu input').attr('disabled', 'disabled');
                $('#cpu-manufacturer select').attr('hidden', 'hidden');
                $('#cpu-manufacturer-input').removeAttr('hidden');
                $('#cpu-select').removeAttr('hidden');
            }
        });
    }

    function addUpdateButtonHandler()
    {
        $('.btnUpdate').on('click', function(event) 
        {
            
        });
    }

    // function bindDropdownnHandler()
    // {
    //     $('#categoryDropdown option').click(function(event) 
    //     {
    //         console.log("click this");
    //         var option = $(event.target);
    //         if (option.text() == "laptop")
    //         {
    //             console.log("in here");
    //             $('.modal fade').modal('show');	
    //         }
    //     });
    // }

    function resetPagination()
    {
        $('#light-pagination').pagination({

            // Total number of items that will be used to calculate the pages.
            items: 25, 
  
            // Number of items displayed on each page.
            itemsOnPage: 5, 
  
            // If specified, items and itemsOnPage will not be used to calculate the number of pages.
            pages: 0, 
  
            // How many page numbers should be visible while navigating. Minimum allowed: 3 (previous, current & next)
            displayedPages: 5, 
  
            // How many page numbers are visible at the beginning/ending of the pagination.
            edges: 2,
  
            // Enables start/end edge
            useStartEdge : true,
            useEndEdge : true,
  
            // Which page will be selected immediately after init.
            currentPage: 0, 
  
            // Uses anchor links or spans
            useAnchors: true,
  
            // A string used to build the href attribute, added before the page number.
            hrefTextPrefix: "#page-", 
  
            // Another string used to build the href attribute, added after the page number.
            hrefTextSuffix: '', 
  
            // Text to be display on the previous button.
            prevText: "Prev", 
  
            // Text to be display on the next button.
            nextText: "Next", 
  
            // Ellipse Character
            ellipseText: '&hellip;',
            ellipsePageSet: true,
  
            // list style
            listStyle: '',
  
            // The class of the CSS theme:
            // "light-theme", "compact-theme", and "dark-theme"
            cssStyle: "light-theme", 
  
            // Set to false if you don't want to select the page immediately after click.
            selectOnClick: true,
  
            // Next button at front.
            nextAtFront: false,
  
            // Inverts page order
            invertPageOrder: false
  
          });
    }
});