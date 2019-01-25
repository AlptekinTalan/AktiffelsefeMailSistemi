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

<script>
$(document).ready(function() {
	jQuery.extend( jQuery.fn.dataTableExt.oSort, {
	    "de_datetime-asc": function ( a, b ) {
	        var x, y;
	        if (jQuery.trim(a) !== '') {
	            var deDatea = jQuery.trim(a).split(' ');
	            var deTimea = deDatea[1].split(':');
	            var deDatea2 = deDatea[0].split('.');
	                        if(typeof deTimea[2] != 'undefined') {
	                            x = (deDatea2[2] + deDatea2[1] + deDatea2[0] + deTimea[0] + deTimea[1] + deTimea[2]) * 1;
	                        } else {
	                            x = (deDatea2[2] + deDatea2[1] + deDatea2[0] + deTimea[0] + deTimea[1]) * 1;
	                        }
	        } else {
	            x = -Infinity; // = l'an 1000 ...
	        }
	 
	        if (jQuery.trim(b) !== '') {
	            var deDateb = jQuery.trim(b).split(' ');
	            var deTimeb = deDateb[1].split(':');
	            deDateb = deDateb[0].split('.');
	                        if(typeof deTimeb[2] != 'undefined') {
	                            y = (deDateb[2] + deDateb[1] + deDateb[0] + deTimeb[0] + deTimeb[1] + deTimeb[2]) * 1;
	                        } else {
	                            y = (deDateb[2] + deDateb[1] + deDateb[0] + deTimeb[0] + deTimeb[1]) * 1;
	                        }
	        } else {
	            y = -Infinity;
	        }
	        var z = ((x < y) ? -1 : ((x > y) ? 1 : 0));
	        return z;
	    },
	 
	    "de_datetime-desc": function ( a, b ) {
	        var x, y;
	        if (jQuery.trim(a) !== '') {
	            var deDatea = jQuery.trim(a).split(' ');
	            var deTimea = deDatea[1].split(':');
	            var deDatea2 = deDatea[0].split('.');
	                        if(typeof deTimea[2] != 'undefined') {
	                            x = (deDatea2[2] + deDatea2[1] + deDatea2[0] + deTimea[0] + deTimea[1] + deTimea[2]) * 1;
	                        } else {
	                            x = (deDatea2[2] + deDatea2[1] + deDatea2[0] + deTimea[0] + deTimea[1]) * 1;
	                        }
	        } else {
	            x = Infinity;
	        }
	 
	        if (jQuery.trim(b) !== '') {
	            var deDateb = jQuery.trim(b).split(' ');
	            var deTimeb = deDateb[1].split(':');
	            deDateb = deDateb[0].split('.');
	                        if(typeof deTimeb[2] != 'undefined') {
	                            y = (deDateb[2] + deDateb[1] + deDateb[0] + deTimeb[0] + deTimeb[1] + deTimeb[2]) * 1;
	                        } else {
	                            y = (deDateb[2] + deDateb[1] + deDateb[0] + deTimeb[0] + deTimeb[1]) * 1;
	                        }
	        } else {
	            y = -Infinity;
	        }
	        var z = ((x < y) ? 1 : ((x > y) ? -1 : 0));
	        return z;
	    },
	 
	    "de_date-asc": function ( a, b ) {
	        var x, y;
	        if (jQuery.trim(a) !== '') {
	            var deDatea = jQuery.trim(a).split('.');
	            x = (deDatea[2] + deDatea[1] + deDatea[0]) * 1;
	        } else {
	            x = Infinity; // = l'an 1000 ...
	        }
	 
	        if (jQuery.trim(b) !== '') {
	            var deDateb = jQuery.trim(b).split('.');
	            y = (deDateb[2] + deDateb[1] + deDateb[0]) * 1;
	        } else {
	            y = -Infinity;
	        }
	        var z = ((x < y) ? -1 : ((x > y) ? 1 : 0));
	        return z;
	    },
	 
	    "de_date-desc": function ( a, b ) {
	        var x, y;
	        if (jQuery.trim(a) !== '') {
	            var deDatea = jQuery.trim(a).split('.');
	            x = (deDatea[2] + deDatea[1] + deDatea[0]) * 1;
	        } else {
	            x = -Infinity;
	        }
	 
	        if (jQuery.trim(b) !== '') {
	            var deDateb = jQuery.trim(b).split('.');
	            y = (deDateb[2] + deDateb[1] + deDateb[0]) * 1;
	        } else {
	            y = Infinity;
	        }
	        var z = ((x < y) ? 1 : ((x > y) ? -1 : 0));
	        return z;
	    }
	} );
	$('#example').dataTable( {
		"pageLength": 50, 
		columnDefs: [
	       { type: 'de_date', targets: 3 }
	     ]
	  } );
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
						action="searchEtkinlik">

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

	<div class="alert alert-success">
		<strong>${fn:length(etkinlikListe)}</strong> Etkinlik Listelendi .
	</div>

	<sec:authorize access="hasRole('ROLE_ADMIN')">

		<c:if test="${listAllEtkinlik==true}">

			<canvas id="myChart" width="300" height="100"></canvas>


			<script>
				var ctx = document.getElementById('myChart').getContext('2d');
				var chart = new Chart(ctx, {
					// The type of chart we want to create
					type : 'bar',

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
				            yAxes: [{
				                ticks: {
				                    beginAtZero:true
				                }
				            }]
				        }
				    }
				});
			</script>
		</c:if>

	</sec:authorize>

	<div class="panel-body">
		<h6>
			<!-- 
			<c:if test="${empty etkinlikListe}">
				<div class="alert alert-danger">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Böyle bir etkinlik yok :(((</strong>
				</div>
			</c:if>
			 -->
			<c:if test="${not empty etkinlikListe}">

				<table id="example"
					class="table table-striped table-bordered table-hover">
					<thead style="background-color: #DFF0D8;">
						<tr>
							<th>Id</th>
							<th>Etkinlik Adı</th>
							<th>Etkinlik Yeri</th>
							<th>Etkinlik Tarihi</th>
							<th>Etkinlik Saati</th>
							<th>Gönderilme Sayısı</th>
							<th>Gönderilmeme Sayısı</th>
							<sec:authorize access="hasRole('ROLE_ADMIN')">
								<th>Etkinlik Şube</th>
								<th>Katılımcı Düzenle/Listele</th>
								<th>Görevli Düzenle/Listele</th>
								<th>Eğitmen Düzenle/Listele</th>
							</sec:authorize>

							<sec:authorize access="hasRole('ROLE_ISTANBULBAKIRKOY')">
								<th>Katılımcı Listele</th>
							</sec:authorize>

							<th>Etkinlik Düzenle</th>
							<th>Etkinliği Sil</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${etkinlikListe}" var="etkinlik">
							<tr>
								<th><c:out value="${etkinlik.etkinlikId}" /></th>
								<th><c:out value="${etkinlik.etkinlikAdi}" /></th>
								<th><c:out value="${etkinlik.etkinlikAdresi}" /></th>
								<th><fmt:formatDate value="${etkinlik.etkinlikTarihi}"
										pattern="dd.MM.yyyy" /></th>
								<th><c:out value="${etkinlik.etkinlikSaati}" /></th>
								<th><c:out value="${etkinlik.etkinlikGonderilmeSayisi}" /></th>
								<jsp:useBean id="now" class="java.util.Date" />
								<c:if test="${now lt etkinlik.etkinlikTarihi}">
									<th><c:out value="0" /></th>

								</c:if>
								<c:if test="${now ge etkinlik.etkinlikTarihi}">
									<th><c:out value="${etkinlik.etkinlikGonderilememeSayisi}" /></th>

								</c:if>
								<sec:authorize access="hasRole('ROLE_ADMIN')">

									<th><c:out value="${etkinlik.sube.subeAdi}" /></th>
									<th><a
										href="listKatilimciToEtkinlik?etkinlikId=<c:out value='${etkinlik.etkinlikId}'/>">Katılımcı
											Düzenle/Listele</a></th>
									<th><a
										href="listGorevliToEtkinlik?etkinlikId=<c:out value='${etkinlik.etkinlikId}'/>">Görevli
											Düzenle/Listele</a></th>
									<th><a
										href="listEgitmenToEtkinlik?etkinlikId=<c:out value='${etkinlik.etkinlikId}'/>">Eğitmen
											Düzenle/Listele</a></th>

								</sec:authorize>

								<sec:authorize access="hasRole('ROLE_ISTANBULBAKIRKOY')">
									<th><a
										href="listKatilimciToEtkinlik?etkinlikId=<c:out value='${etkinlik.etkinlikId}'/>">Katılımcı
											Listele</a></th>
								</sec:authorize>

								<th><a
									href="editEtkinlik?id=<c:out value='${etkinlik.etkinlikId}'/>">Etkinliği
										Düzenle</a></th>
								<th><a
									href="removeEtkinlik?id=<c:out value='${etkinlik.etkinlikId}'/>">Etkinliği
										Sil</a></th>

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