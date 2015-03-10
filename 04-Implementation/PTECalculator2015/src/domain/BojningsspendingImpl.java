package domain;

public class BojningsspendingImpl implements Bojningsspending {
	
	private double sigmaBoj;
	
	@Override
	public void beregnSigmaBoj(Inertimoment i, Halvhojde e, double MB) {
		sigmaBoj = (MB *e.getHalvhojde()) / i.getInertimoment();
	}
	
	public double getSigmaBoj() {
		return sigmaBoj;
	}

}
