/*
 * 
 * 
 * 
 * 
 * 
 * 
 */
package com.djt.cvpp.ota.orfin.app.policy.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.djt.cvpp.ota.common.exception.EntityAlreadyExistsException;
import com.djt.cvpp.ota.common.exception.EntityDoesNotExistException;
import com.djt.cvpp.ota.common.exception.ValidationException;
import com.djt.cvpp.ota.orfin.api.policy.PolicySetApplicationService;
import com.djt.cvpp.ota.orfin.api.policy.PolicySetList;
import com.djt.cvpp.ota.orfin.delivery.event.OrfinDeliveryRuleSetEventPublisher;
import com.djt.cvpp.ota.orfin.delivery.event.impl.MockOrfinDeliveryRuleSetEventPublisher;
import com.djt.cvpp.ota.orfin.delivery.repository.DeliveryRuleSetRepository;
import com.djt.cvpp.ota.orfin.delivery.repository.impl.MockDeliveryRuleSetRepositoryImpl;
import com.djt.cvpp.ota.orfin.delivery.service.DeliveryRuleSetService;
import com.djt.cvpp.ota.orfin.delivery.service.impl.DeliveryRuleSetServiceImpl;
import com.djt.cvpp.ota.orfin.odl.event.OrfinOdlEventPublisher;
import com.djt.cvpp.ota.orfin.odl.event.impl.MockOrfinOdlEventPublisher;
import com.djt.cvpp.ota.orfin.odl.repository.OptimizedDataListRepository;
import com.djt.cvpp.ota.orfin.odl.repository.impl.MockOptimizedDataListRepositoryImpl;
import com.djt.cvpp.ota.orfin.odl.service.OptimizedDataListService;
import com.djt.cvpp.ota.orfin.odl.service.impl.OptimizedDataListServiceImpl;
import com.djt.cvpp.ota.orfin.policy.event.OrfinPolicySetEvent;
import com.djt.cvpp.ota.orfin.policy.event.impl.MockOrfinPolicySetEventPublisher;
import com.djt.cvpp.ota.orfin.policy.model.PolicySet;
import com.djt.cvpp.ota.orfin.policy.repository.PolicySetRepository;
import com.djt.cvpp.ota.orfin.policy.repository.impl.ClasspathPolicySetRepositoryImpl;
import com.djt.cvpp.ota.orfin.policy.service.PolicySetService;
import com.djt.cvpp.ota.orfin.policy.service.impl.PolicySetServiceImpl;
import com.djt.cvpp.ota.orfin.program.model.ProgramModelYear;
import com.djt.cvpp.ota.orfin.program.repository.ProgramModelYearRepository;
import com.djt.cvpp.ota.orfin.program.repository.impl.MockProgramModelYearRepositoryImpl;
import com.djt.cvpp.ota.orfin.program.service.ProgramModelYearService;
import com.djt.cvpp.ota.orfin.program.service.impl.ProgramModelYearServiceImpl;
import com.djt.cvpp.ota.orfin.vadrevent.repository.OrfinVadrReleaseEventRepository;
import com.djt.cvpp.ota.orfin.vadrevent.repository.impl.MockOrfinVadrReleaseEventRepositoryImpl;
import com.djt.cvpp.ota.orfin.vadrevent.service.OrfinVadrReleaseEventService;
import com.djt.cvpp.ota.orfin.vadrevent.service.impl.OrfinVadrReleaseEventServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 *
 * @author tmyers1@yahoo.com (Tom Myers)
 *
 */
@RestController
@RequestMapping(path = PolicySetApplicationService.POLICY_SET_URI)
public class PolicySetController implements PolicySetApplicationService {

	
	// ORFIN dependencies
	private OrfinVadrReleaseEventRepository orfinVadrReleaseEventRepository;
	private OrfinVadrReleaseEventService orfinVadrReleaseEventService;
	
	private OrfinDeliveryRuleSetEventPublisher orfinDeliveryRuleSetEventPublisher;
	private DeliveryRuleSetRepository deliveryRuleSetRepository;
	private DeliveryRuleSetService deliveryRuleSetService;
	
	private PolicySetRepository policySetRepository;
	private MockOrfinPolicySetEventPublisher orfinPolicySetEventPublisher;
	private PolicySetService policySetService;
	
	private OrfinOdlEventPublisher orfinOdlEventPublisher;
	private OptimizedDataListRepository optimizedDataListRepository; 
	private OptimizedDataListService optimizedDataListService;

	private ProgramModelYearRepository programModelYearRepository;
	private ProgramModelYearService programModelYearService;
	
	public PolicySetController() {

		// ORFIN dependencies
		orfinVadrReleaseEventRepository = new MockOrfinVadrReleaseEventRepositoryImpl();
		orfinVadrReleaseEventService = new OrfinVadrReleaseEventServiceImpl(this.orfinVadrReleaseEventRepository);
		
		deliveryRuleSetRepository = MockDeliveryRuleSetRepositoryImpl.getInstance();
		deliveryRuleSetRepository.reset();
		orfinDeliveryRuleSetEventPublisher = MockOrfinDeliveryRuleSetEventPublisher.getInstance();
		deliveryRuleSetService = new DeliveryRuleSetServiceImpl(
			deliveryRuleSetRepository,
			orfinVadrReleaseEventService,
			orfinDeliveryRuleSetEventPublisher);

		optimizedDataListRepository = MockOptimizedDataListRepositoryImpl.getInstance();
		optimizedDataListRepository.reset();
		orfinOdlEventPublisher = MockOrfinOdlEventPublisher.getInstance(); 
		optimizedDataListService = new OptimizedDataListServiceImpl(
			optimizedDataListRepository,
			orfinOdlEventPublisher);

		orfinPolicySetEventPublisher = MockOrfinPolicySetEventPublisher.getInstance();
		policySetRepository = ClasspathPolicySetRepositoryImpl.getInstance();
		policySetService = new PolicySetServiceImpl(
			policySetRepository,
			orfinPolicySetEventPublisher);
		
		programModelYearRepository = MockProgramModelYearRepositoryImpl.getInstance();
		programModelYearRepository.reset();
		programModelYearService = new ProgramModelYearServiceImpl(
			programModelYearRepository,
			deliveryRuleSetService,
			optimizedDataListService,
			policySetService);
	}

	
	// ************************************************************************************************************************************
	// CONTROLLER METHODS MAP TO SERVICE METHODS ONE-TO-ONE.  ALL SWAGGER DOC INFO COMES FROM JAVADOC IN SERVICE INTERFACE.
	// ************************************************************************************************************************************

	
	@ApiOperation(value = CREATE_POLICY_SET_URI, notes = "Creates a policy set with the given name.  There can only be one policy set that can be considered \"global\" and its name must be \"GLOBAL\". A \"non-global\" policy set is able to be associated to one or more <code>ProgramModelYear</code> instances. The \"global\" policy set cannot be associated with any <code>ProgramModelYear</code> instances.  However, it is the only policy set that can have \"program level\" policy value overrides. In addition, \"non-global\" policy sets can only have region and vehicle level policy overrides associated with its child policies. (Only the global policy set can)")
	@GetMapping(CREATE_POLICY_SET_URI)
	public @ResponseBody com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet createPolicySet(
		@ApiParam("The unique name of the policy set, \"GLOBAL\" to create the global policy set")
		@RequestParam
		String policySetName) 
	throws 
		EntityAlreadyExistsException, 
		ValidationException {
		
		return this.policySetService.getDtoMapper().mapEntityToDto(this.policySetService.createPolicySet(policySetName));
	}

	
	@ApiOperation(value = GET_ALL_POLICY_SETS_URI, notes = "All policy sets that have been created")
	@GetMapping(GET_ALL_POLICY_SETS_URI)
	public @ResponseBody PolicySetList getAllPolicySets() {
		
		List<com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet> policySetDtos = new ArrayList<>();
		
		Iterator<com.djt.cvpp.ota.orfin.policy.model.PolicySet> iterator = this.policySetService.getAllPolicySets().iterator();
		while (iterator.hasNext()) {
		
			policySetDtos.add(this.policySetService.getDtoMapper().mapEntityToDto(iterator.next()));
		}
		
		PolicySetList dto = new PolicySetList();
		dto.setPolicies(policySetDtos);
		return dto;
	}

	
	@ApiOperation(value = GET_POLICY_SET_BY_NAME_URI, notes = "The policy set with the name specified by policySetName")
	@GetMapping(GET_POLICY_SET_BY_NAME_URI)
	public @ResponseBody com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet getPolicySetByName(
		@ApiParam("The unique name of the policy set. Use \"GLOBAL\" to retrieve the global policy set") 
		@RequestParam 
		String policySetName) 
	throws 
		EntityDoesNotExistException, 
		ValidationException {
		
		return this.policySetService.getDtoMapper().mapEntityToDto(this.policySetService.getPolicySetByName(policySetName));
	}

	
	@ApiOperation(value = CREATE_VEHICLE_POLICY_URI, notes = "Creates the specified vehicle policy and returns the affected/policy set")
	@GetMapping(CREATE_VEHICLE_POLICY_URI)
	public @ResponseBody com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet createVehiclePolicy(
		@ApiParam("The unique name of the policy set (\"GLOBAL\" for the global policy set.)")
		@RequestParam
		String policySetName,
		
		@ApiParam("The unique name of the policy to be created")
		@RequestParam
		String policyName,
		
		@ApiParam("The description of the policy")
		@RequestParam
		String policyDescription,
		
		@ApiParam("The value of the policy to use as a default")
		@RequestParam
		Object policyValue,
		
		@ApiParam("Specifies whether or not this policy's value can be overridden at the regional level")
		@RequestParam
		Boolean allowRegionalChangeable,
		
		@ApiParam("Specifies whether or not this policy's value can be overridden by the consumer. This resolves to the \"vehicle level\"")
		@RequestParam
		Boolean allowUserChangeable,
		
		@ApiParam("Specifies whether or not this policy's value can be overridden by service/dealership. This also resolves to the \"vehicle level\".")
		@RequestParam
		Boolean allowServiceChangeable,
		
		@ApiParam("Specifies whether or not this policy allows for customer feedback")
		@RequestParam
		Boolean allowCustomerFeedback,
		
		@ApiParam("An enumeration value that specifies whether the user can (R)ead, Read&Write (RW) or None (N) on the HMI (human machine interface) in the vehicle.")
		@RequestParam
		String hmi,
		
		@ApiParam("A CSV line that contains an ID, a \"vehicle HMI description\", and a pipe-delimited set of button names (e.g. \"Cancel\"|\"Allow\") (Assumes that <code>allowUserChangeable</code> is true AND that <code>hmi</code> is either R or RW)")
		@RequestParam
		String vehicleHmiFile,
		
		@ApiParam("An enumeration that says whether or not a phone number is displayed (\"NONE\" means that no phone number is displayed)")
		@RequestParam
		String phone,
		
		@ApiParam("An enumeration that specifies which high level OTA Function is being dealt with (e.g. OTA_MANAGER, OTA_STATUS_MANAGER, IVSU_TRIGGER, etc.)")
		@RequestParam
		String otaFunction,
		
		@ApiParam("Specifies the type of policy value.  It must be STRING, NUMERIC or ENUM.")
		@RequestParam
		String policyValueType,
		
		@ApiParam("If <code>policyValueType</code> is ENUM, then constraints contain a comma separated list of enum choices.  If PolicyValueType is NUMERIC, then the constraints are the lower and upperbound.")
		@RequestParam
		String policyValueConstraints)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException,
		ValidationException {

		return this.policySetService.getDtoMapper().mapEntityToDto(this.policySetService
			.createPolicy(
				PolicySet.VEHICLE_POLICY, 
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
				policyValueConstraints));
	}
	
	
	@ApiOperation(value = CREATE_CLOUD_POLICY_URI, notes = "Creates the specified cloud policy and returns the affected/policy set")
	@GetMapping(CREATE_CLOUD_POLICY_URI)
	public @ResponseBody com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet createCloudPolicy(
		@ApiParam("The unique name of the policy set (\"GLOBAL\" for the global policy set.)")
		@RequestParam
		String policySetName,
		
		@ApiParam("The unique name of the policy to be created")
		@RequestParam
		String policyName,
		
		@ApiParam("The description of the policy")
		@RequestParam
		String policyDescription,
		
		@ApiParam("The value of the policy to use as a default")
		@RequestParam
		Object policyValue,
		
		@ApiParam("Specifies the type of policy value.  It must be STRING, NUMERIC or ENUM.")
		@RequestParam
		String policyValueType,
		
		@ApiParam("If <code>policyValueType</code> is ENUM, then constraints contain a comma separated list of enum choices.  If PolicyValueType is NUMERIC, then the constraints are the lower and upperbound.")
		@RequestParam
		String policyValueConstraints)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException,
		ValidationException {
		
		return this.policySetService.getDtoMapper().mapEntityToDto(this.policySetService.createPolicy(
			PolicySet.CLOUD_POLICY, 
			policySetName, 
			policyName, 
			policyDescription, 
			policyValue, 
			null, 
			null, 
			null, 
			null, 
			null, 
			null, 
			null, 
			null, 
			policyValueType,
			policyValueConstraints));
	}
	

	@ApiOperation(value = CREATE_PROGRAM_LEVEL_POLICY_OVERRIDE_URI, notes = "Creates the specified program level policy override and returns the affected/policy set, which would be the GLOBAL policy set")
	@GetMapping(CREATE_PROGRAM_LEVEL_POLICY_OVERRIDE_URI)
	public @ResponseBody com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet createProgramLevelPolicyOverride(
		@ApiParam("The unique name of the policy")
		@RequestParam
		String policyName,
		
		@ApiParam("The program code for the program model year to create the policy override for")
		@RequestParam
		String programCode,
		
		@ApiParam("The model year for the program model year to create the policy override for")
		@RequestParam
		Integer modelYear,

		@ApiParam("The value to use for the override")
		@RequestParam
		Object policyValue)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException,
		ValidationException {
		
		ProgramModelYear programModelYear = this.programModelYearService.getProgramModelYearByProgramCodeAndModelYear(programCode, modelYear);
		return this.policySetService.getDtoMapper().mapEntityToDto(this.policySetService.createProgramLevelPolicyOverride(policyName, programModelYear, policyValue));
	}

	
	@ApiOperation(value = CREATE_REGION_LEVEL_POLICY_OVERRIDE_URI, notes = "Creates the specified region level policy override and returns the affected/policy set, which would be the GLOBAL policy set")
	@GetMapping(CREATE_REGION_LEVEL_POLICY_OVERRIDE_URI)
	public @ResponseBody com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet createRegionLevelPolicyOverride(
		@ApiParam("The unique name of the policy set (\"GLOBAL\" for the global policy set.)")
		@RequestParam
		String policySetName,
		
		@ApiParam("The unique name of the policy")
		@RequestParam
		String policyName,
		
		@ApiParam("The unique code of the region")
		String regionCode,	
		
		@ApiParam("The value to use for the override")
		@RequestParam
		Object policyValue)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException,
		ValidationException {
		
		return this.policySetService.getDtoMapper().mapEntityToDto(this.policySetService.createRegionLevelPolicyOverride(policySetName, policyName, regionCode, policyValue));
	}

	
	@ApiOperation(value = "renamePolicySet()", notes = "Renames the policySet specified by oldPolicySetName to newPolicySetName")
	@GetMapping("/renamePolicySet")
	public @ResponseBody com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet renamePolicySet(
		@ApiParam("The old name of the policy set")
		@RequestParam
		String oldPolicySetName,
		
		@ApiParam("The new name of the policy set")
		@RequestParam
		String newPolicySetName)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException,
		ValidationException {
		
		return this.policySetService.getDtoMapper().mapEntityToDto(this.policySetService.renamePolicySet(oldPolicySetName, newPolicySetName));
	}

	
	@ApiOperation(value = "renamePolicy()", notes = "Renames the policy specified by oldPolicyName to newPolicyName for policy set specified by policySetName")
	@GetMapping("/renamePolicy")
	public @ResponseBody com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet renamePolicy(
			@ApiParam("The unique name of the policy set, \"GLOBAL\" to create the global policy set")
		@RequestParam
		String policySetName,
		
		@ApiParam("The old name of the policy")
		@RequestParam
		String oldPolicyName,
		
		@ApiParam("The new name of the policy")
		@RequestParam
		String newPolicyName)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException,
		ValidationException {
		
		return null;
	}
	
	
	@ApiOperation(value = "updateVehiclePolicy()", notes = "Updates the vehicle policy with policyName and policy set policySetName. NOTE: policyValue must agree with the value specified by policy value type")
	@GetMapping("/updateVehiclePolicy")
	public @ResponseBody com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet updateVehiclePolicy(
			
		@ApiParam("The unique name of the policy set (\"GLOBAL\" for the global policy set.)")
		@RequestParam
		String policySetName,
		
		@ApiParam("The unique name of the policy")
		@RequestParam
		String policyName,
		
		@RequestParam
		String policyDescription,
		
		@RequestParam
		Object policyValue,
		
		@RequestParam
		Boolean allowRegionalChangeable,
		
		@RequestParam
		Boolean allowUserChangeable,
		
		@RequestParam
		Boolean allowServiceChangeable,
		
		@RequestParam
		Boolean allowCustomerFeedback,
		
		@RequestParam
		String hmi,
		
		@RequestParam
		String vehicleHmiFile,
		
		@RequestParam
		String phone,
		
		@RequestParam
		String otaFunction,
		
		@ApiParam("Specifies the type of policy value.  It must be STRING, NUMERIC or ENUM.")
		@RequestParam
		String policyValueType,
		
		@ApiParam("If <code>policyValueType</code> is ENUM, then constraints contain a comma separated list of enum choices.  If PolicyValueType is NUMERIC, then the constraints are the lower and upperbound.")
		@RequestParam
		String policyValueConstraints)
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		return this.policySetService.getDtoMapper().mapEntityToDto(this.policySetService.updateVehiclePolicy(policySetName, policyName, policyDescription, policyValue, allowRegionalChangeable, allowUserChangeable, allowServiceChangeable, allowCustomerFeedback, hmi, vehicleHmiFile, phone, otaFunction, policyValueType, policyValueConstraints));
	}


	
	@ApiOperation(value = "updateCloudPolicy()", notes = "Updates the cloud policy with policyName and policy set policySetName. NOTE: policyValue must agree with the value specified by policy value type")
	@GetMapping("/updateCloudPolicy")
	public @ResponseBody com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet updateCloudPolicy(
			
		@ApiParam("The unique name of the policy set (\"GLOBAL\" for the global policy set.)")
		@RequestParam
		String policySetName,
		
		@ApiParam("The unique name of the policy")
		@RequestParam
		String policyName,
		
		@RequestParam
		String policyDescription,
		
		@RequestParam
		Object policyValue,
		
		@ApiParam("Specifies the type of policy value.  It must be STRING, NUMERIC or ENUM.")
		@RequestParam
		String policyValueType,
		
		@ApiParam("If <code>policyValueType</code> is ENUM, then constraints contain a comma separated list of enum choices.  If PolicyValueType is NUMERIC, then the constraints are the lower and upperbound.")
		@RequestParam
		String policyValueConstraints)
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		return this.policySetService.getDtoMapper().mapEntityToDto(this.policySetService.updateCloudPolicy(policySetName, policyName, policyDescription, policyValue, policyValueType, policyValueConstraints));
	}

	
	@ApiOperation(value = "updateProgramLevelPolicyOverride()", notes = "Updates the program level override for the GLOBAL policy with policyName. NOTE: policyValue must agree with the value specified by policy value type")
	@GetMapping("/updateProgramLevelPolicyOverride")
	public @ResponseBody com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet updateProgramLevelPolicyOverride(
		@ApiParam("The unique name of the GLOBAL policy")
		@RequestParam
		String policyName,
		
		@ApiParam("The program code for the program model year")
		@RequestParam
		String programCode,

		@ApiParam("The model year for the program model year")
		@RequestParam
		Integer modelYear,
		
		@ApiParam("The new value of the program level policy override to use")
		@RequestParam
		Object policyValue)
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		ProgramModelYear programModelYear = this.programModelYearService.getProgramModelYearByProgramCodeAndModelYear(programCode, modelYear);
		return this.policySetService.getDtoMapper().mapEntityToDto(this.policySetService.updateProgramLevelPolicyOverride(policyName, programModelYear, policyValue));
	}

	
	@ApiOperation(value = "updateRegionLevelPolicyOverride()", notes = "Updates the region level override for the specified policy set policySetName. NOTE: policyValue must agree with the value specified by policy value type")
	@GetMapping("/updateRegionLevelPolicyOverride")
	public @ResponseBody com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet updateRegionLevelPolicyOverride(
		@ApiParam("The unique name of the policy set (\"GLOBAL\" for the global policy set.)")
		@RequestParam
		String policySetName,
		
		@ApiParam("The unique name of the policy")
		@RequestParam
		String policyName,
		
		@ApiParam("The unique code of the region")
		@RequestParam
		String regionCode,
		
		@ApiParam("The new value of the region level policy override to use")
		@RequestParam
		Object policyValue)
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		return this.policySetService.getDtoMapper().mapEntityToDto(this.policySetService.updateRegionLevelPolicyOverride(policySetName, policyName, regionCode, policyValue));
	}

	
	@ApiOperation(value = "deletePolicySet()", notes = "Deletes the policy set specified by policySetName")
	@GetMapping("/deletePolicySet")
	public void deletePolicySet(
		@ApiParam("The unique name of the policy set")
		@RequestParam
		String policySetName) 
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		// TODO: TDM: Add return type of whatever is normally returned for a DELETE
		this.policySetService.deletePolicySet(policySetName);
	}

	
	@ApiOperation(value = "deletePolicy()", notes = "Deletes the policy specified by policyName and policy set policySetName")
	@GetMapping("/deletePolicy")
	public @ResponseBody com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet deletePolicy(
		@ApiParam("The unique name of the policy set (\"GLOBAL\" for the global policy set.)")
		@RequestParam
		String policySetName,
		
		@ApiParam("The unique name of the policy set")
		@RequestParam
		String policyName)
	throws
		EntityDoesNotExistException,
		ValidationException {
		
		return this.policySetService.getDtoMapper().mapEntityToDto(this.policySetService.deletePolicy(policySetName, policyName));
	}
	
	
	@ApiOperation(value = "deleteProgramLevelPolicyOverride()", notes = "Deletes the program level policy override for the GLOBAL policy specified by policyName")
	@GetMapping("/deleteProgramLevelPolicyOverride")
	public @ResponseBody com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet deleteProgramLevelPolicyOverride(
		@ApiParam("The unique name of the GLOBAL policy")
		@RequestParam
		String policyName,
		
		@ApiParam("The program code for the program model year")
		@RequestParam
		String programCode,

		@ApiParam("The model year for the program model year")
		@RequestParam
		Integer modelYear)
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		ProgramModelYear programModelYear = this.programModelYearService.getProgramModelYearByProgramCodeAndModelYear(programCode, modelYear);
		return this.policySetService.getDtoMapper().mapEntityToDto(this.policySetService.deleteProgramLevelPolicyOverride(policyName, programModelYear));
	}
	
	
	@ApiOperation(value = "deleteRegionLevelPolicyOverride()", notes = "Updates the policy with policyName and policy set policySetName. NOTE: policyValue must agree with the value specified by policy value type")
	@GetMapping("/deleteRegionLevelPolicyOverride")
	public @ResponseBody com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet deleteRegionLevelPolicyOverride(
			
		@ApiParam("The unique name of the policy set (\"GLOBAL\" for the global policy set.)")
		@RequestParam
		String policySetName,
		
		@ApiParam("The unique name of the policy")
		@RequestParam
		String policyName,
		
		@ApiParam("The unique code of the region")
		@RequestParam
		String regionCode)
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		return this.policySetService.getDtoMapper().mapEntityToDto(this.policySetService.deleteRegionLevelPolicyOverride(policySetName, policyName, regionCode));
	}

	
	@ApiOperation(value = "createRegion()", notes = "Creates the specified region")
	@GetMapping("/createRegion")
	public @ResponseBody com.djt.cvpp.ota.orfin.policy.mapper.dto.region.Region createRegion(
		@ApiParam("The unique code of the region")
		@RequestParam
		String regionCode,
		
		@ApiParam("The name for the region")
		@RequestParam
		String countryName)
	throws 
		EntityAlreadyExistsException,
		ValidationException {
		
		return this.policySetService.getDtoMapper().mapRegionEntityToDto(this.policySetService.createRegion(regionCode, countryName));
	}
	
	
	@ApiOperation(value = "getAllRegions()", notes = "Gets all regions")
	@GetMapping("/getAllRegions")
	public @ResponseBody List<com.djt.cvpp.ota.orfin.policy.mapper.dto.region.Region> getAllRegions() {
		
		List<com.djt.cvpp.ota.orfin.policy.mapper.dto.region.Region> list = new ArrayList<>();
		
		Iterator<com.djt.cvpp.ota.orfin.policy.model.region.Region> iterator = this.policySetService.getAllRegions().iterator();
		while (iterator.hasNext()) {
		
			list.add(this.policySetService.getDtoMapper().mapRegionEntityToDto(iterator.next()));
		}
		
		return list;
	}
	
	
	@ApiOperation(value = "getRegionByCode()", notes = "Gets the specified region specified by regionCode")
	@GetMapping("/getRegionByCode")
	public @ResponseBody com.djt.cvpp.ota.orfin.policy.mapper.dto.region.Region getRegionByCode(
		@ApiParam("The unique code of the region")
		@RequestParam
		String regionCode)
	throws 
		EntityDoesNotExistException {
		
		return this.policySetService.getDtoMapper().mapRegionEntityToDto(this.policySetService.getRegionByCode(regionCode));
	}

	
	@ApiOperation(value = "renameRegion()", notes = "Renames the region specified by oldRegionCode to newRegionCode")
	@GetMapping("/renameRegion")
	public @ResponseBody com.djt.cvpp.ota.orfin.policy.mapper.dto.region.Region renameRegion(
		String oldRegionCode, 
		String newRegionCode)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException {
	
		return this.policySetService.getDtoMapper().mapRegionEntityToDto(this.policySetService.renameRegion(oldRegionCode, newRegionCode));
	}

	
	@ApiOperation(value = "updateRegion()", notes = "Updates the region specified by regionCode")
	@GetMapping("/updateRegion")
	public @ResponseBody com.djt.cvpp.ota.orfin.policy.mapper.dto.region.Region updateRegion(
		@ApiParam("The unique code of the region")
		@RequestParam
		String regionCode,
		
		@ApiParam("The new name for the region")
		@RequestParam
		String countryName)
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		return this.policySetService.getDtoMapper().mapRegionEntityToDto(this.policySetService.updateRegion(regionCode, countryName));
	}
	
	
	@ApiOperation(value = "deleteRegion()", notes = "Deletes the region specified by regionCode")
	@GetMapping("/deleteRegion")
	public void deleteRegion(
		@ApiParam("The unique code of the region")
		@RequestParam
		String regionCode)
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		this.policySetService.deleteRegion(regionCode);
	}
	
	
	@ApiOperation(value = "renderGenericPolicyTableJsonForProgramAndRegion()", notes = "Renders the Policy Table JSON for upload to TMC for the given programCode, modelYear and regionCode")
	@GetMapping("/renderGenericPolicyTableJsonForProgramAndRegion")
	public @ResponseBody String renderGenericPolicyTableJsonForProgramAndRegion(
		@ApiParam("The programCode")
		@RequestParam
		String programCode,

		@ApiParam("The modelYear")
		@RequestParam
		Integer modelYear,
		
		@ApiParam("The regionCode")
		@RequestParam
		String regionCode)
	throws 
		EntityDoesNotExistException,
		ValidationException {

		ProgramModelYear programModelYear = this.programModelYearService.getProgramModelYearByProgramCodeAndModelYear(programCode, modelYear);
		return this.policySetService.renderGenericPolicyTableJsonForProgramAndRegion(programModelYear, regionCode);
	}
	

	@ApiOperation(value = PUBLISH_POLICY_SET_EVENT_URI, notes = "Publishes the policy set identified by policy set name")
	@GetMapping(PUBLISH_POLICY_SET_EVENT_URI)
	public @ResponseBody OrfinPolicySetEvent publishOrfinPolicySetEvent(
		@ApiParam("The ID of the user that is approving of the publish event") 
		@RequestParam 
		String owner,
		
		@ApiParam("The programCode")
		@RequestParam
		String programCode,

		@ApiParam("The modelYear")
		@RequestParam
		Integer modelYear,

		@ApiParam("The regionCode")
		@RequestParam
		String regionCode,
		
		@ApiParam("The unique name of the policy set (\"GLOBAL\" for the global policy set.)")
		@RequestParam
		String policySetName)
	throws 
		ValidationException {
		
		return this.policySetService.publishOrfinPolicySetEvent(owner, programCode, modelYear, regionCode, policySetName);
	}	
}
