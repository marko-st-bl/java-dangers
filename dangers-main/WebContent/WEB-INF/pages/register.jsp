<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="userBean" class="marko.ip.beans.UserBean"
	scope="session" />

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Danger Info</title>
<link rel="stylesheet" href="./css/styles.css" />
<script src="./js/validateRegisterForm.js"></script>
</head>

<body class="bg-light">
	<!--Register form-->
	<section class="page-section" id="#register">
		<div class="container justify-content-center align-items-center">
			<div class="text-center">
				<h2 class="section-heading text-uppercase text-primary">Register</h2>
				<h3 class="section-subheading text-muted">Please fill up the
					form with your info.</h3>
			</div>
			<div class="row form-container justify-content-center align-items-center">
				<div class="col-md-6 col-lg-5">
					<form id="registerForm" onsubmit="return validateForm()"
						action="?action=registerUser" method="POST" novalidate>
						<div class=" row justify-content-center form-group">
							<input class="form-control" id="fname" type="text"
								placeholder="First Name " name="firstName" required />
						</div>
						<div class="row justify-content-center form-group">
							<input class="form-control" id="lname" type="text"
								placeholder="Last Name " name="lastName" required />
						</div>
						<div class="form-group row justify-content-center">
							<input class="form-control" id="username" type="text"
								placeholder="Username " name="username" required />
						</div>
						<div class="form-group row justify-content-center">
							<input class="form-control" id="email" type="email"
								placeholder="Email " name="email" />
						</div>
						<div class="form-group row justify-content-center">
							<input class="form-control" id="password1" type="password"
								placeholder="Password " name="password1" required />
						</div>
						<div class="form-group row justify-content-center">
							<input class="form-control" id="password2" type="password"
								placeholder="Repeat Password " required name="password2" />
						</div>
						<p id="validationResult"
							class="row help-block justify-content-center text-center text-danger"><%=session.getAttribute("notification")%></p>
						<div class="text-center">
							<div id="success"></div>
							<button class="btn btn-primary btn-xl text-uppercase"
								id="registerButton" type="submit">Register</button>
						</div>
					</form>
				</div>
			</div>
			<div class="text-center">
				Already have an acount? <a href="?action=index">Login</a>
			</div>
		</div>
	</section>
</body>

</html>