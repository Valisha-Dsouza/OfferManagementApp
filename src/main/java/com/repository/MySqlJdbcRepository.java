package com.repository;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class MySqlJdbcRepository {
	public static Connection connection = null;
	public static String url;
	public static String username;
	public static String password;
	
	static{
		
		Properties properties = new Properties();
		try {
			InputStream fileInputStream = MySqlJdbcRepository.class.getClassLoader().getResourceAsStream("mysql.properties");
			properties.load(fileInputStream);
			username = properties.getProperty("jdbc.username");
			password = properties.getProperty("jdbc.password");
			url = "jdbc:mysql://"+properties.getProperty("jdbc.host")+":"+ properties.getProperty("jdbc.port") + "/" + properties.getProperty("jdbc.database");
		} catch (IOException e) {
			System.out.println("Jdbc Properties file not found");
		} 
	}

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(url,username,password);
		return connection;
	}

}