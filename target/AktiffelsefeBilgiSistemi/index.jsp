<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="/resources/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<script src="/resources/js/bootstrap.min.js"></script>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/css/bootstrap-select.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/js/bootstrap-select.min.js"></script>

<!-- (Optional) Latest compiled and minified JavaScript translation files -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/js/i18n/defaults-*.min.js"></script>


<title>Aktiffelsefe Bilgi Sistemi</title>
</head>
<body>
	<div class="container-fluid">

		<!-- HEADER ------------------------------------------------------------- -->
		<div class="row">
			<div class="col-md-6">
				<img alt="Bootstrap Image Preview"
					src="resources/photos/aktiffelsefe.png" />
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
								data-toggle="dropdown">Üye Bilgileri<strong class="caret"></strong></a>
								<ul class="dropdown-menu">
									<li><a href="newUye">Üye Ekle</a></li>
									<li><a href="listAllUye">Üyeleri Listele / Düzenle</a></li>
									<li class="divider"></li>
									<li><a href="hakkimizda">Hakkımızda</a></li>

								</ul></li>
						</ul>
						<ul class="nav navbar-nav">
							<li class="dropdown"><a href="/" class="dropdown-toggle"
								data-toggle="dropdown">Etkinlik Bilgileri<strong
									class="caret"></strong></a>
								<ul class="dropdown-menu">
									<li><a href="newEtkinlik">Etkinlik Ekle</a></li>
									<li><a href="listAllEtkinlik">Etkinlikleri Listele /
											Düzenle</a></li>
									<li class="divider"></li>
								</ul></li>

						</ul>
						<!-- 
						<form class="navbar-form navbar-left" role="search"
							action="searchUye">

							<select class="selectpicker" id="color">
								<option value="id" selected="selected">Id'ye Göre Ara</option>
								<option value="ad">Ada Göre Ara</option>
								<option value="soyad">Soyada Göre Ara</option>
							</select> <input type='text' class="form-control" name='searchAdi'
								id='searchAdi' /> <input type='hidden' class="form-control"
								name='searchTur' id='searchTur' value="ad" />

							<button type="submit" class="btn btn-default">Ara</button>
						</form>
 						-->
					</div>

				</nav>
			</div>
		</div>
		<!-- HEADER ------------------------------------------------------------- -->



		<div class="row">
			<div class="col-md-12">

				<div class="carousel slide" id="carousel-231541">
					<ol class="carousel-indicators">
						<li class="active" data-slide-to="0"
							data-target="#carousel-231541"></li>
						<li data-slide-to="1" data-target="#carousel-231541">
					</ol>
					<div class="carousel-inner">
						<div class="item active">
							<img alt="Carousel Bootstrap First"
								src="resources/photos/slider1.jpg" />

						</div>
						<div class="item">
							<img alt="Carousel Bootstrap Second"
								src="resources/photos/slider2.jpg" />

						</div>
					</div>
					<a class="Left carousel-control" href="#carousel-231541"
						data-slide="prev"><span
						class="glyphicon glyphicon-chevron-left"></span></a> <a
						class="right carousel-control" href="#carousel-231541"
						data-slide="next"><span
						class="glyphicon glyphicon-chevron-right"></span></a>
				</div>
			</div>
		</div>


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

