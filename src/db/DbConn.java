package db;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**
 * connect to Oracle db
 * @author liwei
 *
 */
public final class DbConn {
	public static Connection getConn(){
		Connection conn = null;
		
		String user = "scott";
		String password = "tiger";
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");         //load oracle driver
			conn = DriverManager.getConnection(url, user, password);  //get connection
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		return conn;
	}
}
