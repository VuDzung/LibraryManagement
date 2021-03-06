$( document ).ready(function() {
    //PAGINATE--------------------------------------------------------------------------------------------------------------
	var textsearch = $('.galary').attr("textsearch");
	$.ajax({
		type : "POST",
		url : '/search/resultlistbook/' + textsearch,
		success : function(data) {
			$(".galary").empty();
			$.each(data, function(key, val){
            	setdatawhensearchbook(val);               
            });
		},
		error : function(e) {
			console.log("ERROR : ", e);
		}
	});
	//SHOW BOOK'S INFO IN POP UP--------------------------------------------------------------------------------------------
        $("body").on("click", ".btn-info", function() {
            var info_url = "./home/book/"+$(this).attr("isbn");
            $.ajax({
                url : info_url,
                success : function(data) {
                	setdatatomodal(data);
                }
            });
        });
        
        //SEARCH BOOK-----------------------------------------------------------------------------------------------------------
        $('#w-input-search').autocomplete({
    		autoSelectFirst: true,
    		serviceUrl: '/search/book',
    		paramName: "titlebook",
    		delimiter: ",",
    		onSelect: function(suggestion) {
    			checkvalidateisbn(suggestion.data);
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
        
        //BORROW BOOK-----------------------------------------------------------------------------------------------------------
        $("body").on("click", ".btn-borrow", function() {
        	var isbn = $(this).attr("isbn");
        	$.ajax({
    			type : "GET",
    			url : '/home/borrow/' + isbn,
    			success : function(data) {
    					//alert(data);
    					if(data=='Borrow Successful!'){
    						swal("Borrow done!", "Please return the book early!", "success");
    					}if(data=='Number Of Borowed Books Reached The Limit!'){
    						swal("Error!", "Number Of Borowed Books Reached The Limit!", "error");
    					}if(data=='ISBN Code Is Not Correct!'){
    						swal("Error!", "ISBN Code Is Not Correct!", "error");
    					}if(data=='Book Is Not Available!'){
    						swal("Error!", "Book Is Not Available!", "error");
    					}
    			},
    			error : function(e) {
    				console.log("ERROR : ", e);
    			}
    		});
        });
        
        //SHOW BORROWED BOOK
        $('body').off('click', '#menu-borrowed').on('click', '#menu-borrowed', function(){
        	$.ajax({
        		type : "GET",
        		url : "/home/borrowed-books",
        		success : function(data){
        			$('#ticket-borrowed-book').empty();
        			$.each(data , function(key, val){
        				$('#ticket-borrowed-book').append('<tr>\
																<td>'+val.isbnBean.isbn+'</td>\
																<td>'+val.isbnBean.book.titleOfBook+'</td>\
																<td>'+val.isbnBean.book.author+'</td>\
																<td>'+val.isbnBean.book.publishYear+'</td>\
																<td>'+val.dateBorrow+'</td>\
															</tr>');       													
        			});
        		}
        	});
        });
        
        //SCROLL TO CONTACT DIV
        $("body").on('click', '#menu-contact', function() {
            $('html, body').animate({
                scrollTop: $(".contact").offset().top
            }, 1000);
        });
        
        //CHECK ISBN------------------------------------------------------------------------------------------------------------
    	function checkvalidateisbn(isbn) {
    		$.ajax({
    			type : "GET",
    			url : '/bookmanagement/checkisbn/' + isbn,
    			success : function(data) {
    				setdatatomodal(data);
    				$("#mytest").click();
    			},
    			error : function(e) {
    				console.log("ERROR : ", e);
    			}
    		});
    	}
    	
    	//FUNCTION TO SHOW BOOK'S INFO------------------------------------------------------------------------------------------ 
    	function setdatatomodal(data){
    		if (data != null && data.isbn != null) {
				if(data.totalBook<=data.numberBooksBorrowed){
					$("button#addBook").attr('disabled','');
				}else{
					$("button#addBook").removeAttr('disabled');
				}
			    $("#book-image").attr("src",data.book.image);
			    $("#book-isbn").text(data.isbn);
			    $("#book-title").text(data.book.titleOfBook);
			    $("#book-author").text(data.book.author);
			    $("#book-publish-year").text(data.book.publishYear);
			    $("#book-description").text(data.book.shortDescription);
			    $("#addBook").attr('isbn',data.isbn);
			}
    	}
    	
    	//FUNCTION TO SHOW BOOK lIST-------------------------------------------------------------------------------------------- 
    	function setdatawhensearchbook(val){
    		var status = "Unavailable";
        	var notvailable = "disabled";
        	if(val.totalBook>val.numberBooksBorrowed){
        		status = "Available";
        		notvailable = "";
        	}
            $(".galary").append("<div class='well well-sm itemBook'>\
                                    <div class='allcontain'>\
                                        <div class='product-f-image'>\
                                            <div class='txthover'>\
                                                <img class='setImgListBook' src='"+val.book.image+"' alt='car2'>\
                                                    <div class='txtcontent'>\
                                                        <div class='stars'>\
                                                            <div class='glyphicon glyphicon-star'></div>\
                                                            <div class='glyphicon glyphicon-star'></div>\
                                                            <div class='glyphicon glyphicon-star'></div>\
                                                        </div>\
                                                        <div class='simpletxt'>\
                                                            <p class='name'>Title: "+val.book.titleOfBook+"</p>\
                                                            <p>ISBN: "+val.isbn+" </p>\
                                                            <p>Author: "+val.book.author+"</p>\
                                                            <p class='price'>Status: "+status+" </p>\
                                                            <p>Year publish: "+val.book.publishYear+" </p>\
                                                            <button class='btn btn-info' data-toggle='modal' data-target='#addModal' isbn='"+val.isbn+"'> Read more </button>\
                                                            <button class='btn btn-success btn-borrow' isbn='"+val.isbn+"' "+notvailable+">BORROW</button>\
                                                        </div>\
                                                        <div class='stars2'>\
                                                            <div class='glyphicon glyphicon-star'></div>\
                                                            <div class='glyphicon glyphicon-star'></div>\
                                                            <div class='glyphicon glyphicon-star'></div>\
                                                        </div>\
                                                    </div>\
                                            </div>\
                                        </div>\
                                    </div>\
                                </div> ");  
    	}
    	
    	
//    	function loadbeforeloadpage(){
//    		alert("key press");
//    		var titlebook = $('#w-input-search').val();
//    		$.ajax({
//    			type : "POST",
//    			url : '/search/resultlistbook/' + titlebook,
//    			success : function(data) {
//    				$(".galary").empty();
//    				setdatawhensearchbook(data);
//    			},
//    			error : function(e) {
//    				console.log("ERROR : ", e);
//    			}
//    		});
//    	}
    
});
