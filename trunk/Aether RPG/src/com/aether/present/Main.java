package com.aether.present;

import java.awt.Color;

import org.gap.jseed.ServiceStore;

import com.aether.present.css.BssColor;
import com.aether.present.css.BssFontStyle;
import com.aether.present.css.BssStyleClass;
import com.aether.present.css.BssTextEffect;
import com.aether.present.css.BssWriter;
import com.aether.present.state.ActiveState;
import com.aether.present.state.FinishGameService;
import com.aether.present.state.GameStateTransitioner;
import com.aether.present.state.MainMenuPresenter;
import com.aether.present.state.MainMenuView;
import com.aether.present.state.ShutdownService;
import com.aether.present.state.StateTransitioner;
import com.jme.app.AbstractGame;
import com.jme.input.InputHandler;
import com.jme.input.MouseInput;
import com.jme.util.Timer;
import com.jmex.bui.BStyleSheet;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.PolledRootNode;
import com.jmex.bui.bss.BStyleSheetUtil;
import com.jmex.game.StandardGame;

public class Main {
	public static void main(String[] args) {
		StandardGame game = new StandardGame("Aether RPG");
		game.start();
		
		MouseInput.get().setCursorVisible(true);
		ServiceStore store = new ServiceStore();
		
		loadServices(game, store);
		
		loadBaseStyleSheet();
		bindGameState(store);
	}

	private static void loadServices(StandardGame game, ServiceStore store) {
		store.bind(AbstractGame.class, game);
		store.bind(ShutdownService.class, FinishGameService.class);
		store.bind(StateTransitioner.class, GameStateTransitioner.class);
	}

	private static void loadBaseStyleSheet() {
		try {
			BssWriter writer = new BssWriter();
			
			BssStyleClass button = writer.button();
			button.setFont("Helvetica", BssFontStyle.BOLD, 24);
			button.setColor(BssColor.RED);
			button.setBackground(BssColor.BLUE);
			
			BssStyleClass buttonHover = writer.buttonHover();
			buttonHover.setFont("Helvetica", BssFontStyle.BOLD, 24);
			buttonHover.setTextEffect(BssTextEffect.OUTLINE);
			buttonHover.setEffectColor(new BssColor(new Color(255, 0, 0, 50)));
			
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
		store.bind(MainMenuView.class, MainWindow.class);
		store.bind(MainMenuPresenter.class, MainMenuPresenter.class);

		StateTransitioner stateTransitioner = store.get(StateTransitioner.class);
		
		ActiveState mainMenu = store.get(MainMenuPresenter.class);
		
		stateTransitioner.add(mainMenu, MainMenuPresenter.CREATE_CHARACTER_TRANSITION, mainMenu);
		stateTransitioner.setStartState(mainMenu);
	}
}
