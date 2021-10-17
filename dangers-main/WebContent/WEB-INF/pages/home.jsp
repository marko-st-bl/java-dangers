<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collections"%>
<%@page import="marko.ip.dto.Warning"%>
<%@page import="marko.ip.dto.Category"%>
<jsp:useBean id="userBean" class="marko.ip.beans.UserBean"
	scope="session" />
<jsp:useBean id="warningBean" class="marko.ip.beans.WarningBean"
	scope="session" />
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Dangers Info</title>
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.13.0/js/all.js"></script>
<link rel="shortcut icon" href="#">
<link rel="stylesheet" href="css/styles.css" />
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic"
	rel="stylesheet" type="text/css" />
<!-- My scripts -->
<script src="./js/weather.js"></script>
<script src="./js/warnings.js"></script>
<script src="./js/posts.js"></script>
<script src="./js/postForm.js"></script>
</head>

<body class="bg-light">
	<!--Navbar-->
	<nav class="navbar sticky-top navbar-expand-md navbar-dark bg-dark shadow-sm">
		<div class="container">
			<a class="navbar-brand" href="#">Dangers Info</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-ckntrols="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse ml-auto"
				id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item"><a href="?action=home" class="nav-link">Home</a></li>
					<li class="nav-item"><a href="?action=profile"
						class="nav-link">Profile</a></li>
					<li class="nav-item"><a href="?action=warning"
						class="nav-link">Warn</a></li>
					<li class="nav-item"><a href="?action=logout" class="nav-link">Logout</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<div id="main-page" class="container">
		<div class="row">
			<!--SIDEBAR-->
			<div class="profile-sidebar align-self-start col-md-4 col-lg-3 overflow-auto">
			<div class="bg-white border rounded shadow-sm py-2">			
				<div class="avatar row justify-content-center">
					<img class="rounded-circle"
						src="<%=userBean.getUser().getAvatar()%>" />
				</div>
				<div id="firstName" class="row justify-content-center">
					<h5><%=userBean.getUser().getFirstName()%></h5>
				</div>
				<div id="lastName" class="row justify-content-center">
					<h5><%=userBean.getUser().getLastName()%></h5>
				</div>
				<div class="row justify-content-center text-muted" id="numOfLogin">
					<%=userBean.getNumOfLogins()%> logins
				</div>
			</div>
			<%
			List<Warning> warnings = warningBean.getUrgentWarnings();
			Collections.sort(warnings);
			
			if(userBean.getUser().isNotificationApp()){	
				out.print("<div class=\"row justify-content-center my-2 mx-0 bg-white text-primary\">"
				+			"<button type=\"button\" class=\"btn btn-outline-danger btn-block\""
				+				"data-toggle=\"collapse\" data-target=\"#warn-list\" aria-expanded=\"false\" aria-controls=\"warn-list\">"
				+				"Notifications <span id=\"countNotifications\" class=\"badge badge-danger\">"
				+				warnings.size()
				+				"</span></button>"
				+		"</div>"
				+		"<div class=\"notifications-container mb-3\">"
				+			"<div id=\"warn-list\" class=\"list-group collapse show\">");
					
						for(Warning warn:warnings){
							if(warn.isUrgent()){
							out.print("<a id=\"warn-"+warn.getId()+"\"");
							if(warn.isLocationSet()){
								out.print("target=\"_blank\" href=\"https://www.google.com/maps/@" +warn.getLat() +","+ warn.getLng()+",15z\"");
							}else{
								out.print("href=\"#\"");
							}						
							out.print("class=\"list-group-item list-group-item-action flex-column align-items-start\">"
							+ "<div class=\"d-flex w-100 justify-content-between\">"
							+	"<h5 class=\"mb-1\">");
							for(Category cat:warn.getCategories()){
								out.print(cat.getName() + " ");
							}
							out.print("</h5>");
							if(warn.isLocationSet()){
								out.print("<i class=\"fas fa-map-marker-alt\"></i>");
							}
								
							out.print("</div>"
							+"<p class=\"mb-1\">");
							out.print(warn.getDescription()
							+"</p> <small>"
							+ warn.getFormattedDate() 
							+ "</small>"
							+ "</a>");
							}
						}
						out.print("</div></div>");
			}
						%>
					
				
			</div>
			<!--MAIN-->
			<div class="posts-container col-md-8 col-lg-7">
				<!--POST-FORM-->
				<div class="post-form-container bg-white shadow-sm border rounded">
					<div class="post-form-header">
						<h3 class="text-primary">Add new post...</h3>
					</div>
					<div class="post-form">
						<form id="new-post-form" class="form"
							action="http://localhost:8080/dangers-main/NewPost" method="POST"
							enctype="multipart/form-data">
							<div class="row justify-content-center">
								<textarea id="post-text" class="form-control col-11" name="text"
									cols="30" rows="3"
									placeholder="Share text, image, video or link"></textarea>
							</div>
							<div class="row justify-content-center">
								<div class="col-11">
									<button id="img-btn" class="btn" type="button">
										<i class="fas fa-image"></i>
									</button>
									<button id="video-btn" class="btn" type="button">
										<i class="fas fa-video"></i>
									</button>
									<button id="yt-btn" class="btn" type="button" data-toggle="collapse" 
										data-target="#youtube-input-div" aria-expanded="false" aria-controls="youtube-input-div">
										<i class="fab fa-youtube"></i>
									</button>
									<button id="link-btn" class="btn" type="button" data-toggle="collapse" 
										data-target="#link-input-div" aria-expanded="false" aria-controls="link-input-div">
										<i class="fas fa-link"></i>
									</button>
								</div>
							</div>
							<input type="file" id="img-input" name="img" accept="image/*"
								style="display: none" /> <input type="file" id="video-input"
								name="video" accept="video/*" style="display: none" /> <input
								type="text" id="post-type" name="type" value="text"
								style="display: none" />
							<div id="link-input-div" class="row justify-content-center collapse">
								<input id="link-input" class="form-control p-2 col-11"
									type="url" id="link-input" name="link" placeholder="Paste url" />
							</div>
							<div id="youtube-input-div" class="row justify-content-center collapse">
								<input id="youtube-input" class="p-2 col-11 form-control"
									type="url" name="youtube" placeholder="Paste youtube link"/>
							</div>
							<div class="row justify-content-center">
								<img id="img-preview" class="p-1 col-11" src="">
								<video id="video" class="p-1 col-11" width="100%" controls
									style="display: none">
									<source id="video-preview" src="" type="video/mp4" />
									<source src="" type="video/ogg" />
								</video>
							</div>

							<div class="text-right">
								<input type="submit" value="Add" class="btn btn-primary m-2" />
							</div>
						</form>
					</div>
					<div id="validation-result" class="text-danger text-center"></div>
				</div>
				<!--POSTS-->
				<div class="posts"></div>
			</div>
			<!--WEATHER-->
			<div class="forecasts align-self-start col-lg-2">
				<div class="forecast bg-white border rounded shadow-sm mb-2">
					<div class="row justify-content-center p-2">
						<h6 class="cityName text-center" id="cityName1"></h6>
					</div>
					<div class="row justify-content-around align-items-center mx-0">
						<div>
							<p class="m-0"><small id="maxtemp1" class="text-muted"><i class="fas fa-angle-up"></i></small></p>
							<p class="m-0"><small id="mintemp1" class="text-muted"><i class="fas fa-angle-down"></i></small></p>
						</div>
						<img id="icon1" src="" alt="">
					</div>
					<div class="row justify-content-center">
						<div id="temp1" class="temperature"></div>
					</div>
				</div>
				<div class="forecast bg-white border rounded shadow-sm mb-2">
					<div class="row justify-content-center p-2">
						<h6 class="cityName text-center" id="cityName2"></h6>
					</div>
					<div class="row justify-content-around align-items-center mx-0">
						<div>
							<p class="m-0"><small id="maxtemp2" class="text-muted"><i class="fas fa-angle-up"></i></small></p>
							<p class="m-0"><small id="mintemp2" class="text-muted"><i class="fas fa-angle-down"></i></small></p>
						</div>
						<img id="icon2" src="" alt="">
					</div>
					<div class="row justify-content-center">
						<div id="temp2" class="temperature"></div>
					</div>
				</div>
				<div class="forecast bg-white border rounded shadow-sm mb-2">
					<div class="row justify-content-center p-2">
						<h6 class="cityName text-center" id="cityName3"></h6>
					</div>
					<div class="row justify-content-around align-items-center mx-0">
						<div>
							<p class="m-0"><small id="maxtemp3" class="text-muted"><i class="fas fa-angle-up"></i></small></p>
							<p class="m-0"><small id="mintemp3" class="text-muted"><i class="fas fa-angle-down"></i></small></p>
						</div>
						<img id="icon3" src="" alt="">
					</div>
					<div class="row justify-content-center">
						<div id="temp3" class="temperature"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js"></script>
	<!-- JQuery-->
	<script src="./js/jquery-3.5.1.js"></script>
	<script
      src="https://maps.googleapis.com/maps/api/js?libraries=&v=weekly"
      defer
    ></script>
</body>
<!--Script-->
<script>
    //ADD EVENT LISTENERS

        
    window.addEventListener('DOMContentLoaded', function(){
    	//LOAD FORECAST
    	showWeathetForecasts('<%= userBean.getUser().getCountry() %>');
    	addToggleListeners();
		// ADD POST FORM SUBMIT LISTENER
		addPostFormSubmitListener();
		// ADD PREVIEW LISTENER
		addPreviewListener();
		// LOAD WARNINGS
		<% if(userBean.getUser().isNotificationApp()){ %>
		loadWarnings();
		setInterval(loadWarnings, 5000);
		<% } %>
		// LOAD POSTS
		getPosts();
		setInterval(getPosts, 30000);
    });


</script>
</body>

</html>