
$(function(){
	
	$('#calendar').fullCalendar({
		header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,basicWeek,basicDay'
        },
        navLigYnks: true, // can click day/week names to navigate views
        editable: true,
        eventLimit: true, // allow "more" link when too many events
        
		dayClick : function(date, jsEvent, view) {
			let clickDate = date.format();
			$('#start').val(clickDate);
			$('#dialog-window').dialog('open');
			
		},
		
		eventClick: function(calEvent, jsEvent, view) {
			/*$.ajax({
				url : "/calendarPost",
				type : "POST",
				traditional : true,
				data : ({
					eventId : calEvent.eventId
				})
			});*/
			
			$('#title', '#dialog-event').val(calEvent.title);
			$('#start', '#dialog-event').val(calEvent.start.format('YYYY-MM-DD'));
			$('#finish', '#dialog-event').val(calEvent.end.format('YYYY-MM-DD'));
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
	
	$('.datePicker').datepicker({
		dateFormat: "yy-mm-dd"
	});
	
	
	let textList = $('#textList');
		
	$('.memb').on('click', function() {
		let regexp = new RegExp($(this).text(),"gi");
		let replace = new RegExp(" "+$(this).text(),"gi");
		if(textList.text() == ""){
			//membersList = $(this).text();
			textList.text(" "+$(this).text());
			$(this).removeClass("btn-light");
		}else if(textList.text().search(regexp) != -1){
			//alert('member already exist');
			textList.text(textList.text().replace(replace,""));
			$(this).addClass("btn-light");
			
		}else{
			//membersList = membersList+" "+ $(this).text();
			textList.text(textList.text()+" "+$(this).text());
			$(this).removeClass("btn-light");
		}
	
		
		
	});
	
	
	
});




















