package domain;

import exceptions.UgyldigInertiMomentException;

/**
 * Klassen Inertimoment er information expert.
 * @author henrik
 */
public interface Inertimoment {

  /**
   * Denne metode oplyser inertimomentet.
   * @return inertimoment (positiv vaerdi)
   */
	public double getInertimoment();
	
	/**
	 * Denne metode saetter inertimomentet.
	 * @param I (positiv vaerdi)
	 * @throws UgyldigInertiMomentException kastes ved indtastning af negativ vaerdi
	 */
	public void setInertimomoent(double I) throws UgyldigInertiMomentException;
}
