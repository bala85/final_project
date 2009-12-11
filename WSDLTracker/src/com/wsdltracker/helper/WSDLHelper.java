/**
 * 
 */
package com.wsdltracker.helper;

import java.util.ArrayList;
import java.util.Stack;

import org.w3c.dom.Node;

import com.wsdltracker.commons.WSDLCommons;

/**
 * @author Bala Rajagopal
 * @serial 0.1
 * @since Nov 2009
 */
public class WSDLHelper {
	
	/**
	 * Get the Node path trace
	 * @param _nodePath is the list of nodes traversed
	 * @return the String representation of the traversal
	 */
	public static String getNodePath(ArrayList<String> _nodePath){
		String retVal = "";
		String[] values = new String[_nodePath.size()];
		_nodePath.toArray(values);
		for(String currNode : values){
			retVal += currNode+"|";
		}
		return retVal.substring(0, retVal.length()-1);
	}
	
	/**
	 * Get the name of the node that was most recently processed
	 * @param _nodePath is the list of nodes traversed
	 * @return the name of the last node to have been processed
	 */
	public static String getLastNodeName(ArrayList<String> _nodePath){
		return _nodePath.get(_nodePath.size()-1);
	}
	
	/**
	 * Get the Node stack trace as a string
	 * @param _nodeStack is the stack containing all the nodes processed in the current branch
	 * @return the String representation of the path (root to currentNode) 
	 */
	public static String getStackTrace(Stack<Node> _nodeStack){
		String sRetVal = "";
		for(int iCtr=0; iCtr<_nodeStack.size(); iCtr++){
			sRetVal += _nodeStack.get(iCtr).getNodeName()+" --> ";
		}
		return sRetVal;
	}
	
	/**
	 * Check if a node is available in the current execution path
	 * @param _nodeStack is the stack containing all the nodes processed in the current branch
	 * @param _nodeName is the nodeName
	 * @param _qualifier is the qualifier used (xsd/wsdl)
	 * @return the reference to the node if it is available in stack
	 */
	public static Node isInStack(Stack<Node> _nodeStack, String _nodeName, short _qualifier){
		Node retVal = null;
		String refNodeName = getQualifiedNodeName(_qualifier, _nodeName);
		for(int iCtr=(_nodeStack.size()-1); iCtr>=0; iCtr--){
			Node thisNode = _nodeStack.get(iCtr);
			if(thisNode.getNodeName().equals(refNodeName)){
				retVal = thisNode;
				break;
			}
		}
		return retVal;
	}
	
	/**
	 * Get the qualified nodeName
	 * @param _qualifierSelection is the qualifier used (1 = xsd; 2=wsdl)
	 * @param _tagName is the tagName
	 * @return the fully qualified nodeName
	 */
	private static String getQualifiedNodeName(short _qualifierSelection, String _tagName){
		if(_qualifierSelection == WSDLCommons.QUALIFIER_XSD)
			return "xsd:"+_tagName;
		else
			return "wsdl:"+_tagName;
	}
}
