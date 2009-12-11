/**
 * 
 */
package com.wsdltracker.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.wsdltracker.commons.WSDLCommons;

/**
 * @author Bala Rajagopal
 * @since Dec 2009
 * @serial 0.1
 */
public class WSDLTrackerUtilities {
	
	public static Properties loadProperties(String _url) throws FileNotFoundException, IOException{
		Properties retVal = new Properties();
		retVal.load(new FileReader(_url));
		return retVal;
	}
	
	public static String getWSDLTrackerProperty(String _propertyName) throws FileNotFoundException, IOException{
		return loadProperties(WSDLCommons.CONFIG_WSDLTRACKER_FN).getProperty(_propertyName);
	}
}
