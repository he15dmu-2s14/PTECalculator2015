package logic;

public class BelastningImpl implements Belastning {

	private double kg;
	private double ton;
	private double newton;
	private static final double tyngdeAcceleration = 9.816;
	
	public BelastningImpl() {
		this.kg = 0;
		this.ton = 0;
		this.newton = 0;
	}
	
	@Override
	public double getBelastning() {
		return newton;
	}

	@Override
	public double getBelastningIKg() {
		return kg;
	}

	@Override
	public double getBelastningITon() {
		return ton;
	}

	@Override
	public void setBelastning(double vaerdi, Enhed enhed)
			throws UgyldigBelastningException {
		
		if (vaerdi < 0)
			throw new UgyldigBelastningException();
		
		switch(enhed) {
			case kg:
				this.kg = vaerdi;
				this.ton = vaerdi / 1000;
				this.newton = vaerdi * tyngdeAcceleration;
				break;
			case ton:
				this.kg = vaerdi * 1000;
				this.ton = vaerdi;
				this.newton = this.kg * tyngdeAcceleration;
				break;
			case Newton:
				this.kg = vaerdi / tyngdeAcceleration;
				this.ton = this.kg / 1000;
				this.newton = vaerdi;
				break;
		}
	}

	@Override
	public boolean erUnormaltStor() {
		return this.kg > 20000;
	}

}
