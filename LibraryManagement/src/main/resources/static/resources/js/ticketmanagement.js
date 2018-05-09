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
                    	var status="";
                        $.each(data, function(key, val){
                        	if(val.borrowedNumber>0) status="";
                        	else status="disabled";
                            $("#tickets-management").append('<tr>\
                                                                <td>'+val.idUser+'</td>\
                                                                <td>'+val.userName+'</td>\
                                                                <td>'+val.fullName+'</td>\
                                                                <td>'+val.role.nameRole+'</td>\
                                                                <td>'+val.limitNumber+'</td>\
                                                                <td>\
                                                                    <button type="button" class="btn btn-default btn-sm btn-borrowed"\
                                                                        data-toggle="modal" data-target="#modalview" user="'+val.idUser+'" '+status+'>'+val.borrowedNumber+'\
                                                                    </button>\
                                                                </td>\
                                                                <td>\
                                                                    <button type="button" class="btn btn-default btn-sm btn-ticket-edit"\
                                                                        data-toggle="modal" data-target="#add" user="'+val.idUser+'">\
                                                                        <span class="glyphicon glyphicon-edit"></span> edit\
                                                                    </button>\
                                                                </td>\
                                                            </tr>');                                                                      
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
                                                                <td>'+val.isbnBean.isbn+'</td>\
                                                                <td>'+val.isbnBean.book.titleOfBook+'</td>\
                                                                <td>'+val.isbnBean.book.author+'</td>\
                                                                <td>'+val.isbnBean.book.publishYear+'</td>\
                                                                <td>'+val.dateBorrow+'</td>\
                                                                <td>\
                                                                    <button type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#editModal" user="'+val.user.idUser+'" isbn="'+val.isbnBean.isbn+'">\
                                                                        <span class="glyphicon glyphicon-erase"></span> Returned\
                                                                    </button>\
                                                                </td>\
                                                            </tr>');
                    })
                }
            });
        });
    });