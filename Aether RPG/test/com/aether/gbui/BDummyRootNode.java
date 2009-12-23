/**
 * 
 */
package com.aether.gbui;

import com.jmex.bui.BComponent;
import com.jmex.bui.BRootNode;

final class BDummyRootNode extends BRootNode {
	@Override
	public void rootInvalidated(BComponent root) {
	}

	@Override
	public long getTickStamp() {
		return 0;
	}
}