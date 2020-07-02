$(function(){
	deleteGroup();
	updateGroupName();
	groupAddModal();

})

function deleteGroup(){
	$('.group-remove-btn').on('click', function(event){
		if(confirm('삭제하시겠습니까?')){
			var groupId = $(event.target).closest('.taskGroup-div').find('.taskGroup').data('id');
			// console.log(groupId)
			$.ajax({
				url : '/task/groupRemove/' + groupId,
				type : 'DELETE'
			}).done(function(){
				location.reload();			
			}).fail(function(){
				alert('group delete fail')
			})			
		}
		
		
	})
	
}

function updateGroupName(){
	$('.groupName_btn').click(function(event){
		
		var group = $(event.target).closest('.taskGroup-div').find('.taskGroup');
		var groupName = '<input type="text" value="'+ group.data('text') + '" data-id="'+group.data('id')+'" id="groupName" style="margin-bottom: 5px;">';
		group.html(groupName);
		
		group.keydown(function(key){
			if(key.keyCode == 13){
				$.ajax({
					url : '/task/groupName/update',
					type : 'PUT',
					data : {
						taskGroupCode : $(this).data('id'),
						taskGroupName : $('#groupName').val()
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
	$('.group-add-modal').magnificPopup({
		type: 'inline',
		midClick: true
	});
	
	$('#group-add-btn').on('click', function(){
		console.log($('#project-code').val())
		$.ajax({
			url: '/task/groupAdd',
			type: 'POST',
			data: {
				taskGroupName: $('#group-name').val(),
				taskGroupDescription: $('#group-description').val(),
				projectCode: $('#project-code').val()
			}
		}).done(function(){
			$.magnificPopup.instance.close();
			location.reload();
		}).fail(function(){
			
		})
	})
	
	$('#cancel-btn',).on('click', function(){
		$.magnificPopup.instance.close();
	})
}