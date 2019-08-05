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
@Component
@Order(1)
public class TestServiceInitDBRunner implements ApplicationRunner {
	@Autowired
	private DataSource dataSource;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		this.initChaincodeEntities();
		this.initFunctions();
		this.initMenus();
	}
	/**
	 * Initiate the roles
	 */
	private void initFunctions() {
		importFromFile("db.postgresql/business/functions.sql");
	}
	private void initChaincodeEntities() {
		importFromFile("db.postgresql/business/chaincodeentities.sql");
	}
	private void initMenus() {
		importFromFile("db.postgresql/business/menus.sql");
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