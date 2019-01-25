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

<script>
	$(document).ready(function() {
		$('#example').DataTable();
	});
</script>

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.12/css/dataTables.bootstrap.min.css"
	rel="stylesheet" />

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.12/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.12/js/dataTables.bootstrap.min.js"></script>

<title>Aboneler</title>


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
							data-toggle="dropdown">Mail Aboneleri<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li><a href="newAbone">Abone Ekle</a></li>
								<li><a
									href="searchAbone?searchAdi=&searchTur=eposta&subeAdi=">Abone
										Ara</a></li>
								<li><a href="listAllAbone">Abone Listele / Düzenle</a></li>
								<!-- <li><a href="listAboneByPage?page=0">Abone Listele / Düzenle</a></li> -->
								<li class="divider"></li>
							</ul></li>

					</ul>
					<form class="navbar-form navbar-left" role="search"
						action="searchAbone">

						<select class="selectpicker" id="color">
							<option value="eposta" selected="selected">eMaile Göre
								Ara</option>
							<option value="ad">Ada Göre Ara</option>
							<option value="soyad">Soyada Göre Ara</option>
						</select> <input type='text' class="form-control" name='searchAdi'
							id='searchAdi' /> <input type='hidden' class="form-control"
							name='searchTur' id='searchTur' value="id" /> <input
							type='hidden' class="form-control" name='subeAdi' id='subeadi'
							value="${sube.subeAdi}" />
						<button type="submit" class="btn btn-default">Ara</button>


						<label>Şube</label> <select class="form-control" id="sube">
							<option></option>

							<c:forEach items="${subeListe}" var="sube">
								<tr>

									<option value="${sube.subeId}" ${degisken1}>${sube.subeAdi}</option>

								</tr>
							</c:forEach>

						</select>

					</form>

				</div>

			</nav>
		</div>
	</div>
	<!-- HEADER ------------------------------------------------------------- -->

	<div class="alert alert-success">
		<strong>${fn:length(aboneListe)}</strong> Abone Listelendi .
	</div>

	<div class="panel-body">
		<h6>

			<c:if test="${not empty aboneListe}">

				<table id="example"
					class="table table-striped table-bordered table-hover"
					cellspacing="0" width="100%">
					<thead style="background-color: #DFF0D8;">
						<tr>
							<th>Id</th>
							<th>Abone Adı</th>
							<th>Abone Soyadı</th>
							<th>Abone ePosta</th>
							<th>Abone Şube</th>
							<th>Durum</th>
							<th>Hatalı</th>

							<th>Abone Düzenle</th>
							<th>Etkinliği Sil</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${aboneListe}" var="abone">
							<tr>
								<th><c:out value="${abone.aboneId}" /></th>
								<th><c:out value="${abone.aboneAdi}" /></th>
								<th><c:out value="${abone.aboneSoyadi}" /></th>
								<th><c:out value="${abone.aboneEposta}" /></th>
								<th><c:out value="${abone.sube.subeAdi}" /></th>

								<c:set var="degisken" value="${abone.aktif}" />
								<c:if test="${degisken == false}">
									<c:set var="degisken" value="Pasif" />
								</c:if>
								<c:if test="${degisken == true}">
									<c:set var="degisken" value="Aktif" />
								</c:if>

								<th><c:out value="${degisken}" /></th>

								<c:set var="hatali" value="${abone.hatali}" />
								<c:if test="${hatali == false}">
									<c:set var="hatali" value="Hayır" />
								</c:if>
								<c:if test="${hatali == true}">
									<c:set var="hatali" value="Evet" />
								</c:if>

								<th><c:out value="${hatali}" /></th>

								<th><a
									href="editAbone?id=<c:out value='${abone.aboneId}'/>">Abone
										Düzenle</a></th>
								<th><a
									href="removeAbone?id=<c:out value='${abone.aboneId}'/>">Abone
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

	<script type="text/javascript">
		$(function() {
			$('#sube').change(function() {
				$('#subeadi').val($(this).val());
			}).change(); // Trigger the event
		});
	</script>

</body>
</html>