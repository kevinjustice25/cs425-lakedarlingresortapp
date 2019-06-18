package edu.mum.cs.cs425.lakedarlingresortapp.service;

import edu.mum.cs.cs425.lakedarlingresortapp.model.Address;
import edu.mum.cs.cs425.lakedarlingresortapp.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
    Customer save(Customer customer, Address address);
    Customer findById(Long customerId);
    boolean existsById(Long customerId);

}
