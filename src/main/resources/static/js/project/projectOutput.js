$(function(){
	outputModal('#output-add', '.output-cancel-btn');
	outputModal('.output-name', '.output-cancel-btn');
	outputTaskList();
	outputInsert();
	outputDelete();
	outputUpdate();
	outputDownload();
	outputCategorySearch();

	taskListAjax($('.output-input').find('.output-project'));
	
	$('.output-name').on('click', function(e){
		var outputId = $(e.target).data('id');
		taskListAjax($('#'+outputId+'').find('.output-project'));
	})
})

function outputCategorySearch(){
	$('.output-category').on('change', function(){
		$.ajax({
			url : '/output/search/category/' + $('#project-code').val() + "/" + $(this).val(),
			type : 'GET',
			dataType : 'JSON'
		}).done(function(list){
			console.log(list);
			var tbody = $('.output-tbody');
			tbody.empty();
			
			$.each(list, function(index, value){
				var item = '<tr class="output-tbody-tr">' + 
				'<td>' + (index+1) + '</td>' +
				'<td>' + '<a class="output-name" data-path="' + value.outputPath + '" data-taskCode="' + value.taskCode + '" data-mfp-src="#' + value.outputCode + '" data-id="' + value.outputCode + '">' + value.outputName + '</a>' + '</td>' +
				'<td>' + value.outputTypeName + '</td>' +
				'<td>' + value.taskName + '</td>' +
				'<td>' + value.outputUser + '</td>' +
				'<td>' + value.outputDate + '</td>' +
				'<td><a class="output-download-btn"><i class="fa fa-download"></i></a></td>' +
				'<td><a class="output-delete-btn"><i class="fa fa-trash"></i></a></td>'
				+ '</tr>';
				
				tbody.append(item);
			})
			outputModal('.output-name', '.output-cancel-btn');
			outputDownload();
		}).fail(function(){
			alert('output search fail');
		})
	})
	
}

function outputDownload(){
	$('.output-download-btn').on('click', function(e){
		var liObj = $(e.target)
		var path = encodeURIComponent(liObj.closest('.output-tbody-tr').find('.output-name').data('path'));
		self.location = '/output/download?fileName=' + path
	})
}

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
		
		console.log(outputFilePath);
		
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
		if(confirm('삭제하시겠습니까?')){
			var outputCode = $(e.target).closest('.output-tbody-tr').find('.output-name').data('id');
			$.ajax({
				url: '/output/delete/' + outputCode,
				type: 'DELETE'
			}).done(function(){
				location.reload();
			}).fail(function(){
				alert('output delete fail')
			})
			
		}
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
				outputUser: $('#user-id').val(),
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