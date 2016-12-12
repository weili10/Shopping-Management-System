package entity;
/**
 * The goods in the shop. Each good has its own ID, Name, Price and Number.
 * The good class is used to add a new good, show all info of a good, and change a good's info via its ID.
 * @author liwei
 *
 */
public final class Good {
	private int gID;
	private String gName;
	private double gPrice;
	private int gNum;
	/**
	 * add info of one good	
	 * @param gName
	 * @param gPrice
	 * @param gNum
	 */
	public Good(String gName, double gPrice, int gNum) {
		super();
		this.gName = gName;
		this.gPrice = gPrice;
		this.gNum = gNum;
	}
	/**
	 * show all info of one good
	 * @param gID
	 * @param gName
	 * @param gPrice
	 * @param gNum
	 */
	public Good(int gID, String gName, double gPrice, int gNum) {
		super();
		this.gID = gID;
		this.gName = gName;
		this.gPrice = gPrice;
		this.gNum = gNum;
	}
	
	/**
	 * change a good's info via it's ID	
	 * @param gID
	 * @param gNum
	 */
	
	public Good(int gID, int gNum) {
		super();
		this.gID = gID;
		this.gNum = gNum;
	}
	/**
	 * change a good's info via it's ID	
	 * @param gID
	 * @param gPrice
	 */
	public Good(int gID, double gPrice) {
		super();
		this.gID = gID;
		this.gPrice = gPrice;
	}
	/**
	 * change a good's info via it's ID	
	 * @param gID
	 * @param gName
	 */
	public Good(int gID, String gName) {
		super();
		this.gID = gID;
		this.gName = gName;
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

}
