package domain;

public class NormalspaendingImpl {
	
	private Normalkraft normalkraft;
	private Areal areal;
	
	public void setNormalkraft( Normalkraft normalkraft ) {
		this.normalkraft = normalkraft;
	}
	
	public void setAreal( Areal areal ) {
		this.areal = areal;
	}
	
	public double getNormalspaending( Normalkraft normalkraft, Areal areal ) {
		if ( normalkraft != null && areal != null )
			return normalkraft.getNormalkraft() / areal.getAreal();
		else
			return Double.NaN;
	}
}