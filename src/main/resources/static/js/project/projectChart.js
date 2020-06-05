$(function(){
	$.ajax({
		url : '/project/detail/taskChart',
		type : 'GET',
		data : {
			projectId : $('#projectCode').val()
		},
		dataType : 'json'
	}).done(function(array){
		google.charts.load('current', {'packages':['corechart']});
		google.charts.setOnLoadCallback(function(){
			drawChart(array);
		});
	}).fail(function(){
		alert('chart fail!!')
	})
	
	
	$.ajax({
		url : '/project/detail/issueChart',
		type : 'GET',
		data : {
			projectId : $('#projectCode').val()
		},
		dataType : 'json'
	}).done(function(array){
		google.charts.load('current', {'packages':['corechart']});
		google.charts.setOnLoadCallback(function(){
			drawChartIssue(array);
		});
	}).fail(function(){
		alert('chart fail!!')
	})
})



function drawChart(array){
	var lists = [];
	var data = new google.visualization.DataTable();
	data.addColumn('string', 'status');
	data.addColumn('number', 'account');
	array.forEach(function(element){
		lists.push([element.taskStatusName,element.taskStatusAccount]);
	});
	data.addRows(lists);
	
	var options = {
		chartArea: {width:'80%',height:'80%'},
		pieHole: 0.4
	};
	
	var chart = new google.visualization.PieChart(document.getElementById('projectTaskStatusChart'));
	chart.draw(data, options);
}

function drawChartIssue(array){
	var lists = [];
	var data = new google.visualization.DataTable();
	data.addColumn('string', 'status');
	data.addColumn('number', 'account');
	array.forEach(function(element){
		lists.push([element.issueStatusName,element.issueStatusAccount]);
	});
	data.addRows(lists);
	
	var options = {
		chartArea: {width:'80%',height:'80%'},
		pieHole: 0.4
	};
	
	var chart = new google.visualization.PieChart(document.getElementById('projectIssueStatusChart'));
	chart.draw(data, options);
}
