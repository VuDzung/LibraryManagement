$(document).ready(function(){
	$('#registerForm').validate({
		rules:{
			username:{
				required: true,
				maxlength: 16
			},
			password:{
				required: true,
				maxlength: 16
			},
			confirmPass:{
				required: true,
				equalTo: '#password'
			},
			name:{
				required: true,
				maxlength: 30
			}
		},
		messages:{
			username:{
				required: "Username is required",
				maxlength: "Username must be less than 16 characters"
			},
			password:{
				required: "Password is required",
				maxlength: "Password must be less than 16 characters"
			},
			confirmPass:{
				required: "Confirm password is required",
				equalTo: "Confirm password did not match"
			},
			name:{
				required: "Name is required",
				maxlength: "Name must be less than 30 characters"
			}
		}
	})
})