package com.aether.present;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.aether.present.game.TestMyInputHandler;
import com.aether.present.game.TestSkyboxFacingImages;
import com.aether.present.hud.TestConsolePresenter;
import com.aether.present.hud.TestEquipmentPresenter;
import com.aether.present.hud.TestInGameHUDWindowLocator;
import com.aether.present.hud.TestJournalPresenter;
import com.aether.present.hud.TestPersonaPresenter;
import com.aether.present.hud.equipment.TestItemTypeImage;
import com.aether.present.hud.journal.TestQuestLabelProvider;
import com.aether.present.hud.journal.TestQuestTypeImage;
import com.aether.present.hud.persona.TestCharacterTypeImage;
import com.aether.present.hud.persona.TestPersonaImages;
import com.aether.present.state.TestCharacterCreationPresenter;
import com.aether.present.state.TestGamePresentationTransitioner;
import com.aether.present.state.TestInGamePresenter;
import com.aether.present.state.TestLoginPresenter;
import com.aether.present.state.TestMainMenuPresenter;
import com.aether.present.state.TestShutdownService;

@SuiteClasses ({
	TestPlayerMovementState.class,
	TestMyInputHandler.class,
	TestInGameHUDWindowLocator.class,
	
	TestJournalPresenter.class,
	TestPersonaPresenter.class,
	TestEquipmentPresenter.class,
	TestCharacterCreationPresenter.class,
	TestMainMenuPresenter.class,
	TestInGamePresenter.class,
	TestConsolePresenter.class,
	TestLoginPresenter.class,
	
	TestSkyboxFacingImages.class, 
	TestPersonaImages.class,
	
	TestGamePresentationTransitioner.class,
	TestShutdownService.class,
	TestQuestLabelProvider.class,
	
	TestItemTypeImage.class,
	TestCharacterTypeImage.class,
	TestQuestTypeImage.class
})
@RunWith(Suite.class)
public class AllPresentTests {

}
