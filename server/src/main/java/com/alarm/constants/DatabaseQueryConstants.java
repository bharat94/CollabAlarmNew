package com.alarm.constants;

public class DatabaseQueryConstants {
	
	public static final String DROP_USERS = "DROP TABLE users IF EXISTS";
	public static final String CREATE_USERS = "CREATE TABLE users( id SERIAL, name VARCHAR2(255), username VARCHAR2(255), password VARCHAR2(255))";
	public static final String INSERT_USER = "INSERT INTO users (name, username, password) VALUES (?, ?, ?)";
	public static final String SELECT_USER = "SELECT name, username, password FROM users WHERE username = ? and password = ?";
	public static final String DROP_ALARMS = "DROP TABLE alarms IF EXISTS";
	public static final String CREATE_ALARMS = "CREATE TABLE alarms( id SERIAL, time VARCHAR2(255))";
	public static final String INSERT_ALARM = "INSERT INTO alarms (time) VALUES (?)";
	public static final String SELECT_ALARM = "SELECT time FROM alarms";
}
