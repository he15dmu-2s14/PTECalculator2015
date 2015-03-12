package domain;

/**
 * Klassen Bojningsspending er information expert.
 * @author henrik
 */
public interface Bojningsspending {

  /**
   * Denne metode saetter inertimomentet.
   * @param intertimoment (positiv vaerdi)
   */
	public void setInertimoment(Inertimoment intertimoment);
	
	/**
	 * Denne metode saetter halvhoejden.
	 * @param halvhojde (resultat fra Halvhojde.java)
	 */
	public void setHalvhojde(Halvhojde halvhojde);
	
	/**
	 * Denne metode saetter boejningsmomentet.
	 * @param bojningsmoment (resultat fra Bojningsmoment.java)
	 */
	public void setBojningsmoment(Bojningsmoment bojningsmoment);
	
	/**
	 * Denne metode oplyser boejningsspaendingen.
	 * @return bojningsspending
	 */
	public double getBojningsspending();
	
}
