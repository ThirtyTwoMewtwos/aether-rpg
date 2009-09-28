package com.aether.present;

/*
 * AetherTest.java
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

import com.aether.model.Hero;
import java.io.*;
import javax.swing.*;
import java.awt.Point;

public class Start implements ViewEventListener,MoveEventListener
{
    private FileOutputStream file;
    private ObjectOutputStream output;
    
    private HeroCreationUI hc;
    private Hero hero;
    
    private UI ui;
    
    private PersonaPane persona_pane;
    private OptionsPane options_pane;
    
    private VirtualWorld virtualWorld;
    
    public Start()
    {
        hc = new HeroCreationUI();
        hero = hc.getCreatedHero();
        if(hc.heroCreated())
        {
            setupOutputTools();
            saveHero();
            
            virtualWorld = new VirtualWorld(hero.getLocation(),hero.getName());

            ui = new UI(virtualWorld);
            ui.addViewEventListener(this);
            
            options_pane = new OptionsPane();
            persona_pane = new PersonaPane(hero.toString());
            
            options_pane.addViewEventListener(this);
            persona_pane.addViewEventListener(this);
        }
    }
    
    public static void main(String args[])
    {
        new Start();

    }
    public void requestEventOccurred(RequestEvent event)
    {

    }
    public void moveEventOccurred(MoveEvent event)
    {
        if(event.toString().equals("North"))
        {
            hero.setLocation(new Point(hero.getLocation().x,hero.getLocation().y - Hero.MOVE_MODIFIER));
        }
        else if(event.toString().equals("South"))
        {
            hero.setLocation(new Point(hero.getLocation().x,hero.getLocation().y + Hero.MOVE_MODIFIER));
        }
        else if(event.toString().equals("East"))
        {
            hero.setLocation(new Point(hero.getLocation().x + Hero.MOVE_MODIFIER,hero.getLocation().y));
        }
        else if(event.toString().equals("West"))
        {
            hero.setLocation(new Point(hero.getLocation().x - Hero.MOVE_MODIFIER,hero.getLocation().y));
        }

        ui.repaintVirtualWorld(hero.getLocation());
    }
    public void viewEventOccurred(ViewEvent event)
    {
        if(event.toString().equals("view_panel"))
        {
            
        }
        if(event.toString().equals("btn_camp"))
        {

        }
        else if(event.toString().equals("btn_options"))
        {
            options_pane.createAndShowGUI();
        }
        else if(event.toString().equals("btn_slaves"))
        {

        }
        else if(event.toString().equals("btn_persona"))
        {
            persona_pane.createAndShowGUI();
        }
        else if(event.toString().equals("btn_abilities"))
        {

        }
        else if(event.toString().equals("btn_inventory"))
        {

        }
        else if(event.toString().equals("btn_map"))
        {

        }
        else if(event.toString().equals("btn_save_exit"))
        {
            saveAndExit();
        }
        else if(event.toString().equals("export_char_pdf"))
        {
            exportHeroToPDF();
        }
        else if(event.toString().equals("btn_save_bio"))
        {
            updateBio();
        }
    }
    
    private void updateBio()
    {
        hero.setBio(persona_pane.getUpdatedBio());
        persona_pane.killWindow();
        persona_pane = new PersonaPane(hero.toString());
        persona_pane.createAndShowGUI();
    }

    private void saveAndExit()
    {
        if(saveHero())
        {
            System.exit(0);
        }
        else
        {
            String[] options = {"Exit Without Saving","Cancel Exit"};
            int option = JOptionPane.showOptionDialog(null, "ERROR!", "Unable to save character!",JOptionPane.YES_NO_OPTION , JOptionPane.ERROR_MESSAGE, null, options, options[1]);

            if(option == 0)
                System.exit(1);
        }
    }

    private void exportHeroToPDF()
    {
        hero.exportCharToPDF();
    }

    private void setupOutputTools()
    {
        try{
            file = new FileOutputStream( hero.getName() + ".ser" );
            output = new ObjectOutputStream( file );

        }catch(IOException ioe)
        {
            System.err.println(ioe.getMessage() + " in setup.");
        }
      
    }
    
    public boolean saveHero()
    {
        boolean success = false;

        try{
            output.writeObject(hero);
            success = true;
        }catch(Exception e)
        {
            System.err.println(e.getMessage() + " in save.");
        }
        
        return success;
    }
}
