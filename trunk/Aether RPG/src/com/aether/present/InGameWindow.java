package com.aether.present;

import com.aether.present.state.InGamePresenter;
import com.aether.present.state.InGameView;
import com.jme.system.DisplaySystem;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.layout.TableLayout;
import com.jmex.game.state.GameStateManager;

public class InGameWindow extends BaseWindow implements InGameView {
	private InGamePresenter presenter;

    public InGameWindow() {
    	super("");
    	DisplaySystem display = DisplaySystem.getDisplaySystem();
        
    	TableLayout tableLayout = new TableLayout(2, 8, 8);
        tableLayout.setHorizontalAlignment(TableLayout.LEFT);
        tableLayout.setVerticalAlignment(TableLayout.CENTER);
        BWindow window = new BWindow(BuiSystem.getStyle(), tableLayout);
        window.setName(ID);
        window.setSize(display.getWidth() - 80, display.getHeight() - 100);
        setWindow(window);
        
    	GameStateManager.getInstance().attachChild(this);
	}

	public void setPresenter(InGamePresenter presenter) {
		this.presenter = presenter;
	}
}
