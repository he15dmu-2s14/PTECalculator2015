package logic;

import domain.Areal;
import domain.ArealEnhed;
import domain.Belastning;
import domain.Bojningsmoment;
import domain.Enhed;
import domain.Forskydningsspaending;
import domain.Normalkraft;
import domain.Normalspaending;
import domain.Tvaerkraft;
import domain.Vinkel;
import exceptions.UgyldigArealException;
import exceptions.UgyldigBelastningException;
import exceptions.UgyldigVinkelException;

/**
 * Klassen PTECalculator fungerer som Controller og sender informationer videre til de forskellige Information Expert klasser.
 * @author Louise
 *
 */
public interface PTECalculatorController {
	
    /**
     * Denne metode bruges til at angive belastningen i vha. to parametre.
     * @param vaerdi er det reele tal v�rdien er
     * @param enhed er om vaerdien er angivet i N, kg el. ton
     * @throws UgyldigBelastningException hvis vaerdien er negativ
     */
    public void angivBelastning(double vaerdi, Enhed enhed) throws UgyldigBelastningException;
	
    /**
     * Denne metode bruges til at beregne tvaerkraften (Ft) og angive vinklen
     * @param vinkel er vinklen angivet i grader
     * @param tilVandret om der er tale om en vinket maalt til vandret
     * @throws UgyldigVinkelException
     */
    public void beregnTvaerkraft(double vinkel, boolean tilVandret) throws UgyldigVinkelException;

    /**
     * Denne metode bruges til at beregne normalkraften (Fn) og angive vinklen
     * @param vinkel er vinklen angivet i grader
     * @param tilVandret om der er tale om en vinket maalt til vandret
     * @throws UgyldigVinkelException
     */
    public void beregnNormalkraft(double vinkel, boolean tilVandret) throws UgyldigVinkelException;
	
    /**
     * Denne metode bruges til at tilmelder PTEObserveren
     * @param observer
     *
     * @see logic.PTEObserver
     */
    public void tilmeldObserver(PTEObserver observer);
	
    /**
     * Denne metode bruges til at faa vist vinklen i grader. Metoden er kaldt fra ekspert-klassen Vinkel
     * @return Det aktuelle vinkelobjekt el. null hvis den ikke eksisterer
     */
    public Vinkel getVinkel();
	
    /**
     * Denne metode bruges til at faa vist belastningen i N. Metoden er kaldt fra ekspert-klassen Belastning
     * @return Det aktuelle belastningsobjekt el. null hvis den ikke eksisterer
     */
    public Belastning getBelastning();
	
    /**
     * Denne metode bruges til at faa tvaerkraften(Ft). Metoden er kaldt fra ekspert-klassen Tvaerkraft
     * @return Det aktuelle tvaerkraftsobjekt el. null hvis det ikke elsisterer
     * 		   Ellers returneres NaN
     */
    public Tvaerkraft getTvaerkraft();

    public Normalkraft getNormalkraft();
	
    public void notifyObservers();
    
    public void beregnForskydningsspaending(double areal, ArealEnhed enhed) throws UgyldigArealException;
    
    public Forskydningsspaending getForskydningsspaending();
    
    public Areal getAreal();
    
    public void beregnSigmaBoj(double i, double e, double MB);

    //Til Bojningsmoment
    public Bojningsmoment beregnMB(double fdim, double ft);
    
    //Til Normalspaending
    public Normalspaending beregnNormalspaending();
    
    //Til Interimoment
    public Inertimoment angivInertimoment(double I);
    
    public Inertimoment getInertimoment();
    
    //Til Halvhoejde
    public Halvhoejde angivHalvhoejde(double e);
    
    public Halvhoejde getHalvhoejde();
    
    //Til Referencespaending    

    public Referencespaending getReferencespaending();
}
