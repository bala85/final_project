/**
 * 
 */
package com.wsdltracker.main;

import java.io.File;

import com.wsdltracker.beans.WSDLInfoBean;
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
			File inputFile = new File("inputs/1personbicycle4wheeledcar_price_service.wsdl");
			//File inputFile = new File("1personbicyclecar_price_Kohlservice.wsdl");
			WSDLInfoBean wsdlData = new WSDLInfoBean();
			parser.parseXML(inputFile, wsdlData);
			//System.out.println(wsdlData);
		} catch (WSDLTrackerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
