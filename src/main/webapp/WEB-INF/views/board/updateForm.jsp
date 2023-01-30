<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@include file="../layout/header.jsp"%>

<main role="main">
	<div class="container">
		<form>
			<input type="hidden" id="boardId" name="boardId" value="${board.id }">
			<div class="form-group">
				<label for="title">Title</label>
				<input value="${board.title }" type="text" class="form-control" id="title" />
			</div>

			<div class="form-group">
				<label for="content">Content</label>
				<textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
			</div>

			<div class="text-right">
				<button id="btn-update" class="btn btn-primary" type="button">수정</button>
				<button class="btn btn-primary" type="button"><a class="text-white" href="/">취소</a></button>
			</div>
		</form>
	</div>
</main>
<!-- /.container -->

<script>
  $('.summernote').summernote({
    tabsize: 2,
    height: 500
  });
</script>
<script src="/js/board.js"></script>
<%@include file="../layout/footer.jsp"%>