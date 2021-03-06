<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

<title>Üye Listele</title>


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
						<!-- 
						<li class="active">
							<a href="#">Link</a>
						</li>
						<li>
							<a href="#">Link</a>
						</li>
						 -->
						<li class="dropdown"><a href="/" class="dropdown-toggle"
							data-toggle="dropdown">Etkinlik Bilgileri<strong
								class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li><a href="newEtkinlik">Etkinlik Ekle</a></li>
								<li><a href="listAllEtkinlik">Etkinlikleri Listele /
										Düzenle</a></li>
								<li class="divider"></li>
							</ul></li>

					</ul>
					<form class="navbar-form navbar-left" role="search"
						action="searchUyeForEtkinlik">

						<select class="selectpicker" id="color">
							<option value="id" selected="selected">Id'ye Göre Ara</option>
							<option value="ad">Ada Göre Ara</option>
							<option value="soyad">Soyada Göre Ara</option>
						</select> <input type='text' class="form-control" name='searchAdi'
							id='searchAdi' /> <input type='hidden' class="form-control"
							name='searchTur' id='searchTur' value="ad" /> <input
							type='hidden' class="form-control" name='etkinlikId'
							id='etkinlikId' value="${param['etkinlikId']}" />

						<button type="submit" class="btn btn-default">Ara</button>
					</form>

				</div>

			</nav>
		</div>
	</div>
	<!-- HEADER ------------------------------------------------------------- -->

	<div class="panel-body">
		<h6>

			<c:if test="${empty uyeListe}">
				<div class="alert alert-danger">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Çok üzgünüz fakat seni bulamadık :(((</strong>
				</div>
			</c:if>
			<c:if test="${not empty uyeListe}">

				<table class="table table-hover table-bordered">
					<thead style="background-color: #DFF0D8;">
						<tr>

							<th>EKLE</th>

							<th>Id</th>
							<th>Adı</th>
							<th>Soyadı</th>
							<th>Ev Tel</th>
							<th>İş Tel</th>
							<th>Cep Tel</th>
							<th>e Posta</th>
							<th>Doğum Tarihi</th>
							<th>Meslek</th>
							<th>Adres</th>
							<th>Kayıt Tarihi</th>
							<th>Aktif/Pasif</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${uyeListe}" var="uye">
							<tr>
								<th><a
									href="addEgitmenToEtkinlik?uyeId=<c:out value='${uye.uyeId}'/>&etkinlikId=<c:out value="${param['etkinlikId']}"></c:out>">Etkinliğe
										Ekle</a></th>

								<th><c:out value="${uye.uyeId}" /></th>
								<th><c:out value="${uye.uyeAdi}" /></th>
								<th><c:out value="${uye.uyeSoyadi}" /></th>
								<th><c:out value="${uye.uyeTelEv}" /></th>
								<th><c:out value="${uye.uyeTelIs}" /></th>
								<th><c:out value="${uye.uyeTelCep}" /></th>
								<th><c:out value="${uye.ePosta}" /></th>
								<th><c:out value="${uye.dogumTarihi}" /></th>
								<th><c:out value="${uye.meslek}" /></th>
								<th><c:out value="${uye.adres}" /></th>
								<th><c:out value="${uye.kayitTarihi}" /></th>
								<c:set var="degisken" value="${uye.aktif}" />
								<c:if test="${degisken == false}">
									<c:set var="degisken" value="Hayır" />
								</c:if>
								<c:if test="${degisken == true}">
									<c:set var="degisken" value="Evet" />
								</c:if>

								<th><c:out value="${degisken}" /></th>

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