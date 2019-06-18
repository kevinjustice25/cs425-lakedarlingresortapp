package edu.mum.cs.cs425.lakedarlingresortapp.service.impl;

import edu.mum.cs.cs425.lakedarlingresortapp.model.Owner;
import edu.mum.cs.cs425.lakedarlingresortapp.model.Villa;
import edu.mum.cs.cs425.lakedarlingresortapp.repository.VillaRepository;
import edu.mum.cs.cs425.lakedarlingresortapp.service.IVillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("villaService")
public class VillaService implements IVillaService {

    @Autowired
    VillaRepository villaRepository;

    @Autowired
    AddressService addressService;

    @Autowired
    OwnerService ownerService;

    @Override
    public List<Villa> findAll() {
        return villaRepository.findAll();
    }

    @Override
    public Villa save(Villa villa, Long ownerId) {
        villa.setOwner(ownerService.findById(ownerId));
        return villaRepository.save(villa);
    }

    @Override
    public Villa findById(Long villaId) {
        return villaRepository.findById(villaId).get();
    }

    @Override
    public boolean existsbyId(Long villaId) {
        return villaRepository.existsById(villaId);
    }
}
