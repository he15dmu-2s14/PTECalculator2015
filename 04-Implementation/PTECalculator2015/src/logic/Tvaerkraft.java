package logic;

/**
 * Klassen Tvaerkraft fungere som Information Expert indenfor tvaerkraft
 * @author Louise
 */
public interface Tvaerkraft {
	
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
	 * Denne metode bruges til at få tvaerkraften(Ft). Dette goeres vha. denne formel: (fdim = kg * 9,826) 
	 * @return Tvaerkraften hvis det er muligt at beregne den. 
	 * 		   Ellers returneres NaN
	 */
	public double getTvaerkraft();

}
