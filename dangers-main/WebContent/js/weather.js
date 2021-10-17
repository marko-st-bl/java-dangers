/**
 * 
 */
let countries;
let lon;
let lat;

const countriesEuropeUrl = 'https://restcountries.com/v3.1/region/europe';
const openWeatherUrl = 'http://api.openweathermap.org/data/2.5/find?';
const openWeatherAPIKey = '529b81310379224f19501c0caf39800b';

function showWeathetForecasts(country){
	getCountryCoords(country, getWeatherForecasts);
}

function getCountryCoords(country, callback){
	let xhr = new XMLHttpRequest();
	xhr.open('GET', countriesEuropeUrl, 'true');
	xhr.onload = function() {
		if (this.status == 200) {
			countries = JSON.parse(this.responseText);
			let output = '';
			for ( let i in countries) {
				if (countries[i].name.common == country) {
					lat = countries[i].latlng[0];
					lon = countries[i].latlng[1];
					console.log(lat + " " + lon);
					callback();
					break;
				}
			}
		}
	}
	xhr.send();
}

function addForecasts(forecasts){
	
	for(let i=1; i<4; i++){
		let forecast = Math.floor(Math.random() * forecasts.count);
		document.getElementById('cityName' + i).innerHTML = forecasts.list[forecast].name;
		document.getElementById('icon' + i).src = 'http://openweathermap.org/img/wn/'
				+ forecasts.list[forecast].weather[0].icon
				+ '@2x.png';
		document.getElementById('temp' + i).innerHTML = Math
				.round(forecasts.list[forecast].main.temp)
				+ '&#8451;';
		document.getElementById('maxtemp' + i).innerHTML += Math.round(forecasts.list[forecast].main.temp_max) + '&#8451;';
		document.getElementById('mintemp' + i).innerHTML += Math.round(forecasts.list[forecast].main.temp_min) + '&#8451;';
	}
	
}

function getWeatherForecasts(){
	xhr1 = new XMLHttpRequest();
	url = openWeatherUrl 
			+ 'lat=' + lat
			+ '&lon=' + lon
			+ '&cnt=30&units=metric&&APPID=' + openWeatherAPIKey;
	xhr1.open('GET', url, 'true');
	xhr1.onload = function() {
		
		if (this.status == 200) {
			let forecasts = JSON.parse(this.responseText);
			console.log(forecasts);
			addForecasts(forecasts);
		}
		
	}
	xhr1.send();
}