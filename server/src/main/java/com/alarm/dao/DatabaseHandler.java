package com.alarm.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import com.alarm.constants.DatabaseQueryConstants;

public class DatabaseHandler {
	
	public static void setUpDatabase(JdbcTemplate jdbcTemplate) {
		jdbcTemplate.execute(DatabaseQueryConstants.DROP_USERS);
		jdbcTemplate.execute(DatabaseQueryConstants.CREATE_USERS);
		jdbcTemplate.execute(DatabaseQueryConstants.DROP_ALARMS);
		jdbcTemplate.execute(DatabaseQueryConstants.CREATE_ALARMS);
		System.out.println("tables created");
	}

}
