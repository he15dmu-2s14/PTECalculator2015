package domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import domain.Areal;
import domain.ArealEnhed;
import domain.ArealImpl;
import domain.Belastning;
import domain.BelastningImpl;
import domain.Bojningsmoment;
import domain.BojningsmomentImpl;
import domain.Bojningsspaending;
import domain.BojningsspaendingImpl;
import domain.Enhed;
import domain.Forskydningsspaending;
import domain.ForskydningsspaendingImpl;
import domain.Halvhojde;
import domain.HalvhojdeImpl;
import domain.Inertimoment;
import domain.InertimomentImpl;
import domain.Normalkraft;
import domain.NormalkraftImpl;
import domain.Normalspaending;
import domain.NormalspaendingImpl;
import domain.Referencespaending;
import domain.ReferencespaendingImplc;
import domain.Tvaerkraft;
import domain.TvaerkraftImpl;
import domain.Vinkel;
import domain.VinkelImpl;
import exceptions.UgyldigArealException;
import exceptions.UgyldigBelastningException;
import exceptions.UgyldigHalvhojdeException;
import exceptions.UgyldigInertiMomentException;
import exceptions.UgyldigVinkelException;

public class ReferencespaendningTest {
	
	private Referencespaending referencespending;
	private Normalspaending sigman;
	private Bojningsspaending sigmaboej;
	private Forskydningsspaending tau;
	private Areal areal;
	private Tvaerkraft ft;
	private Belastning belastning;
	private Vinkel vinkel;
	private Normalkraft normalkraft;
	private Bojningsmoment bojningsmoment;
	private Tvaerkraft tvaerkraft;
	private Halvhojde halvhojde;
	private Inertimoment intertimoment;

	@Before
	public void init(){
		referencespending = new ReferencespaendingImplc();
		sigman = new NormalspaendingImpl();
		sigmaboej = new BojningsspaendingImpl();
		areal = new ArealImpl();
		ft = new TvaerkraftImpl();
		belastning = new BelastningImpl();
		vinkel = new VinkelImpl();
		normalkraft = new NormalkraftImpl();
		tau = new ForskydningsspaendingImpl();
		bojningsmoment = new BojningsmomentImpl();
		tvaerkraft = new TvaerkraftImpl();
		halvhojde = new HalvhojdeImpl();
		intertimoment = new InertimomentImpl();
	}
	
	@Test //000
	public void SigmaboejOgTauOgSigmanErNull() {
		
		referencespending.setBojningsspending(null);
		referencespending.setForskydningsspaending(null);
		referencespending.setNormalspaending(null);		
		
		assertTrue(Double.isNaN(referencespending.getReferencespaending()));	
	}
	
	@Test //001
	public void SigmaBoejOgTauErNullOgSigmanErSat() throws UgyldigArealException, UgyldigBelastningException, UgyldigVinkelException {
		
		areal.setAreal(2000, ArealEnhed.mm2);
		belastning.setBelastning(500, Enhed.Newton);
		vinkel.setGradtal(22, true);
		
		normalkraft.setBelastning(belastning);
		normalkraft.setVinkel(vinkel);
		sigman.setAreal(areal);
		sigman.setNormalkraft(normalkraft);
		
		referencespending.setBojningsspending(null);
		referencespending.setForskydningsspaending(null);
		referencespending.setNormalspaending(sigman);
		
		assertTrue(Double.isNaN(referencespending.getReferencespaending()));
	}
	
	@Test //010
	public void SigmaboejErNullTauErSatSigmanErNull() throws UgyldigArealException, UgyldigBelastningException, UgyldigVinkelException {
		
		areal.setAreal(200, ArealEnhed.cm2);
		belastning.setBelastning(500, Enhed.Newton);
		vinkel.setGradtal(40, true);
		ft.setBelastning(belastning);
		ft.setVinkel(vinkel);
		
		tau.setAreal(areal);
		tau.setTvaerkraft(ft);
		
		referencespending.setBojningsspending(null);
		referencespending.setForskydningsspaending(tau);;
		referencespending.setNormalspaending(null);
		
		assertTrue(Double.isNaN(referencespending.getReferencespaending()));		
	}	
	
	@Test //011
	public void SigmaboejErNullTauErSatOgSigmanErSat() throws UgyldigArealException, UgyldigBelastningException, UgyldigVinkelException{
		areal.setAreal(1, ArealEnhed.m2);
		belastning.setBelastning(900, Enhed.Newton);
		vinkel.setGradtal(22, true);
		ft.setBelastning(belastning);
		ft.setVinkel(vinkel);
		tau.setAreal(areal);
		tau.setTvaerkraft(ft);
		
		sigman.setAreal(areal);
		normalkraft.setBelastning(belastning);
		normalkraft.setVinkel(vinkel);
		sigman.setNormalkraft(normalkraft);
		
		referencespending.setBojningsspending(null);
		referencespending.setForskydningsspaending(tau);
		referencespending.setNormalspaending(sigman);		
	
		
		assertTrue(Double.isNaN(referencespending.getReferencespaending()));
	}
	
	@Test //100
	public void SigmaboejErSatTauErNullSigmanErNull() throws UgyldigHalvhojdeException, UgyldigBelastningException, UgyldigVinkelException, UgyldigInertiMomentException {
		
		belastning.setBelastning(200, Enhed.Newton);
		vinkel.setGradtal(43, false);
		
		tvaerkraft.setBelastning(belastning);
		tvaerkraft.setVinkel(vinkel);
		
		bojningsmoment.setArmlangde(2000.5,Laengde.mm);
		bojningsmoment.setTvaerkraft(tvaerkraft);
		
		halvhojde.setHalvhojde(200.5);
		
		intertimoment.setInertimomoent(40.9);
		
		sigmaboej.setBojningsmoment(bojningsmoment);
		sigmaboej.setHalvhojde(halvhojde);
		sigmaboej.setInertimoment(intertimoment);
		
		referencespending.setBojningsspending(sigmaboej);
		referencespending.setForskydningsspaending(null);
		referencespending.setNormalspaending(null);
		
		assertTrue(Double.isNaN(referencespending.getReferencespaending()));
	}
	
	@Test //101
	public void SigmaboejErSatTauErNullsigmanErSat() throws UgyldigBelastningException, UgyldigVinkelException, UgyldigHalvhojdeException, UgyldigInertiMomentException, UgyldigArealException {
	
		belastning.setBelastning(200, Enhed.Newton);
		vinkel.setGradtal(43, false);
		
		tvaerkraft.setBelastning(belastning);
		tvaerkraft.setVinkel(vinkel);
		
		bojningsmoment.setArmlangde(2000.5, Laengde.mm);
		bojningsmoment.setTvaerkraft(tvaerkraft);
		
		halvhojde.setHalvhojde(200.5);
		
		intertimoment.setInertimomoent(40.9);
		
		sigmaboej.setBojningsmoment(bojningsmoment);
		sigmaboej.setHalvhojde(halvhojde);
		sigmaboej.setInertimoment(intertimoment);
		
		areal.setAreal(15, ArealEnhed.cm2);
		sigman.setAreal(areal);
		normalkraft.setBelastning(belastning);
		normalkraft.setVinkel(vinkel);
		sigman.setNormalkraft(normalkraft);
		
		referencespending.setBojningsspending(sigmaboej);
		referencespending.setForskydningsspaending(null);
		referencespending.setNormalspaending(sigman);
		
		assertTrue(Double.isNaN(referencespending.getReferencespaending()));
	}
	
	@Test //110
	public void SigmabeojErSatTauErSatSigmanErNull() throws UgyldigBelastningException, UgyldigVinkelException, UgyldigHalvhojdeException, UgyldigInertiMomentException, UgyldigArealException {
		
		belastning.setBelastning(200, Enhed.Newton);
		vinkel.setGradtal(43, false);
		
		tvaerkraft.setBelastning(belastning);
		tvaerkraft.setVinkel(vinkel);
		
		bojningsmoment.setArmlangde(2000.5,Laengde.mm);
		bojningsmoment.setTvaerkraft(tvaerkraft);
		
		halvhojde.setHalvhojde(200.5);
		
		intertimoment.setInertimomoent(40.9);
		
		sigmaboej.setBojningsmoment(bojningsmoment);
		sigmaboej.setHalvhojde(halvhojde);
		sigmaboej.setInertimoment(intertimoment);
		
		areal.setAreal(200, ArealEnhed.cm2);
		ft.setBelastning(belastning);
		ft.setVinkel(vinkel);
		
		tau.setAreal(areal);
		tau.setTvaerkraft(ft);
		
		referencespending.setBojningsspending(sigmaboej);
		referencespending.setForskydningsspaending(tau);
		referencespending.setNormalspaending(null);
		
		assertTrue(Double.isNaN(referencespending.getReferencespaending()));
	}
	
	@Test //111
	public void SigmaboejErSatTauErSatSigmanErSat() throws UgyldigBelastningException, UgyldigVinkelException, UgyldigHalvhojdeException, UgyldigInertiMomentException, UgyldigArealException {
		
		belastning.setBelastning(863.8, Enhed.Newton);
		vinkel.setGradtal(30, true);		
		ft.setVinkel(vinkel);
		ft.setBelastning(belastning);
		bojningsmoment.setArmlangde(2500.0, Laengde.mm);
		bojningsmoment.setTvaerkraft(ft);
		halvhojde.setHalvhojde(50);
		intertimoment.setInertimomoent(8333333.33);
		sigmaboej.setBojningsmoment(bojningsmoment);
		sigmaboej.setHalvhojde(halvhojde);
		sigmaboej.setInertimoment(intertimoment);
		areal.setAreal(10000, ArealEnhed.mm2);
		tau.setAreal(areal);
		tau.setTvaerkraft(ft);
		sigman.setAreal(areal);
		normalkraft.setBelastning(belastning);
		normalkraft.setVinkel(vinkel);
		sigman.setNormalkraft(normalkraft);
		
		referencespending.setBojningsspending(sigmaboej);
		referencespending.setForskydningsspaending(tau);
		referencespending.setNormalspaending(sigman);
		assertEquals(11.2651, referencespending.getReferencespaending(),0.0001);
	}
}