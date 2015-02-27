package logic;

public interface Belastning {
	
	public double getBelastning();
	
	public double getBelastningIKg();
	
	public double getBelastningITon();
	
	/**
	 * @param vaerdi
	 * @param enhed
	 * @throws UgyldigBelastningException hvis vaerdi er negativ.
	 */
	public void setBelastning(double vaerdi, Enhed enhed) throws UgyldigBelastningException;
	
	public boolean erUnormaltStor();

}
