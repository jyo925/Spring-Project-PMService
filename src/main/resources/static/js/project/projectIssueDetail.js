$(function() {
	taskList();
	issueUpdate();
	issueFileDelete();
	issueDelete();

	taskListAjax($('.project-id'));
})

function taskList() {
	$('.project-id').on('change', function() {
		taskListAjax($(this))
	})
}

function taskListAjax(target) {
	var projectId = target.val();
	$.ajax({
		url : '/issue/taskList/' + projectId,
		type : 'GET',
		dataType : 'json'
	}).done(function(list) {
		var taskList = $('.issue-task-list');
		taskList.empty();
		$.each(list, function(i, group) {
			var str = '';
			/*
			 * console.log(group.taskGroupName) str = '<option
			 * value="'+ group.taskGroupCode +'">' + group.taskGroupName + '</option>';
			 * taskList.append(str);
			 */
			$.each(group.projectTasks, function(j, task) {
				var taskName = '<option value="' + task.taskCode
						+ '" >' + task.taskName + '</option>';
				taskList.append(taskName);
			})

		})
		
		var taskVal = $('#issue-task-list option');
		$.each(taskVal, function(i, value) {
			if (taskVal[i].value == $('#issue-task').val()) {
				 $('#issue-task-list option:eq('+i+')').prop("selected", true)
				//taskVal[i].prop("selected", true);
			}

		})

	}).fail(function() {
		alert('get taskList fail')
	})
}

function issueUpdate() {
	$('#issue-update-btn').on('click', function() {

		var formData = new FormData();
		var issueData = new Object();
		var issueFile = $('#issue-file')[0].files[0];
		var issueFileName = '';
		if($('#issue-file')[0].files[0] != null){
			issueFileName = issueFile.name;
		} else {
			issueFileName = $('#issue-file-item').text();
		}
	
		issueData.issueCode =  $('#issue-name').data('code');
		issueData.issueName = $('#issue-name').val();
		issueData.issueDescription = $('#issue-description').val();
		issueData.issueUserId = $('#issue-name').data('user');
		issueData.issueDate = $('#issue-name').data('date');
		issueData.issueActionId = $('#issue-action-user').val();
		issueData.issueActionDate = $('#issue-action-date').val();
		issueData.issueTypeCode = $('#issue-type').val();
		issueData.taskCode = $('#issue-task-list').val();
		issueData.issueFileName = issueFileName;
		
		formData.append("issueFile", issueFile);
		formData.append("issueCode", $('#issue-name').data('code'));
		formData.append("issueFilePath", $('#issue-file-item').data('path'));
		
		$.ajax({
			url : '/issue/update',
			type : 'PUT',
			data : issueData
		}).done(function(){

		}).fail(function(){
			alert('issue update fail')
		})
		
		$.ajax({
			url : '/issue/update/file',
			type : 'POST',
			data : formData,
			enctype : 'multipart/form-data',
			contentType : false,
			processData : false,
			cache : false
		}).done(function() {
			location.reload();
		}).fail(function() {
			alert('issue file update fail')
		})

	})
}

function issueFileDelete(){
	$('#issue-file-delete').on('click', function(e){
		if(confirm('삭제하시겠습니까?')){
			$.ajax({
				url: '/issue/delete/file/'+$('#issue-name').data('code'),
				type: 'DELETE',
				data: {issuePath: $('#issue-file-item').data('path')}
			}).done(function(){
				location.reload();
			}).fail(function(){
				alert('issue file delete fail')
			})			
		}
		
		
		
	})
}

function issueDelete(){
	$('#issue-delete-btn').on('click', function(){
		if(confirm('삭제하시겠습니까?')){
			location.href='/removeProjectIssue/'+$('#issue-name').data('code');			
		}
		/*$.ajax({
			url: '/issue/delete/'+$('#issue-name').data('code'),
			type: 'DELETE'
		}).done(function(){
			
		}).fail(function(){
			alert('issue delete fail')
		})*/
	})
}