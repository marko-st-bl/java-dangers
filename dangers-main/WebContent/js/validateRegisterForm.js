function validateForm(){
	const form = document.forms[0];
	const firstName = form.elements.firstName.value;
	const lastName = form.elements.lastName.value;
	const username = form.elements.username.value;
	const email = form.elements.email.value;
	const pass1 = form.elements.password1.value;
	const pass2 = form.elements.password2.value;
	
	let message = document.getElementById('validationResult');
	const namePattern = /^[A-Za-z]+$/;
	const usernamePattern = /^[A-Za-z0-9]+$/;
	const emailPattern = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
	
	if(firstName == ""){
		message.innerHTML = "You must enter first name!";
		return false;
	} else if(!namePattern.test(firstName)){
		message.innerHTML = "First name cannot contain numbers or symbols!";
		return false
	} else if(lastName == ""){
		message.innerHTML = "You must enter last name!";
		return false;
	} else if(!namePattern.test(lastName)){
		message.innerHTML = "Last name cannot contain numbers or symbols!";
		return false;
	} else if(username == ""){
		message.innerHTML = "You must enter username!";
		return false;
	} else if(!usernamePattern.test(username)){
		message.innerHTML = "You must enter valid username!";
		return false;
	} else if(email == ""){
		message.innerHTML = "You must enter email address!";
		return false;
	} else if(!emailPattern.test(email)){
		message.innerHTML = "You must enter valid email address!";
		return false;
	} else if(pass1 == ""){
		message.innerHTML = "You must enter password";
		return false;
	} else if(pass1 != pass2){
		message.innerHTML = "Passwords dont match!";
		return false;
	} else {
		return true;
	}
	
}