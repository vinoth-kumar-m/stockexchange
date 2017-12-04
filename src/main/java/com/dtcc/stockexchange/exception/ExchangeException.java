
package com.dtcc.stockexchange.exception;

/**
 * ExchangeException - This class implements exception to handle all the application related exceptions
 * @author Vinoth
 *
 */
public class ExchangeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public ExchangeException(String message) {
		super(message);
		this.message = message;
	}
	
	@Override
	public String toString() {
		return message;
	}

}
