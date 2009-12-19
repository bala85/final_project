/**
 * 
 */
package com.wsdltracker;

import java.io.File;

import org.apache.log4j.Level;

import com.wsdltracker.beans.WSDLInfoBean;
import com.wsdltracker.commons.WSDLCommons;
import com.wsdltracker.exception.WSDLTrackerException;
import com.wsdltracker.parser.XMLDomParser;

/**
 * @author Bala Rajagopal
 *
 */
public class WSDLTestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		XMLDomParser parser = new XMLDomParser();
		try {
			WSDLTrackerLogger.setWSDLTrackerLogger(WSDLCommons.LOGTYPE_CONSOLE);
			File inputFile = new File("1personbicycle4wheeledcar_price_service.wsdl");
			//File inputFile = new File("1personbicyclecar_price_Kohlservice.wsdl");
			//File inputFile = new File("AmazonWS.wsdl");
			//File inputFile = new File("google.wsdl");
			WSDLInfoBean wsdlData = new WSDLInfoBean();
			parser.parseXML(inputFile, wsdlData);
			WSDLTrackerLogger.logThis(Level.DEBUG,wsdlData);
		} catch (WSDLTrackerException e) {
			if(e.getSeverity()==Level.FATAL)
				System.exit(1);
		}
	}
}