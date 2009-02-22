package mtn.sevenuplive.main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import promidi.MidiIO;


public class ConnectionPanel extends JPanel 
							     implements ActionListener{
	
	private static final long serialVersionUID = -2961359562232170547L;
	
	mtn.sevenuplive.main.MainApp mainApp;
	JTextField txtOscPrefix;
	JTextField txtOscHostAddress;
	JTextField txtOscHostPort;
	JTextField txtOscListenPort;
	JComboBox drpMidiInputs;
	JComboBox drpMidiStepperOutput;
	JComboBox drpMidiLooperOutput;
	JComboBox drpMidiMelder1Output;
	JComboBox drpMidiMelder2Output;
	JComboBox drpMonomeChoices;
	
	private ConnectionSettings sevenUpConnections = new ConnectionSettings();
	
	// Constants for settings page and persistent properties
	
	private static final String OSC_PREFIX = "Osc Prefix";
	private static final String OSC_PREFIX_PROP = "osc.prefix";
	private static final String OSC_PREFIX_DEFAULT = "box";
	
	private static final String OSC_HOST_ADDR = "Osc Host Address";
	private static final String OSC_HOST_ADDR_PROP = "osc.host.addr";
	private static final String OSC_HOST_ADDR_DEFAULT = "127.0.0.1";
	
	private static final String OSC_HOST_PORT = "Osc Host Port";
	private static final String OSC_HOST_PORT_PROP = "osc.host.port";
	private static final String OSC_HOST_PORT_DEFAULT = "8080";
	
	private static final String OSC_LISTEN_PORT = "Osc Listen Port";
	private static final String OSC_LISTEN_PORT_PROP = "osc.listen.port";
	private static final String OSC_LISTEN_PORT_DEFAULT = "8000";
	
	private static final String MIDI_INPUT_DEVICE = "Midi Input Device";
	private static final String MIDI_INPUT_DEVICE_PROP = "midi.input.device";
	
	private static final String STEPPER_OUTPUT_DEVICE = "Stepper Output Device";
	private static final String STEPPER_OUTPUT_DEVICE_PROP = "stepper.output.device";
	
	private static final String LOOPER_OUTPUT_DEVICE = "Looper Output Device";
	private static final String LOOPER_OUTPUT_DEVICE_PROP = "looper.output.device";
	
	private static final String MELODIZER1_OUTPUT_DEVICE = "Melodizer 1 Output Device";
	private static final String MELODIZER1_OUTPUT_DEVICE_PROP = "melodizer.1.output.device";
	
	private static final String MELODIZER2_OUTPUT_DEVICE = "Melodizer 2 Output Device";
	private static final String MELODIZER2_OUTPUT_DEVICE_PROP = "melodizer.2.output.device";
	
	private static final String MONOME_TYPE = "Monome";
	private static final String MONOME_TYPE_PROP = "monome.type";
	
	public ConnectionPanel(mtn.sevenuplive.main.MainApp parentFrame)
	{
		super(new GridLayout(0,2));  //X rows, 2 columns
		
		mainApp = parentFrame;
		
		// Read our connections at startup
		readConnections(sevenUpConnections);
		
		JLabel lblSetMonomeType = new JLabel(MONOME_TYPE);
		lblSetMonomeType.setBorder(new javax.swing.border.EmptyBorder(4,4,4,4));
		Vector<String> monomeChoices = new Vector<String>();
        monomeChoices.add("64");
        monomeChoices.add("128H");
        monomeChoices.add("128V");
        monomeChoices.add("3 x 64");
        monomeChoices.add("256");
        monomeChoices.add("5 x 64");
        monomeChoices.add("6 x 64");
        monomeChoices.add("7 x 64");
        monomeChoices.add("8 x 64");
        monomeChoices.add("9 x 64");
        monomeChoices.add("10 x 64");
        drpMonomeChoices = new JComboBox(monomeChoices);
        drpMonomeChoices.setSelectedIndex(sevenUpConnections.monomeType);
        drpMonomeChoices.addActionListener(
        		new ActionListener(){
        			public void actionPerformed(ActionEvent e) {
        				JComboBox cb = (JComboBox)e.getSource();
        				sevenUpConnections.monomeType = cb.getSelectedIndex();
        			}
        		}
        );
        super.add(lblSetMonomeType);
		super.add(drpMonomeChoices);
        
		JLabel lblOscPrefix = new JLabel(OSC_PREFIX);
		lblOscPrefix.setBorder(new javax.swing.border.EmptyBorder(4,4,4,4));
		txtOscPrefix = new JTextField(sevenUpConnections.oscPrefix);
		super.add(lblOscPrefix);
		super.add(txtOscPrefix);
		
		JLabel lblOscHostAddress = new JLabel(OSC_HOST_ADDR);
		lblOscHostAddress.setBorder(new javax.swing.border.EmptyBorder(4,4,4,4));
		txtOscHostAddress = new JTextField(sevenUpConnections.oscHostAddress);
		super.add(lblOscHostAddress);
		super.add(txtOscHostAddress);
		
		JLabel lblOscHostPort = new JLabel(OSC_HOST_PORT);
		lblOscHostPort.setBorder(new javax.swing.border.EmptyBorder(4,4,4,4));
		txtOscHostPort = new JTextField(sevenUpConnections.oscHostPort > 0 ? Integer.toString(sevenUpConnections.oscHostPort) : OSC_HOST_PORT_DEFAULT);
		super.add(lblOscHostPort);
		super.add(txtOscHostPort);
		
		JLabel lblOscListenPort = new JLabel(OSC_LISTEN_PORT);
		lblOscListenPort.setBorder(new javax.swing.border.EmptyBorder(4,4,4,4));
		txtOscListenPort = new JTextField(sevenUpConnections.oscListenPort > 0 ? Integer.toString(sevenUpConnections.oscListenPort) : OSC_LISTEN_PORT_DEFAULT);
		super.add(lblOscListenPort);
		super.add(txtOscListenPort);
		
		JLabel lblMidiInput = new JLabel(MIDI_INPUT_DEVICE);	
		lblMidiInput.setBorder(new javax.swing.border.EmptyBorder(4,4,4,4));
		drpMidiInputs = new JComboBox(getMidiInputs().toArray());
		super.add(lblMidiInput);
		super.add(drpMidiInputs);
		
		JLabel lblStepperInput = new JLabel(STEPPER_OUTPUT_DEVICE);	
		lblStepperInput.setBorder(new javax.swing.border.EmptyBorder(4,4,4,4));
			drpMidiStepperOutput = new JComboBox(getMidiOutputs().toArray());
		super.add(lblStepperInput);
		super.add(drpMidiStepperOutput);
		
		JLabel lblLooperInput = new JLabel(LOOPER_OUTPUT_DEVICE);	
		lblLooperInput.setBorder(new javax.swing.border.EmptyBorder(4,4,4,4));
			drpMidiLooperOutput = new JComboBox(getMidiOutputs().toArray());
		super.add(lblLooperInput);
		super.add(drpMidiLooperOutput);
		
		JLabel lblMelder1Input = new JLabel(MELODIZER1_OUTPUT_DEVICE);	
		lblMelder1Input.setBorder(new javax.swing.border.EmptyBorder(4,4,4,4));
			drpMidiMelder1Output = new JComboBox(getMidiOutputs().toArray());
		super.add(lblMelder1Input);
		super.add(drpMidiMelder1Output);
		
		JLabel lblMelder2Input = new JLabel(MELODIZER2_OUTPUT_DEVICE);	
		lblMelder2Input.setBorder(new javax.swing.border.EmptyBorder(4,4,4,4));
			drpMidiMelder2Output = new JComboBox(getMidiOutputs().toArray());
		super.add(lblMelder2Input);
		super.add(drpMidiMelder2Output);

		//Set default drop down items
		if (sevenUpConnections.midiInputDeviceName == null) {
			for(int i = 0; i < drpMidiInputs.getItemCount();i++)
			{	
				if (drpMidiInputs.getItemAt(i).toString().contains(" 1"))
					drpMidiInputs.setSelectedIndex(i);
			}
		} else {
			for(int i = 0; i < drpMidiInputs.getItemCount();i++)
			{	
				if (drpMidiInputs.getItemAt(i).equals(sevenUpConnections.midiInputDeviceName))
					drpMidiInputs.setSelectedIndex(i);
			}
		}
		
		if (sevenUpConnections.stepperOutputDeviceName == null) {
			for(int i = 0; i < drpMidiStepperOutput.getItemCount();i++)
			{	
				if (drpMidiStepperOutput.getItemAt(i).toString().contains(" 2"))
					drpMidiStepperOutput.setSelectedIndex(i);
			}
		} else {
			for(int i = 0; i < drpMidiStepperOutput.getItemCount();i++)
			{	
				if (drpMidiStepperOutput.getItemAt(i).equals(sevenUpConnections.stepperOutputDeviceName))
					drpMidiStepperOutput.setSelectedIndex(i);
			}
		}
		
		if (sevenUpConnections.looperOuputDeviceName == null) {
			for(int i = 0; i < drpMidiLooperOutput.getItemCount();i++)
			{
				if (drpMidiLooperOutput.getItemAt(i).toString().contains(" 3"))
					drpMidiLooperOutput.setSelectedIndex(i);
			}
		} else {
			for(int i = 0; i < drpMidiLooperOutput.getItemCount();i++)
			{
				if (drpMidiLooperOutput.getItemAt(i).equals(sevenUpConnections.looperOuputDeviceName))
					drpMidiLooperOutput.setSelectedIndex(i);
			}
		}
		
		if (sevenUpConnections.melod1OutputDeviceName == null) {
			for(int i = 0; i < drpMidiMelder1Output.getItemCount();i++)
			{
				if (drpMidiMelder1Output.getItemAt(i).toString().contains(" 4") && !(drpMidiMelder1Output.getItemAt(i).toString().contains("FW 410")))
					drpMidiMelder1Output.setSelectedIndex(i);
			}
		} else {
			for(int i = 0; i < drpMidiMelder1Output.getItemCount();i++)
			{
				if (drpMidiMelder1Output.getItemAt(i).equals(sevenUpConnections.melod1OutputDeviceName))
					drpMidiMelder1Output.setSelectedIndex(i);
			}
		}
		
		if (sevenUpConnections.melod2OutputDeviceName == null) {
			for(int i = 0; i < drpMidiMelder2Output.getItemCount();i++)
			{
				if (drpMidiMelder2Output.getItemAt(i).toString().contains(" 5"))
					drpMidiMelder2Output.setSelectedIndex(i);
			}
		} else {
			for(int i = 0; i < drpMidiMelder2Output.getItemCount();i++)
			{
				if (drpMidiMelder2Output.getItemAt(i).equals(sevenUpConnections.melod2OutputDeviceName))
					drpMidiMelder2Output.setSelectedIndex(i);
			}
		}

		JButton btnInitialize = new JButton();
		btnInitialize = new JButton("Initialize");
		btnInitialize.addActionListener(this);
		super.add(btnInitialize);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("initialize"))
		{
			this.setVisible(false);
			
			sevenUpConnections.oscPrefix = txtOscPrefix.getText();
			sevenUpConnections.oscHostAddress = txtOscHostAddress.getText();
			
			try {
				sevenUpConnections.oscHostPort = Integer.parseInt(txtOscHostPort.getText());
			} catch (NumberFormatException nfe) {
				System.err.println("Host port out of range: cause: " + nfe);
				sevenUpConnections.oscHostPort =  Integer.parseInt(OSC_HOST_PORT_DEFAULT);
			}
			
			try {
				sevenUpConnections.oscListenPort = Integer.parseInt(txtOscListenPort.getText());
			} catch (NumberFormatException nfe) {
				System.err.println("Listen port out of range: cause: " + nfe);
				sevenUpConnections.oscListenPort = Integer.parseInt(OSC_LISTEN_PORT_DEFAULT);
			}
			
			sevenUpConnections.midiInputDeviceName = drpMidiInputs.getSelectedItem().toString();
			sevenUpConnections.midiInputDeviceIndex = drpMidiInputs.getSelectedIndex();
			sevenUpConnections.stepperOutputDeviceName = drpMidiStepperOutput.getSelectedItem().toString();
			sevenUpConnections.looperOuputDeviceName = drpMidiLooperOutput.getSelectedItem().toString();
			sevenUpConnections.melod1OutputDeviceName = drpMidiMelder1Output.getSelectedItem().toString();
			sevenUpConnections.melod2OutputDeviceName = drpMidiMelder2Output.getSelectedItem().toString();
			sevenUpConnections.monomeType = drpMonomeChoices.getSelectedIndex();
			
			writeConnections(sevenUpConnections);
			
			mainApp.ShowSevenUp(sevenUpConnections);
		}

	}
	
	public static ArrayList<String> getMidiInputs()
	{
		MidiIO midiSystem = MidiIO.getInstance();
		
		ArrayList<String> midiInputs = new ArrayList<String>();
		
		for(int i =0; i< midiSystem.numberOfInputDevices(); i++)
		{
			midiInputs.add(midiSystem.getInputDeviceName(i));
		}
		
		midiSystem.closePorts();
		
		return midiInputs;
	}
	
	public static ArrayList<String> getMidiOutputs()
	{
		MidiIO midiSystem = MidiIO.getInstance();
		
		ArrayList<String> midiOutputs = new ArrayList<String>();
		
		for(int i =0; i< midiSystem.numberOfOutputDevices(); i++)
			midiOutputs.add(midiSystem.getOutputDeviceName(i));
		
		return midiOutputs;
	}
	
	protected static void writeConnections(ConnectionSettings connections) {
		Properties props = MainApp.getProperties();
		props.setProperty(OSC_PREFIX_PROP, connections.oscPrefix);
		props.setProperty(OSC_HOST_ADDR_PROP, connections.oscHostAddress);
		props.setProperty(OSC_HOST_PORT_PROP, Integer.toString(connections.oscHostPort));
		props.setProperty(OSC_LISTEN_PORT_PROP, Integer.toString(connections.oscListenPort));
		props.setProperty(MIDI_INPUT_DEVICE_PROP, connections.midiInputDeviceName);
		props.setProperty(STEPPER_OUTPUT_DEVICE_PROP, connections.stepperOutputDeviceName);
		props.setProperty(LOOPER_OUTPUT_DEVICE_PROP, connections.looperOuputDeviceName);
		props.setProperty(MELODIZER1_OUTPUT_DEVICE_PROP, connections.melod1OutputDeviceName);
		props.setProperty(MELODIZER2_OUTPUT_DEVICE_PROP, connections.melod2OutputDeviceName);
		props.setProperty(MONOME_TYPE_PROP, Integer.toString(connections.monomeType));
		
		try {
			MainApp.writeProperties();
		} catch (Exception e) {
			System.err.println("Could not load sevenup settings from property file: cause: " + e);
		}
	}
	
	protected static Properties readConnections(ConnectionSettings connections) {
		Properties props = new Properties();
		
		try {
			props = MainApp.readProperties();
		} catch (Exception e) {
			System.err.println("Could not load sevenup settings from property file: cause: " + e);
		}
		
		connections.oscPrefix = props.getProperty(OSC_PREFIX_PROP, OSC_PREFIX_DEFAULT);
		connections.oscHostAddress = props.getProperty(OSC_HOST_ADDR_PROP, OSC_HOST_ADDR_DEFAULT);
		connections.oscHostPort = Integer.parseInt(props.getProperty(OSC_HOST_PORT_PROP, OSC_HOST_PORT_DEFAULT));
		connections.oscListenPort = Integer.parseInt(props.getProperty(OSC_LISTEN_PORT_PROP, OSC_LISTEN_PORT_DEFAULT));
		connections.midiInputDeviceName = props.getProperty(MIDI_INPUT_DEVICE_PROP);
		connections.stepperOutputDeviceName = props.getProperty(STEPPER_OUTPUT_DEVICE_PROP);
		connections.looperOuputDeviceName = props.getProperty(LOOPER_OUTPUT_DEVICE_PROP);
		connections.melod1OutputDeviceName = props.getProperty(MELODIZER1_OUTPUT_DEVICE_PROP);
		connections.melod2OutputDeviceName = props.getProperty(MELODIZER2_OUTPUT_DEVICE_PROP);
		try {
			connections.monomeType = new Integer(props.getProperty(MONOME_TYPE_PROP, "0"));
		} catch (NumberFormatException e) {
			connections.monomeType = 0;
		}

		return props;
	}

}
