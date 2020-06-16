$(function(){
	dropDownEvent();
	insertProject();
})

function dropDownEvent(){
	/* dropDown */
	$('#pmTeamChoice').on('change', function(){		
		$.ajax({
			url : '/project/team',
			type : 'GET',
			data : {
				teamCode : $('#pmTeamChoice').val()
			},
			dataType : 'json'
		}).done(function(teamList){
			var teamSelect = $('#pmIdChoice')
			teamSelect.empty();
			
			var team = '<option>' + '아이디' + '</option>';
			teamSelect.html(team)
			
			$.each(teamList, function(index, value){
				console.log(value.userName + ' : ' + value.userId)
				var teamItem = '<option value=' + value.userId + '>' + value.userName + ' : ' + value.userId + '</option>';
				teamSelect.append(teamItem)
			})
			
		}).fail(function(){
			alert('teamList fail!!');
		})
	})
	
	$('#pmoTeamChoice').on('change', function(){		
		$.ajax({
			url : '/project/team',
			type : 'GET',
			data : {
				teamCode : $('#pmoTeamChoice').val()
			},
			dataType : 'json'
		}).done(function(teamList){
			var teamSelect = $('#pmoIdChoice')
			teamSelect.empty();
			
			var team = '<option>' + '아이디' + '</option>';
			teamSelect.html(team)
			
			$.each(teamList, function(index, value){
				console.log(value.userName + ' : ' + value.userId)
				var teamItem = '<option value=' + value.userId + '>' + value.userName + ' : ' + value.userId + '</option>';
				teamSelect.append(teamItem)
			})
		}).fail(function(){
			alert('teamList fail!!');
		})
	})
}

function insertProject(){
	$('#update_btn').on('click',function(){
		$.ajax({
			url : '/project/'+$('#projectCode').val(),
			type : 'PUT',
			data : {
				projectName : $('#projectName').val(),
				projectSubName : $('#projectSubName').val(),
				projectTypeCode : $('#projectTypeChoice').val(),
				projectStart : $('#projectStart').val(),
				projectFinish : $('#projectFinish').val(),
				projectPm : $('#pmIdChoice').val(),
				projectPmo : $('#pmoIdChoice').val(),
				projectDescription : $('#projectDescription').val(),
				projectStatusCode : $('#projectStatusChoice').val()
			},
			dataType : 'text'
		}).done(function(result){
			if(result === '0') alert('수정된 부분이 없습니다.')
			else alert('수정이 완료되었습니다')
			location.reload();
		}).fail(function(){
			alert('다시 입력해주세요')
		})
	})
}
