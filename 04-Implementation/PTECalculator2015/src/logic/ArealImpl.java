package logic;

public class ArealImpl implements Areal {
	private double areal;
	
	public ArealImpl() {
		this.areal = Double.NaN;
	}

	@Override
	public double getArealIcm2() {
		return areal / 100;
	}

	@Override
	public double getArealIm2() {
		return areal / 1000000;
	}

	@Override
	public double getAreal() {
		return areal;
	}

	@Override
	public void setAreal(double areal, ArealEnhed enhed) throws UgyldigArealException {
		this.areal = areal;
	}

}
