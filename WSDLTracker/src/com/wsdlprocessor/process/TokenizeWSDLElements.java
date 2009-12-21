/**
 * 
 */
package com.wsdlprocessor.process;

import java.util.ArrayList;

import com.wsdltracker.beans.WSDLInfoBean;

/**
 * @author Bala Rajagopal
 * @version 0.1
 * @since Dec 2009
 */
public class TokenizeWSDLElements {
	
	private ArrayList<String> retVal = new ArrayList<String>();
	
	/**
	 * Tokenize the input data
	 * @param _infoBean is the Input WSDL information bean
	 */
	public ArrayList<String> tokenizeData(WSDLInfoBean _infoBean){	
		//Tokenize the inputs and get the Data
		String[] simpleTypes = _infoBean.getAllSimpleTypes();
		String[] complexTypes = _infoBean.getAllComplexTypes();
		String[] elementNames = _infoBean.getAllElementNames();
		String[] messages = _infoBean.getAllMessages();
		String[] operations = _infoBean.getAllOperations();
		String[] portNames = _infoBean.getAllPortNames();
		String[] bindings = _infoBean.getAllWsdlBindings();
		String[] ctArrayTypes = _infoBean.getAllCtArrayNames();
		processTokens(simpleTypes);
		processTokens(complexTypes);
		processTokens(elementNames);
		processTokens(messages);
		processTokens(operations);
		processTokens(portNames);
		processTokens(bindings);
		processTokens(ctArrayTypes);
		return retVal;
	}
	
	/**
	 * Process every token from an array of tokens
	 * @param _wsdlData is the String array containing the tokens
	 */
	private void processTokens(String[] _wsdlData){
		for(String token: _wsdlData){
			retVal.addAll(camelCaseProcessor(token));
		}
	}
	
	// TODO: Try to improve the efficiency of camelCaseProcessor
	/**
	 * Process camelCase words to extract tokens
	 * @param _word is the the camelCase word
	 */
	private ArrayList<String> camelCaseProcessor(String _word){
		_word = _word.replaceAll("[^a-zA-Z0-9]", "");
		int lastCtr = 0;
		ArrayList<String> ccProcessor = new ArrayList<String>();
		ArrayList<Integer> iCts = new ArrayList<Integer>();
		if(_word.toUpperCase().equals(_word)){
			ccProcessor.add(_word);
		}
		else{
			for(int ctr = 0; ctr < _word.length(); ctr++){
				if(_word.substring(ctr,ctr+1).equals(_word.substring(ctr,ctr+1).toUpperCase())){
					if(iCts.size() == 1){
						String value = _word.substring(iCts.remove(0), ctr);
						if(value.length()>1)
							ccProcessor.add(value);
						lastCtr = ctr;
					}
					if(ctr != 0 && _word.substring(lastCtr, ctr).length()>1){
						String value = _word.substring(lastCtr, ctr);
						if(value.length()>1)
							ccProcessor.add(value);
					}
					iCts.add(ctr);
				}
			}
			if(iCts.size()>0 || (lastCtr == 0 && _word.length()>0)){
				String value = _word.substring((iCts.size()>0?iCts.remove(0):0),_word.length());
				if(value.length()>1)
					ccProcessor.add(value);
			}
		}
		return ccProcessor;
	}
}