package com.aether.gbui;

/*
 * BDraggableChatWindow.java
 *
 * Copyright (c) 2008, Tyler Hoersch
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the University of Wisconsin Oshkosh nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY COPYRIGHT HOLDERS AND CONTRIBUTORS ''AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

import java.awt.Point;

import com.jmex.bui.BChatWindow;
import com.jmex.bui.BStyleSheet;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.BEvent;
import com.jmex.bui.event.MouseEvent;
import com.jmex.bui.layout.BLayoutManager;

public class BDraggableChatWindow extends BChatWindow {
	static final boolean CONSUME_EVENT = true;
	
	private boolean dragging = false;
	private Point grabbedInWindowPosition;

	public BDraggableChatWindow(BStyleSheet style, BLayoutManager layout) {
		super(style, layout);
	}

	@Override
	public boolean dispatchEvent(final BEvent event) {
		boolean isConsumed = false;
		if (event instanceof MouseEvent) {
			isConsumed = handleMouseEvent((MouseEvent)event);
		}
		return (isConsumed) ? isConsumed : super.dispatchEvent(event);
	}

	private boolean handleMouseEvent(MouseEvent mouseEvent) {
		switch (mouseEvent.getType()) {
		case MouseEvent.MOUSE_DRAGGED:
			dragWindow(mouseEvent);
			break;
		case MouseEvent.MOUSE_PRESSED:
			beginDragWindow(mouseEvent);
			return CONSUME_EVENT;
		case MouseEvent.MOUSE_RELEASED:
			endDragWindow(mouseEvent);
			return CONSUME_EVENT;
		}
		return false;
	}

	private void dragWindow(MouseEvent mev) {
		if (dragging) {
			int x = mev.getX() + grabbedInWindowPosition.x;
			int y = mev.getY() + grabbedInWindowPosition.y;
			setLocation(x, y);
		}
	}

	private void endDragWindow(MouseEvent mev) {
		if (dragging) {
			dragging = false;
			windowReleased(mev);
		}
	}

	private void beginDragWindow(MouseEvent mev) {
		if (mev.getButton() == MouseEvent.BUTTON1) {
			dragging = true;
			
			grabbedInWindowPosition = new Point(getX() - mev.getX(), getY()	- mev.getY());

			fireAction(mev.getWhen(), mev.getModifiers());
		}
	}

	protected void fireAction(final long when, final int modifiers) {
		emitEvent(new ActionEvent(this, when, modifiers, "activate window"));
	}

	/**
	 * This method is called when the window is released. It does nothing by
	 * default.
	 * 
	 * @param event
	 *            The mouse event generated when the window was released.
	 */
	protected void windowReleased(MouseEvent event) {
	}
}