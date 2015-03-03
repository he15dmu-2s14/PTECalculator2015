package logic;

public class VinkelImpl implements Vinkel {
   private double gradtal;
   private boolean tilVandret;

   @Override
   public double getGradtal() {
      return gradtal;
   }

   @Override
   public void setGradtal(double vinkel, boolean tilVandret) throws UgyldigVinkelException {
	  if (vinkel < 0 || vinkel > 90)
		  throw new UgyldigVinkelException();
      this.gradtal = vinkel;
      this.tilVandret = tilVandret;
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
