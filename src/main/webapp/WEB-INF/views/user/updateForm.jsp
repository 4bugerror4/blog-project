<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal" />
</sec:authorize>

<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<link href="../css/signin.css" rel="stylesheet">

<!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

<title>Hello, world!</title>
</head>
<body class="text-center">
	<form class="form-signin">
		<input type="hidden" id="id" value="${principal.user.id }" />
		<a href="/"><img class="mb-4" src="https://getbootstrap.com/docs/5.3/assets/brand/bootstrap-logo.svg" alt="" width="72" height="72"></a>
		<h1 class="h3 mb-3 font-weight-normal">회원 수정</h1>
		<label for="username" class="sr-only">Username</label>
		<input type="text" id="username" name="username" class="form-control" value="${principal.user.username }" placeholder="UserId" readonly required autofocus>
		
		<label for="password" class="sr-only">Password</label>
		<input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
		
		<label for="email" class="sr-only">Password</label>
		<input type="email" id="email" name="email" class="form-control" value="${principal.user.email }" placeholder="Email" required>
		
		<button id="btn-update" class="btn btn-lg btn-primary btn-block" type="button">회원수정 완료</button>
		<p class="mt-5 mb-3 text-muted">&copy; 2023.01</p>
	</form>
	
	<script src="/js/user.js"></script>
</body>
</html>
