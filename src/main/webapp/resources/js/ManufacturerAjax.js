$(document).ready(function()
{
    var current = 0; var totalPages; var current_page = 0;
    var page_per_load = 5;
    var max_current;
    function resetVar() {current = 0}
    bindButtonSubmitHandler();bindButtonAddHandler();
    fetchGet(1);showPagination();

    function bindButtonAddHandler()
    {
        $('.btnAddManufacturer').on('click', 
            function(event)
            {
                resetModal();
                $('.manufacturerForm').addClass('Add');
                $('#manufacturerModal').modal({keyboard: true, backdrop: true});
            }
        );
    }

    function resetModal()
    {
        $('.manufacturerForm').removeClass('Update').removeClass('Add');
        $('.modal-body #id').attr('value', '');
        $('.modal-body #nameOfManufacturer').attr('value', '');
    }

    function fetchGet(_page)
    {
        fetch('http://localhost:8080/api/manufacturer/get-all?page=' + _page)
        // Handle success
        .then(response => response.json())  // convert to json
        .then(json => display_manufacturer(json))    //print data to console
        .catch(err => console.log('Request Failed', err)); // Catch errors
    }

    function display_manufacturer(json)
    {
        $('.manufacturerTable tbody').empty();
        var manufacturers = json.content;
        for (var i=0;i<manufacturers.length;i++)
        {
            var manufacturerRow = '<tr style="text-align: center;">' +
											'<td width="20%"">' + manufacturers[i].id + '</td>' +
											'<td>' + manufacturers[i].name + '</td>' +
											'<td>' + '<input type="hidden" value=' + manufacturers[i].id + '>' +
											'<button class="btn btn-primary btnUpdateManufacturer" >Update</button>' +
											'   <button class="btn btn-danger btnDeleteManufacturer">Delete</button></td>' +
											'</tr>';
					
            $('.manufacturerTable tbody').append(manufacturerRow);
        }
        bindButtonUpdateHandler();
        bindButtonDeleteHandler();
    }

    function bindButtonUpdateHandler()
    {
        $('.btnUpdateManufacturer').on("click",
            function (event) 
            {
                resetModal();
                var id = $(event.target).siblings('input').attr('value');
                var name = $(event.target).parent().siblings()[1].innerHTML;
                $('.modal-body #id').attr('value', id);
                $('.modal-body #nameOfManufacturer').attr('value', name);
                $('.manufacturerForm').addClass('Update');
                $('#manufacturerModal').modal({keyboard: true, backdrop: true});
            }
        );
    }

    function bindButtonDeleteHandler()
    {
        $('.btnDeleteManufacturer').on("click", 
            function (event)
            {
                if (confirm("Are you sure") == true)
                {
                    var id = $(event.target).siblings('input').attr('value');
                    fetch('http://localhost:8080/api/manufacturer/delete/' + id, { method: 'DELETE' })
                    .then(async response => response.json())
                    .then(json => 
                            {
                                if (json.ok)
                                {
                                    alert("Success");
                                }
                                handleAfterAddOrDelete();
                            }
                        )
                    .catch(error => {console.log(error);});
                }
            }
        );
    }

    function bindButtonSubmitHandler()
    {
        $('#btnSubmit').on("click",
            function(event)
            {
                var id1;
                var name1;
                var manufacturer;
                if ($('.manufacturerForm').hasClass('Update'))
                {
                    id1 = $('.modal-body #id').val();
                    name1 = $('.modal-body #nameOfManufacturer').val();
                    manufacturer = {id : id1, name : name1};
                    
                    (async () => {
                        const requestOptions = {
                            method: 'PUT',
                            headers: { 'Content-Type': 'application/json' },
                            body: JSON.stringify(manufacturer)
                        };
                        const response = await fetch('http://localhost:8080/api/manufacturer/update', requestOptions)
                        .catch(erorr => console.log(erorr));

                        const data = await response.json();
                        if (data.ok == 'true')
                        {
                            $('#manufacturerModal').modal('hide');
                            alert("Success");
                        }
                        else 
                        {
                            alert(JSON.stringify(data));
                        }
                    })();
                }

                if ($('.manufacturerForm').hasClass('Add'))
                {
                    name1 = $('.modal-body #nameOfManufacturer').val();
                    manufacturer = {id : null, name : name1};

                    (async () => {
                        const requestOptions = {
                            method: 'POST',
                            headers: { 'Content-Type': 'application/json' },
                            body: JSON.stringify(manufacturer)
                        };
                        const response = await fetch('http://localhost:8080/api/manufacturer/add', requestOptions)
                        .catch(erorr => console.log(erorr));

                        const data = await response.json();
                        if (data.ok == 'true')
                        {
                            $('#manufacturerModal').modal('hide');
                            alert("Success");
                            handleAfterAddOrDelete();
                        }
                        else 
                        {
                            alert(JSON.stringify(data));
                        }
                    })();
                }
            }
        );
    }

    async function handleAfterAddOrDelete()
    {
        await showPagination();
        resetVar();
        fetchGet(current_page);
    }

    function showPagination()
    {
        return new Promise((resolve) => 
        {
            $('.pagination').empty();
            fetch('http://localhost:8080/api/manufacturer/get-all?page=1')
            // Handle success
            .then(response => response.json())  // convert to json
            .then(json => 
            {
                totalPages = json.totalPages; 

                $('.pagination').append('<li class="page-item"><a class="page-link" id="<" href="#">Previous</a></li>');
                for (var i=1;i<=totalPages;i++)
                {
                    console.log("Have run this");
                    var page = `<li class="page-item num"><a class="page-link" id=${i} href="#">` + i + '</a></li>';
                    $('.pagination').append(page);
                    if (i > page_per_load) $(`.pagination li #${i}`).hide("slow");
                }
                $('.pagination').append('<li class="page-item"><a class="page-link" id=">" href="#">Next</a></li>');
                $('.pagination li #1').parent().addClass('active')
                bindPaginationHandler();
            })    //print data to console
            .catch(err => console.log('Request Failed', err)); // Catch errors
            resolve();
        });
    }

    function bindPaginationHandler()
    {
        $('.pagination .num a').on("click", 
            function(event)
            {
                var button = event.target; // dom
                $('.pagination li').removeClass('active');
                $(button).parent().addClass('active');
                current_page = button.id;
                fetchGet(button.id)
            }
        );

        max_current = (totalPages%page_per_load==0)?Math.trunc(totalPages/page_per_load)-1:Math.trunc(totalPages/page_per_load);
        $('.pagination li [id="<"]').on("click",
            function(event)
            {
                if (current != 0) 
                {
                    $('.pagination .num a').hide("slow");
                    current--;
                    for (var i=(current*page_per_load+1)
                        ;i<=(current*page_per_load+5)
                        ;i++
                    )
                    {
                        $('.pagination li #'+i).show();
                    }
                }
            }
        );

        $('.pagination li [id=">"]').on("click",
            function(event)
            {
                if (current != max_current)
                {
                    $('.pagination .num a').hide("slow");
                    current++;
                    for (var i=(current*page_per_load+1)
                        ;i<=(current==max_current?totalPages:current*page_per_load+page_per_load)
                        ;i++
                    )
                    {
                        console.log(i);
                        $('.pagination li #'+i).show();
                    }
                }
            }
        );
    }


});