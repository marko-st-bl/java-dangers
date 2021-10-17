package marko.ip.beans;

import java.io.Serializable;
import java.util.List;

import marko.ip.dao.CommentDAO;
import marko.ip.dto.Comment;

public class CommentBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Comment comment = new Comment();
	
	public CommentBean() {
		super();
	}
	
	public List<Comment> getCommentsByPostId(int postId){
		return new CommentDAO().getAllCommentsByPostId(postId);
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public boolean addComment() {
		return new CommentDAO().addComment(comment);
	}
	

}
