$(function(){	
	dropDownEvent();
	insertProject();
	deleteProject();
	regExp();
	check();
	
	$('#back-btn').on('click', function(){
		window.history.back();
	})
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

function deleteProject(){
	$('#delete_btn').on('click', function(){
		var result = confirm('삭제하시겠습니까?');
		if(result) {
			location.href = "/projectDelete/" + $('#projectCode').val();
		}
	})
}

function regExp(){
	$('#project-subName').keyup(function(e){
		var pattern = /^[A-Z]{3,4}$/;
		var str = $(this).val();
		
		if(!pattern.test(str)) {
			var item = '<span style="color: red" >* 규칙에 맞게 입력해주세요</span>';
			$('#project-subName-div').html(item);
		} else if(pattern.test(str)){
			var item = '<span style="color: green;">* 정상적으로 입력하셨습니다</span>';
			$('#project-subName-div').html(item);
		}
	})

}

function check(){
	$('#check_btn').on('click', function(){
		$.ajax({
			url: '/project/subName/check',
			type: 'GET',
			data: {subName : $('#project-subName').val()}
		}).done(function(num){
			if(num <= 0) alert('사용가능합니다.')
			else if(num > 0) alert('이미 사용중입니다.')
		}).fail(function(){
			alert('subName check fail')
		})
	})
}