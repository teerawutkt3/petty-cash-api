package com.project.pettycash.withdraw.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.pettycash.withdraw.persistence.entities.PettyCash;

public interface PettyCashReposirory extends CrudRepository<PettyCash, Integer> {

}
