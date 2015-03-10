package logic;

import java.util.LinkedList;

import domain.Areal;
import domain.ArealEnhed;
import domain.ArealImpl;
import domain.Belastning;
import domain.BelastningImpl;
import domain.Bojningsmoment;
import domain.BojningsmomentImpl;
import domain.Enhed;
import domain.Forskydningsspaending;
import domain.ForskydningsspaendingImpl;
import domain.Normalkraft;
import domain.NormalkraftImpl;
import domain.Tvaerkraft;
import domain.TvaerkraftImpl;
import domain.Vinkel;
import domain.VinkelImpl;
import exceptions.UgyldigArealException;
import exceptions.UgyldigBelastningException;
import exceptions.UgyldigVinkelException;

public class PTECalculatorControllerImpl implements PTECalculatorController {
    private Belastning belastning;
    private Vinkel vinkel;
    private Tvaerkraft tvaerkraft;
    private Normalkraft normalkraft;
    private Areal areal;
    private Forskydningsspaending forskydningsspaending;
    private LinkedList<PTEObserver> observerListe = new LinkedList<>();

    @Override
    public void angivBelastning(double vaerdi, Enhed enhed)
            throws UgyldigBelastningException {
        belastning = new BelastningImpl();
        belastning.setBelastning(vaerdi, enhed);

        notifyObservers();
    }

    @Override
    public void beregnTvaerkraft(double gradtal, boolean tilVandret)
            throws UgyldigVinkelException {
        vinkel = new VinkelImpl();
        vinkel.setGradtal(gradtal, tilVandret);

        tvaerkraft = new TvaerkraftImpl();
        tvaerkraft.setVinkel(vinkel);
        tvaerkraft.setBelastning(belastning);

        notifyObservers();
    }

    @Override
    public void beregnNormalkraft(double gradtal, boolean tilVandret)
            throws UgyldigVinkelException {
        vinkel = new VinkelImpl();
        vinkel.setGradtal(gradtal, tilVandret);

        normalkraft = new NormalkraftImpl();
        normalkraft.setVinkel(vinkel);
        normalkraft.setBelastning(belastning);

        notifyObservers();
    }


    @Override
    public void tilmeldObserver(PTEObserver observer) {
        if (observer != null && !observerListe.contains(observer))
            observerListe.add(observer);
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
    public Normalkraft getNormalkraft() {
        return normalkraft;
    }

    @Override
    public void notifyObservers() {
        for (PTEObserver obs : observerListe)
            obs.update();
    }

	@Override
	public void beregnForskydningsspaending(double areal, ArealEnhed enhed)
			throws UgyldigArealException {
			this.areal = new ArealImpl();
			this.areal.setAreal(areal, enhed);
			this.forskydningsspaending = new ForskydningsspaendingImpl();
			this.forskydningsspaending.setAreal(this.areal);
			this.forskydningsspaending.setTvaerkraft(this.tvaerkraft);
			notifyObservers();
	}

	@Override
	public Forskydningsspaending getForskydningsspaending() {
		return forskydningsspaending;
	}

	@Override
	public Areal getAreal() {
		return areal;
	}

	@Override
	public void beregnSigmaBoj(double i, double e, double MB) {
		Bojningsmoment bojMoment = new BojningsmomentImpl();
		bojMoment.beregnSigmaBoj(i, e, MB);
		notifyObservers();
	}
}
