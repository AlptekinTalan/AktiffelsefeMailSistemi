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

<script type="text/javascript">
	$(function() {
		$('#ders').change(function() {
			$('#yoklamaDersAdi').val($(this).val());
			$('#yoklamaAdi').val($(this).val());
		}).change(); // Trigger the event
	});
</script>

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
								<li><a href="listAllOzelEtkinlik">Faaliyetleri Listele
										/ Düzenle</a></li>
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
		enctype="multipart/form-data" action="saveYoklama">

		<p class="bg-success">Yoklama Oluştur</p>

		<div class="row">

			<div class="col-md-4">

				<form:hidden id="yoklamaId" name="yoklamaId" path="yoklamaId"
					value="${yoklamaObject.yoklamaId}" />

				<form:hidden path="ozeletkinlik.etkinlikId" id="etkinlikId"
					name="etkinlikId" value="${ozelEtkinlikObject.etkinlikId}" />

				<div class="form-group">
					<div class="control-label col-xs-2">
						<form:label path="yoklamaAdi">Yoklama Adı</form:label>
					</div>
					<div class="col-xs-10">
						<form:input cssClass="form-control" path="yoklamaAdi"
							id="yoklamaAdi" value="${yoklamaObject.yoklamaAdi}" />
					</div>

				</div>

			</div>
			<div class="col-md-4">
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
			<div class="col-md-4">
				<div class="form-group">

					<div class="control-label col-xs-2">
						<label>Ders Seç</label>
					</div>
					<div class="col-xs-8">
						<c:set var="degisken1" value="${yoklamaObject.yoklamaDersAdi}" />
						<c:if test="${degisken1 == 'Etik'}">
							<c:set var="degisken1" value="selected" />
						</c:if>
						<c:set var="degisken2" value="${yoklamaObject.yoklamaDersAdi}" />
						<c:if test="${degisken2 == 'Bhagavad Gita-1'}">
							<c:set var="degisken2" value="selected" />
						</c:if>
						<c:set var="degisken3" value="${yoklamaObject.yoklamaDersAdi}" />
						<c:if test="${degisken3 == 'Bhagavad Gita-2'}">
							<c:set var="degisken3" value="selected" />
						</c:if>
						<c:set var="degisken4" value="${yoklamaObject.yoklamaDersAdi}" />
						<c:if test="${degisken4 == 'Bhagavad Gita-3'}">
							<c:set var="degisken4" value="selected" />
						</c:if>
						<c:set var="degisken5" value="${yoklamaObject.yoklamaDersAdi}" />
						<c:if test="${degisken5 == 'Sessizliğin Sesi-1'}">
							<c:set var="degisken5" value="selected" />
						</c:if>
						<c:set var="degisken6" value="${yoklamaObject.yoklamaDersAdi}" />
						<c:if test="${degisken6 == 'Sessizliğin Sesi-2'}">
							<c:set var="degisken6" value="selected" />
						</c:if>
						<c:set var="degisken7" value="${yoklamaObject.yoklamaDersAdi}" />
						<c:if test="${degisken7 == 'Buddha-1'}">
							<c:set var="degisken7" value="selected" />
						</c:if>
						<c:set var="degisken8" value="${yoklamaObject.yoklamaDersAdi}" />
						<c:if test="${degisken8 == 'Buddha-2'}">
							<c:set var="degisken8" value="selected" />
						</c:if>
						<c:set var="degisken9" value="${yoklamaObject.yoklamaDersAdi}" />
						<c:if test="${degisken9 == 'YYT'}">
							<c:set var="degisken9" value="selected" />
						</c:if>
						<c:set var="degisken10" value="${yoklamaObject.yoklamaDersAdi}" />
						<c:if test="${degisken10 == 'YYT'}">
							<c:set var="degisken10" value="selected" />
						</c:if>
						<c:set var="degisken11" value="${yoklamaObject.yoklamaDersAdi}" />
						<c:if test="${degisken11 == 'YYT'}">
							<c:set var="degisken11" value="selected" />
						</c:if>
						<c:set var="degisken12" value="${yoklamaObject.yoklamaDersAdi}" />
						<c:if test="${degisken12 == 'Etüt-Kant'}">
							<c:set var="degisken12" value="selected" />
						</c:if>
						<c:set var="degisken13" value="${yoklamaObject.yoklamaDersAdi}" />
						<c:if test="${degisken13 == 'Bitmeyen İlerleme Mitosu'}">
							<c:set var="degisken13" value="selected" />
						</c:if>
						<c:set var="degisken14" value="${yoklamaObject.yoklamaDersAdi}" />
						<c:if test="${degisken14 == 'Sosyopolitiğe Giriş'}">
							<c:set var="degisken14" value="selected" />
						</c:if>
						<c:set var="degisken15" value="${yoklamaObject.yoklamaDersAdi}" />
						<c:if test="${degisken15 == 'Birey-Toplum-Devlet'}">
							<c:set var="degisken15" value="selected" />
						</c:if>
						<c:set var="degisken16" value="${yoklamaObject.yoklamaDersAdi}" />
						<c:if test="${degisken16 == 'Toplumda Roller'}">
							<c:set var="degisken16" value="selected" />
						</c:if>
						<c:set var="degisken17" value="${yoklamaObject.yoklamaDersAdi}" />
						<c:if test="${degisken17 == 'Devlet-1'}">
							<c:set var="degisken17" value="selected" />
						</c:if>
						<c:set var="degisken18" value="${yoklamaObject.yoklamaDersAdi}" />
						<c:if test="${degisken18 == 'Devlet-2'}">
							<c:set var="degisken18" value="selected" />
						</c:if>
						<c:set var="degisken19" value="${yoklamaObject.yoklamaDersAdi}" />
						<c:if test="${degisken19 == 'Sosyopolitik Formlar'}">
							<c:set var="degisken19" value="selected" />
						</c:if>
						<c:set var="degisken20" value="${yoklamaObject.yoklamaDersAdi}" />
						<c:if test="${degisken20 == 'Tarih Felsefesi-1'}">
							<c:set var="degisken20" value="selected" />
						</c:if>
						<c:set var="degisken21" value="${yoklamaObject.yoklamaDersAdi}" />
						<c:if test="${degisken21 == 'Tarih Felsefesi-2'}">
							<c:set var="degisken21" value="selected" />
						</c:if>
						<c:set var="degisken22" value="${yoklamaObject.yoklamaDersAdi}" />
						<c:if test="${degisken22 == 'Tarih Felsefesi-3'}">
							<c:set var="degisken22" value="selected" />
						</c:if>
						<c:set var="degisken23" value="${yoklamaObject.yoklamaDersAdi}" />
						<c:if test="${degisken23 == 'Özet'}">
							<c:set var="degisken23" value="selected" />
						</c:if>
						<c:set var="degisken24" value="${yoklamaObject.yoklamaDersAdi}" />
						<c:if test="${degisken24 == 'Sınav'}">
							<c:set var="degisken24" value="selected" />
						</c:if>
						<c:set var="degisken25" value="${yoklamaObject.yoklamaDersAdi}" />
						<c:if test="${degisken25 == 'Psikoloji Pratikleri'}">
							<c:set var="degisken25" value="selected" />
						</c:if>
						<select class="form-control" id="ders">
							<option value=""></option>
							<option value="Etik" ${degisken1}>Etik</option>
							<option value="Bhagavad Gita-1" ${degisken2}>Bhagavad
								Gita-1</option>
							<option value="Bhagavad Gita-2" ${degisken3}>Bhagavad
								Gita-2</option>
							<option value="Bhagavad Gita-3" ${degisken4}>Bhagavad
								Gita-3</option>
							<option value="Sessizliğin Sesi-1" ${degisken5}>Sessizliğin
								Sesi-1</option>
							<option value="Sessizliğin Sesi-2" ${degisken6}>Sessizliğin
								Sesi-2</option>
							<option value="Buddha-1" ${degisken7}>Buddha-1</option>
							<option value="Buddha-2" ${degisken8}>Buddha-2</option>
							<option value="Aristoteles" ${degisken9}>Aristoteles</option>
							<option value="Plotinus" ${degisken10}>Plotinus</option>
							<option value="YYT" ${degisken11}>YYT</option>
							<option value="Etüt-Kant" ${degisken12}>Etüt-Kant</option>
							<option value="Bitmeyen İlerleme Mitosu" ${degisken13}>Bitmeyen
								İlerleme Mitosu</option>
							<option value="Sosyopolitiğe Giriş" ${degisken14}>Sosyopolitiğe
								Giriş</option>
							<option value="Birey-Toplum-Devlet" ${degisken15}>Birey-Toplum-Devlet</option>
							<option value="Toplumda Roller" ${degisken16}>Toplumda
								Roller</option>
							<option value="Devlet-1" ${degisken17}>Devlet-1</option>
							<option value="Devlet-2" ${degisken18}>Devlet-2</option>
							<option value="Sosyopolitik Formlar" ${degisken19}>Sosyopolitik
								Formlar</option>
							<option value="Tarih Felsefesi-1" ${degisken20}>Tarih
								Felsefesi-1</option>
							<option value="Tarih Felsefesi-2" ${degisken21}>Tarih
								Felsefesi-2</option>
							<option value="Tarih Felsefesi-3" ${degisken22}>Tarih
								Felsefesi-3</option>
							<option value="Özet" ${degisken23}>Özet</option>
							<option value="Sınav" ${degisken24}>Sınav</option
							<option value="Psikoloji Pratikleri" ${degisken25}>Psikoloji Pratikleri</option>
							>

						</select>
						<form:input type='hidden' class="form-control"
							name='yoklamaDersAdi' path="yoklamaDersAdi"
							id='yoklamaDersAdi' value="${yoklamaObject.yoklamaDersAdi}" />
					</div>


				</div>

			</div>

		</div>


		<c:if test="${not empty katilimcilarObject}">

			<c:forEach items="${katilimcilarObject}" var="katlimci"
				varStatus="stat">

				<tr>

					<div class="col-md-8">

						<c:set var="diyez" value="#" />
						<c:set var="tik" value="tik" />
						<form:input cssClass="form-control" path=""
							value="${katlimci.uye.uyeAdi} ${katlimci.uye.uyeSoyadi}"
							readonly="true" />
						<!-- 
							<form:input cssClass="form-control" path=""
								value="${katlimci.uye.uyeId}" />
 							-->

					</div>

					<div class="col-md-4">

						<label> <input type="checkbox" id="${tik}${stat.index}"
							name="${tik}${stat.index}" /> Var
						</label>

					</div>

				</tr>

				<div class="row"></div>

			</c:forEach>

		</c:if>

		<c:if test="${not empty devKatilimcilarObject}">

			<c:forEach items="${devKatilimcilarObject}" var="devKatlimci"
				varStatus="stat">

				<tr>

					<div class="col-md-8">

						<c:set var="diyez" value="#" />

						<c:set var="tik" value="tik" />

						<form:input cssClass="form-control" path=""
							value="${devKatlimci.uye.uyeAdi}" readonly="true" />

						<!-- 
							<form:input cssClass="form-control" path=""
								value="${devKatlimci.uye.uyeId}" />
 							-->
					</div>

					<div class="col-md-4">

						<c:set var="degisken" value="${devKatlimci.var}" />
						<c:if test="${degisken == false}">
							<label> <input type="checkbox" id="${tik}${stat.index}"
								name="${tik}${stat.index}" /> Var
							</label>
						</c:if>
						<c:if test="${degisken == true}">
							<label> <input type="checkbox" checked="${degisken}"
								id="${tik}${stat.index}" name="${tik}${stat.index}" /> Var
							</label>
						</c:if>

					</div>

				</tr>
				<div class="row"></div>


			</c:forEach>

		</c:if>


		<p class="bg-success"></p>

		<div class="row">
			<div class="col-md-12">
				<input type="submit" id="saveYoklama"
					class="btn btn-primary btn-block" value="Kaydet" />
			</div>
		</div>

	</form:form>


</body>
</html>