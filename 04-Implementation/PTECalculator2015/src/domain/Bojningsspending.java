package domain;

public interface Bojningsspending {

	public void beregnSigmaBoj(double i, double e, double MB);
	
	public double getSigmaBoj();
	
	public void setArmLaengde( double mm );
	
	public double beregnMb( double ft, double mm );
	
	public double getMb();
}
