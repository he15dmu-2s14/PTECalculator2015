package logic;

import java.util.LinkedList;

import domain.Areal;
import domain.ArealEnhed;
import domain.ArealImpl;
import domain.Belastning;
import domain.BelastningImpl;
import domain.Bojningsmoment;
import domain.Bojningsspending;
import domain.BojningsspendingImpl;
import domain.Enhed;
import domain.Forskydningsspaending;
import domain.ForskydningsspaendingImpl;
import domain.Halvhojde;
import domain.Inertimoment;
import domain.Normalkraft;
import domain.NormalkraftImpl;
import domain.Normalspaending;
import domain.Referencespaending;
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
    private Inertimoment inertimoment;
    private Halvhojde halvhojde;
    private Referencespaending referencespaending;
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
	public void beregnBojningspaending(double i, double e, double MB) {
		Bojningsspending bojMoment = new BojningsspendingImpl();
		bojMoment.beregnBojningspaending(i, e, MB);
		notifyObservers();
	}
	
	//Til Bojningsmoment
	//!!OBS!! Mangler Impl
	@Override
    public Bojningsmoment beregnBojninsmoment(double fdim, double ft){
    	return null;
    }
    
    //Til Normalspaending
	//!!OBS!! Mangler Impl
	@Override
    public Normalspaending beregnNormalspaending(){
		
    	return null;
    }
    
    //Til Interimoment
	//!!OBS!! Mangler Impl
    @Override
    public void angivInertimoment(double I){
    }
    
  //!!OBS!! Mangler Impl
    @Override
    public Inertimoment getInertimoment(){
    	return inertimoment;
    }
    
    //Til Halvhoejde
  //!!OBS!! Mangler Impl
    @Override
    public void angivHalvhoejde(double e) {
    	throws UgyldigHalvhojdeException() {
    		
    	}
    }
    
  //!!OBS!! Mangler Impl
    @Override
    public Halvhojde getHalvhoejde(){
    	return halvhojde;
    }
    
    //Til Referencespaending
  //!!OBS!! Mangler Impl
    @Override
    public void angivReferencespaending() {
    	
    }

  //!!OBS!! Mangler Impl
    @Override
    public Referencespaending getReferencespaending(){
    	return referencespaending;
    }
}
