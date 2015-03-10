package domain;

import exceptions.UgyldigInertiMomentException;

public interface Inertimoment {

	public double getInertimoment();
	
	public void setInertimomoent(double I) throws UgyldigInertiMomentException;
}
