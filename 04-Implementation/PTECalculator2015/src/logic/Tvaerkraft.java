package logic;

public interface Tvaerkraft {
	
	public void setVinkel(Vinkel vinkel);
	
	public void setBelastning(Belastning belastning);
	
	/**
	 * @return Tvaerkraften hvis det er muligt at beregne den. 
	 * 		   Ellers returneres NaN.
	 */
	public double getTvaerkraft();

}
