$( document ).ready(function() {
	//VALIDATE ADDBOOK FORM-----------------------------------------------------------------------------------------------------
	$('#addBookForm').validate({
		rules : {
			inputISBN : {
				required : true,
				maxlength : 13
			},
			inputTotal : {
				required : true,
				digits : true,
				minlength: 1,
				maxlength: 3
			}
		},
		messages : {
			inputISBN : {
				required : 'ISBN is required',
				maxlength : 'ISBN must be less than 12 characters'
			},
			inputTotal : {
				required : 'Total is required',
				digits : 'Total must be number',
				minlength: 'Total must be greater than 1',
				maxlength: 'Total must be less than 999'
			}
		}
	});
	
	//CLOSE ADD BOOK POP UP-----------------------------------------------------------------------------------------------------
	$('#add-close').click(function() {
		$('#inputISBN').val('');
		$('#inputTitle').val('');
		$('#inputAuthor').val('');
		$('#inputShortDescription').val('');
		$('#inputPublishYear').val('');
		$('#inputImage').attr("src", "");
		$('#inputTotal').val('');
		$('#spin-icon').attr('hidden',true);
		$('#close-icon').attr('hidden',true);	
		$('#check-icon').attr('hidden',true);	
	});
	
	//AUTO GENDER BOOK INFO BY TYPE ISBN----------------------------------------------------------------------------------------
	$('#inputISBN').keyup(function() {
						var search = document.getElementById('inputISBN').value
						$('#addBook').attr("disabled","");
						if ($('#inputISBN').val().length == 10
								|| $('#inputISBN').val().length == 13) {
							$('#spin-icon').attr('hidden',false);
							$('#close-icon').attr('hidden',true);	
							$('#check-icon').attr('hidden',true);
							$('#addBook').attr("disabled","");
							// checkvalidateisbn();
							$.ajax({
										url : 'https://www.googleapis.com/books/v1/volumes?q=isbn:'
												+ search,
										dataType : 'json',
										type : 'GET',
										success : function(data) {
											if (data.totalItems == 0) {
												$('#inputTitle').val('')
												$('#inputAuthor').val('')
												$('#inputShortDescription').val('')
												$('#inputPublishYear').val('')
												$('#inputImage').attr("src", "")
												$('#inputTotal').val('')
												$('#spin-icon').attr('hidden',true);
												$('#close-icon').attr('hidden',false);	
												$('#check-icon').attr('hidden',true);	
												$('#addBook').attr("disabled","");
											} else {
												$('#inputTitle').val(data.items[0].volumeInfo.title);
												$('#inputAuthor').val(data.items[0].volumeInfo.authors);
												$('#inputShortDescription').val(data.items[0].volumeInfo.description);
												$('#inputPublishYear').val(data.items[0].volumeInfo.publishedDate.substr(0,4));
												$('#inputImage').attr("src",data.items[0].volumeInfo.imageLinks.thumbnail);
												$('#spin-icon').attr('hidden',true);
												$('#close-icon').attr('hidden',true);
												$('#check-icon').attr('hidden',false);												
												$('#addBook').removeAttr("disabled");
											}

										}					
									});
						} else {
							$('#inputTitle').val('')
							$('#inputAuthor').val('')
							$('#inputShortDescription').val('')
							$('#inputPublishYear').val('')
							$('#inputImage').attr("src", "")
							$('#inputTotal').val('')
							$('#spin-icon').attr('hidden',true);
							$('#close-icon').attr('hidden',false);	
							$('#check-icon').attr('hidden',true);
						}
	});

	//PAGINATE------------------------------------------------------------------------------------------------------------------
	total = $("#books").attr("num");
    window.pagObj = $('#pagination').twbsPagination({
        totalPages: total,
        visiblePages: 10,
        onPageClick: function (evt, page) {
            $("#book-management").empty();
            var url = "./home/page/"+page;
            $.ajax({
                url : url,
                success : function(data) {
                    $.each(data, function(key, val){
                    	setdataaline(val);                       
                                                
                    });
                }
            });
        }
    });
    //ADD BOOK
    $("body").off("click", "#addBook").on("click", "#addBook", function() {
    	url = '/bookmanagement/savebook';
    	libIsbn = {
    			"isbn" : $('#inputISBN').val(),
    			"totalBook" : $('#inputTotal').val(),
    			"author" : $('#inputAuthor').val(),
    			"publishYear" : $('#inputPublishYear').val(),
    			"image" : $('#inputImage').attr("src"),
    			"shortDescription" : $('#inputShortDescription').val(),
    			"titleOfBook" : $('#inputTitle').val()
    		}
    		isbn = $('#book-isbn').val().trim();
    		$.ajax({
    			type : "POST",
    			contentType : 'application/json; charset=utf-8',
    			url : url,
    			data : JSON.stringify(libIsbn), // Note it is important
    			success : function(data) {
    				$("#books").attr('num',data[0]);
                	$("#books").trigger('click');
                	if(data[1].indexOf('Successful')!=-1){
						swal("Successful!", data[1], "success");
					}else{
						swal("Error!", data[1], "error");
					}
    			},
    			error : function(e) {
    				swal("Error!", "System error! Please try again.", "error");
    				console.log("ERROR : ", e);
    			}
    		});
    });
    //SHOW EDIT POPUP-----------------------------------------------------------------------------------------------------------
    $("body").off('click', '.btn-edit').on('click', '.btn-edit', function(){
    	$.ajax({
    		type : "GET",
    		url: "/home/book/"+$(this).attr("isbn"),
    		success : function(data) {
			    $("#book-isbn").val(data.isbn);
			    $("#book-title").val(data.book.titleOfBook);
			    $("#book-author").val(data.book.author);
			    $("#book-publish-year").val(data.book.publishYear);
			    $("#book-description").val(data.book.shortDescription);
			    $('#book-total').val(data.totalBook);
			    $('#inputImage2').attr("src",data.book.image);
    		},
	    	error : function(e) {
				console.log("ERROR : ", e);
			}
    	});
    });
    
    //EDIT BOOK ----------------------------------------------------------------------------------------------------------------
    $("body").off("click", ".btn-edit-book").on("click", ".btn-edit-book", function() {
        var url = "/bookmanagement/editbook",
        libIsbn = {
     			"isbn" : $('#book-isbn').val(),
     			"totalBook" : $('#book-total').val(),
     			"author" : $('#book-author').val(),
     			"publishYear" : $('#book-publish-year').val(),
     			"shortDescription" : $('#book-description').val(),
     			"titleOfBook" : $('#book-title').val()
     	}
        isbn = $('#book-isbn').val().trim();
        screenTop = $('html').scrollTop();
     	$.ajax({
     		type : "POST",
     		contentType : 'application/json; charset=utf-8',
     		url : url,
     		data : JSON.stringify(libIsbn), // Note it is important
     		success : function(data) {   			
               	if(data.indexOf('Successful')!=-1){
					swal("Successful!", data, "success");
					element = $(".active");
					element.removeClass('active');
					element.children().trigger('click');
					$('html, body').animate({
				        scrollTop: screenTop
				    }, 100);
					$(document).click(function(){
						$(".btn-edit[isbn='" + isbn +"']").parent().parent().css("background-color","rgba(0,255,0,0.3)");
						
					});
					
				}else{
					swal("Error!", data, "error");
				}
     		},
	     	error : function(e) {
				swal("Error!", "System error! Please try again.", "error");
				console.log("ERROR : ", e);
			}
	     });
     });
    
    //DELETE BOOK---------------------------------------------------------------------------------------------------------------
    $("body").off("click", ".btn-delete-book").on("click", ".btn-delete-book", function(ev) {
    	isbn = $(this).attr("isbn");
    	screenTop = $('html').scrollTop();
    	$.ajax({
            url : "/bookmanagement/delete/"+isbn,
            type : "GET",
            success : function(data) {
            	if(data[1].indexOf('Successful')!=-1){
					swal("Successful!", data[1], "success");
    				$("#books").attr('num',data);
                	$("#books").trigger('click');
                	element = $(".active");
					element.removeClass('active');
					element.children().trigger('click');
					$('html, body').animate({
				        scrollTop: screenTop
				    }, 100);
				}else {
					swal("Error!", data[1], "error");
				}	
            }
    	});
    });
    
    //SEARCH BOOK---------------------------------------------------------------------------------------------------------------
    $('#w-input-searchbook').autocomplete({
		autoSelectFirst: true,
		serviceUrl: '/search/book',
		paramName: "titlebook",
		delimiter: ",",
		onSelect: function(suggestion) {
			checkvalidateisbn(suggestion.data);
			$(".divNextPage").attr('hidden','');
        },
	   transformResult: function(response) {
		    	
		return {      	
		  suggestions: $.map($.parseJSON(response), function(item) {
			  var titleOfBook = $.trim(item.book.titleOfBook);
			  var author = $.trim(item.book.author);
		      return { value: titleOfBook + ' ( Author: ' + author + ' ) ', data: item.isbn };
		   })
		            
		 };
        }
	 });
});

function checkvalidateisbn(isbn) {
	$.ajax({
		type : "GET",
		dataType : 'json',
		contentType : "application/json",
		url : '/bookmanagement/checkisbn/' + isbn,
		success : function(val) {
			$("#book-management").empty();
			setdataaline(val);
		},
		error : function(e) {
			console.log("ERROR : ", e);
		}
	});
}    
    
function setdataaline(val){
	if (val != null && val.isbn != null) {
		var status = "Unavailable";
        var notvailable = "disabled";
        if(val.totalBook>val.numberBooksBorrowed){
           	status = "Available";
           	notvailable = "";
        }
        $("#book-management").append('<tr>\
										<td class="textPosition">'+val.isbn+'</td>\
										<td class="bookmanagement-img"><img src="'+val.book.image+'"></td>\
										<td class="textPosition">'+val.book.titleOfBook+'</td>\										<td class="textPosition">'+val.book.author+'</td>\
										<td class="textPosition">'+val.book.publishYear+'</td>\
										<td><div class="tdScroll">'+val.book.shortDescription+'</div></td>\
										<td class="textPosition">'+val.totalBook+'</td>\
										<td class="textPosition" style="width:11%;">\
											<button type="button" class="btn btn-custom btn-sm btn-edit" data-toggle="modal" data-target="#editModal" isbn='+val.isbn+'>\
												<span class="glyphicon glyphicon-edit"></span> Edit\
											</button>\
											<button type="button" class="btn btn-custom btn-sm btn-delete-book" isbn="'+val.isbn+'">\
												<span class="glyphicon glyphicon-trash"></span>\
											</button>\
										</td>\
									</tr>');
	}
}

function myaddbook(url) {
		var libIsbn = {
			"isbn" : $('#inputISBN').val(),
			"totalBook" : $('#inputTotal').val(),
			"author" : $('#inputAuthor').val(),
			"publishYear" : $('#inputPublishYear').val(),
			"image" : $('.inputImage').attr("src"),
			"shortDescription" : $('#inputShortDescription').val(),
			"titleOfBook" : $('#inputTitle').val()
		}
		$.ajax({
			type : "POST",
			contentType : 'application/json; charset=utf-8',
			dataType : 'json',
			url : url,
			data : JSON.stringify(libIsbn), // Note it is important
			success : function(libIsbn) {
				$("#books").attr('num',data);
            	$("#books").trigger('click');
				alert(libIsbn.totalBook)
			}
		});
	}
