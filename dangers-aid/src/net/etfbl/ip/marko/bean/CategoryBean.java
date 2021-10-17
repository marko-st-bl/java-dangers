package net.etfbl.ip.marko.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.etfbl.ip.marko.dao.CategoryDAO;
import net.etfbl.ip.marko.dto.Category;

@ManagedBean
@SessionScoped
public class CategoryBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6448275024014407597L;
	
	private Category category;
	private List<Category> categories;
	
	public CategoryBean() {
		category = new Category();
		categories = new ArrayList<>();
		categories = new CategoryDAO().getCategories();
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	

}
