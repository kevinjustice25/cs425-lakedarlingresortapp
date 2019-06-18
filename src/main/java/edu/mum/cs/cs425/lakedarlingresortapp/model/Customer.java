package edu.mum.cs.cs425.lakedarlingresortapp.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long customer_id;

    @Column
    private String name;

    @OneToOne
    private Address address;

    @Column

    private String phone;

    @Column
    private String email;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @ManyToMany(cascade=CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name="customer_roles",
            joinColumns={@JoinColumn(name="customer_id", referencedColumnName="customer_id")},
            inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="id")})
    private Set<Role> roles = new HashSet<>();

    @Column(nullable=false)
    @NotBlank(message = "* Password is required")
    @Size(min=8)
    private String password;


    public Customer() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getCustomerId() {
        return customer_id;
    }

    public void setCustomerId(Long customerId) {
        this.customer_id = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customer_id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
