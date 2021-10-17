package marko.ip.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Part;

import marko.ip.beans.UserBean;

public class FormValidator {
	
	//ADD EMAIL VALIDATION REGEX
	public static String validateRegisterForm(String firstName, String lastName, String username, String password1, 
			String password2, String email) {
		String retVal = "OK";
		UserBean userBean = new UserBean();
		
		if(firstName == null || firstName.equals("")) {			
			retVal = "First name cannot be blank.";			
		} else if(lastName == null || lastName.equals("")) {			
			retVal = "Last name cannot be blank.";			
		} else if(username == null || username.equals("")) {			
			retVal = "Username name cannot be blank.";			
		} else if (userBean.isUsernameUsed(username)){			
			retVal = "Username already used";			
		} else if(email == null || email.equals("")) {
			retVal = "Email cannot be blank.";
		} else if (userBean.isEmailUsed(email)) {			
			retVal = "Email already used.";			
		} else if(password1 == null || password1.equals("")) {			
			retVal = "Password cannot be blank.";			
		} else if(password2 == null || password2.equals("")) {			
			retVal = "Password cannot be blank.";		
		} else if(!(password1.equals(password2))){		
			retVal = "Passwords do not match";
		} 
		
		return retVal;
	}
	
	public static String validateProfileForm(String firstName, String lastName, String username, 
			String oldPassword, String password1, String password2, String email, UserBean userBean, 
			String country){
		
		String retVal = "OK";
		
		if(firstName == null || firstName.equals("")) {
			retVal = "First name cannot be blank.";
		} else if(lastName == null || lastName.equals("")) {
			retVal = "Last name cannot be blank.";
		} else if(username == null || username.equals("")) {
			retVal = "Username name cannot be blank.";
		} else if (!username.equals(userBean.getUser().getUsername()) && userBean.isUsernameUsed(username)){		
			retVal = "Username already used.";
		} else if(email == null || email.equals("")) {
			retVal = "Email cannot be blank.";
		} else if(!email.equals(userBean.getUser().getEmail()) && userBean.isEmailUsed(email)) {
			retVal = "Email alraedy used.";
		} else if(!oldPassword.equals("")) {
			if (!(new UserBean().login(userBean.getUser().getUsername(), oldPassword))){
				retVal = "Invalid password.";
			} else if(password1 == null || password1.equals("") || password2 == null || password2.equals("")) {
				retVal = "Password cannot be blank.";
			} else if(!password1.equals(password2)) {
				retVal = "Passwords dont match";
			}
		} else if (country == null || country.equals("")) {
			retVal = "You must select country";
		}
		
		return retVal;
	}
	
	public static String validateWarningForm(String description, String lat, String lng, String[] categories) {
		String retVal = "OK";
		
		if(description == null || description.equals("")) {
			retVal = "You must enter description.";
		} else if(lat != null && !lat.equals("") && lng != null && !lng.equals("")) {
			try {
				double latitude = Double.parseDouble(lat);
				double longitude = Double.parseDouble(lng);
				
				if(latitude < -90) {
					retVal = "Latitude cannot be smaller than -90.";
				} else if (latitude > 90) {
					retVal = "Latitude cannot be greater than 90.";
				} else if ( longitude < -180) {
					retVal = "Longitude cannot be smaller than -180.";
				} else if ( longitude > 180 ) {
					retVal = "Longitude cannot be greater than 180.";
				}
			} catch(NumberFormatException e) {
				retVal = "Error parsing lat/lng. You must enter valid number.";
			}
		} else if ( categories == null) {
			retVal = "You must choose at least one category.";
		}
		
		return retVal;
	}

	public static String validateNewPostForm(String type, String text, String youtube, String link, Part imgPart,
			Part videoPart) {
		String retVal = "OK";
		
		String ytPattern = "^https?:\\/\\/www\\.youtube.com\\/watch\\?v=[a-zA-z0-9]+$";
		String linkPattern = "^(https?\\:)\\/\\/(([^:\\/?#]*)(?:\\:([0-9]+))?)([\\/]{0,1}[^?#]*)(\\?[^#]*|)(#.*|)$";
		String imgPattern = "^.*(jpeg|jpg|png|gif)$";
		
		switch (type) {
		case "text":
			if(text == null || text.equals("")) {
				retVal = "You must enter some text.";
			}
			break;
		case "youtube":
			Pattern yt = Pattern.compile(ytPattern);
			Matcher ytMatcher = yt.matcher(youtube);
			if(!ytMatcher.matches()) {
				retVal = "You must enter valid youtube link.";
			}
			break;
		case "link":
			Pattern url = Pattern.compile(linkPattern);
			Matcher linkMatcher = url.matcher(link);
			if(!linkMatcher.matches()) {
				retVal = "You must enter valid http[s] address.";
			}
			break;
		case "image":
			Pattern img = Pattern.compile(imgPattern);
			Matcher imgMatcher = img.matcher(imgPart.getSubmittedFileName());
			if(imgPart == null || imgPart.getSize() == 0) {
				retVal = "No image file added.";
			} else if(!imgMatcher.matches()) {
				System.out.println(imgPart.getSubmittedFileName());
				retVal = "Unsuported image file type.";
			}
			break;
		case "video":
			if(videoPart == null || videoPart.getSize() == 0) {
				retVal = "No video file added.";
			} else if(!videoPart.getSubmittedFileName().endsWith(".mp4")) {
				retVal = "Unsuported video file type. Add mp4 video.";
			}
			break;
		default:
			retVal = "Invalid post type.";
		}
		return retVal;
	}
}
