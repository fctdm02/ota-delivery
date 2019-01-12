/*
 * 
 * 
 * 
 * 
 * 
 * 
 */
package com.djt.cvpp.ota.orfin.api.odl;


import com.djt.cvpp.ota.common.exception.EntityAlreadyExistsException;
import com.djt.cvpp.ota.common.exception.EntityDoesNotExistException;
import com.djt.cvpp.ota.common.exception.ValidationException;
import com.djt.cvpp.ota.orfin.odl.event.OrfinOdlEvent;

/**
 *
 * @author tmyers1@yahoo.com (Tom Myers)
 *
 */
public interface OptimizedDataListApplicationService {

	String ODL_URI = "/api/v1/odl";
	
	String ODL_NAME = "odlName";
	String NETWORK_NAME = "networkName";
	String PROTOCOL = "protocol";
	String DATA_RATE = "dataRate";
	String DCL_NAME = "dclName";
	String NETWORK_PINS = "networkPins";
	String NODE_ACRONYM = "nodeAcronym";
	String NODE_ADDRESS = "nodeAddress";
	String GATEWAY_NODE_ID = "gatewayNodeId";
	String GATEWAY_TYPE = "gatewayType";
	String HAS_CONDITION_BASED_ON_DTC = "hasConditionBasedOnDtc";
	String DIAGNOSTIC_SPECIFICATION_RESPONSE = "diagnosticSpecificationResponse";
	String IS_OVTP = "isOvtp";
	String OVTP_DESTINATION_ADDRESS = "ovtpDestinationAddress";
	String SPECIFICATION_CATEGORY_TYPE = "specificationCategoryType";
	String ACTIVATION_TIME = "activationTime";
	String IGNORED_DIDS = "ignoredDids";
	String DID_NAME = "didName";
	String DESCRIPTION = "description";
	String VIN_SPECIFIC_FLAG = "vinSpecificDidFlag";
	String DIRECT_CONFIGURATION_DID_FLAG = "directConfigurationDidFlag";
	String PRIVATE_NETWORK_DID_FLAG = "privateNetworkDidFlag";
    String PROGRAM_CODE = "programCode";
    String MODEL_YEAR = "modelYear";
    String ECG_SIGNAL_NAME = "ecgSignalName";
    String CUSTOM_ODL_NODE_LIST = "customOdlNodeList";
	String CUSTOM_ODL_NAME = "customOdlName";
	String INCLUDE_ECG_SIGNALS = "includeEcgSignals";
	String OWNER = "owner";
	
	
	/**
	 * 
	 * @param odlName
	 * 
	 * @return
	 * 
	 * @throws EntityAlreadyExistsException
	 * @throws ValidationException
	 */
	String CREATE_ODL_URI = "/createOdl";
	com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl createOdl(
		String odlName) 
	throws 
		EntityAlreadyExistsException, 
		ValidationException;
	
	
	/**
	 * 
	 * @param odlName
	 * @param networkName
	 * @param protocol
	 * @param dataRate
	 * @param dclName
	 * @param networkPins
	 * 
	 * @return
	 * 
	 * @throws EntityDoesNotExistException
	 * @throws EntityAlreadyExistsException
	 * @throws ValidationException
	 */
	String CREATE_NETWORK_URI = "/createNetwork";
	com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl createNetwork(
		String odlName,
		String networkName,
		String protocol,
		String dataRate,
		String dclName,
		String networkPins)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException,
		ValidationException;

	
	/**
	 * 
	 * @param odlName
	 * @param networkName
	 * @param nodeAcronym
	 * @param nodeAddress
	 * @param fesn
	 * @param publicKeyHash
	 * @param gatewayType
	 * @param hasConditionBasedOnDtc
	 * @param isOvtp
	 * @param ovtpDestinationAddress
	 * @param specificationCategoryType
	 * @param activationTime
	 * @param vehicleInhibitActivationTime
	 * 
	 * @return
	 * 
	 * @throws EntityDoesNotExistException
	 * @throws EntityAlreadyExistsException
	 * @throws ValidationException
	 */
	String CREATE_NODE_URI = "/createNode";
	com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl createNode(
		String odlName,
		String networkName,
		String nodeAcronym,
		String nodeAddress,
		String gatewayNodeId,
		String gatewayType,
		Boolean hasConditionBasedOnDtc,
		Boolean isOvtp,
		String ovtpDestinationAddress,
		String specificationCategoryType,
		Integer diagnosticSpecificationResponse,
		Integer activationTime)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException,
		ValidationException;

	
	/**
	 * 
	 * @param odlName
	 * @param networkName
	 * @param nodeAcronym
	 * @param nodeAddress
	 * @param ignoredDids
	 * 
	 * @return
	 * 
	 * @throws EntityDoesNotExistException
	 * @throws EntityAlreadyExistsException
	 * @throws ValidationException
	 */
	String ADD_IGNORED_DIDS_TO_NODE_URI = "/addIgnoredDidsToNode";
	com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl addIgnoredDidsToNode(
		String odlName,
		String networkName,
		String nodeAcronym,
		String nodeAddress,
		String ignoredDids)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException,
		ValidationException;
	
	
	/**
	 * 
	 * @param odlName
	 * @param networkName
	 * @param nodeAcronym
	 * @param nodeAddress
	 * @param didName
	 * @param description
	 * @param vinSpecificDidFlag
	 * @param directConfigurationDidFlag
	 * @param privateNetworkDidFlag
	 * 
	 * @return
	 * 
	 * @throws EntityDoesNotExistException
	 * @throws EntityAlreadyExistsException
	 * @throws ValidationException
	 */
	String CREATE_DID_URI = "/createDid";
	com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl createDid(
		String odlName,
		String networkName,
		String nodeAcronym,
		String nodeAddress,
		String didName,
		String description,
		Boolean vinSpecificDidFlag,
		Boolean directConfigurationDidFlag,
		Boolean privateNetworkDidFlag)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException,
		ValidationException;
	

	/**
	 * 
	 * @param odlName
	 * @param ecgSignalName
	 * 
	 * @return
	 * 
	 * @throws EntityDoesNotExistException
	 * @throws EntityAlreadyExistsException
	 * @throws ValidationException
	 */
	String ADD_ECG_SIGNAL_TO_ODL_URI = "/addEcgSignalToOdl";
	com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl addEcgSignalToOdl(
		String odlName,
		String ecgSignalName)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException,
		ValidationException;

	
	/**
	 * 
	 * @param odlName
	 * @param customOdlName
	 * @param customOdlNodeList A comma separated list of nodes where each node is of the form: <code>nodeAcronym_nodeAddress</code> and node *must* already be associated to the parent, or master, ODL
	 * e.g. AA_00,BB_01,CC_02
	 * @return
	 * @throws EntityDoesNotExistException
	 * @throws EntityAlreadyExistsException
	 * @throws ValidationException
	 */
	String ADD_CUSTOM_ODL_TO_ODL_URI = "/addCustomOdlToOdl";
	com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl addCustomOdlToOdl(
		String odlName,
		String customOdlName,
		String customOdlNodeList)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException,
		ValidationException;

		
	/**
	 * 
	 * @return
	 */
	String GET_ALL_ODLS_URI = "/getAllOdls";
	OdlList getAllOdls();

	
	/**
	 * 
	 * @param odlName
	 * 
	 * @return
	 * 
	 * @throws EntityDoesNotExistException
	 * @throws ValidationException
	 */
	String GET_ODL_BY_NAME_URI = "/getOdlByName";
	com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl getOdlByName(
		String odlName) 
	throws 
		EntityDoesNotExistException, 
		ValidationException;

	
	/**
	 * 
	 * @param programCode
	 * @param modelYear
	 * 
	 * @return
	 * 
	 * @throws EntityDoesNotExistException
	 * @throws ValidationException
	 */
	String GET_ODL_BY_PROGRAM_CODE_AND_MODEL_YEAR_URI = "/getOdlByProgramCodeAndModelYear";
	com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl getOdlByProgramCodeAndModelYear(
        String programCode,
        Integer modelYear)
	throws 
		EntityDoesNotExistException, 
		ValidationException;
	
	
	/**
	 * 
	 * Convenience method for the other "render" operation, with *all* nodes processed and *all* defined ECG signals included.
	 * 
	 * @param programCode
	 * @param modelYear
	 * 
	 * @return
	 * 
	 * @throws EntityDoesNotExistException
	 * @throws ValidationException
	 */
	String RENDER_FULL_ODL_WITH_ECG_SIGNALS_FOR_PROGRAM_URI = "/renderFullOdlWithEcgSignalsForProgram";
	String renderFullOdlWithEcgSignalsForProgram(
        String programCode,
        Integer modelYear)
	throws 
		EntityDoesNotExistException,
		ValidationException;

	
	/**
	 * 
	 * @param programCode
	 * @param modelYear
	 * @param customOdlName If specified, then the rendered ODL JSON will only include those networks/nodes that 
	 * specified by the "custom ODL", identified by <code>customOdlName</code>
	 * @param includeEcgSignals If <code>true</code>, then the rendered ODL JSON will include ECG signal information.
	 * 
	 * @return
	 * 
	 * @throws EntityDoesNotExistException
	 * @throws ValidationException
	 */
	String RENDER_ODL_FOR_PROGRAM_URI = "/renderOdlForProgram";
	String renderOdlForProgram(
        String programCode,
        Integer modelYear,
		String customOdlName,
		Boolean includeEcgSignals)
	throws 
		EntityDoesNotExistException,
		ValidationException;
	
	
	/**
	 * 
	 * @param owner
	 * @param programCode
	 * @param modelYear
	 * @param odlName
	 * @return
	 * @throws ValidationException
	 */
	String PUBLISH_ODL_EVENT_URI = "/publishOrfinOdlEvent";
	OrfinOdlEvent publishOrfinOdlEvent(
		String owner,
		String programCode,
		Integer modelYear,
		String odlName)
	throws 
		ValidationException;	
}
