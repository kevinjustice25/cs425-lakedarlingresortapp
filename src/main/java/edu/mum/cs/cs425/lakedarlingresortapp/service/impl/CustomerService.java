package edu.mum.cs.cs425.lakedarlingresortapp.service.impl;

import edu.mum.cs.cs425.lakedarlingresortapp.model.Address;
import edu.mum.cs.cs425.lakedarlingresortapp.model.Customer;
import edu.mum.cs.cs425.lakedarlingresortapp.model.Role;
import edu.mum.cs.cs425.lakedarlingresortapp.repository.AddressRepository;
import edu.mum.cs.cs425.lakedarlingresortapp.repository.CustomerRepository;
import edu.mum.cs.cs425.lakedarlingresortapp.service.ICustomerService;
import edu.mum.cs.cs425.lakedarlingresortapp.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("customerService")
public class CustomerService implements ICustomerService, UserDetailsService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AddressService addressService;

    @Autowired
    IRoleService roleService;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }

    @Override
    public Customer save(Customer customer, Address address) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
//        Role role = Role.getAdminRole();
//        Role role1 = Role.getCustomerRole();
        Role role = roleService.findById(2); //Customer Role ID
        role.getCustomers().add(customer);
//        role1.getCustomers().add(customer);
        roleService.save(role);
//        roleService.save(role1);
        addressService.save(address);
        customer.getRoles().add(role);
//        customer.getRoles().add(role1);
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
        return new org.springframework.security.core.userdetails.User(customer.getEmail(), customer.getPassword(),
                getAuthorities(customer));
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(Customer customer) {
        String[] userRoles = customer.getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }
}
