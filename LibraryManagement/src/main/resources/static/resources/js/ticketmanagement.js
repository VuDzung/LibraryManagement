$(function () {
        var total = $("#ticket").attr("num");
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: total,
            visiblePages: 10,
            onPageClick: function (evt, page) {
                $("#tickets-management").empty();
                var url = "./admin/tickets/"+page;
                $.ajax({
                    url : url,
                    success : function(data) {
                        $.each(data, function(key, val){
                        	setdatapopup(val);                                                                     
                        });
                    }
                });
                $("html, body").animate({
                    scrollTop: $('#tickets-management').offset().top 
                });
            }
        });
        $("body").on("click", ".btn-ticket-edit", function() {
            var info_url = "./admin/ticket/"+$(this).attr("user");
            $.ajax({
                url : info_url,
                success : function(data) {
                    $("#ticket-id").val(data.idUser);
                    $("#ticket-mail").val(data.userName);
                    $("#ticket-name").val(data.fullName);
                    $("#ticket-role").val(data.role.nameRole);
                    $("#ticket-limit").val(data.limitNumber);
                    $("#ticket-borrowed").val(data.borrowedNumber);
                    $("#edit-ticket").attr('value',data.idUser);
                }
            });
        });
        $("body").on("click", ".btn-borrowed", function() {
            var info_url = "./admin/borrowed/"+$(this).attr("user");
            $.ajax({
                url : info_url,
                success : function(data) {
                    $("#ticket-borrowed-book").empty();
                    $("#title-and-name").text("Borroweds Books Of: "+data[0].user.fullName)
                    $.each(data, function(key, val){
                        $("#ticket-borrowed-book").append('<tr>\
                                                                <td class="textPosition">'+val.isbnBean.isbn+'</td>\
                                                                <td class="textPosition">'+val.isbnBean.book.titleOfBook+'</td>\
                                                                <td class="textPosition">'+val.isbnBean.book.author+'</td>\
                                                                <td class="textPosition">'+val.isbnBean.book.publishYear+'</td>\
                                                                <td class="textPosition">'+val.dateBorrow+'</td>\
                                                                <td class="textPosition">\
                                                                    <button type="button" class="btn btn-custom btn-sm" data-toggle="modal" data-target="#editModal" user="'+val.user.idUser+'" isbn="'+val.isbnBean.isbn+'">\
                                                                        <span class="glyphicon glyphicon-erase"></span> Returned\
                                                                    </button>\
                                                                </td>\
                                                            </tr>');
                    })
                }
            });
        });
        
        
        $('#w-input-searchuser').autocomplete({
    		autoSelectFirst: true,
    		serviceUrl: '/search/user',
    		paramName: "username",
    		delimiter: ",",
    		onSelect: function(suggestion) {
    			getinforuser(suggestion.data);
    			$(".divNextPage").attr('hidden','');
            },
    	   transformResult: function(response) {
    		    	
    		return {      	
    		  suggestions: $.map($.parseJSON(response), function(item) {
    		      return { value: item.fullName + '( Account: ' + item.userName + ' ) ', data: item.idUser };
    		   })
    		            
    		 };
            }
    	 });
    	function getinforuser(iduser) {
    		$.ajax({
    			type : "GET",
    			dataType : 'json',
    			contentType : "application/json",
    			url : '/search/user/' + iduser,
    			success : function(data) {
    				if (data != null && data.idUser != null) {
    					$("#tickets-management").empty();
    					setdatapopup(data);
    				}
    				
    				 //window.location.href = "home";
    			},
    			error : function(e) {
    				console.log("ERROR : ", e);
    			}
    		});
    	}
    	
    	function setdatapopup(val){
    		var status="";
            	if(val.borrowedNumber>0) status="";
            	else status="disabled";
                $("#tickets-management").append('<tr>\
                                                    <td class="textPosition" style="width:6%;">'+val.idUser+'</td>\
                                                    <td class="textPosition">'+val.userName+'</td>\
                                                    <td class="textPosition">'+val.fullName+'</td>\
                                                    <td class="textPosition">'+val.role.nameRole+'</td>\
                                                    <td class="textPosition" style="width:10%;">'+val.limitNumber+'</td>\
                                                    <td class="textPosition" style="width:13%;">\
                                                        <button type="button" class="btn btn-custom btn-sm btn-borrowed"\
                                                            data-toggle="modal" data-target="#modalview" user="'+val.idUser+'" '+status+'>'+val.borrowedNumber+'\
                                                        </button>\
                                                    </td>\
                                                    <td>\
                                                        <button type="button" class="btn btn-custom btn-sm btn-ticket-edit"\
                                                            data-toggle="modal" data-target="#add" user="'+val.idUser+'">\
                                                            <span class="glyphicon glyphicon-edit"></span> edit\
                                                        </button>\
                                                    </td>\
                                                </tr>');                                                                      
    	}
    });