package edu.mum.cs.cs425.lakedarlingresortapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "adresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long addressId;

    @Column
    @NotNull
    private Integer houseNumber;

    @Column
    @NotBlank
    private String street;

    @Column
    private String streetLineTwo;

    @Column
    @NotBlank
    private String city;

    @Column
    @NotBlank
    private String state;

    @Column
    @NotNull
    private Integer zip;

    public Address() {
    }

    public Address(Integer houseNumber, String street, String streetLineTwo, String state, Integer zip) {
        this.houseNumber = houseNumber;
        this.street = street;
        this.streetLineTwo = streetLineTwo;
        this.state = state;
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetLineTwo() {
        return streetLineTwo;
    }

    public void setStreetLineTwo(String streetLineTwo) {
        this.streetLineTwo = streetLineTwo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", houseNumber=" + houseNumber +
                ", street='" + street + '\'' +
                ", streetLineTwo='" + streetLineTwo + '\'' +
                ", state='" + state + '\'' +
                ", zip=" + zip +
                '}';
    }
}
