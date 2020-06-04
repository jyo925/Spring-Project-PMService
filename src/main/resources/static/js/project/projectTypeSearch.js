$(function(){
	elementEvent();
})

/*function typeSearch(){
	$('#projectTypeChoice').on('change', function(){
		location.href='/projectList/'+$(this).val()
	})
	
} // type search end*/


// typeSearch() START
function elementEvent(){
	$('#projectTypeChoice').on('change', function(){
		search()
	})
	
	$('#search_btn').on('click', function(){
		search()
	})
} 
// typeSearch() END


// search() START
function search(){
	$.ajax({
		url : '/project/search',
		type : 'GET',
		data : {
			typeCode : $('#projectTypeChoice').val(),
			projectName : $('#projectName').val()
		},
		dataType : 'json'
	}).done(function(projectList){
		var projectsBody = $('#projectListBody')
		projectsBody.empty()
		$.each(projectList, function(index, value){
			var project_code = value.projectCode
			var projects = '<tr>' + 
				'<td>' + (index+1) + '</td>' +
				'<td><a href=/project/' + value.projectCode + '>' + value.projectName + '</a></td>' + 
				'<td>' + value.projectTypeName + '</td>' +
				'<td>' + value.projectStart + '</td>' +
				'<td>' + value.projectFinish + '</td>' +
				'<td>' + value.projectOutputAccount + '</td>' +
				'<td>' + value.projectIssueAccount + '</td>' +
				'</tr>';
			
			if(index === 0) projectsBody.html(projects)
			else if(index > 0) projectsBody.append(projects)
		})
	}).fail(function(){
		alert('fail!')
	})
} 
// search() END