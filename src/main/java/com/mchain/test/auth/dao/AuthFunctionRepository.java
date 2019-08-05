package com.mchain.test.auth.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.mchain.test.auth.entity.AuthFunction;

public interface AuthFunctionRepository extends JpaSpecificationExecutor<AuthFunction>, PagingAndSortingRepository<AuthFunction, String> {

}

