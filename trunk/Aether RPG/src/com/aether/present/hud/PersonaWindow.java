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
    private BLabel name;
    private BLabel race;
    private BLabel sex;
    private BLabel classification;

    private BLabel hp;
    private BLabel mp;
    private BLabel level;
    private BLabel exp;
    private BLabel nextlevel;
	private BLabel strength;
	private BLabel dexterity;
	private BLabel intelligence;
	private BLabel wisdom;
    private BLabel toughness;
    private BLabel defense;
    private BLabel melee;
    private BLabel range;
    private BLabel magic;
    private BLabel dispell;
    private BLabel block;
    private BLabel dodge;
    private BLabel crit;
	
	public PersonaWindow() {
		window = initWindow();
		initStatistics(window);
	}
	
	private BWindow initWindow() {
		AbsoluteLayout layout = new AbsoluteLayout();
		BWindow result = new BDraggableWindow(BuiSystem.getStyle(), layout);
		result.setName(PERSONA_ID);
		result.setSize(150, 380);
		result.center();
		result.setVisible(false);
		return result;
	}
	
	private void initStatistics(BWindow window) {

        name = createStat(window,"Luccin","",340);
        classification = createStat(window,"Dark Gard","",325);
        race = createStat(window,"Revenant","",310);
        sex = createStat(window,"Male","",295);
        hp = createStat(window, "100", HP_STATISTIC, 280);
        mp = createStat(window, "100", MP_STATISTIC, 265);
        level = createStat(window, "1", LEVEL_STATISTIC, 250);
        exp = createStat(window, "0", EXP_STATISTIC, 235);
        nextlevel = createStat(window, "300", NEXTLEVEL_STATISTIC, 220);

		strength = createStat(window, "9", STRENGTH_STATISTIC, 205);
		dexterity = createStat(window, "9", DEXTERITY_STATISTIC, 190);
		intelligence = createStat(window, "9", INTELLIGENCE_STATISTIC, 175);
		wisdom = createStat(window, "9", WISDOM_STATISTIC, 160);
        toughness = createStat(window,"9",TOUGHNESS_STATISTIC, 145);

        defense = createStat(window,"15",DEFENSE_STATISTIC,130);
        melee = createStat(window,"9.7",MELEE_STATISTIC,115);
        range = createStat(window,"9.7",RANGE_STATISTIC,100);
        magic = createStat(window,"9.7",MAGIC_STATISTIC,85);
        dispell = createStat(window,"9.7",DISPELL_STATISTIC,70);
        dodge = createStat(window,"9.7",DODGE_STATISTIC,55);
        crit = createStat(window,"9.7", CRITICAL_STATISTIC,40);
        block = createStat(window,"9.7",BLOCK_STATISTIC,25);
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
    public void setToughness(int value){
        toughness.setText("" + value);
    }

    @Override
    public void setDefense(int value){
        defense.setText("" + value);
    }

    @Override
    public void setHP(int value){
        hp.setText("" + value);
    }

    @Override
    public void setMP(int value){
        mp.setText("" + value);
    }

    @Override
    public void setLevel(int value){
        level.setText("" + value);
    }

    @Override
    public void setExp(int value){
        exp.setText("" + value);
    }

    @Override
    public void setNextLevel(int value){
        nextlevel.setText("" + value);
    }

    @Override
    public void setMelee(double value){
        melee.setText("" + value);
    }

    @Override
    public void setRange(double value){
        range.setText("" + value);
    }

    @Override
    public void setMagic(double value){
        magic.setText("" + value);
    }

    @Override
    public void setDispell(double value){
        dispell.setText("" + value);
    }

    @Override
    public void setBlock(double value){
        block.setText("" + value);
    }

    @Override
    public void setDodge(double value){
        dodge.setText("" + value);
    }

    @Override
    public void setCrit(double value){
        crit.setText("" + value);
    }

    @Override
    public void setName(String vlaue){
        name.setText("" + vlaue);
    }

    @Override
    public void setRace(String value){
        race.setText("" + value);
    }

    @Override
    public void setClassification(String value){
        classification.setText("" + value);
    }

    @Override
    public void setSex(String value){
        sex.setText("" + value);
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
