let index = {
	init: function() {
		$('#btn-save').on('click', () => {
			this.save();
		});
		
		$('#btn-delete').on('click', () => {
			this.deleteById();
		});
		
		$('#btn-update').on('click', () => {
			this.update();
		});
		
		$('#btn-reply-save').on('click', () => {
			this.replySave();
		});
	},
	
	save: function() {
		let data = {
			title: $('#title').val(),
			content: $('#content').val()
		};
		
		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert('글쓰기가 완료되었습니다.');
			location.href='/';
		}).fail(function(error) {
			alert(JSON.stringify(error));
		})
	},
	
	deleteById: function() {
		
		let id = $('#boardId').text();
		
		console.log(id);
		
		$.ajax({
			type: "DELETE",
			url: "/api/board/" + id,
			dataType: "json"
		}).done(function(resp) {
			alert('삭제가 완료되었습니다.');
			location.href='/';
		}).fail(function(error) {
			alert(JSON.stringify(error));
		})
	},
	
	update: function() {
		let data = {
			id: $('#boardId').val(),
			title: $('#title').val(),
			content: $('#content').val()
		};
		
		$.ajax({
			type: "PUT",
			url: "/api/board/" + data.id,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert('글 수정 완료되었습니다.');
			location.href='/';
		}).fail(function(error) {
			alert(JSON.stringify(error));
		})
	},
	
	replySave: function() {
		let data = {
			userId: $('#userId').val(),
			boardId: $('#boardId').text(),
			content: $('#reply-content').val()
		};
		
		// console.log(data);
		
		$.ajax({
			type: "POST",
			url: '/api/board/' + data.boardId + '/reply',
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert('댓글 쓰기 완료되었습니다.');
			location.href='/board/' + data.boardId;
		}).fail(function(error) {
			alert(JSON.stringify(error));
		})
	},
	
	replyDelete: function(boardId, replyId) { // html 에서 던진 값을 받는다.
		
		$.ajax({
			type: "DELETE",
			url: "/api/board/" + boardId + "/reply/" + replyId,
			dataType: "json"
		}).done(function(resp) {
			alert('댓글 삭제 완료되었습니다.');
			location.href='/board/' + boardId;
		}).fail(function(error) {
			alert(JSON.stringify(error));
		})
	}
}

index.init();