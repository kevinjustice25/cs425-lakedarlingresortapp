package edu.mum.cs.cs425.lakedarlingresortapp.service.impl;

import edu.mum.cs.cs425.lakedarlingresortapp.model.Address;
import edu.mum.cs.cs425.lakedarlingresortapp.model.Customer;
import edu.mum.cs.cs425.lakedarlingresortapp.repository.AddressRepository;
import edu.mum.cs.cs425.lakedarlingresortapp.repository.CustomerRepository;
import edu.mum.cs.cs425.lakedarlingresortapp.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("customerService")
public class CustomerService implements ICustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AddressService addressService;

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }

    @Override
    public Customer save(Customer customer, Address address) {
        addressService.save(address);
        customer.setAddress(address);
        return customerRepository.save(customer);
    }

    @Override
    public Customer findById(Long customerId) {
        return customerRepository.findById(customerId).get();
    }

    @Override
    public boolean existsById(Long customerId) {
        return customerRepository.existsById(customerId);
    }
}
