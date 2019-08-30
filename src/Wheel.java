import java.util.Random;
/**
 * This is where the wheel is stored and 'spin' via method
 * @version 1.0
 */

public class Wheel 
{
	private String[] wheel = {"Lose a Turn","400","1000","900","500","200","Bankrupt","700","450","1000","400","Free Spin","750","400","150","600","400","Bankrupt","1000","800","250","500","300","200"};
	private String segment;
	
	/**
	 * 
	 * @return the value of the wheel segment 'landed on'
	 */
	public String spinWheel ()
	{
		int idx = new Random().nextInt(wheel.length);
		segment = (wheel[idx]);
		return segment;
	}
}

