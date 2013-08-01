package com.odea.validators.doubleValidator;

import java.util.regex.Pattern;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

public class DoubleValidator implements IValidator<Double> {
	
	private static final long serialVersionUID = 1L;
	
	private final String DOUBLE_PATTERN = "[-+]?[0-9]*\\.?[0-9]+";
	private final Pattern duracionPattern;
	private String componente;
	
	
	public DoubleValidator(String nombreComponente) {
		this.duracionPattern = Pattern.compile(DOUBLE_PATTERN);
		this.componente = nombreComponente;
	}

	@Override
	public void validate(IValidatable<Double> validatable) {
		Double valor = validatable.getValue();
		
		if (!duracionPattern.matcher(valor.toString()).matches()) {	
			this.error(validatable, "El campo '" + this.componente + "' tiene formato incorrecto.");
		}
	}
	
	private void error(IValidatable<Double> validatable, String errorKey) {
		ValidationError error = new ValidationError();
		error.addKey(getClass().getSimpleName() + "." + errorKey);
		error.setMessage(errorKey);
		validatable.error(error);
	}

}
