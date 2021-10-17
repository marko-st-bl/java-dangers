const POSTS_URL = 'http://localhost:8080/dangers-main/Posts';

function drawMap(id, lat, lng){

		    var myLatLng = {
		      lat: lat,
		      lng: lng
		    };

		    var map = new google.maps.Map(document.getElementById(id), {
		      zoom: 11,
		      center: myLatLng
		    });

		    var marker = new google.maps.Marker({
		      position: myLatLng,
		      map: map
		    });
		    
}

function getPosts(){
	let xhr = new XMLHttpRequest();
	xhr.open('get', POSTS_URL, 'true');
	xhr.onload = function(){
		if(this.status == 200){
			let posts = JSON.parse(this.responseText);
			for(post of posts){
				if(post.type != "rss"){
					showUserPost(post);
				} else if(post.type == "rss"){
					showRSSPost(post);
				}
			}
		}
	}
	xhr.send();
}

function showUserPost(post){
	if(!document.getElementById(`post-${post.id}`)){
		let postDiv = document.createElement("div");
		postDiv.id = `post-${post.id}`;
		postDiv.classList.add("post", "border", "bg-white", "rounded", "shadow-sm", "my-2", "px-0", "pt-1");
		
		let row1 = document.createElement("div");
		row1.classList.add("row", "mx-0", "p-2", "align-items-center");
		let imgAuthor = document.createElement('img');
		imgAuthor.src = post.author.avatar;
		imgAuthor.classList.add("avatar", "rounded-circle");
		let author = document.createElement('h5');
		author.classList.add("row", "mb-1", "mx-2");
		author.innerHTML = `${post.author.firstName} ${post.author.lastName}`;
		
		let row2 = document.createElement("div");
		row2.classList.add("mx-0", "px-2");
		//row2.innerHTML = `<small>${post.createdAt}</small>`;
		//postDiv.append(row2);
		row2.append(author);
		row2.innerHTML += `<small class="row mx-2 text-muted">${post.createdAt}</small>`;
		row1.append(imgAuthor);
		//row1.append(author);
		row1.append(row2);
		postDiv.append(row1);
		
		let desc = document.createElement('p');
		desc.classList.add("mx-2");
		desc.innerHTML = post.description;
		postDiv.append(desc);
		
		switch(post.type){
		
		case "youtube":
			let row3 = document.createElement("div");
			row3.classList.add("row", "mx-0");
			let embedDiv = document.createElement("div");
			embedDiv.classList.add("embed-responsive", "embed-responsive-16by9");
			let iframe = document.createElement('iframe');
			iframe.classList.add("embed-responsive-item");
			iframe.src=`https://www.youtube.com/embed/${post.url.split("?v=")[1]}`;
			embedDiv.append(iframe);
			row3.append(embedDiv);
			postDiv.append(row3);
			break;
			
		case "link":
			let linkDiv = document.createElement("div");
			linkDiv.classList.add("row", "mx-2");
			let link = document.createElement("a");
			link.classList.add("py-2");
			link.href = post.url;
			link.target = "_blank";
			link.innerHTML = '&lt;&ltLINK&gt;&gt;'
			
			linkDiv.append(link);
			postDiv.append(linkDiv);
			break;
			
		case "image":
			let imageDiv = document.createElement("div");
			imageDiv.classList.add("row", "mx-0");
			let image = document.createElement("img");
			image.classList.add("post-image", "py-2");
			image.src = post.url;
			
			imageDiv.append(image);
			postDiv.append(imageDiv);
			break;
			
		case "video":
			let videoDiv = document.createElement("div");
			videoDiv.classList.add("row", "mx-0");
			let video = document.createElement("video");
			video.classList.add("my-2");
			video.style.width = "100%";
			video.controls = "controls";
			let source = document.createElement("source");
			source.src = post.url;
			source.type = "video/mp4";
			video.append(source);
			videoDiv.append(video);
			postDiv.append(videoDiv);
			break;
			
		case "warning":
			let warningDiv = document.createElement("div");
			warningDiv.classList.add("row" ,"m-2");
			for(category of post.categories){				
				let categorySpan = document.createElement("span");
				categorySpan.classList.add("badge", "badge-pill", "badge-danger", "text-lowercase", "mr-1");
				categorySpan.innerHTML = category.name;
				warningDiv.append(categorySpan);	
			}
			postDiv.append(warningDiv);
			
			if(post.lat && post.lng){
				let mapDiv =document.createElement("div");
				mapDiv.classList.add("row", "my-2", "mx-0");
				let map = document.createElement("div");
				map.id = `map-${post.id}`;
				map.classList.add("map");
				mapDiv.append(map);
				postDiv.append(mapDiv);
			}
			break;
			default:
				break;}
		
		document.querySelector('.posts').prepend(postDiv);
		if(post.type == "warning" && (post.lat || post.lng)){
			drawMap(`map-${post.id}`, post.lat, post.lng);
		}
		
		let commentButtonsDiv = document.createElement("div");
		commentButtonsDiv.classList.add("row" ,"mx-1", "border-top", "border-bottom");
		let commentButton = document.createElement("button");
		commentButton.id = `comment-btn-${post.id}`;
		commentButton.classList.add("post-button", "btn", "col-6");
		commentButton.type = "button";
		commentButton.setAttribute("data-toggle", "collapse");
		commentButton.setAttribute("data-target", `#comment-container-${post.id}`);
		commentButton.setAttribute("aria-expanded", "false");
		commentButton.setAttribute("aria-controls", `comment-container-${post.id}`);
		commentButton.innerHTML = '<i class="far fa-comment"></i> Comment';
		let commentsButton = document.createElement("button");
		commentsButton.id = `comments-btn-${post.id}`;
		commentsButton.classList.add("post-button", "btn", "col-6");
		commentsButton.type = "button";
		commentsButton.setAttribute("data-toggle", "collapse");
		commentsButton.setAttribute("data-target", `#comments-${post.id}`);
		commentsButton.setAttribute("aria-expanded", "false");
		commentsButton.setAttribute("aria-controls", `comments-${post.id}`);
		commentsButton.innerHTML = '<i class="far fa-comments"></i> Comments';
		
		commentButtonsDiv.append(commentButton);
		commentButtonsDiv.append(commentsButton);
		document.querySelector(`#post-${post.id}`).append(commentButtonsDiv);
		
		let shareButtonsDiv = document.createElement("div");
		shareButtonsDiv.classList.add("share-buttons-container", "row", "justify-content-end", "mx-1");
		let tweeterButton = document.createElement("iframe");
		tweeterButton.src =`http://platform.twitter.com/widgets/tweet_button.html?via=&amp;text=${post.url ? post.url:post.description}&amp;count=horizontal`;
		tweeterButton.scrolling = "no";
		tweeterButton.frameBorder = "0";
		tweeterButton.allowtransparency= "true";
		tweeterButton.style = "width: 80px; height: 20px;";
		let fbButton = document.createElement("iframe");
		fbButton.src = `https://www.facebook.com/plugins/share_button.php?href=${post.url ? post.url : 'http://localhost:8080/dangers-main/?action=home/'}&layout=button&size=small&width=67&height=20&appId`;
		fbButton.width = "67";
		fbButton.height = "20";
		fbButton.style = "border:none;overflow:hidden";
		fbButton.scrolling = "no";
		fbButton.frameborder = "0";
		fbButton.allowTransparency = "true";
		fbButton.allow = "encrypted-media";
		shareButtonsDiv.append(tweeterButton);
		shareButtonsDiv.append(fbButton);
		document.querySelector(`#post-${post.id}`).append(shareButtonsDiv);
		let commentsDiv = document.createElement("div");
		commentsDiv.classList.add("collapse", "comments", "mx-0", "py-2");
		commentsDiv.id = `comments-${post.id}`;
		document.querySelector(`#post-${post.id}`).append(commentsDiv);
		
		showComments(post);
		
		let newCommentContainer = document.createElement("div");
		newCommentContainer.classList.add("collapse", "new-comment-container", "row", "align-items-center", "border-top", "m-2");
		newCommentContainer.id = `comment-container-${post.id}`;
		newCommentContainer.innerHTML = `<div class="col-12 p-0">
											<form id="form-${post.id}"
											action="http://localhost:8080/dangers-main/NewComment" method="POST" enctype="multipart/form-data">
												<div class="comment-form row justify-content-between my-2">
													<input id="comment-input-${post.id}"
													class="col-9 form-control pl-1" type="text" name="text" placeholder="Write a comment..."/>
													<button class="btn btn-outline-primary col-2" type="submit">Post</button>
													<button id="new-comment-btn-${post.id}"
													class="btn col-1" type="button"><i class="fas fa-image"></i></button>
												</div>
												<input id="comment-img-input-${post.id}"
												type="file" name="image" accept="image/*" style="display: none"/>
												<input type="text" name="postId" value="${post.id}" style="display: none"/>
												<button id="btn-${post.id}" type="submit" style="display: none"></button>
											</form>
											<div class="row mx-0">
												<img id="comment-img-preview-${post.id}" src="" width="100%">
											</div>
										</div>`;
		document.querySelector(`#post-${post.id}`).append(newCommentContainer);
		
		let imgBtn = document.getElementById(`new-comment-btn-${post.id}`);
		imgBtn.addEventListener("click", function(){
			document.getElementById(`comment-img-input-${post.id}`).click();
		});
		
		let imgInput = document.getElementById(`comment-img-input-${post.id}`);
		imgInput.addEventListener("change", function(){
			let file = this.files[0];
			if (file) {
				let src = URL.createObjectURL(file);
				console.log(src);
				document.getElementById(`comment-img-preview-${post.id}`).src = src;
			} else {
				document.getElementById(`comment-img-preview-${post.id}`).src = "";
			}
		});
		
		let form = document.getElementById(`form-${post.id}`).addEventListener("submit", function(e){
			let form = this;
			e.preventDefault();
			const xhr = new XMLHttpRequest();
			let formData = new FormData(this);
			xhr.open(this.method, this.action, "true");
			
			xhr.addEventListener("load", function(){
				if(this.status == 200){
					console.log('Comment posted...');
					form.reset();
					document.getElementById(`comment-img-preview-${post.id}`).src = "";
				}
			});
			xhr.send(formData);
		});
		
	} else if(document.getElementById(`post-${post.id}`)){
		showComments(post);
	}
}

function showComments(post){
	let commentsDiv = document.getElementById(`comments-${post.id}`);
	if(commentsDiv){
		commentsDiv.innerHTML = "";				
	
	for(comment of post.comments){
		let commentDiv = document.createElement("div");
		commentDiv.classList.add("row", "m-2");
		let commentAvatar = document.createElement("div");
		commentAvatar.classList.add("comment-avatar-container", "col-2", "col-lg-1");
		commentAvatar.innerHTML = `<img class="comment-avatar rounded-circle" src="${comment.author.avatar}" alt="">`;
		let commentContainer = document.createElement("div");
		commentContainer.classList.add("col-10", "col-lg-11");
		commentContainer.innerHTML = `<div class="p-2 bg-light border rounded">
										 <h6 class="comment-author">${comment.author.firstName} ${comment.author.lastName}</h6>
										 <p class="comment-text">${comment.text}</p></div>`;
		if(comment.url){
			commentContainer.innerHTML += `<img src="${comment.url}" alt="" width="100%">`;
		}
		commentDiv.append(commentAvatar);
		commentDiv.append(commentContainer);
		commentsDiv.append(commentDiv);
	}
	}
}

function showRSSPost(post){
	if(!document.getElementById(`post-${post.guid}`)){
		let postDiv = document.createElement("div");
		postDiv.id = `post-${post.guid}`;
		postDiv.classList.add("post", "border", "bg-white", "rounded", "shadow-sm", "my-2", "px-0");
		postDiv.innerHTML = `<div class="row mx-2">
								<h5 class="pl-2">${post.title}</h5>
							 </div>
							 <div class="row text-muted mx-2"><small>
							 	${post.createdAt}</small>
							 </div>
							 <p class="m-2">${post.description}
							 <a target="_blank" href="${post.url}"> Read More</a></p>`;
		document.querySelector(".posts").prepend(postDiv);
	}
}