package com.aether.present.hud;

import java.util.Vector;
import java.util.concurrent.Callable;

import com.aether.present.hud.questJournal.JournalHeader;
import com.aether.present.hud.questJournal.JournalDescriptionHeader;
import com.aether.model.KillQuest;
import com.aether.model.Quest;
import com.jme.util.GameTaskQueueManager;
import com.jmex.bui.BButton;
import com.jmex.bui.BList;
import com.jmex.bui.BScrollPane;
import com.jmex.bui.BTextArea;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.headlessWindows.BDraggableWindow;
import com.jmex.bui.layout.AbsoluteLayout;
import com.jmex.bui.util.Rectangle;

class QuestJournalWindow implements QuestJournalView {
    private Vector<Quest> qpointers = new Vector<Quest>(30);
    
    private JournalHeader journalHeader;
    private JournalDescriptionHeader descriptionHeader;

   	private BWindow window;

    private BButton share;
    private BButton abandon;
    
    private BList questLog;
    
    private BScrollPane scrollQuests;
    private BScrollPane scrollDescription;
    
    private BTextArea questDescription;
    
   // TODO We should get any model related stuff unnassociated with the window/view, and make
    // the presenter know about the model, and feed information to the view as simple strings
    // this will make it easier to separate the UI from the model.

	public QuestJournalWindow() 
    {
		window = initWindow();
		setupDisplay(window);
        
        // Testing purposes only
        Quest peskyBadgers = new KillQuest("Damn Badgers!","I hate those pesky badgers, kill me ten of them!",1,10,"Badgers");
        Quest moreBadgers = new KillQuest("More Badgers!","I really hate those pesky badgers, kill me ten more of them!",1,10,"Badgers");
        addQuest(peskyBadgers);
        addQuest(moreBadgers);
        addQuest(moreBadgers);
        addQuest(moreBadgers);
        addQuest(moreBadgers);
        showDescription(moreBadgers);
	}

	private BWindow initWindow() 
    {
		AbsoluteLayout layout = new AbsoluteLayout();
		BWindow result = new BDraggableWindow(BuiSystem.getStyle(), layout);
		result.setName(JOURNAL_ID);
		result.setSize(350, 450);
		result.center();
		result.setVisible(false);
		return result;
	}

	private void setupDisplay(BWindow window)
    {
        share = new BButton("Share");
        abandon = new BButton("Abandon");

        
        questLog = new BList();
        questLog.setEnabled(true);
        questDescription = new BTextArea();
        scrollQuests = new BScrollPane(questLog);
        scrollQuests.setVisible(true);
        scrollQuests.setShowScrollbarAlways(true);
        scrollDescription = new BScrollPane(questDescription);
        scrollDescription.setVisible(true);
        scrollDescription.setShowScrollbarAlways(true);


        journalHeader = new JournalHeader();
        descriptionHeader = new JournalDescriptionHeader();

        questLog.setSize(310, 200);
        questDescription.setEnabled(false);


        window.add(journalHeader,new Rectangle(0, 385, 340, 54));

        window.add(scrollQuests,new Rectangle(0,315,350,70));
        window.add(descriptionHeader,new Rectangle(0,230,340,54));
        window.add(scrollDescription,new Rectangle(0,120,340,100));

        window.add(share,new Rectangle(10,10,100,40));
        window.add(abandon,new Rectangle(200, 10, 125, 40));
	}


    public void showDescription(Quest quest)
    {
        questDescription.setText("");
        questDescription.setText(quest.getStatus() + "\n");
        questDescription.appendText(quest.getDescription());
    }
    
	public void addQuest(Quest quest)
    {
        String logFormat = quest.getName() + "-                                                  [" + quest.getLevelRequirement() + "]";
        if(qpointers.size() < qpointers.capacity())
        {
            questLog.addValue(logFormat);
            qpointers.add(quest);
        }
    }

   /* public Quest getSelectedQuest()
    {
        Quest targetQuest = null;
        String name = (String)questLog.getSelectedValue();
        name = name.substring(0, name.indexOf("-"));

        for(int i = 0; i < qpointers.size();i++)
        {
            if(qpointers.elementAt(i).getName().equals(name))
            {
                targetQuest = qpointers.elementAt(i);
            }
        }

        return targetQuest;
    }*/

    /*public void removeQuest(Quest quest)
    {
        for(int i = 0;i < qpointers.size();i++)
        {
            if(quest.getName().equals(qpointers.elementAt(i).getName()))
            {
                questLog.remove(i+1);
                qpointers.remove(i);
            }
        }
    }*/


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

	// Instead of passing in quests, can we pass in data to be displayed?
    @Override
    public void setQuests(Vector quests)
    {
        qpointers = quests;
    }

	@Override
	public void setPresenter(QuestJournalPresenter anyObject) {

	}

    @Override
	public void setVisible(boolean visibleState) {
		window.setVisible(visibleState);
		BuiSystem.getRootNode().updateRenderState();
	}
}
