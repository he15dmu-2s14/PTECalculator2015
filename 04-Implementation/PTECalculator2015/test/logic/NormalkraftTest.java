package logic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class NormalkraftTest {
	private Normalkraft normalkraft;
	private Vinkel vinkel;
	private Belastning belastning;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		normalkraft = new NormalkraftImpl();
		vinkel = new VinkelImpl();
		belastning = new BelastningImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void vinkelErMindreEnd0() throws UgyldigVinkelException {
		try {
			vinkel.setGradtal(-50, true);
			fail("Exception forventet");
		} catch (UgyldigVinkelException e) {
			//Test passed
		}
	}
	
	@Test
	public void NaNTestForVinkel() throws UgyldigBelastningException {
		belastning.setBelastning(490.8, Enhed.Newton );
		
		assertEquals(Double.NaN, normalkraft.getNormalkraft(), 0.01);
	}
	
	@Test
	public void vinkelErStoerrerEnd90() throws UgyldigVinkelException {
		try {
			vinkel.setGradtal(100, true);
			fail("Exception forventet");
		} catch (UgyldigVinkelException e) {
			//Test passed
		}
	}
	
	@Test
	public void tilVandretSatTilFalse() throws UgyldigVinkelException, UgyldigBelastningException {
		vinkel.setGradtal(50, false);
		belastning.setBelastning(490.8, Enhed.Newton);
		normalkraft.setBelastning(belastning);
		normalkraft.setVinkel(vinkel);
		
		assertEquals(315.48, normalkraft.getNormalkraft(), 0.01);
	}
	
	@Test
	public void tilVandretSatTiltrue() throws UgyldigVinkelException, UgyldigBelastningException {
		vinkel.setGradtal(50, true);
		belastning.setBelastning(490.8, Enhed.Newton);
		normalkraft.setBelastning(belastning);
		normalkraft.setVinkel(vinkel);
		
		assertEquals(375.97, normalkraft.getNormalkraft(), 0.01);
	}

	@Test
	public void tvaerkraftBeregnetMedBelastningIKg() throws UgyldigVinkelException, UgyldigBelastningException {
		vinkel.setGradtal(50, true);
		belastning.setBelastning(490.8, Enhed.kg);
		normalkraft.setBelastning(belastning);
		normalkraft.setVinkel(vinkel);
		
		assertEquals(3690.57, normalkraft.getNormalkraft(), 0.01);
	}
	
	@Test
	public void normalkraftBeregnetMedBelastningITon() throws UgyldigVinkelException, UgyldigBelastningException {
		vinkel.setGradtal(50, true);
		belastning.setBelastning(0.02, Enhed.ton);
		normalkraft.setBelastning(belastning);
		normalkraft.setVinkel(vinkel);
		
		assertEquals(150.39, normalkraft.getNormalkraft(), 0.01);
	}	
}
