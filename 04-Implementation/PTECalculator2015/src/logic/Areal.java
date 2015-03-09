
package logic;

public interface Areal {
  
  public double getArealIcm2();
  
  public double getArealIm2();
  
  public double getAreal();
  
  public void setAreal(double areal, ArealEnhed enhed) throws UgyldigArealException;
}