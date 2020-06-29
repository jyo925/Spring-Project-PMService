$(function(){
    /* projectAllChart */
    $.ajax({
        url : '/dashBoard/chart/projectAll',
        type : 'GET',
        dataType : 'json'
    }).done(function(status){
        console.log(status);
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(function(){
            drawProjectAllChart(status);
        });
    }).fail(function(){
        alert('chart fail!!')
    })

    /* issueALlChart */
    $.ajax({
        url : '/dashBoard/chart/issueAll',
        type : 'GET',
        dataType : 'json'
    }).done(function(status){
        console.log(status);
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(function(){
            drawIssueAllChart(status);
        });
    }).fail(function(){
        alert('chart fail!!')
    })

    /* monthlyBarChart */
    $.ajax({
        url : '/dashBoard/chart/monthlyProject',
        type : 'GET',
        dataType : 'json'
    }).done(function(status){
        console.log(status);
        google.charts.load('current', {'packages':['corechart','bar']});
        google.charts.setOnLoadCallback(function(){
            drawMonthlyProjectChart(status);
        });
    }).fail(function(){
        alert('chart fail!!')
    })
})

function drawProjectAllChart(status){
    var lists = [];
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'name');
    data.addColumn('number', 'count');
    status.forEach(function(element){
        lists.push([element.projectStatusName,element.projectStatus]);
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

    var chart = new google.visualization.PieChart(document.getElementById('ProjectAllStatusChart'));
    chart.draw(data, options);
}

function drawIssueAllChart(status){
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

    var chart = new google.visualization.PieChart(document.getElementById('IssueAllStatusChart'));
    chart.draw(data, options);
}

function drawMonthlyProjectChart(status){
    var lists = new Array();
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'month');
    data.addColumn('string', 'type');
    data.addColumn('number', 'count');
    console.log(status);
    // lists.push('dd',1);
    status.forEach(function(element){
        lists.push([[element.projectMonth, element.projectType], element.projectCount]);
    });
    data.addRows(lists);

    var options = {
        chartArea: {width:'100%',height:'80%'},
        colors: ['#4FC1E9','#5D9CEC','#48CFAD','#A0D468','#FFCE54','#FC6E51'],
        legend: 'none',
        isStacked: true
    };

    if (data.getNumberOfRows() == 0) {
        data.addRows([
            ['없음', 1]
        ]);
        options.tooltip = {
            trigger: 'none'
        }
    }

    var chart = new google.visualization.ColumnChart(document.getElementById('MonthlyProjectChart'));
    chart.draw(data, options);
}