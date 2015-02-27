package logic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TvaerkraftTest {
	private Tvaerkraft tvaerkraft;
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
		tvaerkraft = new TvaerkraftImpl();
		vinkel = new VinkelImpl();
		belastning = new BelastningImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void vinkelErMindreEnd0() throws UgyldigBelastningException {
		vinkel.setGradtal(-50, true);
		belastning.setBelastning(490.8, Enhed.Newton);
		tvaerkraft.setBelastning(belastning);
		tvaerkraft.setVinkel(vinkel);
		
		assertEquals(315.48, tvaerkraft.getTvaerkraft(), 0.01);
	}
	
	@Test
	public void NaNTestForVinkel() throws UgyldigBelastningException {
		belastning.setBelastning(490.8, Enhed.Newton );
		
		assertEquals(Double.NaN, tvaerkraft.getTvaerkraft(), 0.01);
	}
	
	@Test
	public void vinkelErSt�rrerEnd90() throws UgyldigBelastningException {
		vinkel.setGradtal(100, true);
		belastning.setBelastning(490.8, Enhed.Newton);
		tvaerkraft.setBelastning(belastning);
		tvaerkraft.setVinkel(vinkel);
	
		assertEquals(-85.22, tvaerkraft.getTvaerkraft(), 0.01);
	}
	
	@Test
	public void tilVandretSatTilFalse() throws UgyldigBelastningException {
		vinkel.setGradtal(50, false);
		belastning.setBelastning(490.8, Enhed.Newton);
		tvaerkraft.setBelastning(belastning);
		tvaerkraft.setVinkel(vinkel);
		
		assertEquals(375.97, tvaerkraft.getTvaerkraft(), 0.01);
	}
	
	@Test
	public void tilVandretSatTiltrue() throws UgyldigBelastningException {
		vinkel.setGradtal(50, true);
		belastning.setBelastning(490.8, Enhed.Newton);
		tvaerkraft.setBelastning(belastning);
		tvaerkraft.setVinkel(vinkel);
		
		assertEquals(315.48, tvaerkraft.getTvaerkraft(), 0.01);
	}
	
	@Test
	public void tvaerkraftBeregnetMedBelastningIKg() throws UgyldigBelastningException {
		vinkel.setGradtal(50, true);
		belastning.setBelastning(490.8, Enhed.kg);
		tvaerkraft.setBelastning(belastning);
		tvaerkraft.setVinkel(vinkel);
		
		assertEquals(3096.75, tvaerkraft.getTvaerkraft(), 0.01);
	}
	
	@Test
	public void tvaerkraftBeregnetMedBelastningITon() throws UgyldigBelastningException {
		vinkel.setGradtal(50, true);
		belastning.setBelastning(0.02, Enhed.ton);
		tvaerkraft.setBelastning(belastning);
		tvaerkraft.setVinkel(vinkel);
		
		assertEquals(126.19, tvaerkraft.getTvaerkraft(), 0.01);
	}	
}