<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>login | BiteCart</title>
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap"
	rel="stylesheet" />
<link rel="stylesheet" href="login.css" />
</head>
<body>
	<div class="login-wrapper">
		<div class="login-box">
			<!-- Title -->
			<h2>
				Welcome Back to <span class="brand">BiteCart</span>
			</h2>
			<p class="tagline">Please re-enter your credentials 🍽️</p>

			<!-- Error Message Display -->
			<%
        String errorMsg = (String) request.getAttribute("error");
        if (errorMsg != null) {
      %>
			<div style="color: red; font-weight: bold; margin-bottom: 15px;">
				<%= errorMsg %>
			</div>
			<%
        }
      %>
			<%
  String success = (String) request.getAttribute("success");
  if (success != null) {
%>
			<div style="color: green; font-weight: bold; margin-bottom: 15px;">
				<%= success %>
			</div>
			<%
  }
%>


			<!-- Re-login Form -->
			<form action="Login" method="post">
				<input type="text" name="username" placeholder="Username or Email"
					required /> <input type="password" name="password"
					placeholder="Password" required />
				<button type="submit">Try Again</button>
			</form>

			<!-- Register Prompt -->
			<p class="link">
				Don’t have an account? <a href="register-customer.html">Register
					Now</a>
			</p>
		</div>
	</div>
</body>
</html>
