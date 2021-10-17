package net.etfbl.ip.marko.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


import net.etfbl.ip.marko.dao.AdminDAO;
import net.etfbl.ip.marko.dto.Admin;

@ManagedBean(name = "adminBean")
@SessionScoped
public class AdminBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6073214935137892761L;

	private Admin admin = new Admin();
	
	public AdminBean() {
		super();
	}
	
	public String login() {
		String retVal = "";
		Admin admin1 = new AdminDAO().getAdminByUsernamePassword(admin.getUsername(), admin.getPassword());
		if(admin1 != null) {
			admin = admin1;
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("admin", admin);
			retVal = "index.xhtml?faces-redirect=true";
			System.out.println("ok");
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("login-form:login-btn", new FacesMessage("Invalid username/password."));
		return retVal;
	}
	
	public String logout() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		session.invalidate();
		return "login.xhtml?faces-redirect=true";
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
}
