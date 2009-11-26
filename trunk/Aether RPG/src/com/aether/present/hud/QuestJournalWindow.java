package com.aether.present.hud;

import java.util.concurrent.Callable;
import java.util.Vector;

import com.aether.present.UILookAndFeel;
import com.aether.model.Quest;
import com.aether.model.KillQuest;
import com.jme.util.GameTaskQueueManager;
import com.jmex.bui.*;
import com.jmex.bui.headlessWindows.BDraggableWindow;
import com.jmex.bui.layout.AbsoluteLayout;
import com.jmex.bui.util.Rectangle;

class QuestJournalWindow implements QuestJournalView 
{
    private Vector<Quest> qpointers = new Vector(30);

   	private BWindow window;

    private BButton share;
    private BButton abandon;
    
    private BList questLog;
    
    private BScrollPane scrollQuests;
    private BScrollPane scrollDescription;
    
    private BTextArea questDescription;
    
    private BLabel questsLabel;
    private BLabel questsLevelLabel;
    private BLabel questDescriptionLabel;
   
	public QuestJournalWindow() 
    {
		window = initWindow();
		setupDisplay(window);
        
        // Testing purposes only
        Quest peskyBadgers = new KillQuest("Damn Badgers!","I hate those pesky badgers, kill me ten of them!",1,10,"Badgers");
        addQuest(peskyBadgers);
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
        questDescription = new BTextArea();
        scrollQuests = new BScrollPane(questLog);
        scrollDescription = new BScrollPane(questDescription);

        questsLabel = new BLabel("Quest Log:");
        questsLevelLabel = new BLabel("Level:");
        questDescriptionLabel = new BLabel("Description:");

        questLog.setSize(310, 200);
        questDescription.setEnabled(false);


        window.add(questsLabel,new Rectangle(10,430,155,10));
        window.add(questsLevelLabel,new Rectangle(156,430, 155, 10));

        window.add(scrollQuests,new Rectangle(0,200,310,200));
        window.add(questDescriptionLabel,new Rectangle(10,230,155,10));
        window.add(scrollDescription,new Rectangle(10,240,310,200));

        window.add(share,new Rectangle(10,20,40,55));
        window.add(abandon,new Rectangle(170, 20, 40, 55));
	}

	public void addQuest(Quest quest)
    {
        String logFormat = quest.getName() + "-             [" + quest.getLevelRequirement() + "]";
		questLog.addValue(logFormat);
        qpointers.add(quest);
    }

    public void updateQuest(Quest quest)
    {
        if(quest.isComplete())
        {
            String name = (String)questLog.getSelectedValue();
            name = name.substring(0, name.indexOf("-"));

            String foundName = "";
            for(int i = 0; i < qpointers.size();i++)
            {
                if(qpointers.elementAt(i).getName().equals(name))
                {
                    foundName = qpointers.elementAt(i).getName();
                    questLog.setSelectedValue(foundName + "-             [" + quest.getLevelRequirement() + "]");
                    removeQuest(quest);
                }
            }
            String logformat = foundName + "-             [ Complete ]";
            questLog.addValue(logformat);
        }
  
    }

    public void removeQuest(Quest quest)
    {
        for(int i = 0;i < qpointers.size();i++)
        {
            if(quest.getName().equals(qpointers.elementAt(i).getName()))
            {
                questLog.remove(i+1);
                qpointers.remove(i);
            }
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
