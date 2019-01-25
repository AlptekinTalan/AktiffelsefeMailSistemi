<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="Alptekin Talan">
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/datepicker.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<!-- Latest compiled and minified JavaScript -->
<!-- 
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/js/bootstrap-select.min.js"></script>
 -->
<!-- (Optional) Latest compiled and minified JavaScript translation files -->
<!--
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/js/i18n/defaults-*.min.js"></script>
-->
<!-- 
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
 -->

<!-- Tarih seçicinin güzel görünmesini sağlıyor -->
<link rel="stylesheet" href="/resources/css/datepicker.css">
<link rel="stylesheet" href="/resources/css/bootstrap.css">


<script type="text/javascript">
	$(document).ready(function() {

		$('#tarihSec').datepicker({ //# ile id üzerinden yaparsanız yanlızca bir input çalışır 
			format : "dd.mm.yyyy" //dd-mm-yyyy
		});

	});
</script>

<script type="text/javascript">
	$(document).ready(function() {

		$('#gelisTarihSec').datepicker({ //# ile id üzerinden yaparsanız yanlızca bir input çalışır 
			format : "dd.mm.yyyy" //dd-mm-yyyy
		});

	});
</script>

<script type="text/javascript">
	$(document).ready(function() {

		$('#ziyaretciTarihSec').datepicker({ //# ile id üzerinden yaparsanız yanlızca bir input çalışır 
			format : "dd.mm.yyyy" //dd-mm-yyyy
		});
		$('#uyeTarihSec').datepicker({ //# ile id üzerinden yaparsanız yanlızca bir input çalışır 
			format : "dd.mm.yyyy" //dd-mm-yyyy
		});
		$('#onSekreterTarihSec').datepicker({ //# ile id üzerinden yaparsanız yanlızca bir input çalışır 
			format : "dd.mm.yyyy" //dd-mm-yyyy
		});
		$('#yonetimKuruluUyesiAdayiTarihSec').datepicker({ //# ile id üzerinden yaparsanız yanlızca bir input çalışır 
			format : "dd.mm.yyyy" //dd-mm-yyyy
		});
		$('#yonetimKuruluUyesiTarihSec').datepicker({ //# ile id üzerinden yaparsanız yanlızca bir input çalışır 
			format : "dd.mm.yyyy" //dd-mm-yyyy
		});
		$('#sekreterTarihSec').datepicker({ //# ile id üzerinden yaparsanız yanlızca bir input çalışır 
			format : "dd.mm.yyyy" //dd-mm-yyyy
		});
		$('#egitmenTarihSec').datepicker({ //# ile id üzerinden yaparsanız yanlızca bir input çalışır 
			format : "dd.mm.yyyy" //dd-mm-yyyy
		});
		$('#subeBaskanYardimcisiTarihSec').datepicker({ //# ile id üzerinden yaparsanız yanlızca bir input çalışır 
			format : "dd.mm.yyyy" //dd-mm-yyyy
		});
		$('#subeBaskaniTarihSec').datepicker({ //# ile id üzerinden yaparsanız yanlızca bir input çalışır 
			format : "dd.mm.yyyy" //dd-mm-yyyy
		});

		$('#ziyaretciTerkTarihSec').datepicker({ //# ile id üzerinden yaparsanız yanlızca bir input çalışır 
			format : "dd.mm.yyyy" //dd-mm-yyyy
		});
		$('#uyeTerkTarihSec').datepicker({ //# ile id üzerinden yaparsanız yanlızca bir input çalışır 
			format : "dd.mm.yyyy" //dd-mm-yyyy
		});
		$('#onSekreterTerkTarihSec').datepicker({ //# ile id üzerinden yaparsanız yanlızca bir input çalışır 
			format : "dd.mm.yyyy" //dd-mm-yyyy
		});
		$('#yonetimKuruluUyesiAdayiTerkTarihSec').datepicker({ //# ile id üzerinden yaparsanız yanlızca bir input çalışır 
			format : "dd.mm.yyyy" //dd-mm-yyyy
		});
		$('#yonetimKuruluUyesiTerkTarihSec').datepicker({ //# ile id üzerinden yaparsanız yanlızca bir input çalışır 
			format : "dd.mm.yyyy" //dd-mm-yyyy
		});
		$('#sekreterTerkTarihSec').datepicker({ //# ile id üzerinden yaparsanız yanlızca bir input çalışır 
			format : "dd.mm.yyyy" //dd-mm-yyyy
		});
		$('#egitmenTerkTarihSec').datepicker({ //# ile id üzerinden yaparsanız yanlızca bir input çalışır 
			format : "dd.mm.yyyy" //dd-mm-yyyy
		});
		$('#subeBaskanYardimcisiTerkTarihSec').datepicker({ //# ile id üzerinden yaparsanız yanlızca bir input çalışır 
			format : "dd.mm.yyyy" //dd-mm-yyyy
		});
		$('#subeBaskaniTerkTarihSec').datepicker({ //# ile id üzerinden yaparsanız yanlızca bir input çalışır 
			format : "dd.mm.yyyy" //dd-mm-yyyy
		});
	});
</script>

<script type="text/javascript">
	$(function() {
		$('#kanGrubu').change(function() {
			$('#uyeKanGrubu').val($(this).val());
		}).change(); // Trigger the event
	});
</script>

<script type="text/javascript">
	$(function() {
		$('#uyeIlceSec').change(function() {
			$('#uyeIlce').val($(this).val());
		}).change(); // Trigger the event
	});
</script>

<script type="text/javascript">
	$(function() {
		$('#Statu').change(function() {
			$('#uyeStatu').val($(this).val());
		}).change(); // Trigger the event
	});
</script>

<script type="text/javascript">
	$(function() {
		$('#gelisSekli').change(function() {
			$('#uyeGelisSekli').val($(this).val());
		}).change(); // Trigger the event
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

<script type="text/javascript">
	$(function() {
		$('#seviye').change(function() {
			$('#uyeseviye').val($(this).val());
		}).change(); // Trigger the event
	});
</script>

<script type="text/javascript">
	$(function() {
		$('#uyedurum').change(function() {
			$('#durum').val($(this).val());
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
		$('input.example').on('change', function() {
			$('input.example').not(this).prop('checked', false);
		});
	});
</script>

<script type="text/javascript">
	function doAjaxPost() {

		var uyeAdi = $('#uyeAdi').val();
		var uyeSoyadi = $('#uyeSoyadi').val();

		$.ajax({
			type : "Get",
			url : "searchUyeAdiSoyadi",
			data : "uyeAdi=" + uyeAdi + "&uyeSoyadi=" + uyeSoyadi,
			success : function(response) {
				if ($.trim(response)) {
					//alertify.warning(response);
					//$modal.find('.edit-content').html(response);
					//$target.html(response);
					$("#getCodeModal").modal("toggle");
					$("#getCode").html(response);
				}

			}
		});
	}
</script>

<!-- 
<script type="text/javascript">
	var intervalId = 0;
	intervalId = setInterval(doAjaxPost, 10000);
</script>
-->
<!-- 
				$('#result').html(response);

<script type="text/javascript">
	$(function() {
		$("#uyeRegisterForm").submit(function(e) {
			var result = confirm("Are you sure ?");
			if (result) {
				// do something
			} else {
				alertify.error("Error mesage.");
				e.preventDefault(); // this will prevent from submitting the form.
			}
		});
	});
</script>
-->

<title>Üye Bilgisi</title>

</head>
<body class="container-fluid">

	<!-- HEADER ------------------------------------------------------------- -->
	<div class="row">
		<div class="col-md-6">
			<a href="/"> <img alt="Bootstrap Image Preview"
				src="/resources/photos/aktiffelsefe.png" />
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
						<!-- 
						<li class="active">
							<a href="#">Link</a>
						</li>
						<li>
							<a href="#">Link</a>
						</li>
						 -->
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
				</div>

			</nav>
		</div>
	</div>
	<!-- HEADER ------------------------------------------------------------- -->
	<form:form data-toggle="validator" role="form" id="uyeRegisterForm"
		cssClass="form-horizontal" modelAttribute="uye" method="post"
		enctype="multipart/form-data" action="saveUye">

		<div class="container-fluid">


			<div class="container">

				<!-- Modal -->
				<div class="modal fade" id="getCodeModal" role="dialog">
					<div class="modal-dialog">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Uyarı...</h4>
							</div>
							<div class="modal-body" id="getCode"></div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Kapat</button>
							</div>
						</div>

					</div>
				</div>

			</div>

			<!-- Modal -->
			<!-- 
			<div class="modal fade" id="getCodeModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">API CODE</h4>
						</div>
						<div class="modal-body" id="getCode" style="overflow-x: scroll;"></div>
					</div>
				</div>
			</div>
 			-->

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

			<input type="file" name="file" accept=".jpg,.gif,.png"
				onchange="readURL(this);" title="Üye Fotosu Ekle"
				class="form-control input-sm" />

			<!-- ÜYE FOTO -->

			<div class="row">
				<div class="col-md-4">

					<div class="form-group">
						<div class="control-label col-xs-2">
							<label>Durum</label>
						</div>
						<div class="col-xs-8">

							<c:set var="degisken1" value="${uyeObject.durum}" />
							<c:if test="${degisken1 == 'AKTİF'}">
								<c:set var="degisken1" value="selected" />
							</c:if>
							<c:set var="degisken2" value="${uyeObject.durum}" />
							<c:if test="${degisken2 == 'PASİF'}">
								<c:set var="degisken2" value="selected" />
							</c:if>

							<select class="form-control" id="uyedurum">
								<option value="AKTİF" ${degisken1}>AKTİF</option>
								<option value="PASİF" ${degisken2}>PASİF</option>

							</select>
							<form:hidden class="form-control" name='durum'
								path="durum" id='durum' value="${uyeObject.durum}" />
						</div>
					</div>
				</div>

				<div class="col-md-3">

					<div class="form-group">
						<div class="control-label col-xs-2">
							<label>Seviye</label>
						</div>
						<div class="col-xs-4">

							<c:set var="degisken1" value="${uyeObject.uyeSeviye}" />
							<c:if test="${degisken1 == 1}">
								<c:set var="degisken1" value="selected" />
							</c:if>
							<c:set var="degisken2" value="${uyeObject.uyeSeviye}" />
							<c:if test="${degisken2 == 2}">
								<c:set var="degisken2" value="selected" />
							</c:if>
							<c:set var="degisken3" value="${uyeObject.uyeSeviye}" />
							<c:if test="${degisken3 == 3}">
								<c:set var="degisken3" value="selected" />
							</c:if>
							<c:set var="degisken4" value="${uyeObject.uyeSeviye}" />
							<c:if test="${degisken4 == 4}">
								<c:set var="degisken4" value="selected" />
							</c:if>
							<c:set var="degisken5" value="${uyeObject.uyeSeviye}" />
							<c:if test="${degisken5 == 5}">
								<c:set var="degisken5" value="selected" />
							</c:if>
							<c:set var="degisken6" value="${uyeObject.uyeSeviye}" />
							<c:if test="${degisken6 == 6}">
								<c:set var="degisken6" value="selected" />
							</c:if>
							<c:set var="degisken7" value="${uyeObject.uyeSeviye}" />
							<c:if test="${degisken7 == 7}">
								<c:set var="degisken7" value="selected" />
							</c:if>
							<c:set var="degisken8" value="${uyeObject.uyeSeviye}" />
							<c:if test="${degisken8 == 8}">
								<c:set var="degisken8" value="selected" />
							</c:if>
							<c:set var="degisken9" value="${uyeObject.uyeSeviye}" />
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
							<form:hidden class="form-control" name='uyeseviye'
								path="uyeSeviye" id='uyeseviye' value="${uyeObject.uyeSeviye}" />
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

					<c:set var="degisken" value="${uyeObject.ilg_ekoloji}" />
					<c:if test="${degisken == false}">
						<c:set var="degisken" value="" />
					</c:if>

					<div class="checkbox">
						<label> <form:checkbox path="ilg_ekoloji"
								value="${uyeObject.ilg_ekoloji}" checked="${degisken}" /> <b>Ekoloji
								Çalışmaları:</b> Çevre Temizliği, Ağaçlandırma, Doğada Pratik
							Seminerler
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
						<label> <form:checkbox path="hbr_ePosat" class="example"
								value="${uyeObject.hbr_ePosat}" checked="${degisken}" /> Eposta
						</label>
					</div>

					<c:set var="degisken" value="${uyeObject.hbr_webSitesi}" />
					<c:if test="${degisken == false}">
						<c:set var="degisken" value="" />
					</c:if>

					<div class="checkbox">
						<label> <form:checkbox path="hbr_webSitesi"
								class="example" value="${uyeObject.hbr_webSitesi}"
								checked="${degisken}" /> Aktiffelsefe'nin Web Sayfası
						</label>
					</div>

				</div>
				<div class="col-md-2">

					<c:set var="degisken" value="${uyeObject.hbr_facebook}" />
					<c:if test="${degisken == false}">
						<c:set var="degisken" value="" />
					</c:if>

					<div class="checkbox">
						<label> <form:checkbox path="hbr_facebook" class="example"
								value="${uyeObject.hbr_facebook}" checked="${degisken}" />
							Facebook-Twitter
						</label>
					</div>

					<c:set var="degisken" value="${uyeObject.hbr_brosur}" />
					<c:if test="${degisken == false}">
						<c:set var="degisken" value="" />
					</c:if>

					<div class="checkbox">
						<label> <form:checkbox path="hbr_brosur" class="example"
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
						<label> <form:checkbox path="hbr_afis" class="example"
								value="${uyeObject.hbr_afis}" checked="${degisken}" /> Duvar
							Afişi
						</label>
					</div>

					<c:set var="degisken" value="${uyeObject.hbr_arkadas}" />
					<c:if test="${degisken == false}">
						<c:set var="degisken" value="" />
					</c:if>

					<div class="checkbox">
						<label> <form:checkbox path="hbr_arkadas" class="example"
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
						<label> <form:checkbox path="hbr_sergi" class="example"
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
								class="example" value="${uyeObject.hbr_digerInternet}"
								checked="${degisken}" /> Diğer İnternet Sayfaları
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
								class="example" value="${uyeObject.hbr_yaziliBasin}"
								checked="${degisken}" />Yazılı Basın
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

			<!-- 
			<div class="alert alert-warning" id="result"></div>
 -->

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
							<form:input cssClass="form-control" id="uyeAdi" name="uyeAdi"
								path="uyeAdi" value="${uyeObject.uyeAdi}" required="required"
								onchange="doAjaxPost()" />
						</div>
					</div>

					<div class="form-group">
						<div class="control-label col-xs-2">
							<form:label path="uyeSoyadi">Soyadı</form:label>
						</div>
						<div class="col-xs-10">
							<form:input Class="form-control" id="uyeSoyadi" name="uyeSoyadi"
								path="uyeSoyadi" value="${uyeObject.uyeSoyadi}"
								required="required" onchange="doAjaxPost()" />
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
					<div class="form-group">
						<div class="control-label col-xs-2">
							<form:label path="uyeTcNo">TC. No</form:label>
						</div>
						<div class="col-xs-10">
							<form:input cssClass="form-control" path="uyeTcNo"
								value="${uyeObject.uyeTcNo}" />
						</div>

					</div>
					<div class="form-group">
						<div class="control-label col-xs-2">
							<form:label path="uyeIlce">İlçe</form:label>
						</div>
						<div class="col-xs-10">
							<c:set var="degisken1" value="${uyeObject.uyeIlce}" />
							<c:if test="${degisken1 == 'Arnavutköy'}">
								<c:set var="degisken1" value="selected" />
							</c:if>
							<c:set var="degisken2" value="${uyeObject.uyeIlce}" />
							<c:if test="${degisken2 == 'Avcılar'}">
								<c:set var="degisken2" value="selected" />
							</c:if>
							<c:set var="degisken3" value="${uyeObject.uyeIlce}" />
							<c:if test="${degisken3 == 'Bağcılar'}">
								<c:set var="degisken3" value="selected" />
							</c:if>
							<c:set var="degisken4" value="${uyeObject.uyeIlce}" />
							<c:if test="${degisken4 == 'Bahçelievler'}">
								<c:set var="degisken4" value="selected" />
							</c:if>
							<c:set var="degisken5" value="${uyeObject.uyeIlce}" />
							<c:if test="${degisken5 == 'Bakırköy'}">
								<c:set var="degisken5" value="selected" />
							</c:if>
							<c:set var="degisken6" value="${uyeObject.uyeIlce}" />
							<c:if test="${degisken6 == 'Başakşehir'}">
								<c:set var="degisken6" value="selected" />
							</c:if>
							<c:set var="degisken7" value="${uyeObject.uyeIlce}" />
							<c:if test="${degisken7 == 'Bayrampaşa'}">
								<c:set var="degisken7" value="selected" />
							</c:if>
							<c:set var="degisken8" value="${uyeObject.uyeIlce}" />
							<c:if test="${degisken8 == 'Beşiktaş'}">
								<c:set var="degisken8" value="selected" />
							</c:if>
							<c:set var="degisken9" value="${uyeObject.uyeIlce}" />
							<c:if test="${degisken9 == 'Beylikdüzü'}">
								<c:set var="degisken9" value="selected" />
							</c:if>
							<c:set var="degisken10" value="${uyeObject.uyeIlce}" />
							<c:if test="${degisken10 == 'Beyoğlu'}">
								<c:set var="degisken10" value="selected" />
							</c:if>
							<c:set var="degisken11" value="${uyeObject.uyeIlce}" />
							<c:if test="${degisken11 == 'Büyükçekmece'}">
								<c:set var="degisken11" value="selected" />
							</c:if>
							<c:set var="degisken12" value="${uyeObject.uyeIlce}" />
							<c:if test="${degisken12 == 'Çatalca'}">
								<c:set var="degisken12" value="selected" />
							</c:if>
							<c:set var="degisken13" value="${uyeObject.uyeIlce}" />
							<c:if test="${degisken13 == 'Esenler'}">
								<c:set var="degisken13" value="selected" />
							</c:if>
							<c:set var="degisken14" value="${uyeObject.uyeIlce}" />
							<c:if test="${degisken14 == 'Esenyurt'}">
								<c:set var="degisken14" value="selected" />
							</c:if>
							<c:set var="degisken15" value="${uyeObject.uyeIlce}" />
							<c:if test="${degisken15 == 'Eyüp'}">
								<c:set var="degisken15" value="selected" />
							</c:if>
							<c:set var="degisken16" value="${uyeObject.uyeIlce}" />
							<c:if test="${degisken16 == 'Fatih'}">
								<c:set var="degisken16" value="selected" />
							</c:if>
							<c:set var="degisken17" value="${uyeObject.uyeIlce}" />
							<c:if test="${degisken17 == 'Gaziosmanpaşa'}">
								<c:set var="degisken17" value="selected" />
							</c:if>
							<c:set var="degisken18" value="${uyeObject.uyeIlce}" />
							<c:if test="${degisken18 == 'Güngören'}">
								<c:set var="degisken18" value="selected" />
							</c:if>
							<c:set var="degisken19" value="${uyeObject.uyeIlce}" />
							<c:if test="${degisken19 == 'Kâğıthane'}">
								<c:set var="degisken19" value="selected" />
							</c:if>
							<c:set var="degisken20" value="${uyeObject.uyeIlce}" />
							<c:if test="${degisken20 == 'Küçükçekmece'}">
								<c:set var="degisken20" value="selected" />
							</c:if>
							<c:set var="degisken21" value="${uyeObject.uyeIlce}" />
							<c:if test="${degisken21 == 'Sarıyer'}">
								<c:set var="degisken21" value="selected" />
							</c:if>
							<c:set var="degisken22" value="${uyeObject.uyeIlce}" />
							<c:if test="${degisken22 == 'Silivri'}">
								<c:set var="degisken22" value="selected" />
							</c:if>
							<c:set var="degisken23" value="${uyeObject.uyeIlce}" />
							<c:if test="${degisken23 == 'Sultangazi'}">
								<c:set var="degisken23" value="selected" />
							</c:if>
							<c:set var="degisken24" value="${uyeObject.uyeIlce}" />
							<c:if test="${degisken24 == 'Şişli'}">
								<c:set var="degisken24" value="selected" />
							</c:if>
							<c:set var="degisken25" value="${uyeObject.uyeIlce}" />
							<c:if test="${degisken25 == 'Zeytinburnu'}">
								<c:set var="degisken25" value="selected" />
							</c:if>

							<select class="form-control" id="uyeIlceSec">
								<option value=""></option>
								<option value="Arnavutköy" ${degisken1}>Arnavutköy</option>
								<option value="Avcılar" ${degisken2}>Avcılar</option>
								<option value="Bağcılar" ${degisken3}>Bağcılar</option>
								<option value="Bahçelievler" ${degisken4}>Bahçelievler</option>
								<option value="Bakırköy" ${degisken5}>Bakırköy</option>
								<option value="Başakşehir" ${degisken6}>Başakşehir</option>
								<option value="Bayrampaşa" ${degisken7}>Bayrampaşa</option>
								<option value="Beşiktaş" ${degisken8}>Beşiktaş</option>
								<option value="Beylikdüzü" ${degisken9}>Beylikdüzü</option>
								<option value="Beyoğlu" ${degisken10}>Beyoğlu</option>
								<option value="Büyükçekmece" ${degisken11}>Büyükçekmece</option>
								<option value="Çatalca" ${degisken12}>Çatalca</option>
								<option value="Esenler" ${degisken13}>Esenler</option>
								<option value="Esenyurt" ${degisken14}>Esenyurt</option>
								<option value="Eyüp" ${degisken15}>Eyüp</option>
								<option value="Fatih" ${degisken16}>Fatih</option>
								<option value="Gaziosmanpaşa" ${degisken17}>Gaziosmanpaşa</option>
								<option value="Güngören" ${degisken18}>Güngören</option>
								<option value="Kâğıthane" ${degisken19}>Kâğıthane</option>
								<option value="Küçükçekmece" ${degisken20}>Küçükçekmece</option>
								<option value="Sarıyer" ${degisken21}>Sarıyer</option>
								<option value="Silivri" ${degisken22}>Silivri</option>
								<option value="Sultangazi" ${degisken23}>Sultangazi</option>
								<option value="Şişli" ${degisken24}>Şişli</option>
								<option value="Zeytinburnu" ${degisken25}>Zeytinburnu</option>


							</select>
							<form:input type='hidden' class="form-control" name='uyeIlce'
								path="uyeIlce" id='uyeIlce' value="${uyeObject.uyeIlce}" />
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
							<form:label path="dogumTarihi">Doğum T.</form:label>
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

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="gelisTarihi">Geliş T</form:label>
						</div>
						<fmt:formatDate value="${uyeObject.gelisTarihi}" var="dateString"
							pattern="dd.MM.yyyy" />
						<div class="col-xs-9">

							<p>
								<form:input type="text" cssClass="form-control"
									path="gelisTarihi" id="gelisTarihSec" value="${dateString}" />
							</p>

						</div>

					</div>

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="uyeStatu">Statü</form:label>
						</div>
						<div class="col-xs-9">
							<c:set var="degisken1" value="${uyeObject.uyeStatu}" />
							<c:if test="${degisken1 == 'Ziyaretçi'}">
								<c:set var="degisken1" value="selected" />
							</c:if>
							<c:set var="degisken2" value="${uyeObject.uyeStatu}" />
							<c:if test="${degisken2 == 'Üye'}">
								<c:set var="degisken2" value="selected" />
							</c:if>
							<c:set var="degisken3" value="${uyeObject.uyeStatu}" />
							<c:if test="${degisken3 == 'Ön Sekreter'}">
								<c:set var="degisken3" value="selected" />
							</c:if>
							<c:set var="degisken4" value="${uyeObject.uyeStatu}" />
							<c:if test="${degisken4 == 'Yönetim Kurulu Üyesi Adayı'}">
								<c:set var="degisken4" value="selected" />
							</c:if>
							<c:set var="degisken5" value="${uyeObject.uyeStatu}" />
							<c:if test="${degisken5 == 'Yönetim Kurulu Üyesi'}">
								<c:set var="degisken5" value="selected" />
							</c:if>
							<c:set var="degisken6" value="${uyeObject.uyeStatu}" />
							<c:if test="${degisken6 == 'Sekreter'}">
								<c:set var="degisken6" value="selected" />
							</c:if>
							<c:set var="degisken7" value="${uyeObject.uyeStatu}" />
							<c:if test="${degisken7 == 'Eğitmen'}">
								<c:set var="degisken7" value="selected" />
							</c:if>
							<c:set var="degisken8" value="${uyeObject.uyeStatu}" />
							<c:if test="${degisken8 == 'Şube Başkan Yardımcısı'}">
								<c:set var="degisken8" value="selected" />
							</c:if>
							<c:set var="degisken9" value="${uyeObject.uyeStatu}" />
							<c:if test="${degisken9 == 'Şube Başkanı'}">
								<c:set var="degisken9" value="selected" />
							</c:if>
							<select class="form-control" id="Statu">
								<option value=""></option>
								<option value="Ziyaretçi" ${degisken1}>Ziyaretçi</option>
								<option value="Üye" ${degisken2}>Üye</option>
								<option value="Ön Sekreter" ${degisken3}>Ön Sekreter</option>
								<option value="Yönetim Kurulu Üyesi Adayı" ${degisken4}>Yönetim
									Kurulu Üyesi Adayı</option>
								<option value="Yönetim Kurulu Üyesi" ${degisken5}>Yönetim
									Kurulu Üyesi</option>
								<option value="Sekreter" ${degisken6}>Sekreter</option>
								<option value="Eğitmen" ${degisken7}>Eğitmen</option>
								<option value="Şube Başkan Yardımcısı" ${degisken8}>Şube
									Başkan Yardımcısı</option>
								<option value="Şube Başkanı" ${degisken9}>Şube Başkanı</option>


							</select>
							<form:input type='hidden' class="form-control" name='uyeStatu'
								path="uyeStatu" id='uyeStatu' value="${uyeObject.uyeStatu}" />
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
							<form:input type="email" cssClass="form-control" path="ePosta"
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

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="uyeKanGrubu">Kan G.</form:label>
						</div>
						<div class="col-xs-9">
							<c:set var="degisken1" value="${uyeObject.uyeKanGrubu}" />
							<c:if test="${degisken1 == 'AB +'}">
								<c:set var="degisken1" value="selected" />
							</c:if>
							<c:set var="degisken2" value="${uyeObject.uyeKanGrubu}" />
							<c:if test="${degisken2 == 'AB -'}">
								<c:set var="degisken2" value="selected" />
							</c:if>
							<c:set var="degisken3" value="${uyeObject.uyeKanGrubu}" />
							<c:if test="${degisken3 == 'A +'}">
								<c:set var="degisken3" value="selected" />
							</c:if>
							<c:set var="degisken4" value="${uyeObject.uyeKanGrubu}" />
							<c:if test="${degisken4 == 'A -'}">
								<c:set var="degisken4" value="selected" />
							</c:if>
							<c:set var="degisken5" value="${uyeObject.uyeKanGrubu}" />
							<c:if test="${degisken5 == 'B +'}">
								<c:set var="degisken5" value="selected" />
							</c:if>
							<c:set var="degisken6" value="${uyeObject.uyeKanGrubu}" />
							<c:if test="${degisken6 == 'B -'}">
								<c:set var="degisken6" value="selected" />
							</c:if>
							<c:set var="degisken7" value="${uyeObject.uyeKanGrubu}" />
							<c:if test="${degisken7 == '0 +'}">
								<c:set var="degisken7" value="selected" />
							</c:if>
							<c:set var="degisken8" value="${uyeObject.uyeKanGrubu}" />
							<c:if test="${degisken8 == '0 -'}">
								<c:set var="degisken8" value="selected" />
							</c:if>
							<select class="form-control" id="kanGrubu">
								<option value=""></option>
								<option value="AB +" ${degisken1}>AB +</option>
								<option value="AB -" ${degisken2}>AB -</option>
								<option value="A +" ${degisken3}>A +</option>
								<option value="A -" ${degisken4}>A -</option>
								<option value="B +" ${degisken5}>B +</option>
								<option value="B -" ${degisken6}>B -</option>
								<option value="0 +" ${degisken7}>0 +</option>
								<option value="0 -" ${degisken8}>0 -</option>

							</select>
							<form:input type='hidden' class="form-control" name='uyeKanGrubu'
								path="uyeKanGrubu" id='uyeKanGrubu'
								value="${uyeObject.uyeKanGrubu}" />
						</div>

					</div>

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="uyeGelisSekli">Geliş Ş.</form:label>
						</div>
						<div class="col-xs-9">
							<c:set var="degisken1" value="${uyeObject.uyeGelisSekli}" />
							<c:if test="${degisken1 == 'Halkla İlişkiler'}">
								<c:set var="degisken1" value="selected" />
							</c:if>
							<c:set var="degisken2" value="${uyeObject.uyeGelisSekli}" />
							<c:if test="${degisken2 == 'Felsefe Semineri'}">
								<c:set var="degisken2" value="selected" />
							</c:if>
							<c:set var="degisken3" value="${uyeObject.uyeGelisSekli}" />
							<c:if test="${degisken3 == 'Okul Ziyareti'}">
								<c:set var="degisken3" value="selected" />
							</c:if>

							<select class="form-control" id="gelisSekli">
								<option value=""></option>
								<option value="Halkla İlişkiler" ${degisken1}>Halkla
									İlişkiler</option>
								<option value="Felsefe Semineri" ${degisken2}>Felsefe
									Semineri</option>
								<option value="Okul Ziyareti" ${degisken3}>Okul
									Ziyareti</option>

							</select>
							<form:input type='hidden' class="form-control"
								name='uyeGelisSekli' path="uyeGelisSekli" id='uyeGelisSekli'
								value="${uyeObject.uyeGelisSekli}" />
						</div>

					</div>

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="uyeGelisSekliDiger">Geliş Ş. D.</form:label>
						</div>
						<div class="col-xs-9">
							<form:input cssClass="form-control" path="uyeGelisSekliDiger"
								value="${uyeObject.uyeGelisSekliDiger}" />
						</div>
					</div>

				</div>

			</div>

			<p class="bg-success">Özel Notlar</p>


			<div class="row">
				<div class="col-md-12">

					<div class="col-xs-12">

						<form:input type="text" class="form-control" path="uyeOzelNot"
							value="${uyeObject.uyeOzelNot}" rows="5"
							placeholder="Özel Notlar..." />

					</div>

				</div>
			</div>
			<div class="row"></div>

			<p class="bg-success"></p>

			<p class="bg-success">Geçmiş</p>

			<div class="form-group">
				<label class="col-sm-3 control-label">Ziyaretçi Olma Tarihi</label>
				<div class="col-sm-2">

					<fmt:formatDate value="${uyeObject.ziyaretciTarihi}"
						var="dateString" pattern="dd.MM.yyyy" />

					<form:input type="text" cssClass="form-control"
						path="ziyaretciTarihi" id="ziyaretciTarihSec"
						value="${dateString}" />

				</div>

				<label class="col-sm-3 control-label">Ziyaretçi Terk Tarihi</label>
				<div class="col-sm-2">

					<fmt:formatDate value="${uyeObject.ziyaretciTerkTarihi}"
						var="dateString" pattern="dd.MM.yyyy" />

					<form:input type="text" cssClass="form-control"
						path="ziyaretciTerkTarihi" id="ziyaretciTerkTarihSec"
						value="${dateString}" />

				</div>
			</div>
			<div class="form-group">
				<label for="disabledInput" class="col-sm-3 control-label">Üye
					Olma Tarihi</label>
				<div class="col-sm-2">

					<fmt:formatDate value="${uyeObject.uyeTarihi}" var="dateString"
						pattern="dd.MM.yyyy" />

					<form:input type="text" cssClass="form-control" path="uyeTarihi"
						id="uyeTarihSec" value="${dateString}" />

				</div>

				<label for="disabledInput" class="col-sm-3 control-label">Üye
					Terk Tarihi</label>
				<div class="col-sm-2">

					<fmt:formatDate value="${uyeObject.uyeTerkTarihi}" var="dateString"
						pattern="dd.MM.yyyy" />

					<form:input type="text" cssClass="form-control"
						path="uyeTerkTarihi" id="uyeTerkTarihSec" value="${dateString}" />

				</div>
			</div>
			<div class="form-group">
				<label for="disabledInput" class="col-sm-3 control-label">Ön
					Sekreter Olma Tarihi</label>
				<div class="col-sm-2">

					<fmt:formatDate value="${uyeObject.onSekreterTarihi}"
						var="dateString" pattern="dd.MM.yyyy" />

					<form:input type="text" cssClass="form-control"
						path="onSekreterTarihi" id="onSekreterTarihSec"
						value="${dateString}" />

				</div>
				<label for="disabledInput" class="col-sm-3 control-label">Ön
					Sekreter Terk Tarihi</label>
				<div class="col-sm-2">

					<fmt:formatDate value="${uyeObject.onSekreterTerkTarihi}"
						var="dateString" pattern="dd.MM.yyyy" />

					<form:input type="text" cssClass="form-control"
						path="onSekreterTerkTarihi" id="onSekreterTerkTarihSec"
						value="${dateString}" />

				</div>
			</div>
			<div class="form-group">
				<label for="disabledInput" class="col-sm-3 control-label">Yönetim
					Kurulu Üyesi Adayı Olma Tarihi</label>
				<div class="col-sm-2">

					<fmt:formatDate value="${uyeObject.yonetimKuruluUyesiAdayiTarihi}"
						var="dateString" pattern="dd.MM.yyyy" />

					<form:input type="text" cssClass="form-control"
						path="yonetimKuruluUyesiAdayiTarihi"
						id="yonetimKuruluUyesiAdayiTarihSec" value="${dateString}" />

				</div>
				<label for="disabledInput" class="col-sm-3 control-label">Yönetim
					Kurulu Üyesi Adayı Terk Tarihi</label>
				<div class="col-sm-2">

					<fmt:formatDate
						value="${uyeObject.yonetimKuruluUyesiAdayiTerkTarihi}"
						var="dateString" pattern="dd.MM.yyyy" />

					<form:input type="text" cssClass="form-control"
						path="yonetimKuruluUyesiAdayiTerkTarihi"
						id="yonetimKuruluUyesiAdayiTerkTarihSec" value="${dateString}" />

				</div>
			</div>

			<div class="form-group">
				<label for="disabledInput" class="col-sm-3 control-label">Yönetim
					Kurulu Üyesi Olma Tarihi</label>
				<div class="col-sm-2">

					<fmt:formatDate value="${uyeObject.yonetimKuruluUyesiTarihi}"
						var="dateString" pattern="dd.MM.yyyy" />

					<form:input type="text" cssClass="form-control"
						path="yonetimKuruluUyesiTarihi" id="yonetimKuruluUyesiTarihSec"
						value="${dateString}" />

				</div>

				<label for="disabledInput" class="col-sm-3 control-label">Yönetim
					Kurulu Üyesi Terk Tarihi</label>
				<div class="col-sm-2">

					<fmt:formatDate value="${uyeObject.yonetimKuruluUyesiTerkTarihi}"
						var="dateString" pattern="dd.MM.yyyy" />

					<form:input type="text" cssClass="form-control"
						path="yonetimKuruluUyesiTerkTarihi"
						id="yonetimKuruluUyesiTerkTarihSec" value="${dateString}" />

				</div>
			</div>
			<div class="form-group">
				<label for="disabledInput" class="col-sm-3 control-label">
					Sekreter Olma Tarihi</label>
				<div class="col-sm-2">

					<fmt:formatDate value="${uyeObject.sekreterTarihi}"
						var="dateString" pattern="dd.MM.yyyy" />

					<form:input type="text" cssClass="form-control"
						path="sekreterTarihi" id="sekreterTarihSec" value="${dateString}" />

				</div>

				<label for="disabledInput" class="col-sm-3 control-label">
					Sekreter Terk Tarihi</label>
				<div class="col-sm-2">

					<fmt:formatDate value="${uyeObject.sekreterTerkTarihi}"
						var="dateString" pattern="dd.MM.yyyy" />

					<form:input type="text" cssClass="form-control"
						path="sekreterTerkTarihi" id="sekreterTerkTarihSec"
						value="${dateString}" />

				</div>
			</div>
			<div class="form-group">
				<label for="disabledInput" class="col-sm-3 control-label">
					Eğitmen Olma Tarihi</label>
				<div class="col-sm-2">

					<fmt:formatDate value="${uyeObject.egitmenTarihi}" var="dateString"
						pattern="dd.MM.yyyy" />

					<form:input type="text" cssClass="form-control"
						path="egitmenTarihi" id="egitmenTarihSec" value="${dateString}" />

				</div>

				<label for="disabledInput" class="col-sm-3 control-label">
					Eğitmen Terk Tarihi</label>
				<div class="col-sm-2">

					<fmt:formatDate value="${uyeObject.egitmenTerkTarihi}"
						var="dateString" pattern="dd.MM.yyyy" />

					<form:input type="text" cssClass="form-control"
						path="egitmenTerkTarihi" id="egitmenTerkTarihSec"
						value="${dateString}" />

				</div>
			</div>
			<div class="form-group">
				<label for="disabledInput" class="col-sm-3 control-label">
					Şube Başkan Yardımcısı Olma Tarihi</label>
				<div class="col-sm-2">

					<fmt:formatDate value="${uyeObject.subeBaskanYardimcisiTarihi}"
						var="dateString" pattern="dd.MM.yyyy" />

					<form:input type="text" cssClass="form-control"
						path="subeBaskanYardimcisiTarihi"
						id="subeBaskanYardimcisiTarihSec" value="${dateString}" />

				</div>

				<label for="disabledInput" class="col-sm-3 control-label">
					Şube Başkan Yardımcısı Terk Tarihi</label>
				<div class="col-sm-2">

					<fmt:formatDate value="${uyeObject.subeBaskanYardimcisiTerkTarihi}"
						var="dateString" pattern="dd.MM.yyyy" />

					<form:input type="text" cssClass="form-control"
						path="subeBaskanYardimcisiTerkTarihi"
						id="subeBaskanYardimcisiTerkTarihSec" value="${dateString}" />

				</div>
			</div>
			<div class="form-group">
				<label for="disabledInput" class="col-sm-3 control-label">
					Şube Başkan Olma Tarihi</label>
				<div class="col-sm-2">

					<fmt:formatDate value="${uyeObject.subeBaskaniTarihi}"
						var="dateString" pattern="dd.MM.yyyy" />

					<form:input type="text" cssClass="form-control"
						path="subeBaskaniTarihi" id="subeBaskaniTarihSec"
						value="${dateString}" />

				</div>
				<label for="disabledInput" class="col-sm-3 control-label">
					Şube Başkan Terk Tarihi</label>
				<div class="col-sm-2">

					<fmt:formatDate value="${uyeObject.subeBaskaniTerkTarihi}"
						var="dateString" pattern="dd.MM.yyyy" />

					<form:input type="text" cssClass="form-control"
						path="subeBaskaniTerkTarihi" id="subeBaskaniTerkTarihSec"
						value="${dateString}" />

				</div>
			</div>
			<div class="row"></div>



			<div class="row">
				<div class="col-md-12">
					<input type="submit" id="saveUye" class="btn btn-primary btn-block"
						value="Kaydet" />
				</div>
			</div>
		</div>

	</form:form>

</body>

</html>