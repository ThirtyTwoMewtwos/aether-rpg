package com.aether.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.aether.model.character.TestClassification;
import com.aether.model.items.TestCharacterBackpack;
import com.aether.model.items.TestGenericItem;
import com.aether.model.parser.TestConsoleCommandParser;
import com.aether.model.parser.TestItemCommandParser;
import com.aether.model.quests.TestJournal;

@SuiteClasses ({
	TestStatistic.class,
	TestClassification.class, 
	TestJournal.class,
	TestGenericItem.class,
	TestCharacterBackpack.class,
	TestConsoleCommandParser.class,
	TestItemCommandParser.class
})
@RunWith(Suite.class)
public class AllModelTests {

}
