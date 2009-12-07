package com.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@SuiteClasses ({
	TestFileUtil.class,
	TestStringUtil.class
})
@RunWith(Suite.class)
public class AllUtilTests {

}
