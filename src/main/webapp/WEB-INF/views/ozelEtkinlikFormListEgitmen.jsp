<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
	$(function() {
		$('#color').change(function() {
			$('#searchTur').val($(this).val());
		}).change(); // Trigger the event
	});
</script>

<script type="text/javascript">
	$(document).ready(function() {

		$('#tarihSec').datepicker({ //# ile id üzerinden yaparsanız yanlızca bir input çalışır 
			format : "dd.mm.yyyy" //dd-mm-yyyy
		});

	});
</script>

<title>Faaliyet Giriş</title>
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
								<li><a href="listAllOzelEtkinlik">Faaliyetleri Listele
										/ Düzenle</a></li>
								<li class="divider"></li>
							</ul></li>

					</ul>
					<form class="navbar-form navbar-left" role="search"
						action="searchUyeInOzelEtkinlik">

						<select class="selectpicker" id="color">
							<option value="id" selected="selected">Id'ye Göre Ara</option>
							<option value="ad">Ada Göre Ara</option>
							<option value="soyad">Soyada Göre Ara</option>
						</select> <input type='text' class="form-control" name='searchAdi'
							id='searchAdi' /> <input type='hidden' class="form-control"
							name='etkinlikId' id='etkinlikId' value="${param['etkinlikId']}" />
						<input type='hidden' class="form-control" name='searchTur'
							id='searchTur' value="id" />


						<button type="submit" class="btn btn-default">Ara</button>
					</form>
				</div>

			</nav>
		</div>
	</div>
	<!-- HEADER ------------------------------------------------------------- -->

	<form:form data-toggle="validator" role="form"
		id="ozeletkinlikRegisterForm" cssClass="form-horizontal"
		modelAttribute="ozelEtkinlikObject" method="post"
		enctype="multipart/form-data" action="saveOzelEtkinlik">


		<p class="bg-success">Faaliyet Bilgileri</p>

		<div class="row">

			<div class="col-md-4">

				<form:hidden path="etkinlikId"
					value="${ozelEtkinlikObject.etkinlikId}" />

				<div class="form-group">
					<div class="control-label col-xs-2">
						<form:label path="etkinlikAdi">Adı</form:label>
					</div>
					<div class="col-xs-10">
						<form:input cssClass="form-control" path="etkinlikAdi"
							value="${ozelEtkinlikObject.etkinlikAdi}" readonly="true" />
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">

					<div class="control-label col-xs-2">
						<form:label path="etkinlikTarihi">Etkinlik Tarihi</form:label>
					</div>
					<div class="col-xs-10">
						<form:input type="text" cssClass="form-control"
							path="etkinlikTarihi" id="tarihSec"
							value="${ozelEtkinlikObject.etkinlikTarihi}" readonly="true" />
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">

					<div class="control-label col-xs-2">
						<form:label path="etkinlikSaati">Etkinlik Saati</form:label>
					</div>
					<div class="col-xs-10">
						<form:input cssClass="form-control" path="etkinlikSaati"
							value="${ozelEtkinlikObject.etkinlikSaati}" readonly="true" />
					</div>
				</div>
			</div>
		</div>
		<!-- 
		<div class="row">
			<div class="col-md-12">
				<input type="submit" id="saveEtkinlik"
					class="btn btn-primary btn-block" value="Kaydet" />
			</div>
		</div>
 		-->
		<!-- TABLO -------------------------------------------- -->
		<div class="panel-body">
			<h6>
				<c:if test="${empty uyeListe}">
					<div class="alert alert-danger">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong>Etkinlik İçin Eğitmen Bulunamadı :(((</strong>
					</div>
				</c:if>
				<table class="table table-hover table-bordered">
					<thead style="background-color: #DFF0D8;">
						<tr>
							<th>Egitmen Id</th>
							<th>Uye Id</th>
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
							<th>Çıkar</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${uyeListe}" var="mus" varStatus="status">
							<tr>
								<th><c:out value="${egitmenListe[status.index].egitmenId}" /></th>
								<th><c:out value="${mus.uyeId}" /></th>
								<th><c:out value="${mus.uyeAdi}" /></th>
								<th><c:out value="${mus.uyeSoyadi}" /></th>
								<th><c:out value="${mus.uyeTelEv}" /></th>
								<th><c:out value="${mus.uyeTelIs}" /></th>
								<th><c:out value="${mus.uyeTelCep}" /></th>
								<th><c:out value="${mus.ePosta}" /></th>
								<th><c:out value="${mus.dogumTarihi}" /></th>
								<th><c:out value="${mus.meslek}" /></th>
								<th><c:out value="${mus.adres}" /></th>
								<th><c:out value="${mus.kayitTarihi}" /></th>
								<c:set var="degisken" value="${mus.aktif}" />
								<c:if test="${degisken == false}">
									<c:set var="degisken" value="Hayır" />
								</c:if>
								<c:if test="${degisken == true}">
									<c:set var="degisken" value="Evet" />
								</c:if>

								<th><c:out value="${degisken}" /></th>
								<th><a
									href="removeEgitmenFromOzelEtkinlik?egitmenId=<c:out value='${egitmenListe[status.index].egitmenId}'/>&etkinlikId=<c:out value='${ozelEtkinlikObject.etkinlikId}'/>">Çıkar</a></th>

							</tr>
						</c:forEach>
						<tr>
							<th><a
								href="listAllUyeForOzelEtkinlikEgitmen?etkinlikId=<c:out value='${ozelEtkinlikObject.etkinlikId}'/>">EKLE</a></th>
							<th><a
								href="listOzelEtkinlik?ozelEtkinlikId=<c:out value='${ozelEtkinlikObject.etkinlikId}'/>">FAALİYETE
									GERİ DÖN</a></th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>

						</tr>

					</tbody>
				</table>
			</h6>

		</div>

		<!-- TABLO -------------------------------------------- -->

	</form:form>


</body>
</html>