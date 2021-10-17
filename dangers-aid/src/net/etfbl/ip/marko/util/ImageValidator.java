package net.etfbl.ip.marko.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("net.etfbl.ip.marko.util.ImageValidator")
public class ImageValidator implements Validator{
	
	private static String imagePattern = "^(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpeg|jpg|gif|png)$";

	@Override
	public void validate(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException {
		String imageUrl = value.toString();
		Pattern imgUrlPattern = Pattern.compile(imagePattern);
		Matcher matcher = imgUrlPattern.matcher(imageUrl);
		
		if(!matcher.matches()) {
			throw new ValidatorException(new FacesMessage("You must enter valid image url."));
		}
		
	}

}
