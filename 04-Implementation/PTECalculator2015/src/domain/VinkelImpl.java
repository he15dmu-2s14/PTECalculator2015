package domain;

import exceptions.UgyldigVinkelException;

public class VinkelImpl implements Vinkel {
   private double gradtal;
   private boolean tilVandret;

   @Override
   public double getGradtal() {
      return gradtal;
   }

   @Override
   public void setGradtal(double vinkel, boolean tilVandret) throws UgyldigVinkelException {
      this.gradtal = vinkel;
      this.tilVandret = tilVandret;
      if (erUdenForNormalomraade())
    		  throw new UgyldigVinkelException();
   }

   @Override
   public boolean tilVandret() {
      return tilVandret;
   }

   @Override
   public boolean erUdenForNormalomraade() {
      return (gradtal < 0 || gradtal > 90);
   }
}
