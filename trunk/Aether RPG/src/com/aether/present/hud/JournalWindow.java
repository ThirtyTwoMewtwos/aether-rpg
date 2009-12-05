package com.aether.present.hud;

import java.util.Collection;
import java.util.Vector;
import java.util.concurrent.Callable;

import com.aether.model.quests.JournalEntry;
import com.aether.present.hud.questJournal.JournalDescriptionHeader;
import com.aether.present.hud.questJournal.JournalHeader;
import com.jme.util.GameTaskQueueManager;
import com.jmex.bui.BButton;
import com.jmex.bui.BList;
import com.jmex.bui.BScrollPane;
import com.jmex.bui.BTextArea;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.headlessWindows.BDraggableWindow;
import com.jmex.bui.layout.AbsoluteLayout;
import com.jmex.bui.util.Rectangle;

class JournalWindow implements JournalView {
	private Vector<JournalEntry> qpointers = new Vector<JournalEntry>(30);

	private JournalHeader journalHeader;
	private JournalDescriptionHeader descriptionHeader;

	private BWindow window;

	private BButton share;
	private BButton abandon;

	private BList entries;

	private BScrollPane scrollQuests;
	private BScrollPane scrollDescription;

	private BTextArea questDescription;

	private JournalPresenter presenter;

	public JournalWindow() {
		window = initWindow();
		setupDisplay(window);
	}

	private BWindow initWindow() {
		AbsoluteLayout layout = new AbsoluteLayout();
		BWindow result = new BDraggableWindow(BuiSystem.getStyle(), layout);
		result.setName(JOURNAL_ID);
		result.setSize(350, 450);
		result.center();
		result.setVisible(false);
		return result;
	}

	private void setupDisplay(BWindow window) {
		share = new BButton("Share");
		abandon = new BButton("Abandon");

		entries = new BList();
		entries.setName(JOURNAL_ENTRIES_ID);
		entries.setEnabled(true);
		entries.addListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (event.getAction() == BList.SELECT) {
					String selected = (String)entries.getSelectedValue();
					presenter.showDescription(selected);
				}
			}
		});
		
		questDescription = new BTextArea();
		questDescription.setName(ENTRY_DESCRIPTION_ID);
		questDescription.setEnabled(false);

		scrollQuests = new BScrollPane(entries);
		scrollQuests.setVisible(true);
		scrollQuests.setShowScrollbarAlways(true);
		scrollDescription = new BScrollPane(questDescription);
		scrollDescription.setVisible(true);
		scrollDescription.setShowScrollbarAlways(true);

		journalHeader = new JournalHeader();
		descriptionHeader = new JournalDescriptionHeader();

		entries.setSize(310, 200);

		window.add(journalHeader, new Rectangle(0, 385, 340, 54));

		window.add(scrollQuests, new Rectangle(0, 315, 350, 70));
		window.add(descriptionHeader, new Rectangle(0, 230, 340, 54));
		window.add(scrollDescription, new Rectangle(0, 120, 340, 100));

		window.add(share, new Rectangle(10, 10, 100, 40));
		window.add(abandon, new Rectangle(200, 10, 125, 40));
	}

	public void addQuest(JournalEntry quest) {
		String logFormat = quest.getTitle()
				+ "-                                                  ["
				+ quest.getLevelRequirement() + "]";
		if (qpointers.size() < qpointers.capacity()) {
			entries.addValue(logFormat);
			qpointers.add(quest);
		}
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
	public void setQuests(Collection<String> listOfQuests) {
		entries.removeAll();
		for (String each : listOfQuests) {
			entries.addValue(each);
		}
	}

	@Override
	public void setDescription(String description) {
		questDescription.setText(description);
	}

	@Override
	public void setPresenter(JournalPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setVisible(boolean visibleState) {
		window.setVisible(visibleState);
		BuiSystem.getRootNode().updateRenderState();
	}
}
