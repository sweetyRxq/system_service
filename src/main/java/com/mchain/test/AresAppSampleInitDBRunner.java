package com.mchain.test;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import com.mchain.test.auth.dao.AuthFunctionRepository;
import com.mchain.test.auth.dao.AuthMenuRepository;
import com.mchain.test.auth.dao.AuthRoleRepository;
import com.mchain.test.auth.dao.AuthUserRepository;

@Component
@Order(1)
public class AresAppSampleInitDBRunner implements ApplicationRunner {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private AuthRoleRepository authRoleRepository;
	@Autowired
	private AuthUserRepository authUserRepository;
	@Autowired
	private AuthFunctionRepository authFunctionRepository;
	@Autowired
	private AuthMenuRepository authMenuRepository;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		this.initRoles();
		this.initUsers();
		this.initFunctions();
		this.initMenus();
	}
	
	/**
	 * Initiate the roles
	 */
	private void initRoles() {
		long count = authRoleRepository.count();
		if(count == 0L) {
			importFromFile("db.postgresql/roles.sql");
		}
	}
	
	/**
	 * Initiate the users
	 */
	private void initUsers() {
		long count = authUserRepository.count();
		if(count == 0L) {
			importFromFile("db.postgresql/users.sql");
		}
	}
	
	private void initFunctions() {
		long count = authFunctionRepository.count();
		if(count == 0L) {
			importFromFile("db.postgresql/functions.sql");
		}
	}
	
	private void initMenus() {
		long count = authMenuRepository.count();
		if(count == 0L) {
			importFromFile("db.postgresql/menus.sql");
		}
	}
	/**
	 * Import database SQL script from resources file
	 * @param path The relative path of the SQL file
	 * @return
	 */
	private boolean importFromFile(String path) {
		try {
			Resource resource = new ClassPathResource(path);
			ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(resource);
			databasePopulator.execute(dataSource);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}

