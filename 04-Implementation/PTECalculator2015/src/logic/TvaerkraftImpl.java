package logic;

public class TvaerkraftImpl extends KraftImpl implements Tvaerkraft {

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
