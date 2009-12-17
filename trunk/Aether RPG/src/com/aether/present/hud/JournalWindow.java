package com.aether.present.hud;

import java.util.Collection;
import java.util.concurrent.Callable;

import org.gap.jseed.injection.annotation.Singleton;

import com.aether.model.quests.JournalEntry;
import com.aether.present.UILookAndFeel;
import com.aether.present.hud.journal.JournalDescriptionHeader;
import com.aether.present.hud.journal.JournalHeader;
import com.jme.renderer.ColorRGBA;
import com.jme.util.GameTaskQueueManager;
import com.jmex.bui.BButton;
import com.jmex.bui.BList;
import com.jmex.bui.BScrollPane;
import com.jmex.bui.BTextArea;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.event.SelectionListener;
import com.jmex.bui.event.StateChangedEvent;
import com.jmex.bui.event.StateChangedEvent.SelectionState;
import com.jmex.bui.headlessWindows.BDraggableWindow;
import com.jmex.bui.layout.AbsoluteLayout;
import com.jmex.bui.util.Rectangle;

@Singleton
class JournalWindow implements JournalView {
	private JournalHeader journalHeader;

	private BWindow window;
	private BList entries;
	private BTextArea description;

	private JournalPresenter presenter;

	public JournalWindow() {
		window = initWindow();
		setupDisplay(window);
	}

	private BWindow initWindow() {
		AbsoluteLayout layout = new AbsoluteLayout();
		BWindow result = new BDraggableWindow(BuiSystem.getStyle(), layout);
		result.setName(JOURNAL_ID);
		result.setSize(300, 375);
		result.center();
		result.setVisible(false);
		return result;
	}

	private void setupDisplay(BWindow window) {
		journalHeader = new JournalHeader();
		createJournalEntries();
		JournalDescriptionHeader descriptionHeader = new JournalDescriptionHeader();
		createDescription();

		BScrollPane scrollQuests = new BScrollPane(entries);
		scrollQuests.setVisible(true);
		scrollQuests.setShowScrollbarAlways(true);
		
		BScrollPane scrollDescription = new BScrollPane(description);
		scrollDescription.setVisible(true);
		scrollDescription.setShowScrollbarAlways(true);

		BButton abandon = createAbandonButton();
		
		window.add(journalHeader, new Rectangle(0, 327, 290, 40));
		window.add(scrollQuests, new Rectangle(5, 247, 280, 70));
		window.add(descriptionHeader, new Rectangle(0, 165, 290, 40));
		window.add(scrollDescription, new Rectangle(0, 65, 290, 100));
		window.add(abandon, new Rectangle(150, 10, 125, 40));
	}

	private void createDescription() {
		description = new BTextArea();
		description.getHorizontalAlignment();
		description.setName(ENTRY_DESCRIPTION_ID);
		description.setEnabled(false);
	}

	private void createJournalEntries() {
		entries = new BList();
		entries.setName(JOURNAL_ENTRIES_ID);
		entries.setEnabled(true);
		entries.setLabelProvider(new QuestLabelProvider());
		entries.addSelectionListener(new SelectionListener() {
			@Override
			public void stateChanged(StateChangedEvent event) {
				if (event.getType() == SelectionState.Selected) {
					JournalEntry selected = (JournalEntry)entries.getSelectedValue();
					if (selected != null)
						presenter.showQuest(selected);
				}
			}
		});
		
	}

	private BButton createAbandonButton() {
		BButton result = new BButton("Abandon");
		result.setStyleClass(UILookAndFeel.STATISTICS_HEALTH);
		result.addListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				presenter.removeQuest((JournalEntry)entries.getSelectedValue());
			}
		});
		return result;
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
	public void setQuests(Collection<JournalEntry> listOfQuests) {
		entries.removeAll();
		for (JournalEntry each : listOfQuests) {
			entries.addValue(each);
		}
	}
	
	@Override
	public void setSelection(JournalEntry selection) {
		entries.setSelectedValue(selection);
	}
	
	@Override
	public void setLevelRequirement(String level) {
		journalHeader.setLevelRequirement(level);
	}

	@Override
	public void setDescription(String description) {
		this.description.clearText();
		this.description.appendText(description, ColorRGBA.white, BTextArea.BOLD);
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
