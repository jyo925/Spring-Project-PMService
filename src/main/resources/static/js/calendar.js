document.addEventListener('DOMContentLoaded', function() {
	var calendarEl = document.getElementById('calendar');
	var calendar = new FullCalendar.Calendar(calendarEl, {
		plugins : [ 'dayGrid', 'interaction' ],
		defaultView : 'dayGridMonth',
		selectable : true,
		/* theme : true, */
		dateClick : function(info) {
			let clickDate = info.dateStr;
			$('#start').val(clickDate);
			$('#dialog-window').dialog('open');
			$.ajax({
				
				url : '/calendar',
				type : 'POST',
				data : {clickDate: clickDate},
				success : function(res){
					console.log(res);
				},
				error : function(){
					alert('error!');
				}
				
			});
		},
		eventSources: [
		    {
		      url: '/calendarE'
		    }
		  ]

	/*
	 * eventSources : [{ events : events, color : '#B5A7AE', textColor : '#fff' }]
	 */

	});

	calendar.render();
});

$(document).ready(function() {

	$('#dialog-window').dialog({

		autoOpen : false,
		show : {
			effect : 'drop',
			duration : 500
		},

		hide : {
			effect : 'clip',
			duration : 500
		}

	})

	$('.datePicker').datepicker({
		dateFormat : "yy-mm-dd"
	});

});