package domain;


public class KraftImpl implements Kraft {
	protected Vinkel vinkel;
	protected Belastning belastning;

	@Override
	public void setVinkel(Vinkel vinkel) {
		this.vinkel = vinkel;
	}

	@Override
	public void setBelastning(Belastning belastning) {
		this.belastning = belastning;
	}

}
