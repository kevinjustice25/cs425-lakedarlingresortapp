package edu.mum.cs.cs425.lakedarlingresortapp.service.impl;

import edu.mum.cs.cs425.lakedarlingresortapp.model.Address;
import edu.mum.cs.cs425.lakedarlingresortapp.repository.AddressRepository;
import edu.mum.cs.cs425.lakedarlingresortapp.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("addressService")
public class AddressService implements IAddressService {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }
}
