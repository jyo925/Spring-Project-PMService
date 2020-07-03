$(function(){
	const userDuty = $('#project-user-duty').val();
	var normalInfo = $('#normal-info');
	var admin = $('#project-admin-btn');

	
	if(userDuty == 'duty300' || userDuty == 'duty400'){
		admin.css('display', 'none');
		normalInfo.css('display', 'none')
	}
})