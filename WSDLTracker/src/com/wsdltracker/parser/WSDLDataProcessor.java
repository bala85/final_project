/**
 * 
 */
package com.wsdltracker.parser;

import java.util.Stack;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
			}
			else{
				String elementName = _currentNode.getAttributes().getNamedItem("name").getNodeValue();
				String elementType = _currentNode.getAttributes().getNamedItem("type").getNodeValue();;
				_bean.addWSDLTypeElementName(elementName, elementType);
			}
		}
		if(currNodeName.equals(WSDLCommons.XSD_NODENAME_SIMPLETYPE)){
			String elementName = _currentNode.getAttributes().getNamedItem("name").getNodeValue();
			String elementType = _currentNode.getChildNodes().item(1).getAttributes().getNamedItem("base").getNodeValue();;
			_bean.addSimpleTypeDefinition(elementName, elementType);
		}
		if(currNodeName.equals(WSDLCommons.WSDL_NODENAME_MESSAGE)){
			String messageName = _currentNode.getAttributes().getNamedItem("name").getNodeValue();
			NodeList nlParts = _currentNode.getChildNodes();
			for(int i=0; i<nlParts.getLength(); i++){
				Node childNode = nlParts.item(i); 
				if(childNode.getNodeType() == Node.ELEMENT_NODE){
					String partName = childNode.getAttributes().getNamedItem("name").getNodeValue();
					String partType = childNode.getAttributes().getNamedItem("type").getNodeValue();
					partType = partType.substring(partType.indexOf(":")+1);
					_bean.addMessageDefinition(messageName, partName, partType);
				}
			}
		}
		if(currNodeName.equals(WSDLCommons.WSDL_NODENAME_PORTTYPE)){
			String portName = _currentNode.getAttributes().getNamedItem("name").getNodeValue();
			NodeList nlOperations = _currentNode.getChildNodes();
			for(int i=0; i<nlOperations.getLength(); i++){
				Node childNode = nlOperations.item(i); 
				if(childNode.getNodeType() == Node.ELEMENT_NODE){
					String childNodeName = childNode.getNodeName();
					childNodeName = childNodeName.substring(childNodeName.indexOf(":")+1); 
					if(childNodeName.equals(WSDLCommons.WSDL_NODENAME_OPERATION)){
						String operationName = (_currentNode.getChildNodes().item(1)).getAttributes().getNamedItem("name").getNodeValue();
						_bean.addPortDefinition(portName, operationName);
					}
				}
			}
		}
		if(currNodeName.equals(WSDLCommons.WSDL_NODENAME_OPERATION)){
			Node operationNameNode = _currentNode.getAttributes().getNamedItem("name");
			if(operationNameNode!=null){
				String operationName = operationNameNode.getNodeValue();
				if(!_bean.checkForOperation(operationName)){
					NodeList nlData = _currentNode.getChildNodes();
					for(int i=0; i<nlData.getLength(); i++){
						Node childNode = nlData.item(i); 
						if(childNode.getNodeType() == Node.ELEMENT_NODE){
							String nodeName = childNode.getNodeName();
							nodeName = nodeName.substring(nodeName.indexOf(":")+1);
							String nodeType = childNode.getAttributes().getNamedItem("message").getNodeValue();
							nodeType = nodeType.substring(nodeType.indexOf(":")+1);
							_bean.addOperationDefinition(operationName, nodeName, nodeType);
						}
					}
				}
			}
		}
		else{
			
		}
	}
}
