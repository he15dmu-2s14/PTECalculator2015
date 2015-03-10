package domain;

import exceptions.UgyldigInertiMomentException;

public class InertimomentImpl implements Inertimoment {

	
	private double inertimoment = 0;
	
	
	@Override
	public double getInertimoment() {
		return inertimoment;
	}

	@Override
	public void setInertimomoent(double i) throws UgyldigInertiMomentException {
		if (i < 0)
			throw new UgyldigInertiMomentException();
		else
			this.inertimoment = i;
	}

}
