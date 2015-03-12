package domain;

public class SikkerhedsfaktorImpl implements Sikkerhedsfaktor {
   private Double tilladeligSpaending;
   private Referencespaending referencespaending;

   public void setTilladeligSpaending(Double ts) {
      tilladeligSpaending = ts;
   }

   public void setReferencespaending(Referencespaending rs) {
      referencespaending = rs;
   }

   public double getSikkerhedsfaktor() {
      if (tilladeligSpaending == null || referencespaending == null)
         return Double.NaN;

      return tilladeligSpaending / referencespaending.getReferencespaending();
   }
}
