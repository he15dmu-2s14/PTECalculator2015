package domain;

public class ReferencespaendingImplc implements Referencespaending {
  private Normalspaending sigman;
  private Bojningsspending sigmaboej;
  private Forskydningsspaending tau;

  @Override
  public double getReferencespaending() {
    if ( sigman == null || sigmaboej == null || tau == null )
      return Double.NaN;
    return Math.sqrt( Math.pow( (sigman.getNormalspaending() + sigmaboej.getBojningsspending()), 2 ) + (3*Math.pow( tau.getForskydningsspaending(), 2 )) );
  }
  
  @Override
  public void setNormalspaending(Normalspaending sigman) {
    this.sigman = sigman;
  }
  
  @Override
  public void setBojningsspending(Bojningsspending sigmab) {
    this.sigmaboej = sigmab;
  }
  
  @Override
  public void setForskydningsspaending(Forskydningsspaending tau) {
    this.tau = tau; 
  }

}