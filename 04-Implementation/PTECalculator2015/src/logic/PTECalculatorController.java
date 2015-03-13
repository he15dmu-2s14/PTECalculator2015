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
import domain.Laengde;
import domain.Normalkraft;
import domain.Normalspaending;
import domain.Referencespaending;
import domain.Sikkerhedsfaktor;
import domain.Tvaerkraft;
import domain.Vinkel;
import exceptions.UgyldigArealException;
import exceptions.UgyldigBelastningException;
import exceptions.UgyldigHalvhojdeException;
import exceptions.UgyldigInertiMomentException;
import exceptions.UgyldigLaengdeException;
import exceptions.UgyldigVinkelException;

/**
 * Klassen PTECalculator fungerer som Controller og sender informationer videre til de forskellige Information Expert klasser.
 * @author Louise
 *
 */
public interface PTECalculatorController {
    /**
     * Denne metode instantierer et Belastning objekt og set'er dens datakerne med den angivne parameter.
     * @param "vaerdi" er det reelle tal vaerdien er.
     * @param "enhed" er et enum der angiver om vaerdien er i N, kg eller ton.
     * @throws UgyldigBelastningException kastes hvis vaerdien er negativ.
     */
    public void angivBelastning(double vaerdi, Enhed enhed) throws UgyldigBelastningException;

    /**
     * Denne metode instantierer et Vinkel objekt og set'er datakernen med de angivne parametre.
     * Herefter instantieres et Tvaerkraft objekt og dens datakerne bliver sat til det Vinkel objekt,
     * der netop er blevet oprettet samt det Belastning objekt, der ligger i PTECalculatorController's datakerne.
     * @param "vinkel" er vinklen angivet i grader.
     * @param "tilVandret" angiver om vinklen er maalt fra vandret.
     * @throws UgyldigVinkelException kastes hvis vaerdien er negativ eller over 90.
     */
    public void beregnTvaerkraft(double vinkel, boolean tilVandret) throws UgyldigVinkelException;

    /**
     * Denne metode instantierer et Vinkel objekt og set'er datakernen med de angivne parametre.
     * Herefter instantieres et Normalkraft objekt og dens datakerne bliver sat til det Vinkel objekt,
     * der netop er blevet oprettet samt det Belastning objekt, der ligger i PTECalculatorController's datakerne.
     * @param "vinkel" er vinklen angivet i grader.
     * @param "tilVandret" angiver om vinklen er maalt fra vandret.
     * @throws UgyldigVinkelException kastes hvis vaerdien er negativ eller over 90.
     */
    public void beregnNormalkraft(double vinkel, boolean tilVandret) throws UgyldigVinkelException;

    /**
     * Denne metode bruges til at tilmelde et observer objekt til observerListe,
     * hvis den angivne reference ikke er null og objektet ikke allerede eksisterer i listen.
     * @param "observer" angiver det PTEObserver objekt der skal tilmeldes.
     */
    public void tilmeldObserver(PTEObserver observer);

    /**
     * Denne metode returnerer en reference til et Vinkel objekt, hvis dette eksisterer i datakernen.
     * @return Det aktuelle Vinkel objekt eller null, hvis den ikke eksisterer
     */
    public Vinkel getVinkel();

    /**
     * Denne metode returnerer en reference til et Belastning objekt, hvis dette eksisterer i datakernen.
     * @return Det aktuelle Belastning objekt eller null, hvis det ikke eksisterer
     */
    public Belastning getBelastning();

    /**
     * Denne metode returnerer en reference til et Tvaerkraft objekt, hvis dette eksisterer i datakernen.
     * @return Det aktuelle Tvaerkraft objekt eller null, hvis det ikke eksisterer
     */
    public Tvaerkraft getTvaerkraft();

    /**
     * Denne metode returnerer en reference til et Normalkraft objekt, hvis dette eksisterer i datakernen.
     * @return Det aktuelle Normalkraft objekt eller null, hvis det ikke eksisterer
     */
    public Normalkraft getNormalkraft();

    /**
     * Denne metode koerer et for-each loop på observerListe og
     * kalder update() metoden på alle tilmeldte observere.
     */
    public void notifyObservers();

    /**
     * Denne metode instantierer et Areal objekt og set'er datakernen med de angivne parametre.
     * Herefter instantieres et Forskydningsspaending objekt og dens datakerne bliver sat til det Areal objekt,
     * der netop er blevet oprettet samt det Tvaerkraft objekt, der allerede ligger i PTECalculatorController's datakerne.
     * @param "areal" er det reelle tal vaerdien er.
     * @param "areal" er et enum der angiver om vaerdien er i mm2, cm2 eller m2.
     * @throws UgyldigArealException kastes hvis vaerdien er nul eller negativ.
     */
    public void beregnForskydningsspaending(double areal, ArealEnhed enhed) throws UgyldigArealException;

    /**
     * Denne metode returnerer en reference til et Forskydningsspaending objekt, hvis dette eksisterer i datakernen.
     * @return Det aktuelle Forskydningsspaending objekt eller null, hvis det ikke eksisterer
     */
    public Forskydningsspaending getForskydningsspaending();

    /**
     * Denne metode returnerer en reference til et Areal objekt, hvis dette eksisterer i datakernen.
     * @return Det aktuelle Areal objekt eller null, hvis det ikke eksisterer
     */
    public Areal getAreal();

    /**
     * Denne metode instantierer et Inertimoment objekt og set'er datakernen med den angivne parameter (i).
     * Herefter instantieres et Halvhojde objekt og dens datakerne bliver sat til den anden angivne parameter (e).
     * Til sidst instantieres et Bojningsspaending objekt og dens datakerne bliver sat til det Inertimoment- og Halvhojde objekt,
     * der netop er blevet oprettet samt det Bojningsmoment objekt, der allerede ligger i PTECalculatorController's datakerne.
     * @param "i" er inertimomenten, angivet i mm^4.
     * @param "e" er tyngdepunktsafstanden/halvhoejden, angivet i mm.
     * @throws UgyldigInertiMomentException
     * @throws UgyldigHalvhojdeException
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
    
    public void beregnSikkerhedsfaktor(double tilladelig);
    
    public Sikkerhedsfaktor getSikkerhedsfaktor();
}
