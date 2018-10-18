<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
</head>
<body>
	<noscript>
<style>
.pagecontainer {
	display: none;
}
</style>
		<div class="noscriptmsg">You don't have javascript enabled. Good luck
			with that.</div>
	</noscript>
	<form id="frm_details" method="post" name="frm_details" action = "../AjaxServlet">
		<input id="email" name="email" placeholder="Your Email id" type="text" />
		<button class="subscribe-box__btn" type="submit">Need
			Assistance</button>
	</form>
	<div id="message"></div>

	<script type="text/javascript" src="ajax.js"></script>
</body>
</html>