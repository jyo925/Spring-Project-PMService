$(function(){
	$('#update_btn').on('click',function(){
		$.ajax({
			url : '/project/'+$('#projectCode').val(),
			type : 'PUT',
			data : {
				project
			}
		})
	})
})