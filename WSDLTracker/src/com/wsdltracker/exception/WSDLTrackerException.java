package com.wsdltracker.exception;

import org.apache.log4j.Level;

import com.wsdltracker.WSDLTrackerLogger;


/**
 * @author Bala Rajagopal
 * @since Dec 2009
 * @serial 0.1
 */
public class WSDLTrackerException extends Throwable {

	private static final long serialVersionUID = -4582122815353162393L;
	
	private Level severity;
	/**
	 * Overloaded constructor
	 * @param _e is the exception
	 * @param _className the name of the class where the exception occurred
	 * @param _methodName the name of the method where the exception occurred
	 * @param _level the severity of the exception
	 */
	public WSDLTrackerException(Exception _e, String _className, String _methodName, Level _level){
		severity = _level;
		WSDLTrackerLogger.logThis(Level.INFO, "Triggered inside Class: \'"+_className+"\', Method: \'"+_methodName+"\'");
		WSDLTrackerLogger.logThis(severity, _e);
	}
	
	/**
	 * @return the severity
	 */
	public Level getSeverity() {
		return severity;
	}
}