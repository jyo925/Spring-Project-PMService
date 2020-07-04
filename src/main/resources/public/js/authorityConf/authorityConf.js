

$(document).ready(function() {
	
	let myAlert = $('#myAlert').hide()
	
	$( function() {
	  $( "#tabs" ).tabs();
	});
	
	$('#developer .items').each(function() {
		
		$(this).find('option:eq(3)').attr('selected', 'selected');
		
		if($(this).find('th:eq(5)').text() != "duty400") {
			$(this).remove();
		};
		
	});
	
	$('#manager .items').each(function() {
		
		$(this).find('option:eq(2)').attr('selected', 'selected');
		
		if($(this).find('th:eq(5)').text() != "duty300") {
			$(this).remove();
		};
	});
	
	$('#pm .items').each(function() {
		
		$(this).find('option:eq(1)').attr('selected', 'selected');
		
		if($(this).find('th:eq(5)').text() != "duty200") {
			$(this).remove();
		};
	});
	
	$('#pmo .items').each(function() {
		
		$(this).find('option:eq(0)').attr('selected', 'selected');
		
		if($(this).find('th:eq(5)').text() != "duty100") {
			$(this).remove();
		};
	});
	
	
	
	$('.btn').on('click', function() {
		
		let data = '';
		let pm = new RegExp("duty200", "gi");
		let pmo = new RegExp("duty100", "gi");
		let manager = new RegExp("duty300", "gi");
		
		$('#developer .items').each(function() {
			data += " "+$(this).find('select').val()
			data += ","+$(this).find('input').val()
		})
		
		$('#manager .items').each(function() {
			data += " "+$(this).find('select').val()
			data += ","+$(this).find('input').val()
		})
		
		$('#pm .items').each(function() {
			data += " "+$(this).find('select').val()
			data += ","+$(this).find('input').val()
		})
		
		$('#pmo .items').each(function() {
			data += " "+$(this).find('select').val()
			data += ","+$(this).find('input').val()
		})
		
		$('form #data').val(data);
		
		if((data.match(pm) || []).length > 1) {
			myAlert.text("There's should be only one PM in project").show();
		} else if((data.match(pmo) || []).length > 1) {
			myAlert.text("There's should be only one PMO in project").show();
		} else if((data.match(manager) || []).length > 1) {
			myAlert.text("There's should be only one Manager in project").show();
		} else {
			$('#form').submit();
		}
		
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
});