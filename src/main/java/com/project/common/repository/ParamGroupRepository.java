package com.project.common.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.common.entities.ParamGroup;

public interface ParamGroupRepository extends CrudRepository<ParamGroup, Integer>{
	
	ParamGroup findByTypeAndValue(String type, String value);

}
