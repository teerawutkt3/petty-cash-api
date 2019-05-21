package com.project.common.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.common.persistence.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {

}
