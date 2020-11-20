package com.sales.recommendations.common;

import java.util.List;

public class ApplicationValidationException extends Exception {
	private static final long serialVersionUID = 1L;
	private final List<String> errors;

	public ApplicationValidationException(List<String> errors) {
		super();
		this.errors = errors;
	}

	public List<String> getErrors() {
		return errors;
	}

}
