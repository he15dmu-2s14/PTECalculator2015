package logic;

import java.util.LinkedList;

public class PTECalculatorControllerImpl implements PTECalculatorController {
   private Belastning belastning;
   private Vinkel vinkel;
   private Tvaerkraft tvaerkraft;
   private Normalkraft normalkraft;
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
   public void notifyObservers() {
      for (PTEObserver obs : observerListe)
         obs.update();
   }
}
