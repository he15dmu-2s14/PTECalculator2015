package logic;

public interface PTECalculatorController {
	
	/**
	 * @param vaerdi
	 * @param enhed
	 * @throws UgyldigBelastningException hvis vaerdi er negativ.
	 */
	public void angivBelastning(double vaerdi, Enhed enhed) throws UgyldigBelastningException;
	
	public void beregnTvaerkraft(double vinkel, boolean tilVandret);
	
	public void tilmeldObserver(PTEObserver observer);
	
	public Vinkel getVinkel();
	
	public Belastning getBelastning();
	
	public Tvaerkraft getTvaerkraft();

}
