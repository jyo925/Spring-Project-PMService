$(document).ready(function(){
	
	///////////Change password validation
	let alertBox = $('#alert').hide(),
		newPassword = $('#new-password'),
		changePassword = $('#change-password');
	
	$('#pwButton').on('click', function() {
		
		
		if(newPassword.val() == "" || changePassword.val() == ""){
			
			alertBox.text('Please input new password and repeat pasword!');
			alertBox.show();
			
		}else if(newPassword.val() === changePassword.val()){
			
			$('#pwChange').submit();
			
		}else{
			
			alertBox.text('Pasword is not mached, please try again!');
			alertBox.show();
			
		};
		
	});
	
	
	
	///////////Cancel button
	$('#cancelButton').on('click', function(){
		
		newPassword.val("");
		changePassword.val("");
		
	})
	
	
	
	////////////Delete photo function
	let deletePhotoForm = $("#photo");
	$('#deletePhoto').on("click", function(e) {
		e.preventDefault();
		deletePhotoForm.attr("action", "/deleteImg");
		deletePhotoForm.submit();
	});
	
});
