/*
 * 
 * 
 * 
 * 
 * 
 * 
 */
package com.djt.cvpp.ota.orfin.api.policy;

import java.util.List;

import com.djt.cvpp.ota.common.exception.EntityAlreadyExistsException;
import com.djt.cvpp.ota.common.exception.EntityDoesNotExistException;
import com.djt.cvpp.ota.common.exception.ValidationException;
import com.djt.cvpp.ota.orfin.policy.event.OrfinPolicySetEvent;

/**
 *
 * @author tmyers1@yahoo.com (Tom Myers)
 *
 */
public interface PolicySetApplicationService {
	
	String POLICY_SET_URI = "/api/v1/policyset";
	
	String POLICY_SET_NAME = "policySetName";
	String POLICY_NAME = "policyName";
	String POLICY_DESCRIPTION = "policyDescription";
	String POLICY_VALUE = "policyValue";
	String ALLOW_REGIONAL_CHANGEABLE = "allowRegionalChangeable";
	String ALLOW_USER_CHANGEABLE = "allowUserChangeable";
	String ALLOW_SERVICE_CHANGEABLE = "allowServiceChangeable";
	String ALLOW_CUSTOMER_FEEDBACK = "allowCustomerFeedback";
	String HMI = "hmi";
	String VEHICLE_HMI_FILE = "vehicleHmiFile";
	String PHONE = "phone";
	String OTA_FUNCTION = "otaFunction";
	String POLICY_VALUE_TYPE = "policyValueType";
	String POLICY_VALUE_CONSTRAINTS = "policyValueConstraints";
	String PROGRAM_CODE = "programCode";
	String MODEL_YEAR = "modelYear";
	String REGION_CODE = "regionCode";
	
	
	/**
	 * 
	 * @param policySetName
	 * @return
	 * @throws EntityAlreadyExistsException
	 * @throws ValidationException
	 */
	String CREATE_POLICY_SET_URI = "/createPolicySet";
	com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet createPolicySet(
		String policySetName) 
	throws 
		EntityAlreadyExistsException, 
		ValidationException;

	
	/**
	 * 
	 * @return
	 */
	String GET_ALL_POLICY_SETS_URI = "/getAllPolicySets";
	PolicySetList getAllPolicySets();

	
	/**
	 * 
	 * @param policySetName
	 * @return
	 * @throws EntityDoesNotExistException
	 * @throws ValidationException
	 */
	String GET_POLICY_SET_BY_NAME_URI = "/getPolicySetByName";
	com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet getPolicySetByName(
		String policySetName) 
	throws 
		EntityDoesNotExistException, 
		ValidationException;
	
	
	/**
	 * 
	 * @param policySetName
	 * @param policyName
	 * @param policyDescription
	 * @param policyValue
	 * @param allowRegionalChangeable
	 * @param allowUserChangeable
	 * @param allowServiceChangeable
	 * @param allowCustomerFeedback
	 * @param hmi
	 * @param vehicleHmiFile
	 * @param phone
	 * @param otaFunction
	 * @param policyValueType
	 * @param policyValueConstraints
	 * @return
	 * @throws EntityDoesNotExistException
	 * @throws EntityAlreadyExistsException
	 * @throws ValidationException
	 */
	String CREATE_VEHICLE_POLICY_URI = "/createVehiclePolicy";
	com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet createVehiclePolicy(
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
		ValidationException;

	
	/**
	 * 
	 * @param policySetName
	 * @param policyName
	 * @param policyDescription
	 * @param policyValue
	 * @param policyValueType
	 * @param policyValueConstraints
	 * @return
	 * @throws EntityDoesNotExistException
	 * @throws EntityAlreadyExistsException
	 * @throws ValidationException
	 */
	String CREATE_CLOUD_POLICY_URI = "/createCloudPolicy";
	com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet createCloudPolicy(
		String policySetName,
		String policyName,
		String policyDescription,
		Object policyValue,
		String policyValueType,
		String policyValueConstraints)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException,
		ValidationException;

	
	/**
	 * 
	 * @param policyName
	 * @param programCode
	 * @param modelYear
	 * @param policyValue
	 * @return
	 * @throws EntityDoesNotExistException
	 * @throws EntityAlreadyExistsException
	 * @throws ValidationException
	 */
	String CREATE_PROGRAM_LEVEL_POLICY_OVERRIDE_URI = "/createProgramLevelPolicyOverride";
	com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet createProgramLevelPolicyOverride(
		String policyName,
		String programCode,
		Integer modelYear,
		Object policyValue)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException,
		ValidationException;

	
	/**
	 * 
	 * @param policySetName
	 * @param policyName
	 * @param regionCode
	 * @param policyValue
	 * @return
	 * @throws EntityDoesNotExistException
	 * @throws EntityAlreadyExistsException
	 * @throws ValidationException
	 */
	String CREATE_REGION_LEVEL_POLICY_OVERRIDE_URI = "/createRegionLevelPolicyOverride";
	com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet createRegionLevelPolicyOverride(
		String policySetName,
		String policyName,
		String regionCode,	
		Object policyValue)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException,
		ValidationException;
		
	
	/**
	 * 
	 * @param oldPolicySetName
	 * @param newPolicySetName
	 * @return
	 * @throws EntityDoesNotExistException
	 * @throws EntityAlreadyExistsException
	 * @throws ValidationException
	 */
	com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet renamePolicySet(
		String oldPolicySetName,
		String newPolicySetName)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException,
		ValidationException;

	
	/**
	 * 
	 * @param policySetName
	 * @param oldPolicyName
	 * @param newPolicyName
	 * @return
	 * @throws EntityDoesNotExistException
	 * @throws EntityAlreadyExistsException
	 * @throws ValidationException
	 */
	com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet renamePolicy(
		String policySetName,
		String oldPolicyName,
		String newPolicyName)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException,
		ValidationException;
	
	
	/**
	 * 
	 * @param policySetName
	 * @param policyName
	 * @param policyDescription
	 * @param policyValue
	 * @param allowRegionalChangeable
	 * @param allowUserChangeable
	 * @param allowServiceChangeable
	 * @param allowCustomerFeedback
	 * @param hmi
	 * @param vehicleHmiFile
	 * @param phone
	 * @param otaFunction
	 * @param policyValueType
	 * @param policyValueConstraints
	 * @return
	 * @throws EntityDoesNotExistException
	 * @throws ValidationException
	 */
	com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet updateVehiclePolicy(
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
		ValidationException;


	/**
	 * 
	 * @param policySetName
	 * @param policyName
	 * @param policyDescription
	 * @param policyValue
	 * @param policyValueType
	 * @param policyValueConstraints
	 * @return
	 * @throws EntityDoesNotExistException
	 * @throws ValidationException
	 */
	com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet updateCloudPolicy(
		String policySetName,
		String policyName,
		String policyDescription,
		Object policyValue,
		String policyValueType,
		String policyValueConstraints)
	throws 
		EntityDoesNotExistException,
		ValidationException;
	
	
	/**
	 * 
	 * @param policyName
	 * @param programCode
	 * @param modelYear
	 * @param policyValue
	 * @return
	 * @throws EntityDoesNotExistException
	 * @throws ValidationException
	 */
	com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet updateProgramLevelPolicyOverride(
		String policyName,
		String programCode,
		Integer modelYear,
		Object policyValue)
	throws 
		EntityDoesNotExistException,
		ValidationException;

	
	/**
	 * 
	 * @param policySetName
	 * @param policyName
	 * @param regionCode
	 * @param policyValue
	 * @return
	 * @throws EntityDoesNotExistException
	 * @throws ValidationException
	 */
	com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet updateRegionLevelPolicyOverride(
		String policySetName,
		String policyName,
		String regionCode,
		Object policyValue)
	throws 
		EntityDoesNotExistException,
		ValidationException;

	
	/**
	 * 
	 * @param policySetName
	 * @throws EntityDoesNotExistException
	 * @throws ValidationException
	 */
	void deletePolicySet(
		String policySetName) 
	throws 
		EntityDoesNotExistException,
		ValidationException;

	
	/**
	 * 
	 * @param policySetName
	 * @param policyName
	 * @return
	 * @throws EntityDoesNotExistException
	 * @throws ValidationException
	 */
	com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet deletePolicy(
		String policySetName,
		String policyName)
	throws
		EntityDoesNotExistException,
		ValidationException;
	
	
	/**
	 * 
	 * @param policyName
	 * @param programCode
	 * @param modelYear
	 * @return
	 * @throws EntityDoesNotExistException
	 * @throws ValidationException
	 */
	com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet deleteProgramLevelPolicyOverride(
		String policyName,
		String programCode,
		Integer modelYear)
	throws 
		EntityDoesNotExistException,
		ValidationException;
	
	
	/**
	 * 
	 * @param policySetName
	 * @param policyName
	 * @param regionCode
	 * @return
	 * @throws EntityDoesNotExistException
	 * @throws ValidationException
	 */
	com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet deleteRegionLevelPolicyOverride(
		String policySetName,
		String policyName,
		String regionCode)
	throws 
		EntityDoesNotExistException,
		ValidationException;

	
	/**
	 * 
	 * @param regionCode
	 * @param countryName
	 * @return
	 * @throws EntityAlreadyExistsException
	 * @throws ValidationException
	 */
	com.djt.cvpp.ota.orfin.policy.mapper.dto.region.Region createRegion(
		String regionCode,
		String countryName)
	throws 
		EntityAlreadyExistsException,
		ValidationException;
	
	
	/**
	 * 
	 * @return
	 */
	List<com.djt.cvpp.ota.orfin.policy.mapper.dto.region.Region> getAllRegions();
	
	
	/**
	 * 
	 * @param regionCode
	 * @return
	 * @throws EntityDoesNotExistException
	 */
	com.djt.cvpp.ota.orfin.policy.mapper.dto.region.Region getRegionByCode(
		String regionCode)
	throws 
		EntityDoesNotExistException;

	
	/**
	 * 
	 * @param oldRegionCode
	 * @param newRegionCode
	 * @return
	 * @throws EntityDoesNotExistException
	 * @throws EntityAlreadyExistsException
	 */
	com.djt.cvpp.ota.orfin.policy.mapper.dto.region.Region renameRegion(
		String oldRegionCode, 
		String newRegionCode)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException;

	
	/**
	 * 
	 * @param regionCode
	 * @param countryName
	 * @return
	 * @throws EntityDoesNotExistException
	 * @throws ValidationException
	 */
	com.djt.cvpp.ota.orfin.policy.mapper.dto.region.Region updateRegion(
		String regionCode,
		String countryName)
	throws 
		EntityDoesNotExistException,
		ValidationException;
	
	
	/**
	 * 
	 * @param regionCode
	 * @throws EntityDoesNotExistException
	 * @throws ValidationException
	 */
	void deleteRegion(
		String regionCode)
	throws 
		EntityDoesNotExistException,
		ValidationException;
	
	
	/**
	 * 
	 * @param programCode
	 * @param modelYear
	 * @param regionCode
	 * @return
	 * @throws EntityDoesNotExistException
	 * @throws ValidationException
	 */
	String renderGenericPolicyTableJsonForProgramAndRegion(
		String programCode,
		Integer modelYear,
		String regionCode)
	throws 
		EntityDoesNotExistException,
		ValidationException;
	
	
	/**
	 * 
	 * @param owner
	 * @param programCode
	 * @param modelYear
	 * @param  regionCode The region code is optional, if null, then all vehicles belonging to the given program code and model year will be targeted for the policy table update.  Otherwise, if specified,
	 * then only those vehicles that are "in" the given region will be targeted for the policy table update.
	 * @param policySetName
	 * @return
	 * @throws ValidationException
	 */
	String PUBLISH_POLICY_SET_EVENT_URI = "/publishOrfinPolicySetEvent";
	OrfinPolicySetEvent publishOrfinPolicySetEvent(
		String owner,
		String programCode,
		Integer modelYear,
		String regionCode,
		String policySetName)
	throws 
		ValidationException;
}
