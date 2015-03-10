package domain;

public interface Bojningsspending {

	public void beregnSigmaBoj(Inertimoment i, Halvhojde e, double MB);
	
	public double getSigmaBoj();
	
	public void setArmLaengde( double mm );
	
	public double beregnMb( double ft, double mm );
	
	public double getMb();
}
