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

<script>
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#blah').attr('src', e.target.result).width(100).height(100);
			};

			reader.readAsDataURL(input.files[0]);
		}
	}
</script>

<title>Üye Bilgisi</title>

</head>
<body class="container-fluid">

	<!-- HEADER ------------------------------------------------------------- -->
	<div class="row">
		<div class="col-md-6">
			<img alt="Bootstrap Image Preview"
				src="resources/photos/aktiffelsefe.png" />
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
							data-toggle="dropdown">Üye Bilgileri<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li><a href="newUye">Üye Ekle</a></li>
								<li><a href="listAllUye">Üyeleri Listele / Düzenle</a></li>
								<li class="divider"></li>
								<li><a href="hakkimizda">Hakkımızda</a></li>

							</ul></li>

					</ul>
					<!-- 
					<form class="navbar-form navbar-left" role="search"
						action="searchUye">

						<select class="selectpicker" id="color">
							<option value="id" selected="selected">Id'ye Göre Ara</option>
							<option value="ad">Ada Göre Ara</option>
							<option value="soyad">Soyada Göre Ara</option>
						</select> <input type='text' class="form-control" name='searchAdi'
							id='searchAdi' /> <input type='hidden' class="form-control"
							name='searchTur' id='searchTur' value="ad" />

						<button type="submit" class="btn btn-default">Ara</button>
					</form>
 					-->
				</div>

			</nav>
		</div>
	</div>
	<!-- HEADER ------------------------------------------------------------- -->

	<form:form data-toggle="validator" role="form" id="uyeRegisterForm"
		cssClass="form-horizontal" modelAttribute="uye" method="post"
		enctype="multipart/form-data" action="saveUye">

		<div class="container-fluid">

			<!-- ÜYE FOTO -->

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

			<input type="file" name="file" onchange="readURL(this);"
				title="Üye Fotosu Ekle" class="form-control input-sm" />

			<!-- ÜYE FOTO -->

			<c:set var="degisken" value="${uyeObject.aktif}" />
			<c:if test="${degisken == false}">
				<c:set var="degisken" value="" />
			</c:if>

			<div class="checkbox">
				<label> <form:checkbox path="aktif"
						value="${uyeObject.aktif}" checked="${degisken}" /> <b>Üyelik
						Durumu Aktif/Pasif </b>
				</label>
			</div>
			<br>

			<p class="bg-success">Üyenin İlgili Olduğu Alanlar</p>

			<div class="row">
				<div class="col-md-6">

					<c:set var="degisken" value="${uyeObject.ilg_konferans}" />
					<c:if test="${degisken == false}">
						<c:set var="degisken" value="" />
					</c:if>

					<div class="checkbox">
						<label> <form:checkbox path="ilg_konferans"
								value="${uyeObject.ilg_konferans}" checked="${degisken}" /> <b>Konferans
								ve Seminerler:</b> Etik, Felsefe, Psikoloji, Mitoloji, Sanat, Tarih
						</label>
					</div>

					<c:set var="degisken" value="${uyeObject.ilg_sanat}" />
					<c:if test="${degisken == false}">
						<c:set var="degisken" value="" />
					</c:if>

					<div class="checkbox">
						<label> <form:checkbox path="ilg_sanat"
								value="${uyeObject.ilg_sanat}" checked="${degisken}" /> <b>Sanat
								Atölyesi: </b> Reprodüksiyonlar, Ahşap Atölyesi, Mozaik, Ebru Sanatı
						</label>
					</div>

					<c:set var="degisken" value="${uyeObject.ilg_yardim}" />
					<c:if test="${degisken == false}">
						<c:set var="degisken" value="" />
					</c:if>

					<div class="checkbox">
						<label> <form:checkbox path="ilg_yardim"
								value="${uyeObject.ilg_yardim}" checked="${degisken}" /> <b>Yardım
								Kampanyaları:</b> Çocuklar ve yaşlılar için...
						</label>
					</div>

					<c:set var="degisken" value="${uyeObject.ilg_deprem}" />
					<c:if test="${degisken == false}">
						<c:set var="degisken" value="" />
					</c:if>

					<div class="checkbox">
						<label> <form:checkbox path="ilg_deprem"
								value="${uyeObject.ilg_deprem}" checked="${degisken}" /> <b>Depremde
								arama kurtarma çalışmaları </b>
						</label>
					</div>

					<c:set var="degisken" value="${uyeObject.ilg_uzakdogu}" />
					<c:if test="${degisken == false}">
						<c:set var="degisken" value="" />
					</c:if>

					<div class="checkbox">
						<label> <form:checkbox path="ilg_uzakdogu"
								value="${uyeObject.ilg_uzakdogu}" checked="${degisken}" /> <b>Uzakdoğu
								felsefi savaş sanatı:</b> Tai Chi Chuan
						</label>
					</div>


				</div>
				<div class="col-md-6">

					<c:set var="degisken" value="${uyeObject.ilg_tiyatro}" />
					<c:if test="${degisken == false}">
						<c:set var="degisken" value="" />
					</c:if>

					<div class="checkbox">
						<label> <form:checkbox path="ilg_tiyatro"
								value="${uyeObject.ilg_tiyatro}" checked="${degisken}" /> <b>Tiyatro
								Çalışmaları </b>
						</label>
					</div>

					<c:set var="degisken" value="${uyeObject.ilg_fotograf}" />
					<c:if test="${degisken == false}">
						<c:set var="degisken" value="" />
					</c:if>

					<div class="checkbox">
						<label> <form:checkbox path="ilg_fotograf"
								value="${uyeObject.ilg_fotograf}" checked="${degisken}" /> <b>Fotoğraf:
						</b> Yarışmalar, Sergiler
						</label>
					</div>

					<c:set var="degisken" value="${uyeObject.ilg_geziler}" />
					<c:if test="${degisken == false}">
						<c:set var="degisken" value="" />
					</c:if>

					<div class="checkbox">
						<label> <form:checkbox path="ilg_geziler"
								value="${uyeObject.ilg_geziler}" checked="${degisken}" /> <b>Geziler:
						</b> Doğa Yürüyüşleri, Kültürel Amaçlı Geziler
						</label>
					</div>

					<c:set var="degisken" value="${uyeObject.ilg_muzik}" />
					<c:if test="${degisken == false}">
						<c:set var="degisken" value="" />
					</c:if>

					<div class="checkbox">
						<label> <form:checkbox path="ilg_muzik"
								value="${uyeObject.ilg_muzik}" checked="${degisken}" /> <b>Müzik:
						</b> Konserler ve Koro Çalışmaları
						</label>
					</div>

					<c:set var="degisken" value="${uyeObject.ilg_dans}" />
					<c:if test="${degisken == false}">
						<c:set var="degisken" value="" />
					</c:if>

					<div class="checkbox">
						<label> <form:checkbox path="ilg_dans"
								value="${uyeObject.ilg_dans}" checked="${degisken}" /> <b>Dans
								Seminerleri: </b>
						</label>
					</div>

					<div class="checkbox">
						<form:input type="text" path="ilg_diger" class="form-control"
							value="${uyeObject.ilg_diger}" id="pwd"
							placeholder="Diğer İlgili Alanlar" />
					</div>
				</div>
			</div>

			<p class="bg-success">Etkinliklerimizden Nasıl Haberdar Olundu</p>

			<div class="row">
				<div class="col-md-3">

					<c:set var="degisken" value="${uyeObject.hbr_ePosat}" />
					<c:if test="${degisken == false}">
						<c:set var="degisken" value="" />
					</c:if>

					<div class="checkbox">
						<label> <form:checkbox path="hbr_ePosat"
								value="${uyeObject.hbr_ePosat}" checked="${degisken}" /> Eposta
						</label>
					</div>

					<c:set var="degisken" value="${uyeObject.hbr_webSitesi}" />
					<c:if test="${degisken == false}">
						<c:set var="degisken" value="" />
					</c:if>

					<div class="checkbox">
						<label> <form:checkbox path="hbr_webSitesi"
								value="${uyeObject.hbr_webSitesi}" checked="${degisken}" />
							Aktiffelsefe'nin Web Sayfası
						</label>
					</div>

				</div>
				<div class="col-md-2">

					<c:set var="degisken" value="${uyeObject.hbr_facebook}" />
					<c:if test="${degisken == false}">
						<c:set var="degisken" value="" />
					</c:if>

					<div class="checkbox">
						<label> <form:checkbox path="hbr_facebook"
								value="${uyeObject.hbr_facebook}" checked="${degisken}" />
							Facebook-Twitter
						</label>
					</div>

					<c:set var="degisken" value="${uyeObject.hbr_brosur}" />
					<c:if test="${degisken == false}">
						<c:set var="degisken" value="" />
					</c:if>

					<div class="checkbox">
						<label> <form:checkbox path="hbr_brosur"
								value="${uyeObject.hbr_brosur}" checked="${degisken}" /> El
							Broşürü
						</label>
					</div>

				</div>

				<div class="col-md-2">

					<c:set var="degisken" value="${uyeObject.hbr_afis}" />
					<c:if test="${degisken == false}">
						<c:set var="degisken" value="" />
					</c:if>

					<div class="checkbox">
						<label> <form:checkbox path="hbr_afis"
								value="${uyeObject.hbr_afis}" checked="${degisken}" /> Duvar
							Afişi
						</label>
					</div>

					<c:set var="degisken" value="${uyeObject.hbr_arkadas}" />
					<c:if test="${degisken == false}">
						<c:set var="degisken" value="" />
					</c:if>

					<div class="checkbox">
						<label> <form:checkbox path="hbr_arkadas"
								value="${uyeObject.hbr_arkadas}" checked="${degisken}" />
							Arkadaş Tavsiyesi
						</label>
					</div>
				</div>

				<div class="col-md-2">

					<c:set var="degisken" value="${uyeObject.hbr_sergi}" />
					<c:if test="${degisken == false}">
						<c:set var="degisken" value="" />
					</c:if>

					<div class="checkbox">
						<label> <form:checkbox path="hbr_sergi"
								value="${uyeObject.hbr_sergi}" checked="${degisken}" /> Sergi
							veya Fuar
						</label>
					</div>

					<c:set var="degisken" value="${uyeObject.hbr_digerInternet}" />
					<c:if test="${degisken == false}">
						<c:set var="degisken" value="" />
					</c:if>

					<div class="checkbox">
						<label> <form:checkbox path="hbr_digerInternet"
								value="${uyeObject.hbr_digerInternet}" checked="${degisken}" />
							Diğer İnternet Sayfaları
						</label>
					</div>
				</div>

				<c:set var="degisken" value="${uyeObject.hbr_yaziliBasin}" />
				<c:if test="${degisken == false}">
					<c:set var="degisken" value="" />
				</c:if>

				<div class="col-md-3">
					<div class="checkbox">
						<label> <form:checkbox path="hbr_yaziliBasin"
								value="${uyeObject.hbr_yaziliBasin}" checked="${degisken}" />Yazılı
							Basın
						</label>
					</div>

					<div class="checkbox">

						<form:input type="text" class="form-control" path="hbr_diger"
							value="${uyeObject.hbr_diger}"
							placeholder="Diğer Haberdar Olma Şekli" />

					</div>
				</div>
			</div>

			<p class="bg-success">Özlük Bilgileri</p>

			<div class="row">

				<div class="col-md-4">

					<form:hidden path="uyeId" value="${uyeObject.uyeId}" />

					<fmt:formatDate value="${uyeObject.kayitTarihi}" var="kayitTarihi"
						pattern="dd.MM.yyyy" />

					<form:hidden path="kayitTarihi" value="${kayitTarihi}" />

					<div class="form-group">
						<div class="control-label col-xs-2">
							<form:label path="uyeAdi">Adı</form:label>
						</div>
						<div class="col-xs-10">
							<form:input cssClass="form-control" path="uyeAdi"
								value="${uyeObject.uyeAdi}" />
						</div>
					</div>

					<div class="form-group">
						<div class="control-label col-xs-2">
							<form:label path="uyeSoyadi">Soyadı</form:label>
						</div>
						<div class="col-xs-10">
							<form:input Class="form-control" path="uyeSoyadi"
								value="${uyeObject.uyeSoyadi}" />
						</div>
					</div>
					<div class="form-group">
						<div class="control-label col-xs-2">
							<form:label path="adres">Adres</form:label>
						</div>
						<div class="col-xs-10">
							<form:input cssClass="form-control" path="adres"
								value="${uyeObject.adres}" />
						</div>

					</div>
				</div>
				<div class="col-md-4">

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="uyeTelEv">Tel: (Ev)</form:label>
						</div>
						<div class="col-xs-9">
							<form:input cssClass="form-control" path="uyeTelEv"
								value="${uyeObject.uyeTelEv}" />
						</div>
					</div>

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="uyeTelIs">Tel: (İş)</form:label>
						</div>
						<div class="col-xs-9">
							<form:input cssClass="form-control" path="uyeTelIs"
								value="${uyeObject.uyeTelIs}" />
						</div>
					</div>
					<div class="form-group">



						<div class="control-label col-xs-3">
							<form:label path="dogumTarihi">Doğum Tarihi</form:label>
						</div>
						<fmt:formatDate value="${uyeObject.dogumTarihi}" var="dateString"
							pattern="dd.MM.yyyy" />
						<div class="col-xs-9">

							<p>
								<form:input cssClass="form-control" type="text"
									path="dogumTarihi" id="tarihSec" value="${dateString}" />
							</p>

						</div>

					</div>
				</div>
				<div class="col-md-4">

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="uyeTelCep">Gsm</form:label>
						</div>
						<div class="col-xs-9">
							<form:input cssClass="form-control" path="uyeTelCep"
								value="${uyeObject.uyeTelCep}" />
						</div>
					</div>

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="ePosta">e Posta</form:label>
						</div>
						<div class="col-xs-9">
							<form:input cssClass="form-control" path="ePosta"
								value="${uyeObject.ePosta}" />
						</div>
					</div>

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="meslek">Meslek</form:label>
						</div>
						<div class="col-xs-9">
							<form:input cssClass="form-control" path="meslek"
								value="${uyeObject.meslek}" />
						</div>

					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<input type="submit" id="saveUye" class="btn btn-primary btn-block"
						value="Kaydet" onclick="return submitUyeForm();" />
				</div>
			</div>
		</div>

	</form:form>

	<script type="text/javascript">
		function submitUyeForm() {

			var uyeAdi = $('#uyeAdi').val().trim();
			var uyeSoyadi = $('#uyeSoyadi').val().trim();

			if (uyeAdi.length == 0) {
				alert('Lütfen bir isim giriniz!');
				$('#uyeAdi').focus();
				return false;
			}

			if (uyeSoyadi.length == 0) {
				alert('Lütfen bir soyisim giriniz!');
				$('#uyeSoyadi').focus();
				return false;
			}

			return true;
		};

		$(function() {
			$('#color').change(function() {
				$('#searchTur').val($(this).val());
			}).change(); // Trigger the event
		});
	</script>

</body>
</html>