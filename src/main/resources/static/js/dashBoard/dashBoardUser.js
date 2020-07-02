$(function(){
	download();
})

function download(){
	$('.output-name').on('click', function(e){
		var liObj = $(e.target);
		var path = encodeURIComponent(liObj.data('path'));
		self.location = '/output/download?fileName=' + path;
	})
}