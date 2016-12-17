package db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * close the resource used in operating the db
 * @author liwei
 *
 */
public final class DbClose {
	/**
	 * close 'add' resource
	 * @param psmt
	 * @param conn
	 */
	public static void addClose(PreparedStatement psmt, Connection conn){
		/**
		 * multiple 'try-catch': for security
		 * single 'try-catch': once one of the exception occurs, the consequent resources can't be closed.
		 */
		try{
			if(psmt != null){
				psmt.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		try{
			if(conn != null){
				conn.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	/**
	 * close resource
	 * @param pmt
	 * @param rs
	 * @param conn
	 */
	public static void queryClose(PreparedStatement psmt, ResultSet rs, Connection conn){
		try{
			if(psmt != null){
				psmt.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		try{
			if(rs != null){
				rs.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		try{
			if(conn != null){
				conn.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
