package com.aether.present;

import com.aether.gbui.NameOperatorSearch;
import com.aether.gbui.operators.BButtonOperator;
import com.aether.gbui.operators.BComboBoxOperator;
import com.aether.gbui.operators.BComponentOperatorUtil;
import com.aether.gbui.operators.BTextFieldOperator;
import com.aether.model.character.Classification;
import com.aether.model.character.Race;
import com.aether.present.state.CharacterCreationView;
import com.jmex.bui.BWindow;

public class CreateCharacterPage {
	private BWindow window;
	private BButtonOperator finishButton;
	private BButtonOperator backButton;
	private BComboBoxOperator raceSelection;
	private BComboBoxOperator classSelection;

	public CreateCharacterPage() throws InterruptedException {
		window = BComponentOperatorUtil.windowWithId(CharacterCreationView.ID);
	}
	
	public void clickBack() throws Exception {
		getBackButton().click();		
	}
	
	private BButtonOperator getBackButton() throws Exception {
		if (backButton == null) {
			backButton = new BButtonOperator(window, "Back");
		}
		return backButton;
	}

	public InGamePage clickFinish() throws Exception {
		getFinishButton().click();
		return new InGamePage();
	}

	public void setName(String name) {
		new BTextFieldOperator(window, "").setText(name);
	}

	public void clearRace() {
		getRaceSelection().select(0);
	}
	
	public void selectRace(Race race) {
		getRaceSelection().select(race);
	}

	public BComboBoxOperator getRaceSelection() {
		if (raceSelection == null) {
			raceSelection = new BComboBoxOperator(window, new NameOperatorSearch(CharacterCreationView.RACE_SELELECTION_NAME));
		}
		return raceSelection;
	}
	

	public void selectClass(Classification classification) {
		getClassSelection().select(classification);
	}

	public BComboBoxOperator getClassSelection() {
		if (classSelection == null) {
			classSelection = new BComboBoxOperator(window, new NameOperatorSearch(CharacterCreationView.CLASS_SELECTION_NAME));
		}
		return classSelection;
	}

	public void loadDummyData() {
		setName("John Grisham");
		selectRace(Race.Human);
		selectClass(Classification.Crusader);		
	}

	public BButtonOperator getFinishButton() throws Exception {
		if (finishButton == null) {
			finishButton = new BButtonOperator(window, "Finish");
		}
		return finishButton;
	}
}
