$(function () {	
	a = $(".price").each(function(){
		if($(this).text().indexOf("Unavailable")!=-1) $(this).css("background-color","rgba(255,0,0,0.5)");
		else $(this).css("background-color","rgba(0,255,0,0.5)");
	});
        
        //PAGINATE--------------------------------------------------------------------------------------------------------------
		var paginate_time = 0;
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
                        	setdatawhensearchbook(val);               
                        });
                    }
                });
                if(paginate_time==1)
	                $("html, body").animate({
	    	            scrollTop: $('.galary').offset().top 
	    	        });
                else paginate_time=1;
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
    		      return { value: item.book.titleOfBook + '( Author: ' + item.book.author + ' ) ', data: item.isbn };
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
    					alert(data)
    			},
    			error : function(e) {
    				console.log("ERROR : ", e);
    			}
    		});
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
    	
    	$("#searchbutton").click(function(){
    		alert("key press");
    		var titlebook = $('#w-input-search').val();
    		$.ajax({
    			type : "POST",
    			url : '/search/resultlistbook/' + titlebook,
    			success : function(data) {
    				$(".galary").empty();
    				setdatawhensearchbook(data);
    			},
    			error : function(e) {
    				console.log("ERROR : ", e);
    			}
    		});
    	});
//    	$('#w-input-search').bind("enterKey",function(e){
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
//    		});
//    		$('#w-input-search').keyup(function(e){
//    		    if(e.keyCode == 13)
//    		    {
//    		        $(this).trigger("enterKey");
//    		    }
//    		});
    });
