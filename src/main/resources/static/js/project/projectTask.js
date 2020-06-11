$(function(){
	updateGroupName();
	groupAddModal();
})

function updateGroupName(){
	$('#groupName_btn').on('click',function(){
		var group = $('#taskGroup');
		var groupName = '<input type="text" value="'+ group.data('text') + '" data-id="'+group.data('id')+'" id="groupName" style="margin-bottom: 5px;">';
		group.html(groupName);
		
		$('#groupName').keydown(function(key){
			if(key.keyCode == 13){
				$.ajax({
					url : '/task/groupName/update',
					type : 'PUT',
					data : {
						taskGroupCode : $(this).data('id'),
						taskGroupName : $(this).val()
					}
				}).done(function(){
					location.reload();
				}).fail(function(){
					alert('groupName update fail')
				})
			}
			
		})
	})
}

function groupAddModal(){
	$('.groupAdd_btn').magnificPopup({
		type: 'inline',
		midClick: true
	});
}