package com.aether.present.state;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.createStrictMock;
import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.matches;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.junit.Before;
import org.junit.Test;

import com.aether.model.CharacterSheet;
import com.aether.model.character.CharacterLocator;
import com.aether.model.character.Classification;
import com.aether.model.character.Race;

public class TestCharacterCreationPresenter {
    private StateTransition stateTransition;
    private CharacterCreationView view;
	private CharacterLocator characterManager;

    @Before
    public void setUp() {
        stateTransition = createMock(StateTransition.class);
        view = createStrictMock(CharacterCreationView.class);
        characterManager = createStrictMock(CharacterLocator.class);
        view.setPresenter((CharacterCreationPresenter)anyObject());
        view.disableSave();
    }

    @Test
    public void testCharacterCreation() throws Exception {
    	view = createNiceMock(CharacterCreationView.class);
    	view.setPresenter((CharacterCreationPresenter)anyObject());
        view.setClasses(Classification.getAvailableFor(Race.Human));
        characterManager.setPlayer((CharacterSheet)anyObject());
        stateTransition.transition((ActiveState)anyObject(), matches(CharacterCreationPresenter.GAME_WINDOW_TRANSITION));

        replay(view, stateTransition, characterManager);
        CharacterCreationPresenter presenter = new CharacterCreationPresenter(view, stateTransition, characterManager);
        presenter.setName("Billy the Kid");
        presenter.setRace(Race.Human);
        presenter.setClassification(Classification.Crusader);
        presenter.setName(""); // not a valid name, disable save
        presenter.setName("Super boy"); // valid name, enable save
        presenter.finish();
        verify(view, stateTransition, characterManager);
    }
    
    @Test
	public void test_Cancel_create_character() throws Exception {
		stateTransition.transition((ActiveState)anyObject(), matches(CharacterCreationPresenter.CANCEL_CREATE_CHARACTER_TRANSITION));
    	
		replay(view, stateTransition, characterManager);
		CharacterCreationPresenter presenter = new CharacterCreationPresenter(view, stateTransition, characterManager);
		presenter.backToMainMenu();
		verify(view, stateTransition, characterManager);
	}

    @Test
    public void testEnterActiveAndExitActiveState() throws Exception {
        view.activate();
        view.deactivate();

        replay(view, stateTransition);
        CharacterCreationPresenter presenter = new CharacterCreationPresenter(view, stateTransition, characterManager);
        presenter.enter();
        presenter.exit();
        verify(view, stateTransition);
    }
    
    @Test
    public void testRacialSelectionState() throws Exception {
    	view.setClasses(Classification.getAvailableFor(Race.Human));
    	view.disableSave();
    	view.clearClassifications();
    	view.disableSave();
    	 
        replay(view, stateTransition);
        CharacterCreationPresenter presenter = new CharacterCreationPresenter(view, stateTransition, characterManager);
        presenter.setRace(Race.Human);
        presenter.setRace(null);
        verify(view, stateTransition);
    }
}
