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
	$(document)
			.ready(
					function() {
						jQuery
								.extend(
										jQuery.fn.dataTableExt.oSort,
										{
											"de_datetime-asc" : function(a, b) {
												var x, y;
												if (jQuery.trim(a) !== '') {
													var deDatea = jQuery
															.trim(a).split(' ');
													var deTimea = deDatea[1]
															.split(':');
													var deDatea2 = deDatea[0]
															.split('.');
													if (typeof deTimea[2] != 'undefined') {
														x = (deDatea2[2]
																+ deDatea2[1]
																+ deDatea2[0]
																+ deTimea[0]
																+ deTimea[1] + deTimea[2]) * 1;
													} else {
														x = (deDatea2[2]
																+ deDatea2[1]
																+ deDatea2[0]
																+ deTimea[0] + deTimea[1]) * 1;
													}
												} else {
													x = -Infinity; // = l'an 1000 ...
												}

												if (jQuery.trim(b) !== '') {
													var deDateb = jQuery
															.trim(b).split(' ');
													var deTimeb = deDateb[1]
															.split(':');
													deDateb = deDateb[0]
															.split('.');
													if (typeof deTimeb[2] != 'undefined') {
														y = (deDateb[2]
																+ deDateb[1]
																+ deDateb[0]
																+ deTimeb[0]
																+ deTimeb[1] + deTimeb[2]) * 1;
													} else {
														y = (deDateb[2]
																+ deDateb[1]
																+ deDateb[0]
																+ deTimeb[0] + deTimeb[1]) * 1;
													}
												} else {
													y = -Infinity;
												}
												var z = ((x < y) ? -1
														: ((x > y) ? 1 : 0));
												return z;
											},

											"de_datetime-desc" : function(a, b) {
												var x, y;
												if (jQuery.trim(a) !== '') {
													var deDatea = jQuery
															.trim(a).split(' ');
													var deTimea = deDatea[1]
															.split(':');
													var deDatea2 = deDatea[0]
															.split('.');
													if (typeof deTimea[2] != 'undefined') {
														x = (deDatea2[2]
																+ deDatea2[1]
																+ deDatea2[0]
																+ deTimea[0]
																+ deTimea[1] + deTimea[2]) * 1;
													} else {
														x = (deDatea2[2]
																+ deDatea2[1]
																+ deDatea2[0]
																+ deTimea[0] + deTimea[1]) * 1;
													}
												} else {
													x = Infinity;
												}

												if (jQuery.trim(b) !== '') {
													var deDateb = jQuery
															.trim(b).split(' ');
													var deTimeb = deDateb[1]
															.split(':');
													deDateb = deDateb[0]
															.split('.');
													if (typeof deTimeb[2] != 'undefined') {
														y = (deDateb[2]
																+ deDateb[1]
																+ deDateb[0]
																+ deTimeb[0]
																+ deTimeb[1] + deTimeb[2]) * 1;
													} else {
														y = (deDateb[2]
																+ deDateb[1]
																+ deDateb[0]
																+ deTimeb[0] + deTimeb[1]) * 1;
													}
												} else {
													y = -Infinity;
												}
												var z = ((x < y) ? 1
														: ((x > y) ? -1 : 0));
												return z;
											},

											"de_date-asc" : function(a, b) {
												var x, y;
												if (jQuery.trim(a) !== '') {
													var deDatea = jQuery
															.trim(a).split('.');
													x = (deDatea[2]
															+ deDatea[1] + deDatea[0]) * 1;
												} else {
													x = Infinity; // = l'an 1000 ...
												}

												if (jQuery.trim(b) !== '') {
													var deDateb = jQuery
															.trim(b).split('.');
													y = (deDateb[2]
															+ deDateb[1] + deDateb[0]) * 1;
												} else {
													y = -Infinity;
												}
												var z = ((x < y) ? -1
														: ((x > y) ? 1 : 0));
												return z;
											},

											"de_date-desc" : function(a, b) {
												var x, y;
												if (jQuery.trim(a) !== '') {
													var deDatea = jQuery
															.trim(a).split('.');
													x = (deDatea[2]
															+ deDatea[1] + deDatea[0]) * 1;
												} else {
													x = -Infinity;
												}

												if (jQuery.trim(b) !== '') {
													var deDateb = jQuery
															.trim(b).split('.');
													y = (deDateb[2]
															+ deDateb[1] + deDateb[0]) * 1;
												} else {
													y = Infinity;
												}
												var z = ((x < y) ? 1
														: ((x > y) ? -1 : 0));
												return z;
											}
										});
						$('#example').dataTable({
							"pageLength": 50,
							columnDefs : [ {
								type : 'de_date',
								targets : 4
							} ]
						});
					});
</script>

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.12/css/dataTables.bootstrap.min.css"
	rel="stylesheet" />

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.12/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.12/js/dataTables.bootstrap.min.js"></script>

<title>Faaliyetler</title>


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

	<div class="panel-body">
		<h6>

			<c:if test="${empty etkinlikListe}">
				<div class="alert alert-danger">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Böyle bir etkinlik yok :(((</strong>
				</div>
			</c:if>
			<c:if test="${not empty etkinlikListe}">

				<table id="example"
					class="table table-striped table-bordered table-hover">
					<thead style="background-color: #DFF0D8;">
						<tr>
							<th>Id</th>
							<th>Etkinlik Adı</th>
							<th>Etkinlik Yeri</th>
							<th>Etkinlik Türü</th>
							<th>Etkinlik Tarihi</th>
							<th>Etkinlik Saati</th>

							<th>Yoklama Oluştur/Düzenle</th>
							<th>Katılımcı Düzenle/Listele</th>
							<th>Görevli Düzenle/Listele</th>
							<th>Eğitmen Düzenle/Listele</th>

							<sec:authorize access="hasRole('ROLE_ISTANBULBAKIRKOY')">
								<th>Katılımcı Listele</th>
							</sec:authorize>

							<th>Etkinlik Düzenle</th>
							<sec:authorize access="hasRole('ROLE_ADMIN')">
								<th>Etkinliği Sil</th>
							</sec:authorize>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${etkinlikListe}" var="etkinlik">
							<tr>
								<th><c:out value="${etkinlik.etkinlikId}" /></th>
								<th><c:out value="${etkinlik.etkinlikAdi}" /></th>
								<th><c:out value="${etkinlik.etkinlikAdresi}" /></th>
								<th><c:out value="${etkinlik.etkinlikTuru}" /></th>
								<th><fmt:formatDate value="${etkinlik.etkinlikTarihi}"
										pattern="dd.MM.yyyy" /></th>
								<th><c:out value="${etkinlik.etkinlikSaati}" /></th>

								<th><a
									href="listAddYoklamaToOzelEtkinlik?etkinlikId=<c:out value='${etkinlik.etkinlikId}'/>">Yoklama
										Oluştur/Düzenle</a></th>
								<th><a
									href="listKatilimciToOzelEtkinlik?etkinlikId=<c:out value='${etkinlik.etkinlikId}'/>">Katılımcı
										Düzenle/Listele</a></th>
								<th><a
									href="listGorevliToOzelEtkinlik?etkinlikId=<c:out value='${etkinlik.etkinlikId}'/>">Görevli
										Düzenle/Listele</a></th>
								<th><a
									href="listEgitmenToOzelEtkinlik?etkinlikId=<c:out value='${etkinlik.etkinlikId}'/>">Eğitmen
										Düzenle/Listele</a></th>

								<sec:authorize access="hasRole('ROLE_ISTANBULBAKIRKOY')">
									<th><a
										href="listKatilimciToOzelEtkinlik?etkinlikId=<c:out value='${etkinlik.etkinlikId}'/>">Katılımcı
											Listele</a></th>
								</sec:authorize>

								<th><a
									href="editOzelEtkinlik?id=<c:out value='${etkinlik.etkinlikId}'/>">Etkinliği
										Düzenle</a></th>
								<sec:authorize access="hasRole('ROLE_ADMIN')">
									<th><a
										href="removeOzelEtkinlik?id=<c:out value='${etkinlik.etkinlikId}'/>">Etkinliği
											Sil</a></th>
								</sec:authorize>

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