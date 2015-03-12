package logic;

import domain.*;
import exceptions.*;

import java.util.LinkedList;

public class PTECalculatorControllerImpl implements PTECalculatorController {
    private Belastning belastning;
    private Vinkel vinkel;
    private Tvaerkraft tvaerkraft;
    private Normalkraft normalkraft;
    private Areal areal;
    private Inertimoment inertimoment;
    private Halvhojde halvhojde;
    private Referencespaending referencespaending;
    private Forskydningsspaending forskydningsspaending;
    private Bojningsspaending bojningsspaending;
    private Bojningsmoment bojningsmoment;
    private Normalspaending normalspaending;
    private Sikkerhedsfaktor sikkerhedsfaktor;
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
	public void beregnBojningspaending(double i, double e)
            throws UgyldigInertiMomentException, UgyldigHalvhojdeException {
	  inertimoment = new InertimomentImpl();
	  inertimoment.setInertimomoent( i );
	  halvhojde = new HalvhojdeImpl();
	  halvhojde.setHalvhojde( e );
		bojningsspaending = new BojningsspaendingImpl();
		bojningsspaending.setInertimoment( inertimoment );
		bojningsspaending.setHalvhojde( halvhojde );
		bojningsspaending.setBojningsmoment( bojningsmoment );
		notifyObservers();
	}

    @Override
    public Bojningsspaending getBojningsspaending() {
        return bojningsspaending;
    }

    @Override
    public void beregnBojningsmoment(double l, Laengde enhed) throws UgyldigLaengdeException {
    	bojningsmoment = new BojningsmomentImpl();
    	bojningsmoment.setTvaerkraft( tvaerkraft );
    	bojningsmoment.setArmlangde( l, enhed );
    	notifyObservers();
    }

    @Override
    public Bojningsmoment getBojningsmoment() {
        return bojningsmoment;
    }

    @Override
    public void beregnNormalspaending(double areal, ArealEnhed enhed) throws UgyldigArealException {
	    this.areal = new ArealImpl();
	    this.areal.setAreal(areal, enhed);
		  normalspaending = new NormalspaendingImpl();
		  normalspaending.setAreal( this.areal );
		  normalspaending.setNormalkraft( normalkraft );
		  notifyObservers();
    }

    @Override
    public Normalspaending getNormalspaending() {
        return normalspaending;
    }

    @Override
    public void angivInertimoment(double i) throws UgyldigInertiMomentException {
        inertimoment = new InertimomentImpl();
        inertimoment.setInertimomoent(i);

        notifyObservers();
    }

    @Override
    public Inertimoment getInertimoment(){
    	return inertimoment;
    }

    @Override
    public void angivHalvhoejde(double e) throws UgyldigHalvhojdeException {
        halvhojde = new HalvhojdeImpl();
        halvhojde.setHalvhojde(e);

        notifyObservers();
    }

    @Override
    public Halvhojde getHalvhoejde(){
    	return halvhojde;
    }

    @Override
    public Referencespaending getReferencespaending(){
    	return referencespaending;
    }

   @Override
   public void beregnSikkerhedsfaktor(double tilladelig) {
      sikkerhedsfaktor = new SikkerhedsfaktorImpl();
      sikkerhedsfaktor.setTilladeligSpaending(tilladelig);
      sikkerhedsfaktor.setReferencespaending(referencespaending);

      notifyObservers();
   }

   @Override
   public Sikkerhedsfaktor getSikkerhedsfaktor() {
      return sikkerhedsfaktor;
   }
}
