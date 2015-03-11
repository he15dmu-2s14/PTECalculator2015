package domain;

public class NormalspaendingImpl {
	
	public void setNormalkraft( Normalkraft normalkraft ) {
	}
	
	public void setAreal( Areal areal ) {
	}
	
	public double getNormalspaending( Normalkraft normalkraft, Areal areal ) {
		if ( normalkraft != null && areal != null )
			return normalkraft.getNormalkraft() / areal.getAreal();
		else
			return Double.NaN;
	}
}