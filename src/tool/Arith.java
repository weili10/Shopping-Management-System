package tool;
import java.math.BigDecimal;
/**
 * provide accurate float-point operations, including addition, subtraction, multiplication, division, and rounding.
 * @author liwei
 *
 */
public class Arith {
	private static final int DIFF_DIV_SCALE = 2;    //the default precision of division operation.
	private Arith(){}
	/**
	 * accurate addition operation.
	 * @param v1
	 * @param v2
	 * @return the sum of v1 and v2
	 */
	public static double add(double v1, double v2){
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}
	/**
	 * accurate subtraction operation
	 * @param v1
	 * @param v2
	 * @return the diff between v1 and v2
	 */
	public static double sub(double v1, double v2){
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}
	/**
	 * accurate multiplication operation
	 * @param v1
	 * @param v2
	 * @return the product of v1 and v2
	 */
	public static double mul(double v1, double v2){
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}
	/**
	 * accurate division operation
	 * @param v1
	 * @param v2
	 * @param scale   specifies the number of decimal place
	 * @return  v1/v2
	 */
	public static double div(double v1, double v2, int scale){
		if(scale < 0){
			throw new IllegalArgumentException("the scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * accurate division operation, keep 2 decimal place.
	 * @param v1
	 * @param v2
	 * @return  v1/v2
	 */
	public static double div(double v1, double v2){
		return div(v1, v2, DIFF_DIV_SCALE);
	}
	/**
	 * accurate rounding operation
	 * @param v     the number need to be rounded
	 * @param scale  specifies the number of decimal place
	 * @return  rounded v with specific scale
	 */
	public static double round(double v, int scale){
		if(scale < 0){
			throw new IllegalArgumentException("the scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
}
