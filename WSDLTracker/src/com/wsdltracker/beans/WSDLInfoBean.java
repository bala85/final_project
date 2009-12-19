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
	private String wsdlAddress = null;
	private HashMap<String, String> wsdlBindings = null;
	private HashMap<String, String> wsdlInputs = null; // Mapping to Store WSDL {OperationName --> Message} 
	private HashMap<String, String> wsdlOutputs = null; // Mapping to Store WSDL {OperationName --> Message}
	
	// Mapping to capture message --> {partName --> partType} relationship
	private HashMap<String, HashMap<String, String>> wsdlMessages = null; 
	
	// Mapping to capture the various complexTypes --> {elementName --> elementType} relationships
	private HashMap<String, HashMap<String, String>> complexTypes = null; 
	private HashMap<String, String> simpleTypes = null;
	private HashMap<String, String> elementNames = null; // Mapping to capture {elementNames --> complexType} relationship
	private HashMap<String, ArrayList<String>> portTypeNames = null; // Mapping to capture {portTypeName --> operation} relationship
	private HashMap<String, HashMap<String, String>> operations = null;
	private HashMap<String, String> port = null;
	
	/**
	 * Default constructor 
	 */
	public WSDLInfoBean(){
		complexTypes = new HashMap<String, HashMap<String,String>>();
		simpleTypes = new HashMap<String, String>();
		elementNames = new HashMap<String, String>();
		wsdlMessages = new HashMap<String, HashMap<String,String>>();
		portTypeNames = new HashMap<String, ArrayList<String>>();
		operations = new HashMap<String, HashMap<String,String>>();
		wsdlBindings = new HashMap<String, String>();
		port = new HashMap<String, String>();
		wsdlInputs = new HashMap<String, String>();
		wsdlOutputs = new HashMap<String, String>();
	}
	
	/**
	 * Add a WSDL Input
	 * @param _operationName is the Operation Name
	 * @param _messageName is the Message Name
	 */
	public void addWsdlInput(String _operationName, String _messageName){
		wsdlInputs.put(_operationName, _messageName);
	}
	
	/**
	 * Get the Message associated with a WSDL Operation 
	 * @param _operationName is the tagName
	 * @return the associated complexType value
	 */
	public String getWsdlInput(String _operationName){
		return wsdlInputs.get(_operationName);
	}
	
	/**
	 * Get all the WSDL input mesasgeTypes
	 * @return a String array of all the input mesasgeTypes
	 */
	public String[] getAllWsdlInputMessages(){
		ArrayList<String> messageTypes = new ArrayList<String>();
		Iterator<String> itMessageTypes = wsdlInputs.values().iterator();
		while(itMessageTypes.hasNext()){
			messageTypes.add((String)itMessageTypes.next());
		}
		String[] retVal = new String[messageTypes.size()];
		messageTypes.toArray(retVal);
		return retVal;
	}
	
	/**
	 * Add a WSDL Output
	 * @param _operationName is the WSDL Operation Name
	 * @param _messageName is the Message Name
	 */
	public void addWsdlOutput(String _operationName, String _messageName){
		wsdlOutputs.put(_operationName, _messageName);
	}
	
	/**
	 * Get the message associated with WSDL operation
	 * @param _operationName is the operationName
	 * @return the associated Message Name
	 */
	public String getWsdlOutput(String _operationName){
		return wsdlOutputs.get(_operationName);
	}
	
	/**
	 * Get all the WSDL output messageTypes
	 * @return a String array of all the output messageTypes
	 */
	public String[] getAllWsdlOutputMessages(){
		ArrayList<String> messageTypes = new ArrayList<String>();
		Iterator<String> itOutputTypes = wsdlInputs.values().iterator();
		while(itOutputTypes.hasNext()){
			messageTypes.add((String)itOutputTypes.next());
		}
		String[] retVal = new String[messageTypes.size()];
		messageTypes.toArray(retVal);
		return retVal;
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
		String[] retVal = new String[complexTypeNames.size()];
		complexTypeNames.toArray(retVal);
		return retVal;
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
		String[] retVal = new String[complexTypeElementNames.size()];
		complexTypeElementNames.toArray(retVal);
		return retVal;
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
		String[] retVal = new String[messageNames.size()];
		messageNames.toArray(retVal);
		return retVal;
	}
	
	/**
	 * Associate an operation to a port
	 * @param _portName is the portName
	 * @param _operation is the operationName
	 */
	public void addPortTypeDefinition(String _portTypeName, String _operation){
		if(!portTypeNames.containsKey(_portTypeName))
			portTypeNames.put(_portTypeName, new ArrayList<String>());
		portTypeNames.get(_portTypeName).add(_operation);
	}
	
	/**
	 * Associate a port with the service
	 * @param _portName is the portName
	 * @param _binding is the binding
	 */
	public void addPortDefinition(String _portName, String _binding){
		port.put(_portName, _binding);
	}
		
	/**
	 * Get all the port names
	 * @return
	 */
	public String[] getAllPortNames(){
		ArrayList<String> keySet = new ArrayList<String>();
		Iterator<String> itKeys = port.keySet().iterator();
		while(itKeys.hasNext()){
			keySet.add(itKeys.next());
		}
		String[] retVal = new String[keySet.size()];
		keySet.toArray(retVal);
		return retVal;
	}
	
	/**
	 * Get the binding associated with a port
	 * @param _portName is the portName
	 * @return the binding associated to the port
	 */
	public String getBindingByPortName(String _portName){
		return port.get(_portName);
	}
	
	/**
	 * Get all the portTypes in the WSDL
	 * @return a String array of all the portTypeNames in the WSDL
	 */
	public String[] getAllPortTypes(){
		ArrayList<String> portName = new ArrayList<String>();
		Iterator<String> itPortNames = portTypeNames.keySet().iterator();
		while(itPortNames.hasNext()){
			portName.add(itPortNames.next());
		}
		String[] retVal = new String[portName.size()];
		portName.toArray(retVal);
		return retVal;
	}
	
	/**
	 * Get all the operations associated to a port
	 * @param _portName the name of the port
	 * @return a String array of all the operations associated with a port
	 */
	public String[] getAllOperationsInPortType(String _portName){
		ArrayList<String> operationNames =  portTypeNames.get(_portName);
		return (String[]) operationNames.toArray();
	}
	
	/**
	 * Add a binding definition
	 * @param _bindingName is the binding name
	 * @param _portType is the port type associated to the binding
	 */
	public void addBindingDefinition(String _bindingName, String _portType){
		wsdlBindings.put(_bindingName, _portType);
	}
	
	/**
	 * Get all the Bindings in the WSDL
	 * @return a String array of all the binding names
	 */
	public String[] getAllWsdlBindings(){
		ArrayList<String> bindingNames = new ArrayList<String>();
		Iterator<String> itBindingNames = wsdlBindings.keySet().iterator();
		while(itBindingNames.hasNext()){
			bindingNames.add(itBindingNames.next());
		}
		String[] retVal = new String[bindingNames.size()];
		bindingNames.toArray(retVal);
		return retVal;
	}
	
	/**
	 * Get the port type of a binding
	 * @param _bindingName is the binding name
	 * @return the port type associated with the binding
	 */
	public String getBindingPortType(String _bindingName){
		return wsdlBindings.get(_bindingName);
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
		String[] retVal = new String[operationName.size()];
		operationName.toArray(retVal);
		return retVal;
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
		ArrayList<String> sTypes = new ArrayList<String>();
		Iterator<String> itSimpleTypes = simpleTypes.keySet().iterator();
		while(itSimpleTypes.hasNext()){
			sTypes.add(itSimpleTypes.next());
		}
		String[] retVal = new String[simpleTypes.size()];
		sTypes.toArray(retVal);
		return retVal;
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
	 * Get the list of all the Elements in the WSDL
	 * @return a String array of all the Element names
	 */
	public String[] getAllElementNames(){
		ArrayList<String> eleNames = new ArrayList<String>();
		Iterator<String> itElementNames = elementNames.keySet().iterator();
		while(itElementNames.hasNext()){
			eleNames.add(itElementNames.next());
		}
		String[] retVal = new String[eleNames.size()];
		eleNames.toArray(retVal);
		return retVal;
	}
	
	/**
	 * Get the elementType by its Name
	 * @param _elementName is the ElementName
	 * @return the elementType with the given ElementName
	 */
	public String getElementTypeByName(String _elementName){
		return elementNames.get(_elementName);
	}
	
	/**
	 * @return the name
	 */
	public String getServiceName() {
		return wsdlName;
	}

	/**
	 * @param name the name to set
	 */
	public void setServiceName(String _name) {
		this.wsdlName = _name;
	}
	
	/**
	 * @return the wsdlAddress
	 */
	public String getWsdlAddress() {
		return wsdlAddress;
	}

	/**
	 * @param wsdlAddress the wsdlAddress to set
	 */
	public void setWsdlAddress(String wsdlAddress) {
		this.wsdlAddress = wsdlAddress;
	}

	/**
	 * Print the details of the WSDL Bean
	 */
	public String toString(){
		String retVal = null;
		retVal = "\n------------ WSDL Information ------------\n";
		retVal += "\nService Name: \t\t\t"+this.getServiceName();
		retVal += "\nService Location: \t\t"+this.getWsdlAddress();
		retVal += "\nNumber of Inputs: \t\t"+this.getAllWsdlInputMessages().length;
		retVal += "\nNumber of Outputs: \t\t"+this.getAllWsdlOutputMessages().length;
		retVal += "\nNumber of Messages: \t\t"+this.getAllMessages().length;
		retVal += "\nNumber of SimpleTypes: \t\t"+this.getAllSimpleTypes().length;
		retVal += "\nNumber of ComplexTypes: \t"+this.getAllComplexTypes().length;
		retVal += "\nNumber of Elements: \t\t"+this.getAllElementNames().length;
		retVal += "\nNumber of PortTypes: \t\t"+this.getAllPortTypes().length;
		retVal += "\nNumber of Operations: \t\t"+this.getAllOperations().length;
		retVal += "\nNumber of Ports: \t\t"+this.getAllPortNames().length;
		return retVal;
	}
}