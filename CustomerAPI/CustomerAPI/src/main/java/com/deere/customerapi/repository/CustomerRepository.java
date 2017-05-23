package com.deere.customerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.deere.customerapi.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	 @Query("SELECT c FROM Customer c WHERE c.id =:id and c.password=:password")
	 public Customer findCustomer(@Param("id") Integer id, @Param("password") String password);
}
