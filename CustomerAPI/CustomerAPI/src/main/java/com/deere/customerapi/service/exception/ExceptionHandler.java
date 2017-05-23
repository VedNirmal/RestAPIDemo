/**
 * 
 */
package com.deere.customerapi.service.exception;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author nirmal.ved
 *
 */
@Deprecated
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleCustomerAlreadyExistsException(CustomerAlreadyExistsException e) {
        return e.getMessage();
    }
    
    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleCustomerNotFoundException(CustomerNotFoundException e) {
        return e.getMessage();
    }
    
    @org.springframework.web.bind.annotation.ExceptionHandler
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String handleCustomerUnauthorizedAccessException(UnauthorizedAccessException e) {
	      return e.getMessage();
    }
    
    @org.springframework.web.bind.annotation.ExceptionHandler
   	@ResponseStatus(HttpStatus.BAD_REQUEST)
   	public String handleBadRequest(TypeMismatchException e) {
   	      return "Bad Request Recieved!! Paramater type did not match the for the request.";
       }
    @org.springframework.web.bind.annotation.ExceptionHandler
   	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   	public String handleInternalServerError(Throwable e) {
   	      return "Some system error occurred, Please try later.";
    }
    

}
