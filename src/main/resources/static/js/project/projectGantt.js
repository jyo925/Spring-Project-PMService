$(function(){
	gantt();
})

function gantt(){
	$.ajax({
		url: '/projectMember/gantt',
		type: 'get',
		data: {projectCode : $('#project-code').val()},
		dataType: 'JSON'
	}).done(function(list){
		google.charts.load('current', {'packages':['timeline']});
		google.charts.setOnLoadCallback(function(){
			drawChart(list);
		});
	}).fail(function(){
		alert('member gantt fail')
	})
}

function drawChart(list){
	var lists = [];
	var data = new google.visualization.DataTable();
	data.addColumn({type: 'string', id: 'User ID'});
	data.addColumn({type: 'string', id: 'Task Name'});
	data.addColumn({type: 'date', id: 'Start'});
	data.addColumn({type: 'date', id: 'End'});

	list.forEach(function(value){

		var startYear = new Date(value.taskStart).getFullYear()
		var startMonth = new Date(value.taskStart).getMonth()+1
		var startDay = new Date(value.taskStart).getDate()

		var finishYear = new Date(value.taskFinish).getFullYear()
		var finishMonth = new Date(value.taskFinish).getMonth()+1
		var finishDay = new Date(value.taskFinish).getDate()

		lists.push([value.userId, value.taskName, new Date(startYear, startMonth, startDay), new Date(finishYear, finishMonth, finishDay)]);
	});

	data.addRows(lists);

	var options = {
		height: 400,
		gantt: {
			trackHeight: 30
		}
	};

	var chart = new google.visualization.Timeline(document.getElementById('gantt-div'));

	chart.draw(data, options);
}