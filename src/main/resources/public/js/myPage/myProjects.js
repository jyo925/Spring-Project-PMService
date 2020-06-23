$(document).ready(function() {
	
	$('#elasticSearch').on('input', function() {
		
		let value = new RegExp($(this).val().trim(), "gi");
		let	elasticItems = $('.items th');
		if(value != "/(?:)/gi") {
			console.log("0");
			console.log(elasticItems.text());
			console.log(value);
			elasticItems.each(function(element) {
				//console.log($(this).text());
				if($(this).text().search(value) == -1) {
					$(this).parent().show();
					//$(element).parent().hide();
					console.log("1");
					console.log(value);
				} else {
					$(this).parent().hide();
					console.log("2");
				}
			});
		} else if (value == "/(?:)/gi") {
			elasticItems.each(function(element) {
				$('.items').show();
				//$(element).parent().show();
				console.log("3");
			});
		}
		
	});
	
	
});