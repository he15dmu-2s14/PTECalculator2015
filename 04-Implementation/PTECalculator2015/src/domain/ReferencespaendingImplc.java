package domain;

public class ReferencespaendingImplc implements Referencespaending {
  private SigmaN sigman;
  private Bojningsspending sigmaboej;
  private Forskydningsspaending tau;

  @Override
  public double getSigmaRef() {
    if ( sigman == null || sigmaboej == null || tau == null )
      return Double.NaN;
    return Math.sqrt( Math.pow( (sigman.getSigman() + sigmaboej.getSigmaBoj()), 2 ) + (3*Math.pow( tau.getForskydningsspaending(), 2 )) );
  }
  
  @Override
  public void setSigmaN(SigmaN sigman) {
    this.sigman = sigman;
  }
  
  @Override
  public void setSigmaBoej(Bojningsspending sigmab) {
    this.sigmaboej = sigmab;
  }
  
  @Override
  public void setTau(Forskydningsspaending tau) {
    this.tau = tau; 
  }

}