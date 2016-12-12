package entity;
/**
 * The transaction of a good sale. For each sale, we record the good's ID, name, price, store number and sold number, 
 * the total sold number, and the sales man's ID.
 * 
 * @author liwei
 *
 */
public final class GoodSale {
	private int gID;
	private int sID;
	private int sNum;
	
	private String gName;
	private double gPrice;
	private int gNum;
	private int allSum;
	/**
	 * implement a sale
	 * @param gID
	 * @param sID
	 * @param sNum
	 */
	public GoodSale(int gID, int sID, int sNum) {
		super();
		this.gID = gID;
		this.sID = sID;
		this.sNum = sNum;
	}
	/**
	 * show the goods list
	 * @param gName
	 * @param gPrice
	 * @param gNum
	 * @param allSum
	 */
	public GoodSale(String gName, double gPrice, int gNum, int allSum) {
		super();
		this.gName = gName;
		this.gPrice = gPrice;
		this.gNum = gNum;
		this.allSum = allSum;
	}
	/**
	 * getters and setters
	 * @return
	 */
	public int getgID() {
		return gID;
	}
	public void setgID(int gID) {
		this.gID = gID;
	}
	public int getsID() {
		return sID;
	}
	public void setsID(int sID) {
		this.sID = sID;
	}
	public int getsNum() {
		return sNum;
	}
	public void setsNum(int sNum) {
		this.sNum = sNum;
	}
	public String getgName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	public double getgPrice() {
		return gPrice;
	}
	public void setgPrice(double gPrice) {
		this.gPrice = gPrice;
	}
	public int getgNum() {
		return gNum;
	}
	public void setgNum(int gNum) {
		this.gNum = gNum;
	}
	public int getAllSum() {
		return allSum;
	}
	public void setAllSum(int allSum) {
		this.allSum = allSum;
	}
	
	
}
