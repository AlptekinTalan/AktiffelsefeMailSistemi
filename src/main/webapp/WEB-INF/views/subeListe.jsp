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

<title>Şubeler</title>


</head>
<body class="container-fluid">

	<!-- HEADER ------------------------------------------------------------- -->
	<div class="row">
		<div class="col-md-6">
			<a href="/"> <img alt="Bootstrap Image Preview"
				src="resources/photos/aktiffelsefe.png" />
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
						<li class="dropdown"><a href="/" class="dropdown-toggle"
							data-toggle="dropdown">Şube Bilgileri<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li><a href="newSube">Şube Ekle</a></li>
								<li><a href="listAllSube">Şubeleri Listele / Düzenle</a></li>
								<li class="divider"></li>
							</ul></li>

					</ul>
				</div>

			</nav>
		</div>
	</div>
	<!-- HEADER ------------------------------------------------------------- -->

	<div class="panel-body">
		<h6>

			<c:if test="${empty subeListe}">
				<div class="alert alert-danger">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Böyle bir sube yok :(((</strong>
				</div>
			</c:if>
			<c:if test="${not empty subeListe}">

				<table class="table table-hover table-bordered">
					<thead style="background-color: #DFF0D8;">
						<tr>
							<th>Id</th>
							<th>Sube Adı</th>
							<th>Sube Adresi</th>
							<th>Sube Telefon</th>
							<th>Sube Faks</th>
							<th>Sube Web Sitesi</th>
							<th>Sube ePosta</th>
							<th>Sube Düzenle</th>
							<th>Şube Sil</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${subeListe}" var="sube">
							<tr>
								<th><c:out value="${sube.subeId}" /></th>
								<th><c:out value="${sube.subeAdi}" /></th>
								<th><c:out value="${sube.subeAdresi}" /></th>
								<th><c:out value="${sube.subeTelefon}" /></th>
								<th><c:out value="${sube.subeFaks}" /></th>
								<th><c:out value="${sube.subeWebSitesi}" /></th>
								<th><c:out value="${sube.subeEposta}" /></th>

								<th><a href="editSube?id=<c:out value='${sube.subeId}'/>">Şube
										Düzenle</a></th>
								<th><a href="removeSube?id=<c:out value='${sube.subeId}'/>">Şube
										Sil</a></th>

							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</h6>

	</div>


	<script type="text/javascript">
		$(function() {
			$('#color').change(function() {
				$('#searchTur').val($(this).val());
			}).change(); // Trigger the event
		});
	</script>

</body>
</html>