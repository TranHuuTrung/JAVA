<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/login.css">
<script src="js/login.js"></script>
<div id="loginform" class="modallogin">
	<form class="modallogin-content animatelogin" action="/action_page.php">
		<div class="imgcontainerloginlogin">
			<span
				onclick="document.getElementById('loginform').style.display='none'"
				class="closelogin" title="Close">&times;</span> <img
				src="img/profile.jpg" alt="Avatar" class="avatar">
		</div>
		<div class="containerlogin">
			<label><b>Username</b></label> <input type="text"
				placeholder="Enter Username" name="uname" required> <label><b>Password</b></label>
			<input type="password" placeholder="Enter Password" name="psw"
				required>

			<button type="submit">Login</button>
			<input type="checkbox" checked="checked"> Remember me
		</div>

		<div class="containerlogin" style="background-color: #f1f1f1">
			<button type="button"
				onclick="document.getElementById('loginform').style.display='none'"
				class="cancelbtnlogin">Cancel</button>
			<span class="psw">Forgot <a href="#">password?</a></span>
		</div>
	</form>
</div>