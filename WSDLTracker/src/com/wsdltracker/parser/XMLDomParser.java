/**
 * 
 */
package com.wsdltracker.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.wsdltracker.beans.WSDLInfoBean;
import com.wsdltracker.helper.WSDLHelper;


/**
 * @author Bala Rajagopal
 * @serial 0.1
 * @since Nov 2009
 */

public class XMLDomParser implements ErrorHandler {
	
	private ArrayList<String> nodePath = new ArrayList<String>();
	private WSDLInfoBean wsdlBean = null;

	public void parseXML(File inFile, WSDLInfoBean _wsdlInfoBean) throws IOException, SAXException, ParserConfigurationException
	{
		wsdlBean = _wsdlInfoBean;
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inFile);
		Node n = doc.getDocumentElement();
		if(n.getNodeType() == Node.ELEMENT_NODE)
			parseNode(n);
	}
	
	public void parseXML(String inData) throws IOException, SAXException, ParserConfigurationException
	{
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inData);
		Node n = doc.getDocumentElement();
		if(n.getNodeType() == Node.ELEMENT_NODE)
			parseNode(n);
	}
	

	private void parseNode(Node n)
	{
		if(n.hasChildNodes())
		{
			nodePath.add(n.getNodeName());
			System.out.println(WSDLHelper.getLastNodeName(nodePath));
			for(int i=0; i<n.getAttributes().getLength();i++){
				Node attNode = n.getAttributes().item(i);
				nodePath.add(attNode.getNodeName());
				System.out.println(WSDLHelper.getNodePath(nodePath)+": "+attNode.getNodeValue());
				nodePath.remove(attNode.getNodeName());
			}
			NodeList nl = n.getChildNodes();
			Node newNode = null;
			for(int i=1;i<(nl.getLength());i++)
			{
				newNode = nl.item(i);
				parseNode(newNode);
			}
			System.out.println(WSDLHelper.getLastNodeName(nodePath));
			nodePath.remove(n.getNodeName());
		}
		else
		{
			if(n.getNodeValue() == null)
			{
				nodePath.add(n.getNodeName());
				System.out.println(WSDLHelper.getLastNodeName(nodePath));
				for(int i=0; i<n.getAttributes().getLength();i++){
					Node attNode = n.getAttributes().item(i);
					nodePath.add(attNode.getNodeName());
					System.out.println(WSDLHelper.getNodePath(nodePath)+": "+attNode.getNodeValue());
					nodePath.remove(attNode.getNodeName());
				}
				System.out.println(WSDLHelper.getLastNodeName(nodePath));
				nodePath.remove(n.getNodeName());
			}
		}
	}

	/**
     * Captures,logs and throws Exceptions for the Fatal Errors generated
     * during XSD validation
     */
    public void error(SAXParseException exception) throws SAXParseException {
        System.out.println("error: "+ exception.getMessage());
        throw exception;
    }
    
    /**
     * Captures,logs and throws Exceptions for the Fatal Errors generated
     * during XSD validation
     */
    public void fatalError(SAXParseException exception) throws SAXParseException {
        System.out.println("fatalError: "+ exception.getMessage());
        throw exception;
    }
    
    /**
     * Captures,logs and throws Exceptions for the Fatal Errors generated
     * during XSD validation
     */
    public void warning(SAXParseException exception) throws SAXParseException {
        System.out.println("warning: "+ exception.getMessage());
        throw exception;
    }
}