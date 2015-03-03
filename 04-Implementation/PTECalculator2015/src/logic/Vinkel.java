package logic;

/**
 * Klassen Vinkel fungere som information Expert indenfor vinkler 
 * @author Louise
 */
public interface Vinkel {
	
	/**
	 * Denne metode bruges til at f� oplyst vinklen i grader.
	 * @return vinklens antal grader
	 */
	public double getGradtal();
	
	/**
	 * Denne metode bruges til at bestemme, om der er tale om, om vinklen er maalt til vandret el. lodret tvaersnit
	 * @return True = Vandret
	 */
	public boolean tilVandret();
	
	/**
	 * Denne metode bruge til at faa angive vinklens stoerrelse i grader, og om det er vandret el. lodret
	 * @param vinkel er vinklen i grader
	 * @param tilVandret er on vinklen er maalt til vandret
	 * @throws UgyldigVinkelException 
	 */
	public void setGradtal(double vinkel, boolean tilVandret) throws UgyldigVinkelException;
	
	/**
	 * Denne metode bruges til at fortaelle, om den angivne vaerdi ligger udenfor normalen.
	 * Normalt interval ligger p� 0 < 90
	 * @return True hvis mindre end 0 el. stoerre end 90 
	 */
	public boolean erUdenForNormalomraade();

}
