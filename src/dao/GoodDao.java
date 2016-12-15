package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DbConn;
import db.DbClose;
import entity.Good;

/**
 * operate Good table in db
 * @author liwei
 *
 */
public final class GoodDao {
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	/**
	 * add a new good into db
	 * @param good
	 * @return true if succeed, false if failed
	 */
	public boolean addGood(Good good){
		boolean succeed = false;
		conn = DbConn.getConn();
		String sql = "INSERT INTO GOOD(GNAME,GPRICE,GNUM) VALUES(?,?,?)";
		
		try{
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, good.getgName());
			psmt.setDouble(2, good.getgPrice());
			psmt.setInt(3, good.getgNum());
			
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
	 * update the info of good
	 * @param key   choose which info to change. 1->gName, 2->gPrice, 3->gNum
	 * @param good
	 * @return true if succeed, false if failed
	 */
	public boolean updatesGood(int key, Good good){
		boolean succeed = false;
		conn = DbConn.getConn();
		switch(key){
		case 1:  // change good's name
			String sqlName = "UPDATE GOOD SET GNAME=? WHERE GID=?";
			try{
				psmt = conn.prepareStatement(sqlName);
				psmt.setString(1, good.getgName());
				psmt.setInt(2, good.getgID());
				
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
		case 2:  // change good's price
			String sqlPrice = "UPDATE GOOD SET GPRICE=? WHERE GID=?";
			try{
				psmt = conn.prepareStatement(sqlPrice);
				psmt.setDouble(1, good.getgPrice());
				psmt.setInt(2, good.getgID());
				
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
		case 3: // change good's number
			String sqlNum = "UPDATE GOOD SET GNUM=? WHERE GID=?";
			try{
				psmt = conn.prepareStatement(sqlNum);
				psmt.setInt(1, good.getgNum());
				psmt.setInt(2, good.getgID());
				
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
	 * delete a good via its ID
	 * @param gID
	 * @return true if succeed, false if failed
	 */
	public boolean deleteGood(int gID){
		boolean succeed = false;
		conn = DbConn.getConn();
		String sql = "DELETE FROM GOODS WHERE GID=?";
		try{
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, gID);
			
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
	 * show goods' info
	 * @param key  ways to search
	 * @return ArrayList of the searching result
	 */
	public ArrayList<Good> queryGood(int key){
		ArrayList<Good> goodList = new ArrayList<Good>();
		conn = DbConn.getConn();
		
		switch(key){
		case 1: // order by numbers
			String sqlgNum = "SELECT * FROM GOOD ORDER BY GNUM ASC";
			try{
				psmt = conn.prepareStatement(sqlgNum);
				rs = psmt.executeQuery();
				while(rs.next()){
					int gID = rs.getInt("GID");
					String gName = rs.getString(2);
					double gPrice = rs.getDouble(3);
					int gNum = rs.getInt(4);
					
					Good good = new Good(gID, gName, gPrice, gNum);
					goodList.add(good);
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				DbClose.queryClose(psmt, rs, conn);
			}
			break;
		case 2: //order by price
			String sqlgPrice = "SELECT * FROM GOOD ORDER BY GPRICE ASC";
			try{
				psmt = conn.prepareStatement(sqlgPrice);
				rs = psmt.executeQuery();
				while(rs.next()){
					int gID = rs.getInt("GID");
					String gName = rs.getString(2);
					double gPrice = rs.getDouble(3);
					int gNum = rs.getInt(4);
					
					Good good = new Good(gID, gName, gPrice, gNum);
					goodList.add(good);
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				DbClose.queryClose(psmt, rs, conn);
			}
			break;
		case 3:  //search by a key word
			String nameGet = ScannerChoice.ScannerInfoString();
			String sqlgName = "SELECT * FROM GOOD WHERE GNAME LIKE '%'||?||'%'";
			try{
				psmt = conn.prepareStatement(sqlgName);
				psmt.setString(1, nameGet);
				rs = psmt.executeQuery();
				while(rs.next()){
					int gID = rs.getInt("GID");
					String gName = rs.getString(2);
					double gPrice = rs.getDouble(3);
					int gNum = rs.getInt(4);
					
					Good good = new Good(gID, gName, gPrice, gNum);
					goodList.add(good);
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				DbClose.queryClose(psmt, rs, conn);
			}
			break;
		default:
			break;
		}
		return goodList;
	}
	/**
	 * show all the goods in db
	 * @return ArrayList of all the goods
	 */
	public ArrayList<Good> displayGood(){
		ArrayList<Good> goodList = new ArrayList<Good>();
		conn = DbConn.getConn();
		String sql = "SELECT * FROM GOOD";
		try{
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()){
				int gID = rs.getInt("GID");
				String gName = rs.getString(2);
				double gPrice = rs.getDouble(3);
				int gNum = rs.getInt(4);
				
				Good good = new Good(gID, gName, gPrice, gNum);
				goodList.add(good);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DbClose.queryClose(psmt, rs, conn);
		}
		return goodList;
	}
}
