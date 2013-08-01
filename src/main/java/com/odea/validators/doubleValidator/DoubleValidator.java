package com.odea.validators.doubleValidator;

import java.util.regex.Pattern;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

public class DoubleValidator implements IValidator<String> {
	
	private static final long serialVersionUID = 1L;
	
	private final String DOUBLE_PATTERN = "[-+]?[0-9]*(\\.[0-9]+([eE][-+]?[0-9]{1,2})?)?";
	private final Pattern duracionPattern;
	private String componente;
	
	
	public DoubleValidator(String nombreComponente) {
		this.duracionPattern = Pattern.compile(DOUBLE_PATTERN);
		this.componente = nombreComponente;
	}

	@Override
	public void validate(IValidatable<String> validatable) {
		String strDuracion = validatable.getValue(); 	
		
		if (!duracionPattern.matcher(strDuracion).matches()) {	
			this.error(validatable, "Campo '" + this.componente + "' debe tener formato '000.0000'. El número puede ocupar hasta 10 espacios en total.");
		}
	}
	
	private void error(IValidatable<String> validatable, String errorKey) {
		ValidationError error = new ValidationError();
		error.addKey(getClass().getSimpleName() + "." + errorKey);
		error.setMessage(errorKey);
		validatable.error(error);
	}

}
