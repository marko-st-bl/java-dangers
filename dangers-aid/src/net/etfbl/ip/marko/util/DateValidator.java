package net.etfbl.ip.marko.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("net.etfbl.ip.marko.util.DateValidator")
public class DateValidator implements Validator{
	
	private static final String DATE_PATTERN = "^(0?[1-9]|[12][0-9]|3[01])\\.(0?[1-9]|1[012])\\.(\\d{4}) ([0-1][0-9]|[2][0-3]):([0-5][0-9])$";

	@Override
	public void validate(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException {
		String date = value.toString();
		Pattern datePattern = Pattern.compile(DATE_PATTERN);
		Matcher matcherDate = datePattern.matcher(date);
		
		if(!matcherDate.matches()) {
			throw new ValidatorException(new FacesMessage("You must enter date in format: dd.MM.yyyy HH:mm"));
		}
		
	}

}
