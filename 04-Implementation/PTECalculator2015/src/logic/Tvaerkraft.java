package logic;

/**
 * Klassen Tvaerkraft fungere som Information Expert indenfor tvaerkraft
 * @author Louise
 */
public interface Tvaerkraft extends Kraft{
	/**
	 * Denne metode bruges til at f� tvaerkraften(Ft). Dette goeres vha. denne formel: (fdim = kg * 9,826) 
	 * @return Tvaerkraften hvis det er muligt at beregne den. 
	 * 		   Ellers returneres NaN
	 */
	public double getTvaerkraft();

}
