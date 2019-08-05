package com.mchain.test.auth.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.mchain.test.auth.entity.AuthMenu;

public interface AuthMenuRepository extends JpaSpecificationExecutor<AuthMenu>, PagingAndSortingRepository<AuthMenu, String> {

	@Query("SELECT m FROM AuthMenu m WHERE m.parentCode IS NULL ORDER BY m.sortIndex ASC")
	List<AuthMenu> fetchTopMenus();
}

