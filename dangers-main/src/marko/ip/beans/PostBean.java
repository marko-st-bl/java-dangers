package marko.ip.beans;

import java.io.Serializable;
import java.util.List;

import marko.ip.dao.PostDAO;
import marko.ip.dto.Post;

public class PostBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Post post = new Post();
	
	public PostBean() {
		super();
	}
	
	public List<Post> getAllPosts(){
		return new PostDAO().getAllPosts();
	}
	
	public boolean addPost() {
		return new PostDAO().addPost(post);
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

}
