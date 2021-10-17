<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="userBean" class="marko.ip.beans.UserBean"
	scope="session" />

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>DangerInfo - Profile</title>
<link rel="stylesheet" href="css/styles.css" />
<script src="./js/validateProfileForm.js"></script>
</head>

<body class="bg-light">
	<!--Register form-->
	<section class="page-section" id="#register">
		<div class="container justify-content-center align-items-center">
			<div class="text-center">
				<h2 class="section-heading text-uppercase text-primary">Profile</h2>
				<h3 class="section-subheading text-muted">Please fill up the
					form with your info.</h3>
			</div>
			<div class="row justify-content-center align-items-center">
				<div class="col-md-6 col-lg-5">				
			<form id="registerForm" onsubmit="return validateForm()" action="?action=updateUserProfile"
				method="POST" enctype="multipart/form-data" novalidate>
				<div class=" row justify-content-center form-group">
					<input class="form-control" id="firstName"
						type="text" placeholder="First Name " name="firstName"
						required="required" value="<%=userBean.getUser().getFirstName()%>"/>
				</div>
				<div class="row justify-content-center form-group">
					<input class="form-control" id="lastName"
						type="text" placeholder="Last Name " name="lastName"
						required="required" value="<%=userBean.getUser().getLastName()%>"/>
				</div>
				<div class="form-group row justify-content-center">
					<input class="form-control" id="username"
						type="text" placeholder="Username " name="username"
						required="required" value="<%=userBean.getUser().getUsername()%>"/>
				</div>
				<div class="form-group row justify-content-center">
					<input class="form-control" id="email"
						type="email" placeholder="Email " name="email" required="required"
						value="<%=userBean.getUser().getEmail()%>"/>
				</div>
				<div class="row justify-content-center">
						<a id="changePassword" href="#passwordOld">Change password</a>
					
				</div>
				<div class="form-group row justify-content-center">
					<input class="form-control" id="passwordOld"
						type="password" placeholder="Old password " name="oldPassword"
						style="display: none" />
				</div>
				<div class="form-group row justify-content-center">
					<input class="form-control" id="password1"
						type="password" placeholder="Password " name="password1"
						style="display: none" />
				</div>
				<div class="form-group row justify-content-center">
					<input class="form-control" id="password2"
						type="password" placeholder="Repeat Password " name="password2"
						style="display: none" />
				</div>
				<div class="form-group row justify-content-center">
					<select class="form-control" id="countries"
						name="country" onchange="loadRegions()">
						<option value="" disabled selected>Select country</option>
					</select>
					<p class="help-block text-danger"></p>
				</div>
				<div class="form-group row justify-content-center">
					<select class="form-control" id="regions"
						name="region" onchange="loadCities()">
						<option value="" disabled selected>Select region</option>
					</select>
					<p class="help-block text-danger"></p>
				</div>
				<div class="form-group row justify-content-center">
					<select class="form-control" id="cities"
						name="city">
						<option value="" disabled selected>Select city</option>
					</select>
				</div>
				<div class="row justify-content-center">
						<label for="img">Profile picture:</label>
					
				</div>
				<div class="form-group  row justify-content-center">
					<div class="form-control">
						<input type="file" id="img" name="img" accept="image/*" />
					</div>
				</div>
				<div class="form-group row justify-content-center">
					<div class="form-control align-items-left">
						<input type="checkbox" id="subscribeApp" name="notification"
							value="notificationApp" /> <label for="subscribeApp">Send
							me notifications in app.</label>
					</div>
				</div>
				<div class="form-group row justify-content-center">
					<div class="form-control">
						<input type="checkbox" id="subscribeEmail" name="notification"
							value="notificationEmail" /> <label for="subscribeEmail">Send
							me notifications on email.</label>
					</div>
				</div>
				<input type="hidden" id="flag" name="flag" value="" /> 
				<input type="hidden" id="a2c" name="a2c" value="" />
				<p id="validationResult" class="row help-block justify-content-center text-danger"><%= session.getAttribute("notification") %></p>
				<div class="text-center">
					<div id="success"></div>
					<button class="btn btn-primary btn-xl text-uppercase"
						id="registerButton" type="submit">Save</button>
				</div>
			</form>
				</div>
			</div>
		</div>
	</section>
	<!--Location info script-->
	<script>
    document.body.onload = loadCountries();
    document.getElementById('changePassword').addEventListener('click', showPassword);
    let countries;

    function loadCountries() {
        let xhr = new XMLHttpRequest();
        xhr.open('GET', 'https://restcountries.com/v3.1/region/europe', 'true');
        xhr.onload = function () {
            if (this.status == 200) {
                countries = JSON.parse(this.responseText);
                let output = '';
                for(let i in countries){
                    output += '<option value="' + countries[i].name.common + '">' +
                        countries[i].name.common +
                        '</option>'
                }
                document.getElementById('countries').innerHTML += output;
            }
        }
        xhr.send();
    }

    function loadRegions() {
        let a2c;
        for(let i in countries){
            if(countries[i].name.common == document.getElementById('countries').value){
                a2c = countries[i].cca2;
                document.getElementById('flag').value = countries[i].flags.png;
                document.getElementById('a2c').value = countries[i].cca2;
            }
        }
        
        url='http://battuta.medunes.net/api/region/' + a2c + '/all/?key=ebc3d8dc69d0c08f178c0e386a242f64&callback=cb';
        JsonpHttpRequest(url,'cb', 'regions');
    }

    function loadCities(){
        let reg = document.getElementById('regions').value;
        let a2c = document.getElementById('a2c').value;
        url='http://battuta.medunes.net/api/city/' + a2c + '/search/?region=' + reg + '&key=ebc3d8dc69d0c08f178c0e386a242f64&callback=cb';
        JsonpHttpRequest(url, 'cb', 'cities');
    }

    function JsonpHttpRequest(url, callback, type) {
    var e = document.createElement('script');
    e.src = url;
    document.body.appendChild(e);
    window[callback] = (data) => {
        console.log(data);
        output = '';
        for(let i in data){
            if(type == 'regions'){
                output += '<option value"' + data[i].region + '">' + data[i].region + "</option>";
            } else if(type == 'cities'){
                output += '<option value"' + data[i].region + '">' + data[i].city + "</option>";
            }
        }
        document.getElementById(type).innerHTML = output;
    }
}
    function showPassword(){
        document.getElementById('passwordOld').style.display = 'block';
        document.getElementById('password1').style.display = 'block';
        document.getElementById('password2').style.display = 'block';
    }

    </script>
</body>

</html>