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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.djt.cvpp.ota.common.exception.EntityAlreadyExistsException;
import com.djt.cvpp.ota.common.exception.EntityDoesNotExistException;
import com.djt.cvpp.ota.common.exception.ValidationException;
import com.djt.cvpp.ota.orfin.api.policy.PolicySetList;
import com.djt.cvpp.ota.orfin.client.BaseOrfinApplicationServiceClientTest;
import com.djt.cvpp.ota.orfin.client.program.ProgramModelYearApplicationServiceClient;
import com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet;
import com.djt.cvpp.ota.orfin.policy.mapper.dto.override.AbstractPolicyOverride;
import com.djt.cvpp.ota.orfin.policy.model.enums.OtaFunction;
import com.djt.cvpp.ota.orfin.policy.model.enums.PolicyValueType;
import com.djt.cvpp.ota.orfin.policy.repository.impl.ClasspathPolicySetRepositoryImpl;
import com.djt.cvpp.ota.orfin.program.repository.impl.MockProgramModelYearRepositoryImpl;

/**
 *
 * @author tmyers1@yahoo.com (Tom Myers)
 *
 */
public class PolicySetApplicationServiceClientTest extends BaseOrfinApplicationServiceClientTest {

	@Autowired
	private ProgramModelYearApplicationServiceClient programModelYearApplicationServiceClient;
	
	@Autowired
	private PolicySetApplicationServiceClient policySetApplicationServiceClient;
	
	@Before
	public void before() {
		ClasspathPolicySetRepositoryImpl.getInstance().reset();
		MockProgramModelYearRepositoryImpl.getInstance().reset();
	}
	
	@Test
	public void createPolicySet() throws EntityAlreadyExistsException, ValidationException {

		// STEP 1: ARRANGE
		String policySetName = "test_policy_set_name";
		
		
		// STEP 2: ACT
		com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet policySet = policySetApplicationServiceClient.createPolicySet(policySetName);
		
		
		// STEP 3: ASSERT
		Assert.assertNotNull("policySet is null", policySet);
		Assert.assertEquals("policySetName is incorrect", policySetName, policySet.getPolicySetName());
		Assert.assertEquals("number of policies is incorrect", "0", Integer.toString(policySet.getPolicies().size()));
	}
	
	@Test
	public void getAllPolicySets() {
		
		// STEP 1: ARRANGE
		
		
		// STEP 2: ACT
		PolicySetList policySetList = policySetApplicationServiceClient.getAllPolicySets();
		
		
		// STEP 3: ASSERT
		Assert.assertNotNull("policySetList is null", policySetList);
		Assert.assertEquals("policySetList size is incorrect", "1", Integer.toString(policySetList.getPolicies().size()));
		PolicySet policySet = policySetList.getPolicies().get(0);
		Assert.assertEquals("global policy set name is incorrect", "GLOBAL", policySet.getPolicySetName());
		Assert.assertEquals("number of policies is incorrect", "44", Integer.toString(policySet.getPolicies().size()));
	}

	@Test
	public void getPolicySetByName() throws EntityDoesNotExistException, ValidationException, EntityAlreadyExistsException {

		// STEP 1: ARRANGE
		String policySetName = "test_policy_set_name";
		policySetApplicationServiceClient.createPolicySet(policySetName);
		
		
		// STEP 2: ACT
		com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet policySet = policySetApplicationServiceClient.getPolicySetByName(policySetName);
		
		
		// STEP 3: ASSERT
		Assert.assertNotNull("policySet is null", policySet);
		Assert.assertEquals("policySet name is incorrect", policySetName, policySet.getPolicySetName());
		Assert.assertEquals("number of policies is incorrect", "0", Integer.toString(policySet.getPolicies().size()));
	}

	@Test
	public void createVehiclePolicy() throws EntityAlreadyExistsException, ValidationException, EntityDoesNotExistException {

		// STEP 1: ARRANGE
		String policySetName = "test_policy_set_name";
		policySetApplicationServiceClient.createPolicySet(policySetName);
		String policyName = "test_policy_name";
		String policyDescription = "test_description";
		Object policyValue = "test_value";
		Boolean allowRegionalChangeable = Boolean.TRUE;
		Boolean allowUserChangeable = Boolean.TRUE;
		Boolean allowServiceChangeable = Boolean.TRUE;
		Boolean allowCustomerFeedback = Boolean.TRUE;
		String hmi = "test_hmi";
		String vehicleHmiFile = "test_vehicle_hmi";
		String phone = "test_phone";
		String otaFunction = OtaFunction.DOWNLOAD_MANAGER.toString();
		String policyValueType = PolicyValueType.STRING.toString();
		String policyValueConstraints = "policyValueConstraints";
		
		
		// STEP 2: ACT
		com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet policySet = policySetApplicationServiceClient.createVehiclePolicy(
			policySetName, 
			policyName, 
			policyDescription, 
			policyValue, 
			allowRegionalChangeable, 
			allowUserChangeable, 
			allowServiceChangeable, 
			allowCustomerFeedback, 
			hmi, 
			vehicleHmiFile, 
			phone, 
			otaFunction, 
			policyValueType, 
			policyValueConstraints);
		
		
		// STEP 3: ASSERT
		Assert.assertNotNull("policySet is null", policySet);
		Assert.assertEquals("policySet name is incorrect", policySetName, policySet.getPolicySetName());
		List<com.djt.cvpp.ota.orfin.policy.mapper.dto.AbstractPolicy> policies = policySet.getPolicies();
		Assert.assertNotNull("policies is null", policies);
		Assert.assertEquals("number of policies is incorrect", "1", Integer.toString(policies.size()));
		com.djt.cvpp.ota.orfin.policy.mapper.dto.AbstractPolicy policy = policies.get(0);
		Assert.assertNotNull("policy is null", policy);
		Assert.assertTrue("policy is wrong type", policy instanceof com.djt.cvpp.ota.orfin.policy.mapper.dto.VehiclePolicy);
		com.djt.cvpp.ota.orfin.policy.mapper.dto.VehiclePolicy vehiclePolicy = (com.djt.cvpp.ota.orfin.policy.mapper.dto.VehiclePolicy)policy;
		Assert.assertEquals("policy name is incorrect", policyName, vehiclePolicy.getPolicyName());
	}
	
	@Test
	public void createCloudPolicy() throws EntityAlreadyExistsException, ValidationException, EntityDoesNotExistException {

		// STEP 1: ARRANGE
		String policySetName = "test_policy_set_name";
		policySetApplicationServiceClient.createPolicySet(policySetName);
		String policyName = "test_policy_name";
		String policyDescription = "test_description";
		Object policyValue = "test_value";
		String policyValueType = PolicyValueType.STRING.toString();
		String policyValueConstraints = "policyValueConstraints";
		
		
		// STEP 2: ACT
		com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet policySet = policySetApplicationServiceClient.createCloudPolicy(
			policySetName, 
			policyName, 
			policyDescription, 
			policyValue, 
			policyValueType, 
			policyValueConstraints);
		
		
		// STEP 3: ASSERT
		Assert.assertNotNull("policySet is null", policySet);
		Assert.assertEquals("policySet name is incorrect", policySetName, policySet.getPolicySetName());
		List<com.djt.cvpp.ota.orfin.policy.mapper.dto.AbstractPolicy> policies = policySet.getPolicies();
		Assert.assertNotNull("policies is null", policies);
		Assert.assertEquals("number of policies is incorrect", "1", Integer.toString(policies.size()));
		com.djt.cvpp.ota.orfin.policy.mapper.dto.AbstractPolicy policy = policies.get(0);
		Assert.assertNotNull("policy is null", policy);
		Assert.assertTrue("policy is wrong type", policy instanceof com.djt.cvpp.ota.orfin.policy.mapper.dto.CloudPolicy);
		com.djt.cvpp.ota.orfin.policy.mapper.dto.CloudPolicy cloudPolicy = (com.djt.cvpp.ota.orfin.policy.mapper.dto.CloudPolicy)policy;
		Assert.assertEquals("policy name is incorrect", policyName, cloudPolicy.getPolicyName());
	}
	
	@Test
	public void createProgramLevelPolicyOverride() throws EntityAlreadyExistsException, ValidationException, EntityDoesNotExistException {

		// STEP 1: ARRANGE
		String policySetName = "GLOBAL";
		String policyName = "test_policy_name";
		String policyDescription = "test_description";
		Object policyValue = "test_value";
		Boolean allowRegionalChangeable = Boolean.TRUE;
		Boolean allowUserChangeable = Boolean.TRUE;
		Boolean allowServiceChangeable = Boolean.TRUE;
		Boolean allowCustomerFeedback = Boolean.TRUE;
		String hmi = "test_hmi";
		String vehicleHmiFile = "test_vehicle_hmi";
		String phone = "test_phone";
		String otaFunction = OtaFunction.DOWNLOAD_MANAGER.toString();
		String policyValueType = PolicyValueType.STRING.toString();
		String policyValueConstraints = "policyValueConstraints";
		com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet policySet = policySetApplicationServiceClient.createVehiclePolicy(
			policySetName, 
			policyName, 
			policyDescription, 
			policyValue, 
			allowRegionalChangeable, 
			allowUserChangeable, 
			allowServiceChangeable, 
			allowCustomerFeedback, 
			hmi, 
			vehicleHmiFile, 
			phone, 
			otaFunction, 
			policyValueType, 
			policyValueConstraints);
		String programCode = "C344N";
		Integer modelYear = Integer.valueOf(2017);
		programModelYearApplicationServiceClient.createProgramModelYear(programCode, modelYear);
		policyValue = "test_override_value";
		
		
		// STEP 2: ACT
		policySet = policySetApplicationServiceClient.createProgramLevelPolicyOverride(
			policyName, 
			programCode, 
			modelYear, 
			policyValueConstraints);
		
		
		// STEP 3: ASSERT
		Assert.assertNotNull("policySet is null", policySet);
		Assert.assertEquals("policySet name is incorrect", policySetName, policySet.getPolicySetName());
		List<com.djt.cvpp.ota.orfin.policy.mapper.dto.AbstractPolicy> policies = policySet.getPolicies();
		Assert.assertNotNull("policies is null", policies);
		Assert.assertEquals("number of policies is incorrect", "45", Integer.toString(policies.size()));
		com.djt.cvpp.ota.orfin.policy.mapper.dto.AbstractPolicy policy = policySet.getPolicyByName(policyName);
		Assert.assertNotNull("policy is null", policy);
		Assert.assertTrue("policy is wrong type", policy instanceof com.djt.cvpp.ota.orfin.policy.mapper.dto.VehiclePolicy);
		com.djt.cvpp.ota.orfin.policy.mapper.dto.VehiclePolicy vehiclePolicy = (com.djt.cvpp.ota.orfin.policy.mapper.dto.VehiclePolicy)policy;
		List<AbstractPolicyOverride> policyOverrides = vehiclePolicy.getPolicyOverrides();
		Assert.assertNotNull("policyOverrides is null", policy);
		Assert.assertEquals("policyOverrides size is incorrect", "1", Integer.toString(policyOverrides.size()));
	}
	
	@Test
	public void createRegionLevelPolicyOverride() throws EntityAlreadyExistsException, ValidationException, EntityDoesNotExistException {

		// STEP 1: ARRANGE
		String policySetName = "test_policy_set";
		this.policySetApplicationServiceClient.createPolicySet(policySetName);
		String policyName = "test_policy_name";
		String policyDescription = "test_description";
		Object policyValue = "test_value";
		Boolean allowRegionalChangeable = Boolean.TRUE;
		Boolean allowUserChangeable = Boolean.TRUE;
		Boolean allowServiceChangeable = Boolean.TRUE;
		Boolean allowCustomerFeedback = Boolean.TRUE;
		String hmi = "test_hmi";
		String vehicleHmiFile = "test_vehicle_hmi";
		String phone = "test_phone";
		String otaFunction = OtaFunction.DOWNLOAD_MANAGER.toString();
		String policyValueType = PolicyValueType.STRING.toString();
		String policyValueConstraints = "policyValueConstraints";
		com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet policySet = policySetApplicationServiceClient.createVehiclePolicy(
			policySetName, 
			policyName, 
			policyDescription, 
			policyValue, 
			allowRegionalChangeable, 
			allowUserChangeable, 
			allowServiceChangeable, 
			allowCustomerFeedback, 
			hmi, 
			vehicleHmiFile, 
			phone, 
			otaFunction, 
			policyValueType, 
			policyValueConstraints);
		String programCode = "C344N";
		Integer modelYear = Integer.valueOf(2017);
		programModelYearApplicationServiceClient.createProgramModelYear(programCode, modelYear);
		policyValue = "test_override_value";
		String regionCode = "us";
		
		
		// STEP 2: ACT
		policySet = policySetApplicationServiceClient.createRegionLevelPolicyOverride(
			policySetName, 
			policyName, 
			regionCode, 
			policyValueConstraints);
		
		
		// STEP 3: ASSERT
		Assert.assertNotNull("policySet is null", policySet);
		Assert.assertEquals("policySet name is incorrect", policySetName, policySet.getPolicySetName());
		List<com.djt.cvpp.ota.orfin.policy.mapper.dto.AbstractPolicy> policies = policySet.getPolicies();
		Assert.assertNotNull("policies is null", policies);
		Assert.assertEquals("number of policies is incorrect", "1", Integer.toString(policies.size()));
		com.djt.cvpp.ota.orfin.policy.mapper.dto.AbstractPolicy policy = policySet.getPolicyByName(policyName);
		Assert.assertNotNull("policy is null", policy);
		Assert.assertTrue("policy is wrong type", policy instanceof com.djt.cvpp.ota.orfin.policy.mapper.dto.VehiclePolicy);
		com.djt.cvpp.ota.orfin.policy.mapper.dto.VehiclePolicy vehiclePolicy = (com.djt.cvpp.ota.orfin.policy.mapper.dto.VehiclePolicy)policy;
		List<AbstractPolicyOverride> policyOverrides = vehiclePolicy.getPolicyOverrides();
		Assert.assertNotNull("policyOverrides is null", policy);
		Assert.assertEquals("policyOverrides size is incorrect", "1", Integer.toString(policyOverrides.size()));
	}
}
