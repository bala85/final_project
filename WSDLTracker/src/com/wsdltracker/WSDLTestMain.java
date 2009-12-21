/**
 * 
 */
package com.wsdltracker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Level;

import com.wsdlprocessor.ProcessWSDLData;
import com.wsdltracker.beans.WSDLInfoBean;
import com.wsdltracker.beans.WSDLTermMatrix;
import com.wsdltracker.commons.WSDLCommons;
import com.wsdltracker.exception.WSDLTrackerException;
import com.wsdltracker.utils.WSDLTrackerUtilities;

/**
 * @author Bala Rajagopal
 *
 */
public class WSDLTestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			WSDLTrackerLogger.setWSDLTrackerLogger(WSDLCommons.LOGTYPE_FILE);
			File[] inFiles = null;
			File inputFolder = new File(
					WSDLTrackerUtilities.loadProperties(
							WSDLCommons.CONFIG_WSDLTRACKER_FN).
							getProperty(WSDLCommons.CONFIG_INPUTFOLDER));
			if(inputFolder.isDirectory()){
				inFiles = inputFolder.listFiles();
			}
			ProcessWSDLData wsdlProcessor = new ProcessWSDLData();
			HashMap<String, Object> globalTermMatrix = new HashMap<String, Object>();
			ArrayList<WSDLInfoBean> wsdlData = wsdlProcessor.processWSDLData(inFiles);
			for(WSDLInfoBean _currBean : wsdlData){
				WSDLTermMatrix termMatrix = _currBean.getTermMatrix();
				globalTermMatrix.put(_currBean.getServiceName(), termMatrix.getMatrixData());
			}
			WSDLTrackerLogger.logThis(Level.DEBUG, globalTermMatrix);
		} catch (WSDLTrackerException e) {
			if(e.getSeverity()==Level.FATAL)
				System.exit(1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}