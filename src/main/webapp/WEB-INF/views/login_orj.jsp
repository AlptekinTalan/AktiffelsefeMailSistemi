<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Aktiffelsefe Bilgi Sistemi Kullanıcı Girişi</title>

<!-- CSS -->

<link href="<c:url value='/assets/bootstrap/css/bootstrap.min.css' />"
	rel="stylesheet"></link>
<link
	href="<c:url value='/assets/font-awesome/css/font-awesome.min.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/assets/css/form-elements.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/assets/css/style.css' />" rel="stylesheet"></link>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

<!-- Favicon and touch icons -->
<link rel="shortcut icon" href="assets/ico/favicon.png">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="assets/ico/apple-touch-icon-57-precomposed.png">

</head>

<body>

	<!-- Top content -->
	<div class="top-content">

		<div class="inner-bg">
			<div class="container">

				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 form-box">
						<div class="form-top">
							<div class="form-top-left">
								<h3>Aktiffelsefe Bilgi Sistemi</h3>
								<p>Lütfen Kullanıcı Adı ve Şifrenizle Giriş Yapınız:</p>
							</div>
							<div class="form-top-right">
								<i class="fa fa-key"></i>
							</div>
						</div>
						<div class="form-bottom">

							<c:url var="loginUrl" value="/j_spring_security_check" />

							<form role="form" action="${loginUrl}" method="post"
								class="login-form">
								<div class="form-group">
									<label class="sr-only" for="j_username">Kullanıcı Adı</label> <input
										type="text" placeholder="Kullanıcı Adı..."
										class="form-username form-control" name="j_username"
										id="j_username">
								</div>
								<div class="form-group">
									<label class="sr-only" for="j_password">Şifre</label> <input
										type="password" placeholder="Şifre..."
										class="form-password form-control" name="j_password"
										id="j_password">
								</div>

								<button type="submit" name="submit" class="btn">Giriş!</button>
							</form>

						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

	<!-- Javascript -->
	<script src="assets/js/jquery-1.11.1.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/js/jquery.backstretch.min.js"></script>
	<script src="assets/js/scripts.js"></script>

	<!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->

</body>

</html>