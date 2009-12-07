package com.aether.present;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.aether.present.game.TestMyInputHandler;
import com.aether.present.game.TestSkyboxFacingImage;
import com.aether.present.hud.TestInGameHUDWindowLocator;
import com.aether.present.hud.TestPersonaPresenter;
import com.aether.present.hud.persona.TestPersonaImages;
import com.aether.present.state.TestCharacterCreationPresenter;
import com.aether.present.state.TestGamePresentationTransitioner;
import com.aether.present.state.TestInGamePresenter;
import com.aether.present.state.TestMainMenuPresenter;
import com.aether.present.state.TestShutdownService;

@SuiteClasses ({
	TestPlayerMovementState.class,
	TestMyInputHandler.class,
	TestSkyboxFacingImage.class, 
	TestInGameHUDWindowLocator.class,
	TestPersonaPresenter.class,
	TestPersonaImages.class, 
	TestCharacterCreationPresenter.class,
	TestGamePresentationTransitioner.class,
	TestInGamePresenter.class,
	TestMainMenuPresenter.class,
	TestShutdownService.class
})
@RunWith(Suite.class)
public class AllPresenterTests {

}
