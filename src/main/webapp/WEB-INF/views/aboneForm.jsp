<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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

<!-- Bootstrap Date-Picker Plugin -->
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css" />

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/css/bootstrap-select.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/js/bootstrap-select.min.js"></script>

<!-- (Optional) Latest compiled and minified JavaScript translation files -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/js/i18n/defaults-*.min.js"></script>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!-- Bootstrap CSS and bootstrap datepicker CSS used for styling the demo pages-->
<link rel="stylesheet" href="/resources/css/datepicker.css">
<link rel="stylesheet" href="/resources/css/bootstrap.css">

<!-- Load jQuery and bootstrap datepicker scripts -->
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/datepicker.js"></script>

<script type="text/javascript">
	$(document).ready(function() {

		$('#tarihSec').datepicker({ //# ile id üzerinden yaparsanız yanlızca bir input çalışır 
			format : "dd.mm.yyyy" //dd-mm-yyyy
		});

	});
</script>

<script type="text/javascript">
	$(function() {
		$('#seviye').change(function() {
			$('#subeSeviye').val($(this).val());
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

<title>Abone Giriş</title>
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
								<!-- <li><a href="listAboneByPage?page=0">Abone Listele / Düzenle</a></li>  -->
								<li class="divider"></li>
							</ul></li>

					</ul>
				</div>

			</nav>
		</div>
	</div>
	<!-- HEADER ------------------------------------------------------------- -->

	<form:form data-toggle="validator" role="form" id="aboneRegisterForm"
		cssClass="form-horizontal" modelAttribute="abone" method="post"
		enctype="multipart/form-data" action="saveAbone">


		<p class="bg-success">Abone Bilgileri</p>

		<div class="row">

			<div class="col-md-4">

				<form:hidden path="aboneId" value="${aboneObject.aboneId}" />

				<fmt:formatDate value="${aboneObject.kayitTarihi}" var="kayitTarihi"
					pattern="dd.MM.yyyy" />

				<form:hidden path="kayitTarihi" value="${kayitTarihi}" />

				<div class="form-group">
					<div class="control-label col-xs-2">
						<form:label path="aboneAdi">Abone Adı</form:label>
					</div>
					<div class="col-xs-10">
						<form:input cssClass="form-control" path="aboneAdi"
							value="${aboneObject.aboneAdi}" />
					</div>

				</div>

			</div>

			<div class="col-md-4">
				<div class="form-group">
					<div class="control-label col-xs-2">
						<form:label path="aboneSoyadi">Abone Soyadı</form:label>
					</div>
					<div class="col-xs-10">
						<form:input cssClass="form-control" path="aboneSoyadi"
							value="${aboneObject.aboneSoyadi}" />
					</div>

				</div>
			</div>


			<div class="col-md-4">
				<div class="form-group">

					<c:set var="degisken" value="${aboneObject.aktif}" />
					<c:if test="${degisken == false}">
						<c:set var="degisken" value="" />
					</c:if>

					<div class="checkbox">
						<label> <form:checkbox path="aktif"
								value="${aboneObject.aktif}" checked="${degisken}" /> <b>Durum
								Aktif/Pasif </b>
						</label>
					</div>

				</div>
			</div>

		</div>
		<div class="row">

			<div class="col-md-4">
				<div class="form-group">
					<div class="control-label col-xs-2">
						<form:label path="aboneEposta">Abone ePosta</form:label>
					</div>
					<div class="col-xs-10">
						<form:input type="email" cssClass="form-control" path="aboneEposta"
							value="${aboneObject.aboneEposta}" required="required"/>
					</div>

				</div>
			</div>



			<div class="col-md-4">
				<div class="form-group">
					<div class="control-label col-xs-2">
						<label>Şube</label>
					</div>
					<div class="col-xs-8">

						<c:set var="secilisube" value="${seciliSube}" />

						<select class="form-control" id="sube">

							<c:forEach items="${subeListe}" var="sube">
								<tr>
									<c:set var="degisken1" value="" />
									<c:if test="${secilisube.subeId == sube.subeId}">
										<c:set var="degisken1" value="selected" />
									</c:if>
									<option value="${sube.subeId}" ${degisken1}>${sube.subeAdi}</option>


								</tr>
							</c:forEach>

						</select>

						<form:hidden cssClass="form-control" path="" id='subeadi'
							name="subeAdi" value="${sube.subeAdi}" />

					</div>

				</div>
			</div>

			<div class="col-md-4">
				<div class="form-group">

					<c:set var="degisken" value="${aboneObject.hatali}" />
					<c:if test="${degisken == false}">
						<c:set var="degisken" value="" />
					</c:if>

					<div class="checkbox">
						<label> <form:checkbox path="hatali"
								value="${aboneObject.hatali}" checked="${degisken}" /> <b>
								HATALI </b>
						</label>
					</div>

				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<input type="submit" id="saveAbone"
					class="btn btn-primary btn-block" value="Kaydet" />
			</div>
		</div>

	</form:form>


</body>
</html>