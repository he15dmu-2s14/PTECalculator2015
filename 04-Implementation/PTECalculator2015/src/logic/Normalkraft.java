package logic;

/**
 * Klassen Normalkraft fungere som Information Expert indenfor normalkraft
 * @author Louise
 */
public interface Normalkraft extends Kraft{
	
	/**
	 * Denne metode bruges til at saette vinklen. Dette goeres vha. et parameter fra ekspert-klassen Vinkel
	 * @param vinkel er vinklen angivet i grader
	 */
	public void setVinkel(Vinkel vinkel);
	
	/**
	 * Denne metode bruges til at saette belastningen. Dette goeres vha. et parameter fra ekspert-klassen Belastning
	 * @param belastning er belastningen angivet i N, kg el. ton
	 */
	public void setBelastning(Belastning belastning);
	
	/**
	 * Denne metode bruges til at f� normalkraften(Fn). Dette goeres vha. denne formel: (fdim = kg * 9,816) 
	 * @return Normalkraften hvis det er muligt at beregne den. 
	 * 		   Ellers returneres NaN
	 */
	public double getNormalkraft();

}
