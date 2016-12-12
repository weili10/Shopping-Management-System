package entity;
/**
 * The sales men in the shop. Each sales man has his own ID, Name, and Password.
 * All the sales man can login this system by its name and password.
 * @author liwei
 *
 */
public final class SalesMan {
	private int sID;
	private String sName;
	private String sPassword;
	
	/**
	 * check the info used in login
	 * @param sID
	 * @param sPassWord
	 */
	public SalesMan(int sID, String sPassWord) {
		super();
		this.sID = sID;
		this.sPassword = sPassWord;
	}

	/**
	 * search for a sales man, or change his password
	 * @param sID
	 * @param sName
	 * @param sPassWord
	 */
	public SalesMan(int sID, String sName, String sPassWord) {
		super();
		this.sID = sID;
		this.sName = sName;
		this.sPassword = sPassWord;
	}
	/**
	 * create a new sales man 
	 * @param sName
	 * @param sPassWord
	 */
	public SalesMan(String sName, String sPassWord) {
		super();
		this.sName = sName;
		this.sPassword = sPassWord;
	}
	/**
	 * getters and setters
	 * @return
	 */
	public int getsID() {
		return sID;
	}

	public void setsID(int sID) {
		this.sID = sID;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsPassWord() {
		return sPassword;
	}

	public void setsPassWord(String sPassWord) {
		this.sPassword = sPassWord;
	}

}
