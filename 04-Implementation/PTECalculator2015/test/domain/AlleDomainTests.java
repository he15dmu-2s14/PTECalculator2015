package domain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ArealTest.class, BelastningTest.class, BojningsspaendingTest.class, ForskydningsspaendingTest.class, HalvhojdeImplTest.class, InertimomentTest.class, NormalkraftTest.class, NormalspaendingTest.class,
		ReferencespaendningTest.class, TvaerkraftTest.class })
public class AlleDomainTests {

}
