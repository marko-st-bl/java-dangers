function validateForm(){
	let form = document.forms[0];
	
	let firstName = form.elements.firstName.value;
	let lastName = form.elements.lastName.value;
	let username = form.elements.username.value;
	let email = form.elements.email.value;
	let oldPass = form.elements.oldPassword.value;
	let password1 = form.elements.password1.value;
	let password2 = form.elements.password2.value;
	let country = form.elements.country.value;
	
	const message = document.getElementById('validationResult');
	const namePattern = /^[A-Za-z]+$/;
	const usernamePattern = /^[A-Za-z0-9]+$/;
	const emailPattern = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
	
	if(firstName == ""){
		message.innerHTML = "You must enter first name!";
		return false;
	} else if(!namePattern.test(firstName)){
		message.innerHTML = "First name cannot contain numbers or symbols.";
		return false;
	} else if(lastName == ""){
		message.innerHTML = "You must enter last name!";
		return false;
	} else if(!namePattern.test(lastName)){
		message.innerHTML = "Last name cannot contain numbers or symbols.";
		return false;
	} else if(username == ""){
		message.innerHTML = "You must enter username";
		return false;
	} else if(!usernamePattern.test(username)){
		message.innerHTML = "You must enter valid username.";
		return false;
	} else if(email == ""){
		message.innerHTML = "You must enter email address!";
		return false;
	} else if(!emailPattern.test(email)){
		message.innerHTML = "You must enter valid email address!";
		return false;
	} else if(oldPass != ""){
		if(password1 == ""){
			message.HTML = "You muster enter new password!";
			return false;
		} else if(password1 != password2){
			message.innerHTML = "Passwords dont match!";
			return false;
		}
	} else if (country == ""){
		message.innerHTML = "You must select country!"
			return false;
	} else {
		return true;
	}
}