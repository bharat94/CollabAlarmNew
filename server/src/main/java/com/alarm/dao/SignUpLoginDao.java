package com.alarm.dao;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alarm.constants.DatabaseQueryConstants;
import com.alarm.model.Alarm;
import com.alarm.model.User;

public class SignUpLoginDao {
	
	public Boolean addUser(User user, JdbcTemplate jdbcTemplate) {
		Boolean flag = true;
		try {
			jdbcTemplate.batchUpdate(DatabaseQueryConstants.INSERT_USER);
		} catch (DataAccessException e) {
			flag = false;
		}
		return flag;
	}

	public Boolean authenticate(User user, JdbcTemplate jdbcTemplate) {
		Boolean flag = false;
		try {
//			jdbcTemplate.batchUpdate(DatabaseQueryConstants.SELECT_USER);
			User retrievedUser = (User) jdbcTemplate.query(DatabaseQueryConstants.SELECT_USER, new Object[] { "rohan", "pass123" }, (rs, rowNum) -> new User(rs.getString("name"), rs.getString("username"), rs.getString("password")));
			if(retrievedUser.getUsername().equalsIgnoreCase(user.getUsername())) {
				flag = true;
			}
		} catch (DataAccessException e) {
			flag = false;
		}
		return flag;
	}

	public Boolean saveAlarm(Alarm alarm, JdbcTemplate jdbcTemplate) {
		Boolean flag = true;
		try {
			String time = alarm.getTime(); 
			jdbcTemplate.batchUpdate(DatabaseQueryConstants.INSERT_ALARM, time);
		} catch (DataAccessException e) {
			flag = false;
		}
		return flag;
	}
	
	public String getAlarm(JdbcTemplate jdbcTemplate) {
		Alarm time = (Alarm) jdbcTemplate.query(DatabaseQueryConstants.SELECT_ALARM, (rs, rowNum) -> new Alarm(rs.getString("time")));
		System.out.println("Time: " + time.getTime());
		return time.getTime();
	}

}