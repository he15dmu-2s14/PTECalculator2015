package logic;

import domain.Areal;
import domain.ArealEnhed;
import domain.Belastning;
import domain.Enhed;
import domain.Forskydningsspaending;
import domain.Normalkraft;
import domain.Tvaerkraft;
import domain.Vinkel;
import exceptions.UgyldigArealException;
import exceptions.UgyldigBelastningException;
import exceptions.UgyldigVinkelException;

public class PTECalculatorControllerStub implements PTECalculatorController {

	private Belastning belastning = new Belastning(){

		@Override
		public double getBelastning() {
			// TODO Auto-generated method stub
			return 9816;
		}

		@Override
		public double getBelastningIKg() {
			// TODO Auto-generated method stub
			return 1000;
		}

		@Override
		public double getBelastningITon() {
			// TODO Auto-generated method stub
			return 1;
		}

		@Override
		public void setBelastning(double vaerdi, Enhed enhed)
				throws UgyldigBelastningException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean erUnormaltStor() {
			// TODO Auto-generated method stub
			return false;
		}
		
	};
	private Vinkel vinkel = new Vinkel(){

		@Override
		public double getGradtal() {
			// TODO Auto-generated method stub
			return 45;
		}

		@Override
		public boolean tilVandret() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setGradtal(double vinkel, boolean tilVandret) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean erUdenForNormalomraade() {
			// TODO Auto-generated method stub
			return false;
		}
		
	}; 
	
	Tvaerkraft tvaerkraft = new Tvaerkraft(){

		@Override
		public void setVinkel(Vinkel vinkel) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setBelastning(Belastning belastning) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public double getTvaerkraft() {
			// TODO Auto-generated method stub
			return 65;
		}
		
	};

	@Override
	public void angivBelastning(double vaerdi, Enhed enhed)
			throws UgyldigBelastningException {
		// TODO Auto-generated method stub

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
 
		
		return vinkel; 
	}

	@Override
	public Belastning getBelastning() {
		// TODO Auto-generated method stub
		
		return belastning;
	}

	@Override
	public Tvaerkraft getTvaerkraft() {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beregnNormalkraft(double vinkel, boolean tilVandret)
			throws UgyldigVinkelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Normalkraft getNormalkraft() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void beregnForskydningsspaending(double areal, ArealEnhed enhed) throws UgyldigArealException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Forskydningsspaending getForskydningsspaending() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Areal getAreal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void beregnSigmaBoj(double i, double e, double MB) {
		// TODO Auto-generated method stub
		
	}

}
