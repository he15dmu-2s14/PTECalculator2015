package logic;

public class ForskydningsspaendingImpl implements Forskydningsspaending {
	private Areal areal;
	private Tvaerkraft tvaerkraft;

	@Override
	public double getForskydningsspaending() {
		if ( areal == null || tvaerkraft == null )
			return Double.NaN;
		return tvaerkraft.getTvaerkraft() / areal.getAreal();
	}

	@Override
	public void setAreal(Areal areal) {
		this.areal = areal;
		
	}

	@Override
	public void setTvaerkraft(Tvaerkraft ft) {
		this.tvaerkraft = ft;
		
	}

	@Override
	public boolean erUnormaltStor() {
		return getForskydningsspaending() > 100;
	}

}
