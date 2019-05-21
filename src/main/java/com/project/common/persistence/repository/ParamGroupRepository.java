package com.project.common.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.common.persistence.entities.ParamGroup;

public interface ParamGroupRepository extends CrudRepository<ParamGroup, Integer>{
	
	ParamGroup findByTypeAndValue(String type, String value);

}
