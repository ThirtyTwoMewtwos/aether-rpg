package com.aether.present.hud;

public interface QuestJournalView {
	public static final String JOURNAL_ID = "hud.journal.view";


	void setPresenter(QuestJournalPresenter anyObject);

	void activate();

	void deactivate();

    void setVisible(boolean b);
    
    void setQuests(java.util.Vector log);
}
