package domain;


public class NormalkraftImpl extends KraftImpl implements Normalkraft {

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
