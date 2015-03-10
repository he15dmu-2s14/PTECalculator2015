package domain;

import exceptions.UgyldigHalvhojdeException;

public class HalvhojdeImpl implements Halvhojde {

	private double halvhojde = 0;
	
	@Override
	public double getHalvhojde() {
		return halvhojde;
	}

	@Override
	public void setHalvhojde(double e) throws UgyldigHalvhojdeException {

		if (e < 0)
			throw new UgyldigHalvhojdeException();
		else
			this.halvhojde = e;
	}

}
