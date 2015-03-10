package domain;

public class BojningsmomentImpl implements Bojningsmoment {

	double sigmaBoj = 0;
	
	@Override
	public void beregnSigmaBoj(double i, double e, double MB) {
		sigmaBoj = (MB * e) / i;
	}
	
	public double getSigmaBoj() {
		return sigmaBoj;
	}
}
