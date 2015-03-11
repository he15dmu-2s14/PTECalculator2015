package domain;

public class NormalspaendingImpl implements Normalspaending{
	
	private Normalkraft normalkraft;
	private Areal areal;
	
	@Override
	public void setNormalkraft( Normalkraft normalkraft ) {
		this.normalkraft = normalkraft;
	}
	
	@Override
	public void setAreal( Areal areal ) {
		this.areal = areal;
	}
	
	@Override
	public double getNormalspaending() {
		if ( normalkraft != null && areal != null )
			return normalkraft.getNormalkraft() / areal.getAreal();
		else
			return Double.NaN;
	}
}