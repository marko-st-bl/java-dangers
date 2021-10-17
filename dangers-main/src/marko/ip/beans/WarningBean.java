package marko.ip.beans;

import java.io.Serializable;
import java.util.List;

import marko.ip.dao.WarningDAO;
import marko.ip.dto.Warning;

public class WarningBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1772907729450608792L;
	
	private Warning warning;
	
	public WarningBean() {
		super();
		warning = new Warning();
	}

	public Warning getWarning() {
		return warning;
	}

	public void setWarning(Warning warning) {
		this.warning = warning;
	}
	
	public boolean addWarning() {
		return new WarningDAO().addWarning(warning);
	}
	
	public List<Warning> getAllWarnings(){
		return new WarningDAO().getAllWarnings();
	}
	
	public List<Warning> getUrgentWarnings(){
		return new WarningDAO().getUrgentWarnings();
	}
	

}
