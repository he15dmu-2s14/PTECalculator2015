package domain;

public class BojningsmomentImpl implements Bojningsmoment {

	private double fDim;
	private double ft;
	private double arm;
	private double bøjningsMoment;
	
	private double sigmaBoj;
	
	@Override
	public void beregnSigmaBoj(double i, double e, double MB) {
		sigmaBoj = (MB * e) / i;
	}
	
	public double getSigmaBoj() {
		return sigmaBoj;
	}

	//Metoder til MB
	@Override
	public void setArmLaengde(double mm) {
		arm = mm;
		
	}

	@Override
	public double beregnMb(double ft, double mm) {
		bøjningsMoment = this.ft * arm;
		return bøjningsMoment;
	}

	@Override
	public double getMb() {
		return bøjningsMoment;
	}
}
