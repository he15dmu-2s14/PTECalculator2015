package domain;

public class BojningsmomentImpl implements Bojningsmoment {
	
	private double armlaengde;
	private Tvaerkraft tvaerkraft;

	@Override
	public void setArmlangde(double armlaengde) {
		this.armlaengde = armlaengde;
		
	}

	@Override
	public void setTvaerkraft(Tvaerkraft tvaerkraft) {
		this.tvaerkraft = tvaerkraft;
		
	}

	@Override
	public double getBojningsmoment() {
		return this.armlaengde * this.tvaerkraft.getTvaerkraft();
	}

}
