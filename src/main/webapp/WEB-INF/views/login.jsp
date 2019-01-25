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

<script>
	var count_particles, stats, update;
	stats = new Stats;
	stats.setMode(0);
	stats.domElement.style.position = 'absolute';
	stats.domElement.style.left = '0px';
	stats.domElement.style.top = '0px';
	document.body.appendChild(stats.domElement);
	count_particles = document.querySelector('.js-count-particles');
	update = function() {
		stats.begin();
		stats.end();
		if (window.pJSDom[0].pJS.particles
				&& window.pJSDom[0].pJS.particles.array) {
			count_particles.innerText = window.pJSDom[0].pJS.particles.array.length;
		}
		requestAnimationFrame(update);
	};
	requestAnimationFrame(update);
</script>

<style>
canvas {
	display: block;
	background: rgb(33, 36, 50);
	position: fixed;
	z-index: -1;
}
/* Particles Canvas*/
#particles-js {
	height: 500px;
	width: 100%;
	background-color: #D0EDF5;
	background-image: url('');
	background-size: cover;
	background-position: 50% 50%;
	background-repeat: no-repeat;
}
</style>

<!-- 
<meta name="description"
	content="particles.js is a lightweight JavaScript library for creating particles.">
-->
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=yes">


</head>

<body id="particles-js">

	<!-- scripts -->
	<script src="/resources/js/particles.js"></script>
	<script src="/resources/js/app.js"></script>

	<!-- stats.js -->
	<script src="/resources/js/stats.js"></script>
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

</body>

</html>