<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet"
	href="/resources/css/bootstrap.css">

<!-- Load jQuery and bootstrap datepicker scripts -->
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/datepicker.js"></script>

</head>
<body>

	<div class="container">
		<h2>Aktiffelsefe Bilgi Sistemi</h2>
		<c:if test="${durum eq true}">

			<div class="alert alert-success">
				<strong>Tebrikler!</strong> Artık Etkinliklerimizden Haberdar
				Olmayacaksınız...</a>.
			</div>

		</c:if>
		<c:if test="${durum eq false}">

			<div class="alert alert-danger">
				<strong>ePosta Bulunamadı !</strong> Girdiğiniz ePosta Adresi
				Kayıtlarımızda Bulunamadı.
			</div>

		</c:if>
	</div>

</body>
</html>