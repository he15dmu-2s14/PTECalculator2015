package domain;

public class BojningsmomentImpl implements Bojningsmoment {
	
	private double ft;
	private double arm;
	private double bøjningsMoment;
	
	public BojningsmomentImpl( double ft, double arm ) {
		beregnMB( ft, arm );
	}
	
	//Denne metode er også set-metode for datakernen
	@Override
	public double beregnMB(double ft, double arm ) {
		this.ft = ft;
		this.arm = arm;
		bøjningsMoment = this.ft * this.arm;
		return bøjningsMoment;
	}

	@Override
	public double getMB() {
		return bøjningsMoment;
	}


}
