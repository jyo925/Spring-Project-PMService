$(function(){
	ajaxMethod($('#project-select'));
	selectEvent('#project-select');
	projectInfo($('#project-select'));
})

function selectEvent(target){
	$(target).on('change', function(e){
		ajaxMethod($(e.target));
		projectInfo($(e.target));
	})
}
function projectInfo(target){
	$.ajax({
		url: '/project/detail/info/' + target.val(),
		type: 'get',
	}).done(function(project){
		console.log(project)
		$('#project-name').text(project.projectName);
		$('#project-start').text(project.projectStart);
		$('#project-finish').text(project.projectFinish);
		$('#project-pm').text(project.projectPm);
		$('#project-type').text(project.projectTypeName);
		$('#project-task-account').text(project.projectTaskAccount);
		$('#project-member-account').text(project.projectMemberAccount);
	}).fail(function(){
		alert('project detail info fail')
	})
}

function ajaxMethod(target){
	$.ajax({
		url : '/project/detail/taskChart',
		type : 'GET',
		data : {
			projectId : target.val()
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
			projectId : target.val()
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
	
	$.ajax({
		url: '/projectMemberInfo/gantt',
		type: 'get',
		data: {projectCode : target.val()},
		dataType: 'JSON'
	}).done(function(list){
		google.charts.load('current', {'packages':['timeline']});
		google.charts.setOnLoadCallback(function(){
			drawChartGantt(list);
		});
	}).fail(function(){
		alert('member gantt fail')
	})
}



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

function drawChartGantt(list){
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

    var chart = new google.visualization.Timeline(document.getElementById('projectMemberGantt'));

    chart.draw(data, options);
}
