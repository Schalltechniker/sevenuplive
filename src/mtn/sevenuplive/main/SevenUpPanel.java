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
	JComboBox drpLoopMultiplier;
	JComboBox drpGateLoopChoke;
	JComboBox drpMelRecMode;
	JButton btnPrevPatch;
	JButton btnNextPatch;
	JCheckBox chkMuteLooper;
	JFrame parentFrame;
	
	JComboBox[] chokecontrols = new JComboBox[7];
	JComboBox[] multipliercontrols = new JComboBox[7];
	
	
    public SevenUpPanel(ConnectionSettings sevenUpConnections, JFrame _parent) 
    {
    	super();  
    	
    	parentFrame = _parent;
    	
    	// Resize a bit
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	parentFrame.setBounds(screenSize.width / 2,screenSize.height / 2,376,420);
 
    	initLayout();
    	
    	//Initialize Sevenup Applet
        sevenUpApplet = new SevenUpApplet(sevenUpConnections, this);
        
        super.add(sevenUpApplet);
        
    	sevenUpApplet.setVisible(false);
    	
    	this.addComponentListener(new ResizeListener());
    }
    
    private void initLayout() {
		try {
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
			JPanel secondPanel = new JPanel(fourcolumn);
			JPanel thirdPanel = new JPanel(twocolumnstraight);
			
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
			
			
			c.gridx = 0; c.gridy = 0; firstPanel.add(lblSetScale, c);
			c.gridx = 1; firstPanel.add(drpScaleChoices1, c);
	        
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
	        
	        c.gridx = 0; c.gridy = 1; firstPanel.add(lblSetScale, c);
	        c.gridx = 1; firstPanel.add(drpScaleChoices2, c);
	        
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
	        JLabel lblSetLoopMultiplier;
	        
	        for(Integer i=0; i<7;i++)
	        {
	        	lblSetLoopGate = new JLabel("Loop " + (i+1) + "  Choke Group");
	            lblSetLoopGate.setBorder(new javax.swing.border.EmptyBorder(4,4,4,4));
	            String[] chokeChoices = { "Nope", "1", "2", "3", "4", "5", "6", "7"};
	            drpLoopChoke = new JComboBox(chokeChoices);
	            chokecontrols[i] = drpLoopChoke;
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
	            // Loop multiplier choices
	            lblSetLoopMultiplier = new JLabel("Multipler");
	            lblSetLoopMultiplier.setBorder(new javax.swing.border.EmptyBorder(4,4,4,4));
	            String[] multiplierChoices = { "1", "2", "3", "4", "5", "6", "7", "8"};
	            drpLoopMultiplier = new JComboBox(multiplierChoices);
	            multipliercontrols[i] = drpLoopMultiplier;
	            drpLoopMultiplier.setName(i.toString());
	            drpLoopMultiplier.setSelectedIndex(0);
	            drpLoopMultiplier.addActionListener(
	            		new ActionListener(){
	            			public void actionPerformed(ActionEvent e) {
	            				JComboBox cb = (JComboBox)e.getSource();
	            				sevenUpApplet.setLoopMultiplier(Integer.parseInt(cb.getName()), cb.getSelectedIndex() + 1);
	            			}
	            		}
	            );
	            c.gridx = 0; c.gridy = 3 + i; secondPanel.add(lblSetLoopGate, c);
	            c.gridx = 1; secondPanel.add(drpLoopChoke, c);
	            c.gridx = 2; secondPanel.add(lblSetLoopMultiplier, c);
	            c.gridx = 3; secondPanel.add(drpLoopMultiplier, c);
	        
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
	        c.gridx = 0; c.gridy = 3; firstPanel.add(lblGateLoopChoke, c);
	        c.gridx = 1; firstPanel.add(drpGateLoopChoke, c);
	        
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
	        
	        c.gridx = 0; c.gridy = 4; firstPanel.add(lblMuteLooper, c);
	        c.gridx = 1; firstPanel.add(chkMuteLooper, c);
	        
	        //Add prev/next patch buttons
	        btnPrevPatch = new JButton("<< Prev Patch");
	        btnPrevPatch.addActionListener(this);
	        btnPrevPatch.setEnabled(false);
	        thirdPanel.add(btnPrevPatch, BorderLayout.WEST);
	        
	        btnNextPatch = new JButton("Next Patch >>");
	        btnNextPatch.addActionListener(this);
	        btnNextPatch.setEnabled(false);
	        thirdPanel.add(btnNextPatch, BorderLayout.EAST);
	        
	        add(firstPanel);
	        add(secondPanel);
	        add(thirdPanel);
	        
	        this.setPreferredSize(new Dimension(400, 200));
	        this.setMinimumSize(new Dimension(200, 200));
	        this.setMaximumSize(new Dimension(500, 300));
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
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
    	int loopMultiplier;
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
    		chokecontrols[i].setSelectedIndex(chokeGroup);
    		
    		loopMultiplier = sevenUpApplet.getLoopMultiplier(i);
    		if(loopMultiplier == -1)loopMultiplier = 0;
    		multipliercontrols[i].setSelectedIndex(loopMultiplier);
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
	
	class ResizeListener implements ComponentListener {
	    
		public void componentHidden(ComponentEvent e) {}

		public void componentMoved(ComponentEvent e) {}

		public void componentResized(ComponentEvent e) {
			//SevenUpPanel source = (SevenUpPanel)e.getSource();
			//System.out.println("new size width:" + source.getSize().width + " height:" + source.getSize().height);
		}

		public void componentShown(ComponentEvent e) {}
	}


	
}
