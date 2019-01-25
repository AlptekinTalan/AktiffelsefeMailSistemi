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

<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script>


<script src="/resources/js/datepicker.js"></script>

<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css"
	rel="stylesheet">

<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>

<link href="./resources/summernote/dist/summernote.css" rel="stylesheet">

<script src="./resources/summernote/dist/summernote.js"></script>


<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css" />

<script type="text/javascript">
	$(function() {
		$('#myTextarea').val('${etkinlikObject.etkinlikMailIcerik}');

	});
</script>

<script>
	$(document).ready(function() {
		$('#myTextarea').summernote({
			lang : 'tr-TR' // default: 'en-US'
		});
	});
</script>


<script type="text/javascript">
	$(function() {
		$('#sube').change(function() {
			$('#subeadi').val($(this).val());
		}).change(); // Trigger the event
	});
</script>
<!-- Bootstrap Date-Picker Plugin -->


<script type="text/javascript">
	$(function() {
		$('#tarihSec').datepicker({ //# ile id üzerinden yaparsanız yanlızca bir input çalışır 
			format : "dd.mm.yyyy" //dd-mm-yyyy
		});
	});
</script>

<c:set var="readonly" value="${etkinlikObject.etkinlikId}" />

<c:if test="${readonly == 0}">

	<script type="text/javascript">
		$(document).ready(function() {

			document.getElementById("testMailBut").disabled = true;
			document.getElementById("testMailAdresi").disabled = true;
		});
		//]]>
	</script>

</c:if>

<title>Etkinlik Giriş</title>
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
							data-toggle="dropdown">Etkinlik Bilgileri<strong
								class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li><a href="newEtkinlik">Etkinlik Ekle</a></li>
								<li><a
									href="searchEtkinlik?searchAdi=Etkinlik Adı&searchTur=ad&searchTarih1=&searchTarih2=">Etkinlik
										Ara</a></li>
								<li><a href="listAllEtkinlik">Etkinlikleri Listele /
										Düzenle</a></li>
								<li class="divider"></li>
							</ul></li>

					</ul>
					<form class="navbar-form navbar-left" role="testMail"
						action="sendTestMail">

						<button type="submit" id="testMailBut" class="btn btn-warning">Test
							Maili Gönder</button>

						<input type='email' class="form-control" name='testMailAdresi'
							placeholder="Test Mail Adresi" id='testMailAdresi' required /> <input
							type='hidden' name='etkinlikId'
							value="${etkinlikObject.etkinlikId}" />

					</form>
				</div>


			</nav>
		</div>
	</div>
	<!-- HEADER ------------------------------------------------------------- -->

	<form:form data-toggle="validator" role="form"
		id="etkinlikRegisterForm" cssClass="form-horizontal"
		modelAttribute="etkinlik" method="post" enctype="multipart/form-data"
		action="saveEtkinlik">


		<p class="bg-success">Etkinlik Bilgileri</p>

		<div class="row">

			<div class="col-md-4">

				<form:hidden path="etkinlikId" value="${etkinlikObject.etkinlikId}" />

				<form:hidden path="etkinlikGonderilmeSayisi"
					value="${etkinlikObject.etkinlikGonderilmeSayisi}" />
				<form:hidden path="etkinlikGonderilememeSayisi"
					value="${etkinlikObject.etkinlikGonderilememeSayisi}" />

				<div class="form-group">
					<div class="control-label col-xs-2">
						<form:label path="etkinlikAdi">Etkinlik Adı</form:label>
					</div>
					<div class="col-xs-10">
						<form:input cssClass="form-control" path="etkinlikAdi"
							value="${etkinlikObject.etkinlikAdi}" />
					</div>

				</div>

			</div>
			<div class="col-md-4">
				<div class="form-group">

					<div class="control-label col-xs-2">
						<form:label path="etkinlikTarihi">Etkinlik Tarihi</form:label>
					</div>
					<fmt:formatDate value="${etkinlikObject.etkinlikTarihi}"
						var="dateString" pattern="dd.MM.yyyy" />
					<div class="col-xs-10">

						<form:input type="text" cssClass="form-control"
							path="etkinlikTarihi" id="tarihSec" value="${dateString}" required="required"/>

					</div>

				</div>
			</div>

			<!-- Modal -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">UYARI !</h4>
						</div>
						<div class="modal-body">"Mail Gönder" seçeneği seçilip
							kaydedilen etkinlik tekrar düzenlenemez. "Mail Gönder" seçeneği
							aktif olan etkinliklerin duyurulmasını istemiyorsanız etkinliği
							listeleyip iptal edebilirsiniz.</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Tamam</button>
						</div>
					</div>
				</div>
			</div>

			<div class="col-md-2">
				<c:set var="degisken" value="${etkinlikObject.etkinlikBildir}" />
				<c:if test="${degisken == false}">
					<c:set var="degisken" value="" />
				</c:if>

				<div class="checkbox">
					<label> <form:checkbox path="etkinlikBildir"
							id="mailgonder" value="${etkinlikObject.etkinlikBildir}"
							checked="${degisken}" /> <b>Mail Gönder </b>
					</label>
				</div>

			</div>
		</div>
		<div class="row">

			<div class="col-md-4">
				<div class="form-group">

					<div class="control-label col-xs-2">
						<form:label path="etkinlikSaati">Etkinlik Adresi</form:label>
					</div>
					<div class="col-xs-10">
						<form:input type="text" cssClass="form-control"
							path="etkinlikAdresi" value="${etkinlikObject.etkinlikAdresi}" />
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">

					<div class="control-label col-xs-2">
						<form:label path="etkinlikSaati">Etkinlik Saati</form:label>
					</div>
					<div class="col-xs-10">
						<form:input type="text" cssClass="form-control"
							path="etkinlikSaati" value="${etkinlikObject.etkinlikSaati}" />
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
		</div>

		<p class="bg-success">Mail Gönderim Bilgileri</p>

		<div class="container">
			<div class="progress">
				<div class="progress-bar progress-bar-striped active"
					role="progressbar" aria-valuenow="40" aria-valuemin="0"
					title="%${gonderilmeOrani} Gönderilen: ${etkinlikObject.etkinlikGonderilmeSayisi}"
					aria-valuemax="100" style="width: ${gonderilmeOrani}%">%${gonderilmeOrani}
					Gönderilen: ${etkinlikObject.etkinlikGonderilmeSayisi}</div>
			</div>
			<jsp:useBean id="now" class="java.util.Date" />
			<c:if test="${now > etkinlikObject.etkinlikTarihi}">
				<div class="progress">
					<div
						class="progress-bar progress-bar-danger progress-bar-striped active"
						role="progressbar" aria-valuenow="70" aria-valuemin="0"
						title="%${gonderilememeOrani} Gönderilmeyen: ${etkinlikObject.etkinlikGonderilememeSayisi}"
						aria-valuemax="100" style="width: ${gonderilememeOrani}%">%${gonderilememeOrani}
						Gönderilmeyen: ${etkinlikObject.etkinlikGonderilememeSayisi}</div>
				</div>
			</c:if>

		</div>

		<p class="bg-success">Etkinlik Mail İçerik</p>

		<div>
			<p>
				UYARI !!! <br>Microsoft word veya benzer zengin metin içerik
				düzenleyici programlardan kopyalama yaparken, kopyaladığınız
				metinlerin kaybolmaması için, ctrl+shift+V ile ya da farenizin sağ
				tuşuna basıp düz metin olarak yapıştır seçeneğini kullanarak
				kopyalayınız.
			</p>
		</div>

		<div id="sample">

			<div class="row">
				<div class="col-md-12">

					<div class="col-xs-12">
						<form:textarea id="myTextarea" name="myTextarea" type="text"
							rows="10" cssClass="form-control" path="etkinlikMailIcerik" />

					</div>
				</div>
			</div>

		</div>

		<p class="bg-success"></p>

		<div class="row">
			<div class="col-md-12">
				<input type="submit" id="saveEtkinlik"
					class="btn btn-primary btn-block" value="Kaydet" />
			</div>
		</div>

	</form:form>
</body>
</html>