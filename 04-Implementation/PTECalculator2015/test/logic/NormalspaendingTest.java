package logic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.Areal;
import domain.ArealImpl;
import domain.Belastning;
import domain.BelastningImpl;
import domain.Enhed;
import domain.Normalkraft;
import domain.NormalkraftImpl;
import domain.Normalspaending;
import domain.NormalspaendingImpl;
import domain.Vinkel;
import domain.VinkelImpl;
import domain.Enhed;
import domain.ArealEnhed;

public class NormalspaendingTest {
	
	Belastning belastning;
	Vinkel vinkel;
	Normalkraft normalkraft;
	Areal areal;
	Normalspaending normalspaending;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		belastning = new BelastningImpl();
		vinkel = new VinkelImpl();
		normalkraft = new NormalkraftImpl();
		areal = new ArealImpl();
		normalspaending = new NormalspaendingImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetNormalspaending() {
		
		try {
			belastning.setBelastning(20, Enhed.kg);
			vinkel.setGradtal(20, true);
			normalkraft.setBelastning(belastning);
			normalkraft.setVinkel(vinkel);
			areal.setAreal(20, ArealEnhed.cm2);
			normalspaending.setAreal(areal);
			normalspaending.setNormalkraft(normalkraft);
		} catch (Exception e) {
			fail("Failer i oppsett");
		}
		assertEquals(0.03, normalspaending.getNormalspaending(), 0.01);
	}
	
	@Test
	public void testGetNormalspaendingArealNaN() {
		try {
			belastning.setBelastning(20, Enhed.kg);
			vinkel.setGradtal(20, true);
			normalkraft.setBelastning(belastning);
			normalkraft.setVinkel(vinkel);
			//a.setAreal(20, ArealEnhed.cm2); Areal som null
			normalspaending.setAreal(areal);
			normalspaending.setNormalkraft(normalkraft);
		} catch (Exception e) {
			fail("Failer i oppsett");
		}
		
		assertTrue(Double.isNaN(normalspaending.getNormalspaending()));
		
	}
	
	@Test
	public void testGetNormalspaendingNormalkraftNaN() {
		try {
			belastning.setBelastning(20, Enhed.kg);
			vinkel.setGradtal(20, true);
			normalkraft.setBelastning(belastning);
			normalkraft.setVinkel(vinkel);
			areal.setAreal(20, ArealEnhed.cm2);
			normalspaending.setAreal(areal);
			//ns.setNormalkraft(n); Normalkraft som null
		} catch (Exception e) {
			fail("Failer i oppsett");
		}
		
		assertTrue(Double.isNaN(normalspaending.getNormalspaending()));
		
	}

}
