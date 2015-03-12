package domain;

/**
 * Klassen Referencespaending er information expert.
 * @author henrik
 */
public interface Referencespaending {
  
  /**
   * Denne metode oplyser referencespaendingen.
   * @return referencespaendingen (positiv vaerdi)
   */
  public double getReferencespaending();
  
  /**
   * Denne metode saetter normalspaendingen.
   * @param sigman (resultat fra Normalspaending.java)
   */
  public void setNormalspaending(Normalspaending sigman);
  
  /**
   * Denne metode saetter bojningsspaendingen.
   * @param sigmab (resultat fra Bojningsspending.java)
   */
  public void setBojningsspending(Bojningsspending sigmab);
  
  /**
   * Denne metode saetter forskydningsspaendingen.
   * @param tau (resultat fra Forskydningsspaending.java)
   */
  public void setForskydningsspaending(Forskydningsspaending tau);
}
