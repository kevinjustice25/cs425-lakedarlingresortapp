package edu.mum.cs.cs425.lakedarlingresortapp.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "villas")
public class Villa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long villaId;

    @Column
    @NotNull
    private Integer villaNumber;

    @Column
    @NotNull
    private Integer numberBeds;
    @Column
    @NotNull
    private Float price;

    @OneToOne
    private Owner owner;

    public Villa(Integer villaNumber, Address address, Integer numberBeds, Float price) {
        this.villaNumber = villaNumber;
        this.numberBeds = numberBeds;
        this.price = price;
    }

    public Villa() {
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Long getVillaId() {
        return villaId;
    }

    public void setVillaId(Long villaId) {
        this.villaId = villaId;
    }

    public Integer getVillaNumber() {
        return villaNumber;
    }

    public void setVillaNumber(Integer villaNumber) {
        this.villaNumber = villaNumber;
    }


    public Integer getNumberBeds() {
        return numberBeds;
    }

    public void setNumberBeds(Integer numberBeds) {
        this.numberBeds = numberBeds;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Villa{" +
                "villaId=" + villaId +
                ", villaNumber=" + villaNumber +
                ", numberBeds=" + numberBeds +
                ", price=" + price +
                ", owner=" + owner.toString() +
                '}';
    }
}
