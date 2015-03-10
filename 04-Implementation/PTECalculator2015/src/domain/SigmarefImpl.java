package domain;

public class SigmarefImpl implements Sigmaref {
  private SigmaN sigman;
  private SigmaBoej sigmaboej;
  private Forskydningsspaending tau;

  @Override
  public double getSigmaRef() {
    if ( sigman == null || sigmaboej == null || tau == null )
      return Double.NaN;
    return Math.sqrt( Math.pow( (sigman.getSigman() + sigmaboej.getSigmab()), 2 ) + (3*Math.pow( tau.getForskydningsspaending(), 2 )) );
  }
  
  @Override
  public void setSigmaN(SigmaN sigman) {
    this.sigman = sigman;
  }
  
  @Override
  public void setSigmaBoej(SigmaBoej sigmab) {
    this.sigmaboej = sigmab;
  }
  
  @Override
  public void setTau(Forskydningsspaending tau) {
    this.tau = tau; 
  }

}