$(document).ready(function(){
	
	$('#addButton').on('click', function(){
		$('#file-dialog').dialog('open');
	});
	
	
	$('.edit-button').on('click', function(){
		let fileId = $(this).parent().prev().text();
		let fileName = $(this).parent().parent().find('th:eq(0)').text();
		$('#file-id').text(fileId);
		$('#outputCode').val(fileId);
		$('#file-name').text(fileName);
		console.log(fileId);
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

