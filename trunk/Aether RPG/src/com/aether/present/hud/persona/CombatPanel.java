package com.aether.present.hud.persona;

import java.awt.Image;

import com.aether.gbui.BImageUtils;
import com.aether.present.UILookAndFeel;
import com.aether.present.hud.PersonaView;
import com.jmex.bui.BContainer;
import com.jmex.bui.BImage;
import com.jmex.bui.BLabel;
import com.jmex.bui.background.ImageBackground;
import com.jmex.bui.enumeratedConstants.ImageBackgroundMode;
import com.jmex.bui.layout.TableLayout;

public class CombatPanel extends BContainer {
	private BLabel melee;
	private BLabel block;
	
	private BLabel range;
	private BLabel dodge;
	
	private BLabel magic;
	private BLabel dispell;

	private BLabel critical;
	private BLabel defense;

	public CombatPanel() {
		TableLayout layout = new TableLayout(2, 5, 35);
		
		setLayoutManager(layout);
		setStyleClass(UILookAndFeel.PERSONA_ATTRIBUTES_PANEL);
		
		melee = createStatistic(PersonaView.MELEE_STATISTIC);
		block = createStatistic(PersonaView.BLOCK_STATISTIC);
		range = createStatistic(PersonaView.RANGE_STATISTIC);
		dodge = createStatistic(PersonaView.DODGE_STATISTIC);
		magic = createStatistic(PersonaView.MAGIC_STATISTIC);
		dispell = createStatistic(PersonaView.DISPELL_STATISTIC);
		critical = createStatistic(PersonaView.CRITICAL_STATISTIC);
		defense = createStatistic(PersonaView.DEFENSE_STATISTIC);
	}

	private BLabel createStatistic(String statistic) {
		BLabel result = new BLabel("100");
		result.setName(statistic);
		result.setStyleClass(UILookAndFeel.PERSONA_STATISTICS_VALUES);
		result.setSize(45, 45);
		add(result);
		return result;
	}
	
	public void setBlock(String newBlockValue) {
		block.setText(newBlockValue);
		Image image = PersonaImages.getImage(CombatPanelStatistics.Block);
		BImageUtils.loadBackgroundImageFor(block, image);
	}
	
	public void setDodge(String newDodgeValue) {
		dodge.setText(newDodgeValue);
		Image image = PersonaImages.getImage(CombatPanelStatistics.Dodge);
		BImageUtils.loadBackgroundImageFor(dodge, image);
	}
	
	public void setRange(String newRangeValue) {
		range.setText(newRangeValue);
		Image image = PersonaImages.getImage(CombatPanelStatistics.Range);
		BImageUtils.loadBackgroundImageFor(range, image);
	}
	
	public void setMelee(String newMeleeValue) {
		melee.setText(newMeleeValue);
		Image image = PersonaImages.getImage(CombatPanelStatistics.Melee);
		BImageUtils.loadBackgroundImageFor(melee, image);
	}

	public void setMagic(String newMagicValue) {
		magic.setText(newMagicValue);
		Image image = PersonaImages.getImage(CombatPanelStatistics.Magic);
		BImageUtils.loadBackgroundImageFor(magic, image);
	}
	
	public void setDispell(String newDispellValue) {
		dispell.setText(newDispellValue);
		Image image = PersonaImages.getImage(CombatPanelStatistics.Dispell);
		BImageUtils.loadBackgroundImageFor(dispell, image);
	}
	
	public void setCritical(String newCriticalValue) {
		critical.setText(newCriticalValue);
		Image image = PersonaImages.getImage(CombatPanelStatistics.Critical);
		BImageUtils.loadBackgroundImageFor(critical, image);
	}
	
	public void setDefense(String newDefenseValue) {
		defense.setText(newDefenseValue);
		Image image = PersonaImages.getImage(CombatPanelStatistics.Defense);
		BImageUtils.loadBackgroundImageFor(defense, image);
	}
}
