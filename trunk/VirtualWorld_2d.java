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

import javax.swing.*;
import java.awt.*;

public class VirtualWorld_2d extends JPanel
{
    private final Color AGRO_ENEMY_COLOR = Color.red;
    private final Color NON_AGRO_ENEMY_COLOR = Color.yellow;
    private final Color PLAYER_COLOR = Color.blue;
    private final Color NPC_COLOR = Color.white;
    private final Color GUILD_COLOR = Color.green;
    private final Color GROUP_COLOR = Color.cyan;
    private final Color OBJECT_COLOR = Color.lightGray;

    private Point heroLocation;

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        g.setColor(Color.black);
        g.draw3DRect(heroLocation.x, heroLocation.y, 20, 35,true);
    }

    public VirtualWorld_2d(Point heroLoc)
    {
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(780,465));
        this.setSize(this.getPreferredSize());
        this.setVisible(true);
        setHeroLocation(heroLoc);
        repaint();
    }
    public void setHeroLocation(Point heroLoc)
    {
        heroLocation = heroLoc;
    }
}
