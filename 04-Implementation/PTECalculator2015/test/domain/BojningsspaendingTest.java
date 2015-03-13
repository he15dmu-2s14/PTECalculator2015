package domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import exceptions.UgyldigBelastningException;
import exceptions.UgyldigHalvhojdeException;
import exceptions.UgyldigInertiMomentException;
import exceptions.UgyldigVinkelException;

public class BojningsspaendingTest {
	private Bojningsspaending bojningsspaending;
	private Bojningsmoment bojningsmoment;
	private Halvhojde halvhojde;
	private Inertimoment intertimoment;
	private Tvaerkraft tvaerkraft;
	private Belastning belastning;
	private Vinkel vinkel;
	
	@Before
	public void init(){
		bojningsspaending = new BojningsspaendingImpl();
		bojningsmoment = new BojningsmomentImpl();
		halvhojde = new HalvhojdeImpl();
		intertimoment = new InertimomentImpl();
		tvaerkraft = new TvaerkraftImpl();
		belastning = new BelastningImpl();
		vinkel = new VinkelImpl();
		intertimoment = new InertimomentImpl();
	}
	@Test
	public void test() throws UgyldigBelastningException, UgyldigVinkelException, UgyldigHalvhojdeException, UgyldigInertiMomentException {
		
		belastning.setBelastning(863.8, Enhed.Newton);
		vinkel.setGradtal(30, true);
		tvaerkraft.setBelastning(belastning);
		tvaerkraft.setVinkel(vinkel);
		
		bojningsmoment.setArmlangde(2500, Laengde.mm);
		bojningsmoment.setTvaerkraft(tvaerkraft);
		
		halvhojde.setHalvhojde(50.0);
		
		intertimoment.setInertimomoent(8333333.33);
		
		bojningsspaending.setBojningsmoment(bojningsmoment);
		bojningsspaending.setHalvhojde(halvhojde);
		bojningsspaending.setInertimoment(intertimoment);
		
		assertEquals(11.2212, bojningsspaending.getBojningsspending(),0.01);
	}

}
