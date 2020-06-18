$(function(){
    /* taskChart */
    $.ajax({
        url : '/dashBoard/chart/task',
        type : 'GET',
        dataType : 'json'
    }).done(function(status){
        console.log(status);
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(function(){
            drawTaskChart(status);
        });
    }).fail(function(){
        alert('chart fail!!')
    })

    /* issueChart */
    $.ajax({
        url : '/dashBoard/chart/issue',
        type : 'GET',
        dataType : 'json'
    }).done(function(status){
        console.log(status);
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(function(){
            drawIssueChart(status);
        });
    }).fail(function(){
        alert('chart fail!!')
    })
})

function drawTaskChart(status){
    var lists = [];
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'name');
    data.addColumn('number', 'count');
    status.forEach(function(element){
        lists.push([element.taskStatusName,element.taskStatus]);
    });
    data.addRows(lists);

    var options = {
        chartArea: {width:'100%',height:'80%'},
        pieHole: 0.4,
        colors: ['#4FC1E9','#5D9CEC','#48CFAD','#A0D468','#FFCE54','#FC6E51'],
        pieSliceText: 'label',
        legend: 'none'
    };

    var chart = new google.visualization.PieChart(document.getElementById('UserTaskStatusChart'));
    chart.draw(data, options);
}

function drawIssueChart(status){
    var lists = new Array();
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'name');
    data.addColumn('number', 'count');
    console.log(status);
   // lists.push('dd',1);
    status.forEach(function(element){
        lists.push([element.issueStatusName,element.issueStatus]);
    });
    data.addRows(lists);


    var options = {
        chartArea: {width:'100%',height:'80%'},
        pieHole: 0.4,
        colors: ['#4FC1E9','#5D9CEC','#48CFAD','#A0D468','#FFCE54','#FC6E51'],
        pieSliceText: 'label',
        legend: 'none'
    };

    if (data.getNumberOfRows() == 0) {
        data.addRows([
            ['없음', 1]
        ]);
        options.pieSliceTextStyle = {
            color: 'black'
        };
        options.tooltip = {
            trigger: 'none'
        }
    }

    var chart = new google.visualization.PieChart(document.getElementById('UserIssueStatusChart'));
    chart.draw(data, options);
}