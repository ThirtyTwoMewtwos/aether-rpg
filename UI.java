/*
 * UI.java
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
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class UI extends JFrame implements ActionListener, KeyListener
{
    private JPanel windowPane = new JPanel();
    private JPanel gameWindow = new JPanel();
    private JPanel controlsWindow = new JPanel();
    
    private JTextArea output = new JTextArea(5,25);
    private Hero hero;
    
    public UI(Hero hero)
    {
        this.hero = hero;
        
        windowPane.setLayout(new BorderLayout());
        
        output.setEnabled(false);
        controlsWindow.add(output);
        
        gameWindow.setMinimumSize(new Dimension(600,600));
        controlsWindow.setMinimumSize(new Dimension(600,150));
        
        windowPane.add(gameWindow, BorderLayout.CENTER);
        windowPane.add(controlsWindow, BorderLayout.SOUTH);
        add(windowPane);
        
        setMinimumSize(new Dimension(600,750));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(getMinimumSize());
        setTitle("Aether");
        pack();
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        
    }
    public void keyReleased(KeyEvent e)
    {
        
    }   
    public void keyPressed(KeyEvent e)
    {
        
    }   
    public void keyTyped(KeyEvent e)
    {
        
    }
}
