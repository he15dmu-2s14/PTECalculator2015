
package domain;

import exceptions.UgyldigArealException;

/**
 * Klassen Areal er information expert.
 * @author henrik
 */
public interface Areal {
  
  /**
   * Denne metode oplyser arealet omregnet til mm2.
   * @return areal i cm2 (positiv vaerdi) / 100
   */
  public double getArealIcm2();
  
  /**
   * Denne metode oplyser arealet omregnet til mm2.
   * @return areal i m2 (positiv vaerdi) / 1000000
   */
  public double getArealIm2();
  
  /**
   * Denne metode oplyser arealet i mm2.
   * @return areal i mm2 (positiv vaerdi)
   */
  public double getAreal();
  
  /**
   * Denne metode saetter arealet.
   * @param areal (positiv vaerdi)
   * @param enhed (mm2, cm2, m2)
   * @throws UgyldigArealException kastes ved indtastning af negativ vaerdi
   */
  public void setAreal(double areal, ArealEnhed enhed) throws UgyldigArealException;
}