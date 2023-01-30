<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@include file="../layout/header.jsp"%>

<main role="main">
	<div class="container">
		<div class="text-right">
			글 번호 : <span id="boardId"><i>${board.id }</i></span> 작성자 : <span><i>${board.user.username }</i></span>
		</div>
		<br />

		<form>
			<input type="hidden" id="userId" value="${principal.user.id}" >
			<div class="form-group">
				<h5>Title</h5>
				<h3>${board.title }</h3>
			</div>
			<hr />

			<div class="form-group">
				<h5>Content</h5>
				<div>${board.content }</div>
			</div>
			<hr />

			<div class="card mb-3">
				<div class="card-body">
					<textarea id="reply-content" rows="1" class="form-control"></textarea>
				</div>
				<div class="card-footer">
					<button id="btn-reply-save" class="btn btn-primary" type="button">등록</button>
				</div>
			</div>

			<div class="card mb-3">
				<div class="card-header">댓글 리스트</div>
				<ul id="replys-box" class="list-group">
					<c:forEach var="reply" items="${board.replys }">
						<li id="reply-${reply.id}" class="list-group-item d-flex justify-content-between">
							<div>${reply.content }</div>
							<div class="d-flex">
								<div class="font-italic">작성자 : ${reply.user.username } &nbsp</div>
								<c:if test="${reply.user.id == principal.user.id}">
									<button onClick="index.replyDelete(${board.id}, ${reply.id})" class="badge btn btn-primary" type="button">삭제</button>
								</c:if>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>

			<div class="text-right">
				<c:if test="${board.user.id == principal.user.id}">
					<a href="/board/${board.id}/updateForm" class="btn btn-warning" type="button">수정</a>
					<button id="btn-delete" class="btn btn-danger" type="button">삭제</button>
				</c:if>
				<button class="btn btn-secondary" onclick="history.back()" type="button">돌아가기</button>
			</div>

		</form>
	</div>
</main>
<!-- /.container -->
<script src="/js/board.js"></script>
<%@include file="../layout/footer.jsp"%>