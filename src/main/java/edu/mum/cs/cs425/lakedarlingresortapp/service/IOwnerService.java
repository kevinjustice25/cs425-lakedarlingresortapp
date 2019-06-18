package edu.mum.cs.cs425.lakedarlingresortapp.service;

import edu.mum.cs.cs425.lakedarlingresortapp.model.Address;
import edu.mum.cs.cs425.lakedarlingresortapp.model.Owner;

import java.util.List;

public interface IOwnerService {
    Owner save(Owner owner, Address address);
    Owner save(Owner owner);
    List<Owner> findAll();
    Owner findById(Long ownerId);
    Owner findById(String ownerId);
}
