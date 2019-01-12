/*
 * 
 * 
 * 
 * 
 * 
 * 
 */
package com.djt.cvpp.ota.orfin.client.odl;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.djt.cvpp.ota.common.exception.EntityAlreadyExistsException;
import com.djt.cvpp.ota.common.exception.EntityDoesNotExistException;
import com.djt.cvpp.ota.common.exception.ValidationException;
import com.djt.cvpp.ota.orfin.api.odl.OdlList;
import com.djt.cvpp.ota.orfin.api.odl.OptimizedDataListApplicationService;
import com.djt.cvpp.ota.orfin.client.AbstractOrfinServiceClient;
import com.djt.cvpp.ota.orfin.odl.event.OrfinOdlEvent;

/**
 *
 * @author tmyers1@yahoo.com (Tom Myers)
 *
 */
@Component
public class OptimizedDataListApplicationServiceClient extends AbstractOrfinServiceClient implements OptimizedDataListApplicationService {
	
	
	private RestTemplate restTemplate;
	
	public OptimizedDataListApplicationServiceClient() {
		
		this.restTemplate = super.restTemplate();
	}
	
	public com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl createOdl(
		String odlName) 
	throws 
		EntityAlreadyExistsException, 
		ValidationException {
		
		String uri = buildEndpointUri(ODL_URI, CREATE_ODL_URI);
		uri =  UriComponentsBuilder
			.fromHttpUrl(uri)
			.queryParam(ODL_NAME, odlName)
			.toUriString();
		
		return this.restTemplate.getForObject(uri, com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl.class);
	}
	
	@GetMapping(CREATE_NETWORK_URI)
	public @ResponseBody com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl createNetwork(
		String odlName,
		String networkName,
		String protocol,
		String dataRate,
		String dclName,
		String networkPins)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException,
		ValidationException {
		
		String uri = buildEndpointUri(ODL_URI, CREATE_NETWORK_URI);
		uri =  UriComponentsBuilder
			.fromHttpUrl(uri)
			.queryParam(ODL_NAME, odlName)
			.queryParam(NETWORK_NAME, networkName)
			.queryParam(PROTOCOL, protocol)
			.queryParam(DATA_RATE, dataRate)
			.queryParam(DCL_NAME, dclName)
			.queryParam(NETWORK_PINS, networkPins)
			.toUriString();
		
		return this.restTemplate.getForObject(uri, com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl.class);
	}

	public @ResponseBody com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl createNode(
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
		ValidationException {

		String uri = buildEndpointUri(ODL_URI, CREATE_NODE_URI);
		uri =  UriComponentsBuilder
			.fromHttpUrl(uri)
			.queryParam(ODL_NAME, odlName)
			.queryParam(NETWORK_NAME, networkName)
			.queryParam(NODE_ACRONYM, nodeAcronym)
			.queryParam(NODE_ADDRESS, nodeAddress)
			.queryParam(GATEWAY_NODE_ID, gatewayNodeId)
			.queryParam(GATEWAY_TYPE, gatewayType)
			.queryParam(HAS_CONDITION_BASED_ON_DTC, hasConditionBasedOnDtc)
			.queryParam(IS_OVTP, isOvtp)
			.queryParam(OVTP_DESTINATION_ADDRESS, ovtpDestinationAddress)
			.queryParam(SPECIFICATION_CATEGORY_TYPE, specificationCategoryType)
			.queryParam(DIAGNOSTIC_SPECIFICATION_RESPONSE, diagnosticSpecificationResponse)
			.queryParam(ACTIVATION_TIME, activationTime)
			.toUriString();
		
		return this.restTemplate.getForObject(uri, com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl.class);
	}
	
	public @ResponseBody com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl addIgnoredDidsToNode(
		String odlName,
		String networkName,
		String nodeAcronym,
		String nodeAddress,
		String ignoredDids)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException,
		ValidationException {
		
		String uri = buildEndpointUri(ODL_URI, ADD_IGNORED_DIDS_TO_NODE_URI);
		uri =  UriComponentsBuilder
			.fromHttpUrl(uri)
			.queryParam(ODL_NAME, odlName)
			.queryParam(NETWORK_NAME, networkName)
			.queryParam(NODE_ACRONYM, nodeAcronym)
			.queryParam(NODE_ADDRESS, nodeAddress)
			.queryParam(IGNORED_DIDS, ignoredDids)
			.toUriString();
		
		return this.restTemplate.getForObject(uri, com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl.class);
	}
	
	public com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl createDid(
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
		ValidationException {
		
		String uri = buildEndpointUri(ODL_URI, CREATE_DID_URI);
		uri =  UriComponentsBuilder
			.fromHttpUrl(uri)
			.queryParam(ODL_NAME, odlName)
			.queryParam(NETWORK_NAME, networkName)
			.queryParam(NODE_ACRONYM, nodeAcronym)
			.queryParam(NODE_ADDRESS, nodeAddress)
			.queryParam(DID_NAME, didName)
			.queryParam(DESCRIPTION, description)
			.queryParam(VIN_SPECIFIC_FLAG, vinSpecificDidFlag)
			.queryParam(DIRECT_CONFIGURATION_DID_FLAG, directConfigurationDidFlag)
			.queryParam(PRIVATE_NETWORK_DID_FLAG, privateNetworkDidFlag)
			.toUriString();
		
		return this.restTemplate.getForObject(uri, com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl.class);
	}
	
	public OdlList getAllOdls() {
		
		String uri = buildEndpointUri(ODL_URI, GET_ALL_ODLS_URI);
		return this.restTemplate.getForObject(uri, OdlList.class);
	}
	
	public com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl getOdlByName(
		String odlName) 
	throws 
		EntityDoesNotExistException, 
		ValidationException {
		
		String uri = buildEndpointUri(ODL_URI, GET_ODL_BY_NAME_URI);
		uri =  UriComponentsBuilder
			.fromHttpUrl(uri)
			.queryParam(ODL_NAME, odlName)
			.toUriString();
		
		return this.restTemplate.getForObject(uri, com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl.class);
	}
	
	public com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl getOdlByProgramCodeAndModelYear(
		String programCode,
		Integer modelYear) 
	throws 
		EntityDoesNotExistException, 
		ValidationException {
		
		String uri = buildEndpointUri(ODL_URI, GET_ODL_BY_PROGRAM_CODE_AND_MODEL_YEAR_URI);
		uri =  UriComponentsBuilder
			.fromHttpUrl(uri)
			.queryParam(PROGRAM_CODE, programCode)
			.queryParam(MODEL_YEAR, modelYear)
			.toUriString();
		
		return this.restTemplate.getForObject(uri, com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl.class);
	}
	
	public com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl addEcgSignalToOdl(
		String odlName,
		String ecgSignalName)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException,
		ValidationException {
		
		String uri = buildEndpointUri(ODL_URI, ADD_ECG_SIGNAL_TO_ODL_URI);
		uri =  UriComponentsBuilder
			.fromHttpUrl(uri)
			.queryParam(ODL_NAME, odlName)
			.queryParam(ECG_SIGNAL_NAME, ecgSignalName)
			.toUriString();
		
		return this.restTemplate.getForObject(uri, com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl.class);
	}
	
	public com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl addCustomOdlToOdl(
		String odlName,
		String customOdlName,
		String customOdlNodeList)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException,
		ValidationException {
		
		String uri = buildEndpointUri(ODL_URI, ADD_CUSTOM_ODL_TO_ODL_URI);
		uri =  UriComponentsBuilder
			.fromHttpUrl(uri)
			.queryParam(ODL_NAME, odlName)
			.queryParam(CUSTOM_ODL_NAME, customOdlName)
			.queryParam(CUSTOM_ODL_NODE_LIST, customOdlNodeList)
			.toUriString();
		
		return this.restTemplate.getForObject(uri, com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl.class);
	}
	
	public String renderFullOdlWithEcgSignalsForProgram(
        String programCode,
        Integer modelYear)
	throws 
		EntityDoesNotExistException,
		ValidationException {

		String uri = buildEndpointUri(ODL_URI, RENDER_FULL_ODL_WITH_ECG_SIGNALS_FOR_PROGRAM_URI);
		uri =  UriComponentsBuilder
			.fromHttpUrl(uri)
			.queryParam(PROGRAM_CODE, programCode)
			.queryParam(MODEL_YEAR, modelYear)
			.toUriString();
		
		return this.restTemplate.getForObject(uri, String.class);
	}

	public String renderOdlForProgram(
        String programCode,
        Integer modelYear,
		String customOdlName,
		Boolean includeEcgSignals)
	throws 
		EntityDoesNotExistException,
		ValidationException {

		String uri = buildEndpointUri(ODL_URI, RENDER_ODL_FOR_PROGRAM_URI);
		uri =  UriComponentsBuilder
			.fromHttpUrl(uri)
			.queryParam(PROGRAM_CODE, programCode)
			.queryParam(MODEL_YEAR, modelYear)
			.queryParam(CUSTOM_ODL_NAME, customOdlName)
			.queryParam(INCLUDE_ECG_SIGNALS, includeEcgSignals)
			.toUriString();
		
		return this.restTemplate.getForObject(uri, String.class);
	}
	
	public OrfinOdlEvent publishOrfinOdlEvent(
		String owner,
		String programCode,
		Integer modelYear,
		String odlName)
	throws 
		ValidationException {
	
		String uri = buildEndpointUri(ODL_URI, PUBLISH_ODL_EVENT_URI);
		uri =  UriComponentsBuilder
			.fromHttpUrl(uri)
			.queryParam(ODL_NAME, odlName)
			.queryParam(PROGRAM_CODE, programCode)
			.queryParam(MODEL_YEAR, modelYear)
			.queryParam(OWNER, owner)
			.toUriString();
		
		return this.restTemplate.getForObject(uri, OrfinOdlEvent.class);
	}	
}
