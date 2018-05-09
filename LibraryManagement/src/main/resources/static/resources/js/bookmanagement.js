$(function () {
	$('#addBookForm').validate({
		rules : {
			inputISBN : {
				required : true,
				maxlength : 13
			},
			inputTotal : {
				required : true,
				digits : true
			}
		},
		messages : {
			inputISBN : {
				required : 'ISBN is required',
				maxlength : 'ISBN must be less than 12 characters'
			},
			inputTotal : {
				required : 'Total is required',
				digits : 'Total must be number'
			}
		}
	});

	// $('#addBook').click(function() {
	//	
	// }

	$('#close').click(function() {
		$('#inputISBN').val('');
		$('#inputTitle').val('')
		$('#inputAuthor').val('')
		$('#inputShortDescription').val('')
		$('#inputPublishYear').val('')
		$('#inputImage').attr("src", "")
		$('#inputTotal').val('')
	});

	$('#inputISBN')
			.keyup(
					function() {
						var search = document.getElementById('inputISBN').value

						if ($('#inputISBN').val().length == 10
								|| $('#inputISBN').val().length == 13) {
							// checkvalidateisbn();
							$
									.ajax({
										url : 'https://www.googleapis.com/books/v1/volumes?q=isbn:'
												+ search,
										dataType : 'json',

										success : function(data) {
											if (data.totalItems == 0) {
												$('#inputTitle').val('')
												$('#inputAuthor').val('')
												$('#inputShortDescription').val('')
												$('#inputPublishYear').val('')
												$('#inputImage').attr("src", "")
												$('#inputTotal').val('')

											} else {

												$('#inputTitle')
														.val(
																data.items[0].volumeInfo.title)
												$('#inputAuthor')
														.val(
																data.items[0].volumeInfo.authors)
												$('#inputShortDescription')
														.val(
																data.items[0].volumeInfo.description)
												$('#inputPublishYear')
														.val(
																data.items[0].volumeInfo.publishedDate.substr(0,4))
												$('#inputImage')
														.attr(
																"src",
																data.items[0].volumeInfo.imageLinks.thumbnail)
											}

										},
										type : 'GET'
									});
						} else {
							$('#inputTitle').val('')
							$('#inputAuthor').val('')
							$('#inputShortDescription').val('')
							$('#inputPublishYear').val('')
							$('#inputImage').attr("src", "")
							$('#inputTotal').val('')
						}
	});


	// paginate-----------------------
	var total = $("#books").attr("num");
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
    $("body").on("click", ".btn-edit", function() {
         var info_url = "./home/book/"+$(this).attr("isbn");
         $.ajax({
            url : info_url,
            success : function(data) {
                $("#book-image").attr("src",data.book.image);
                $("#book-isbn").val(data.isbn);
                $("#book-title").val(data.book.titleOfBook);
                $("#book-author").val(data.book.author);
                $("#book-publish-year").val(data.book.publishYear);
                $("#book-description").val(data.book.shortDescription);
                $("#book-total").val(data.totalBook);
            }
         });
     });
    
    
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
		      return { value: item.book.titleOfBook + '( Author: ' + item.book.author + ' ) ', data: item.isbn };
		   })
		            
		 };
        }
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
											<td class="textPosition">'+val.book.titleOfBook+'</td>\
											<td class="textPosition">'+val.book.author+'</td>\
											<td class="textPosition">'+val.book.publishYear+'</td>\
											<td><div class="tdScroll">'+val.book.shortDescription+'</div></td>\
											<td class="textPosition">'+val.totalBook+'</td>\
											<td class="textPosition" style="width:11%;">\
												<button type="button" class="btn btn-custom btn-sm btn-edit" data-toggle="modal" data-target="#editModal" isbn='+val.isbn+'>\
													<span class="glyphicon glyphicon-edit"></span> Edit\
												</button>\
												<button type="button" class="btn btn-custom btn-sm" isbn="'+val.isbn+'">\
													<span class="glyphicon glyphicon-trash"></span>\
												</button>\
											</td>\
										</tr>');
		}
	}
    
});
function myaddbook() {
		var libIsbn = {
			"isbn" : $('#inputISBN').val(),
			"totalBook" : $('#inputTotal').val(),
			"author" : $('#inputAuthor').val(),
			"publishYear" : $('#inputPublishYear').val(),
			"image" : $('#inputImage').attr("src"),
			"shortDescription" : $('#inputShortDescription').val(),
			"titleOfBook" : $('#inputTitle').val()
		}
		$.ajax({
			type : "POST",
			contentType : 'application/json; charset=utf-8',
			dataType : 'json',
			url : '/bookmanagement/savebook',
			data : JSON.stringify(libIsbn), // Note it is important
			success : function(libIsbn) {
				alert(libIsbn.totalBook)
			}
		});
	}
