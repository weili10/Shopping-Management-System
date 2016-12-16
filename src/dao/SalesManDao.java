package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DbClose;
import db.DbConn;
import entity.SalesMan;
/**
 * operate SalesMan table in db
 * @author liwei
 *
 */
public final class SalesManDao {
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	/**
	 * salesman log in
	 * @param sName
	 * @return arrayList of salesman, containing the sID and sPassword that matched the given sName
	 */
	public ArrayList<SalesMan> checkStandLog(String sName){
		ArrayList<SalesMan> salesManInfo = new ArrayList<SalesMan>();
		conn = DbConn.getConn();
		String sql = "SELECT SID, SPASSWORD FROM SALESMAN WHERE SNAME=?";
		try{
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, sName);
			rs = psmt.executeQuery();
			while(rs.next()){
				String sPassword = rs.getString("sPassword");
				int sID = rs.getInt("sID");
				SalesMan salesMan = new SalesMan(sID,sPassword);
				salesManInfo.add(salesMan);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DbClose.queryClose(psmt, rs, conn);
		}
		return salesManInfo;
	}
	/**
	 * add a new salesman
	 * @param sMan
	 * @return true if succeed, false if failed
	 */
	public boolean addSalesMan(SalesMan sMan){
		boolean succeed = false;
		conn = DbConn.getConn();
		String sql = "INSERT INTO SALESMAN(SNAME,SPASSWORD) VALUE(?,?)";
		try{
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, sMan.getsName());
			psmt.setString(2, sMan.getsPassWord());
			
			int rs = psmt.executeUpdate();
			if(rs>0){
				succeed = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DbClose.addClose(psmt, conn);
		}
		return succeed;
	}
	/**
	 * update salesMan's info
	 * @param key  what info to update. 1->sName, 2->sPassword
	 * @param sMan
	 * @return true if succeed, false if failed
	 */
	public boolean updateSalesMan(int key, SalesMan sMan){
		boolean succeed = false;
		conn = DbConn.getConn();
		switch(key){
		case 1:  //change salesman's name
			String sqlName = "UPDATE SALESMAN SET SNAME=? WHERE SID=?";
			try{
				psmt = conn.prepareStatement(sqlName);
				psmt.setString(1, sMan.getsName());
				psmt.setInt(2, sMan.getsID());
				
				int rs = psmt.executeUpdate();
				if(rs>0){
					succeed = true;
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				DbClose.addClose(psmt, conn);
			}
			break;
		case 2:  //change salesman's password
			String sqlPw = "UPDATE SALESMAN SET SPASSWORD=? WHERE SID=?";
			try{
				psmt = conn.prepareStatement(sqlPw);
				psmt.setString(1, sMan.getsPassWord());
				psmt.setInt(2, sMan.getsID());
				
				int rs = psmt.executeUpdate();
				if(rs>0){
					succeed = true;
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				DbClose.addClose(psmt, conn);
			}
			break;
		default:
			break;
		}
		return succeed;
	}
	/**
	 * delete a salesman
	 * @param sName   the salesman's name
	 * @return true if succeed, false if failed
	 */
	public boolean deleteSalesMan(String sName){
		boolean succeed = false;
		conn = DbConn.getConn();
		String sql = "DELETE FROM SALESMAN WHERE SNAME=?";
		try{
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, sName);
			int rs = psmt.executeUpdate();
			if(rs>0){
				succeed = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DbClose.addClose(psmt, conn);
		}
		return succeed;
	}
	/**
	 * search for the info of a salesman
	 * @param sName   the salesman's name
	 * @return ArrayList of salesman who named sName
	 */
	public ArrayList<SalesMan> querySalesMan(String sName){
		ArrayList<SalesMan> salesManList = new ArrayList<SalesMan>();
		conn = DbConn.getConn();
		String sql = "SELECT * FROM SALESMAN WHERE SNAME LIKE ?";
		sName = "%" + sName + "%";
		try{
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, sName);
			rs = psmt.executeQuery();
			while(rs.next()){
				int sID = rs.getInt("SID");
				String sname = rs.getString(2);
				String sPassword = rs.getString(3);
				
				SalesMan sMan = new SalesMan(sID, sname, sPassword);
				salesManList.add(sMan);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DbClose.queryClose(psmt, rs, conn);
		}
		return salesManList;
	}
	/**
	 * show all salesmen's info
	 * @return Arraylist of all the salesmen
	 */
	public ArrayList<SalesMan> displaySalesMan(){
		ArrayList<SalesMan> salesManList = new ArrayList<SalesMan>();
		conn = DbConn.getConn();
		String sql = "SELECT * FROM SALESMAN";
		try{
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()){
				int sID = rs.getInt("SID");
				String sName = rs.getString(2);
				String sPassword = rs.getString(3);
				
				SalesMan sMan = new SalesMan(sID, sName, sPassword);
				salesManList.add(sMan);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DbClose.queryClose(psmt, rs, conn);
		}
		return salesManList;
	}
}
