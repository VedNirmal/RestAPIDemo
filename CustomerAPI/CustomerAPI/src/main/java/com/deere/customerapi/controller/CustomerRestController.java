package com.deere.customerapi.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.deere.customerapi.domain.Customer;
import com.deere.customerapi.service.CustomerService;
import com.deere.customerapi.service.exception.CustomerAlreadyExistsException;
import com.deere.customerapi.service.exception.CustomerNotFoundException;
import com.deere.customerapi.service.exception.UnauthorizedAccessException;

/**
 * 
 * @author nirmal.ved
 *
 */
@RestController
public class CustomerRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerRestController.class);
    private final CustomerService customerService;
    
    @Inject
    public CustomerRestController(final CustomerService CustomerService) {
        this.customerService = CustomerService;
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer customer) {
        LOGGER.debug("Received request to create the {}", customer);
        return customerService.save(customer);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> listCustomers() {
        LOGGER.debug("Received request to list all Customers");
        return customerService.getList();
    }
    
    @RequestMapping(value = "/customer/{customerId}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public Customer getCustomer(@PathVariable Integer customerId) throws CustomerNotFoundException {
        LOGGER.debug("Received request to list customer for given id: ", customerId);
        return customerService.getCustomerByCustomerId(customerId);
    }
    
    @RequestMapping(value = "/customer", method = RequestMethod.PUT)
    public Customer updateCustomer(@RequestBody Customer customer) throws CustomerNotFoundException {
        LOGGER.debug("Received request to list all Customers");
        return customerService.updateCustomerByCustomer(customer);
    }
    
    @RequestMapping(value = "/customer/{customerId}", method = RequestMethod.DELETE)
    public Customer deleteCustomer(@PathVariable Integer customerId) throws CustomerNotFoundException {
        LOGGER.debug("Received request to list customer for given id: ", customerId);
        return customerService.deleteCustomerByCustomerId(customerId);
    }
  
    /**
     * Below part can be implemented in other way also, which can be used in all other components.
     */

	@ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleCustomerAlreadyExistsException(CustomerAlreadyExistsException e) {
        return e.getMessage();
    }
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleCustomerNotFoundException(CustomerNotFoundException e) {
        return e.getMessage();
    }
    
    @ExceptionHandler
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String handleCustomerUnauthorizedAccessException(UnauthorizedAccessException e) {
	      return e.getMessage();
    }
    
    
    @ExceptionHandler
   	@ResponseStatus(HttpStatus.BAD_REQUEST)
   	public String handleBadRequest(TypeMismatchException e) {
   	      return "Bad Request Recieved!! Paramater type did not match the for the request.";
    }
    
    @ExceptionHandler
   	@ResponseStatus(HttpStatus.BAD_REQUEST)
   	public String handleBadRequestMessage(HttpMessageNotReadableException e) {
   	      return "Bad Request Recieved!! Paramater type did not match the for the request.";
    }
    
    @ExceptionHandler
   	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   	public String handleInternalServerError(Throwable e) {
   	      return "Some system error occurred, Please try later.";
    }
    
    

}
