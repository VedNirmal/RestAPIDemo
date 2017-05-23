package com.deere.customerapi.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deere.customerapi.domain.Customer;
import com.deere.customerapi.repository.CustomerRepository;
import com.deere.customerapi.service.exception.CustomerAlreadyExistsException;
import com.deere.customerapi.service.exception.CustomerNotFoundException;
import com.deere.customerapi.service.exception.UnauthorizedAccessException;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final CustomerRepository repository;

    @Inject
    public CustomerServiceImpl(final CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Customer save(@NotNull final Customer customer) {
        LOGGER.debug("Creating {}", customer);
        Customer existing = repository.findOne(customer.getId());
        if (existing != null) {
            throw new CustomerAlreadyExistsException("There already exists a Customer with id= "+customer.getId());
        }
        return repository.save(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> getList() {
        LOGGER.debug("Retrieving the list of all Customers");
        return repository.findAll();
    }

	@Override
	public Customer getCustomerByCustomerId(Integer customerId) throws CustomerNotFoundException {
		List<Customer> allCustomer = repository.findAll();
		/**
		 * Below code can be optimized using sql query to get data based on customer id.
		 * But I used Java 8 Stream Feature to demonstrate.
		 */
		List<Customer> result = allCustomer.stream()				        
                .filter(eachCustomer -> eachCustomer.getId() == customerId)     
                .collect(Collectors.toList());              
	       if(null == result || result.isEmpty()){
		       throw new CustomerNotFoundException(
	                   String.format("No Customer Found with provided Customer Id=" + customerId));
	       }
		return result.get(0);
	}

	@Override
	public Customer updateCustomerByCustomer(Customer customer) throws CustomerNotFoundException {
		Customer existing = repository.findOne(customer.getId());
			if(existing == null) {
				 throw new CustomerNotFoundException(
		                   String.format("No Customer Found with provided Customer Id = " + customer.getId()));
			}
			existing.setPassword(customer.getPassword());
			repository.save(existing);
		return existing;
	}

	@Override
	public Customer deleteCustomerByCustomerId(Integer customerId) throws CustomerNotFoundException {
		Customer existing = repository.findOne(customerId);
			if(existing == null) {
				 throw new CustomerNotFoundException(
		                   String.format("No Customer Found with provided Customer Id = " + customerId));
			}
		repository.delete(customerId);
		return existing;
	}

	@Override
	public Customer findCustomer(Integer id, String password) throws UnauthorizedAccessException {
		Customer user = repository.findCustomer(id, password);
		if(user == null) {
			 throw new UnauthorizedAccessException("User is not authorized to perform this operation");
		}
		return user;
	}

}
