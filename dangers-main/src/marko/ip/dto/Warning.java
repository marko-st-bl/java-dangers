package marko.ip.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Warning extends Post implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 832864386779749898L;
	
	private boolean urgent;
	private double lat;
	private double lng;
	private List<Category> categories = new ArrayList<>();
	
	public Warning() {
		super();
	}

	public Warning(User author, double lat, double lng, boolean urgent, String text) {
		super(author, text, "warning");
		this.lat = lat;
		this.lng = lng;
		this.urgent = urgent;
	}

	public Warning(int id, double lat, double lng, Timestamp createdAt, boolean urgent, String description) {
		super(id, description, createdAt, "warning");
		this.lat= lat;
		this.lng = lng;
		this.urgent = urgent;
	}

	public Warning(User author, boolean urgent, String description) {
		super(author, description, "warning");
		this.urgent=urgent;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public boolean isUrgent() {
		return urgent;
	}

	public void setUrgent(boolean urgent) {
		this.urgent = urgent;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}
	
	public boolean isLocationSet() {
		if(lat != 0 && lng != 0) {
			return true;
		}else {
			return false;
		}
	}

}
