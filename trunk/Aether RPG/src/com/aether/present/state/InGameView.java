package com.aether.present.state;

import java.awt.Image;

public interface InGameView {
	public static final String CHAT_ID = "in.game.chat";
	public static final String CHAT_INPUT_ID = "in.game.chat.input";

	public static final String STATS_ID = "in.game.statistics";
	public static final String STATS_HEALTH_ID = "in.game.statistics.health";
	public static final String STATS_MANA_ID = "in.game.statistics.mana";
	public static final String STATS_SYMBOL_ID = "in.game.statistics.symbol";

	void setPresenter(InGamePresenter anyObject);

	void activate();

	void deactivate();

	/**
	 * A percentage value
	 * @param i
	 * @param j 
	 */

        void setXP(int currentXP, int xpNeededToLevel);
        
	void setHealth(int maximum, int current);

	void setMana(int maximum, int current);

	void setImage(Image image);
}
