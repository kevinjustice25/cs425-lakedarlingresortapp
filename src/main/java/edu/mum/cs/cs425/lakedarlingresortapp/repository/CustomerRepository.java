package edu.mum.cs.cs425.lakedarlingresortapp.repository;

import edu.mum.cs.cs425.lakedarlingresortapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByEmail(String username);
}
