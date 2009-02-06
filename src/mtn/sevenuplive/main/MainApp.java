/**
 * SevenUp for Monome's 40h
 * @author Adam Ribaudo
 * arribaud@gmail.com
 * 12/09/2007
 * Copyright 2007 - All rights reserved
 */

package mtn.sevenuplive.main;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;

public class MainApp extends JFrame
                               implements ActionListener
{
	private static final long serialVersionUID = -5398755465771631845L;
	
	JMenu menu;
	SevenUpPanel sevenUpPanel;
	JFileChooser fc;
	
	//Sevenup Properties
	public MainApp() {
        super("7up");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(screenSize.width / 2,screenSize.height / 2,350,300);
 
        fc = new JFileChooser();

        setJMenuBar(createMenuBar());
        this.setContentPane(new ConnectionPanel(this));
    }
    
    public void ShowSevenUp(ConnectionSettings sevenUpConnections)
    {
    	sevenUpPanel = new SevenUpPanel(sevenUpConnections, this);
    	this.setContentPane(sevenUpPanel);
    	//Enable load and save
    	menu.getItem(0).setEnabled(true);
    	menu.getItem(1).setEnabled(true);
    }

    protected JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        //Set up the menu.
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_D);
        menuBar.add(menu);

        JMenuItem menuItem;
        
        //Open menu item
        menuItem = new JMenuItem("Open 7up Patch...");
        menuItem.setActionCommand("open");
        menuItem.setEnabled(false);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //Save patch menu item
        menuItem = new JMenuItem("Save Patch as...");
        menuItem.setActionCommand("saveas");
        menuItem.setEnabled(false);
        menuItem.addActionListener(this);
        menu.add(menuItem);

        //Quit menu item
        menuItem = new JMenuItem("Quit");
        menuItem.setMnemonic(KeyEvent.VK_Q);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("quit");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        return menuBar;
    }

    //React to menu selections.
    public void actionPerformed(ActionEvent e) {
    	if ("open".equals(e.getActionCommand())) {
    		int returnVal = fc.showOpenDialog(this);
    		if (returnVal == JFileChooser.APPROVE_OPTION) {
               java.io.File file = fc.getSelectedFile();
               SAXBuilder builder = new SAXBuilder();
               try
               {
            	   Document doc = builder.build(file);
            	   sevenUpPanel.loadJDOMXMLDocument(doc);
                   //this.setTitle("7up - " + file.getName());
                   fc.setCurrentDirectory(new File(file.getParent()));
               }
               catch(Exception ex)
               {
            	   ex.printStackTrace();   
               }
            }	
        }
          else if ("saveas".equals(e.getActionCommand())) {
            try {
        		int returnVal = fc.showSaveDialog(this);
        		if (returnVal == JFileChooser.APPROVE_OPTION) {
        			java.io.File file = fc.getSelectedFile();
        			
                    org.jdom.Document doc = sevenUpPanel.toJDOMXMLDocument(file.getName());
                    org.jdom.output.XMLOutputter fmt = new XMLOutputter();
                    
        			java.io.FileWriter fileWriter = new FileWriter(file);
        			fmt.output(doc, fileWriter);
        			fileWriter.close();
        			fc.setCurrentDirectory(new File(file.getParent()));
        		
        		}
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
        } else if ("quit".equals(e.getActionCommand())) {
        	 sevenUpPanel.quit();
        	 System.exit(0);
        } 
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        MainApp frame = new MainApp();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Display the window.
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
