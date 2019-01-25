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
	$(document).ready(function() {

		$('#tarihSec').datepicker({ //# ile id üzerinden yaparsanız yanlızca bir input çalışır 
			format : "dd.mm.yyyy" //dd-mm-yyyy
		});

	});
</script>

<script type="text/javascript">
	$(function() {
		$('#seviye').change(function() {
			$('#etkinlikSeviye').val($(this).val());
		}).change(); // Trigger the event
	});
</script>

<script type="text/javascript">
	$(function() {
		$('#tsEtkinlikTurleri').change(function() {
			$('#tsEtkinlikTuru').val($(this).val());
		}).change(); // Trigger the event
	});
</script>

<script type="text/javascript">
	$(function() {
		$('#ders').change(function() {
			$('#etkinlikDersAdi').val($(this).val());
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


<script type="text/javascript">
	$(function() {
		$('#myTextarea').val('${ozelEtkinlikObject.etkinlikMailIcerik}');

	});
</script>

<script>
	$(document).ready(function() {
		$('#myTextarea').summernote({
			lang : 'tr-TR' // default: 'en-US'
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

					<sec:authorize access="hasRole('ROLE_ADMIN')">

						<ul class="nav navbar-nav">
							<li class="dropdown"><a href="/" class="dropdown-toggle"
								data-toggle="dropdown">Katılımcı Bilgileri<strong
									class="caret"></strong></a>
								<ul class="dropdown-menu">
									<li><a href="newUye">Katılımcı Ekle</a></li>
									<li><a href="listAllUye?uyeId=0">Katılımcı Listele /
											Düzenle</a></li>
									<li class="divider"></li>

								</ul></li>
						</ul>

						<ul class="nav navbar-nav">
							<li class="dropdown"><a href="/" class="dropdown-toggle"
								data-toggle="dropdown">Faaliyet Bilgileri<strong
									class="caret"></strong></a>
								<ul class="dropdown-menu">
									<li><a
										href="newOzelEtkinlik?faaliyetTur=Halkla
										 İlişkiler Faaliyeti">Halkla
											İlişkiler Faaliyeti Ekle</a></li>
									<li><a
										href="newOzelEtkinlik?faaliyetTur=Temel
										 Seminer Faaliyeti">Temel
											Seminer Faaliyeti Ekle</a></li>
									<li><a
										href="newOzelEtkinlik?faaliyetTur=Üye
										 Faaliyeti">Üye
											Faaliyeti Ekle</a></li>
									<li><a
										href="newOzelEtkinlik?faaliyetTur=Diğer
										 Faaliyet">Diğer
											Faaliyet Ekle</a></li>
									<li><a href="listAllOzelEtkinlik">Faaliyetleri Listele
											/ Düzenle</a></li>
									<li class="divider"></li>
								</ul></li>

						</ul>

						<ul class="nav navbar-nav">
							<li class="dropdown"><a href="/" class="dropdown-toggle"
								data-toggle="dropdown">Sube Bilgileri<strong class="caret"></strong></a>
								<ul class="dropdown-menu">
									<li><a href="newSube">Sube Ekle</a></li>
									<li><a href="listAllSube">Subeleri Listele / Düzenle</a></li>
									<li class="divider"></li>
								</ul></li>

						</ul>

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
									<li><a href="newTopluAbone">Toplu Abone Ekle</a></li>

								</ul></li>

						</ul>

					</sec:authorize>

					<sec:authorize
						access="hasAnyRole('ROLE_ISTANBULBAKIRKOY',
							'ROLE_ANKARAMERKEZ',
							'ROLE_ANKARABATIKENT',
							'ROLE_ANTALYA',
							'ROLE_ADANA',
							'ROLE_KUZEYADANA',
							'ROLE_AYDIN',
							'ROLE_BURSA',
							'ROLE_DENİZLİ',
							'ROLE_ESKİŞEHİR',
							'ROLE_ISTANBULFATİH',
							'ROLE_ISTANBULKADIKÖY',
							'ROLE_ISTANBULLEVENT',
							'ROLE_ISTANBULMALTEPE',
							'ROLE_ISTANBULŞİŞLİ',
							'ROLE_ISTANBULÜSKÜDAR',
							'ROLE_İZMİRALSANCAK',
							'ROLE_İZMİRBORNOVA',
							'ROLE_İZMİRBUCA',
							'ROLE_İZMİRGÜZELYALI',
							'ROLE_İZMİRKARŞIYAKA',
							'ROLE_İZMİT',
							'ROLE_MANİSA',
							'ROLE_MERSİN')">

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
									<li><a href="newTopluAbone">Toplu Abone Ekle</a></li>

								</ul></li>

						</ul>

					</sec:authorize>

					<sec:authorize access="hasRole('ROLE_YOKLAMABAKIRKOY')">

						<ul class="nav navbar-nav">
							<li class="dropdown"><a href="/" class="dropdown-toggle"
								data-toggle="dropdown">Katılımcı Bilgileri<strong
									class="caret"></strong></a>
								<ul class="dropdown-menu">
									<li><a href="newUye">Katılımcı Ekle</a></li>
									<li><a href="listAllUye?uyeId=0">Katılımcı Listele /
											Düzenle</a></li>
									<li class="divider"></li>

								</ul></li>
						</ul>

						<ul class="nav navbar-nav">
							<li class="dropdown"><a href="/" class="dropdown-toggle"
								data-toggle="dropdown">Faaliyet Bilgileri<strong
									class="caret"></strong></a>
								<ul class="dropdown-menu">
									<li><a
										href="newOzelEtkinlik?faaliyetTur=Halkla
										 İlişkiler Faaliyeti">Halkla
											İlişkiler Faaliyeti Ekle</a></li>
									<li><a
										href="newOzelEtkinlik?faaliyetTur=Temel
										 Seminer Faaliyeti">Temel
											Seminer Faaliyeti Ekle</a></li>
									<li><a
										href="newOzelEtkinlik?faaliyetTur=Üye
										 Faaliyeti">Üye
											Faaliyeti Ekle</a></li>
									<li><a
										href="newOzelEtkinlik?faaliyetTur=Diğer
										 Faaliyet">Diğer
											Faaliyet Ekle</a></li>
									<li><a href="listAllOzelEtkinlik">Faaliyetleri Listele
											/ Düzenle</a></li>
									<li class="divider"></li>
								</ul></li>

						</ul>

					</sec:authorize>

					<sec:authorize access="hasRole('ROLE_HALKLAILISKILER')">

						<ul class="nav navbar-nav">
							<li class="dropdown"><a href="/" class="dropdown-toggle"
								data-toggle="dropdown">Katılımcı Bilgileri<strong
									class="caret"></strong></a>
								<ul class="dropdown-menu">
									<li><a href="newUye">Katılımcı Ekle</a></li>
									<li><a href="listAllUye?uyeId=0">Katılımcı Listele /
											Düzenle</a></li>
									<li class="divider"></li>

								</ul></li>
						</ul>

						<ul class="nav navbar-nav">
							<li class="dropdown"><a href="/" class="dropdown-toggle"
								data-toggle="dropdown">Faaliyet Bilgileri<strong
									class="caret"></strong></a>
								<ul class="dropdown-menu">
									<li><a
										href="newOzelEtkinlik?faaliyetTur=Halkla
										 İlişkiler Faaliyeti">Halkla
											İlişkiler Faaliyeti Ekle</a></li>
									<li><a href="listAllOzelEtkinlik">Faaliyetleri Listele
											/ Düzenle</a></li>
									<li class="divider"></li>
								</ul></li>

						</ul>

					</sec:authorize>

					<sec:authorize access="hasRole('ROLE_TEMELSEMINER')">

						<ul class="nav navbar-nav">
							<li class="dropdown"><a href="/" class="dropdown-toggle"
								data-toggle="dropdown">Katılımcı Bilgileri<strong
									class="caret"></strong></a>
								<ul class="dropdown-menu">
									<li><a href="newUye">Katılımcı Ekle</a></li>
									<li><a href="listAllUye?uyeId=0">Katılımcı Listele /
											Düzenle</a></li>
									<li class="divider"></li>

								</ul></li>
						</ul>

						<ul class="nav navbar-nav">
							<li class="dropdown"><a href="/" class="dropdown-toggle"
								data-toggle="dropdown">Faaliyet Bilgileri<strong
									class="caret"></strong></a>
								<ul class="dropdown-menu">
									<li><a
										href="newOzelEtkinlik?faaliyetTur=Temel
										 Seminer Faaliyeti">Temel
											Seminer Faaliyeti Ekle</a></li>
									<li><a href="listAllOzelEtkinlik">Faaliyetleri Listele
											/ Düzenle</a></li>
									<li class="divider"></li>
								</ul></li>

						</ul>

					</sec:authorize>

					<sec:authorize access="hasRole('ROLE_UYEGRUPLARI')">

						<ul class="nav navbar-nav">
							<li class="dropdown"><a href="/" class="dropdown-toggle"
								data-toggle="dropdown">Katılımcı Bilgileri<strong
									class="caret"></strong></a>
								<ul class="dropdown-menu">
									<li><a href="newUye">Katılımcı Ekle</a></li>
									<li><a href="listAllUye?uyeId=0">Katılımcı Listele /
											Düzenle</a></li>
									<li class="divider"></li>

								</ul></li>
						</ul>

						<ul class="nav navbar-nav">
							<li class="dropdown"><a href="/" class="dropdown-toggle"
								data-toggle="dropdown">Faaliyet Bilgileri<strong
									class="caret"></strong></a>
								<ul class="dropdown-menu">
									<li><a
										href="newOzelEtkinlik?faaliyetTur=Üye
										 Faaliyeti">Üye
											Faaliyeti Ekle</a></li>
									<li><a
										href="newOzelEtkinlik?faaliyetTur=Diğer
										 Faaliyet">Diğer
											Faaliyet Ekle</a></li>
									<li><a href="listAllOzelEtkinlik">Faaliyetleri Listele
											/ Düzenle</a></li>
									<li class="divider"></li>
								</ul></li>

						</ul>

					</sec:authorize>

					<ul class="nav navbar-nav navbar-right">

						<li><a href="hakkimizda">Hakkımızda</a></li>

						<c:if test="${pageContext.request.userPrincipal.name != null}">
							<li><a href="<c:url value="/j_spring_security_logout" />">${pageContext.request.userPrincipal.name}
									| Çıkış</a></li>
						</c:if>

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
						<form:label path="etkinlikAdi">Etkinlik Adı</form:label>
					</div>
					<div class="col-xs-10">
						<form:input cssClass="form-control" path="etkinlikAdi"
							value="${ozelEtkinlikObject.etkinlikAdi}" />
					</div>

				</div>

			</div>
			<div class="col-md-4">
				<div class="form-group">

					<div class="control-label col-xs-2">
						<form:label path="etkinlikTarihi">Etkinlik Tarihi</form:label>
					</div>
					<fmt:formatDate value="${ozelEtkinlikObject.etkinlikTarihi}"
						var="dateString" pattern="dd.MM.yyyy" />
					<div class="col-xs-10">

						<form:input type="text" cssClass="form-control"
							path="etkinlikTarihi" id="tarihSec" value="${dateString}" />

						<!--
						<form:input type='Date' class="form-control" path="etkinlikTarihi"
							id='tarihSec' value="${ozelEtkinlikObject.etkinlikTarihi}" />
							 -->
					</div>
				</div>
			</div>

			<div class="col-md-2">

				<div class="form-group">
					<div class="control-label col-xs-4">
						<label>Seviye</label>
					</div>
					<div class="col-xs-6">

						<c:set var="degisken1"
							value="${ozelEtkinlikObject.etkinlikSeviye}" />
						<c:if test="${degisken1 == 1}">
							<c:set var="degisken1" value="selected" />
						</c:if>
						<c:set var="degisken2"
							value="${ozelEtkinlikObject.etkinlikSeviye}" />
						<c:if test="${degisken2 == 2}">
							<c:set var="degisken2" value="selected" />
						</c:if>
						<c:set var="degisken3"
							value="${ozelEtkinlikObject.etkinlikSeviye}" />
						<c:if test="${degisken3 == 3}">
							<c:set var="degisken3" value="selected" />
						</c:if>
						<c:set var="degisken4"
							value="${ozelEtkinlikObject.etkinlikSeviye}" />
						<c:if test="${degisken4 == 4}">
							<c:set var="degisken4" value="selected" />
						</c:if>
						<c:set var="degisken5"
							value="${ozelEtkinlikObject.etkinlikSeviye}" />
						<c:if test="${degisken5 == 5}">
							<c:set var="degisken5" value="selected" />
						</c:if>
						<c:set var="degisken6"
							value="${ozelEtkinlikObject.etkinlikSeviye}" />
						<c:if test="${degisken6 == 6}">
							<c:set var="degisken6" value="selected" />
						</c:if>
						<c:set var="degisken7"
							value="${ozelEtkinlikObject.etkinlikSeviye}" />
						<c:if test="${degisken7 == 7}">
							<c:set var="degisken7" value="selected" />
						</c:if>
						<c:set var="degisken8"
							value="${ozelEtkinlikObject.etkinlikSeviye}" />
						<c:if test="${degisken8 == 8}">
							<c:set var="degisken8" value="selected" />
						</c:if>
						<c:set var="degisken9"
							value="${ozelEtkinlikObject.etkinlikSeviye}" />
						<c:if test="${degisken9 == 9}">
							<c:set var="degisken9" value="selected" />
						</c:if>
						<select class="form-control" id="seviye">
							<option value="1" ${degisken1}>1</option>
							<option value="2" ${degisken2}>2</option>
							<option value="3" ${degisken3}>3</option>
							<option value="4" ${degisken4}>4</option>
							<option value="5" ${degisken5}>5</option>
							<option value="6" ${degisken6}>6</option>
							<option value="7" ${degisken7}>7</option>
							<option value="8" ${degisken8}>8</option>
							<option value="9" ${degisken9}>9</option>

						</select>
						<form:input type='hidden' class="form-control"
							name='etkinlikSeviye' path="etkinlikSeviye" id='etkinlikSeviye'
							value="${ozelEtkinlikObject.etkinlikSeviye}" />
					</div>
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
							path="etkinlikAdresi"
							value="${ozelEtkinlikObject.etkinlikAdresi}" />
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
							path="etkinlikSaati" value="${ozelEtkinlikObject.etkinlikSaati}" />
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
		<div class="row">
			<div class="col-md-4">
				<div class="form-group">

					<div class="control-label col-xs-2">
						<label>Faaliyet Türü</label>
					</div>
					<div class="col-xs-8">
						<form:input type="text" cssClass="form-control"
							path="etkinlikTuru" readonly="true"
							value="${param['faaliyetTur']}" />
					</div>


				</div>

			</div>

			<div class="col-md-4">
				<div class="form-group">

					<div class="control-label col-xs-2">
						<label>TS Faatliyet Türü</label>
					</div>
					<div class="col-xs-9">
						<c:set var="degisken1"
							value="${ozelEtkinlikObject.tsEtkinlikTuru}" />
						<c:if test="${degisken1 == 'A'}">
							<c:set var="degisken1" value="selected" />
						</c:if>
						<c:set var="degisken2"
							value="${ozelEtkinlikObject.tsEtkinlikTuru}" />
						<c:if test="${degisken2 == 'B'}">
							<c:set var="degisken2" value="selected" />
						</c:if>
						<c:set var="degisken3"
							value="${ozelEtkinlikObject.tsEtkinlikTuru}" />
						<c:if test="${degisken3 == 'C'}">
							<c:set var="degisken3" value="selected" />
						</c:if>
						<c:set var="degisken4"
							value="${ozelEtkinlikObject.tsEtkinlikTuru}" />
						<c:if test="${degisken4 == 'D'}">
							<c:set var="degisken4" value="selected" />
						</c:if>
						<c:set var="degisken5"
							value="${ozelEtkinlikObject.tsEtkinlikTuru}" />
						<c:if test="${degisken5 == 'E'}">
							<c:set var="degisken5" value="selected" />
						</c:if>
						<c:set var="degisken6"
							value="${ozelEtkinlikObject.tsEtkinlikTuru}" />
						<c:if test="${degisken6 == 'F'}">
							<c:set var="degisken6" value="selected" />
						</c:if>
						<c:set var="degisken7"
							value="${ozelEtkinlikObject.tsEtkinlikTuru}" />
						<c:if test="${degisken7 == 'G'}">
							<c:set var="degisken7" value="selected" />
						</c:if>
						<select class="form-control" id="tsEtkinlikTurleri">
							<option value=""></option>
							<option value="A" ${degisken1}>Entegrasyon etkinlikleri.
								(Felsefi sohbet, etüt dersi, psikoloji pratikleri, şiir okuma
								akşamı, parti...)</option>
							<option value="B" ${degisken2}>Entegrasyon etkinlikleri.
								(Kahvaltı, kamp, ...)</option>
							<option value="C" ${degisken3}>Eğitimsel etkinlikler.
								(Konferanslar, seminer dizileri, ...)</option>
							<option value="D" ${degisken4}>Günübirlik diğer
								etkinlikler (Kültür gezileri, doğa yürüyüşleri, ...)</option>
							<option value="E" ${degisken5}>Ders öncesinde yapılan
								gönüllü çalışmalar. (Kafeteryaya destek, kitapçıkların veya
								broşürlerin hazırlanması, ...)</option>
							<option value="F" ${degisken6}>Günübirlik gönüllü
								(cömertlik) etkinlikleri. (Kamp hazırlığı, kermes, reçel vb.
								çalışmalara destek)</option>
							<option value="G" ${degisken7}>Tekrar edilen/dizi
								gönüllü (cömertlik) etkinlikleri. (Oyuncak atölyesi, fuar
								hazırlığı için reprodüksiyon atölyesine destek)</option>

						</select>
						<form:input type='hidden' class="form-control"
							name='tsEtkinlikTuru' path="tsEtkinlikTuru" id='tsEtkinlikTuru'
							value="${ozelEtkinlikObject.tsEtkinlikTuru}" />
					</div>


				</div>

			</div>

			<div class="col-md-4"></div>

		</div>


		<p class="bg-success">Faaliyet Mail İçerik</p>

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
				<input type="submit" id="saveOzelEtkinlik"
					class="btn btn-primary btn-block" value="Kaydet" />
			</div>
		</div>

	</form:form>

</body>
</html>