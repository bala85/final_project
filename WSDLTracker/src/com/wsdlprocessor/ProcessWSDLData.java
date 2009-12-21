/**
 * 
 */
package com.wsdlprocessor;

import java.io.File;
import java.util.ArrayList;

import com.wsdlprocessor.process.TokenizeWSDLElements;
import com.wsdltracker.beans.WSDLInfoBean;
import com.wsdltracker.beans.WSDLTermMatrix;
import com.wsdltracker.commons.WSDLStopWords;
import com.wsdltracker.exception.WSDLTrackerException;
import com.wsdltracker.parser.XMLDomParser;

/**
 * @author Bala Rajagopal
 * @serial 0.1
 * @since Dec 2009
 */
public class ProcessWSDLData {
	XMLDomParser parser = new XMLDomParser();
	ArrayList<WSDLInfoBean> infoBeans = new ArrayList<WSDLInfoBean>();

	public ArrayList<WSDLInfoBean> processWSDLData(File[] _inputFiles) throws WSDLTrackerException{
		WSDLInfoBean wsdlData = null;
		for(File _inputFile : _inputFiles){
			wsdlData = new WSDLInfoBean();
			WSDLTermMatrix termMatrix = new WSDLTermMatrix();
			parser.parseXML(_inputFile, wsdlData);
			TokenizeWSDLElements _tokenizer = new TokenizeWSDLElements();
			ArrayList<String> dataTokens = _tokenizer.tokenizeData(wsdlData);
			for(String word : dataTokens){
				if(!WSDLStopWords.isStopWord(word))
					termMatrix.processWsdlTerm(WSDLStopWords.doCustomStemming(word));
			}
			wsdlData.setTermMatrix(termMatrix);
			infoBeans.add(wsdlData);
		}
		return infoBeans;
	}	
}
