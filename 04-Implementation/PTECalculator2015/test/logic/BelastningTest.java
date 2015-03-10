package logic;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.Belastning;
import domain.BelastningImpl;
import domain.Enhed;
import exceptions.UgyldigBelastningException;


public class BelastningTest {
	private Belastning belastning;
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
	   belastning=new BelastningImpl();
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testTC1NegativVærdiMedEnhedKG() {
	  try {
		  belastning.setBelastning( -5, Enhed.kg );
		  fail("En exception skulle være kastet");
	  }
	  catch (UgyldigBelastningException ube) {
		  // Test pass
	  }

  }
  
  @Test
  public void testTC2PositivVærdiMedEnhedKG() throws UgyldigBelastningException {
	  belastning.setBelastning( 50, Enhed.kg );
	  
	  assertEquals( 50, belastning.getBelastningIKg(), 0.01 );
	  assertEquals( 0.05, belastning.getBelastningITon(), 0.01 );
	  assertEquals( 490.8, belastning.getBelastning(), 0.01 );
	  assertFalse( belastning.erUnormaltStor() );
  }
  
  @Test
  public void testTC3ForStorVærdiMedEnhedKG() throws UgyldigBelastningException {
	  belastning.setBelastning( 22304, Enhed.kg );
	  
	  assertEquals( 22304, belastning.getBelastningIKg(), 0.01 );
	  assertEquals( 22.304, belastning.getBelastningITon(), 0.01 );
	  assertEquals( 218936.064, belastning.getBelastning(), 0.01 );
	  assertTrue( belastning.erUnormaltStor() );
  }
  
  @Test
  public void testTC4NegativVærdiMedEnhedTon() throws UgyldigBelastningException {
	  try {
	  	belastning.setBelastning( -5, Enhed.ton );
	  	fail("Exception forventet");
	  }
	  catch (UgyldigBelastningException ube) {
		  // Test passed
	  }
  }
  
  @Test
  public void testTC5PositivVærdiMedEnhedTon() throws UgyldigBelastningException {
	  belastning.setBelastning( 9, Enhed.ton );
	  
	  assertEquals( 9000, belastning.getBelastningIKg(), 0.01 );
	  assertEquals( 9, belastning.getBelastningITon(), 0.01 );
	  assertEquals( 88344, belastning.getBelastning(), 0.01 );
	  assertFalse( belastning.erUnormaltStor() );
  }
  
  @Test
  public void testTC6ForStorKGVærdiMedEnhedTon() throws UgyldigBelastningException {
	  belastning.setBelastning( 34, Enhed.ton );
	  
	  assertEquals( 34000, belastning.getBelastningIKg(), 0.01 );
	  assertEquals( 34, belastning.getBelastningITon(), 0.01 );
	  assertEquals( 333744, belastning.getBelastning(), 0.01 );
	  assertTrue( belastning.erUnormaltStor() );
  }
  
  @Test
  public void testTC7NegativVærdiMedEnhedNewton() {
	  try {
		  belastning.setBelastning( -5, Enhed.Newton );
		  fail("Exception forventet");
	  }
	  catch (UgyldigBelastningException ube) {
		  //Test passed
	  }
  }
  
  @Test
  public void testTC8PositivVærdiMedEnhedNewton() throws UgyldigBelastningException {
	  belastning.setBelastning( 150498.94 , Enhed.Newton );
	  
	  assertEquals( 15332, belastning.getBelastningIKg(), 0.01 );
	  assertEquals( 15.33, belastning.getBelastningITon(), 0.01 );
	  assertEquals( 150498.94, belastning.getBelastning(), 0.01 );
	  assertFalse( belastning.erUnormaltStor() );
  }
  
  @Test
  public void testTC9ForHøjKGVærdiMedEnhedNewton () throws UgyldigBelastningException {
	  belastning.setBelastning( 270500, Enhed.Newton );
	  
	  assertEquals( 27557.05, belastning.getBelastningIKg(), 0.01 );
	  assertEquals( 27.56, belastning.getBelastningITon(), 0.01 );
	  assertEquals( 270500, belastning.getBelastning(), 0.01 );
	  assertTrue( belastning.erUnormaltStor() );
  }
  
  @Test
  public void testTC10VærdiEr0MedEnhedKG () throws UgyldigBelastningException {
	  belastning.setBelastning( 0 , Enhed.kg );
	  
	  assertEquals( 0, belastning.getBelastningIKg(), 0.01 );
	  assertEquals( 0, belastning.getBelastningITon(), 0.01 );
	  assertEquals( 0, belastning.getBelastning(), 0.01 );
	  assertFalse( belastning.erUnormaltStor() );
  }
  
  @Test
  public void testTC11VærdiEr0MedEnhedTon () throws UgyldigBelastningException {
	  belastning.setBelastning( 0 , Enhed.ton );
	  
	  assertEquals( 0, belastning.getBelastningIKg(), 0.01 );
	  assertEquals( 0, belastning.getBelastningITon(), 0.01 );
	  assertEquals( 0, belastning.getBelastning(), 0.01 );
	  assertFalse( belastning.erUnormaltStor() );
  }
  
  @Test
  public void testTC12VærdiEr0MedEnhedNewton () throws UgyldigBelastningException {
	  belastning.setBelastning( 0 , Enhed.Newton );
	  
	  assertEquals( 0, belastning.getBelastningIKg(), 0.01 );
	  assertEquals( 0, belastning.getBelastningITon(), 0.01 );
	  assertEquals( 0, belastning.getBelastning(), 0.01 );
	  assertFalse( belastning.erUnormaltStor() );
  }
  
}
