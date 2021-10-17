<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="marko.ip.dto.Category" %>
<jsp:useBean id="categoryBean" class="marko.ip.beans.CategoryBean"
	scope="session" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danger Info | Warn</title>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
<link rel="stylesheet" href="css/styles.css" />
<!-- Bootstrap core JS-->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js"></script>
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic"
	rel="stylesheet" type="text/css" />
<!-- Google Maps-->
<script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
</head>
<body class="bg-light">
<!--Navbar-->
    <nav class="navbar sticky-top navbar-expand-md navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="#">Dangers Info</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-ckntrols="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse ml-auto" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item"><a href="?action=home" class="nav-link">Home</a></li>
                    <li class="nav-item"><a href="?action=profile" class="nav-link">Profile</a></li>
                    <li class="nav-item"><a href="?action=warning" class="nav-link">Warn</a></li>
                    <li class="nav-item"><a href="?action=logout" class="nav-link">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
	<section class="page-section">
        <div class="container">
            <div class="text-center">
                <h2 class="section-heading text-uppercase text-primary">Warn</h2>
                <h3 class="section-subheading text-muted">You know about any threats?</h3>
            </div>
            <form id="warn-form" action="?action=addWarning" method="POST">
            <div class="row justify-content-center">
            	<div class="col-md-6 col-lg-5 text-muted">
            		Description
            	</div>
            </div>
            <div class="form-group row justify-content-center">
            	<input class="form-control col-md-6 col-lg-5" name="description" type="text" required/>
            </div>
            
                <div class="row justify-content-center">
                    <div class="col-md-6 col-lg-5 text-muted">
                        Categories
                    </div>
                </div>
                <div class="form-group row justify-content-center">
                    <select multiple class="form-control col-md-6 col-lg-5" name="category" size="3" required>
                        <%for(Category cat: categoryBean.getAllCategories()){
                        	out.print("<option value=\"" + cat.getId() + "\">" + cat.getName() + "</option>");
                        }
                        %>
                    </select>
                </div>
                <div class="row justify-content-center">
                    <div class="form-check col-md-6 col-lg-5">
                        <input type="checkbox" class="form-check-input" name="urgent">
                        <label class="form-check-label">Is Urgent?</label>
                    </div>
                </div>
                <div class="row justify-content-center">
                    <div class="col-md-6 col-lg-5 py-2 text-muted">Location</div>
                </div>
                <div class="form-group row justify-content-center">
                    <input id="lat" class="form-control col-md-6 col-lg-5" name="lat" type="number" step="0.000001"
                        min="-90" max="90" placeholder="Latitude">
                </div>
                <div class="form-group row justify-content-center">
                    <input id="lon" class="form-control col-md-6 col-lg-5" name="lng" type="number" step="0.000001"
                        min="-180" max="180" placeholder="Longitude">
                </div>
                <div class="text-danger text-center"><%= session.getAttribute("notification") %></div>
                <div class="text-center">
                    <button class="btn btn-primary btn-xl text-uppercase" id="registerButton" type="submit">Add</button>
                </div>
            </form>
        </div>
    </section>
    <div class="container">
        <div id="map">
        </div>
    </div>
    <script>
        "use strict";
        
        let map;
        let lastMarker;

        function initMap() {
        	var myOptions = {
        		      zoom: 4,
        		      mapTypeControl: true,
        		      mapTypeControlOptions: {style: google.maps.MapTypeControlStyle.DROPDOWN_MENU},
        		      navigationControl: true,
        		      navigationControlOptions: {style: google.maps.NavigationControlStyle.SMALL},
        		      mapTypeId: google.maps.MapTypeId.ROADMAP      
        		    }
            map = new google.maps.Map(document.getElementById('map'), myOptions);

            map.addListener('click', function (e) {
                placeMarkerAndPanTo(e.latLng, map);
            });
        }
        
        if(!navigator.geolocation) {
            alert('Geolocation is not supported by your browser');
          } else {
            navigator.geolocation.getCurrentPosition(success, error);
          }
        
        function success(position){
        	var pos=new google.maps.LatLng(position.coords.latitude,position.coords.longitude);
        	map.setCenter(pos);
        	map.setZoom(14);
        }
        
        function error(){
        	alert('error');
        }

        function placeMarkerAndPanTo(latLng, map) {
            var marker = new google.maps.Marker({
                position: latLng,
                map: map
            });
            if (lastMarker) {
                lastMarker.setMap(null);
            }
            lastMarker = marker;
            map.panTo(latLng);
            console.log(latLng.toString())
            let matches = latLng.toString().match(/\((.*?)\)/);
            console.log(matches);
            let lat = matches[1].split(', ')[0];
            console.log(parseFloat(lat).toFixed(6));
            document.getElementById('lat').value = parseFloat(matches[1].split(', ')[0]).toFixed(6);
            document.getElementById('lon').value = parseFloat(matches[1].split(', ')[1]).toFixed(6);
        }
    </script>
    <script src="https://maps.googleapis.com/maps/api/js?callback=initMap&libraries=&v=weekly" defer></script>
</body>
</html>