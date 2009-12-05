package com.aether.present;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.aether.present.hud.TestPersonaWindow;
import com.aether.present.hud.TestJournalWindow;

@SuiteClasses( 
		{
			TestCharacterCreationWindow.class,
			TestInGameWindow.class,
			TestMainMenuWindow.class,
			TestPersonaWindow.class,
			TestJournalWindow.class
		})
@RunWith(Suite.class)
public class AllFunctionalTests {
}