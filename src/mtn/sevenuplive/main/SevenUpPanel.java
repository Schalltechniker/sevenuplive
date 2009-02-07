package mtn.sevenuplive.main;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import mtn.sevenuplive.scales.Scale;
import org.jdom.*;
import java.util.*;

public class SevenUpPanel extends JPanel implements ActionListener
						  {
	
	private static final long serialVersionUID = 1L;
	SevenUpApplet sevenUpApplet;
	JComboBox drpScaleChoices1;
	JComboBox drpScaleChoices2;
	JComboBox drpLoopChoke;
	JComboBox drpGateLoopChoke;
	JComboBox drpMelRecMode;
	JButton btnPrevPatch;
	JButton btnNextPatch;
	JCheckBox chkMuteLooper;
	JFrame parentFrame;
	
    public SevenUpPanel(ConnectionSettings sevenUpConnections, JFrame _parent) 
    {
    	super(new GridLayout(0,2));  //X rows, 2 columns
    	//7up Gui
    	parentFrame = _parent;
    	
        //Scale
        JLabel lblSetScale = new JLabel("Melodizer 1 scale");
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
        scaleChoices.add("Pentatonic");
        scaleChoices.add("Pentatonic (Minor)");
        scaleChoices.add("Ultra Locrian");
        
        drpScaleChoices1 = new JComboBox(scaleChoices);
        drpScaleChoices1.setSelectedIndex(5);
        
        drpScaleChoices1.addActionListener(
        		new ActionListener(){
        			public void actionPerformed(ActionEvent e) {
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
        
        add(lblSetScale);
        add(drpScaleChoices1);
        
        //Melodizer 2 Scale
        lblSetScale = new JLabel("Melodizer 2 scale");
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
        scaleChoices.add("Pentatonic");
        scaleChoices.add("Pentatonic (Minor)");
        scaleChoices.add("Ultra Locrian");
        
        drpScaleChoices2 = new JComboBox(scaleChoices);
        drpScaleChoices2.setSelectedIndex(4);
        
        drpScaleChoices2.addActionListener(
        		new ActionListener(){
        			public void actionPerformed(ActionEvent e) {
        				JComboBox cb = (JComboBox)e.getSource();
        					sevenUpApplet.setMelody2Scale(cb.getSelectedItem().toString());
        					sevenUpApplet.setMelody2ClipMode(false);
        			}
        		}
        );
        
        add(lblSetScale);
        add(drpScaleChoices2);
        
        //Melodizer record mode
        JLabel lblMelRecMode = new JLabel("Melodizer Rec Mode");
        lblMelRecMode.setBorder(new javax.swing.border.EmptyBorder(4,4,4,4));
        Vector<String> recModeChoices = new Vector<String>();
        recModeChoices.add("On Button Press");
        recModeChoices.add("Quantized");
        drpMelRecMode = new JComboBox(recModeChoices);
        drpMelRecMode.addActionListener(
        		new ActionListener(){
        			public void actionPerformed(ActionEvent e) {
        				JComboBox cb = (JComboBox)e.getSource();
        				if(cb.getSelectedItem().toString().equals("On Button Press"))
	        				sevenUpApplet.setMelRecMode(MonomeUp.MEL_ON_BUTTON_PRESS);
        				else 
        					sevenUpApplet.setMelRecMode(MonomeUp.MEL_QUANTIZED);
        			}
        		}
        );
        add(lblMelRecMode);
        add(drpMelRecMode);
        
        //Loop gate
        JLabel lblSetLoopGate;
        for(Integer i=0; i<7;i++)
        {
        	lblSetLoopGate = new JLabel("Loop " + (i+1) + " Choke Group");
            lblSetLoopGate.setBorder(new javax.swing.border.EmptyBorder(4,4,4,4));
        	String[] chokeChoices = { "Nope", "1", "2", "3", "4", "5", "6", "7"};
            drpLoopChoke = new JComboBox(chokeChoices);
            drpLoopChoke.setName(i.toString());
            drpLoopChoke.setSelectedIndex(0);
            drpLoopChoke.addActionListener(
            		new ActionListener(){
            			public void actionPerformed(ActionEvent e) {
            				JComboBox cb = (JComboBox)e.getSource();
            				if(cb.getSelectedIndex() == 0)
            					sevenUpApplet.setLoopChokeGroup(Integer.parseInt(cb.getName()), -1);
            				else 
            					sevenUpApplet.setLoopChokeGroup(Integer.parseInt(cb.getName()), cb.getSelectedIndex());
            			}
            		}
            );
            
            add(lblSetLoopGate);
            add(drpLoopChoke);
        }
        
        JLabel lblGateLoopChoke = new JLabel("Gate choked loops?");
        lblGateLoopChoke.setBorder(new javax.swing.border.EmptyBorder(4,4,4,4));
    	
        Vector<String> gateChoices = new Vector<String>();
        gateChoices.add("Ok");
        gateChoices.add("Nope");
        drpGateLoopChoke = new JComboBox(gateChoices);
        drpGateLoopChoke.addActionListener(
        		new ActionListener(){
        			public void actionPerformed(ActionEvent e) {
        				JComboBox cb = (JComboBox)e.getSource();
        				sevenUpApplet.setGateLoopChokes(cb.getSelectedItem().toString().equals("Ok"));
        			}
        		}
        );
        add(lblGateLoopChoke);
        add(drpGateLoopChoke);
        
        //Add mute looper checkbox
        JLabel lblMuteLooper = new JLabel("Mute Looper (to assign MIDI)");
        lblMuteLooper.setBorder(new javax.swing.border.EmptyBorder(4,4,4,4));
    	
        chkMuteLooper = new JCheckBox();
        chkMuteLooper.addActionListener(
        		new ActionListener(){
        			public void actionPerformed(ActionEvent e) {
        				JCheckBox chk = (JCheckBox)e.getSource();
       					sevenUpApplet.setLooperMute(chk.isSelected());
        			}
        		}
        );
        
        add(lblMuteLooper);
        add(chkMuteLooper);
        
        //Add prev/next patch buttons
        btnPrevPatch = new JButton("<< Prev Patch");
        btnPrevPatch.addActionListener(this);
        btnPrevPatch.setEnabled(false);
        add(btnPrevPatch);
        
        btnNextPatch = new JButton("Next Patch >>");
        btnNextPatch.addActionListener(this);
        btnNextPatch.setEnabled(false);
        add(btnNextPatch);
        
        
        
        //Initialize Sevenup Applet
        sevenUpApplet = new SevenUpApplet(sevenUpConnections, this);
        super.add(sevenUpApplet, BorderLayout.CENTER);
        sevenUpApplet.setVisible(false);
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
    	boolean isPatchPack = false;
    	
    	isPatchPack = sevenUpApplet.loadJDOMXMLDocument(XMLDoc);

    	btnPrevPatch.setEnabled(false);
    	btnNextPatch.setEnabled(isPatchPack);

    	updateGui();
    }
    
    private void updateGui()
    {
    	int chokeGroup;
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
    		if(chokeGroup == -1)chokeGroup = 0;
    		for(int k=0; k<this.getComponentCount();k++)
    			if(this.getComponent(k).getName() != null)
    			if(this.getComponent(k).getName().equals(i.toString()))
    			{
    				drpLoopChoke = (JComboBox)this.getComponent(k);
    				drpLoopChoke.setSelectedIndex(chokeGroup);
    			}    		
    	}
    	
    	gateLoopChokes = sevenUpApplet.getGateLoopChokes();
    	if(gateLoopChokes)
    		drpGateLoopChoke.setSelectedIndex(0);
    	else
    		drpGateLoopChoke.setSelectedIndex(1);
    	
    	setTitle(sevenUpApplet.getPatchTitle());
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

	
}
