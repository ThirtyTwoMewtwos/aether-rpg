package com.aether.present.hud;

import java.util.concurrent.Callable;

import com.aether.present.UILookAndFeel;
import com.jme.util.GameTaskQueueManager;
import com.jmex.bui.BLabel;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.headlessWindows.BDraggableWindow;
import com.jmex.bui.layout.AbsoluteLayout;
import com.jmex.bui.util.Rectangle;

class PersonaWindow implements PersonaView {
	private BWindow window;
	private BLabel strength;
	private BLabel dexterity;
	private BLabel intelligence;
	private BLabel wisdom;
	
	public PersonaWindow() {
		window = initWindow();
		initStatistics(window);
	}
	
	private BWindow initWindow() {
		AbsoluteLayout layout = new AbsoluteLayout();
		BWindow result = new BDraggableWindow(BuiSystem.getStyle(), layout);
		result.setName(PERSONA_ID);
		result.setSize(150, 250);
		result.center();
		result.setVisible(false);
		return result;
	}
	
	private void initStatistics(BWindow window) {
		strength = createStat(window, "9", STRENGTH_STATISTIC, 225);
		dexterity = createStat(window, "9", DEXTERITY_STATISTIC, 210);
		intelligence = createStat(window, "9", INTELLIGENCE_STATISTIC, 195);
		wisdom = createStat(window, "9", WISDOM_STATISTIC, 180);
	}

	private BLabel createStat(BWindow window, String value, String statName, int y) {
		BLabel statValue = new BLabel(value);
		statValue.setStyleClass(UILookAndFeel.PERSONA_STATISTICS_VALUES);
		statValue.setName(statName);
		window.add(statValue, new Rectangle(0, y, 25, 15));
		
		BLabel stat = new BLabel(statName);
		stat.setStyleClass(UILookAndFeel.PERSONA_STATISTICS);
		window.add(stat, new Rectangle(25, y, 40, 15));
		
		return statValue;
	}
	
	@Override
	public void activate() {
		GameTaskQueueManager.getManager().update(new Callable<Object>() {
			public Object call() throws Exception {
				BuiSystem.addWindow(window);
				return null;
			}
		});
	}

	@Override
	public void deactivate() {
		GameTaskQueueManager.getManager().update(new Callable<Object>() {
			public Object call() throws Exception {
				BuiSystem.removeWindow(window);
				return null;
			}
		});
	}

	@Override
	public void setStrength(int value) {
		strength.setText("" + value);
	}

	@Override
	public void setDexterity(int value) {
		dexterity.setText("" + value);
	}

	@Override
	public void setInteligence(int value) {
		intelligence.setText("" + value);
	}

	@Override
	public void setWisdom(int value) {
		wisdom.setText("" + value);
	}

	@Override
	public void setPresenter(PersonaPresenter anyObject) {

	}

	@Override
	public void setVisible(boolean visibleState) {
		window.setVisible(visibleState);
		BuiSystem.getRootNode().updateRenderState();
	}
}
