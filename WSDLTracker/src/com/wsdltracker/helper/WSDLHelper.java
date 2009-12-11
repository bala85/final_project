/**
 * 
 */
package com.wsdltracker.helper;

import java.util.ArrayList;

/**
 * @author Bala Rajagopal
 * @serial 0.1
 * @since Nov 2009
 */
public class WSDLHelper {
	
	public static String getNodePath(ArrayList<String> _nodePath){
		String retVal = "";
		String[] values = new String[_nodePath.size()];
		_nodePath.toArray(values);
		for(String currNode : values){
			retVal += currNode+"|";
		}
		return retVal.substring(0, retVal.length()-1);
	}
	
	public static String getLastNodeName(ArrayList<String> _nodePath){
		return _nodePath.get(_nodePath.size()-1);
	}
}
