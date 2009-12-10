package com.aether;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.aether.gbui.bss.AllGbuiTests;
import com.aether.model.AllModelTests;
import com.aether.present.AllPresentTests;
import com.aether.test.AllAetherTestTests;
import com.util.AllUtilTests;

/**
 * If you are running junit from eclipse or netbeans, you can run this suite to have
 * all tests unit tests run (this does not include the functional tests).
 * 
 * @author gpelcha
 */
@SuiteClasses({
	AllGbuiTests.class,
	AllModelTests.class,
	AllPresentTests.class,
	AllUtilTests.class,
	AllAetherTestTests.class
})
@RunWith(Suite.class)
public class AllTestSuites {

}
