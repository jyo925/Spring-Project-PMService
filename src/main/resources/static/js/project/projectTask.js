$(function(){
	insertTask();
	updateTask();
	deleteTask();
	slideAction();
	
	// taskOutput();
	
	/*var taskStatus = $('.task-status-name');
	for(var i = 0 ;i<taskStatus.length;i++){
		console.log(taskStatus[i].textContent);
	}
	console.log(taskStatus);*/
	
})

function slideAction(){
	
	$('.success-task-title').on('click', function(event){
		$(event.target).closest('.success-task-list').find('.success-task').slideToggle("fast");
	})
	
	$('.group-btn-list').on('click', function(event){
		$(event.target).closest('.taskGroup-list').find('.group-btn').slideToggle("fast");
	})
	
	$('.task-btn-list').on('click', function(event){
		$(event.target).closest('.task').find('.task-btn').slideToggle("fast");
	})
	
	$('.success-task-btn-list').on('click', function(event){
		$(event.target).closest('.success-task').find('.success-task-btn').slideToggle("fast");
	})
}


function updateTask(){
	
	/* task status success start */
	$('.task-success-btn').on('click', function(event){
		
		$task_code = $(event.target).closest('.task').find('.task-code').val();
		
		$.ajax({
			url: '/task/status/update',
			type: 'PUT',
			data: {
				taskCode: $task_code
			}
		}).done(function(){
			location.reload();
		}).fail(function(){
			alert('task status update fail')
		})
	})
	/* task status success end */
	
	/* task status active start */
	$('.task-active-btn').on('click', function(event){
		var task_code = $(event.target).closest('.success-task').find('.task-code').val();
		$.ajax({
			url:'/task/status/back',
			type: 'PUT',
			data: {
				taskCode: task_code
			}
		}).done(function(){
			location.reload();
		}).fail(function(){
			alert('task status back fail')
		})
	})
	/* task status active end */
	
	/* task info start */
	$('.task-name').on('click',function(event){
		$taskEvent = $(event.target);
		$task_code = $(event.target).closest('.task').find('.task-code').val();
		
		/* task update start */
		$('.task-update-btn').on('click', function(event){
			
			var parent_element = $(event.target).closest('.white-popup');
			
			/* task content update end */
			$.ajax({
				url: '/task/update',
				type: 'PUT',
				data: {
					taskCode: $task_code,
					taskName: parent_element.find('#task-name').val(),
					taskStart: parent_element.find('#task-start').val(),
					taskFinish: parent_element.find('#task-finish').val(),
					taskDescription: parent_element.find('#task-description').val(),
					taskStatusCode: parent_element.find('.task-status').val()
				}
			}).done(function(){
				location.reload();
			}).fail(function(){
				alert('task update fail')
			})
			/* task content update end */
			
			/* task manager add start*/
			var array = new Array();
			var managerId = parent_element.find('.add-manager-id');
			console.log(managerId);
			for(var i=0 ; i<managerId.length ; i++){
				var data = new Object();
				data.taskCode = $task_code;
				data.taskManagerId = managerId[i].textContent;
				array.push(data)
			}
			console.log(array);
			
			$.ajax({
				url: '/task/manager/add',
				type: 'POST',
				data: JSON.stringify(array),
				contentType: 'application/json'
			}).done(function(){
				location.reload();
			}).fail(function(){
				alert('task manager add fail');
			})
			/* task manager add end */
		})
		/* task update end */
		
		taskManager('.task-manager')	
		taskOutput('.task-file-add-btn', '.task-file-add-cancel-btn', '.task-file', $task_code);
		
		
		
	})
	/* task info end */
	taskModal('.task-name','.task-update-cancel-btn')
	taskModal('.task-info-btn', '.task-update-cancel-btn')
	taskModal('.task-file-btn', '.task-file-cancel-btn')
	
}

function insertTask(){
	$('.task-add-modal').on('click', function(event){
		$groupId = $(event.target).closest('.taskGroup-div').find('.taskGroup').data('id')
		$('.task-add-btn').on('click', function(event){
			
			$.ajax({
				url : '/task/add',
				type : 'POST',
				data : {
					taskName : $('#task-name').val(),
					taskStart : $('#task-start').val(),
					taskFinish : $('#task-finish').val(),
					taskDescription : $('#task-description').val(),
					taskGroupCode : $groupId,
					taskStatusCode : $('.task-status').val()
				}
			}).done(function(){
				location.reload();
			}).fail(function(){
				alert('task add fail')
			})	
			
		})
		
	})
	
	taskModal('.task-add-modal','.task-cancel-btn');
}

function deleteTask(){
	$('.task-delete-btn').on('click', function(event){
		var result = confirm('삭제하시겠습니까?');
		
		if(result) {
			$task_code = $(event.target).closest('.task').find('.task-code').val();
			
			$.ajax({
				url: '/task/remove/' + $task_code,
				type: 'DELETE'
			}).done(function(){
				location.reload();
			}).fail(function(){
				alert('task delete fail')
			})			
		}
		
	})
}

function taskOutput(openTarget, closeTarget, fileTarget, taskCode){
	// console.log(openTarget);
	
	$(closeTarget).on('click', function(event){
		$(event.target).closest('.task-file-add').css("display", "none")
	})
	
	$(fileTarget).on('change', function(event){
		fileUpload($(event.target).closest('.task-file-add').find('.task-file-upload'), taskCode);
	})
	
	$(openTarget).on('click', function(event){
		$(event.target).closest('.task-file-popup').find('.task-file-add').css("display", "block")
	})
	
	taskOutputDelete('.output-delete', taskCode);
	fileDownload('.output-download');
/*	
	$('.output-delete').on('click', function(e){
		var outputName = $(e.target).closest('.task-file-table').find('.output-name').text()
		console.log(outputName)
	})*/
}

function taskOutputDelete(target, taskCode){
	$(target).on('click', function(e){
		var result = confirm('삭제하시겠습니까?');
		if(result){
			var outputId = $(e.target).closest('tr').find('.output-name').data('id');
			var outputName = $(e.target).closest('tr').find('.output-name').text();
			var outputPath = $(e.target).closest('tr').find('.output-name').data('path');
			
			$.ajax({
				url: '/output/remove/' + outputId,
				type: 'DELETE',
				data: {
					outputName: outputName,
					outputPath: outputPath
				}
			}).done(function(){
				$(e.target).closest('tr').remove();
			}).fail(function(){
				alert('output delete fail')
			})			
		}
		
		
	})
}

function fileUpload(target, taskCode){
	
	$(target).on('click', function(e){
		var files = $(e.target).closest('.task-file-add').find('.task-file')[0].files;
		console.log(files);
		
		var formData = new FormData();
		for(var i=0 ; i<files.length ; i++){
			formData.append("task_output", files[i]);
			formData.append("outputTypeCode", $(e.target).closest('.task-file-add').find('.output-category').val());
			formData.append("taskCode", taskCode);
		}
		
		$.ajax({
			url: '/output/upload',
			type: 'POST',
			enctype: 'multipart/form-data',
			data: formData,
			contentType: false,
			processData: false,
			cache: false
		}).done(function(){
			location.reload();
		}).fail(function(){
			alert('output upload fail')
		})
	})
	
}

function fileDownload(target){
	$(target).on('click', function(e){
		
		var liObj = $(e.target)
		var path = encodeURIComponent(liObj.closest('.output-name').data('path'));
		console.log(liObj.text())
		console.log(path);
		self.location = '/output/download?fileName=' + path
		/*var outputName = $(e.target).closest('tr').find('.output-name').text();
		var outputPath = $(e.target).closest('tr').find('.output-name').data('path');
		
		$.ajax({
			url : '/output/download',
			type : 'GET',
			data : {
				outputName : outputName,
				outputPath : outputPath
			}
		}).done(function(){
			
		}).fail(function(){
			alert('output download fail')
		})*/
	})
}

function taskManager(target){
	$(target).on('change', function(event){
		var userId = $(event.target).val()
		var value = '<span class="add-manager-id">' + userId + '</span>';
		$('.task-manager-list').append(value);
	})
	
	
}

function taskModal(openTarget, closeTarget){
	$(openTarget).magnificPopup({
		type: 'inline',
		midClick: true
	})
	
	$(closeTarget).on('click', function(){
		$.magnificPopup.instance.close();
		
	})
}