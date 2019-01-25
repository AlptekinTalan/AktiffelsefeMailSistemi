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
	$(document).ready(
			function() {
				var table = $('#example').DataTable({
					lengthChange : true,
					"pageLength": 50,
					buttons : [ 'copy', 'excel', 'pdf', 'colvis' ]
				});

				table.buttons().container().appendTo(
						'#example_wrapper .col-sm-6:eq(0)');
			});
</script>

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.16/css/dataTables.bootstrap.min.css"
	rel="stylesheet" />

<link
	href="https://cdn.datatables.net/buttons/1.4.2/css/buttons.bootstrap.min.css" />

<script src="//code.jquery.com/jquery-1.12.4.js"></script>
<script
	src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js"></script>

<script
	src="https://cdn.datatables.net/buttons/1.4.2/js/dataTables.buttons.min.js"></script>
<script
	src="https://cdn.datatables.net/buttons/1.4.2/js/buttons.bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/pdfmake.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/vfs_fonts.js"></script>
<script
	src="https://cdn.datatables.net/buttons/1.4.2/js/buttons.html5.min.js"></script>
<script
	src="https://cdn.datatables.net/buttons/1.4.2/js/buttons.print.min.js"></script>

<script
	src="https://cdn.datatables.net/buttons/1.4.2/js/buttons.colVis.min.js"></script>

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
				<c:if test="${fn:length(uyariEklenemezListe) > 0}">
					<div class="alert alert-danger">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong>Etkinliğe katılımcı eklenemez, seçilen katılımcı
							daha önce etkinliğe eklenmiş...</strong>
					</div>
				</c:if>
				<c:if test="${fn:length(uyariListe) > 0}">
					<div class="alert alert-danger">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong>Etkinlikden ilgili katılımcı çıkartılamaz, daha
							önce ilgili etkinlik için yoklama girilmiş...</strong>
					</div>
				</c:if>
				<c:if test="${empty uyeListe}">
					<div class="alert alert-danger">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong>Etkinlik İçin Katılımcı Bulunamadı :(((</strong>
					</div>
				</c:if>
				<table id="example"
					class="table table-striped table-bordered table-hover"
					cellspacing="0" width="100%">
					<thead style="background-color: #DFF0D8;">
						<tr>
							<th>Katilimci Id</th>
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
								<th><c:out
										value="${katilimciListe[status.index].katilimciId}" /></th>
								<th><c:out value="${mus.uyeId}" /></th>
								<th><c:out value="${mus.uyeAdi}" /></th>
								<th><c:out value="${mus.uyeSoyadi}" /></th>
								<th><c:out value="${mus.uyeTelEv}" /></th>
								<th><c:out value="${mus.uyeTelIs}" /></th>
								<th><c:out value="${mus.uyeTelCep}" /></th>
								<th><c:out value="${mus.ePosta}" /></th>
								<th><fmt:formatDate value="${mus.dogumTarihi}"
										pattern="dd.MM.yyyy" /></th>
								<th><c:out value="${mus.meslek}" /></th>
								<th><c:out value="${mus.adres}" /></th>
								<th><fmt:formatDate value="${mus.kayitTarihi}"
										pattern="dd.MM.yyyy" /></th>
								<c:set var="degisken" value="${mus.aktif}" />
								<c:if test="${degisken == false}">
									<c:set var="degisken" value="Hayır" />
								</c:if>
								<c:if test="${degisken == true}">
									<c:set var="degisken" value="Evet" />
								</c:if>

								<th><c:out value="${degisken}" /></th>
								<th><a
									href="removeKatilimciFromOzelEtkinlik?katilimciId=<c:out value='${katilimciListe[status.index].katilimciId}'/>&etkinlikId=<c:out value='${ozelEtkinlikObject.etkinlikId}'/>">Çıkar</a></th>

							</tr>
						</c:forEach>
						<tr>
							<th><a
								href="listAllUyeForOzelEtkinlikKatilimci?etkinlikId=<c:out value='${ozelEtkinlikObject.etkinlikId}'/>">EKLE</a></th>
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