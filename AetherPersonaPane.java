/*
 * AetherPersonaPane.java
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

public class AetherPersonaPane extends JFrame implements ActionListener
{
    private EventListenerList listenerList;
    
    private JPanel bio_controls;

    private JButton btn_cancel;
    private JButton btn_save_bio;

    private JTextArea txt_persona;
    private JTextArea txt_edit_bio;

    private JScrollPane bio_scroll_pane;


    public AetherPersonaPane(String persona)
    {
        listenerList = new EventListenerList();

        bio_controls = new JPanel(new FlowLayout());

        btn_cancel = new JButton("Cancel");
        btn_save_bio = new JButton("Save Changes");

        txt_persona = new JTextArea(persona);
        txt_edit_bio = new JTextArea(5,20);

        txt_edit_bio.setWrapStyleWord(true);
        txt_edit_bio.setLineWrap(true);

        bio_scroll_pane = new JScrollPane(txt_edit_bio);
        bio_scroll_pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        txt_persona.setEditable(false);
        txt_persona.setEnabled(false);

        bio_controls.add(txt_edit_bio);
        bio_controls.add(btn_save_bio);
        bio_controls.add(btn_cancel);

        add(txt_persona,BorderLayout.CENTER);
        add(bio_controls, BorderLayout.SOUTH);

        createAndShowGUI();
    }

    public void createAndShowGUI()
    {
        this.setTitle("Persona");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setMinimumSize(new Dimension(250, 600));
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

    }
}
