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

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet" />

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<script type="text/javascript">
		$(function() {
			$('#yil').change(function() {
				$('#yilsec').val($(this).val());
			}).change(); // Trigger the event
		});
	</script>

<title>Etkinlikler</title>


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
					<form class="navbar-form navbar-left" role="search"
						action="raporAllEtkinlik">

						<select class="selectpicker" id="yil">
							<option value="2018" selected="selected">2018</option>
							<option value="2017" selected="">2017</option>

						</select> 
						<input
							type='hidden' class="form-control" name='yil'
							id='yilsec' value="" />

						<button type="submit" class="btn btn-default">Ara</button>
					</form>
				</div>

			</nav>
		</div>
	</div>
	<!-- HEADER ------------------------------------------------------------- -->

	<div class="alert alert-success">
		<strong>${fn:length(etkinlikListe)}</strong> Etkinlik Listelendi .
	</div>

	<c:if test="${listAllEtkinlik==true}">

		<canvas id="myChart" width="300" height="100"></canvas>


		<script>
				var ctx = document.getElementById('myChart').getContext('2d');
				var chart = new Chart(ctx, {
					// The type of chart we want to create
					type : 'horizontalBar',

					// The data for our dataset
					data : {
						labels : ${deger1},
						datasets : [ {
							label : "Toplam Gönderilen Mail Sayısı",
							backgroundColor : 'rgb(189, 238, 170)',
							borderColor : 'rgb(189, 238, 170)',
							data : ${deger2},
						} ]
					},

					// Configuration options go here
					options : {
				        scales: {
				            xAxes: [{
				                ticks: {
				                    beginAtZero:true
				                }
				            }]
				        }
				    }
				});
			</script>
	</c:if>

	<canvas id="barChart" width="300" height="100"></canvas>

	<script>
				var ctx = document.getElementById('barChart').getContext('2d');
				var chart = new Chart(ctx, {
					// The type of chart we want to create
					type : 'horizontalBar',

					// The data for our dataset
					data : {
						labels : ${aylar},
						datasets : [ {
							label : "Ay Bazlında Gönderilen Mail Sayısı",
							backgroundColor : 'rgb(48, 245, 245)',
							borderColor : 'rgb(189, 238, 170)',
							data : ${aybasliGonderilenMailSayisi},
						} ]
					},

					// Configuration options go here
					options : {
				        scales: {
				            xAxes: [{
				                ticks: {
				                    beginAtZero:true
				                }
				            }]
				        }
				    }
				});
			</script>

	<canvas id="barChart2" width="300" height="100"></canvas>

	<script>
				var ctx = document.getElementById('barChart2').getContext('2d');
				var chart = new Chart(ctx, {
					// The type of chart we want to create
					type : 'horizontalBar',

					// The data for our dataset
					data : {
						labels : ${aylar},
						datasets : [ {
							label : "Ay Bazında Gönderilemeyen Mail Sayısı",
							backgroundColor : 'rgb(255, 127, 39)',
							borderColor : 'rgb(189, 238, 170)',
							data : ${aybasliGonderilemeyenMailSayisi},
						} ]
					},

					// Configuration options go here
					options : {
				        scales: {
				            xAxes: [{
				                ticks: {
				                    beginAtZero:true
				                }
				            }]
				        }
				    }
				});
			</script>

</body>
</html>