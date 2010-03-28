/**
 * 
 */
package com.wsdltracker;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.wsdltracker.commons.WSDLCommons;
import com.wsdltracker.exception.WSDLTrackerException;
import com.wsdltracker.utils.WSDLTrackerUtilities;

/**
 * @author Bala Rajagopal
 * @since Dec 2009
 * @serial 0.1
 */
public class WSDLTrackerLogger {

	private static String className = WSDLTrackerLogger.class.getName();
	private static Logger logger = null;
	/**
	 * logTypes
	 * 1 - Console
	 * 2 - File
	 */
	private static short logType = 1;
	
	/**
	 * Initialize the most default logger
	 */
	private static void defaultWSDLTrackerLogger(){
		logger = Logger.getLogger(className);
	}
	
	/**
	 * Initialize a logger based on the parameters supplied
	 * @param _logType is the logType (1 = Console; 2 = File)
	 * @throws WSDLTrackerException
	 */
	protected static void setWSDLTrackerLogger(short _logType) throws WSDLTrackerException{
		logger = Logger.getLogger(className);
		if(_logType ==1 || _logType == 2){
			logType = _logType;
		} // else it will be a console appender by default
		initLogging();
	}
	
	/**
	 * Initialize the logger
	 * @throws WSDLTrackerException
	 */
	private static void initLogging() throws WSDLTrackerException{
		if(logType == 2){
			try {
				// Setting the logger to FileLogger
				logger = Logger.getLogger(
				WSDLTrackerUtilities.loadProperties(
						WSDLCommons.CONFIG_WSDLTRACKER_FN).
						getProperty(WSDLCommons.LOGGER_FILELOG_IDENTIFIER));
				System.setProperty("wsdltracker.logfileName", WSDLCommons.FILENAME_LOGFILENAME);
			} catch (FileNotFoundException e) {
				logType = 1; //In case of exception, revert to default logger
				throw new WSDLTrackerException(e, className, "initLogging", Level.WARN);
			} catch (IOException e) {
				logType = 1; //In case of exception, revert to default logger
				throw new WSDLTrackerException(e, className, "initLogging", Level.WARN);
			}
		}
		try {
			PropertyConfigurator.configure(WSDLTrackerUtilities.getWSDLTrackerProperty(WSDLCommons.CONFIG_LOG4JLOGGER));
		} catch (FileNotFoundException e) {
			throw new WSDLTrackerException(e, className, "initLogging", Level.ERROR);
		} catch (IOException e) {
			throw new WSDLTrackerException(e, className, "initLogging", Level.ERROR);
		}
		logger.info("Logging initialized successfully");
	}
	
	/**
	 * Write the message supplied into the corresponding log
	 * @param _level the nature of the message
	 * @param _message the data to be written to the log
	 */
	public static void logThis(Level _level, Object _message){
		if(logger == null)
			defaultWSDLTrackerLogger();
		
		if(_level.equals(Level.TRACE))
			logger.trace(_message);
		if(_level.equals(Level.DEBUG))
			logger.debug(_message);
		if(_level.equals(Level.INFO))
			logger.info(_message);
		if(_level.equals(Level.WARN))
			logger.warn(_message);
		if(_level.equals(Level.ERROR))
			logger.error(_message);
		if(_level.equals(Level.FATAL))
			logger.fatal(_message);
	}
}