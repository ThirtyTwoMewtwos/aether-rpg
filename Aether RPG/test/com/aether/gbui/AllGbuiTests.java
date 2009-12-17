package com.aether.gbui;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.aether.gbui.bss.TestBssColor;
import com.aether.gbui.bss.TestBssStyleClass;
import com.aether.gbui.bss.TestBssWriter;

@SuiteClasses ({
	TestBssColor.class,
	TestBssStyleClass.class,
	TestBssWriter.class,
	TestBGridContainer.class
})
@RunWith(Suite.class)
public class AllGbuiTests {

}
