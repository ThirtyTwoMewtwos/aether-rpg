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

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;

public class UI extends JPanel implements ActionListener, KeyListener
{
    private JPanel control_panel;
    private JPanel view_panel;
    private JPanel general_text_panel;
    private JPanel private_text_panel;
    private JPanel debug_text_panel;
    private JPanel bottom_panel;

    private JTabbedPane tabbed_pane;

    private JScrollPane gen_chat_scroll_pane;
    private JScrollPane pri_chat_scroll_pane;
    private JScrollPane deb_chat_scroll_pane;
    private JTextArea general_chat_box;
    private JTextArea private_chat_box;
    private JTextArea debug_chat_box;

    private JButton btn_slaves;
    private JButton btn_abilities;
    private JButton btn_persona;
    private JButton btn_inventory;
    private JButton btn_map;
    private JButton btn_camp;
    private JButton btn_options;
    private JButton btn_save_exit;
    

    public UI()
    {
        initGUIComponents();
    }

    private void initGUIComponents()
    {
        control_panel = new JPanel(new GridLayout(4,2));
        general_text_panel = new JPanel(new FlowLayout());
        private_text_panel = new JPanel(new FlowLayout());
        debug_text_panel = new JPanel(new FlowLayout());
        view_panel = new JPanel(new FlowLayout());
        bottom_panel = new JPanel(new FlowLayout());

        general_chat_box = new JTextArea(5,25);
        general_chat_box.setOpaque(false);
        general_chat_box.setWrapStyleWord(true);
        general_chat_box.setLineWrap(true);
        general_chat_box.setTabSize(3);
        general_chat_box.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),"Global",TitledBorder.CENTER,TitledBorder.ABOVE_TOP));

        private_chat_box = new JTextArea(5,25);
        private_chat_box.setOpaque(false);
        private_chat_box.setWrapStyleWord(true);
        private_chat_box.setLineWrap(true);
        private_chat_box.setTabSize(3);
        private_chat_box.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),"Tells",TitledBorder.CENTER,TitledBorder.ABOVE_TOP));

        debug_chat_box = new JTextArea(5,25);
        debug_chat_box.setOpaque(false);
        debug_chat_box.setWrapStyleWord(true);
        debug_chat_box.setLineWrap(true);
        debug_chat_box.setTabSize(3);
        debug_chat_box.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),"Debug",TitledBorder.CENTER,TitledBorder.ABOVE_TOP));

        gen_chat_scroll_pane = new JScrollPane(general_chat_box);
        gen_chat_scroll_pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        gen_chat_scroll_pane.setOpaque(false);

        pri_chat_scroll_pane = new JScrollPane(private_chat_box);
        pri_chat_scroll_pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pri_chat_scroll_pane.setOpaque(false);

        deb_chat_scroll_pane = new JScrollPane(debug_chat_box);
        deb_chat_scroll_pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        deb_chat_scroll_pane.setOpaque(false);

        btn_slaves = new JButton("Slaves");
        btn_abilities = new JButton("Abilites");
        btn_persona = new JButton("Persona");
        btn_inventory = new JButton("Inventory");
        btn_map = new JButton("Map");
        btn_camp = new JButton("Camp");
        btn_options = new JButton("Options");
        btn_save_exit = new JButton("Save/Exit");

        btn_slaves.addActionListener(this);
        btn_abilities.addActionListener(this);
        btn_persona.addActionListener(this);
        btn_inventory.addActionListener(this);
        btn_map.addActionListener(this);
        btn_camp.addActionListener(this);
        btn_options.addActionListener(this);
        btn_save_exit.addActionListener(this);

        control_panel.add(btn_persona);
        control_panel.add(btn_abilities);
        control_panel.add(btn_inventory);
        control_panel.add(btn_slaves);
        control_panel.add(btn_map);
        control_panel.add(btn_camp);
        control_panel.add(btn_options);
        control_panel.add(btn_save_exit);

        general_text_panel.add(gen_chat_scroll_pane);
        private_text_panel.add(pri_chat_scroll_pane);
        debug_text_panel.add(deb_chat_scroll_pane);

        tabbed_pane = new JTabbedPane();
        tabbed_pane.setSize(150, 50);
        tabbed_pane.addTab("Global", null, general_text_panel, "Global Broadcast");
        tabbed_pane.addTab("Tells", null, private_text_panel, "Private Tells");
        tabbed_pane.addTab("Debug", null, debug_text_panel, "Debug Messages");

        bottom_panel.add(control_panel);
        bottom_panel.add(tabbed_pane);

        setLayout(new BorderLayout());
        add(view_panel,BorderLayout.CENTER);
        add(bottom_panel,BorderLayout.SOUTH);

    }

    public void sendMessageToGeneral(String msg)
    {
        general_chat_box.append(msg + "\n");
    }

    public void sendMessageToPrivate(String msg)
    {
        private_chat_box.append(msg + "\n");
    }

    public void sendMessageToDebug(String msg)
    {
        debug_chat_box.append(msg + "\n");
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

    public static void createAndShowGUI()
    {
        JFrame f = new JFrame("CODE NAME: SAPHIRE");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new UI());
        f.setPreferredSize(new Dimension(800,650));
        f.setLocation(new Point(150,50));
        f.pack();
        f.setVisible(true);
    }
}
