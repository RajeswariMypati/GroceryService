/**
 * 
 */
package com.online.groceries.util;

/**
 * @author Rajeswari Mypati
 *
 */
public class GroceryServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorMSG;
    
    public GroceryServiceException(String msg) {
        super(msg);
    }
    
    
	public GroceryServiceException(String errorCode, String errorMSG, Throwable cause) {
    	// Log msg here with a Spring AOP
        super(errorMSG, cause);
    }
	
    public GroceryServiceException(String msg, Throwable cause) {
    	// Log msg here with a Spring AOP 
        super(msg, cause);
    }
    
    public GroceryServiceException(Throwable cause) {
        super(cause);
    }
    
    @Override
    public String toString() {
        return errorMSG;
    }
}
