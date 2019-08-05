package com.mchain.test.auth.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.mchain.test.auth.entity.AuthRole;

public interface AuthRoleRepository extends JpaSpecificationExecutor<AuthRole>, PagingAndSortingRepository<AuthRole, String> {

}

