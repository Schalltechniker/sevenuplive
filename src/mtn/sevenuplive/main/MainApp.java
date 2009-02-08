/**
 * SevenUp for Monome's 40h
 * @author Adam Ribaudo
 * arribaud@gmail.com
 * 12/09/2007
 * Copyright 2007 - All rights reserved
 */

package mtn.sevenuplive.main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class MainApp extends JFrame
                               implements ActionListener
{
	private static final long serialVersionUID = -5398755465771631845L;
	
	/** Property were we store last used directory */
	private final static String LAST_DIRECTORY_PROP_NAME = "last.directory"; 
	
	/** Last directory read or stored to */
	File lastDirectory;
	
	JMenu menu;
	SevenUpPanel sevenUpPanel;
	JFileChooser fc;
	JMenu recentsMenu;
	
	/** Application settings */
	private static Properties properties = new Properties(); 
	
	LinkedList<String> recentFiles = new LinkedList<String>();

	static final String SEVENUP_PROPERTIES_COMMENTS = "SevenUpLive startup parameters";

	static final String SEVENUP_PROPERTIES = "sevenup.properties";
	
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
    	menu.getItem(2).setEnabled(true);
    	
    	// Now we can display recent files
    	loadRecentFileList();
    	
    	// This also updates the menu
    	this.storeAndUpdateRecents();     
    }

    protected JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        //Set up the menu.
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_D);
        menuBar.add(menu);

        JMenuItem menuItem;
        JMenuItem subMenuItem;
        
        //Open menu item
        menuItem = new JMenuItem("Open 7up Patch...");
        menuItem.setActionCommand("open");
        menuItem.setEnabled(false);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //Create recents menu item..it gets populated later
        recentsMenu = new JMenu("Open Recent");
        recentsMenu.setEnabled(false);
        menu.add(recentsMenu);
        
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
    	// If command in format "recent.x" then we are processing recent file list
    	if (e.getActionCommand().split("\\.").length > 1) {
    		String[] parts = e.getActionCommand().split("\\.");
    		if (parts[0].equalsIgnoreCase("recent")) {
    			int index = Integer.parseInt(parts[1]);
    			java.io.File file = new File(recentFiles.get(index));
    			if (!file.exists())
    				return; // do nothing
    			
    			loadPatch(file); // load the patch
    		}
    	} else if ("open".equals(e.getActionCommand())) {
    		int returnVal = fc.showOpenDialog(this);
    		if (returnVal == JFileChooser.APPROVE_OPTION) {
               java.io.File file = fc.getSelectedFile();
               loadPatch(file); // load the patch
            }	
        } else if ("saveas".equals(e.getActionCommand())) {
        	
        	java.io.FileWriter fileWriter = null;
        	
        	try {
        		int returnVal = fc.showSaveDialog(this);
        		if (returnVal == JFileChooser.APPROVE_OPTION) {
        			java.io.File file = fc.getSelectedFile();
        			
                    org.jdom.Document doc = sevenUpPanel.toJDOMXMLDocument(file.getName());
                    org.jdom.output.XMLOutputter fmt = new XMLOutputter();
                    
        			fileWriter = new FileWriter(file);
        			fmt.output(doc, fileWriter);
        			lastDirectory = new File(file.getParent());
        			fc.setCurrentDirectory(lastDirectory);
        			addToRecents(file);
        		}
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally { // standard java closing resources in finally
            	if (fileWriter != null)
					try {
						fileWriter.close();
					} catch (IOException e1) {
						// drop it
					}
            }
            
        } else if ("quit".equals(e.getActionCommand())) {
        	if (sevenUpPanel != null)
        		sevenUpPanel.quit();
        	 System.exit(0);
        } 
    }
    
    /**
     * Load up the patch or patch pack
     * must exist
     * @param file
     */
    private void loadPatch(File file) {
    	SAXBuilder builder = new SAXBuilder();
        try
        {
     	   Document doc = builder.build(file);
     	   sevenUpPanel.loadJDOMXMLDocument(doc);
           fc.setCurrentDirectory(new File(file.getParent()));
           addToRecents(file);
        }
        catch(Exception ex)
        {
     	   ex.printStackTrace();   
        }
    }
    
    /**
     * Add a file to the recents list
     * cull the list of duplicates 
     * @param recent
     */
    private void addToRecents(File recent) {
    	recentFiles.addFirst(recent.getAbsolutePath());
    	
    	boolean culled = false;
    	boolean found = false;
    	
    	// Keep iterating until culled
    	while (!culled) {
    		String head = recentFiles.peek();
        	int index = 0;
        	
	    	for (String name : recentFiles) {
	    		if (index == 0) {
	    			index++;
	    			continue;
	    		}
	    		if (name.equals(head)) {
	    			recentFiles.remove(index);
	    			found = true;
	    			break; // go back to while
	    		}
	    		index++;
	    	}
	    	// When we make it here..we are culled..since nothing was matched anymore
	    	if (!found) 
	    		culled = true;
	    	
	    	found = false;
    	} 
    	// Make sure we store it
    	storeAndUpdateRecents();
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
    	
    
    	// Make OS X look better
		String lcOSName = System.getProperty("os.name").toLowerCase();
		boolean mac = lcOSName.startsWith("mac os x");
		
		// If it's a Mac then let's do a few things to make it look better
		if (mac) {
			
			// take the menu bar off the jframe
			System.setProperty("apple.laf.useScreenMenuBar", "true");
			
			// set the name of the application menu item
			System.setProperty("com.apple.mrj.application.apple.menu.about.name", "SevenUpLive");
			
			// a border is added to the bottom of the JFrame so that the grow box can't sit on top of any components contained in the JFrame
			System.setProperty("com.apple.mrj.application.growbox.intrudes", "false");
			
		}
		
	
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    /**
     * Load the recent file list from sevenup.properties
     * Also load last directory used
     */
    private void loadRecentFileList() {
    	Properties props = null;
    	
    	try {
			props = MainApp.readProperties();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
		Integer index = 10;
		
		if (props != null) {
			while (--index >= 0) {
				String name = props.getProperty("recent." + index);
				if (name != null && name.length() > 0) {
					File file = new File(name);
					if (file.exists())
						recentFiles.add(name);
				}
			}
		}
		
		lastDirectory = props.getProperty(LAST_DIRECTORY_PROP_NAME) == null ? null : new File(props.getProperty(LAST_DIRECTORY_PROP_NAME));
    }
    	
    /**
     * Store the recents list
     * Also update the JMenu
     * 
     */
    private void storeAndUpdateRecents() {
    	Properties props = null;
    	recentsMenu.removeAll();
    	JMenuItem subMenuItem = null;
    	
    	try {
			props = MainApp.readProperties();
			
			// Store the last directory we used as well
			if (lastDirectory != null && lastDirectory.exists()) {
				props.setProperty(LAST_DIRECTORY_PROP_NAME, lastDirectory.getAbsolutePath());
			}
		
			Integer index = 0;
			String propnm;
			for (String name : recentFiles) {
				propnm = "recent." + index;
				
				props.setProperty(propnm, name);
				
				// Populate the menu
				subMenuItem = new JMenuItem(name);
		        subMenuItem.setActionCommand("recent." + index);
		        subMenuItem.setEnabled(true);
		        subMenuItem.addActionListener(this);
		        recentsMenu.add(subMenuItem);
		        recentsMenu.setEnabled(true);
				
				index++;
			}	
			recentsMenu.invalidate(); // refresh
			
			// Store them back
			MainApp.writeProperties();
			
    	} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    }
    
    /**
     * Read sevenup.properties and store with this class
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static Properties readProperties() throws FileNotFoundException, IOException {
		File propFile = new File(MainApp.SEVENUP_PROPERTIES);
		
		// Only created if not exists
		propFile.createNewFile();
		
		Properties props = new Properties();
		FileInputStream fs = null;
		
		try {
			fs= new FileInputStream(propFile);
			props.load(fs);
		} finally {
			fs.close();
		}
		setProperties(props);
		return props;
	}
	
	/**
	 * Write sevenup.properties and store with this class
	 * @param props
	 * @throws IOException
	 */
	public static void writeProperties() throws IOException {
		File propFile = new File(MainApp.SEVENUP_PROPERTIES);
		
		// Only created if not exists
		propFile.createNewFile();
		FileOutputStream fs = null;
		try {
			fs = new FileOutputStream(propFile);		
			properties.store(fs, MainApp.SEVENUP_PROPERTIES_COMMENTS);
		} finally {	
			fs.close();
		}
	}
	
	public static Properties getProperties() {
		return properties;
	}

	public static void setProperties(Properties properties) {
		MainApp.properties = properties;
	}

}
