$(function(){
	taskList();
// issueFile();
})

function taskList(){
	$('.project-list').on('change', function(){
		taskListAjax($(this))
	})
}

function taskListAjax(target){
	var projectId = target.val();
	$.ajax({
		url: '/issue/taskList/'+projectId,
		type: 'GET',
		dataType: 'json'
	}).done(function(list){
		var taskList = $('.issue-task-list');
		taskList.empty();
		$.each(list, function(i, group){
			var str = '';
			/*
			 * console.log(group.taskGroupName) str = '<option value="'+
			 * group.taskGroupCode +'">' + group.taskGroupName + '</option>';
			 * taskList.append(str);
			 */
			$.each(group.projectTasks, function(j, task){
				var taskName = '<option class="issue-task-option" value="' + task.taskCode + '" >' + task.taskName + '</option>';
				taskList.append(taskName);
				
			})
				
		})
		
		/*if(taskList.find('.issue-task-option').val() == $('#issue-task').val()){
			taskList.find('issue-task-option').attr("selected", "true")
		}*/
		
	}).fail(function(){
		alert('get taskList fail')
	})
}

/*
 * function issueFile(){ $('#issue-add-btn').on('click', function(){ var file =
 * $(this)[0].files[0]; var formData = new FormData($('#issue-file-form'))
 * 
 * $.ajax({ url: '/issue/upload', type: 'POST', enctype: 'multipart/form-data',
 * data: formData, contentType: false, processData: false, cache: false })
 *  }) }
 */