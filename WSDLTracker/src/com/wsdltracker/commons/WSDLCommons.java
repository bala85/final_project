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
	
	public static final short QUALIFIER_XSD = 1;
	public static final short QUALIFIER_WSDL = 2;
	
	public static final String CONFIG_LOG4JLOGGER = "log4jLogger";
	public static final String CONFIG_WSDLTRACKER_FN = "properties/WSDLTrackerConfig.properties";
	
	public static final String FILENAME_LOGFILENAME = "log4jOutputLogFileName";
	
	public static final short LOGTYPE_CONSOLE = 1;
	public static final short LOGTYPE_FILE = 2;
	
	public static final String LOGGER_FILELOG_IDENTIFIER = "log4jFileLoggerHeader";
}
