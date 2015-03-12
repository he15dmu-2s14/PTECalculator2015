package domain;

public class BojningsmomentImpl implements Bojningsmoment {
	
	private double armlaengde;
	private Tvaerkraft tvaerkraft;

	@Override
	public void setArmlangde(double armlaengde, Laengde enhed) {
		switch (enhed) {
			case cm:
				this.armlaengde = armlaengde * 10;
				break;
			
			case m:
				this.armlaengde = armlaengde * 1000;
				break;

			default:
				this.armlaengde = armlaengde;
				break;
			
		}
		
	}

	@Override
	public void setTvaerkraft(Tvaerkraft tvaerkraft) {
		this.tvaerkraft = tvaerkraft;
		
	}

	@Override
	public double getBojningsmoment() {
		return this.armlaengde * this.tvaerkraft.getTvaerkraft();
	}

	@Override
	public double getArmlaengdeImm() {
		return this.armlaengde;
	}

	@Override
	public double getArmlaengdeIcm() {
		return this.armlaengde / 100.0;
	}

	@Override
	public double getArmlaengdeIm() {
		return this.armlaengde / 1000.0;
	}

}
