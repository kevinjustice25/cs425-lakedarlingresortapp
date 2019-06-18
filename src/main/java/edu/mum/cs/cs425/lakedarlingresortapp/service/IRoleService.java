package edu.mum.cs.cs425.lakedarlingresortapp.service;

import edu.mum.cs.cs425.lakedarlingresortapp.model.Role;

public interface IRoleService {
    Role save(Role role);
    Role findById(Integer roleId);
}
