
package domain;

public interface Forskydningsspaending {
  
  public double getForskydningsspaending();
  
  public void setAreal(Areal areal);
  
  public void setTvaerkraft(Tvaerkraft ft);
  
  public boolean erUnormaltStor();  
}