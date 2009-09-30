package com.aether.present;

import org.gap.jseed.ServiceStore;

import com.aether.present.css.BssColor;
import com.aether.present.css.BssFontStyle;
import com.aether.present.css.BssStyleClass;
import com.aether.present.css.BssWriter;
import com.aether.present.state.ShutdownService;
import com.jme.app.AbstractGame;
import com.jme.input.InputHandler;
import com.jme.input.MouseInput;
import com.jme.util.Timer;
import com.jmex.bui.BStyleSheet;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.PolledRootNode;
import com.jmex.bui.bss.BStyleSheetUtil;
import com.jmex.bui.provider.DefaultResourceProvider;
import com.jmex.game.StandardGame;
import com.jmex.game.state.GameStateManager;

public class Main {
	public static void main(String[] args) {
		StandardGame game = new StandardGame("ATechnique");
		game.start();
		
		MouseInput.get().setCursorVisible(true);
		ServiceStore store = new ServiceStore();
		store.bind(AbstractGame.class, game);
		store.bind(ShutdownService.class, ShutdownService.class);
		store.bind(MainWindow.class, MainWindow.class);

		loadBStyleSheets();
		
		bindGameState(store);
	}

	private static void loadBStyleSheets() {
		try {
			BssWriter writer = new BssWriter();
			
			BssStyleClass button = writer.button();
			button.setFont("Helvetica", BssFontStyle.BOLD, 24);
			button.setColor(BssColor.RED);
			button.setBackground(BssColor.BLUE);
			
			BssStyleClass label = writer.label();
			label.setFont("Helvetica", BssFontStyle.ITALIC, 30);
			label.setColor(BssColor.GREEN);
			label.clearBackground();
			
			BStyleSheet styleSheet = BStyleSheetUtil.getStyleSheet(writer.asReader());
			BuiSystem.init(new PolledRootNode(Timer.getTimer(), new InputHandler()), styleSheet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void bindGameState(ServiceStore store) {
		MainWindow mainWindow = store.get(MainWindow.class);
		GameStateManager.getInstance().attachChild(mainWindow);
		mainWindow.activate();
	}
}
