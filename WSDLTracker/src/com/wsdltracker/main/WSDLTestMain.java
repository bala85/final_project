/**
 * 
 */
package com.wsdltracker.main;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.wsdltracker.beans.WSDLInfoBean;
import com.wsdltracker.parser.XMLDomParser;

/**
 * @author Bala
 *
 */
public class WSDLTestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		XMLDomParser parser = new XMLDomParser();
		try {
			File inputFile = new File("1personbicycle4wheeledcar_price_service.wsdl");
			//File inputFile = new File("1personbicyclecar_price_Kohlservice.wsdl");
			WSDLInfoBean wsdlData = new WSDLInfoBean();
			parser.parseXML(inputFile, wsdlData);
			//System.out.println(wsdlData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
