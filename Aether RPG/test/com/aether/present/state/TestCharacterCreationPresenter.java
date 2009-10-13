package com.aether.present.state;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.createStrictMock;
import static org.easymock.EasyMock.matches;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.junit.Before;
import org.junit.Test;

import com.aether.model.character.Classification;
import com.aether.model.character.Race;

public class TestCharacterCreationPresenter {
    private StateTransition stateTransition;
    private CharacterCreationView view;

    @Before
    public void setUp() {
        stateTransition = createMock(StateTransition.class);
        view = createStrictMock(CharacterCreationView.class);
        view.setPresenter((CharacterCreationPresenter)anyObject());
        view.disableSave();
    }

    @Test
    public void testCharacterCreation() throws Exception {
        view.disableSave();
        view.setClasses(Classification.getAvailableFor(Race.HUMAN));
        view.disableSave();
        view.enableSave();
        view.disableSave();
        view.enableSave();
        stateTransition.transition((ActiveState)anyObject(), matches(CharacterCreationPresenter.MAIN_MENU_TRANSITION));

        replay(view, stateTransition);
        CharacterCreationPresenter presenter = new CharacterCreationPresenter(view, stateTransition);
        presenter.setName("Billy the Kid");
        presenter.setRace(Race.HUMAN);
        presenter.setClassification(Classification.Crusader);
        presenter.setName(""); // not a valid name, disable save
        presenter.setName("Super boy"); // valid name, enable save
        presenter.finish();
        verify(view, stateTransition);
    }

    @Test
    public void testEnterActiveAndExitActiveState() throws Exception {
        view.activate();
        view.deactivate();

        replay(view, stateTransition);
        CharacterCreationPresenter presenter = new CharacterCreationPresenter(view, stateTransition);
        presenter.enter();
        presenter.exit();
        verify(view, stateTransition);
    }
}
