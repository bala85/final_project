/**
 * 
 */
package com.wsdltracker.parser;

import java.util.Stack;

import org.w3c.dom.Node;

import com.wsdltracker.beans.WSDLInfoBean;
import com.wsdltracker.commons.WSDLCommons;
import com.wsdltracker.helper.WSDLHelper;

/**
 * @author Bala Rajagopal
 * @serial 0.1
 * @since Nov 2009
 */
public class WSDLDataProcessor {

	protected static void doWSDLNodeProcessing(Node _currentNode, Stack<Node> _nodeStack, WSDLInfoBean _bean){
		String currNodeName = _currentNode.getNodeName();
		currNodeName = currNodeName.substring(currNodeName.indexOf(":")+1);
		if(currNodeName.equals(WSDLCommons.XSD_NODENAME_ELEMENT)){
			Node parentCtypeNode = WSDLHelper.isInStack(_nodeStack, WSDLCommons.XSD_NODENAME_COMPLEXTYPE, WSDLCommons.QUALIFIER_XSD);
			if(parentCtypeNode!=null){
				String parentCtName = parentCtypeNode.getAttributes().getNamedItem("name").getNodeValue();
				String elementName = _currentNode.getAttributes().getNamedItem("name").getNodeValue();
				String elementType = null;
				try{
					elementType = _currentNode.getAttributes().getNamedItem("type").getNodeValue();
				}
				catch(NullPointerException e){}
				_bean.addComplexTypeDefinition(parentCtName, elementName, elementType);
				//System.out.print(parentCtName+"~~"+elementName+"~~"+elementType);
			}
			else{
				String elementName = _currentNode.getAttributes().getNamedItem("name").getNodeValue();
				String elementType = _currentNode.getAttributes().getNamedItem("type").getNodeValue();;
				_bean.addWSDLTypeElementName(elementName, elementType);
				//System.out.print(elementName+"~~"+elementType);
			}
			//System.out.println();
		}
		if(currNodeName.equals(WSDLCommons.XSD_NODENAME_SIMPLETYPE)){
			String elementName = _currentNode.getAttributes().getNamedItem("name").getNodeValue();
			String elementType = _currentNode.getChildNodes().item(1).getAttributes().getNamedItem("base").getNodeValue();;
			//System.out.print(elementName+"~~"+elementType);
			_bean.addSimpleTypeDefinition(elementName, elementType);
			//System.out.println();
		}
	}
}
