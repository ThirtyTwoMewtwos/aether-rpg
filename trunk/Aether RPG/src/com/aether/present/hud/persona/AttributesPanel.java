package com.aether.present.hud.persona;

import java.awt.Image;

import com.aether.gbui.BImageUtils;
import com.aether.present.UILookAndFeel;
import com.aether.present.hud.PersonaView;
import com.jmex.bui.BContainer;
import com.jmex.bui.BLabel;
import com.jmex.bui.layout.AbsoluteLayout;
import com.jmex.bui.layout.HGroupLayout;
import com.jmex.bui.layout.VGroupLayout;
import com.jmex.bui.util.Rectangle;

public class AttributesPanel extends BContainer {
	private BLabel strength;
	private BLabel dexterity;
	private BLabel intelligence;
	private BLabel wisdom;
	private BLabel toughness;
	private BLabel health;
	private BLabel mana;

	public AttributesPanel() {
		setLayoutManager(new AbsoluteLayout());
		
		createAttributes();
        createStatus();
	}

	private void createAttributes() {
		BContainer stats = createLabels();
		BContainer values = createValues();

        add(values, new Rectangle(5, 65, 45, 120));
        add(stats,  new Rectangle(45, 65, 50, 120));
	}

	private BContainer createLabels() {
		BContainer stats = createContainer();
        
        createAttribute(stats, PersonaView.STRENGTH_STATISTIC);
        createAttribute(stats, PersonaView.DEXTERITY_STATISTIC);
        createAttribute(stats, PersonaView.INTELLIGENCE_STATISTIC);
        createAttribute(stats, PersonaView.WISDOM_STATISTIC);
        createAttribute(stats, PersonaView.TOUGHNESS_STATISTIC);
        
		return stats;
	}
	
	private BContainer createContainer() {
		BContainer result = new BContainer();
		VGroupLayout layout = new VGroupLayout();
		layout.setGap(0);
		result.setLayoutManager(layout);
		result.setSize(80, 120);
		return result;
	}

	private BLabel createAttributeValue(BContainer container, String statId) {
		BLabel result = new BLabel("9");
		result.setStyleClass(UILookAndFeel.HUD_STATISTICS_VALUES);
		result.setName(statId);
		container.add(result);
		return result;
	}

	private BContainer createValues() {
		BContainer values = createContainer();
		values.setStyleClass(UILookAndFeel.PERSONA_ATTRIBUTES_PANEL);
		
		strength = createAttributeValue(values, PersonaView.STRENGTH_STATISTIC);
		dexterity = createAttributeValue(values, PersonaView.DEXTERITY_STATISTIC);
		intelligence = createAttributeValue(values, PersonaView.INTELLIGENCE_STATISTIC);
		wisdom = createAttributeValue(values, PersonaView.WISDOM_STATISTIC);
        toughness = createAttributeValue(values, PersonaView.TOUGHNESS_STATISTIC);
		return values;
	}
	
	private void createAttribute(BContainer container, String text) {
		BLabel result = new BLabel(text);
		result.setStyleClass(UILookAndFeel.HUD_STATISTICS_LABELS);
		container.add(result);
	}

	private void createStatus() {
		BContainer container = new BContainer();
		HGroupLayout layout = new HGroupLayout();
		layout.setGap(20);
		container.setLayoutManager(layout);	
		
		health = createAttributeValue(container, PersonaView.HEALTH_STATISTIC);
		mana = createAttributeValue(container, PersonaView.MANA_STATISTIC);
		
		add(container, new Rectangle(10, 5, 100, 40));
	}
	
	public void setStrength(String newStrength) {
		strength.setText(newStrength);
	}
	
	public void setDexterity(String value) {
		dexterity.setText(value);
	}

	public void setInteligence(String value) {
		intelligence.setText(value);
	}

	public void setWisdom(String value) {
		wisdom.setText(value);
	}

    public void setToughness(String value){
        toughness.setText(value);
    }
    
    public void setHealth(int current, int max) {
    	setAttribute(health, CombatPanelStatistics.Health, current, max);
    }

    public void setMana(int current, int max) {
    	setAttribute(mana, CombatPanelStatistics.Mana, current, max);
    }

    private void setAttribute(BLabel label, CombatPanelStatistics stat, int current, int max) {
    	label.setText(current + "/" + max);
    	Image image = PersonaImages.getImage(stat);
    	BImageUtils.loadBackgroundImageFor(label, image);
    }
}
