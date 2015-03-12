package domain;

public interface Bojningsmoment {
	
	public void setArmlangde(double armlaengde, Laengde enhed);
	
	public double getArmlaengdeImm();
		
	public double getArmlaengdeIcm();
	
	public double getArmlaengdeIm();
	
	public void setTvaerkraft(Tvaerkraft tvaerkraft);
	
	public double getBojningsmoment();

}
