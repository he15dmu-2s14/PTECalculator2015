package domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.UgyldigHalvhojdeException;
import exceptions.UgyldigVinkelException;

public class HalvhojdeImplTest {
  private Halvhojde halvhojde;
  
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
    halvhojde = new HalvhojdeImpl();
    
    
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testGetHalvhojde() {

    
  }

  @Test
  public void setHalvhøjdeNegativ() throws UgyldigHalvhojdeException{
    try{
      halvhojde.setHalvhojde( -3 );
      fail("Exception forventet"); 
        } 
    catch (UgyldigHalvhojdeException e) {
        //Test passed
      }
  }
}
