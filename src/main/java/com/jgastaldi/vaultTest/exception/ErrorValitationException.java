package com.jgastaldi.vaultTest.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class ErrorValitationException extends RuntimeException {

	private static final long serialVersionUID = -1015978931564133118L;

	public ErrorValitationException(Errors error) {
		super(error.getAllErrors().stream().map(e -> e.getCode()).collect(Collectors.joining(" , ")));
	}

}
