<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Üye Kaydedilemez...</title>

<!-- CSS -->

<link href="<c:url value='/assets/bootstrap/css/bootstrap.min.css' />"
	rel="stylesheet"></link>
<link
	href="<c:url value='/assets/font-awesome/css/font-awesome.min.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/assets/css/form-elements.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/assets/css/style.css' />" rel="stylesheet"></link>

</head>

<body>

	<!-- Top content -->
	<div class="top-content">

		<div class="container">

			<div class="row">
				<div class="col-sm-12  form-box">
					<div class="alert alert-danger">
						<strong>Bu ePosta ile daha önce bir kullanıcı eklenmiş,
							lütfen kayıtları kontrol ediniz.</strong>
					</div>
				</div>
			</div>
		</div>

	</div>

</body>

</html>