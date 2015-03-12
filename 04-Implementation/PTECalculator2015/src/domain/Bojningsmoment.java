package domain;

/**
 * Klassen Bojningsmoment er information expert.
 * @author henrik
 */
public interface Bojningsmoment {
	
  /**
   * Denne metode saetter armlaengden.
   * @param armlaengde (cm, m)
   */
	public void setArmlangde(double armlaengde);
	
	/**
	 * Denne metode saetter tvaerkraften.
	 * @param tvaerkraft (resultat fra Tvaerkraft.java)
	 */
	public void setTvaerkraft(Tvaerkraft tvaerkraft);
	
	/**
	 * Denne metode oplyser boejningsmomentet.
	 * @return bojningsmoment
	 */
	public double getBojningsmoment();

}
