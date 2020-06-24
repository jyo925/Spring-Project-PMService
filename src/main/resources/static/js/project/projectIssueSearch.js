$(function() {
	issueSearch();
	issueSearchByType();
})

function issueSearch() {
	$('#issue-search-btn').on('click', function() {
		search();
	})
}

function issueSearchByType() {
	$('#issueTypeChoice').on('change', function() {
		search();
	})
}

function search(){
	$.ajax({
		url: '/issue/search',
		type: 'GET',
		data: {
			issueType : $('#issueTypeChoice').val(),
			issueName : $('#issueName').val(),
			projectCode : $('.project-code').val()
		},
		dataType: 'JSON'
	}).done(function(list){
		var tbody = $('#issue-list-tbody');
		tbody.empty();
		
		$.each(list, function(index, value){
			var item = '<tr>' +
			'<td>' + (index+1) + '</td>' +
			'<td><a href="/projectIssueDetail/' + value.issueCode + '">' + value.issueName + '</a></td>' + 
			'<td>' + value.issueTypeName + '</td>' + 
			'<td>' + value.issueUserId + '</td>' + 
			'<td>' + value.issueActionId + '</td>' + 
			'<td>' + value.issueActionDate + '</td>' +
			'<td>' + value.issueDate + '</td>' +
			'</tr>';
			
			tbody.append(item);
		})
	}).fail(function(){
		alert('issue search fail')
	})
}