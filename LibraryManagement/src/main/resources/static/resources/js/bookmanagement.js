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
})

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
})

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

				})

// function checkvalidateisbn() {
// var isbn = document.getElementById('inputISBN').value;
// $.ajax({
// type : "GET",
// dataType : 'json',
// contentType : "application/json",
// url : '/bookmanagement/checkisbn/' + isbn,
// success : function(data) {
// if (data != null && data.isbn != null) {
// alert("Error");
// }
// // $("#myresul2t").text(data.nameRole);
// // window.location.href = "home";
// },
// error : function(e) {
// console.log("ERROR : ", e);
// }
// });
//
// }

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
