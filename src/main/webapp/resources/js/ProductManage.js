$(document).ready(function() 
{
    displayPage(1);
    resetPagination();
    var cpus_1,displays_1,drives_1,graphicCards_1,operatingSystems_1,batteries_1;

    $('#add-laptop').on('click', function()
    {
        $('#exampleModalLong').modal({keyboard: true, backdrop: true});
        populateSelectBoxes();
        addRadioButtonsHandler();
        addNavHandler();
        addAddButtonHandler();
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
        $('.container select').each(function() {this.selectedIndex = 0;});
        // handler color selection
        $('#color_select option').not('.select-an-option').remove();
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

            $('#color_select').change(function()
            {
                var option = $('#color_select option:selected');
                $("#color").val(option.val());
            });
        })    //print data to console
        .catch(err => console.log('Request Failed', err)); // Catch errors

        // handler material selection
        $('#material_select option').not('.select-an-option').remove();
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

            $('#material_select').change(function()
            {
                var option = $('#material_select option:selected');
                $("#material").val(option.val());
            });
        })    //print data to console
        .catch(err => console.log('Request Failed', err)); // Catch errors

        // handler cpu manufacturer selection
        $('#cpu-manufacturer-select option').not('.select-an-option').remove();
        fetch('http://localhost:8080/api/manufacturer/get-all-wp')
        // Handle success
        .then(response => response.json())  // convert to json
        .then(json => 
        {
            for (var i=0;i<json.length;i++)
			{
				var option = '<option value="' + json[i].id + '">' + json[i].name + '</option>'
				$('#cpu-manufacturer-select').append(option);
			}

            $('#cpu-manufacturer-select').change(function() 
            {
                var option = $('#cpu-manufacturer-select option:selected');
                $('#cpu #cpu-manufacturer-id').val(option.val());
                $('#cpu #cpu-manufacturer-input').val(option.text());
            });
        })    //print data to console
        .catch(err => console.log('Request Failed', err)); // Catch errors

        // handler cpu selection
        $('#cpu-select option').not('.select-an-option').remove();
        fetch('http://localhost:8080/api/cpu/all')
        // Handle success
        .then(response => response.json())  // convert to json
        .then(json => 
        {
            for (var i=0;i<json.length;i++)
			{
				var option = '<option value="' + json[i].id + '">' + json[i].model + " |core:" + json[i].core + "|thread:" + json[i].thread
                + "|speed:" + json[i].processorSpeed + " GHZ|max_speed:" + json[i].maxProcessorSpeed + " GHZ"
                + '</option>';
				$('#cpu-select').append(option);
			}
            cpus_1 = json;

            $('#cpu-select').change(function()
            {
                $("#cpu input[id!='create-new-cpu-manufacturer'][id!='add-existing-cpu-manufacturer']").val('');
                var option = $('#cpu-select option:selected');
                cpu = cpus_1.find(cpu => 
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

        // handler display type selection
        $('#display-type-select option').not('.select-an-option').remove();
        fetch('http://localhost:8080/api/laptop/display/display-type')
        // Handle success
        .then(response => response.json())  // convert to json
        .then(json => 
        {
            for (var i=0;i<json.length;i++)
			{
				var option = '<option value="' + json[i] + '">' + json[i] + '</option>'
				$('#display-type-select').append(option);
			}

            $('#display-type-select').change(function()
            {
                var option = $('#display-type-select option:selected');
                $("#display-displayType").val(option.val());
            });
        })    //print data to console
        .catch(err => console.log('Request Failed', err)); // Catch errors

        // handler panel type selection
        $('#panel-type-select option').not('.select-an-option').remove();
        fetch('http://localhost:8080/api/laptop/display/panel-type')
        // Handle success
        .then(response => response.json())  // convert to json
        .then(json => 
        {
            for (var i=0;i<json.length;i++)
			{
				var option = '<option value="' + json[i] + '">' + json[i] + '</option>'
				$('#panel-type-select').append(option);
			}

            $('#panel-type-select').change(function()
            {
                var option = $('#panel-type-select option:selected');
                $("#display-panelType").val(option.val());
            });
        })    //print data to console
        .catch(err => console.log('Request Failed', err)); // Catch errors

        // handler display selection
        $('#display-select option').not('.select-an-option').remove();
        fetch('http://localhost:8080/api/laptop/display/all')
        // Handle success
        .then(response => response.json())  // convert to json
        .then(json => 
        {
            for (var i=0;i<json.length;i++)
			{
				var option = '<option value="' + json[i].id + '">Size:' + json[i].size + " inches| Resolution:" + json[i].resolution + " pixels| Type:" + json[i].displayType
                + " | Panel:" + json[i].panelType
                + '</option>';
				$('#display-select').append(option);
			}
            displays_1 = json;

            $('#display-select').change(function()
            {
                $("#display input").val('');
                var option = $('#display-select option:selected');
                display = displays_1.find(display => 
                {
                    return display.id == option.val();
                });
                $('#display #display-id').val(display.id);
                $('#display #display-size').val(display.size);
                $('#display #display-technology').val(display.technology);
                $('#display #display-resolution').val(display.resolution);
                $('#display #display-displayType').val(display.displayType);
                $('#display #display-refreshRate').val(display.refreshRate);
                $('#display #display-panelType').val(display.panelType);
            });
        })    //print data to console
        .catch(err => console.log('Request Failed', err)); // Catch errors

        // handler ram manufacturer selection
        $('#ram-manufacturer-select option').not('.select-an-option').remove();
        fetch('http://localhost:8080/api/manufacturer/get-all-wp')
        // Handle success
        .then(response => response.json())  // convert to json
        .then(json => 
        {
            for (var i=0;i<json.length;i++)
			{
				var option = '<option value="' + json[i].id + '">' + json[i].name + '</option>'
				$('#ram-manufacturer-select').append(option);
			}

            $('#ram-manufacturer-select').change(function() 
            {
                var option = $('#ram-manufacturer-select option:selected');
                $('#ram #ram-manufacturer-id').val(option.val());
                $('#ram #ram-manufacturer-input').val(option.text());
            });
        })    //print data to console
        .catch(err => console.log('Request Failed', err)); // Catch errors

        // handler ram type selection
        $('#ram-type-select option').not('.select-an-option').remove();
        fetch('http://localhost:8080/api/ram/ram-type')
        // Handle success
        .then(response => response.json())  // convert to json
        .then(json => 
        {
            for (var i=0;i<json.length;i++)
			{
				var option = '<option value="' + json[i] + '">' + json[i] + '</option>'
				$('#ram-type-select').append(option);
			}

            $('#ram-type-select').change(function()
            {
                var option = $('#ram-type-select option:selected');
                $("#ram-ramType").val(option.val());
            });
        })    //print data to console
        .catch(err => console.log('Request Failed', err)); // Catch errors

        // handler ram selection
        $('#ram-select option').not('.select-an-option').remove();
        fetch('http://localhost:8080/api/ram/all')
        // Handle success
        .then(response => response.json())  // convert to json
        .then(json => 
        {
            for (var i=0;i<json.length;i++)
			{
				var option = '<option value="' + json[i].id + '">' + json[i].model + " | Size:" + json[i].size + " GB| Bus:" + json[i].bus
                + " MHZ| Type:" + json[i].ramType
                + '</option>';
				$('#ram-select').append(option);
			}
            rams_1 = json;

            $('#ram-select').change(function()
            {
                $("#ram input[id!='create-new-ram-manufacturer'][id!='add-existing-ram-manufacturer']").val('');
                var option = $('#ram-select option:selected');
                ram = rams_1.find(ram => 
                {
                    return ram.id == option.val();
                });
                $('#ram #ram-id').val(ram.id);
                $('#ram #ram-model').val(ram.model);
                $('#ram #ram-size').val(ram.size);
                $('#ram #ram-ramType').val(ram.ramType);
                $('#ram #ram-bus').val(ram.bus);
                $('#ram #ram-manufacturer-id').val(ram.manufacturer == null ? '' : ram.manufacturer.id);
                $('#ram #ram-manufacturer-input').val(ram.manufacturer == null ? '' : ram.manufacturer.name);
            });
        })    //print data to console
        .catch(err => console.log('Request Failed', err)); // Catch errors

        // handler drive type selection
        $('#drive-type-select option').not('.select-an-option').remove();
        fetch('http://localhost:8080/api/drive/drive-type')
        // Handle success
        .then(response => response.json())  // convert to json
        .then(json => 
        {
            for (var i=0;i<json.length;i++)
			{
				var option = '<option value="' + json[i] + '">' + json[i] + '</option>'
				$('#drive-type-select').append(option);
			}

            $('#drive-type-select').change(function()
            {
                var option = $('#drive-type-select option:selected');
                $("#drive-driveType").val(option.val());
            });
        })    //print data to console
        .catch(err => console.log('Request Failed', err)); // Catch errors

        // handler drive manufacturer selection
        $('#drive-manufacturer-select option').not('.select-an-option').remove();
        fetch('http://localhost:8080/api/manufacturer/get-all-wp')
        // Handle success
        .then(response => response.json())  // convert to json
        .then(json => 
        {
            for (var i=0;i<json.length;i++)
			{
				var option = '<option value="' + json[i].id + '">' + json[i].name + '</option>'
				$('#drive-manufacturer-select').append(option);
			}

            $('#drive-manufacturer-select').change(function() 
            {
                var option = $('#drive-manufacturer-select option:selected');
                $('#drive #drive-manufacturer-id').val(option.val());
                $('#drive #drive-manufacturer-input').val(option.text());
            });
        })    //print data to console
        .catch(err => console.log('Request Failed', err)); // Catch errors

        // handler drive selection
        $('#drive-select option').not('.select-an-option').remove();
        fetch('http://localhost:8080/api/drive/all')
        // Handle success
        .then(response => response.json())  // convert to json
        .then(json => 
        {
            for (var i=0;i<json.length;i++)
			{
				var option = '<option value="' + json[i].id + '">' + json[i].model + " | Size:" + json[i].size + " GB | Type:" + json[i].driveType
                + '</option>';
				$('#drive-select').append(option);
			}
            drives_1 = json;

            $('#drive-select').change(function()
            {
                $("#drive input[id!='create-new-drive-manufacturer'][id!='add-existing-drive-manufacturer']").val('');
                var option = $('#drive-select option:selected');
                drive = drives_1.find(drive => 
                {
                    return drive.id == option.val();
                });
                $('#drive #drive-id').val(drive.id);
                $('#drive #drive-model').val(drive.model);
                $('#drive #drive-size').val(drive.size);
                $('#drive #drive-driveType').val(drive.driveType);
                $('#drive #drive-maxWrite').val(drive.maxWrite);
                $('#drive #drive-maxRead').val(drive.maxRead);
                $('#drive #drive-manufacturer-id').val(drive.manufacturer == null ? '' : drive.manufacturer.id);
                $('#drive #drive-manufacturer-input').val(drive.manufacturer == null ? '' : drive.manufacturer.name);
            });
        })    //print data to console
        .catch(err => console.log('Request Failed', err)); // Catch errors

        // handler graphic card type selection
        $('#graphic-card-type-select option').not('.select-an-option').remove();
        fetch('http://localhost:8080/api/graphic-card/type')
        // Handle success
        .then(response => response.json())  // convert to json
        .then(json => 
        {
            for (var i=0;i<json.length;i++)
			{
				var option = '<option value="' + json[i] + '">' + json[i] + '</option>'
				$('#graphic-card-type-select').append(option);
			}

            $('#graphic-card-type-select').change(function()
            {
                var option = $('#graphic-card-type-select option:selected');
                $("#graphic-card-type").val(option.val());
            });
        })    //print data to console
        .catch(err => console.log('Request Failed', err)); // Catch errors

        // handler graphic card manufacturer selection
        $('#graphic-card-manufacturer-select option').not('.select-an-option').remove();
        fetch('http://localhost:8080/api/manufacturer/get-all-wp')
        // Handle success
        .then(response => response.json())  // convert to json
        .then(json => 
        {
            for (var i=0;i<json.length;i++)
			{
				var option = '<option value="' + json[i].id + '">' + json[i].name + '</option>'
				$('#graphic-card-manufacturer-select').append(option);
			}

            $('#graphic-card-manufacturer-select').change(function() 
            {
                var option = $('#graphic-card-manufacturer-select option:selected');
                $('#graphic-card #graphic-card-manufacturer-id').val(option.val());
                $('#graphic-card #graphic-card-manufacturer-input').val(option.text());
            });
        })    //print data to console
        .catch(err => console.log('Request Failed', err)); // Catch errors

         // handler graphic card selection
         $('#graphic-card-select option').not('.select-an-option').remove();
         fetch('http://localhost:8080/api/graphic-card/all')
         // Handle success
         .then(response => response.json())  // convert to json
         .then(json => 
         {
             for (var i=0;i<json.length;i++)
             {
                 var option = '<option value="' + json[i].id + '">' + json[i].model + " | VRAM:" + json[i].memory + " GB | Type:" + json[i].graphicCardType
                 + '</option>';
                 $('#graphic-card-select').append(option);
             }
             graphicCards_1 = json;
 
             $('#graphic-card-select').change(function()
             {
                 $("#graphic-card input[id!='create-new-graphic-card-manufacturer'][id!='add-existing-graphic-card-manufacturer']").val('');
                 var option = $('#graphic-card-select option:selected');
                 graphicCard = graphicCards_1.find(graphicCard => 
                 {
                     return graphicCard.id == option.val();
                 });
                 $('#graphic-card #graphic-card-id').val(graphicCard.id);
                 $('#graphic-card #graphic-card-model').val(graphicCard.model);
                 $('#graphic-card #graphic-card-memory').val(graphicCard.memory);
                 $('#graphic-card #graphic-card-type').val(graphicCard.graphicCardType);
                 $('#graphic-card #graphic-card-manufacturer-id').val(graphicCard.manufacturer == null ? '' : graphicCard.manufacturer.id);
                 $('#graphic-card #graphic-card-manufacturer-input').val(graphicCard.manufacturer == null ? '' : graphicCard.manufacturer.name);
             });
         })    //print data to console
         .catch(err => console.log('Request Failed', err)); // Catch errors

         // handler operating system selection
         $('#operating-system-select option').not('.select-an-option').remove();
         fetch('http://localhost:8080/api/laptop/operating-system/all')
         // Handle success
         .then(response => response.json())  // convert to json
         .then(json => 
         {
             for (var i=0;i<json.length;i++)
             {
                 var option = '<option value="' + json[i].id + '">' + json[i].name + " | Type:" + json[i].type + " bit"
                 + '</option>';
                 $('#operating-system-select').append(option);
             }
             operatingSystems_1 = json;
 
             $('#operating-system-select').change(function()
             {
                 $("#operating-system input").val('');
                 var option = $('#operating-system-select option:selected');
                 operatingSystem = operatingSystems_1.find(operatingSystem => 
                 {
                     return operatingSystem.id == option.val();
                 });
                 $('#operating-system #operating-system-id').val(operatingSystem.id);
                 $('#operating-system #operating-system-name').val(operatingSystem.name);
                 $('#operating-system #operating-system-type').val(operatingSystem.type);
             });
         })    //print data to console
         .catch(err => console.log('Request Failed', err)); // Catch errors

         // handler battery selection
         $('#battery-select option').not('.select-an-option').remove();
         fetch('http://localhost:8080/api/laptop/battery/all')
         // Handle success
         .then(response => response.json())  // convert to json
         .then(json => 
         {
             for (var i=0;i<json.length;i++)
             {
                 var option = '<option value="' + json[i].id + '">' + json[i].model + " | Voltages:" + json[i].voltages + " Volts"
                 + '</option>';
                 $('#battery-select').append(option);
             }
             batteries_1 = json;
 
             $('#battery-select').change(function()
             {
                 $("#battery input").val('');
                 var option = $('#battery-select option:selected');
                 battery = batteries_1.find(battery => 
                 {
                     return battery.id == option.val();
                 });
                 $('#battery #battery-id').val(battery.id);
                 $('#battery #battery-model').val(battery.model);
                 $('#battery #battery-voltages').val(battery.voltages);
                 $('#battery #battery-estimatedWorkingTime').val(battery.estimatedWorkingTime);
             });
         })    //print data to console
         .catch(err => console.log('Request Failed', err)); // Catch errors

         // handler product manufacturer selection
        $('#manufacturer-select option').not('.select-an-option').remove();
        fetch('http://localhost:8080/api/manufacturer/get-all-wp')
        // Handle success
        .then(response => response.json())  // convert to json
        .then(json => 
        {
            for (var i=0;i<json.length;i++)
			{
				var option = '<option value="' + json[i].id + '">' + json[i].name + '</option>'
				$('#manufacturer-select').append(option);
			}

            $('#manufacturer-select').change(function() 
            {
                var option = $('#manufacturer-select option:selected');
                $('#manufacturer #manufacturer-id').val(option.val());
                $('#manufacturer #manufacturer-input').val(option.text());
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
                $('#cpu-manufacturer-id').val('');
			}
			
			if (btn.attr('value') == 'add-existing')
			{
				$('#cpu-manufacturer-input').attr('hidden', 'hidden');
				$('#cpu-manufacturer select').removeAttr('hidden');
			}
		});

        //handle ram-manufacturer button events
        $("#ram-manufacturer-radio-btn [type='radio']").change(function(event)
		{
			var btn = $(this);
			if (btn.attr('value') == 'create-new')
			{
				$('#ram-manufacturer select').attr('hidden', 'hidden');
				$('#ram-manufacturer-input').removeAttr('hidden');
                $('#ram-manufacturer-id').val('');
			}
			
			if (btn.attr('value') == 'add-existing')
			{
				$('#ram-manufacturer-input').attr('hidden', 'hidden');
				$('#ram-manufacturer select').removeAttr('hidden');
			}
		});

        //handle drive-manufacturer button events
        $("#drive-manufacturer-radio-btn [type='radio']").change(function(event)
		{
			var btn = $(this);
			if (btn.attr('value') == 'create-new')
			{
				$('#drive-manufacturer select').attr('hidden', 'hidden');
				$('#drive-manufacturer-input').removeAttr('hidden');
                $('#drive-manufacturer-id').val('');
			}
			
			if (btn.attr('value') == 'add-existing')
			{
				$('#drive-manufacturer-input').attr('hidden', 'hidden');
				$('#drive-manufacturer select').removeAttr('hidden');
			}
		});

        //handle graphic-card-manufacturer button events
        $("#graphic-card-manufacturer-radio-btn [type='radio']").change(function(event)
		{
			var btn = $(this);
			if (btn.attr('value') == 'create-new')
			{
				$('#graphic-card-manufacturer select').attr('hidden', 'hidden');
				$('#graphic-card-manufacturer-input').removeAttr('hidden');
                $('#graphic-card-manufacturer-id').val('');
			}
			
			if (btn.attr('value') == 'add-existing')
			{
				$('#graphic-card-manufacturer-input').attr('hidden', 'hidden');
				$('#graphic-card-manufacturer select').removeAttr('hidden');
			}
		});

        //handle manufacturer button events
        $("#manufacturer-radio-btn [type='radio']").change(function(event)
		{
			var btn = $(this);
			if (btn.attr('value') == 'create-new')
			{
				$('#manufacturer select').attr('hidden', 'hidden');
				$('#manufacturer-input').removeAttr('hidden');
                $('#manufacturer-id').val('');
			}
			
			if (btn.attr('value') == 'add-existing')
			{
				$('#manufacturer-input').attr('hidden', 'hidden');
				$('#manufacturer select').removeAttr('hidden');
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
                $("#cpu input[id!='create-new-cpu-manufacturer'][id!='add-existing-cpu-manufacturer']").val('');
                $('#cpu-manufacturer select').attr('hidden', 'hidden');
                $('#cpu-manufacturer-input').removeAttr('hidden');
                $('#cpu-select').removeAttr('hidden');
            }
        });

        //handle display create new | add existing button events
        $("#display-nav a").click(function(event)
        {
            var nav = $(event.target);
            $("#display-nav a").removeClass('active');
            nav.addClass('active');
            
            if(nav.attr('id') == 'create-new')
            {
                $("#display input[id!='display-id'][id!='display-displayType'][id!='display-panelType']").removeAttr('disabled');
                $("#display input").val('');
                $('#display select').removeAttr('hidden');
                $('#display-select').attr('hidden', 'hidden');
            }
            if(nav.attr('id') == 'add-existing')
            {
                $('#display input').attr('disabled', 'disabled').val('');
                $('#display select').attr('hidden', 'hidden');
                $('#display-select').removeAttr('hidden');
            }
        });

        //handle ram create new | add existing button events
        $("#ram-nav a").click(function(event)
        {
            var nav = $(event.target);
            $("#ram-nav a").removeClass('active');
            nav.addClass('active');
            
            if(nav.attr('id') == 'create-new')
            {
                $("#ram input[id!='ram-id'][id!='ram-ramType']").removeAttr('disabled');
                $("#ram input[id!='create-new-ram-manufacturer'][id!='add-existing-ram-manufacturer']").val('');
                $('#ram select[id!="ram-manufacturer-select"]').removeAttr('hidden');
                $('#ram-select').attr('hidden', 'hidden');
            }
            if(nav.attr('id') == 'add-existing')
            {
                $("#ram input").attr('disabled', 'disabled');
                $("#ram input[id!='create-new-ram-manufacturer'][id!='add-existing-ram-manufacturer']").val('');
                $('#ram select').attr('hidden', 'hidden');
                $('#ram-manufacturer-input').removeAttr('hidden');
                $('#ram-select').removeAttr('hidden');
            }
        });

        //handle drive create new | add existing button events
        $("#drive-nav a").click(function(event)
        {
            var nav = $(event.target);
            $("#drive-nav a").removeClass('active');
            nav.addClass('active');
            
            if(nav.attr('id') == 'create-new')
            {
                $("#drive input[id!='drive-id'][id!='drive-driveType']").removeAttr('disabled');
                $("#drive input[id!='create-new-drive-manufacturer'][id!='add-existing-drive-manufacturer']").val('');
                $('#drive select[id!="drive-manufacturer-select"]').removeAttr('hidden');
                $('#drive-select').attr('hidden', 'hidden');
            }
            if(nav.attr('id') == 'add-existing')
            {
                $("#drive input").attr('disabled', 'disabled');
                $("#drive input[id!='create-new-drive-manufacturer'][id!='add-existing-drive-manufacturer']").val('');
                $('#drive select').attr('hidden', 'hidden');
                $('#drive-manufacturer-input').removeAttr('hidden');
                $('#drive-select').removeAttr('hidden');
            }
        });

        //handle graphic-card create new | add existing button events
        $("#graphic-card-nav a").click(function(event)
        {
            var nav = $(event.target);
            $("#graphic-card-nav a").removeClass('active');
            nav.addClass('active');
            
            if(nav.attr('id') == 'create-new')
            {
                $("#graphic-card input[id!='graphic-card-id'][id!='graphic-card-type']").removeAttr('disabled');
                $("#graphic-card input[id!='create-new-graphic-card-manufacturer'][id!='add-existing-graphic-card-manufacturer']").val('');
                $('#graphic-card select[id!="graphic-card-manufacturer-select"]').removeAttr('hidden');
                $('#graphic-card-select').attr('hidden', 'hidden');
            }
            if(nav.attr('id') == 'add-existing')
            {
                $("#graphic-card input").attr('disabled', 'disabled');
                $("#graphic-card input[id!='create-new-graphic-card-manufacturer'][id!='add-existing-graphic-card-manufacturer']").val('');
                $('#graphic-card select').attr('hidden', 'hidden');
                $('#graphic-card-manufacturer-input').removeAttr('hidden');
                $('#graphic-card-select').removeAttr('hidden');
            }
        });

        //handle operating system create new | add existing button events
        $("#operating-system-nav a").click(function(event)
        {
            var nav = $(event.target);
            $("#operating-system-nav a").removeClass('active');
            nav.addClass('active');
            
            if(nav.attr('id') == 'create-new')
            {
                $("#operating-system input").removeAttr('disabled');
                $("#operating-system input").val('');
                $('#operating-system-select').attr('hidden', 'hidden');
            }
            if(nav.attr('id') == 'add-existing')
            {
                $("#operating-system input").attr('disabled', 'disabled');
                $("#operating-system input").val('');
                $('#operating-system-select').removeAttr('hidden');
            }
        });

        $("#battery-nav a").click(function(event)
        {
            var nav = $(event.target);
            $("#battery-nav a").removeClass('active');
            nav.addClass('active');
            
            if(nav.attr('id') == 'create-new')
            {
                $("#battery input[id!='battery-id']").removeAttr('disabled');
                $("#battery input").val('');
                $('#battery-select').attr('hidden', 'hidden');
            }
            if(nav.attr('id') == 'add-existing')
            {
                $("#battery input").attr('disabled', 'disabled');
                $("#battery input").val('');
                $('#battery-select').removeAttr('hidden');
            }
        });
    }

    function addUpdateButtonHandler()
    {
        $('.btnUpdate').on('click', function(event) 
        {
            var product = {}, laptop = {};
            laptop.name = $(".container #name").val();

            product.laptop = laptop;
            console.log(JSON.stringify(product));
        });
    }

    function addAddButtonHandler()
    {
        $('.btn-save-or-update').on('click', function(event) 
        {
            var product = {}, laptop = {}, cpus = [], cpu = {}, cpu_manufacturer = {},
            display = {}, rams = [], ram = {}, ram_manufacturer = {}, drives = [], 
            drive = {}, drive_manufacturer = {}, graphic_cards = [], graphic_card = {},
            graphic_card_manufacturer = {}, operating_system = {}, battery = {}, manufacturer = {},
            category = {};

            laptop.name = $(".container #name").val();
            laptop.size = $(".container #size").val();
            laptop.weight = $(".container #weight").val();
            laptop.ramSlot = $(".container #ramSlot").val();
            laptop.driveSlot = $(".container #driveSlot").val();
            laptop.color = $(".container #color").val();
            laptop.material = $(".container #material").val();
            product.price = $(".container #price").val();
            product.sold = $(".container #sold").val();
            product.stock = $(".container #stock").val();
            product.warranty = $(".container #warranty").val();
            product.portInfo = $(".container #portInfo").val();

            if ($('#cpu #cpu-id').val() != '') cpu.id = $('#cpu #cpu-id').val();
            cpu.model = $('#cpu #cpu-model').val();
            cpu.core = $('#cpu #cpu-core').val();
            cpu.thread = $('#cpu #cpu-thread').val();
            cpu.processorSpeed = $('#cpu #cpu-processorSpeed').val();
            cpu.maxProcessorSpeed = $('#cpu #cpu-maxProcessorSpeed').val();
            cpu.additionalInfo = $('#cpu #cpu-additionalInfo').val();
            if ($('#cpu #cpu-manufacturer-id').val() != '') cpu_manufacturer.id = $('#cpu #cpu-manufacturer-id').val();
            if ($('#cpu #cpu-manufacturer-input').val() == '') cpu_manufacturer = null;
            else cpu_manufacturer.name = $('#cpu #cpu-manufacturer-input').val();
            cpu.manufacturer = cpu_manufacturer;
            cpus.push(cpu);
            laptop.cpus = cpus;

            if ($('#display #display-id').val() != '') display.id = $('#display #display-id').val();
            display.size = $('#display #display-size').val();
            display.technology = $('#display #display-technology').val();
            display.resolution = $('#display #display-resolution').val();
            display.displayType = $('#display #display-displayType').val();
            display.refreshRate = $('#display #display-refreshRate').val();
            display.panelType = $('#display #display-panelType').val();
            laptop.display = display;

            ram.id =  $('#ram #ram-id').val();
            ram.model =  $('#ram #ram-model').val();
            ram.size =  $('#ram #ram-size').val();
            ram.ramType =  $('#ram #ram-ramType').val();
            ram.bus =  $('#ram #ram-bus').val();
            ram_manufacturer.id =  $('#ram #ram-manufacturer-id').val();
            if ($('#ram #ram-manufacturer-input').val() == '') ram_manufacturer = null;
            else ram_manufacturer.name =  $('#ram #ram-manufacturer-input').val();
            ram.manufacturer = ram_manufacturer;
            rams.push(ram);
            laptop.rams = rams;

            drive.id =  $('#drive #drive-id').val();
            drive.model =  $('#drive #drive-model').val();
            drive.size =  $('#drive #drive-size').val();
            drive.driveType =  $('#drive #drive-driveType').val();
            drive.maxWrite =  $('#drive #drive-maxWrite').val();
            drive.maxRead =  $('#drive #drive-maxRead').val();
            drive_manufacturer.id =  $('#drive #drive-manufacturer-id').val();
            if ($('#drive #drive-manufacturer-input').val() == '') drive_manufacturer = null;
            else drive_manufacturer.name =  $('#drive #drive-manufacturer-input').val();
            drive.manufacturer = drive_manufacturer;
            drives.push(drive);
            laptop.drives = drives;

            graphic_card.id =  $('#graphic-card #graphic-card-id').val();
            graphic_card.model =  $('#graphic-card #graphic-card-model').val();
            graphic_card.memory =  $('#graphic-card #graphic-card-memory').val();
            graphic_card.graphicCardType =  $('#graphic-card #graphic-card-type').val();
            graphic_card_manufacturer.id =  $('#graphic-card #graphic-card-manufacturer-id').val();
            if ($('#graphic-card #graphic-card-manufacturer-input').val() == '') graphic_card_manufacturer = null
            else graphic_card_manufacturer.name =  $('#graphic-card #graphic-card-manufacturer-input').val();
            graphic_card.manufacturer = graphic_card_manufacturer;
            graphic_cards.push(graphic_card);
            laptop.graphicCards = graphic_cards;

            operating_system.id = $('#operating-system #operating-system-id').val();
            operating_system.name = $('#operating-system #operating-system-name').val();
            operating_system.type = $('#operating-system #operating-system-type').val();
            laptop.operatingSystem = operating_system;

            battery.id = $('#battery #battery-id').val();
            battery.model = $('#battery #battery-model').val();
            battery.voltages = $('#battery #battery-voltages').val();
            battery.estimatedWorkingTime = $('#battery #battery-estimatedWorkingTime').val();
            laptop.battery = battery;

            manufacturer.id = $('#manufacturer #manufacturer-id').val();
            if ($('#manufacturer #manufacturer-input').val() == '') manufacturer = null;
            else manufacturer.name = $('#manufacturer #manufacturer-input').val();
            laptop.manufacturer = manufacturer;

            product.laptop = laptop;
            category.id = $('#category #category-id').val();
            category.name = $('#category #category-name').val();
            product.category = category;

            replaceEmptyStringWithNull(product);
            
            console.log(JSON.stringify(product, null, 4));

            const requestOptions = 
            {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(product)
            };
            fetch('http://localhost:8080/api/product/save', requestOptions)
            .then((response) => 
            {
                (async () => {
                    const json = await response.json();
                    if (json.ok == "true")
                    {
                        console.log(json.id);
                        $("#laptop-images").attr("action", "http://localhost:8080/api/product/upload-images/" + json.id)
                        $("#laptop-images").trigger("submit");
                    }
                    else
                    {
                        console.log(json);
                    }
                })();
                
            })
            .catch(erorr => console.log(erorr));
        });
    }

    function replaceEmptyStringWithNull(object)
    {
        for (var key in object)
        {
            if (typeof object[key] == "object" && object[key] != null) replaceEmptyStringWithNull(object[key]);
            if (typeof object[key] == "string" && object[key] == "") object[key] = null;
        }
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