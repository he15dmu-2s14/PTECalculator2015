package domain;

public class BojningsmomentImpl implements Bojningsmoment {

	double sigmaBoj = 0;
	
	public double getSigmaBoj() {
		return sigmaBoj;
	}

	@Override
	public void beregnSigmaBoj(Inertimoment i, Halvhojde e, double MB) {

		sigmaBoj = (MB *e.getHalvhojde()) / i.getInertimoment();
	}
}
