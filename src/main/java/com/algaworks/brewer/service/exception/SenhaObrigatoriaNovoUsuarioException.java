package com.algaworks.brewer.service.exception;

public class SenhaObrigatoriaNovoUsuarioException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3742840032944997287L;

	public SenhaObrigatoriaNovoUsuarioException(String message) {
		super(message);
	}
}
