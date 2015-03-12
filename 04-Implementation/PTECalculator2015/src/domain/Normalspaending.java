package domain;

/**
 * Klassen Normalspaending er information expert.
 * @author henrik
 */
public interface Normalspaending {
	
  /**
   * Denne mestode saetter normalkraften.
   * @param normalkraft (resultat fra Normalkraft.java)
   */
	public void setNormalkraft( Normalkraft normalkraft );
	
	/**
	 * Denne metode saetter arealet.
	 * @param areal (resultat fra Areal.java)
	 */
	public void setAreal( Areal areal );
	
	/**
	 * Denne metode oplyser normalspaendingen.
	 * @return normalspaending
	 */
	public double getNormalspaænding();
	
}
