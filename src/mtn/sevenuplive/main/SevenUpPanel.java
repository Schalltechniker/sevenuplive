package mtn.sevenuplive.main;

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
import javax.swing.JTabbedPane;

import mtn.sevenuplive.modes.MelodizerModel;
import mtn.sevenuplive.modes.ModeConstants;
import mtn.sevenuplive.scales.Scale;
import mtn.sevenuplive.scales.ScaleName;

import org.jdom.Document;

public class SevenUpPanel extends JPanel implements ActionListener
{
	private enum eTransposeOptions {GATE, SUSTAIN};
	
	private static final long serialVersionUID = 1L;
	SevenUpApplet sevenUpApplet;
	JComboBox drpScaleChoices1;
	JComboBox drpScaleChoices2;
	JComboBox drpMelodizerModeChoices1;
	JComboBox drpMelodizerModeChoices2;
	JComboBox drpLoopChoke;
	JComboBox drpTransposeGroup1;
	JComboBox drpTransposeGroup2;
	JComboBox drpTransposeOptions1;
	JComboBox drpTransposeOptions2;
	JComboBox drpLoopLength;
	JComboBox drpLoopType;
	JComboBox drpGateLoopChoke;
	JComboBox drpMelRecMode;
	JComboBox drpMelRecMode2;
	JButton btnPrevPatch;
	JButton btnNextPatch;
	JCheckBox chkMuteLooper;
	JCheckBox chkTranspose1;
	JCheckBox chkTranspose2;
	JFrame parentFrame;
	JPanel looperPanel;
	JPanel melodizer1Panel;
	JPanel melodizer2Panel;
	JTabbedPane tabPanel;
	
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
    	parentFrame.setBounds(screenSize.width / 2,screenSize.height / 2,515,420);
 
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
			GridBagLayout twocolumnstraight = new GridBagLayout();
			
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.LAST_LINE_START;
			c.weighty = 0.5;
			c.gridx = 0;
			c.gridy = 0;
			
			tabPanel = new JTabbedPane();
			
			melodizer1Panel = new JPanel(twocolumn);
			melodizer2Panel = new JPanel(twocolumn);
			looperPanel = new JPanel(fourcolumn);
			JPanel thirdPanel = new JPanel(twocolumnstraight);
			
			tabPanel.add("Looper", looperPanel);
			tabPanel.add("Melodizer1", melodizer1Panel);
			tabPanel.add("Melodizer2", melodizer2Panel);
			
			//Scale
	        JLabel lblSetScale = new JLabel("Scale/Mode");
	        setSizeSmall(lblSetScale);
	        lblSetScale.setBorder(new javax.swing.border.EmptyBorder(4,4,4,4));
	        
	        Vector<ScaleName> scaleChoices = new Vector<ScaleName>();
	        ScaleName[] scales = ScaleName.values();
	        for (ScaleName scaleName : scales) {
	        	scaleChoices.add(scaleName);
	        }
	        
	        drpScaleChoices1 = new JComboBox(scaleChoices);
	        setSizeSmall(drpScaleChoices1);
	        drpScaleChoices1.setSelectedItem(ScaleName.Major);
	        
	        drpScaleChoices1.addActionListener(
	        		new ActionListener(){
	        			public void actionPerformed(ActionEvent e) {
	        				setDirty(true);
	        				JComboBox cb = (JComboBox)e.getSource();
	        				sevenUpApplet.setMelody1Scale(cb.getSelectedItem().toString());
	        			}
	        		}
	        );
			
			c.gridx = 0; c.gridy = 0; melodizer1Panel.add(lblSetScale, c);
			c.gridx = 1; melodizer1Panel.add(drpScaleChoices1, c);
	        
			//Melodizer 2 Scale
	        lblSetScale = new JLabel("Scale/Mode");
	        setSizeSmall(lblSetScale);
	        lblSetScale.setBorder(new javax.swing.border.EmptyBorder(4,4,4,4));
	        
	        drpScaleChoices2 = new JComboBox(scaleChoices);
	        setSizeSmall(drpScaleChoices2);
	        drpScaleChoices2.setSelectedItem(ScaleName.Major);
	        
	        drpScaleChoices2.addActionListener(
	        		new ActionListener(){
	        			public void actionPerformed(ActionEvent e) {
	        				setDirty(true);
	        				JComboBox cb = (JComboBox)e.getSource();
	        					sevenUpApplet.setMelody2Scale(cb.getSelectedItem().toString());
	        			}
	        		}
	        );
	        
	        c.gridx = 0; c.gridy = 0; melodizer2Panel.add(lblSetScale, c);
	        c.gridx = 1; melodizer2Panel.add(drpScaleChoices2, c);	        
	        
	        //Melodizer 1 Mode
	        
	        Vector<String> modeChoices = new Vector<String>();
	        modeChoices.add(MelodizerModel.eMelodizerMode.KEYBOARD.toString());
	        modeChoices.add(MelodizerModel.eMelodizerMode.POSITION.toString());
	        modeChoices.add(MelodizerModel.eMelodizerMode.NONE.toString());
	        modeChoices.add(MelodizerModel.eMelodizerMode.CLIP.toString());
	        modeChoices.add(MelodizerModel.eMelodizerMode.KEYBOARD.toString() + "/" + MelodizerModel.eMelodizerMode.POSITION.toString());
	        modeChoices.add(MelodizerModel.eMelodizerMode.KEYBOARD.toString() + "/" + MelodizerModel.eMelodizerMode.NONE.toString());
	        modeChoices.add(MelodizerModel.eMelodizerMode.POSITION.toString() + "/" + MelodizerModel.eMelodizerMode.NONE.toString());
	        
	        drpMelodizerModeChoices1 = new JComboBox(modeChoices);
	        setSizeSmall(drpMelodizerModeChoices1);
	        drpMelodizerModeChoices1.setSelectedIndex(0);
	        
	        drpMelodizerModeChoices1.addActionListener(
	        		new ActionListener(){
	        			public void actionPerformed(ActionEvent e) {
	        				setDirty(true);
	        				JComboBox cb = (JComboBox)e.getSource();
	        				String modeString = cb.getSelectedItem().toString();
	        				String[] modes = modeString.split("/");
	        				if (modes.length == 2) {
	        					sevenUpApplet.setMelody1Mode(MelodizerModel.eMelodizerMode.valueOf(modes[0]));
	        					sevenUpApplet.setMelody1AltMode(MelodizerModel.eMelodizerMode.valueOf(modes[1]));
	        				} else {
	        					sevenUpApplet.setMelody1Mode(MelodizerModel.eMelodizerMode.valueOf(modeString));
	        					sevenUpApplet.setMelody1AltMode(MelodizerModel.eMelodizerMode.valueOf(modeString));
	        				}	
	        			}
	        		}
	        );
	        
	        c.gridx = 2; c.gridy = 0; melodizer1Panel.add(drpMelodizerModeChoices1, c);	        
	        
	        //Melodizer 2 Mode
	        
	        //Remove clip launch option
	        modeChoices = new Vector<String>();
	        modeChoices.add(MelodizerModel.eMelodizerMode.KEYBOARD.toString());
	        modeChoices.add(MelodizerModel.eMelodizerMode.POSITION.toString());
	        modeChoices.add(MelodizerModel.eMelodizerMode.NONE.toString());
	        modeChoices.add(MelodizerModel.eMelodizerMode.KEYBOARD.toString() + "/" + MelodizerModel.eMelodizerMode.POSITION.toString());
	        modeChoices.add(MelodizerModel.eMelodizerMode.KEYBOARD.toString() + "/" + MelodizerModel.eMelodizerMode.NONE.toString());
	        modeChoices.add(MelodizerModel.eMelodizerMode.POSITION.toString() + "/" + MelodizerModel.eMelodizerMode.NONE.toString());
	        
	        drpMelodizerModeChoices2 = new JComboBox(modeChoices);
	        setSizeSmall(drpMelodizerModeChoices2);
	        drpMelodizerModeChoices2.setSelectedIndex(0);
	        
	        drpMelodizerModeChoices2.addActionListener(
	        		new ActionListener(){
	        			public void actionPerformed(ActionEvent e) {
	        				setDirty(true);
	        				JComboBox cb = (JComboBox)e.getSource();
	        				String modeString = cb.getSelectedItem().toString();
	        				String[] modes = modeString.split("/");
	        				if (modes.length == 2) {
	        					sevenUpApplet.setMelody2Mode(MelodizerModel.eMelodizerMode.valueOf(modes[0]));
	        					sevenUpApplet.setMelody2AltMode(MelodizerModel.eMelodizerMode.valueOf(modes[1]));
	        				} else {
	        					sevenUpApplet.setMelody2Mode(MelodizerModel.eMelodizerMode.valueOf(modeString));
	        					sevenUpApplet.setMelody2AltMode(MelodizerModel.eMelodizerMode.valueOf(modeString));
	        				}
	        			}
	        		}
	        );
	        
	        c.gridx = 2; c.gridy = 0; melodizer2Panel.add(drpMelodizerModeChoices2, c);	        
	        
	        //Melodizer record mode
	        JLabel lblMelRecMode = new JLabel("Rec Mode");
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
		        				sevenUpApplet.setMel1RecMode(ModeConstants.MEL_ON_BUTTON_PRESS);
	        				else 
	        					sevenUpApplet.setMel1RecMode(ModeConstants.MEL_QUANTIZED);
	        			}
	        		}
	        );
	        
	        //Melodizer record mode
	        JLabel lblMelRecMode2 = new JLabel("Rec Mode");
	        setSizeSmall(lblMelRecMode2);
	        lblMelRecMode2.setBorder(new javax.swing.border.EmptyBorder(4,4,4,4));
	        drpMelRecMode2 = new JComboBox(recModeChoices);
	        setSizeSmall(drpMelRecMode2);
	        drpMelRecMode2.addActionListener(
	        		new ActionListener(){
	        			public void actionPerformed(ActionEvent e) {
	        				setDirty(true);
	        				JComboBox cb = (JComboBox)e.getSource();
	        				if(cb.getSelectedItem().toString().equals("On Button Press"))
		        				sevenUpApplet.setMel2RecMode(ModeConstants.MEL_ON_BUTTON_PRESS);
	        				else 
	        					sevenUpApplet.setMel2RecMode(ModeConstants.MEL_QUANTIZED);
	        			}
	        		}
	        );
	        c.gridx = 0; c.gridy = 1; melodizer2Panel.add(lblMelRecMode, c); melodizer1Panel.add(lblMelRecMode2, c);
	        c.gridx = 1; melodizer2Panel.add(drpMelRecMode, c); melodizer1Panel.add(drpMelRecMode2, c);
	        
	        // Enable transpose mode for melodizers
	        JLabel lblTranspose1 = new JLabel("Transpose Patterns");
	        setSizeSmall(lblTranspose1);
	        lblTranspose1.setBorder(new javax.swing.border.EmptyBorder(4,4,4,4));
	    	
	        chkTranspose1 = new JCheckBox();
	        setSizeSmall(chkTranspose1);
	        chkTranspose1.addActionListener(
	        		new ActionListener(){
	        			public void actionPerformed(ActionEvent e) {
	        				setDirty(true);
	        				JCheckBox chk = (JCheckBox)e.getSource();
	       					sevenUpApplet.setMelody1Transpose(chk.isSelected());
	        			}
	        		}
	        );
	        
	        // Enable transpose mode 
	        JLabel lblTranspose2 = new JLabel("Transpose Patterns");
	        setSizeSmall(lblTranspose2);
	        lblTranspose1.setBorder(new javax.swing.border.EmptyBorder(4,4,4,4));
	    	
	        chkTranspose2 = new JCheckBox();
	        setSizeSmall(chkTranspose2);
	        chkTranspose2.addActionListener(
	        		new ActionListener(){
	        			public void actionPerformed(ActionEvent e) {
	        				setDirty(true);
	        				JCheckBox chk = (JCheckBox)e.getSource();
	       					sevenUpApplet.setMelody2Transpose(chk.isSelected());
	        			}
	        		}
	        );
	        
	        JPanel childP = new JPanel();
	        JPanel child2P = new JPanel();
	        GridBagConstraints d = new GridBagConstraints();
			d.fill = GridBagConstraints.HORIZONTAL;
			d.anchor = GridBagConstraints.LAST_LINE_START;
			d.weighty = 0.5;
			d.gridx = 0;
			d.gridy = 0;
			
	        childP.add(lblTranspose1, d); child2P.add(lblTranspose2, d);
	        d.gridx = 1;
			childP.add(chkTranspose1, d); child2P.add(chkTranspose2, d);
	        
	        c.gridx = 2; c.gridy = 1; 
	        melodizer1Panel.add(childP, c);
	        melodizer2Panel.add(child2P, c);
	        
	        
	        // Transpose groups
	        JLabel lblSetTransposeGroup;
	        
	        for(Integer i=0; i<7;i++)
	        {
	        	lblSetTransposeGroup = new JLabel("Pattern " + (i+1) + " Transpose Group");
	        	setSizeSmall(lblSetTransposeGroup);
	        	lblSetTransposeGroup.setBorder(new javax.swing.border.EmptyBorder(4,4,4,4));
	            String[] transposeChoices = { "Nope", "1", "2", "3", "4", "5", "6", "7"};
	            drpTransposeGroup1 = new JComboBox(transposeChoices);
	            drpTransposeOptions1 = new JComboBox(eTransposeOptions.values());
	            setSizeSmall(drpTransposeGroup1);
	            setSizeSmall(drpTransposeOptions1);
	            drpTransposeGroup1.setName(i.toString() + "transpose");
	            drpTransposeOptions1.setName(i.toString() + "options");
	            drpTransposeGroup1.setSelectedIndex(0);
	            drpTransposeGroup1.addActionListener(
	            		new ActionListener(){
	            			public void actionPerformed(ActionEvent e) {
	            				setDirty(true);
	            				JComboBox cb = (JComboBox)e.getSource();
	            				sevenUpApplet.setMel1TransposeGroup(Integer.parseInt(cb.getName().substring(0,1)), cb.getSelectedIndex() - 1);
	            			}
	            		}
	            );
	            drpTransposeOptions1.addActionListener(
	            		new ActionListener(){
	            			public void actionPerformed(ActionEvent e) {
	            				setDirty(true);
	            				JComboBox cb = (JComboBox)e.getSource();
	            				sevenUpApplet.setMel1TransposeSustain(Integer.parseInt(cb.getName().substring(0,1)), cb.getSelectedItem() == eTransposeOptions.SUSTAIN ? true: false);
	            			}
	            		}
	            );
	            c.gridx = 0; c.gridy = 2 + i; melodizer1Panel.add(lblSetTransposeGroup, c);
	            c.gridx = 1; melodizer1Panel.add(drpTransposeGroup1, c);
	            c.gridx = 2; melodizer1Panel.add(drpTransposeOptions1, c);
	        }
	        
	        for(Integer i=0; i<7;i++)
	        {
	        	lblSetTransposeGroup = new JLabel("Pattern " + (i+1) + " Transpose Group");
	        	setSizeSmall(lblSetTransposeGroup);
	        	lblSetTransposeGroup.setBorder(new javax.swing.border.EmptyBorder(4,4,4,4));
	            String[] transposeChoices = { "Nope", "1", "2", "3", "4", "5", "6", "7"};
	            drpTransposeOptions2 = new JComboBox(eTransposeOptions.values());
	            drpTransposeGroup2 = new JComboBox(transposeChoices);
	            setSizeSmall(drpTransposeGroup2);
	            setSizeSmall(drpTransposeOptions2);
	            drpTransposeGroup2.setName(i.toString() + "transpose");
	            drpTransposeOptions2.setName(i.toString() + "options");
	            drpTransposeGroup2.setSelectedIndex(0);
	            drpTransposeGroup2.addActionListener(
	            		new ActionListener(){
	            			public void actionPerformed(ActionEvent e) {
	            				setDirty(true);
	            				JComboBox cb = (JComboBox)e.getSource();
	            				sevenUpApplet.setMel2TransposeGroup(Integer.parseInt(cb.getName().substring(0,1)), cb.getSelectedIndex() - 1);
	            			}
	            		}
	            );
	            drpTransposeOptions2.addActionListener(
	            		new ActionListener(){
	            			public void actionPerformed(ActionEvent e) {
	            				setDirty(true);
	            				JComboBox cb = (JComboBox)e.getSource();
	            				sevenUpApplet.setMel2TransposeSustain(Integer.parseInt(cb.getName().substring(0,1)), cb.getSelectedItem() == eTransposeOptions.SUSTAIN ? true: false);
	            			}
	            		}
	            );
	            
	            c.gridx = 0; c.gridy = 2 + i; melodizer2Panel.add(lblSetTransposeGroup, c);
	            c.gridx = 1; melodizer2Panel.add(drpTransposeGroup2, c);
	            c.gridx = 2; melodizer2Panel.add(drpTransposeOptions2, c);
	        }
	        
	        //////////////////////////////////////////
	        // Looper
	        
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
	            String[] typeChoices = { "Loop", "Step", "Shot", "Momentary", "Hit", "Slice"};
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
	            c.gridx = 0; c.gridy = 3 + i; looperPanel.add(lblSetLoopGate, c);
	            c.gridx = 1; looperPanel.add(drpLoopChoke, c);
	            c.gridx = 2; looperPanel.add(lblSetLoopLength, c);
	            c.gridx = 3; looperPanel.add(drpLoopLength, c);
	            c.gridx = 4; looperPanel.add(drpLoopType, c);
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
	        c.gridx = 0; c.gridy++; looperPanel.add(lblGateLoopChoke, c);
	        c.gridx = 1; looperPanel.add(drpGateLoopChoke, c);
	        
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
	        
	        GridBagConstraints e = new GridBagConstraints();
			e.fill = GridBagConstraints.HORIZONTAL;
			e.anchor = GridBagConstraints.LAST_LINE_START;
			e.weighty = 1;
			e.gridx = 0;
			e.gridy = 0;
			
			thirdPanel.add(lblMuteLooper, e);
	        e.gridx = 1;
	        thirdPanel.add(chkMuteLooper, e);
	        
			e.gridx = 0; e.gridy = 1;
			
	        //Add prev/next patch buttons
	        btnPrevPatch = new JButton("<< Prev Patch");
	        btnPrevPatch.putClientProperty("JButton.buttonType", "gradient");
	        btnPrevPatch.addActionListener(this);
	        btnPrevPatch.setEnabled(false);
	        thirdPanel.add(btnPrevPatch, e);
	        
	        e.gridx = 1;
	        
	        btnNextPatch = new JButton("Next Patch >>");
	        btnNextPatch.putClientProperty("JButton.buttonType", "gradient");
	        btnNextPatch.addActionListener(this);
	        btnNextPatch.setEnabled(false);
	        thirdPanel.add(btnNextPatch, e);
	        
	        // Add tab to this container
	        add(tabPanel);
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
    	int transposeGroup;
    	boolean sustain;
    	int loopType;
    	Float length;
    	boolean gateLoopChokes;
    	
    	//Update melody 1 gui based on patch settings
    	Scale melodyScale1 = sevenUpApplet.getMelody1Scale();
    	drpScaleChoices1.setSelectedItem(melodyScale1.Name);
		
		for(int i = drpMelodizerModeChoices1.getItemCount() - 1; i > -1; i--)
    	{
			if(drpMelodizerModeChoices1.getItemAt(i).toString().equals(sevenUpApplet.getMelody1Mode().toString()))
    		{
    			drpMelodizerModeChoices1.setSelectedIndex(i);
    			break;
    		} 
    		else if (drpMelodizerModeChoices1.getItemAt(i).toString().equals(sevenUpApplet.getMelody1Mode().toString() + "/" + sevenUpApplet.getMelody1AltMode().toString()))
    		{
    			drpMelodizerModeChoices1.setSelectedIndex(i);
    			break;
    		}
    	}
		
		//Update melody 2 gui based on patch settings
    	Scale melodyScale2 = sevenUpApplet.getMelody2Scale();
    	drpScaleChoices2.setSelectedItem(melodyScale2.Name);
        
        
        chkTranspose1.setSelected(sevenUpApplet.getMelody1Transpose());
        chkTranspose2.setSelected(sevenUpApplet.getMelody2Transpose());
        
        drpMelRecMode.setSelectedIndex(sevenUpApplet.getMel1RecMode() - 1);
        drpMelRecMode2.setSelectedIndex(sevenUpApplet.getMel2RecMode()- 1);
        
        // Need to count backwards as the choices go from multiple modes to 1 mode
        for(int i = drpMelodizerModeChoices2.getItemCount() - 1; i > -1; i--)
    	{
    		if(drpMelodizerModeChoices2.getItemAt(i).toString().equals(sevenUpApplet.getMelody2Mode().toString()))
    		{
    			drpMelodizerModeChoices2.setSelectedIndex(i);
    			break;
    		}
    		else if (drpMelodizerModeChoices2.getItemAt(i).toString().equals(sevenUpApplet.getMelody2Mode().toString() + "/" + sevenUpApplet.getMelody2AltMode().toString()))
    		{
    			drpMelodizerModeChoices2.setSelectedIndex(i);
    			break;
    		}
    	}
        
        for(Integer i=0;i<7;i++)
    	{
        	transposeGroup = sevenUpApplet.getMel1TransposeGroup(i);
    		
    		for(int k=0; k<melodizer1Panel.getComponentCount();k++)
    		{
    			if(melodizer1Panel.getComponent(k).getName() != null)
    			{
	    			if(melodizer1Panel.getComponent(k).getName().equals(i.toString()+"transpose"))
	    			{
	    				drpTransposeGroup1 = (JComboBox)melodizer1Panel.getComponent(k);
	    				drpTransposeGroup1.setSelectedIndex(transposeGroup + 1);
	    			}
    			}
			}
		}
        
        for(Integer i=0;i<7;i++)
    	{
        	transposeGroup = sevenUpApplet.getMel2TransposeGroup(i);
    		
    		for(int k=0; k<melodizer2Panel.getComponentCount();k++)
    		{
    			if(melodizer2Panel.getComponent(k).getName() != null)
    			{
	    			if(melodizer2Panel.getComponent(k).getName().equals(i.toString()+"transpose"))
	    			{
	    				drpTransposeGroup2 = (JComboBox)melodizer2Panel.getComponent(k);
	    				drpTransposeGroup2.setSelectedIndex(transposeGroup + 1);
	    			}
    			}
			}
		}
        
        for(Integer i=0;i<7;i++)
    	{
        	sustain = sevenUpApplet.getMel1TransposeSustain(i);
    		
    		for(int k=0; k<melodizer1Panel.getComponentCount();k++)
    		{
    			if(melodizer1Panel.getComponent(k).getName() != null)
    			{
	    			if(melodizer1Panel.getComponent(k).getName().equals(i.toString()+"options"))
	    			{
	    				drpTransposeOptions1 = (JComboBox)melodizer1Panel.getComponent(k);
	    				if (sustain) {
	    					drpTransposeOptions1.setSelectedItem(eTransposeOptions.SUSTAIN);
	    				} else {
	    					drpTransposeOptions1.setSelectedItem(eTransposeOptions.GATE);
	    				}
	    			}
    			}
			}
		}
        
        for(Integer i=0;i<7;i++)
    	{
        	sustain = sevenUpApplet.getMel2TransposeSustain(i);
    		
    		for(int k=0; k<melodizer2Panel.getComponentCount();k++)
    		{
    			if(melodizer2Panel.getComponent(k).getName() != null)
    			{
	    			if(melodizer2Panel.getComponent(k).getName().equals(i.toString()+"options"))
	    			{
	    				drpTransposeOptions2 = (JComboBox)melodizer2Panel.getComponent(k);
	    				if (sustain) {
	    					drpTransposeOptions2.setSelectedItem(eTransposeOptions.SUSTAIN);
	    				} else {
	    					drpTransposeOptions2.setSelectedItem(eTransposeOptions.GATE);
	    				}
	    			}
    			}
			}
		}
        
		
		for(Integer i =0;i<7;i++)
    	{
    		chokeGroup = sevenUpApplet.getLoopChokeGroup(i);
    		length = sevenUpApplet.getLoopLength(i);
    		loopType = sevenUpApplet.getLoopType(i);
    		if(chokeGroup == -1)chokeGroup = 0;
    		
    		for(int k=0; k<looperPanel.getComponentCount();k++)
    		{
    			
    			if(looperPanel.getComponent(k).getName() != null)
    			{
	    			if(looperPanel.getComponent(k).getName().equals(i.toString()+"length"))
	    			{
	    				drpLoopLength = (JComboBox)looperPanel.getComponent(k);
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
	    			else if(looperPanel.getComponent(k).getName().equals(i.toString()+"choke"))
	    			{
	    				drpLoopChoke = (JComboBox)looperPanel.getComponent(k);
	    				drpLoopChoke.setSelectedIndex(chokeGroup);
	    			}
	    			else if(looperPanel.getComponent(k).getName().equals(i.toString()+"type"))
	    			{
	    				drpLoopType = (JComboBox)looperPanel.getComponent(k);
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
