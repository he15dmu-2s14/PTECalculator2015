package logic;

public class PTECalculatorControllerImpl implements PTECalculatorController {
   private Belastning belastning;
   private Vinkel vinkel;
   private Tvaerkraft tvaerkraft;
   
	@Override
	public void angivBelastning(double vaerdi, Enhed enhed)
			throws UgyldigBelastningException {
		belastning = new BelastningImpl();
		belastning.setBelastning(vaerdi, enhed);
		
		notifyObservers();
	}

	@Override
	public void beregnTvaerkraft(double gradtal, boolean tilVandret) {
	   vinkel = new VinkelImpl();
	   vinkel.setGradtal(gradtal, tilVandret);
	   
	   tvaerkraft = new TvaerkraftImpl();
	   tvaerkraft.setVinkel(vinkel);
	   tvaerkraft.setBelastning(belastning);
	   
	   notifyObservers();
	}

	@Override
	public void tilmeldObserver(PTEObserver observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vinkel getVinkel() {
	   return vinkel;
	}

	@Override
	public Belastning getBelastning() {
	   return belastning;
	}

	@Override
	public Tvaerkraft getTvaerkraft() {
	   return tvaerkraft;
	}

	@Override
	public void notifyObservers() {
		
	}
}
