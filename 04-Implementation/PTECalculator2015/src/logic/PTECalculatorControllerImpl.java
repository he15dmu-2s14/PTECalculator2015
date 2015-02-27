package logic;

import java.util.LinkedList;

public class PTECalculatorControllerImpl implements PTECalculatorController {

	private Belastning b;
	private LinkedList<PTEObserver> observerListe = new LinkedList();;
	
	@Override
	public void angivBelastning(double vaerdi, Enhed enhed)
			throws UgyldigBelastningException {
		b = new BelastningImpl();
		b.setBelastning(vaerdi, enhed);
		notifyObservers();
	}

	@Override
	public void beregnTvaerkraft(double vinkel, boolean tilVandret) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tilmeldObserver(PTEObserver observer) {
		if (observer != null && !observerListe.contains(observer))
			observerListe.add(observer);
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
		for (PTEObserver obs : observerListe)
			obs.update();
	}

}
