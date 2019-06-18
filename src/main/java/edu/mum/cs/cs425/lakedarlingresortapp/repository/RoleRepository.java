package edu.mum.cs.cs425.lakedarlingresortapp.repository;

import edu.mum.cs.cs425.lakedarlingresortapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
}
