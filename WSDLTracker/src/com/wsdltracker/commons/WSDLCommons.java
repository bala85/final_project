/**
 * 
 */
package com.wsdltracker.commons;

/**
 * @author Bala Rajagopal
 * @serial 0.1
 * @since Nov 2009
 */
public class WSDLCommons {
	
	public static final String XSD_NODENAME_ELEMENT = "element";
	public static final String XSD_NODENAME_SIMPLETYPE = "simpleType";
	public static final String XSD_NODENAME_COMPLEXTYPE = "complexType";
	public static final String WSDL_NODENAME_MESSAGE = "message";
	public static final String WSDL_NODENAME_PORTTYPE = "portType";
	public static final String WSDL_NODENAME_OPERATION = "operation";
	public static final String WSDL_NODENAME_BINDING = "binding";
	public static final String WSDL_NODENAME_SERVICE = "service";
	public static final String WSDL_NODENAME_PORT = "port";
	public static final String WSDL_NODENAME_INPUT = "input";
	public static final String WSDL_NODENAME_OUTPUT = "output";
	public static final String WSDL_NODENAME_ADDRESS = "address";
	public static final String WSDL_NODENAME_ATTRIBUTE = "attribute";
	
	public static final short QUALIFIER_XSD = 1;
	
	public static final String CONFIG_LOG4JLOGGER = "log4jLogger";
	public static final String CONFIG_WSDLTRACKER_FN = "properties/WSDLTrackerConfig.properties";
	public static final String CONFIG_INPUTFOLDER = "wsdlTrackerInputFolder";
	
	public static final String FILENAME_LOGFILENAME = "log4jOutputLogFileName";
	
	public static final short LOGTYPE_CONSOLE = 1;
	public static final short LOGTYPE_FILE = 2;
	
	public static final String LOGGER_FILELOG_IDENTIFIER = "log4jFileLoggerHeader";
	
	public static final String QUALIFIER_WSDL_TAG = "wsdl";
	public static final String QUALIFIER_SOAP_TAG = "soap";
	public static final String QUALIFIER_XSD_TAG = "xsd";
}
