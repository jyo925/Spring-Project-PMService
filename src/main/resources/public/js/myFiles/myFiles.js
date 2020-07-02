$(document).ready(function(){
	
	$('#addButton').on('click', function(){
		$('#file-dialog').dialog('open');
	});
	
	
	$('.edit-button').on('click', function(){
		let fileId = $(this).parent().parent().prev().text();
		let fileName = $(this).parent().parent().parent().find('th:eq(1)').text();
		let type = $(this).parent().parent().parent().find('th:eq(3)').text();
		let task = $(this).parent().parent().parent().find('th:eq(4)').text();
		
		let typeSelector = $('option', '#type');
		for (let i = 0; i < typeSelector.length; i++) {
			if (typeSelector[i].text == type)
				typeSelector[i].selected = true;
		};
		
		let taskSelector = $('option', '#task');
		for (let i = 0; i < taskSelector.length; i++) {
			if (taskSelector[i].text == task)
				taskSelector[i].selected = true;
		};
		
		$('#file-id').text(fileId);
		$('#outputCode').val(fileId);
		$('#file-name').text(fileName);
		$('#fileEdit-dialog').dialog('open');
	});
	

	$('.showFile').on('click', function load() {
		
		let filePath = $(this).prev().text();
		$('#content').load(filePath);
		
	});
	
	$('#file-dialog').dialog({
		autoOpen : false,
		height : 400,
		width : 350,
		modal : true,
		show : {
			effect : 'drop',
			duration : 500
		},
		hide : {
			effect : 'drop',
			duration : 500
		}
	});
	
	
	$('#fileEdit-dialog').dialog({
		autoOpen : false,
		height : 400,
		width : 350,
		modal : true,
		show : {
			effect : 'drop',
			duration : 500
		},
		hide : {
			effect : 'drop',
			duration : 500
		}
	});
	
});

