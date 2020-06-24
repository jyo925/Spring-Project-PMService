$(document).ready(function() {
	
	let	elasticItems = $('.items .val');
	$('#elasticSearch').on('input', function() {
		
		let value = new RegExp($(this).val().trim(), "gi");
		if(value != "/(?:)/gi") {
			console.log("0");
			elasticItems.each(function(element) {
				if($(this).text().search(value) != -1) {
					$(this).parent().show();
					console.log($(this).text());
					console.log("1");
					return;
				} else {
					$(this).parent().hide();
					console.log("2");
				}
			});
		} else if (value == "/(?:)/gi") {
			elasticItems.each(function(element) {
				$('.items').show();
				console.log("3");
			});
		}
		
	});
	
	$('#projectProgress').on('change', function() {
		
		let value = new RegExp($(this).val().trim(), "gi");
		if(value != "/(?:)/gi") {
			console.log("0");
			elasticItems.each(function(element) {
				if($(this).text().search(value) != -1) {
					$(this).parent().show();
					console.log($(this).text());
					console.log("1");
					return;
				} else {
					$(this).parent().hide();
					console.log("2");
				}
			});
		} else if (value == "/(?:)/gi") {
			elasticItems.each(function(element) {
				$('.items').show();
				console.log("3");
			});
		}
		
	});
	
	
});