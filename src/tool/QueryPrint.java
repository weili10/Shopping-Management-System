package tool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.GoodDao;
import db.DbConn;
import db.DbClose;
import entity.Good;
import entity.SalesMan;
/**
 * query && print
 * @author liwei
 *
 */
public final class QueryPrint {
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	
	/**
	 * query good info via gID or gName
	 * @param gID
	 * @param gName
	 * @return ArrayList of good
	 */
	public ArrayList<Good> queryGoodKey(int gID, String gName){
		ArrayList<Good> goodList = new ArrayList<Good>();
		conn = DbConn.getConn();
		String sql = "SELECT * FROM GOOD WHERE GID=? OR GNAME=?";
		try{
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, gID);
			psmt.setString(2, gName);
			rs = psmt.executeQuery();
			
			while(rs.next()){
				int gid = rs.getInt("GID");
				String gname = rs.getString(2);
				double gprice = rs.getDouble(3);
				int gnum = rs.getInt(4);
				
				Good good = new Good(gid, gname, gprice, gnum);
				goodList.add(good);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DbClose.queryClose(psmt, rs, conn);
		}
		return goodList;
	}
	
	/**
	 * query & list good info
	 * @param oper
	 * @return gID, -1 if have any exception
	 */
	public static int query(String oper){
		int gid = -1;
		String shopping = ScannerChoice.ScannerInfoString();   //get the good's name from keyboard
		ArrayList<Good> goodList = new QueryPrint().queryGoodKey(-1, shopping);
		if(goodList == null || goodList.size() <= 0){
			System.err.println("\t!!no such good!!");
			
			//choose the next step
			ScannerChoice.changedInfoNext(oper);
		}else{   //find the good, perform the Change Good's Info operation
			Good good = goodList.get(0);
			
			System.out.println("\t\t\t\t\tgood list\n\n");
			System.out.println("\tid\t\tname\t\tprice\t\tnumber\t\tremarks");
			System.out.println("\t"+good.getgID()+"\t\t"+good.getgName()+"\t\t"+good.getgPrice()+"\t\t"+good.getgNum());
			
			if(good.getgNum() == 0){
				System.out.println("\t\tsold out");
			}else if(good.getgNum() < 10){
				System.out.println("\t\tless than 10");
			}else{
				System.out.println("\t\t-");
			}
			gid = good.getgID();
		}
		return gid;
	}
	/**
	 * search tool
	 * @return If there is only one kind of good and it has stocks, return its id. 
	 *         Otherwise, return -1->good has been sold out, -2->only 1 left, -3-> no such good
	 */
	public static int querySettlement(){
		int gID = -1;
		ArrayList<Good> goodSettlement = new GoodDao().queryGood(3);  //search good via a key word
		if(goodSettlement == null || goodSettlement.size() <= 0){
			System.out.println("\t!!no such good!!\n");
			gID = -3;
		}else{
			System.out.println("\t\t\t\t\tgood list\n\n");
			System.out.println("\tid\t\tname\t\tprice\t\tnumber\t\tremarks");
			for(int i = 0; i < goodSettlement.size(); i++){
				Good good = goodSettlement.get(i);
				if(good.getgNum() >= 0){
					System.out.println("\t"+good.getgID()+"\t\t"+good.getgName()+"\t\t"
										+good.getgPrice()+"\t\t"+good.getgNum());
					if(good.getgNum() == 0){
						System.out.println("\t\tsold out");
					}else if(good.getgNum() < 10){
						System.out.println("\t\tless than 10");
					}else{
						System.out.println("\t\t-");
					}
					if(goodSettlement.size() == 1){
						gID = good.getgID();
					}else{
						gID = -2;
					}
				}
			}
		}
		return gID;
	}
	/**
	 * query a sales man's info
	 * @param sName  the name of the sales man
	 * @return ArrayList of SalesMan
	 */
	public ArrayList<SalesMan> querySalesMan(String sName){
		ArrayList<SalesMan> smList = new ArrayList<SalesMan>();
		conn = DbConn.getConn();
		String sql = "SELECT * FROM SALESMAN WHERE SNAME=?";
		try{
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, sName);
			rs = psmt.executeQuery();
			while(rs.next()){
				int sid = rs.getInt("SID");
				String sname = rs.getString(2);
				String spassword = rs.getString(3);
				
				SalesMan salesMan = new SalesMan(sid, sname, spassword);
				smList.add(salesMan);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DbClose.queryClose(psmt, rs, conn);
		}
		return smList;
	}
}
