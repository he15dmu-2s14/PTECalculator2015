package domain;

import exceptions.UgyldigHalvhojdeException;

/**
 * Klassen Halvhojde er information expert.
 * @author henrik
 */
public interface Halvhojde {

  /**
   * Denne metode oplyser halvhoejden.
   * @return halvhojde (positiv vaerdi)
   */
	public double getHalvhojde();
	
	/**
	 * Denne metode saetter halvhoejden.
	 * @param e (positiv værdi)
	 * @throws UgyldigHalvhojdeException kastes ved indtastning af negativ vaerdi
	 */
	public void setHalvhojde(double e) throws UgyldigHalvhojdeException;
}
