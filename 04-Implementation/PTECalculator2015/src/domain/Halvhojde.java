package domain;

import exceptions.UgyldigHalvhojdeException;

public interface Halvhojde {

	public double getHalvhojde();
	
	public void setHalvhojde(double e) throws UgyldigHalvhojdeException;
}
