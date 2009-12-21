/**
 * 
 */
package com.wsdltracker.beans;

import java.util.HashMap;

/**
 * @author Bala Rajagopal
 * @version 0.1
 * @since Dec 2009
 */
public class WSDLTermMatrix {

	private HashMap<String, Integer> wsdlTermMatrix = 
		new HashMap<String, Integer>();
	
	/**
	 * Process the term
	 * @param _term is the wsdlTerm
	 */
	public void processWsdlTerm(String _term){
		if(!wsdlTermMatrix.containsKey(_term))
			wsdlTermMatrix.put(_term, 0);
		wsdlTermMatrix.put(_term, wsdlTermMatrix.get(_term)+1);
	}
	
	/**
	 * Get the count of unique terms in the WSDL Matrix
	 * @return the count of terms
	 */
	public int getMatrixSize(){
		return wsdlTermMatrix.size();
	}
	
	/**
	 * Get the term matrix of the current WSDL
	 * @return the term matrix of the current WSDL
	 */
	public HashMap<String, Integer> getMatrixData(){
		return wsdlTermMatrix;
	}
	
	/**
	 * Override toString()
	 */
	public String toString(){
		String retVal = "";
		for(String term : wsdlTermMatrix.keySet()){
			retVal += term+": "+wsdlTermMatrix.get(term)+"\n";
		}
		return retVal;
	}
}