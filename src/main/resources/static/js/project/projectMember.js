$(function(){
	
	user();
	member();
	
})

function user(){
	var array = [];
		
		$('#user li').on('click', function(){
		
			var data = new Object();
			var ele = $(this)		
			if(ele.attr('class') == null || ele.attr('class') == '') {
				ele.css("background-color", "rgb(0,0,0,0.1)")
				ele.addClass('userId');
				
				data.userId = ele.data('id');
				data.userName = ele.data('name');
				data.teamName = ele.data('team');
				data.position = ele.data('pos');
				data.projectCode = $('#projectCode').val();
				
				array.push(data);
				
			} else if(ele.attr('class') == 'userId'){
				ele.css("background-color", "rgba(0,0,0,0)")
				
				const idx = array.indexOf(ele.data('id'));
				array.splice(idx,1);
				
				ele.removeClass('userId');
			}
			
			console.log(array);
		})
		array = [];
		memberAdd(array);
}

function memberAdd(array){
	
	$('#memberAdd_btn').on('click', function(){
		$.each(array, function(index, value){
			var member = '<li>' + value.teamName + ' / ' + value.userName + ' / ' + value.position + ' / ' + value.userId + '</li>';
			$('.member').append(member);
			$('#user li').css("background-color", "rgba(0,0,0,0)")
			$('#user li').removeClass('userId');
		});
		
		$.ajax({
			url : '/projectMember/add',
			type : 'POST',
			data : JSON.stringify(array),
			contentType: 'application/json'
		}).done(function(){
			array = [];
			console.log(array);
			location.reload();
		}).fail(function(){
			alert('member add fail');
		})
	})
}

function member(){
	var array = [];
	$('.member li').on('click', function(event){
		
		var ele = $(event.target);
		if(ele.attr('class') == null || ele.attr('class') == ''){
			ele.css("background-color", "rgb(0,0,0,0.1)");
			ele.addClass('memberId');
			
			array.push(ele.data('code'));
		} else if(ele.attr('class') == 'memberId'){
			ele.css("background-color", "rgba(0,0,0,0)");
			
			const idx = array.indexOf(ele.data('code'));
			array.splice(idx,1);
			
			ele.removeClass('memberId');
		}
		
		console.log(array);
		memberRemove(array);
	})
	
	array = [];
}

function memberRemove(array){
	console.log(array)
	$('#memberRemove_btn').on('click', function(){
		$.each(array, function(index, value){
			var item = $('#member li');
			item.css("background-color", "rgba(0,0,0,0)")
		})
		
		$.ajax({
			url : '/projectMember/remove',
			type : 'DELETE',
			data : JSON.stringify(array),
			contentType: 'application/json'
		}).done(function(){
			array = [];
			location.reload();
		}).fail(function(){
			alert('member remove fail');
		})
	})
}