package com.cuasatar.ventas.exception;

public class UsernameOrIdNotFound extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -380858286471042758L;

	public UsernameOrIdNotFound() {
		super("Usuario o Id no encontrado");
	}

	public UsernameOrIdNotFound(String message) {
		super(message);
	}
}
