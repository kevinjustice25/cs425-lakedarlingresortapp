package edu.mum.cs.cs425.lakedarlingresortapp.service;

import edu.mum.cs.cs425.lakedarlingresortapp.model.Address;
import edu.mum.cs.cs425.lakedarlingresortapp.model.Customer;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface ICustomerService extends UserDetailsService {
    List<Customer> findAll();
    Customer save(Customer customer, Address address);
    Customer findById(Long customerId);
    boolean existsById(Long customerId);

}
