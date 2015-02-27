package logic;

public class TvaerkraftImpl implements Tvaerkraft {
   private Vinkel vinkel;
   private Belastning belastning;
   
   @Override
   public void setVinkel(Vinkel vinkel) {
      this.vinkel = vinkel;
   }

   @Override
   public void setBelastning(Belastning belastning) {
      this.belastning = belastning;
   }

   @Override
   public double getTvaerkraft() {
      if (vinkel == null || belastning == null)
         return Double.NaN;
      
      // Konverter gradtal til radianer
      double rad = vinkel.getGradtal() * Math.PI / 180;
      
      if (vinkel.tilVandret())
         return Math.cos(rad) * belastning.getBelastning();
      else
         return Math.sin(rad) * belastning.getBelastning();
   }
}
