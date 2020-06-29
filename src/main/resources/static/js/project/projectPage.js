$(document).ready(function() {

	// 페이지버호 클릭시
	var actionForm = $("#actionForm");
	// 페이지 이전,다음, 숫자 버튼 클릭시
	$(".paginate_button a").on("click", function(e) {
		e.preventDefault(); // a태그 기능 막고
		console.log("click");
		// actionForm pageNum값을 href 속성값으로 추가
		actionForm.find("input[name='pageNum']").val($(this).attr("href"));
		actionForm.submit();
	});

	// 검색버튼 클릭시
	/*var searchForm = $("#searchForm");
	$("#searchForm button").on("click", function(e) {

		if (searchForm.find("option:selected").val() == "NULL") {
			alert("검색 종류를 선택하세요.")
			return false;
		}

		if (!searchForm.find("input[name='keyword']").val()) {
			alert("키워드를 입력하세요.")
			return false;
		}

		searchForm.find("input[name='pageNum']").val("1");
		// e.preventDefault(); //search버튼 클릭시 폼태그 전송막기
		searchForm.submit();
	});*/
});