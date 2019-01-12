/*
 * 
 * 
 * 
 * 
 * 
 * 
 */
package com.djt.cvpp.ota.orfin.client.program;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.djt.cvpp.ota.common.exception.EntityAlreadyExistsException;
import com.djt.cvpp.ota.common.exception.EntityDoesNotExistException;
import com.djt.cvpp.ota.common.exception.ValidationException;
import com.djt.cvpp.ota.orfin.api.program.ProgramModelYearApplicationService;
import com.djt.cvpp.ota.orfin.api.program.ProgramModelYearList;
import com.djt.cvpp.ota.orfin.client.AbstractOrfinServiceClient;

/**
 *
 * @author tmyers1@yahoo.com (Tom Myers)
 *
 */
@Component
public class ProgramModelYearApplicationServiceClient extends AbstractOrfinServiceClient implements ProgramModelYearApplicationService {
	
	private RestTemplate restTemplate;
	
	public ProgramModelYearApplicationServiceClient() {
		
		this.restTemplate = super.restTemplate();
	}
	
	public com.djt.cvpp.ota.orfin.program.mapper.dto.ProgramModelYear createProgramModelYear(
		String programCode,
		Integer modelYear) 
	throws 
		EntityAlreadyExistsException, 
		ValidationException {
		
		String uri = buildEndpointUri(PROGRAM_MODEL_YEAR_URI, CREATE_PROGRAM_MODEL_YEAR_URI);
		uri =  UriComponentsBuilder
			.fromHttpUrl(uri)
			.queryParam(PROGRAM_CODE, programCode)
			.queryParam(MODEL_YEAR, modelYear)
			.toUriString();
		
		return this.restTemplate.getForObject(uri, com.djt.cvpp.ota.orfin.program.mapper.dto.ProgramModelYear.class);
	}
	
	public ProgramModelYearList getAllProgramModelYears() {
		
		String uri = buildEndpointUri(PROGRAM_MODEL_YEAR_URI, GET_ALL_PROGRAM_MODEL_YEARS_URI);
		return this.restTemplate.getForObject(uri, ProgramModelYearList.class);
	}
	
	public com.djt.cvpp.ota.orfin.program.mapper.dto.ProgramModelYear getProgramModelYearByProgramCodeAndModelYear(
		String programCode,
		Integer modelYear) 
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		String uri = buildEndpointUri(PROGRAM_MODEL_YEAR_URI, GET_PROGRAM_MODEL_YEAR_BY_PROGRAM_CODE_AND_MODEL_YEAR_URI);
		uri =  UriComponentsBuilder
			.fromHttpUrl(uri)
			.queryParam(PROGRAM_CODE, programCode)
			.queryParam(MODEL_YEAR, modelYear)
			.toUriString();
		
		return this.restTemplate.getForObject(uri, com.djt.cvpp.ota.orfin.program.mapper.dto.ProgramModelYear.class);
	}
	
	public com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl getOdlByProgramCodeAndModelYear(
		String programCode,
		Integer modelYear)
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet getPolicySetByProgramCodeAndModelYear(
		String programCode,
		Integer modelYear)
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		throw new RuntimeException("Not implemented yet.");
	}

	public com.djt.cvpp.ota.orfin.delivery.mapper.dto.DeliveryRuleSet getDeliveryRuleSetByProgramCodeAndModelYear(
		String programCode,
		Integer modelYear)
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		throw new RuntimeException("Not implemented yet.");
	}

	public com.djt.cvpp.ota.orfin.program.mapper.dto.ProgramModelYear associateOdlToProgramModelYear(
		String odlName,	
		String programCode,
		Integer modelYear)
	throws 
		EntityDoesNotExistException,
		ValidationException,
		EntityAlreadyExistsException {
		
		String uri = buildEndpointUri(PROGRAM_MODEL_YEAR_URI, ASSOCIATE_ODL_TO_PROGRAM_MODEL_YEAR_URI);
		uri =  UriComponentsBuilder
			.fromHttpUrl(uri)
			.queryParam(ODL_NAME, odlName)
			.queryParam(PROGRAM_CODE, programCode)
			.queryParam(MODEL_YEAR, modelYear)
			.toUriString();
		
		return this.restTemplate.getForObject(uri, com.djt.cvpp.ota.orfin.program.mapper.dto.ProgramModelYear.class);
	}

	public com.djt.cvpp.ota.orfin.program.mapper.dto.ProgramModelYear disassociateOdlFromProgramModelYear(
		String odlName,	
		String programCode,
		Integer modelYear)
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public com.djt.cvpp.ota.orfin.program.mapper.dto.ProgramModelYear associatePolicySetToProgramModelYear(
		String policySetName,	
		String programCode,
		Integer modelYear)
	throws 
		EntityDoesNotExistException,
		ValidationException,
		EntityAlreadyExistsException {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public com.djt.cvpp.ota.orfin.program.mapper.dto.ProgramModelYear disassociatePolicySetFromProgramModelYear(
		String policySetName,	
		String programCode,
		Integer modelYear)
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public com.djt.cvpp.ota.orfin.program.mapper.dto.ProgramModelYear addDeliveryRuleSetToProgramModelYear(
		String deliveryRuleSetName,	
		String programCode,
		Integer modelYear)
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		throw new RuntimeException("Not implemented yet.");
	}
		
	public com.djt.cvpp.ota.orfin.program.mapper.dto.ProgramModelYear removeDeliveryRuleSetFromProgramModelYear(
		String deliveryRuleSetName,	
		String programCode,
		Integer modelYear)
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		throw new RuntimeException("Not implemented yet.");
	}
}
