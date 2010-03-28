/**
 * 
 */
package com.wsdltracker.commons;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Bala Rajagopal
 * @serial 0.1
 * @since Dec 2009
 */
public class WSDLStopWords {
	
	private static ArrayList<String> stopWords = new ArrayList<String>();
	private static HashMap<String, String> simpleStemData = new HashMap<String, String>();
	private static String[] stopWordList = {"do",
									 "url",
									 "host",
									 "get",
									 "set",
									 "web",
									 "http",
									 "ftp",
									 "array",
									 "put",
									 "page",
									 "in",
									 "is",
									 "start",
									 "end",
									 "request",
									 "of",
									 "and",
									 "by",
									 "soap"};

	/**
	 * Check if a word occurs in the stop word list
	 * @param _word is the input word
	 * @return if the word is a stopWord or not
	 */
	public static boolean isStopWord(String _word){
		boolean retVal = false;
		if(stopWords.size() == 0)
			loadStopWords();
		if(simpleStemData.size() == 0)
			loadSimpleStemData();
		retVal = stopWords.contains(_word.toLowerCase());
		return retVal;
	}
	
	/**
	 * Perform simple Custom Stemming
	 * @param _word is the input word
	 * @return the stemmed result
	 */
	public static String doCustomStemming(String _word){
		String retVal = _word;
		for(String suffix : simpleStemData.keySet()){
			if(_word.toLowerCase().endsWith(suffix.toLowerCase())){
				String newSuffix = simpleStemData.get(suffix);
				retVal = _word.substring(0, (_word.length()-suffix.length()))+newSuffix;
			}
		}
		return retVal.trim().toLowerCase();
	}
	
	/**
	 * Load all the stopWords into the lookup table
	 */
	private static void loadStopWords(){
		for(String _stopWord : stopWordList){
			stopWords.add(_stopWord);
		}
	}
	
	private static void loadSimpleStemData(){
		simpleStemData.put("ies", "y");
		simpleStemData.put("ing", "");
		simpleStemData.put("able", "");
		simpleStemData.put("encoding", "encode");
		simpleStemData.put("encodable", "encode");
	}
}