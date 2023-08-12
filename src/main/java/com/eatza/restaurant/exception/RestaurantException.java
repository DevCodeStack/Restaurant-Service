package com.eatza.restaurant.exception;

import com.eatza.restaurant.util.ErrorCodesEnum;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RestaurantException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8554789485569010954L;
	
	private ErrorCodesEnum error;

	public RestaurantException() {
		super(ErrorCodesEnum.INTERNAL_SERVER_ERROR.getMsg());
		this.error = ErrorCodesEnum.INTERNAL_SERVER_ERROR;
	}

	public RestaurantException(String message) {
		super(message);
		this.error = ErrorCodesEnum.INTERNAL_SERVER_ERROR;
	}

	public RestaurantException(String message, ErrorCodesEnum error) {
		super(message);
		this.error = error;
	}
	
	

}
