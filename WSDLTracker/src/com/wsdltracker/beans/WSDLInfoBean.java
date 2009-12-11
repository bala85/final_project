/**
 * 
 */
package com.wsdltracker.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author Bala Rajagopal
 * @serial 0.1
 * @since October 2009
 */
public class WSDLInfoBean {
	
	private String wsdlName = null;
	private String wsdlBinding = null;
	private HashMap<String, String> wsdlInputs = null; // Mapping to Store WSDL {Input TagName --> ComplexType} 
	private HashMap<String, String> wsdlOutputs = null; // Mapping to Store WSDL {Output TagName --> ComplexType}
	
	// Mapping to capture message --> {partName --> partType} relationship
	private HashMap<String, HashMap<String, String>> wsdlMessages = null; 
	
	// Mapping to capture the various complexTypes --> {elementName --> elementType} relationships
	private HashMap<String, HashMap<String, String>> complexTypes = null; 
	private HashMap<String, String> simpleTypes = null;
	private HashMap<String, String> elementNames = null; // Mapping to capture {elementNames --> complexType} relationship
	private HashMap<String, ArrayList<String>> portNames = null; // Mapping to capture {portName --> operation} relationship
	private HashMap<String, HashMap<String, String>> operations = null;
	
	/**
	 * Default constructor 
	 */
	public WSDLInfoBean(){
		complexTypes = new HashMap<String, HashMap<String,String>>();
		simpleTypes = new HashMap<String, String>();
		elementNames = new HashMap<String, String>();
		wsdlMessages = new HashMap<String, HashMap<String,String>>();
		portNames = new HashMap<String, ArrayList<String>>();
		operations = new HashMap<String, HashMap<String,String>>();		
	}
	
	/**
	 * Add a WSDL Input
	 * @param _tagName is the WSDL Input tagName
	 * @param _ctypeName is the ComplexType associated with the input
	 */
	public void addWsdlInput(String _tagName, String _ctypeName){
		wsdlInputs.put(_tagName, _ctypeName);
	}
	
	/**
	 * Get the complexType associated with a WSDL tagName 
	 * @param _tagName is the tagName
	 * @return the associated complexType value
	 */
	public String getWsdlInput(String _tagName){
		return wsdlInputs.get(_tagName);
	}

	/**
	 * Get all the WSDL input tagNames
	 * @return a String array of all the input elements
	 */
	public String[] getAllWsdlInputNames(){
		ArrayList<String> inputNames = new ArrayList<String>();
		Iterator<String> itInputNames = wsdlInputs.keySet().iterator();
		while(itInputNames.hasNext()){
			inputNames.add((String)itInputNames.next());
		}
		return (String[]) inputNames.toArray();
	}
	
	/**
	 * Get all the WSDL input complexTypes
	 * @return a String array of all the input complexTypes
	 */
	public String[] getAllWsdlInputTypes(){
		ArrayList<String> inputTypes = new ArrayList<String>();
		Iterator<String> itInputTypes = wsdlInputs.values().iterator();
		while(itInputTypes.hasNext()){
			inputTypes.add((String)itInputTypes.next());
		}
		return (String[]) inputTypes.toArray();
	}
	
	/**
	 * Add a WSDL Output
	 * @param _tagName is the WSDL Output tagName
	 * @param _ctypeName is the ComplexType associated with the output
	 */
	public void addWsdlOutput(String _tagName, String _ctypeName){
		wsdlOutputs.put(_tagName, _ctypeName);
	}
	
	/**
	 * Get the complexType associated with WSDL tagName
	 * @param _tagName is the tagName
	 * @return the associated complexType value
	 */
	public String getWsdlOutput(String _tagName){
		return wsdlOutputs.get(_tagName);
	}
	
	/**
	 * Get all the WSDL output tagNames
	 * @return a String array of all the output elements
	 */
	public String[] getAllWsdlOutputNames(){
		ArrayList<String> outputNames = new ArrayList<String>();
		Iterator<String> itOutputNames = wsdlInputs.keySet().iterator();
		while(itOutputNames.hasNext()){
			outputNames.add((String)itOutputNames.next());
		}
		return (String[]) outputNames.toArray();
	}
	
	/**
	 * Get all the WSDL output complexTypes
	 * @return a String array of all the output complexTypes
	 */
	public String[] getAllWsdlOutputTypes(){
		ArrayList<String> outputTypes = new ArrayList<String>();
		Iterator<String> itOutputTypes = wsdlInputs.values().iterator();
		while(itOutputTypes.hasNext()){
			outputTypes.add((String)itOutputTypes.next());
		}
		return (String[]) outputTypes.toArray();
	}
	
	/**
	 * Add a complexType definition
	 * @param _complexTypeName is the name of the complexType
	 * @param _elementName is the elementName inside the complexType
	 * @param _elementType is the elementType
	 */
	public void addComplexTypeDefinition(String _complexTypeName, String _elementName, String _elementType){
		if(!complexTypes.containsKey(_complexTypeName))
			complexTypes.put(_complexTypeName, new HashMap<String, String>());
		complexTypes.get(_complexTypeName).put(_elementName, _elementType);	
	}
	
	/**
	 * Get all the complexType names
	 * @return a String array of all the complexTypes associated with this WSDL
	 */
	public String[] getAllComplexTypes(){
		ArrayList<String> complexTypeNames = new ArrayList<String>();
		Iterator<String> itComplexTypes = complexTypes.keySet().iterator();
		while(itComplexTypes.hasNext()){
			complexTypeNames.add(itComplexTypes.next());
		}
		return (String[]) complexTypeNames.toArray();
	}
	
	/**
	 * Get all the complexType Element names
	 * @return a String array of all the elements associated with this complexTypes in the WSDL
	 */
	public String[] getAllComplexTypeElementNames(String _complexTypeName){
		ArrayList<String> complexTypeElementNames = new ArrayList<String>();
		Iterator<String> itComplexTypeElementNm = complexTypes.get(_complexTypeName).keySet().iterator();
		while(itComplexTypeElementNm.hasNext()){
			complexTypeElementNames.add(itComplexTypeElementNm.next());
		}
		return (String[]) complexTypeElementNames.toArray();
	}
	
	/**
	 * Get the elementTpe of a particular elementName inside a complexType
	 * @param _complexType is the complexType name
	 * @param _elementName is the elementName inside the complexType
	 * @return the ElementType is the corresponding element node inside the given complexType
	 */
	public String getComplexTypeElementType(String _complexType, String _elementName){
		return complexTypes.get(_complexType).get(_elementName);
	}
	
	/**
	 * Add a message definition
	 * @param _messageName is the messageType name
	 * @param _partName is the part name
	 * @param _partType is the part type
	 */
	public void addMessageDefinition(String _messageName, String _partName, String _partType){
		if(!wsdlMessages.containsKey(_messageName))
			wsdlMessages.put(_messageName, new HashMap<String, String>());
		wsdlMessages.get(_messageName).put(_partName, _partType);	
	}
	
	/**
	 * Add a message definition
	 * @return a String array of all messages in the WSDL
	 */
	public String[] getAllMessages(){
		ArrayList<String> messageNames = new ArrayList<String>();
		Iterator<String> itMessages = wsdlMessages.keySet().iterator();
		while(itMessages.hasNext()){
			messageNames.add(itMessages.next());
		}
		return (String[]) messageNames.toArray();
	}
	
	/**
	 * Associate an operation to a port
	 * @param _portName is the portName
	 * @param _operation is the operationName
	 */
	public void addPortDefinition(String _portName, String _operation){
		if(!portNames.containsKey(_portName))
			portNames.put(_portName, new ArrayList<String>());
		portNames.get(_portName).add(_operation);
	}
	
	/**
	 * Get all the ports in the WSDL
	 * @return a String array of all the portNames in the WSDL
	 */
	public String[] getAllPorts(){
		ArrayList<String> portName = new ArrayList<String>();
		Iterator<String> itPortNames = portNames.keySet().iterator();
		while(itPortNames.hasNext()){
			portName.add(itPortNames.next());
		}
		return (String[]) portName.toArray();
	}
	
	/**
	 * Get all the operations associated to a port
	 * @param _portName the name of the port
	 * @return a String array of all the operations associated with a port
	 */
	public String[] getAllOperationsInPort(String _portName){
		ArrayList<String> operationNames =  portNames.get(_portName);
		return (String[]) operationNames.toArray();
	}
	
	/**
	 * Add an operation definition
	 * @param _operationName is the name of the operation
	 * @param _type is the type of operation (input/output)
	 * @param _dataType is the dataType associated to the operation
	 */
	public void addOperationDefinition(String _operationName, String _type, String _dataType){
		if(!operations.containsKey(_operationName))
			operations.put(_operationName, new HashMap<String, String>());
		operations.get(_operationName).put(_type, _dataType);
	}
	
	/**
	 * Get all the operations contained in the WSDL
	 * @return a String array of all the operations in the WSDL
	 */
	public String[] getAllOperations(){
		ArrayList<String> operationName = new ArrayList<String>();
		Iterator<String> itOperationNames = operations.keySet().iterator();
		while(itOperationNames.hasNext()){
			operationName.add(itOperationNames.next());
		}
		return (String[]) operationName.toArray();
	}
	
	/**
	 * Check for availability of an Operation
	 * @param _operationName is the operationName
	 * @return boolean value indicating the existence
	 */
	public boolean checkForOperation(String _operationName){
		return operations.containsKey(_operationName);
	}
	/**
	 * Add a simpleType definition
	 * @param _simpleTypeName is the simpleType name
	 * @param _simpleTypeType is the simpleType type (tongue twister :P)
	 */
	public void addSimpleTypeDefinition(String _simpleTypeName, String _simpleTypeType){
		if(!simpleTypes.containsKey(_simpleTypeName)){
			simpleTypes.put(_simpleTypeName, _simpleTypeType);
		}
	}
	
	/**
	 * Get all the simpleType names
	 * @return a String array of all the simpleType names
	 */
	public String[] getAllSimpleTypes(){
		ArrayList<String> simpleTypes = new ArrayList<String>();
		Iterator<String> itSimpleTypes = simpleTypes.iterator();
		while(itSimpleTypes.hasNext()){
			simpleTypes.add(itSimpleTypes.next());
		}
		return (String[]) simpleTypes.toArray();
	}
	
	/**
	 * Add an elementName which resides inside the WSDL Types 
	 * @param _elementName is the elementName
	 * @param _elementType is the elementType
	 */
	public void addWSDLTypeElementName(String _elementName, String _elementType){
		elementNames.put(_elementName, _elementType);
	}
	
	/**
	 * @return the name
	 */
	public String getWsdlName() {
		return wsdlName;
	}

	/**
	 * @param name the name to set
	 */
	public void setWsdlName(String name) {
		this.wsdlName = name;
	}

	/**
	 * @return the wsdlBinding
	 */
	public String getWsdlBinding() {
		return wsdlBinding;
	}

	/**
	 * @param wsdlBinding the wsdlBinding to set
	 */
	public void setWsdlBinding(String wsdlBinding) {
		this.wsdlBinding = wsdlBinding;
	}
}