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


<script type="text/javascript">
	$(function() {
		$('#sube').change(function() {
			$('#subeadi').val($(this).val());
		}).change(); // Trigger the event
	});
</script>

<script src='https://www.google.com/recaptcha/api.js'></script>

<title>Bülten Kayıt</title>
</head>

<body class="container-fluid">
	<!-- HEADER ------------------------------------------------------------- -->
	<div class="row">
		<div class="col-md-6">
			<a href="http://www.aktiffelsefe.org/"> <img
				alt="Bootstrap Image Preview"
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

	<!-- HEADER ------------------------------------------------------------- -->

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="jumbotron">
					<h2>Etkinliklerimizi kaçırmamak için e-posta duyuru listemize
						abone olun!</h2>
					<p>Aşağıdaki forma adınızı, duyuru mesajlarını almak
						istediğiniz eposta adresinizi yazın ve etkinliklerini takip etmek
						istediğiniz şubemizi seçin. Seçtiğiniz şubemiz bünyesinde
						gerçekleşecek etkinliklerin tarih ve bilgilerini birkaç gün
						öncesinden mail adresinize gönderelim. Eğer birden fazla şubemizin
						etkinliklerini takip etmek istiyorsanız, her bir şubemiz için
						tekrar form göndererek abone olabilirsiniz. Örneğin hem Alsancak
						hem Levent şubelerimizin etkinliklerini takip etmek için,
						aşağıdaki formu her defasında farklı şube seçerek iki kez doldurup
						gönderin. İstediğiniz zaman aldığınız duyuru postalarının sonunda
						göreceğiniz link ile aboneliğinizi kolayca iptal edebilirsiniz.
						Duyuru listemize abonelik ücretsizdir.</p>
					<p>
						<a href="http://www.aktiffelsefe.org/kullanim-kosullari/">Kullanım
							koşullarını okumak için lütfen tıklayınız...</a>
					</p>
				</div>
			</div>
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<form:form id="aboneRegisterFormWeb" modelAttribute="abone"
					cssClass="form-horizontal" method="post" action="saveAboneWeb">
					<div class="form-group">

						<label for="name"> İsim </label> <input type="text"
							class="form-control" name="ad" id="name"
							placeholder="Lütfen İsminizi Giriniz..." required="required" />

					</div>
					<div class="form-group">

						<label for="lastname"> Soyisim </label> <input type="text"
							name="soyad" class="form-control" id="lastname"
							placeholder="Lütfen Soyisminizi Giriniz..." required="required" />

					</div>
					<div class="form-group">

						<label for="email"> Email adresi </label> <input type="email"
							name="email" class="form-control" id="email"
							placeholder="Lütfen eMailinizi Giriniz..." required="required" />
					</div>

					<div class="form-group">

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

						</select> <input type="hidden" id="subeadi" name="subeAdi"
							value="${sube.subeAdi}" class="form-control" />

					</div>

					<c:set var="recaptcha" value="${recaptchaFalse}" />

					<c:if test="${recaptcha == true}">

						<div class="alert alert-warning">
							<strong>Uyarı!</strong> Lütfen "ben robot değilim" seçeneğini
							işaretleyiniz.
						</div>

					</c:if>

					<div class="g-recaptcha"
						data-sitekey="6LdOhhkUAAAAADAVYdixV58oVzQH3bIm9IYiuMLX"></div>

					<div class="row">
						<div class="col-md-12">
							<input type="submit" id="sendCode"
								class="btn btn-primary btn-block" value="Abone Ol" />
						</div>
					</div>

					<p>
				</form:form>
			</div>
			<div class="col-md-4"></div>
		</div>
</body>
</html>