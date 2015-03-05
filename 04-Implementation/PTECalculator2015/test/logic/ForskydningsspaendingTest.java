package logic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ForskydningsspaendingTest {
  private Forskydningsspaending tau;
  private Areal areal;
  private Tvaerkraft ft;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
    tau=null; //skal være ForskydningsspaendingImpl
    areal=null; //skal være ArealImpl
    ft=new TvaerkraftStub(); //må ikke ændres til TvaerkraftImpl! -hbh
    
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testTauErOver100() {
    areal.setAreal(490, ArealEnhed.mm2);
    
    tau.setAreal(areal);
    tau.setTvaerkraft(ft);
    
    assertEquals(102.0408, tau.getForskydningsspaending(), 0.00499999);
    assertTrue(tau.erUnormaltStor());
  }
  
  @Test
  public void testTauErOK() {
    areal.setAreal(50000, ArealEnhed.mm2);
    
    tau.setAreal(areal);
    tau.setTvaerkraft(ft);
    
    assertEquals(1, tau.getForskydningsspaending(), 0.00499999);
    assertFalse(tau.erUnormaltStor());
  }
  
  @Test
  public void testArealNull()  {
    tau.setAreal(null);
    tau.setTvaerkraft(ft);
    
    assertEquals(Double.NaN, tau.getForskydningsspaending(), 0.00499999);
  }
  
  @Test
  public void testFtNull() {
    tau.setAreal(areal);
    tau.setTvaerkraft(null);
    
    assertEquals(Double.NaN, tau.getForskydningsspaending(), 0.00499999);    
  }
  
  private class TvaerkraftStub implements Tvaerkraft {

    @Override
    public void setVinkel( Vinkel vinkel ) {
    }

    @Override
    public void setBelastning( Belastning belastning ) {
    }

    @Override
    public double getTvaerkraft() {
      return 50000;
    }    
  }
}
