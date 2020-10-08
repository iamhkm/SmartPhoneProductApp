package com.zversal.connection;

import java.sql.Connection;
import java.sql.SQLException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBConnection {

	private final String configFile;
	private final HikariConfig cfg;
	private final HikariDataSource ds;

	public DBConnection() {
		configFile = "src/main/res/DBConnectionAttributes.properties";
		cfg = new HikariConfig(configFile);
		ds = new HikariDataSource(cfg);
	}

	public Connection connection() {
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

}
