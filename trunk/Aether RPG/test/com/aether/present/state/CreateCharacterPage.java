package com.aether.present.state;

import java.awt.AWTException;

import com.aether.gbui.NameOperatorSearch;
import com.aether.gbui.operators.BButtonOperator;
import com.aether.gbui.operators.BComboBoxOperator;
import com.aether.gbui.operators.BComponentOperatorUtil;
import com.aether.gbui.operators.BTextFieldOperator;
import com.aether.model.character.Classification;
import com.aether.model.character.Race;
import com.jmex.bui.BWindow;

public class CreateCharacterPage {
	private BWindow window;

	public CreateCharacterPage() throws InterruptedException {
		window = BComponentOperatorUtil.getWindowWithId(CharacterCreationView.ID);
	}
	
	public void clickFinish() throws AWTException {
		new BButtonOperator(window, "Finish").click();
	}

	public void setName(String name) {
		new BTextFieldOperator(window, "").setText(name);
	}

	public void selectRace(Race race) {
		new BComboBoxOperator(window, new NameOperatorSearch(CharacterCreationView.RACE_SELELECTION_NAME)).select(race);
	}

	public void selectClass(Classification classification) {
		new BComboBoxOperator(window, new NameOperatorSearch(CharacterCreationView.CLASS_SELECTION_NAME)).select(classification);
	}
}
