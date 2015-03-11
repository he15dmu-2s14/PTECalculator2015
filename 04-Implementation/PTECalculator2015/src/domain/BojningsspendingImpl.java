package domain;

public class BojningsspendingImpl implements Bojningsspending {

	private Bojningsmoment bojningsmoment;
	private Halvhojde halvhojde;
	private Inertimoment inertimoment;
	
	@Override
	public double getBojningsspending() {
		return (this.bojningsmoment.getBojningsmoment() * this.halvhojde.getHalvhojde()) / this.inertimoment.getInertimoment();
	}

	@Override
	public void setInertimoment(Inertimoment inertimoment) {
		this.inertimoment = inertimoment;
	}

	@Override
	public void setHalvhojde(Halvhojde halvhojde) {
		this.halvhojde = halvhojde;
	}

	@Override
	public void setBojningsmoment(Bojningsmoment bojningsmoment) {
		this.bojningsmoment = bojningsmoment;
	}
	

}
