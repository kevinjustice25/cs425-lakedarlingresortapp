package edu.mum.cs.cs425.lakedarlingresortapp.service.impl;

import edu.mum.cs.cs425.lakedarlingresortapp.model.Address;
import edu.mum.cs.cs425.lakedarlingresortapp.model.Owner;
import edu.mum.cs.cs425.lakedarlingresortapp.repository.OwnerRepository;
import edu.mum.cs.cs425.lakedarlingresortapp.service.IOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ownerService")
public class OwnerService implements IOwnerService {

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    AddressService addressService;

    @Override
    public Owner save(Owner owner, Address address) {
        addressService.save(address);
        owner.setAddress(address);
        ownerRepository.save(owner);
        return ownerRepository.save(owner);
    }

    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    @Override
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner findById(Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId).get();
        return owner;
    }

    @Override
    public Owner findById(String ownerId) {
        Long ownerIdLong = Long.parseLong(ownerId);
        return ownerRepository.findById(ownerIdLong).get();
    }
}
