package edu.mum.cs.cs425.lakedarlingresortapp.service.impl;


import edu.mum.cs.cs425.lakedarlingresortapp.model.Role;
import edu.mum.cs.cs425.lakedarlingresortapp.repository.RoleRepository;
import edu.mum.cs.cs425.lakedarlingresortapp.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleService implements IRoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findById(Integer roleId) {
        return roleRepository.findById(roleId).get();
    }
}
