package net.etfbl.ip.marko.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.etfbl.ip.marko.dao.AidDAO;
import net.etfbl.ip.marko.dto.Aid;

@ManagedBean(name="aidBean")
@SessionScoped
public class AidBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4279198337866188433L;
	
	private Aid aid;
	private String message;
	
	public AidBean() {
		super();
		this.aid = new Aid();
	}
	
	public String addAid() {
		boolean result = false;
		result = new AidDAO().addAid(aid);
		if(result) {
			this.message = "Call for help succesfully added.";
		} else {
			this.message="Couldn't add call for help.";
		}
		return "";
	}

	public Aid getAid() {
		return aid;
	}

	public void setAid(Aid aid) {
		this.aid = aid;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
