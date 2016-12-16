package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DbConn;
import db.DbClose;
import entity.GoodSale;
/**
 * operate goodsale table in db
 * @author liwei
 *
 */
public final class GoodSaleDao {
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	/**
	 * show the goods sold today
	 * @return ArrayList of goodsale
	 */
	public ArrayList<GoodSale> dailyGoodSale(){
		ArrayList<GoodSale> goodSaleList= new ArrayList<GoodSale>();
		conn = DbConn.getConn();
		String sql = "SELECT GNAME,GPRICE,GNUM,ALLSUM FROM GOOD,"
				+"(SELECT GID AS SALESID, SUM(SNUM) AS ALLSUM "
					+"FROM GOODSALE WHERE TRUNC(SDATE)=TRUNC(SYSDATE) "
					+"GROUP BY GID) "
				+"WHERE GID = SALESID";
		try{
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()){
				String gName = rs.getString(1);
				double gPrice = rs.getDouble(2);
				int gNum = rs.getInt(3);
				int allSum = rs.getInt("ALLSUM");
				GoodSale goodSale = new GoodSale(gName, gPrice, gNum, allSum);
				goodSaleList.add(goodSale);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DbClose.queryClose(psmt, rs, conn);
		}
		return goodSaleList;
	}
	/**
	 * shopping settlement --add a new record of good sale
	 * @param goodSale
	 * @return true if succeed, false if failed
	 */
	public boolean shoppingSettlement(GoodSale goodSale){
		boolean succeed = false;
		conn = DbConn.getConn();
		String sql = "INSERT INTO GOODSALE(GID,SID,SNUM) VALUE(?,?,?)";
		try{
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, goodSale.getgID());
			psmt.setInt(2, goodSale.getsID());
			psmt.setInt(3, goodSale.getsNum());
			
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
}
