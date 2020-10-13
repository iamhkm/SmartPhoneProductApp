package com.zversal.connection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBConnection {

	//private final String configFile;
	private HikariConfig cfg = null;
	private HikariDataSource ds = null;
	private Properties properties = new Properties();
	
	  /*
	  public DBConnection() 
	  { configFile ="res/DBConnectionAttributes.properties";
	  cfg = new HikariConfig(configFile); 
	  ds = new HikariDataSource(cfg);
	  }
      */
	
	public DBConnection() {
		try (InputStream iStream = this.getClass().getResourceAsStream("/DBConnectionAttributes.properties");) {
			properties.load(iStream);
			cfg = new HikariConfig(properties);
			ds = new HikariDataSource(cfg);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Connection connection() {
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

}
