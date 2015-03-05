package logic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ArealTest {
  private Areal areal;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
    areal = null; //Skal være ArealImpl.
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void test() {
    fail( "Not yet implemented" );
  }
  
  @Test
  public void testAfGyldigtInputIkvadratMM() {
    areal.setAreal( 490, ArealEnhed.mm2 );
    
    assertEquals( 490, areal.getAreal(), 0.01 );
  }
  
  @Test
  public void testAfGyldigtInputIkvadratCM() {
    areal.setAreal( 500, ArealEnhed.cm2 );
    
    assertEquals( 50000, areal.getAreal(), 0.01);
  }
  
  @Test
  public void testAfGyldigtInputIkvadratM() {
    areal.setAreal( 1, ArealEnhed.m2 );
    
    assertEquals( 1000000, areal.getAreal(), 0.01);
  }
  
  @Test
  public void testAfUgyldigtInputNul() {
    try {
      areal.setAreal( 0, ArealEnhed.mm2 );
      fail("Exception forventet!");
    }
    catch (UgyldigArealException e) {
      //test passed if exception is thrown
    }
  }
  
  @Test
  public void testAfUgyldigtInputNegativt() {
    try {
      areal.setAreal( -1, ArealEnhed.mm2 );
      fail("Exception forventet!");
    }
    catch (UgyldigArealException e) {
      //test passed if exception is thrown
    }
  }

}
