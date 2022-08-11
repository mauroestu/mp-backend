package com.api.mp.utils;

import lombok.Getter;
import lombok.Setter;

public class GeneralException extends Exception {
	
	private static final long serialVersionUID = 1L;
	@Getter @Setter
	int codError = 0;

	public GeneralException() {
		super();
	}
	
	public GeneralException(String mensaje, Throwable causa){
		super(mensaje, causa);
	}
	
	public GeneralException(String mensaje){
		super(mensaje);
	}
	
	public GeneralException(int codError, String mensaje){
		super(mensaje);
		this.codError = codError;
	}
}
