package com.flashwizserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.flashwizserver.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

}
