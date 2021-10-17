package net.etfbl.ip.marko.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import net.etfbl.ip.marko.dao.CategoryDAO;
import net.etfbl.ip.marko.dto.Category;

@ManagedBean(name="categoryBean")
@SessionScoped
public class CategoryBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6062866959821969547L;
	
	private List<Category> categories;
	private Category category;
	
	public CategoryBean() {
		super();
		categories = new CategoryDAO().getAllCategories();
		category = new Category();
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public void addCategory() {
		int id = new CategoryDAO().addCategory(category);
		categories.add(new Category(id, category.getName()));
	}
	
	public void deleteCategory() {
		 Map<String, String> reqMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			if (reqMap.containsKey("id")) {
				int id = Integer.parseInt(reqMap.get("id"));
				Category categoryToDelete = new Category();
				for(Category category: categories) {
					if(category.getId() == id) {
						categoryToDelete = category;
						new CategoryDAO().deleteCategory(id);
						break;
					}
				}
				categories.remove(categoryToDelete);
			}
	}

}
