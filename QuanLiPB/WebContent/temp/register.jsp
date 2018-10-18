<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/register.css">
<script src="js/register.js"></script>
<div id="registerform" class="modalregister">
	<form class="modalregister-content animateregister" action="/action_page.php">
		<div class="imgcontainerregisterregister">
			<span
				onclick="document.getElementById('registerform').style.display='none'"
				class="closeregister" title="Close">&times;</span> <img
				src="img/profile.jpg" alt="Avatar" class="avatar">
		</div>
		<div class="containerregister">
			<label><b>Username</b></label> <input type="text" placeholder="Enter Username" name="username" id="username" required> 
			<label><b>Email</b></label> <input type="text" placeholder="Enter Email" name="useremail" id="useremail" required> 
			
			<label><b>Password</b></label> <input type="password" placeholder="Enter Password" name="password" id="password" required>
			<label><b>Password</b></label> <input type="password" placeholder="Enter Again" name="cpassword" id="cpassword" required>

			<button type="submit">Register</button>
			<input type="checkbox" checked="checked"> Remember me
		</div>

		<div class="containerregister" style="background-color: #f1f1f1">
			<button type="button"
				onclick="document.getElementById('registerform').style.display='none'"
				class="cancelbtnregister">Cancel</button>
			<span class="psw">Forgot <a href="#">password?</a></span>
		</div>
	</form>
</div>