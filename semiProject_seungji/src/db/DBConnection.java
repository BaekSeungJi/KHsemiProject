package db;
import java.sql.*;

public class DBConnection {
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Loading Success!!");			
		} catch (ClassNotFoundException e) {			 
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection()throws Exception {
		Connection conn = null;		
		
		conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.30.35:1521:xe", "hr", "hr");
		System.out.println("DB Connection Success!!");
			
		return conn;
	}

}




