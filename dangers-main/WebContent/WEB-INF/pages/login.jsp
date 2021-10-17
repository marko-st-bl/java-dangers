<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>DangerInfo</title>
<!-- Main styles (bootstrap included) -->
<link rel="stylesheet" href="./css/styles.css" />
<!-- Font Awesome icons-->
<script src="https://use.fontawesome.com/releases/v5.13.0/js/all.js"></script>
<link rel="shortcut icon" href="#">
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700"
	rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="container">
		<div class="row justify-content-center align-items-center"  style="height: 100vh">
			<div class="col-md-6">
				<div class="containter bg-light border rounded">
				<div class="text-center my-2">
						<h2 class="text-uppercase text-primary">Login</h2>
					</div>
					<form id="loginForm" name="login" action="?action=login"
						method="POST">
						<div class="row justify-content-center">
							<div class="col-10">
								<div class="form-group">
									<input class="form-control" id="username" type="text"
										placeholder="Username" required="required" name="username"/>
								</div>
							</div>
						</div>
						<div class="row justify-content-center">
							<div class="col-10">
								<div class="form-group">
									<input class="form-control" id="password" type="password"
										placeholder="Password" required="required" name="password"/>
								</div>
							</div>
						</div>
						<div class="text-center">
							<div id="response" class="text-danger text-center my-2" role="alert"><%=session.getAttribute("notification")!=null?session.getAttribute("notification").toString():""%></div>
							<button class="btn btn-primary btn-xl text-uppercase"
								id="loginButton" type="submit">Login</button>
						</div>
					</form>
					<div class="text-center">
						Need an account?<a href="?action=register">Register</a> now
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>