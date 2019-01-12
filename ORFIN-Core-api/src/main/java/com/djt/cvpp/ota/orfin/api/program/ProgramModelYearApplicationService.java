/*
 * 
 * 
 * 
 * 
 * 
 * 
 */
package com.djt.cvpp.ota.orfin.api.program;

import com.djt.cvpp.ota.common.exception.EntityAlreadyExistsException;
import com.djt.cvpp.ota.common.exception.EntityDoesNotExistException;
import com.djt.cvpp.ota.common.exception.ValidationException;

/**
 * 
 * @author tmyers1@yahoo.com (Tom Myers)
 *
 */
public interface ProgramModelYearApplicationService {
	
	String PROGRAM_MODEL_YEAR_URI = "/api/v1/program";
	
	String PROGRAM_CODE = "programCode";
	String MODEL_YEAR = "modelYear";
	String ODL_NAME = "odlName";
	String POLICY_SET_NAME = "policySetName";
	String DELIVERY_RULE_SET_NAME = "deliveryRuleSetName";
	
	/**
	 * 
	 * @param programCode
	 * @param modelYear
	 * @return
	 * @throws EntityAlreadyExistsException
	 * @throws ValidationException
	 */
	String CREATE_PROGRAM_MODEL_YEAR_URI = "/createProgramModelYear";
	com.djt.cvpp.ota.orfin.program.mapper.dto.ProgramModelYear createProgramModelYear(
		String programCode,
		Integer modelYear) 
	throws 
		EntityAlreadyExistsException, 
		ValidationException;
	
	
	/**
	 * 
	 * @return
	 */
	String GET_ALL_PROGRAM_MODEL_YEARS_URI = "/getAllProgramModelYears";
	ProgramModelYearList getAllProgramModelYears();
	
	
	/**
	 * 
	 * @param programCode
	 * @param modelYear
	 * @return
	 * @throws EntityDoesNotExistException
	 * @throws ValidationException
	 */
	String GET_PROGRAM_MODEL_YEAR_BY_PROGRAM_CODE_AND_MODEL_YEAR_URI = "/getProgramModelYearByProgramCodeAndModelYear";
	com.djt.cvpp.ota.orfin.program.mapper.dto.ProgramModelYear getProgramModelYearByProgramCodeAndModelYear(
		String programCode,
		Integer modelYear) 
	throws 
		EntityDoesNotExistException,
		ValidationException;	

	
	/**
	 * 
	 * @param programCode
	 * @param modelYear
	 * 
	 * @return The requested ODL
	 * 
	 * @throws EntityDoesNotExistException ORFIN-PROGRAM-1005: There does not exist an ODL that is associated with Program model year: [programCode + modelYear]
	 * @throws ValidationException ORFIN-PROGRAM-1006: Could not retrieve ODL associated with program model year: [programCode + modelYear] because attribute: [attribute] was invalid for reason: [reason]
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
	 * @param programCode
	 * @param modelYear
	 * 
	 * @return The requested PolicySet
	 * 
	 * @throws EntityDoesNotExistException ORFIN-PROGRAM-1007: There does not exist a PolicySet that is associated with Program model year: [programCode + modelYear]
	 * @throws ValidationException ORFIN-PROGRAM-1008: Could not retrieve PolicySet associated with program model year: [programCode + modelYear] because attribute: [attribute] was invalid for reason: [reason]
	 */
	String GET_POLICY_SET_BY_PROGRAM_CODE_AND_MODEL_YEAR_URI = "/getPolicySetByProgramCodeAndModelYear";
	com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet getPolicySetByProgramCodeAndModelYear(
		String programCode,
		Integer modelYear)
	throws 
		EntityDoesNotExistException,
		ValidationException;

	/**
	 * 
	 * @param programCode
	 * @param modelYear
	 * 
	 * @return The requested DeliveryRuleSet
	 * 
	 * @throws EntityDoesNotExistException ORFIN-PROGRAM-1008: There does not exist a DeliveryRuleSet that is associated with Program model year: [programCode + modelYear]
	 * @throws ValidationException ORFIN-PROGRAM-1009: Could not retrieve DeliveryRuleSet associated with program model year: [programCode + modelYear] because attribute: [attribute] was invalid for reason: [reason]
	 */
	String GET_DELIVERY_SET_BY_PROGRAM_CODE_AND_MODEL_YEAR_URI = "/getDeliveryRuleSetByProgramCodeAndModelYear";
	com.djt.cvpp.ota.orfin.delivery.mapper.dto.DeliveryRuleSet getDeliveryRuleSetByProgramCodeAndModelYear(
		String programCode,
		Integer modelYear)
	throws 
		EntityDoesNotExistException,
		ValidationException;

	/**
	 * 
	 * @param odlName
	 * @param programCode
	 * @param modelYear
	 * 
	 * @return The affected ProgramModelYear
	 * 
	 * @throws EntityDoesNotExistException ORFIN-PROGRAM-1010: Could not associate ODL with name: [odlName] to program model year: [programCode + modelYear] because either of the two does not exist
	 * @throws ValidationException ORFIN-PROGRAM-1011: Could not associate ODL with name: [odlName] to program model year: [programCode + modelYear] because attribute: [attribute] was invalid for reason: [reason]
	 * @throws EntityAlreadyExistsException
	 */
	String ASSOCIATE_ODL_TO_PROGRAM_MODEL_YEAR_URI = "/associateOdlToProgramModelYear";
	com.djt.cvpp.ota.orfin.program.mapper.dto.ProgramModelYear associateOdlToProgramModelYear(
		String odlName,	
		String programCode,
		Integer modelYear)
	throws 
		EntityDoesNotExistException,
		ValidationException,
		EntityAlreadyExistsException;

	/**
	 * 
	 * @param odlName
	 * @param programCode
	 * @param modelYear
	 * 
	 * @return The affected ProgramModelYear
	 * 
	 * @throws EntityDoesNotExistException ORFIN-PROGRAM-1012: Could not disassociate ODL with name: [odlName] from program model year: [programCode + modelYear] because either of the two does not exist
	 * @throws ValidationException ORFIN-PROGRAM-1013: Could not disassociate ODL with name: [odlName] from program model year: [programCode + modelYear] because attribute: [attribute] was invalid for reason: [reason]
	 */
	String DISASSOCIATE_ODL_FROM_PROGRAM_MODEL_YEAR_URI = "/disassociateOdlFromProgramModelYear";
	com.djt.cvpp.ota.orfin.program.mapper.dto.ProgramModelYear disassociateOdlFromProgramModelYear(
		String odlName,	
		String programCode,
		Integer modelYear)
	throws 
		EntityDoesNotExistException,
		ValidationException;
	
	/**
	 * 
	 * @param policySetName
	 * @param programCode
	 * @param modelYear
	 * 
	 * @return The affected ProgramModelYear
	 * 
	 * @throws EntityDoesNotExistException ORFIN-PROGRAM-1014: Could not associate PolicySet with name: [policySetName] to program model year: [programCode + modelYear] because either of the two does not exist
	 * @throws ValidationException ORFIN-PROGRAM-1015: Could not associate PolicySet with name: [policySetName] to program model year: [programCode + modelYear] because attribute: [attribute] was invalid for reason: [reason]
	 * @throws EntityAlreadyExistsException
	 */
	String ASSOCIATE_POLICY_SET_TO_PROGRAM_MODEL_YEAR_URI = "/associatePolicySetToProgramModelYear";
	com.djt.cvpp.ota.orfin.program.mapper.dto.ProgramModelYear associatePolicySetToProgramModelYear(
		String policySetName,	
		String programCode,
		Integer modelYear)
	throws 
		EntityDoesNotExistException,
		ValidationException,
		EntityAlreadyExistsException;
	
	/**
	 * 
	 * @param policySetName
	 * @param programCode
	 * @param modelYear
	 * 
	 * @return The affected ProgramModelYear
	 * 
	 * @throws EntityDoesNotExistException ORFIN-PROGRAM-1016: Could not disassociate PolicySet with name: [policySetName] from program model year: [programCode + modelYear] because either of the two does not exist
	 * @throws ValidationException ORFIN-PROGRAM-1017: Could not disassociate PolicySet with name: [policySetName] from program model year: [programCode + modelYear] because attribute: [attribute] was invalid for reason: [reason]
	 */
	String DISASSOCIATE_POLICY_SETL_FROM_PROGRAM_MODEL_YEAR_URI = "/disassociatePolicySetFromProgramModelYear";
	com.djt.cvpp.ota.orfin.program.mapper.dto.ProgramModelYear disassociatePolicySetFromProgramModelYear(
		String policySetName,	
		String programCode,
		Integer modelYear)
	throws 
		EntityDoesNotExistException,
		ValidationException;
	
	/**
	 * 
	 * @param deliveryRuleSetName
	 * @param programCode
	 * @param modelYear
	 * 
	 * @return The affected ProgramModelYear
	 * 
	 * @throws EntityDoesNotExistException ORFIN-PROGRAM-1018: Could not add DeliveryRuleSet with name: [deliveryRuleSetName] to program model year: [programCode + modelYear] because either of the two does not exist
	 * @throws ValidationException ORFIN-PROGRAM-1019: Could not add DeliverySet with name: [deliveryRuleSetName] to program model year: [programCode + modelYear] because attribute: [attribute] was invalid for reason: [reason]
	 */
	String ADD_DELIVERY_RULE_SET_TO_PROGRAM_MODEL_YEAR_URI = "/addDeliveryRuleSetToProgramModelYear";
	com.djt.cvpp.ota.orfin.program.mapper.dto.ProgramModelYear addDeliveryRuleSetToProgramModelYear(
		String deliveryRuleSetName,	
		String programCode,
		Integer modelYear)
	throws 
		EntityDoesNotExistException,
		ValidationException;
		
	/**
	 * 
	 * @param deliveryRuleSetName
	 * @param programCode
	 * @param modelYear
	 * 
	 * @return The affected ProgramModelYear
	 * 
	 * @throws EntityDoesNotExistException ORFIN-PROGRAM-1020: Could not remove DeliveryRuleSet with name: [deliveryRuleSetName] from program model year: [programCode + modelYear] because either of the two does not exist
	 * @throws ValidationException ORFIN-PROGRAM-1021: Could not remove DeliverySet with name: [deliveryRuleSetName] from program model year: [programCode + modelYear] because attribute: [attribute] was invalid for reason: [reason]
	 */
	String REMOVE_DELIVERY_RULE_SET_FROM_PROGRAM_MODEL_YEAR_URI = "/removeDeliveryRuleSetFromProgramModelYear";
	com.djt.cvpp.ota.orfin.program.mapper.dto.ProgramModelYear removeDeliveryRuleSetFromProgramModelYear(
		String deliveryRuleSetName,	
		String programCode,
		Integer modelYear)
	throws 
		EntityDoesNotExistException,
		ValidationException;	
}
