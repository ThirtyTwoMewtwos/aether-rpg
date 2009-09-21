package com.aether.present;

/*
 * VirtualWorld_2d.java
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

import static com.aether.present.ColorConstants.*;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.event.*;

public class VirtualWorld extends JPanel
{
    private final int pHEIGHT = 10;
    private final int pWIDTH = 20;
    private final int textHoverHeight = 4;

    private Point heroLocation;
    private EventListenerList requestListenerList;
    private String heroName;

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        /*
         *  Draw player
         */
        g.setColor(PLAYER_COLOR);
        g.drawString(getHeroName(), getHeroLocation().x - (pWIDTH / 2), getHeroLocation().y - textHoverHeight);
        g.draw3DRect(getHeroLocation().x, getHeroLocation().y, pHEIGHT, pWIDTH, true);
    }

    public VirtualWorld(Point heroLoc, String hName)
    {
        requestListenerList = new EventListenerList();
        setHeroName(hName);
        setHeroLocation(heroLoc);
        
        
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(780,465));
        this.setSize(this.getPreferredSize());
        this.setVisible(true);
        
        repaint();
    }
    public void setHeroName(String hName)
    {
        heroName = hName;
    }
    public String getHeroName()
    {
        return heroName;
    }
    public void setHeroLocation(Point heroLoc)
    {
        heroLocation = heroLoc;
    }
    public Point getHeroLocation()
    {
        return heroLocation;
    }

    public void addRequestEventListener(RequestEventListener listener)
    {
        requestListenerList.add(RequestEventListener.class, listener);
    }

    private void fireRequestEvent(Object source, String name)
    {
        RequestEvent event = new RequestEvent(source, name);

        RequestEventListener[] listeners = requestListenerList.getListeners(RequestEventListener.class);

        for (int i = 0; i < listeners.length; i++)
        {
            listeners[i].requestEventOccurred(event);
        }
    }
}
