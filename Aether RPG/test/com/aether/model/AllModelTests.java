package com.aether.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.aether.model.character.TestCharacterTypeImage;
import com.aether.model.character.TestClassification;
import com.aether.model.quests.TestJournal;

@SuiteClasses ({
	TestStatistic.class,
	TestCharacterTypeImage.class,
	TestClassification.class, 
	TestJournal.class
})
@RunWith(Suite.class)
public class AllModelTests {

}