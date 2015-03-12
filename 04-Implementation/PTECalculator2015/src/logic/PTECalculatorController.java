package logic;

import domain.Areal;
import domain.ArealEnhed;
import domain.Belastning;
import domain.Bojningsmoment;
import domain.Bojningsspaending;
import domain.Enhed;
import domain.Forskydningsspaending;
import domain.Halvhojde;
import domain.Inertimoment;
import domain.Normalkraft;
import domain.Normalspaending;
import domain.Referencespaending;
import domain.Tvaerkraft;
import domain.Vinkel;
import exceptions.*;

/**
 * Klassen PTECalculator fungerer som Controller og sender informationer videre til de forskellige Information Expert klasser.
 * @author Louise
 *
 */
public interface PTECalculatorController {
    /**
     * Denne metode instantierer et Belastning objekt og setter dens datakerne med den angivne parameter.
     * @param vaerdi er det reele tal vaerdien er
     * @param enhed er om vaerdien er angivet i N, kg el. ton
     * @throws UgyldigBelastningException hvis vaerdien er negativ
     */
    public void angivBelastning(double vaerdi, Enhed enhed) throws UgyldigBelastningException;

    /**
     * Denne metode instantierer et Vinkel objekt og setter dens datakerne med den foerste parameter.
     * Herefter instantieres et Tvaerkraft objekt og dens datakerne bliver sat til
     * @param vinkel er vinklen angivet i grader
     * @param tilVandret om der er tale om en vinket maalt til vandret
     * @throws UgyldigVinkelException hvis vaerdien er negativ eller >90
     */
    public void beregnTvaerkraft(double vinkel, boolean tilVandret) throws UgyldigVinkelException;

    /**
     * Denne metode bruges til at beregne normalkraften (Fn) og angive vinklen
     * @param vinkel er vinklen angivet i grader
     * @param tilVandret om der er tale om en vinket maalt til vandret
     * @throws UgyldigVinkelException hvis vaerdien er negativ eller >90
     */
    public void beregnNormalkraft(double vinkel, boolean tilVandret) throws UgyldigVinkelException;

    /**
     * Denne metode bruges til at tilmelde et observer objekt til observerListe
     * @param angiver det PTEObserver der skal tilmeldes
     */
    public void tilmeldObserver(PTEObserver observer);

    /**
     * Denne metode bruges til at faa vinklen i grader. Metoden er kaldt fra ekspert-klassen Vinkel
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

    /**
     * @param areal
     * @param enhed
     * @throws UgyldigArealException
     */
    public void beregnForskydningsspaending(double areal, ArealEnhed enhed) throws UgyldigArealException;

    public Forskydningsspaending getForskydningsspaending();

    public Areal getAreal();

    /**
     * Beregn boejningsspaending
     * @param i inertimoment
     * @param e tyngdepunktsafstand (halvhoejde)
     */
    public void beregnBojningspaending(double i, double e)
            throws UgyldigInertiMomentException, UgyldigHalvhojdeException;

    /**
     * Denne metode bruges til at faa Bojningspaending
     * @return
     */
    public Bojningsspaending getBojningsspaending();

    /**
     * Beregn boejningsmoment
     * @param l armslaengde
     */
    public void beregnBojningsmoment(double l, Laengde enhed) throws UgyldigLaengdeException ;

    /**
     * Denne metode bruges til at faa Bojningsmoment
     * @return Det aktuelle bojningsmomentobjekt
     */
    public Bojningsmoment getBojningsmoment();

    public void beregnNormalspaending(double areal, ArealEnhed enhed) throws UgyldigArealException;

    /**
     * Denne metode bruges til at faa normalspaendingen.
     * @return Det aktuelle normalspaendingobjekt ellers returneres null
     */
    public Normalspaending getNormalspaending();

    public void angivInertimoment(double i) throws UgyldigInertiMomentException;

    public Inertimoment getInertimoment();

    public void angivHalvhoejde(double e) throws UgyldigHalvhojdeException;

    /**
     * Denne metode bruges til at faa Halvhojde
     * @return Det aktuelle halvhojdeobjekt ellers ugyldigHalvhojdeException
     */
    public Halvhojde getHalvhoejde();

    public Referencespaending getReferencespaending();
}
