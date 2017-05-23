package com.deere.customerapi.service;

import com.deere.customerapi.domain.Customer;
import com.deere.customerapi.service.exception.CustomerNotFoundException;
import com.deere.customerapi.service.exception.UnauthorizedAccessException;

import java.util.List;

public interface CustomerService {

    Customer save(Customer Customer);

    List<Customer> getList();

	Customer getCustomerByCustomerId(Integer customerId) throws CustomerNotFoundException;

	Customer updateCustomerByCustomer(Customer customer) throws CustomerNotFoundException;

	Customer deleteCustomerByCustomerId(Integer customerId) throws CustomerNotFoundException;
	
	Customer findCustomer(Integer id, String password) throws UnauthorizedAccessException;

}
