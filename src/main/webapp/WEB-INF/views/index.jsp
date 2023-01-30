<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="layout/header.jsp"%>

<main role="main">
	<div class="container">
		<div class="starter-template">
			<h1>Bootstrap starter template</h1>
			<p class="lead">
				Use this document as a way to quickly start any new project.<br> All you get is this text and a mostly barebones HTML document.
			</p>
		</div>

		<c:forEach var="board" items="${boards.content}">
			<div class="card w-100 mb-3">
				<div class="card-body">
					<h3 class="card-title">${board.title }</h3>
					<a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
				</div>
			</div>
		</c:forEach>

		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
				<c:choose>
					<c:when test="${number == 0}">
						<li class="page-item disabled"><a class="page-link" href="?page=${number - 1}">Previous</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link" href="?page=${number - 1}">Previous</a></li>
					</c:otherwise>
				</c:choose>

				<c:forEach begin="${startPage}" end="${endPage}" var="page">
					<c:choose>
						<c:when test="${number == page - 1}">
							<li class="page-item disabled"><a class="page-link" href="?page=${page - 1}">${page}</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link" href="?page=${page - 1}">${page}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:choose>
					<c:when test="${hasNext}">
						<li class="page-item"><a class="page-link" href="?page=${number + 1}">Next</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item disabled"><a class="page-link" href="?page=${number + 1}">Next</a></li>
					</c:otherwise>
				</c:choose>

			</ul>
		</nav>

	</div>
</main>
<!-- /.container -->

<%@include file="layout/footer.jsp"%>