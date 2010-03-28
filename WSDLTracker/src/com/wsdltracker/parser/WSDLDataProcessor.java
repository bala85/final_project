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
		String qualifier = null;
		if(currNodeName.indexOf(":")>0){
			qualifier = currNodeName.substring(0, currNodeName.indexOf(":"));
			currNodeName = currNodeName.substring(currNodeName.indexOf(":")+1);
		}
		
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
				_bean.addWSDLTypeElementName(elementName, elementType);
			}
			else{
				String elementName = _currentNode.getAttributes().getNamedItem("name").getNodeValue();
				String elementType = _currentNode.getAttributes().getNamedItem("type").getNodeValue();;
				_bean.addWSDLTypeElementName(elementName, elementType);
			}
		}
		else if(currNodeName.equals(WSDLCommons.WSDL_NODENAME_ATTRIBUTE)){
			Node parentCtypeNode = WSDLHelper.isInStack(_nodeStack, WSDLCommons.XSD_NODENAME_COMPLEXTYPE, WSDLCommons.QUALIFIER_XSD);
			if(parentCtypeNode!=null){
				if(qualifier.equals(WSDLCommons.QUALIFIER_XSD_TAG)){
					String parentCtName = parentCtypeNode.getAttributes().getNamedItem("name").getNodeValue();
					String arrayType =  _currentNode.getAttributes().
						getNamedItem(WSDLCommons.QUALIFIER_WSDL_TAG+":arrayType").getNodeValue();
					arrayType = arrayType.substring(arrayType.contains(":")?(arrayType.indexOf(":")+1):0, arrayType.length()-2);
					_bean.addArrayComplexType(parentCtName, arrayType);
				}
			}
		}
		else if(currNodeName.equals(WSDLCommons.XSD_NODENAME_SIMPLETYPE)){
			String elementName = _currentNode.getAttributes().getNamedItem("name").getNodeValue();
			String elementType = _currentNode.getChildNodes().item(1).getAttributes().getNamedItem("base").getNodeValue();;
			_bean.addSimpleTypeDefinition(elementName, elementType);
		}
		else if(currNodeName.equals(WSDLCommons.WSDL_NODENAME_MESSAGE)){
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
		else if(currNodeName.equals(WSDLCommons.WSDL_NODENAME_PORTTYPE)){
			String portName = _currentNode.getAttributes().getNamedItem("name").getNodeValue();
			NodeList nlOperations = _currentNode.getChildNodes();
			for(int i=0; i<nlOperations.getLength(); i++){
				Node childNode = nlOperations.item(i); 
				if(childNode.getNodeType() == Node.ELEMENT_NODE){
					String childNodeName = childNode.getNodeName();
					childNodeName = childNodeName.substring(childNodeName.indexOf(":")+1); 
					if(childNodeName.equals(WSDLCommons.WSDL_NODENAME_OPERATION)){
						String operationName = childNode.getAttributes().getNamedItem("name").getNodeValue();
						_bean.addPortTypeDefinition(portName, operationName);
						NodeList nlOperationIo = childNode.getChildNodes();
						for(int iOper=0; iOper<nlOperationIo.getLength(); iOper++){
							Node ioChild = nlOperationIo.item(iOper);
							if(ioChild.getNodeType() == Node.ELEMENT_NODE){
								String ioNodeName = ioChild.getNodeName();
								ioNodeName = ioNodeName.substring(ioNodeName.indexOf(":")+1);
								if(ioNodeName.equals(WSDLCommons.WSDL_NODENAME_INPUT) 
										|| ioNodeName.equals(WSDLCommons.WSDL_NODENAME_OUTPUT)){
									String ioMessage = ioChild.getAttributes().getNamedItem("message").getNodeValue();
									ioMessage = ioMessage.substring(ioMessage.indexOf(":")+1);
									if(ioNodeName.equals(WSDLCommons.WSDL_NODENAME_INPUT))
										_bean.addWsdlInput(operationName, ioMessage);
									else
										_bean.addWsdlOutput(operationName, ioMessage);
								}
							}
						}
					}
				}
			}
		}
		else if(currNodeName.equals(WSDLCommons.WSDL_NODENAME_OPERATION)){
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
		else if(currNodeName.equals(WSDLCommons.WSDL_NODENAME_BINDING)){
			if(qualifier==null || qualifier.equals(WSDLCommons.QUALIFIER_WSDL_TAG)){
				String _bindingName = _currentNode.getAttributes().
					getNamedItem("name").getNodeValue();
				String _bindingType = _currentNode.getAttributes().
					getNamedItem("type").getNodeValue();
				_bean.addBindingDefinition(_bindingName, _bindingType);
			}
		}
		else if(currNodeName.equals(WSDLCommons.WSDL_NODENAME_SERVICE)){
			String _ServiceName = _currentNode.getAttributes().
				getNamedItem("name").getNodeValue();
			_bean.setServiceName(_ServiceName);
		}
		else if(currNodeName.equals(WSDLCommons.WSDL_NODENAME_PORT)){
			String _serviceName = _currentNode.getAttributes().
				getNamedItem("name").getNodeValue();
			String _binding = _currentNode.getAttributes().
				getNamedItem("binding").getNodeValue();
			_bean.addPortDefinition(_serviceName, _binding);
		}
		else if(qualifier!=null && 
				currNodeName.equals(WSDLCommons.WSDL_NODENAME_ADDRESS)){
			String _wsdlLoc = _currentNode.getAttributes().getNamedItem("location").getNodeValue();
			_bean.setWsdlAddress(_wsdlLoc);
		}
	}
}
