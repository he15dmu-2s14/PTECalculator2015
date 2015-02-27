package logic;

/**
 * Klassen Belastning fungere som Information Expert indenfor belastningen 
 * @author Louise
 */
public interface Belastning {

	/**
	 * Denne metode bruges til at faa belastningen oplyst i N
	 * @return belastning i N (Fdim)
	 */
	public double getBelastning();
	
	/**
	 * Denne metode bruges til at faa belastningen oplyst i kg
	 * @return belastning i kg
	 */
	public double getBelastningIKg();
	
	/**
	 *	Denne metode bruges til at faa belastningen oplyst i ton
	 * @return belastningen i ton
	 */
	public double getBelastningITon();
	
	/**
	 * Denne metode bruges til at faa belastningen sat.
	 *
	 * Brugeren oplyser hvilken vaerdi, der er tale om
	 * Brugeren oplyser om vaerdien er angivet i N, kg el. ton
	 *
	 * Der kastes en UgyldigInputException, hvis der er tale om en negativ vaerdi.
	 * @param vaerdi
	 * @param enhed
	 * @throws UgyldigBelastningException
	 * @see logic.Enhed
	 */
	public void setBelastning(double vaerdi, Enhed enhed) throws UgyldigBelastningException;

	/**
	 * Denne metode er der, for at kunne komme med en advarsel, hvis der er tale om meget store vaerdier, da der i disse tilfaelde kan vaere tale om fejl.
	 * @return True(hvis belastningen er over 20.000 kg)
	 */
	public boolean erUnormaltStor();

}
