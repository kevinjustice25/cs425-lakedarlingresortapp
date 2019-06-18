package edu.mum.cs.cs425.lakedarlingresortapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author okalu
 *
 */
@Entity
@Table(name="roles")
public class Role {
//    private static Role adminRole = new Role("ADMIN", new HashSet<>());
//    private static Role customerRole = new Role("CUSTOMER", new HashSet<>());
//    public static Role getAdminRole(){
//        return adminRole;
//    }
//    public static Role getCustomerRole(){
//        return customerRole;
//    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(nullable=false, unique=true)
    @NotEmpty
    private String name;

    @ManyToMany(mappedBy="roles", fetch = FetchType.EAGER)
    private Set<Customer> customers;

//    private Role(String name, Set<Customer> customers){
//        this.name = name;
//        this.customers = customers;
//    }
    public Role(){}

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }
}
