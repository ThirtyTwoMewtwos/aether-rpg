package com.aether.present;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.aether.present.hud.TestConsoleWindow;
import com.aether.present.hud.TestEquipmentWindow;
import com.aether.present.hud.TestPersonaWindow;
import com.aether.present.hud.TestJournalWindow;

@SuiteClasses( 
{
	TestCharacterCreationWindow.class,
	TestInGameWindow.class,
	TestMainMenuWindow.class,
	TestPersonaWindow.class,
	TestJournalWindow.class,
	TestEquipmentWindow.class,
	TestConsoleWindow.class
})
@RunWith(Suite.class)
public class AllFunctionalTests {
}
