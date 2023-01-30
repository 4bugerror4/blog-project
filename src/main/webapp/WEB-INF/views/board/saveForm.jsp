<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@include file="../layout/header.jsp"%>

<main role="main">
	<div class="container">
		<form>
			<div class="form-group">
				<label for="title">Title</label> <input type="text" class="form-control" placeholder="Enter title" id="title" />
			</div>

			<div class="form-group">
				<label for="content">Content</label>
				<textarea class="form-control summernote" rows="5" id="content"></textarea>
			</div>

			<div class="text-right">
				<button id="btn-save" class="btn btn-primary" type="button">쓰기</button>
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