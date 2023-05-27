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
        $('.btnAddCategory').on('click', 
            function(event)
            {
                resetModal();
                $('.categoryForm').addClass('Add');
                $('#categoryModal').modal({keyboard: true, backdrop: true});
            }
        );
    }

    function resetModal()
    {
        $('.categoryForm').removeClass('Update').removeClass('Add');
        $('.modal-body #id').attr('value', '');
        $('.modal-body #nameOfCategory').attr('value', '');
    }

    function fetchGet(_page)
    {
        fetch('http://localhost:8080/api/category/get-all?page=' + _page)
        // Handle success
        .then(response => response.json())  // convert to json
        .then(json => display_category(json))    //print data to console
        .catch(err => console.log('Request Failed', err)); // Catch errors
    }

    function display_category(json)
    {
        $('.categoryTable tbody').empty();
        var categories = json.content;
        for (var i=0;i<categories.length;i++)
        {
            var categoryRow = '<tr style="text-align: center;">' +
											'<td width="20%"">' + categories[i].id + '</td>' +
											'<td>' + categories[i].name + '</td>' +
											'<td>' + '<input type="hidden" value=' + categories[i].id + '>' +
											'<button class="btn btn-primary btnUpdateCategory" >Update</button>' +
											'   <button class="btn btn-danger btnDeleteCategory">Delete</button></td>' +
											'</tr>';
					
            $('.categoryTable tbody').append(categoryRow);
        }
        bindButtonUpdateHandler();
        bindButtonDeleteHandler();
    }

    function bindButtonUpdateHandler()
    {
        $('.btnUpdateCategory').on("click",
            function (event) 
            {
                resetModal();
                var id = $(event.target).siblings('input').attr('value');
                var name = $(event.target).parent().siblings()[1].innerHTML;
                $('.modal-body #id').attr('value', id);
                $('.modal-body #nameOfCategory').attr('value', name);
                $('.categoryForm').addClass('Update');
                $('#categoryModal').modal({keyboard: true, backdrop: true});
            }
        );
    }

    function bindButtonDeleteHandler()
    {
        $('.btnDeleteCategory').on("click", 
            function (event)
            {
                if (confirm("Are you sure") == true)
                {
                    var id = $(event.target).siblings('input').attr('value');
                    fetch('http://localhost:8080/api/category/delete/' + id, { method: 'DELETE' })
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
                var category;
                if ($('.categoryForm').hasClass('Update'))
                {
                    id1 = $('.modal-body #id').val();
                    name1 = $('.modal-body #nameOfCategory').val();
                    category = {id : id1, name : name1};
                    
                    (async () => {
                        const requestOptions = {
                            method: 'PUT',
                            headers: { 'Content-Type': 'application/json' },
                            body: JSON.stringify(category)
                        };
                        const response = await fetch('http://localhost:8080/api/category/update', requestOptions)
                        .catch(erorr => console.log(erorr));

                        const data = await response.json();
                        if (data.ok == 'true')
                        {
                            $('#categoryModal').modal('hide');
                            alert("Success");
                        }
                        else 
                        {
                            alert(JSON.stringify(data));
                        }
                    })();
                }

                if ($('.categoryForm').hasClass('Add'))
                {
                    name1 = $('.modal-body #nameOfCategory').val();
                    category = {id : null, name : name1};

                    (async () => {
                        const requestOptions = {
                            method: 'POST',
                            headers: { 'Content-Type': 'application/json' },
                            body: JSON.stringify(category)
                        };
                        const response = await fetch('http://localhost:8080/api/category/add', requestOptions)
                        .catch(erorr => console.log(erorr));

                        const data = await response.json();
                        if (data.ok == 'true')
                        {
                            $('#categoryModal').modal('hide');
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
            fetch('http://localhost:8080/api/category/get-all?page=1')
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