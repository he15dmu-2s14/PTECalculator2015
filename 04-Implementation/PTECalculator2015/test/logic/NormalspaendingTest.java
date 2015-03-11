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
	
	Belastning b;
	Vinkel v;
	Normalkraft n;
	Areal a;
	Normalspaending ns;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		b = new BelastningImpl();
		v = new VinkelImpl();
		n = new NormalkraftImpl();
		a = new ArealImpl();
		ns = new NormalspaendingImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetNormalspaending() {
		
		try {
			b.setBelastning(20, Enhed.kg);
			v.setGradtal(20, true);
			n.setBelastning(b);
			n.setVinkel(v);
			a.setAreal(20, ArealEnhed.cm2);
			ns.setAreal(a);
			ns.setNormalkraft(n);
		} catch (Exception e) {
			fail("Failer i oppsett");
		}
		assertEquals(0.03, ns.getNormalspaending(), 0.01);
	}
	
	@Test
	public void testGetNormalspaendingNaN() {
		try {
			b.setBelastning(20, Enhed.kg);
			v.setGradtal(20, true);
			n.setBelastning(b);
			n.setVinkel(v);
			//a.setAreal(20, ArealEnhed.cm2); Areal som null
			ns.setAreal(a);
			ns.setNormalkraft(n);
		} catch (Exception e) {
			fail("Failer i oppsett");
		}
		
		assertTrue(Double.isNaN(ns.getNormalspaending()));
		
	}

}
