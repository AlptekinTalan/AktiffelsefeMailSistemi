<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/css/bootstrap-select.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/js/bootstrap-select.min.js"></script>

<!-- (Optional) Latest compiled and minified JavaScript translation files -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/js/i18n/defaults-*.min.js"></script>

<!-- EN SON EKLENEN -->
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

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>

<script type="text/javascript">
	$(function() {
		$('#color').change(function() {
			$('#searchTur').val($(this).val());
		}).change(); // Trigger the event
	});
</script>


<title>Aktiffelsefe Bilgi Sistemi V1.4.6</title>
</head>

<body>
	<div class="container-fluid">

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
										<li><a href="listAllOzelEtkinlik">Faaliyetleri
												Listele / Düzenle</a></li>
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
										<li><a href="raporAllEtkinlik?yil=">Etkinlik Ayrıntılı
												Raporlar</a></li>
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
										<li><a href="raporAllEtkinlik?yil=">Etkinlik Ayrıntılı
												Raporlar</a></li>
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
										<li><a href="listAllOzelEtkinlik">Faaliyetleri
												Listele / Düzenle</a></li>
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
										<li><a href="listAllOzelEtkinlik">Faaliyetleri
												Listele / Düzenle</a></li>
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
										<li><a href="listAllOzelEtkinlik">Faaliyetleri
												Listele / Düzenle</a></li>
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
										<li><a href="listAllOzelEtkinlik">Faaliyetleri
												Listele / Düzenle</a></li>
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

		<sec:authorize access="hasRole('ROLE_ADMIN')">

			<div class="row">
				<div class="col-md-12">
					<img class="img-responsive" src="resources/photos/slider1.jpg"
						alt="Chania" width="1340" height="300">

				</div>
			</div>

		</sec:authorize>

		<sec:authorize access="!hasRole('ROLE_ADMIN')">

			<div class="row">
				<div class="col-md-12">
					<img class="img-responsive" src="resources/photos/slider1.jpg"
						alt="Chania" width="1340" height="300">

				</div>
			</div>

		</sec:authorize>

	</div>

</body>
</html>

