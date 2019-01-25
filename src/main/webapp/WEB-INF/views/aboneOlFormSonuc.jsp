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
					<h2>E mail adresinize gönderilmiş olan güvenlik kodunu
						aşağıdaki alana girerek abone olma işleminizi tamamlayabilirsiniz.</h2>
					<p></p>
				</div>
			</div>
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<form:form id="aboneRegisterFormWeb" modelAttribute="abone"
					cssClass="form-horizontal" method="post"
					action="saveAboneWebComplete">
					<div class="form-group">

						<label for="kod"> Güvenlik Kodu </label> <input type="text"
							class="form-control" name="sifre" id="sifre"
							placeholder="Lütfen Güvenlik Kodunu Giriniz..."
							required="required" /> <input type="hidden" class="form-control"
							name="email" id="email" value="${param['email']}" />

					</div>

					<div class="row">


						<input type="submit" id="saveAboneWebComplete"
							class="btn btn-primary btn-block" value="Aboneliği Tamamla" />

					</div>

					<p>
				</form:form>
			</div>
			<div class="col-md-4"></div>


		</div>
	</div>

</body>
</html>