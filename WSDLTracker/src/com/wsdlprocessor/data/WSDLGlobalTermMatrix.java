/**
 * 
 */
package com.wsdlprocessor.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Level;

import com.wsdltracker.beans.WSDLTermMatrix;
import com.wsdltracker.exception.WSDLTrackerException;

/**
 * @author Bala Rajagopal
 * @version 0.1
 * @since Dec 2009
 */

public class WSDLGlobalTermMatrix {

	private static HashMap<String, HashMap<String, Integer>> termMatrix = null;
	private static HashMap<String, HashMap<String, Integer>> documentMatrix = null;
	
	public WSDLGlobalTermMatrix(){
		if(termMatrix == null)
			termMatrix = new HashMap<String, HashMap<String, Integer>>();
		if(documentMatrix == null)
			documentMatrix = new HashMap<String, HashMap<String,Integer>>();
	}
	
	public void processTermMatrix(String _wsdlName, WSDLTermMatrix _termMatrix){
		HashMap<String, Integer> inTermMatrix = _termMatrix.getMatrixData();
		if(inTermMatrix!=null && inTermMatrix.size()>0){
			documentMatrix.put(_wsdlName, inTermMatrix);
			Iterator<String> itTerms = inTermMatrix.keySet().iterator();
			while(itTerms.hasNext()){
				String term = itTerms.next();
				HashMap<String, Integer> termInfo = new HashMap<String, Integer>();
				termInfo.put(_wsdlName, inTermMatrix.get(term));
				termMatrix.put(term, termInfo);
			}
		}
	}
	
	public String toString(){
		String retVal = "<NA>|";
		if(termMatrix != null && documentMatrix != null){
			Iterator<String> itDocument = documentMatrix.keySet().iterator();
			while(itDocument.hasNext()){
				String wsdlName = itDocument.next();
				retVal += wsdlName;
				if(itDocument.hasNext()){
					retVal += "|";
				}
			}
			Iterator<String> itTerms = termMatrix.keySet().iterator();
			while(itTerms.hasNext()){
				String term = itTerms.next();
				retVal += "\n"+term+"|";
				itDocument = documentMatrix.keySet().iterator();
				while(itDocument.hasNext()){
					String wsdlName = itDocument.next();
					if(termMatrix.get(term).containsKey(wsdlName)){
						retVal += termMatrix.get(term).get(wsdlName);
					}
					else{
						retVal += "0";
					}
					if(itDocument.hasNext())
						retVal += "|";
				}
			}
		}
		return  retVal;
	}
	
	public void persistData(String sFilePath) throws WSDLTrackerException{
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(sFilePath)));
			bw.write(toString());
			bw.close();
			bw = null;
		} catch (IOException e) {
			throw new WSDLTrackerException(e, this.getClass().getName(), "persistData", Level.FATAL);
		}
	}
}
