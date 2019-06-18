package edu.mum.cs.cs425.lakedarlingresortapp.service;


import edu.mum.cs.cs425.lakedarlingresortapp.model.Villa;

import java.util.List;

public interface IVillaService {
    List<Villa> findAll();
    Villa save(Villa villa, Long ownerId);
    Villa findById(Long villaId);
    boolean existsbyId(Long villaId);
}
