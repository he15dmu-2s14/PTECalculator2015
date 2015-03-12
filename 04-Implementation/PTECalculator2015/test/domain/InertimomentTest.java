package domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import domain.Inertimoment;
import domain.InertimomentImpl;
import exceptions.UgyldigInertiMomentException;

public class InertimomentTest {

	private Inertimoment inertimoment;

	@Before
	public void init(){
		inertimoment = new InertimomentImpl();
	}
	
	@Test
	public void testAfUgyldigInputNegativ() {
		try {
			inertimoment.setInertimomoent(- 0.1);
		      fail("Exception forventet!");
		}
		catch (UgyldigInertiMomentException e){
			//det var godt
		}
	}
	
	@Test
	public void testAfGyldigInputNul() throws UgyldigInertiMomentException{
		inertimoment.setInertimomoent(0.0);
		assertEquals(0, inertimoment.getInertimoment(),0.00);
	}
	
	@Test
	public void testAfGyldigInputHundredKommaSyv() throws UgyldigInertiMomentException{
		inertimoment.setInertimomoent(100.7);
		assertEquals(100.7, inertimoment.getInertimoment(),0.01);
	}

}