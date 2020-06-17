$(function(){
	insertTask();
})

function insertTask(){
	$('.task-add-modal').on('click', function(event){
		$groupId = $(event.target).closest('.taskGroup-div').find('.taskGroup').data('id')
		$('.task-add-btn').on('click', function(event){
			console.log($groupId);
		})
	})
	
	taskAddModal('.task-add-modal','#task-cancel-btn');
}

function taskAddModal(openTarget, closeTarget){
	$(openTarget).magnificPopup({
		type: 'inline',
		midClick: true
	})
	
	$(closeTarget).on('click', function(){
		$.magnificPopup.instance.close();
	})
}