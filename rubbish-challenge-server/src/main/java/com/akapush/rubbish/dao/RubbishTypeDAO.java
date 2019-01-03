package com.akapush.rubbish.dao;

import org.springframework.data.repository.CrudRepository;

import com.akapush.rubbish.domain.model.RubbishType;

public interface RubbishTypeDAO extends CrudRepository<RubbishType, Long> {

}
