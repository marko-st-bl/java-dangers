package net.etfbl.ip.marko.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import net.etfbl.ip.marko.dao.AidDAO;
import net.etfbl.ip.marko.dto.Aid;

@ManagedBean(name = "aidBean")
@ViewScoped
public class AidBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6823474459531375322L;
	
	private Aid aid;
	private List<Aid> aids;
	
	public AidBean() {
		super();
		aids = new AidDAO().getAllAids();
		aid = new Aid();
	}

	public Aid getAid() {
		return aid;
	}

	public void setAid(Aid aid) {
		this.aid = aid;
	}

	public List<Aid> getAids() {
		return aids;
	}

	public void setAids(List<Aid> aids) {
		this.aids = aids;
	}
	
	public void deleteAid() { 
		System.out.println("called delete");
		Map<String, String> reqMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if (reqMap.containsKey("aidId")) {
			int id = Integer.parseInt(reqMap.get("aidId"));
			System.out.println(id);
			Aid aidToDelete = new Aid();
			for(Aid aid: aids) {
				if(aid.getId() == id) {
					aidToDelete = aid;
					new AidDAO().deleteAid(id);
					break;
				}
			}
			aids.remove(aidToDelete);
		}
	}
	
	

}
