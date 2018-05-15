$(document).ready(function(){
	$('#addModalForm').validate({
		rules:{
			newUsername:{
				required: true,
				email: true,
				maxlength: 50
			},
			newFullName:{
				required: true,
				maxlength: 30
			},
			newPassword:{
				required: true,
				maxlength: 16
			},
			confirmNewPass:{
				required: true,
				equalTo: '#new-password'
			}			
		},
		messages:{
			newUsername:{
				required: "Username is required",
				email: "UserName must be email",
				maxlength: "Username must be less than 16 characters"
			},
			newFullName:{
				required: "Name is required",
				maxlength: "Name must be less than 30 characters"
			},
			newPassword:{
				required: "Password is required",
				maxlength: "Password must be less than 16 characters"
			},
			confirmNewPass:{
				required: "Confirm password is required",
				equalTo: "Confirm password did not match"
			}
		}		
	})
})