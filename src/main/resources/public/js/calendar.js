
$(function(){
	
	
	$('#calendar').fullCalendar({
		header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,basicWeek,basicDay'
        },
        navLinks: true, // can click day/week names to navigate views
        editable: true,
        eventLimit: true, // allow "more" link when too many events
         
		dayClick : function(date, jsEvent, view) {
			let clickDate = date.format();
			$('#start').val(clickDate);
			$('#dialog-window').dialog('open');
			
		},
		
		eventClick: function(calEvent, jsEvent, view) {
			$.ajax({
				url : "/calendarPost",
				type : "POST",
				traditional : true,
				data : ({
					eventId : calEvent.eventId
				})
			});
			
			$('#titlet', '#dialog-event').text('Description: ' + calEvent.description);
		    /*alert('Event: ' + calEvent.title);*/
		    $('#dialog-event').dialog('open');
		    

		  },
		
		eventSources: [
			{
				url: '/calendarE'
			}
		]
		
		
	});
	
	
	$('#dialog-window').dialog({
		autoOpen : false,
		height: 400,
	    width: 350,
		modal: true,
		show : {
			effect : 'drop',
			duration : 500
		},
		hide : {
			effect : 'clip',
			duration : 500
		}
	});
	
	$('#dialog-event').dialog({
		autoOpen : false,
		height: 400,
	    width: 350,
		modal: true,
		show : {
			effect : 'drop',
			duration : 500
		},
		hide : {
			effect : 'clip',
			duration : 500
		}
	});
	
	$.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/calendar",
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            alert("success!");

        },
        error: function (e) {

            alert("fail");

        }
    });
	
	
});




















