package logic;

/**
 * Klassen Normalkraft fungere som Information Expert indenfor normalkraft
 * @author Louise
 */
public interface Normalkraft extends Kraft{
		
	/**
	 * Denne metode bruges til at f� normalkraften(Fn). Dette goeres vha. denne formel: (fdim = kg * 9,816) 
	 * @return Normalkraften hvis det er muligt at beregne den. 
	 * 		   Ellers returneres NaN
	 */
	public double getNormalkraft();

}
