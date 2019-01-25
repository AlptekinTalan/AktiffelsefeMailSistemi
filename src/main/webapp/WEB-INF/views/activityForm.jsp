<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<!DOCTYPE html>
<html>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="Alptekin Talan">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/css/bootstrap-select.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/js/bootstrap-select.min.js"></script>

<!-- (Optional) Latest compiled and minified JavaScript translation files -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/js/i18n/defaults-*.min.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet" />

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<title>Üye Aktivite</title>

</head>
<body class="container-fluid">

	<!-- HEADER ------------------------------------------------------------- -->
	<div class="row">
		<div class="col-md-6">
			<a href="/"> <img alt="Bootstrap Image Preview"
				src="/resources/photos/aktiffelsefe.png" />
			</a>
		</div>
		<div class="col-md-6">
			<blockquote>
				<p>Felsefe, Hayatı ve Kendimizi Daha İyi Tanımamızı ve Daha
					Bilinçli Bireyler Olmamızı Sağlar.</p>
			</blockquote>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<nav class="navbar navbar-default" role="navigation">
				<div class="navbar-header">

					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span><span
							class="icon-bar"></span><span class="icon-bar"></span><span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="/">Ana Sayfa</a>
				</div>

				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<!-- 
						<li class="active">
							<a href="#">Link</a>
						</li>
						<li>
							<a href="#">Link</a>
						</li>
						 -->
						<li class="dropdown"><a href="/" class="dropdown-toggle"
							data-toggle="dropdown">Katılımcı Bilgileri<strong
								class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li><a href="newUye">Katılımcı Ekle</a></li>
								<li><a href="listAllUye?uyeId=0">Katılımcı Listele /
										Düzenle</a></li>
								<li class="divider"></li>

							</ul></li>

					</ul>
				</div>

			</nav>
		</div>
	</div>
	<!-- HEADER ------------------------------------------------------------- -->
	<form:form data-toggle="validator" role="form" id="uyeRegisterForm"
		cssClass="form-horizontal" modelAttribute="uye" method="post"
		enctype="multipart/form-data" action="saveUye">

		<!-- ÜYE FOTO -->
		<div class="row">
			<div class="col-md-2">

				<c:set var="degisken" value="${uyeObject.foto}" />

				<c:choose>
					<c:when test="${degisken == null}">
						<img id="blah" src="/resources/photos/avatar.jpg"
							class="img-rounded" width="100" height="100" />
					</c:when>
					<c:otherwise>
						<img id="blah" src="/imageDisplay?id=${uyeObject.uyeId}"
							class="img-rounded" width="100" height="100" />
					</c:otherwise>
				</c:choose>

				<!-- ÜYE FOTO -->

			</div>
			<div class="col-md-2">


				<h1>
					<em>${uyeObject.uyeAdi}&nbsp${uyeObject.uyeSoyadi}</em>
				</h1>

			</div>

			<div class="col-md-6">
				<div class="text-right">
					<h2>
						<em> Geliş Tarihi:&nbsp <fmt:formatDate
								value="${uyeObject.gelisTarihi}" pattern="dd.MM.yyyy" />
							&nbsp&nbsp&nbsp&nbsp
						</em>
					</h2>

				</div>
			</div>

		</div>

		<div class="row">&nbsp</div>

		<p class="bg-success">Raporlar</p>

		<canvas id="myChart" width="300" height="100"></canvas>


		<script>
				var ctx = document.getElementById('myChart').getContext('2d');
				var chart = new Chart(ctx, {
					// The type of chart we want to create
					type : 'horizontalBar',

					// The data for our dataset
					data : {
						labels : ${deger1},
						datasets : [ {
							label : "Toplam Gönderilen Mail Sayısı",
							backgroundColor : 'rgb(189, 238, 170)',
							borderColor : 'rgb(189, 238, 170)',
							data : ${deger2},
						} ]
					},

					// Configuration options go here
					options : {
				        scales: {
				            xAxes: [{
				                ticks: {
				                    beginAtZero:true
				                }
				            }]
				        }
				    }
				});
			</script>

	</form:form>

</body>

</html>