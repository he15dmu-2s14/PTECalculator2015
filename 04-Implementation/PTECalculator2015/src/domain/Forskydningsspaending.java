
package domain;

/**
 * Klassen Forskydningsspaending er information expert.
 * @author henrik
 */
public interface Forskydningsspaending {
  
  /**
   * Denne metode oplyser forskydningsspaendingen.
   * @return forskydningsspaending
   */
  public double getForskydningsspaending();
  
  /**
   * Denne metode saetter arealet.
   * @param areal (resultat fra Areal.java)
   */
  public void setAreal(Areal areal);
  
  /**
   * Denne metode saetter tvaerkraften.
   * @param tvaerkraft (resultat fra Tvaerkraft.java)
   */
  public void setTvaerkraft(Tvaerkraft ft);
  
  /**
   * Denne metode advarer om unormal resultat af forskydningsspaendingen.
   * @return true (hvis forskydningsspaendingen er > 100)
   */
  public boolean erUnormaltStor();  
}