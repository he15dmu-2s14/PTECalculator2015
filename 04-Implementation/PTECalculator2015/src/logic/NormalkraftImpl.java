package logic;

public class NormalkraftImpl implements Normalkraft {
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
   public double getNormalkraft() {
      if (vinkel == null || belastning == null)
         return Double.NaN;
      
      // Konverter gradtal til radianer
      double rad = vinkel.getGradtal() * Math.PI / 180;
      
      if (vinkel.tilVandret())
         return Math.sin(rad) * belastning.getBelastning();
      else
         return Math.cos(rad) * belastning.getBelastning();
   }
}
