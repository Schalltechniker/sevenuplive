package mtn.sevenuplive.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mtn.sevenuplive.modes.ModeConstants;
import mtn.sevenuplive.scales.Scale;

import org.jdom.Document;

public class SevenUpPanel extends JPanel implements ActionListener
{
	
	private static final long serialVersionUID = 1L;
	SevenUpApplet sevenUpApplet;
	JComboBox drpScaleChoices1;
	JComboBox drpScaleChoices2;
	JComboBox drpLoopChoke;
	JComboBox drpLoopLength;
	JComboBox drpLoopType;
	JComboBox drpGateLoopChoke;
	JComboBox drpMelRecMode;
	JButton btnPrevPatch;
	JButton btnNextPatch;
	JCheckBox chkMuteLooper;
	JFrame parentFrame;
	JPanel secondPanel;
	
	boolean isPatchPack = false;
	
	public boolean isPatchPack() {
		return isPatchPack;
	}

	public void setPatchPack(boolean isPatchPack) {
		this.isPatchPack = isPatchPack;
	}

	public SevenUpPanel(ConnectionSettings sevenUpConnections, JFrame _parent) 
    {
    	super();  
    	
    	parentFrame = _parent;
    	
    	// Resize a bit
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	parentFrame.setBounds(screenSize.width / 2,screenSize.height / 2,450,420);
 
    	try
    	{

    	initLayout();
    	}
    	catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    	
    	//Initialize Sevenup Applet
        sevenUpApplet = new SevenUpApplet(sevenUpConnections, this);
        
        super.add(sevenUpApplet);
        
    	sevenUpApplet.setVisible(false);
    	
    	this.addComponentListener(new ResizeListener());
    }
    
    private void initLayout()
    {
			//7up Gui
			
			FlowLayout layout = new FlowLayout();
			layout.setAlignment(FlowLayout.LEFT);
			setLayout(layout);
			
			GridBagLayout twocolumn = new GridBagLayout();
			GridBagLayout fourcolumn = new GridBagLayout();
			BorderLayout twocolumnstraight = new BorderLayout();
			
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.LAST_LINE_START;
			c.weighty = 0.5;
			c.gridx = 0;
			c.gridy = 0;
			
			JPanel firstPanel = new JPanel(twocolumn);
			secondPanel = new JPanel(fourcolumn);
			JPanel thirdPanel = new JPanel(twocolumnstraight);
			
			//Scale
	        JLabel lblSetScale = new JLabel("Melodizer 1 scale");
	        setSizeSmall(lblSetScale);
	        lblSetScale.setBorder(new javax.swing.border.EmptyBorder(4,4,4,4));
	        
	        Vector<String> scaleChoices = new Vector<String>();
	        scaleChoices.add("CLIP LAUNCH");
	        scaleChoices.add("Billian");
	        scaleChoices.add("Blues");
	        scaleChoices.add("Blues (Minor)");
	        scaleChoices.add("Dorian");
	        scaleChoices.add("Major");
	        scaleChoices.add("Minor");
	        scaleChoices.add("Minor Seven");
	        scaleChoices.add("Mullnixian");
	        scaleChoices.add("Yorkian");
	        scaleChoices.add("Pentatonic");
	        scaleChoices.add("Pentatonic (Minor)");
	        scaleChoices.add("Ultra Locrian");
	        
	        drpScaleChoices1 = new JComboBox(scaleChoices);
	        setSizeSmall(drpScaleChoices1);
	        drpScaleChoices1.setSelectedIndex(5);
	        
	        drpScaleChoices1.addActionListener(
	        		new ActionListener(){
	        			public void actionPerformed(ActionEvent e) {
	        				setDirty(true);
	        				JComboBox cb = (JComboBox)e.getSource();
	        				if(cb.getSelectedIndex() == 0)
	        				{
	        					sevenUpApplet.setMelody1Scale("Major");
	        					sevenUpApplet.setMelody1ClipMode(true);
	        				}
	        				else
	        				{
	        					sevenUpApplet.setMelody1Scale(cb.getSelectedItem().toString());
	        					sevenUpApplet.setMelody1ClipMode(false);
	        				}
	        			}
	        		}
	        );
			
			c.gridx = 0; c.gridy = 0; firstPanel.add(lblSetScale, c);
			c.gridx = 1; firstPanel.add(drpScaleChoices1, c);
	        
			//Melodizer 2 Scale
	        lblSetScale = new JLabel("Melodizer 2 scale");
	        setSizeSmall(lblSetScale);
	        lblSetScale.setBorder(new javax.swing.border.EmptyBorder(4,4,4,4));
	        
	        //Remove clip launch option
	        scaleChoices = new Vector<String>();
	        scaleChoices.add("Billian");
	        scaleChoices.add("Blues");
	        scaleChoices.add("Blues (Minor)");
	        scaleChoices.add("Dorian");
	        scaleChoices.add("Major");
	        scaleChoices.add("Minor");
	        scaleChoices.add("Minor Seven");
	        scaleChoices.add("Mullnixian");
	        scaleChoices.add("Yorkian");
	        scaleChoices.add("Pentatonic");
	        scaleChoices.add("Pentatonic (Minor)");
	        scaleChoices.add("Ultra Locrian");
	        
	        drpScaleChoices2 = new JComboBox(scaleChoices);
	        setSizeSmall(drpScaleChoices2);
	        drpScaleChoices2.setSelectedIndex(4);
	        
	        drpScaleChoices2.addActionListener(
	        		new ActionListener(){
	        			public void actionPerformed(ActionEvent e) {
	        				setDirty(true);
	        				JComboBox cb = (JComboBox)e.getSource();
	        					sevenUpApplet.setMelody2Scale(cb.getSelectedItem().toString());
	        					sevenUpApplet.setMelody2ClipMode(false);
	        			}
	        		}
	        );
	        
	        c.gridx = 0; c.gridy = 1; firstPanel.add(lblSetScale, c);
	        c.gridx = 1; firstPanel.add(drpScaleChoices2, c);	        
	        
	        //Melodizer record mode
	        JLabel lblMelRecMode = new JLabel("Melodizer Rec Mode");
	        setSizeSmall(lblMelRecMode);
	        lblMelRecMode.setBorder(new javax.swing.border.EmptyBorder(4,4,4,4));
	        Vector<String> recModeChoices = new Vector<String>();
	        recModeChoices.add("On Button Press");
	        recModeChoices.add("Quantized");
	        drpMelRecMode = new JComboBox(recModeChoices);
	        setSizeSmall(drpMelRecMode);
	        drpMelRecMode.addActionListener(
	        		new ActionListener(){
	        			public void actionPerformed(ActionEvent e) {
	        				setDirty(true);
	        				JComboBox cb = (JComboBox)e.getSource();
	        				if(cb.getSelectedItem().toString().equals("On Button Press"))
		        				sevenUpApplet.setMelRecMode(ModeConstants.MEL_ON_BUTTON_PRESS);
	        				else 
	        					sevenUpApplet.setMelRecMode(ModeConstants.MEL_QUANTIZED);
	        			}
	        		}
	        );
	        c.gridx = 0; c.gridy = 2; firstPanel.add(lblMelRecMode, c);
	        c.gridx = 1; firstPanel.add(drpMelRecMode, c);
	        
	        //Loops
	        JLabel lblSetLoopGate;
	        JLabel lblSetLoopLength;
	        JLabel lblSetLoopType;
	        
	        for(Integer i=0; i<7;i++)
	        {
	        	lblSetLoopGate = new JLabel("Loop " + (i+1) + "  Choke Group");
	        	setSizeSmall(lblSetLoopGate);
	            lblSetLoopGate.setBorder(new javax.swing.border.EmptyBorder(4,4,4,4));
	            String[] chokeChoices = { "Nope", "1", "2", "3", "4", "5", "6", "7"};
	            drpLoopChoke = new JComboBox(chokeChoices);
	            setSizeSmall(drpLoopChoke);
	            drpLoopChoke.setName(i.toString() + "choke");
	            drpLoopChoke.setSelectedIndex(0);
	            drpLoopChoke.addActionListener(
	            		new ActionListener(){
	            			public void actionPerformed(ActionEvent e) {
	            				setDirty(true);
	            				JComboBox cb = (JComboBox)e.getSource();
	            				if(cb.getSelectedIndex() == 0)
	            					sevenUpApplet.setLoopChokeGroup(Integer.parseInt(cb.getName().substring(0,1)), -1);
	            				else 
	            					sevenUpApplet.setLoopChokeGroup(Integer.parseInt(cb.getName().substring(0,1)), cb.getSelectedIndex());
	            			}
	            		}
	            );
	            // Loop length choices
	            lblSetLoopLength = new JLabel("Length");
	            setSizeSmall(lblSetLoopLength);
	            lblSetLoopLength.setBorder(new javax.swing.border.EmptyBorder(4,4,4,4));
	            String[] lengthChoices = { ".5", "1", "2", "4", "8", "16", "32"};
	            drpLoopLength = new JComboBox(lengthChoices);
	            setSizeSmall(drpLoopLength);
	            drpLoopLength.setName(i.toString()+"length");
	            drpLoopLength.setSelectedIndex(1);
	            drpLoopLength.addActionListener(
	            		new ActionListener() {
	            			public void actionPerformed(ActionEvent e) {
	            				setDirty(true);
	            				JComboBox cb = (JComboBox)e.getSource();
	            				Float loopLength = Float.parseFloat(cb.getSelectedItem().toString());
	            				Integer loopNum = Integer.parseInt(cb.getName().substring(0,1));
	            				if(loopLength > 0)
	            					sevenUpApplet.setLoopLength(loopNum, loopLength);
	            				else
	            					System.out.println("Invalid loopLength specified in ComboBox for loop: " + loopNum);
	            				
	            			}
	            		}
	            );
	            
	            // Loop type choices
	            lblSetLoopType = new JLabel("Type");
	            setSizeSmall(lblSetLoopType);
	            lblSetLoopType.setBorder(new javax.swing.border.EmptyBorder(4,4,4,4));
	            String[] typeChoices = { "Loop", "Shot", "Momentary", "Hit", "Slice"};
	            drpLoopType = new JComboBox(typeChoices);
	            setSizeSmall(drpLoopType);
	            drpLoopType.setName(i.toString()+"type");
	            drpLoopType.setSelectedIndex(0);
	            drpLoopType.addActionListener(
	            		new ActionListener(){
	            			public void actionPerformed(ActionEvent e) {
	            				setDirty(true);
	            				JComboBox cb = (JComboBox)e.getSource();
	            				sevenUpApplet.setLoopType(Integer.parseInt(cb.getName().substring(0,1)), cb.getSelectedIndex());
	            			}
	            		}
	            );
	            c.gridx = 0; c.gridy = 3 + i; secondPanel.add(lblSetLoopGate, c);
	            c.gridx = 1; secondPanel.add(drpLoopChoke, c);
	            c.gridx = 2; secondPanel.add(lblSetLoopLength, c);
	            c.gridx = 3; secondPanel.add(drpLoopLength, c);
	            c.gridx = 4; secondPanel.add(drpLoopType, c);
	        }
	        
	        JLabel lblGateLoopChoke = new JLabel("Gate choked loops?");
	        setSizeSmall(lblGateLoopChoke);
	        lblGateLoopChoke.setBorder(new javax.swing.border.EmptyBorder(4,4,4,4));
	    	
	        Vector<String> gateChoices = new Vector<String>();
	        gateChoices.add("Ok");
	        gateChoices.add("Nope");
	        drpGateLoopChoke = new JComboBox(gateChoices);
	        setSizeSmall(drpGateLoopChoke);
	        drpGateLoopChoke.addActionListener(
	        		new ActionListener(){
	        			public void actionPerformed(ActionEvent e) {
	        				setDirty(true);
	        				JComboBox cb = (JComboBox)e.getSource();
	        				sevenUpApplet.setGateLoopChokes(cb.getSelectedItem().toString().equals("Ok"));
	        			}
	        		}
	        );
	        c.gridx = 0; c.gridy = 3; firstPanel.add(lblGateLoopChoke, c);
	        c.gridx = 1; firstPanel.add(drpGateLoopChoke, c);
	        
	        //Add mute looper checkbox
	        JLabel lblMuteLooper = new JLabel("Mute Looper (to assign MIDI)");
	        setSizeSmall(lblMuteLooper);
	        lblMuteLooper.setBorder(new javax.swing.border.EmptyBorder(4,4,4,4));
	    	
	        chkMuteLooper = new JCheckBox();
	        setSizeSmall(chkMuteLooper);
	        chkMuteLooper.addActionListener(
	        		new ActionListener(){
	        			public void actionPerformed(ActionEvent e) {
	        				setDirty(true);
	        				JCheckBox chk = (JCheckBox)e.getSource();
	       					sevenUpApplet.setLooperMute(chk.isSelected());
	        			}
	        		}
	        );
	        
	        c.gridx = 0; c.gridy = 4; firstPanel.add(lblMuteLooper, c);
	        c.gridx = 1; firstPanel.add(chkMuteLooper, c);
	        
	        //Add prev/next patch buttons
	        btnPrevPatch = new JButton("<< Prev Patch");
	        btnPrevPatch.putClientProperty("JButton.buttonType", "gradient");
	        btnPrevPatch.addActionListener(this);
	        btnPrevPatch.setEnabled(false);
	        thirdPanel.add(btnPrevPatch, BorderLayout.WEST);
	        
	        btnNextPatch = new JButton("Next Patch >>");
	        btnNextPatch.putClientProperty("JButton.buttonType", "gradient");
	        btnNextPatch.addActionListener(this);
	        btnNextPatch.setEnabled(false);
	        thirdPanel.add(btnNextPatch, BorderLayout.EAST);
	        
	        add(firstPanel);
	        add(secondPanel);
	        add(thirdPanel);
	        
	        this.setPreferredSize(new Dimension(400, 200));
	        this.setMinimumSize(new Dimension(200, 200));
	        this.setMaximumSize(new Dimension(500, 300));
	}
    

    
    public boolean openSevenUpPatch(String patchPath)
    {
    	return sevenUpApplet.openSevenUpPatch(patchPath);
    }
    
    public Document toJDOMXMLDocument(String fileName)
    {
    	return sevenUpApplet.toJDOMXMLDocument(fileName);
    }
    
    public void loadJDOMXMLDocument(Document XMLDoc)
    {	
    	
    	isPatchPack = sevenUpApplet.loadJDOMXMLDocument(XMLDoc);

    	btnPrevPatch.setEnabled(false);
    	btnNextPatch.setEnabled(isPatchPack);

    	updateGui();
    }
    
    private void updateGui()
    {
    	int chokeGroup;
    	int loopType;
    	Float length;
    	boolean gateLoopChokes;
    	
    	//Update melody 1 gui based on patch settings
    	if(sevenUpApplet.getMelody1ClipMode())
    	{
    		//If clipMode is true then set the appropriate dropdown
    		drpScaleChoices1.setSelectedIndex(0);
    	}
    	else
    	{
    		Scale melodyScale = sevenUpApplet.getMelody1Scale();
    		for(int i = 0; i < drpScaleChoices1.getItemCount(); i++)
        	{
        		if(drpScaleChoices1.getItemAt(i).toString().equals(melodyScale.label.toString()))
        		{
        			drpScaleChoices1.setSelectedIndex(i);
        		}
        	}
    	}
    	
    	
    	//Update melody 2 gui based on patch settings
    	Scale melodyScale = sevenUpApplet.getMelody2Scale();
        for(int i = 0; i < drpScaleChoices2.getItemCount(); i++)
        {
        	if(drpScaleChoices2.getItemAt(i).toString().equals(melodyScale.label.toString()))
        		drpScaleChoices2.setSelectedIndex(i);
        }
        
    	for(Integer i =0;i<7;i++)
    	{
    		chokeGroup = sevenUpApplet.getLoopChokeGroup(i);
    		length = sevenUpApplet.getLoopLength(i);
    		loopType = sevenUpApplet.getLoopType(i);
    		if(chokeGroup == -1)chokeGroup = 0;
    		
    		for(int k=0; k<secondPanel.getComponentCount();k++)
    		{
    			
    			if(secondPanel.getComponent(k).getName() != null)
    			{
	    			if(secondPanel.getComponent(k).getName().equals(i.toString()+"length"))
	    			{
	    				drpLoopLength = (JComboBox)secondPanel.getComponent(k);
	    				if(length == .5)
	    					drpLoopLength.setSelectedIndex(0);
	    				else if(length == 1)
	    					drpLoopLength.setSelectedIndex(1);
	    				else if(length == 2)
	    					drpLoopLength.setSelectedIndex(2);
	    				else if(length == 4)
	    					drpLoopLength.setSelectedIndex(3);
	    				else if(length == 8)
	    					drpLoopLength.setSelectedIndex(4);
	    				else if(length == 16)
	    					drpLoopLength.setSelectedIndex(5);
	    				else if(length == 32)
	    					drpLoopLength.setSelectedIndex(6);
	    				
	    				drpLoopLength.setSelectedItem(length.toString());
	    			}   
	    			else if(secondPanel.getComponent(k).getName().equals(i.toString()+"choke"))
	    			{
	    				drpLoopChoke = (JComboBox)secondPanel.getComponent(k);
	    				drpLoopChoke.setSelectedIndex(chokeGroup);
	    			}
	    			else if(secondPanel.getComponent(k).getName().equals(i.toString()+"type"))
	    			{
	    				drpLoopType = (JComboBox)secondPanel.getComponent(k);
	    				drpLoopType.setSelectedIndex(loopType);
	    				drpLoopType.setSelectedItem(Integer.toString(loopType));
	    			}
    			}
			}
		}
    	
    	gateLoopChokes = sevenUpApplet.getGateLoopChokes();
    	if(gateLoopChokes)
    		drpGateLoopChoke.setSelectedIndex(0);
    	else
    		drpGateLoopChoke.setSelectedIndex(1);
    	
    	// Add star to title if patch is dirty, we clear it on save
    	setTitle(sevenUpApplet.isDirty() ? "*" + sevenUpApplet.getPatchTitle(): sevenUpApplet.getPatchTitle());
    }
    
    public void quit()
    {
    	sevenUpApplet.quit();
    }



	public void actionPerformed(ActionEvent e) {
		JButton curButton = (JButton) e.getSource();
		if(curButton.getText().equals("Next Patch >>"))
		{
			loadNextPatch();
		}
		else
		{	
			loadPrevPatch();
		}
	}
	
	public void loadNextPatch()
	{
		if(sevenUpApplet.getPatchPackSize() > 0)
		{
			btnNextPatch.setEnabled(sevenUpApplet.loadNextPatch() < sevenUpApplet.getPatchPackSize()-1);
			btnPrevPatch.setEnabled(true);
			updateGui();
		}
	}
	
	public void loadPrevPatch()
	{
		if(sevenUpApplet.getPatchPackSize() > 0)
		{
			btnPrevPatch.setEnabled(sevenUpApplet.loadPrevPatch() > 0);
			btnNextPatch.setEnabled(true);
			updateGui();
		}
	}
	
	private void setTitle(String title)
	{
		parentFrame.setTitle("7up - " + title);
	}

	public void setSizeSmall(JComponent component) {
		component.putClientProperty("JComponent.sizeVariant", "small");
	}
	
	class ResizeListener implements ComponentListener {
	    
		public void componentHidden(ComponentEvent e) {}

		public void componentMoved(ComponentEvent e) {}

		public void componentResized(ComponentEvent e) {
			//SevenUpPanel source = (SevenUpPanel)e.getSource();
			//System.out.println("new size width:" + source.getSize().width + " height:" + source.getSize().height);
		}

		public void componentShown(ComponentEvent e) {}
	}
	
	public void setDirty(boolean dirty) {
		sevenUpApplet.setDirty(dirty);
		
		// Add star to title if patch is dirty, we clear it on save
    	setTitle(sevenUpApplet.isDirty() ? "*" + sevenUpApplet.getPatchTitle(): sevenUpApplet.getPatchTitle());
	}

	public boolean isDirty() {
		return sevenUpApplet.isDirty();
	}


	
}
