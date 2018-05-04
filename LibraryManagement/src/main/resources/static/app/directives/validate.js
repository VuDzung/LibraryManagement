$(document).ready(function(){
  $("form[name='registration']").validate({
    // Specify validation rules
    rules: {
      // The key name on the left side is the name attribute
      // of an input field. Validation rules are defined
      // on the right side
      username: "required",
      password: {
        required: true,
        minlength: 6
      }
    },
    // Specify validation error messages
    messages: {
      username: "Please enter your username",
      password: {
        required: "Please enter a password",
        minlength: "Your password must be at least 6 characters long"
      },
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
      submitHandler: function(form) {
        form.submit();
      }
  });
});