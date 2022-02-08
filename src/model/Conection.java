package model;

import java.sql.Connection;
import java.sql.DriverManager;


public class Conection {
	Connection con = null;
	
	public Connection getConnection(){
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ragon\\eclipse-workspace\\TierraMediaWeb\\db\\appTurismoDBWEB.db");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return con;
	}
}
