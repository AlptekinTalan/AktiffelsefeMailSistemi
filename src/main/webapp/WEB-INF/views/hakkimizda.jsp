<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>

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


<title>Hakkımızda</title>

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

		<div class="container">
			<div class="jumbotron">
				<h2>Yeni Yüksektepe Nedir?</h2>
				<p>Yeni Yüksektepe Kültür Derneği, 1989 yılında Ankara'da
					kurulmuş, etkinliklerini kendi ilkeleri doğrultusunda
					gerçekleştiren, kar amacı gütmeyen, siyasi ve dini bir nitelik
					taşımayan felsefi-kültürel bir harekettir. 12 ilde toplam 18 şubesi
					bulunmaktadır.</p>
				<br>
				<p>Kurulduğu günden beri bireyin ve toplumun gelişimi için "Daha
					iyi, güzel ve adil bir dünya" vizyonuyla binlerce kültürel, sosyal
					ve felsefi etkinlik gerçekleştirmiştir. Ve tüm bu faaliyetler
					üyeler tarafından gönüllülük ilkesiyle hayata geçirilmektedir.</p>
				<br>

				<p>İsmimizdeki "yüksektepe" ifadesi, her insanın içinde var olan
					manevi değerlerin bulunduğu yüksek bir tepeyi simgeler. Yüce, kirli
					olmayan, saf olan şeyleri anlatır. Hemen hemen tüm büyük
					uygarlıklar ve kültürler, en yüce amaçlarını, ideallerini yüksek
					tepelerin ve dağların zirvelerine yerleştirmişlerdir. Kutsal dağ,
					evrensel arketip daima mevcut olmuştur: Hintlilerin Meru Dağı,
					İslam kültürünün Kaf Dağı, İnkaların Machu Pichu Dağı ve İyonların
					Akropolleri gibi...</p>

			</div>
		</div>
	</div>

</body>
</html>