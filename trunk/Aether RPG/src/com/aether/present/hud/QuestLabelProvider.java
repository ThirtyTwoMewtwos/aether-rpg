package com.aether.present.hud;

import java.awt.Image;

import com.aether.model.quests.JournalEntry;
import com.aether.model.quests.QuestTypeImage;
import com.jmex.bui.BImage;
import com.jmex.bui.ImageLabelProvider;
import com.jmex.bui.icon.BIcon;
import com.jmex.bui.icon.ImageIcon;

class QuestLabelProvider implements ImageLabelProvider {

	@Override
	public String getText(Object object) {
		if (object instanceof JournalEntry) {
			JournalEntry entry = (JournalEntry) object;
			return entry.getTitle();
		}
		return object.toString();
	}

	@Override
	public BIcon getImage(Object value) {
		JournalEntry entry = (JournalEntry) value;
		Image image = QuestTypeImage.getImage(entry.getQuestType());
		BImage bImage = new BImage(image);
		return new ImageIcon(bImage);
	}

}
