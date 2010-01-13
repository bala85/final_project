/**
 * 
 */
package com.wsdlprocessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Level;

import com.wsdltracker.commons.WSDLCommons;
import com.wsdltracker.exception.WSDLTrackerException;
import com.wsdltracker.utils.WSDLTrackerUtilities;

/**
 * @author Bala Rajagopal
 * @since Dec 2009
 * @version 0.1
 */
public class WSDLClientGenerator {

	public void generateWSDLClient(File[] _wsdlFiles) throws WSDLTrackerException{
		for(File wsdlFile : _wsdlFiles){
			String wsdlLocation = wsdlFile.getPath();
			String line = new String();
			try{
				/*String command = WSDLTrackerUtilities.loadProperties(
						WSDLCommons.CONFIG_WSDLTRACKER_FN).
						getProperty(WSDLCommons.CONFIG_JBOSS_BIN_FOLDER)+
						File.separator+
				"wstools.bat " +
				WSDLTrackerUtilities.loadProperties(
						WSDLCommons.CONFIG_WSDLTRACKER_FN).
						getProperty(WSDLCommons.CONFIG_AXIS2_BIN_FOLDER)+
						File.separator+
				"wsdl2java.bat " +
				"-genTestCase false " +
				"-genHelper true " +
				"-genImplTemplate true " +
				"-genRefrencedOnly false " +
				"-genSkeleton true " +
				"-genStub true " +
				"-genWSDD true " +
				"-tm tmfile.map " +
				"-genall false " +
				"-outDir "+WSDLTrackerUtilities.loadProperties
						(WSDLCommons.CONFIG_WSDLTRACKER_FN).
						getProperty
						(WSDLCommons.CONFIG_CLIENTSTUB_OUTFOLDER)+
				" "+wsdlLocation;*/
				String command = "java org.apache.axis.wsdl.WSDL2Java -v -a -W -n -o "+
					WSDLTrackerUtilities.loadProperties
					(WSDLCommons.CONFIG_WSDLTRACKER_FN).
					getProperty
					(WSDLCommons.CONFIG_CLIENTSTUB_OUTFOLDER)+
					" "+wsdlLocation;
				System.out.println(command);
				Process p = Runtime.getRuntime().exec(command);
				BufferedReader input =
					new BufferedReader
					(new InputStreamReader(p.getInputStream()));
				while ((line = input.readLine()) != null) {
					System.out.println(line);
				}
				System.out.println("Done");
				input.close();
			}
			catch(IOException e){
				throw new WSDLTrackerException(e, this.getClass().getName(), "generateWSDLClient", Level.FATAL);
			}
		}
	}
}