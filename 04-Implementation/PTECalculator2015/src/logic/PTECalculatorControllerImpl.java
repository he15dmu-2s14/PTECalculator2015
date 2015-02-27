package logic;

public class PTECalculatorControllerImpl implements PTECalculatorController {

	@Override
	public void angivBelastning(double vaerdi, Enhed enhed)
			throws UgyldigBelastningException {
		Belastning b = new BelastningImpl();
		b.setBelastning(vaerdi, enhed);
		//notifyObservers();
	}

	@Override
	public void beregnTvaerkraft(double vinkel, boolean tilVandret) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tilmeldObserver(PTEObserver observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vinkel getVinkel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Belastning getBelastning() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tvaerkraft getTvaerkraft() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void notifyObservers() {
		
	}

}
