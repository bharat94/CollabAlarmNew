package com.alarm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alarm.constants.DatabaseQueryConstants;
import com.alarm.dao.DatabaseHandler;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}
	
	@Autowired
	static JdbcTemplate jdbcTemplate;
	
	public void run(String... strings) {
		log.info("Setting up the database");
		System.out.println("Set it up");
//		DatabaseHandler.setUpDatabase(jdbcTemplate);
		jdbcTemplate.execute(DatabaseQueryConstants.DROP_USERS);
		jdbcTemplate.execute(DatabaseQueryConstants.CREATE_USERS);
		jdbcTemplate.execute(DatabaseQueryConstants.DROP_ALARMS);
		jdbcTemplate.execute(DatabaseQueryConstants.CREATE_ALARMS);
	}
	
}
