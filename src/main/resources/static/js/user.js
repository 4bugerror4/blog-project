let index = {
	init: function() {
		$('#btn-save').on('click', () => {
			this.save();
		});
		
		$('#btn-update').on('click', () => {
			this.update();
		});

	},
	
	save: function() {
		
		let data = {
			username: $('#username').val(),
			password: $('#password').val(),
			email: $('#email').val()
		};
		
		// console.log(data);
		
		// ajax 호출 시 default 비동기 호출
		$.ajax({
			// 회원가입 수행 요청
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), // JSON 문자열
			contentType: "application/json; charset=utf-8", // body 타입이 무엇인지
			dataType: "json" // 요청이 서버로해서 응답이 왔을 때
		}).done(function(resp) {
			if (resp.status === 500) {
				alert('회원가입이 실패하였습니다.');
				location.href="/auth/joinForm";	
			} else {
				alert('회원가입이 완료되었습니다.');
				console.log(resp); // UserApiController 에서 리턴 해준값을 resp로 받는다는 것
				location.href="/";	
			}
		}).fail(function(error) {
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경
	},
	
	update: function() {
		
		let data = {
			id: $('#id').val(),
			username: $('#username').val(),
			password: $('#password').val(),
			email: $('#email').val()
		};
		
		// console.log(data);
		
		// ajax 호출 시 default 비동기 호출
		$.ajax({
			// 회원가입 수행 요청
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data), // JSON 문자열
			contentType: "application/json; charset=utf-8", // body 타입이 무엇인지
			dataType: "json" // 요청이 서버로해서 응답이 왔을 때
		}).done(function(resp) {
			alert('회원수정 완료되었습니다.');
			console.log(resp); // UserApiController 에서 리턴 해준값을 resp로 받는다는 것
			location.href="/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경
	}
}

index.init();