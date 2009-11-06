package mtn.sevenuplive.max.mxj;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class SevenUp4LivePatchManager {
	
	private SevenUp4Live app;
	
	public SevenUp4LivePatchManager(SevenUp4Live app) {
		this.app = app;
	}
	
	public void savePatch(String filepath) {

		FileWriter fileWriter = null;
		
		try {
			if (filepath != null) {
				java.io.File file = new File(filepath);
				org.jdom.Document doc = app.getEnvironment().toJDOMXMLDocument(file.getName());
				org.jdom.output.XMLOutputter fmt = new XMLOutputter();

				fileWriter = new FileWriter(file);
				fmt.output(doc, fileWriter);
			}
		} catch (Exception e) {
			SevenUp4Live.post("Could not save patch: " + e);
		} finally { // standard java closing resources in finally
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e1) {
					// drop it
				}
			}	
		}
	}
	
	public void loadPatch(String filepath) {
		
		try {
			if (filepath != null) {
				java.io.File file = new File(filepath);
				SAXBuilder builder = new SAXBuilder();
				try
				{
					Document doc = builder.build(file);
					app.getEnvironment().loadJDOMXMLDocument(doc);
					
					// OK Push everything to the UI
					pushAllPatchSettings();
				}
				catch(Exception ex)
				{
					SevenUp4Live.post(ex.getMessage());   
				}
			}
		} catch (Exception e) {
			SevenUp4Live.post("Could not save patch: " + e);
		} 	

	}


	/**
	 * Push all Patch Settings to the UI
	 */
	private void pushAllPatchSettings() {
		pushLooperSettings();
		pushMelodizer1Settings();
		pushMelodizer2Settings();
		pushControllerSettings();
		pushTiltSettings();
	}
	
	private void pushLooperSettings() {
		
	}
	
	private void pushMelodizer1Settings() {
		
	}
	
	private void pushMelodizer2Settings() {
		
	}
	
	private void pushControllerSettings() {
		
	}
	
	private void pushTiltSettings() {
		
	}
}
