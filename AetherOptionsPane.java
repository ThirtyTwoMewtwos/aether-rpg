/*
 * AetherOptionsPane.java
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
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;


public class AetherOptionsPane extends JDialog implements ActionListener
{
    private JButton export_char_pdf;

    private EventListenerList listenerList;

    public AetherOptionsPane()
    {
        listenerList = new EventListenerList();

        export_char_pdf = new JButton("Export Character to PDF");

        export_char_pdf.addActionListener(this);

        add(export_char_pdf);  
    }

    public void createAndShowGUI()
    {
        this.setTitle("Options");
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setMinimumSize(new Dimension(400, 250));
        this.setLocation(new Point(200,80));
        this.pack();
        this.setVisible(true);
    }

    public void addViewEventListener(ViewEventListener listener)
    {
        listenerList.add(ViewEventListener.class, listener);
    }

    private void fireViewEvent(Object source, String name)
    {
        
        ViewEvent event = new ViewEvent(source, name);
        
        ViewEventListener[] listeners = listenerList.getListeners(ViewEventListener.class);

        for (int i = 0; i < listeners.length; i++)
        {
            listeners[i].viewEventOccurred(event);
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == export_char_pdf)
        {
            fireViewEvent(export_char_pdf,"export_char_pdf");
        }
    }
}
