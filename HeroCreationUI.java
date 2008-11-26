/*
 * HeroCreationUI.java
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

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class HeroCreationUI extends JDialog implements ActionListener, ItemListener
{
    private final static String[] SEX_CHOICES = { "Male","Female" };
    private final static String[] RACE_CHOICES = { "Revenant", "Human" };
    private final static String[] EVIL_CLASS_CHOICES = { "Heretic (healer)",
                                             "Dark Guard (tank)",
                                             "Necromancer (magic dps)",
                                             "Berserker (melee dps)" };
    private final static String[] GOOD_CLASS_CHOICES = { "Sage (healer)",
                                                         "Holy Knight (tank)",
                                                         "Acolyte (magic dps)",
                                                         "Crusader (melee dps)" };
    
    private JPanel windowPane;
    private JPanel imagePane;
    private JPanel inputPane;
    private JPanel buttonsPane;
    private ImageIcon icon;
    private JButton create;
    private JButton cancel;
    private JLabel nameLabel;
    private JLabel bioLabel;
    private JLabel sexLabel;
    private JLabel raceLabel;
    private JLabel classLabel;
    private JTextField nameField;
    private JComboBox sexDropDown;
    private JComboBox raceDropDown;
    private JComboBox classDropDown;
    private JTextArea bioField;
    private JScrollPane scrollPane;
     
    private Hero hero;
    private boolean created;
    
    public HeroCreationUI()
    {
        created = false;
        
        windowPane = new JPanel(new BorderLayout());
        imagePane = new JPanel();
        inputPane = new JPanel(new GridLayout(5,2));
        buttonsPane = new JPanel();
        
        icon = new ImageIcon("logo.jpg");
        create = new JButton("Create");
        cancel = new JButton("Cancel");
        nameLabel = new JLabel("Hero Name:");
        sexLabel = new JLabel("Sex:");
        raceLabel = new JLabel("Race:");
        classLabel = new JLabel("Class:");
        bioLabel = new JLabel("Biography:");
        nameField = new JTextField(20);
        sexDropDown = new JComboBox(SEX_CHOICES);
        raceDropDown = new JComboBox(RACE_CHOICES);
        classDropDown = new JComboBox(EVIL_CLASS_CHOICES);
        bioField = new JTextArea(5,20);
        scrollPane = new JScrollPane(bioField);
        
        bioField.setWrapStyleWord(true);
        bioField.setLineWrap(true);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        imagePane.add(new JLabel(icon));
        
        inputPane.add(nameLabel);
        inputPane.add(nameField);
        inputPane.add(sexLabel);
        inputPane.add(sexDropDown);
        inputPane.add(raceLabel);
        inputPane.add(raceDropDown);
        inputPane.add(classLabel);
        inputPane.add(classDropDown);
        inputPane.add(bioLabel);
        inputPane.add(scrollPane);
        
        buttonsPane.add(create);
        buttonsPane.add(cancel);
        
        windowPane.add(imagePane,BorderLayout.NORTH);
        windowPane.add(inputPane,BorderLayout.CENTER);
        windowPane.add(buttonsPane,BorderLayout.SOUTH);
        
        create.addActionListener(this);
        cancel.addActionListener(this);
        
        raceDropDown.addItemListener(this);
        
        add(windowPane);
        
        setTitle("Hero Creation");
        setModal(true);
        setLocation(100,100);
        setMinimumSize(new Dimension(375,375));
        setMaximumSize(new Dimension(375,375));
        setResizable(false);
        setVisible(true);
    }
    public void itemStateChanged(ItemEvent e)
    {
        String race = "" + raceDropDown.getSelectedItem();
        if(race.equals("Revenant"))
        {
            classDropDown.removeAllItems();
            for(int i = 0;i < EVIL_CLASS_CHOICES.length;i++)
                classDropDown.addItem(EVIL_CLASS_CHOICES[i]);
        }
        else
        {
            classDropDown.removeAllItems();
            for(int i = 0;i < GOOD_CLASS_CHOICES.length;i++)
                classDropDown.addItem(GOOD_CLASS_CHOICES[i]);
        }
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == create)
        {
            HeroValidate hv = new HeroValidate(nameField.getText());
            
            if(hv.validateName())
            {
                String formattedName = nameField.getText();
                formattedName = formattedName.trim();
                formattedName = formattedName.toLowerCase();
                formattedName = formattedName.substring(0, 1).toUpperCase() + formattedName.substring(1,formattedName.length());
                nameField.setText(formattedName);
                
                String formattedClass = classDropDown.getSelectedItem() + "";
                formattedClass = formattedClass.substring(0, formattedClass.indexOf(" ("));
                if(formattedClass.indexOf(" ") != -1)
                    formattedClass = formattedClass.substring(0,formattedClass.indexOf(" ")) + formattedClass.substring(formattedClass.indexOf(" ")+ 1, formattedClass.length());
                Hero.Class hClass = Hero.Class.valueOf(formattedClass.toUpperCase());
                hero = new Hero(formattedName, bioField.getText(),"" + sexDropDown.getSelectedItem(),hClass);
                created = true;
            }
            else
                JOptionPane.showMessageDialog(null,"Name can not contain spaces or characters outside of A-Z. Name must be at least 3 characters long and less than 12","Invalid Name",JOptionPane.ERROR_MESSAGE);
        }
            setVisible(false);
        
    }
    public boolean heroCreated()
    {
        return created;
    }
    public Hero getCreatedHero()
    {
        return hero;
    }
}
