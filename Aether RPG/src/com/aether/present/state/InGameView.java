package com.aether.present.state;

public interface InGameView {
	public static final String CHAT_ID = "in.game.chat";
	public static final String STATS_ID = "in.game.statistics";
	public static final String STATS_HEALTH_ID = "in.game.statistics.health";

	void setPresenter(InGamePresenter anyObject);

	void activate();

	void deactivate();

	/**
	 * A percentage value
	 * @param i
	 * @param j 
	 */
	void setHealth(int maximum, int current);
}
