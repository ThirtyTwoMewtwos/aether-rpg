package com.aether.present.hud;

import java.util.concurrent.Callable;

import com.aether.present.UILookAndFeel;
import com.aether.present.hud.persona.AttributesPanel;
import com.aether.present.hud.persona.CombatPanel;
import com.aether.present.hud.persona.ExperiencePanel;
import com.aether.present.hud.persona.HeaderPanel;
import com.jme.util.GameTaskQueueManager;
import com.jmex.bui.BLabel;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.headlessWindows.BDraggableWindow;
import com.jmex.bui.layout.AbsoluteLayout;
import com.jmex.bui.util.Rectangle;

class PersonaWindow implements PersonaView {
	private BWindow window;
    
	private HeaderPanel headerPanel;
	private AttributesPanel attributesPanel;
	private CombatPanel combatPanel;
	private ExperiencePanel xpPanel;
	
	public PersonaWindow() {
		window = initWindow();
		headerPanel = new HeaderPanel();
		attributesPanel = new AttributesPanel();
		combatPanel = new CombatPanel();
		xpPanel = new ExperiencePanel();
		
		window.add(headerPanel, 	new Rectangle(0, 318, 270, 54));
		window.add(attributesPanel, new Rectangle(0, 120, 130, 120));
		window.add(combatPanel, 	new Rectangle(135, 120, 130, 185));
		window.add(xpPanel,         new Rectangle(0, 80, 270, 40));
	}

	private BWindow initWindow() {
		AbsoluteLayout layout = new AbsoluteLayout();
		BWindow result = new BDraggableWindow(BuiSystem.getStyle(), layout);
		result.setName(PERSONA_ID);
		result.setSize(280, 380);
		result.center();
		result.setVisible(false);
		return result;
	}

	private BLabel createStat(BWindow window, String value, String statName, int y) {
		BLabel statValue = new BLabel(value);
		statValue.setStyleClass(UILookAndFeel.PERSONA_STATISTICS_VALUES);
		statValue.setName(statName);
		window.add(statValue, new Rectangle(0, y, 80, 35));
		
		BLabel stat = new BLabel(statName);
		stat.setStyleClass(UILookAndFeel.PERSONA_STATISTICS);
		window.add(stat, new Rectangle(60, y, 85, 35));
		
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
		attributesPanel.setStrength("" + value);
	}

	@Override
	public void setDexterity(int value) {
		attributesPanel.setDexterity("" + value);
	}

	@Override
	public void setInteligence(int value) {
		attributesPanel.setInteligence("" + value);
	}

	@Override
	public void setWisdom(int value) {
		attributesPanel.setWisdom("" + value);
	}

    @Override
    public void setToughness(int value){
    	attributesPanel.setToughness("" + value);
    }

    @Override
    public void setHP(String value){
        attributesPanel.setHealth("" + value);
    }

    @Override
    public void setMP(String value){
        attributesPanel.setMana("" + value);
    }

    @Override
    public void setLevel(int value){
        headerPanel.setLevel("" + value);
    }

    @Override
    public void setXP(int currentXP, int xpNeededForNextLevel){
        xpPanel.setXP(currentXP, xpNeededForNextLevel);
    }

    @Override
    public void setMelee(double value){
        combatPanel.setMelee("" + value);
    }

    @Override
    public void setRange(double value){
        combatPanel.setRange("" + value);
    }

    @Override
    public void setMagic(double value){
        combatPanel.setMagic("" + value);
    }

    @Override
    public void setDispell(double value){
        combatPanel.setDispell("" + value);
    }

    @Override
    public void setBlock(double newBlock){
    	combatPanel.setBlock("" + newBlock);
    }

    @Override
    public void setDodge(double value){
        combatPanel.setDodge("" + value);
    }

    @Override
    public void setCritical(double value){
        combatPanel.setCritical("" + value);
    }
    
    @Override
    public void setDefense(int value){
        combatPanel.setDefense("" + value);
    }

    @Override
    public void setName(String newName){
        headerPanel.setName(newName);
    }

    @Override
    public void setRaceAndSex(String newRaceAndSex){
        headerPanel.setRaceAndSex(newRaceAndSex);
    }

    @Override
    public void setClassification(String newClass){
        headerPanel.setClassification(newClass);
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
