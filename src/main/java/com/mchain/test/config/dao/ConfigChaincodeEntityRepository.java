package com.mchain.test.config.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.mchain.test.config.entity.ConfigChaincodeEntity;

public interface ConfigChaincodeEntityRepository
		extends JpaSpecificationExecutor<ConfigChaincodeEntity>, PagingAndSortingRepository<ConfigChaincodeEntity, String> {

}

