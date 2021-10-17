package marko.ip.beans;

import java.io.Serializable;
import java.util.List;

import marko.ip.dao.CategoryDAO;
import marko.ip.dto.Category;

public class CategoryBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1933114293173660734L;
	
	private Category category;
	private List<Category> categories;
	
	public CategoryBean() {
		super();
		this.category = new Category();
		categories = getAllCategories();
	}
	
	public List<Category> getAllCategories(){
		return new CategoryDAO().getCategories();
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	public List<Category> getCategories(){
		return categories;
	}
	
	
	

}
