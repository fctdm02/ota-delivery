/*
 * 
 * 
 * 
 * 
 * 
 * 
 */
package com.djt.cvpp.ota.orfin.client.policy;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.djt.cvpp.ota.common.exception.EntityAlreadyExistsException;
import com.djt.cvpp.ota.common.exception.EntityDoesNotExistException;
import com.djt.cvpp.ota.common.exception.ValidationException;
import com.djt.cvpp.ota.orfin.api.policy.PolicySetApplicationService;
import com.djt.cvpp.ota.orfin.api.policy.PolicySetList;
import com.djt.cvpp.ota.orfin.client.AbstractOrfinServiceClient;
import com.djt.cvpp.ota.orfin.policy.event.OrfinPolicySetEvent;

/**
 *
 * @author tmyers1@yahoo.com (Tom Myers)
 *
 */
@Component
public class PolicySetApplicationServiceClient extends AbstractOrfinServiceClient implements PolicySetApplicationService {
	
	private RestTemplate restTemplate;
	
	public PolicySetApplicationServiceClient() {
		
		this.restTemplate = super.restTemplate();
	}
	
	public com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet createPolicySet(
		String policySetName) 
	throws 
		EntityAlreadyExistsException, 
		ValidationException {
		
		String uri = buildEndpointUri(POLICY_SET_URI, CREATE_POLICY_SET_URI);
		uri =  UriComponentsBuilder
			.fromHttpUrl(uri)
			.queryParam(POLICY_SET_NAME, policySetName)
			.toUriString();
		
		return this.restTemplate.getForObject(uri, com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet.class);
	}
	
	public PolicySetList getAllPolicySets() {
		
		String uri = buildEndpointUri(POLICY_SET_URI, GET_ALL_POLICY_SETS_URI);
		return this.restTemplate.getForObject(uri, PolicySetList.class);
	}
	
	public com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet getPolicySetByName(
		String policySetName) 
	throws 
		EntityDoesNotExistException, 
		ValidationException {
		
		String uri = buildEndpointUri(POLICY_SET_URI, GET_POLICY_SET_BY_NAME_URI);
		uri =  UriComponentsBuilder
			.fromHttpUrl(uri)
			.queryParam(POLICY_SET_NAME, policySetName)
			.toUriString();
		
		return this.restTemplate.getForObject(uri, com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet.class);
	}
	
	public com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet createVehiclePolicy(
		String policySetName,
		String policyName,
		String policyDescription,
		Object policyValue,
		Boolean allowRegionalChangeable,
		Boolean allowUserChangeable,
		Boolean allowServiceChangeable,
		Boolean allowCustomerFeedback,
		String hmi,
		String vehicleHmiFile,
		String phone,
		String otaFunction,
		String policyValueType,
		String policyValueConstraints)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException,
		ValidationException {
		
		String uri = buildEndpointUri(POLICY_SET_URI, CREATE_VEHICLE_POLICY_URI);
		uri =  UriComponentsBuilder
			.fromHttpUrl(uri)
			.queryParam(POLICY_SET_NAME, policySetName)
			.queryParam(POLICY_NAME, policyName)
			.queryParam(POLICY_DESCRIPTION, policyDescription)
			.queryParam(POLICY_VALUE, policyValue)
			.queryParam(ALLOW_REGIONAL_CHANGEABLE, allowRegionalChangeable)
			.queryParam(ALLOW_USER_CHANGEABLE, allowUserChangeable)
			.queryParam(ALLOW_SERVICE_CHANGEABLE, allowServiceChangeable)
			.queryParam(ALLOW_CUSTOMER_FEEDBACK, allowCustomerFeedback)
			.queryParam(HMI, hmi)
			.queryParam(VEHICLE_HMI_FILE, vehicleHmiFile)
			.queryParam(PHONE, phone)
			.queryParam(OTA_FUNCTION, otaFunction)
			.queryParam(POLICY_VALUE_TYPE, policyValueType)
			.queryParam(POLICY_VALUE_CONSTRAINTS, policyValueConstraints)
			.toUriString();
		
		return this.restTemplate.getForObject(uri, com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet.class);
	}	
	
	public com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet createCloudPolicy(
		String policySetName,
		String policyName,
		String policyDescription,
		Object policyValue,
		String policyValueType,
		String policyValueConstraints)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException,
		ValidationException {
		
		String uri = buildEndpointUri(POLICY_SET_URI, CREATE_CLOUD_POLICY_URI);
		uri =  UriComponentsBuilder
			.fromHttpUrl(uri)
			.queryParam(POLICY_SET_NAME, policySetName)
			.queryParam(POLICY_NAME, policyName)
			.queryParam(POLICY_DESCRIPTION, policyDescription)
			.queryParam(POLICY_VALUE, policyValue)
			.queryParam(POLICY_VALUE_TYPE, policyValueType)
			.queryParam(POLICY_VALUE_CONSTRAINTS, policyValueConstraints)
			.toUriString();
		
		return this.restTemplate.getForObject(uri, com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet.class);
	}
	
	public com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet createProgramLevelPolicyOverride(
		String policyName,
		String programCode,
		Integer modelYear,
		Object policyValue)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException,
		ValidationException {
		
		String uri = buildEndpointUri(POLICY_SET_URI, CREATE_PROGRAM_LEVEL_POLICY_OVERRIDE_URI);
		uri =  UriComponentsBuilder
			.fromHttpUrl(uri)
			.queryParam(POLICY_NAME, policyName)
			.queryParam(PROGRAM_CODE, programCode)
			.queryParam(MODEL_YEAR, modelYear)
			.queryParam(POLICY_VALUE, policyValue)
			.toUriString();
		
		return this.restTemplate.getForObject(uri, com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet.class);
	}
	
	public com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet createRegionLevelPolicyOverride(
		String policySetName,
		String policyName,
		String regionCode,	
		Object policyValue)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException,
		ValidationException {
		
		String uri = buildEndpointUri(POLICY_SET_URI, CREATE_REGION_LEVEL_POLICY_OVERRIDE_URI);
		uri =  UriComponentsBuilder
			.fromHttpUrl(uri)
			.queryParam(POLICY_SET_NAME, policySetName)
			.queryParam(POLICY_NAME, policyName)
			.queryParam(REGION_CODE, regionCode)
			.queryParam(POLICY_VALUE, policyValue)
			.toUriString();
		
		return this.restTemplate.getForObject(uri, com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet.class);
	}
	
	public com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet renamePolicySet(
		String oldPolicySetName,
		String newPolicySetName)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException,
		ValidationException {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet renamePolicy(
		String policySetName,
		String oldPolicyName,
		String newPolicyName)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException,
		ValidationException {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet updateVehiclePolicy(
		String policySetName,
		String policyName,
		String policyDescription,
		Object policyValue,
		Boolean allowRegionalChangeable,
		Boolean allowUserChangeable,
		Boolean allowServiceChangeable,
		Boolean allowCustomerFeedback,
		String hmi,
		String vehicleHmiFile,
		String phone,
		String otaFunction,
		String policyValueType,
		String policyValueConstraints)
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		throw new RuntimeException("Not implemented yet.");
	}

	public com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet updateCloudPolicy(
		String policySetName,
		String policyName,
		String policyDescription,
		Object policyValue,
		String policyValueType,
		String policyValueConstraints)
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet updateProgramLevelPolicyOverride(
		String policyName,
		String programCode,
		Integer modelYear,
		Object policyValue)
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet updateRegionLevelPolicyOverride(
		String policySetName,
		String policyName,
		String regionCode,
		Object policyValue)
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public void deletePolicySet(
		String policySetName) 
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet deletePolicy(
		String policySetName,
		String policyName)
	throws
		EntityDoesNotExistException,
		ValidationException {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet deleteProgramLevelPolicyOverride(
		String policyName,
		String programCode,
		Integer modelYear)
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet deleteRegionLevelPolicyOverride(
		String policySetName,
		String policyName,
		String regionCode)
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public com.djt.cvpp.ota.orfin.policy.mapper.dto.region.Region createRegion(
		String regionCode,
		String countryName)
	throws 
		EntityAlreadyExistsException,
		ValidationException {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public List<com.djt.cvpp.ota.orfin.policy.mapper.dto.region.Region> getAllRegions() {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public com.djt.cvpp.ota.orfin.policy.mapper.dto.region.Region getRegionByCode(
		String regionCode)
	throws 
		EntityDoesNotExistException {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public com.djt.cvpp.ota.orfin.policy.mapper.dto.region.Region renameRegion(
		String oldRegionCode, 
		String newRegionCode)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public com.djt.cvpp.ota.orfin.policy.mapper.dto.region.Region updateRegion(
		String regionCode,
		String countryName)
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public void deleteRegion(
		String regionCode)
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public String renderGenericPolicyTableJsonForProgramAndRegion(
		String programCode,
		Integer modelYear,
		String regionCode)
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public OrfinPolicySetEvent publishOrfinPolicySetEvent(
		String owner,
		String programCode,
		Integer modelYear,
		String regionCode,
		String policySetName)
	throws 
		ValidationException {
		
		throw new RuntimeException("Not implemented yet.");
	}
}
