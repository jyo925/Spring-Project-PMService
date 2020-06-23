$(function(){
	outputModal('#output-add', '.output-cancel-btn');
	outputModal('.output-name', '.output-cancel-btn');
	outputTaskList();
	outputInsert();
	outputDelete();
	outputUpdate();

	taskListAjax($('.output-input').find('.output-project'));
	
	$('.output-name').on('click', function(e){
		var outputId = $(e.target).data('id');
		taskListAjax($('#'+outputId+'').find('.output-project'));
	})
})

function outputUpdate(){
	$('.output-update-btn').on('click', function(e){
		var item = $(e.target).closest('.output-info');
		console.log(item.attr('id'))
		
		var outputFile = item.find('.output-file')[0].files[0];		
		var outputFileName = '';
		var outputFilePath = '';
		if(outputFile != null){
			outputFileName = outputFile.name;
			outputFilePath = '1';
		}else if(outputFile == null){
			outputFileName = item.find('.output-file-name').text();
			outputFilePath = item.find('.output-task-code').data('path');
		}
		
		console.log()
		
		$.ajax({
			url: '/output/update',
			type: 'PUT',
			data: {
				outputCode : item.attr('id'),
				outputName : outputFileName,
				outputPath : outputFilePath,
				outputUser : $('.name').text(),
				outputTypeCode : item.find('.output-type').val(),
				taskCode : item.find('.output-task-list').val()
			}
		}).done(function(){
			location.reload();
		}).fail(function(){
			alert('output update fail')
		})
	})
}

function outputDelete(){
	$('.output-delete-btn').on('click', function(e){
		var outputCode = $(e.target).closest('.output-tbody-tr').find('.output-name').data('id');
		$.ajax({
			url: '/output/delete/' + outputCode,
			type: 'DELETE'
		}).done(function(){
			location.reload();
		}).fail(function(){
			alert('output delete fail')
		})
	})
}

function outputInsert(){
	$('.output-save-btn').on('click', function(){
		var outputFile = $('#output-file')[0].files[0];
		var outputFileName = '';
		if(outputFile != null) outputFileName = outputFile.name;
		
		$.ajax({
			url: '/output/add',
			type: 'POST',
			data: {
				outputName: outputFileName,
				outputUser: $('.name').text(),
				outputTypeCode: $('#output-type').val(),
				taskCode: $('#output-task-list').val()
			}
		}).done(function(){
			//location.reload();
		}).fail(function(){
			alert('output insert fail')
		})
		
		var formData = new FormData();
		formData.append("outputFile", outputFile);
		
		$.ajax({
			url: '/output/file/add',
			type: 'POST',
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

function outputTaskList(){
	$('.output-project').on('change', function(e){
		taskListAjax($(e.target));
	})
	
}

function taskListAjax(target) {
	var projectId = target.val();
	$.ajax({
		url : '/issue/taskList/' + projectId,
		type : 'GET',
		dataType : 'json'
	}).done(function(list) {
		var taskList = $('.output-task-list');
		taskList.empty();
		
		var taskName = '<option value=""> 작업을 선택해주세요 </option>';
		taskList.append(taskName);
		
		$.each(list, function(i, group) {
			var str = '';
			/*
			 * console.log(group.taskGroupName) str = '<option
			 * value="'+ group.taskGroupCode +'">' + group.taskGroupName + '</option>';
			 * taskList.append(str);
			 */
			$.each(group.projectTasks, function(j, task) {
				
				taskName = '<option value="' + task.taskCode
						+ '" >' + task.taskName + '</option>';
				taskList.append(taskName);
			})
			
			

		})
		
		var taskVal = target.closest('.output-info').find('.output-task-list option');
		$.each(taskVal, function(i, value) {
			if (taskVal[i].value == target.closest('.output-info').find('.output-task-code').val()) {
				 $('.output-task-list option:eq('+i+')').prop("selected", true)
			}

		})


	}).fail(function() {
		alert('get taskList fail')
	})
}

function outputModal(openTarget, closeTarget){
	$(openTarget).magnificPopup({
		type: 'inline',
		midClick: true
	})
	
	$(closeTarget).on('click', function(){
		$.magnificPopup.instance.close();
		
	})
}