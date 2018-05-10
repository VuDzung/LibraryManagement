$(function () {
        var total = $(".galary").attr("num");
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: total,
            visiblePages: 10,
            onPageClick: function (evt, page) {
                $(".galary").empty();
                var url = "./home/page/"+page;
                $.ajax({
                    url : url,
                    success : function(data) {
                        $.each(data, function(key, val){
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
                                                
                        });
                    }
                });
                $("html, body").animate({
    	            scrollTop: $('.galary').offset().top 
    	        });
            }
        });
	    $("html, body").animate({
	        scrollTop: $('body').offset().top 
	    });
        $("body").on("click", ".btn-info", function() {
            var info_url = "./home/book/"+$(this).attr("isbn");
            $.ajax({
                url : info_url,
                success : function(data) {
                	setdatatomodal(data);
                }
            });
        });
        
        
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
    		      return { value: item.book.titleOfBook + '( Author: ' + item.book.author + ' ) ', data: item.isbn };
    		   })
    		            
    		 };
            }
    	 });
        
        //borrow
        $("body").on("click", ".btn-borrow", function() {
        	var isbn = $(this).attr("isbn");
        	$.ajax({
    			type : "GET",
    			dataType : 'json',
    			contentType : "application/json",
    			url : '/home/borrow/' + isbn,
    			success : function(data) {
    				alert: data.status;
    			},
    			error : function(e) {
    				console.log("ERROR : ", e);
    			}
    		});
        });
        
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
    });

