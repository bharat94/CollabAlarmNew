package com.alarm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alarm.dao.SignUpLoginDao;
import com.alarm.model.Alarm;
import com.alarm.model.User;

@RestController
public class SignUpLoginController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private String timeData;
	
	@RequestMapping(value="/signup")
	public Boolean signUp(@RequestParam(value="name") String name, @RequestParam(value="username") String username, @RequestParam(value="password") String password) {
		System.out.println("User: " + name);
		User user = new User(name, username, password);
		return new SignUpLoginDao().addUser(user, jdbcTemplate);
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public Boolean login(@RequestParam(value="username") String username, @RequestParam(value="password") String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		return new SignUpLoginDao().authenticate(user, jdbcTemplate);
	}
	
	@RequestMapping(value="/saveAlarm")
	public Boolean saveAlarm(@RequestParam(value="time") String time) {
		System.out.println("Got time" + time);
		timeData = time;
//		return new SignUpLoginDao().saveAlarm(new Alarm(time), jdbcTemplate);
		return true;
	}
	
	@RequestMapping(value="/getAlarm")
	public String getAlarm() {
		System.out.println("Sending out time " + timeData);
		return timeData;
//		return new SignUpLoginDao().getAlarm(jdbcTemplate);
	}
	
}
