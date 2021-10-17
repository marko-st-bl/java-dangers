/**
 * 
 */

function loadWarnings(){
		$.ajax({
			url: 'http://localhost:8080/dangers-main/warnings',
			type: 'get'
		}).done(function(warnings){
			console.log(warnings);
			if(warnings){
				for(let i in warnings){
					let output='';
					if($('#warn-' + warnings[i].id).length == 0){
						document.getElementById('countNotifications').innerHTML = warnings.length;
						output+= '<a id="warn-'+warnings[i].id+'"';
						if(warnings[i].lat && warnings[i].lng){
							output+= 'target="_blank" href="https://www.google.com/maps/@'+warnings[i].lat+','+warnings[i].lng+',15z"';
						}else{
							output+= 'href"#';
						}
						output+= 'class="list-group-item list-group-item-action flex-column align-items-start">';
						output+= '<div class="d-flex w-100 justify-content-between">';
						output+= '<h5 class="mb-1">';
						for(let j in warnings[i].categories){
							output+=warnings[i].categories[j].name + " ";
						}
						output+= '</h5>';
						if(warnings[i].lat && warnings[i].lng){
							output+= '<i class="fas fa-map-marker-alt"></i>'
						}
						output+= '</div>';
						output+= '<p class="mb-1">' + warnings[i].description + '</p>';
						output+='<small>'+warnings[i].createdAt+ '</small>';
						output+= '</a>';
						$('#warn-list').prepend(output);
						
					}
				}
			}
		});
	}