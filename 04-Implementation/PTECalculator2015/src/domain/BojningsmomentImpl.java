package domain;

public class BojningsmomentImpl implements Bojningsmoment {
	
	private double fDim;
	private double ft;
	private double arm;
	private double bøjningsMoment;
	
	public BojningsmomentImpl( double fdim, double ft ) {
		this.fDim = fdim;
		this.ft = ft;
	}
	
	@Override
	public void setArmLaengde(double mm) {
		arm = mm;
	}

	@Override
	public double beregnMB(double fdim, double ft) {
		bøjningsMoment = this.fDim * this.ft;
		return bøjningsMoment;
	}

	@Override
	public double getMB() {
		return bøjningsMoment;
	}


}
