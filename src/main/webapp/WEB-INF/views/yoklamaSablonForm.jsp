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

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css" />

<script src="/resources/js/datepicker.js"></script>

<c:if test="${!readonlyDate}">

	<script type="text/javascript">
		$(function() {
			$('#tarihSec').datepicker({ //# ile id üzerinden yaparsanız yanlızca bir input çalışır 
				format : "dd.mm.yyyy" //dd-mm-yyyy
			});
		});
	</script>
</c:if>

<title>Yoklamalar</title>


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
							data-toggle="dropdown">Faaliyet Bilgileri<strong
								class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li><a href="newOzelEtkinlik">Faaliyet Ekle</a></li>
								<li><a href="listAllOzelEtkinlik">Faaliyetleri
										Listele / Düzenle</a></li>
								<li class="divider"></li>
							</ul></li>

					</ul>
					<form class="navbar-form navbar-left" role="search"
						action="searchOzelEtkinlik">

						<select class="selectpicker" id="color">
							<option value="ad" selected="selected">Etkinlik Adı</option>
						</select> <input type='text' class="form-control" name='searchAdi'
							id='searchAdi' value="${param['searchAdi']}" /> <input
							type='hidden' class="form-control" name='searchTur'
							id='searchTur' value="ad" /> <select class="selectpicker"
							id="color">
							<option value="id" selected="selected">Etkinlik Tarihi</option>
						</select> <input type='Date' class="form-control" name='searchTarih1'
							id='searchTarih1' value="${param['searchTarih1']}" /> <input
							type='Date' class="form-control" name='searchTarih2'
							id='searchTarih2' value="${param['searchTarih2']}" />

						<button type="submit" class="btn btn-default">Ara</button>
					</form>
				</div>

			</nav>
		</div>
	</div>
	<!-- HEADER ------------------------------------------------------------- -->


	<form:form data-toggle="validator" role="form" id="yoklamaRegisterForm"
		cssClass="form-horizontal" modelAttribute="yoklama" method="post"
		enctype="multipart/form-data" action="saveSablonYoklama">

		<p class="bg-success">Şablon Yoklama Oluştur</p>

		<div class="row">

			<div class="col-md-4">

				<form:hidden path="ozeletkinlik.etkinlikId" id="etkinlikId"
					name="etkinlikId" value="${ozelEtkinlikObject.etkinlikId}" />

				<div class="form-group">

					<div class="control-label col-xs-2">
						<form:label path="yoklamaTarihi">Yoklama Tarihi</form:label>
					</div>
					<fmt:formatDate value="${yoklamaObject.yoklamaTarihi}"
						var="dateString" pattern="dd.MM.yyyy" />
					<div class="col-xs-10">

						<form:input type="text" cssClass="form-control"
							path="yoklamaTarihi" id="tarihSec" value="${dateString}"
							required="required" readonly="${readonlyDate}" />

					</div>

				</div>

			</div>

			<div class="col-md-4"></div>

		</div>


		<p class="bg-success"></p>

		<div class="row">
			<div class="col-md-12">
				<input type="submit" id="saveSablonYoklama"
					class="btn btn-primary btn-block" value="Kaydet" />
			</div>
		</div>

	</form:form>


</body>
</html>