/*
 * 
 * 
 * 
 * 
 * 
 * 
 */
package com.djt.cvpp.ota.orfin.app.program.controller;

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
import com.djt.cvpp.ota.orfin.api.program.ProgramModelYearApplicationService;
import com.djt.cvpp.ota.orfin.api.program.ProgramModelYearList;
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
import com.djt.cvpp.ota.orfin.policy.event.impl.MockOrfinPolicySetEventPublisher;
import com.djt.cvpp.ota.orfin.policy.repository.PolicySetRepository;
import com.djt.cvpp.ota.orfin.policy.repository.impl.ClasspathPolicySetRepositoryImpl;
import com.djt.cvpp.ota.orfin.policy.service.PolicySetService;
import com.djt.cvpp.ota.orfin.policy.service.impl.PolicySetServiceImpl;
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
@RequestMapping(path = ProgramModelYearApplicationService.PROGRAM_MODEL_YEAR_URI)
public class ProgramModelYearController implements ProgramModelYearApplicationService {
	
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
	
	public ProgramModelYearController() {
		
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

	
	@ApiOperation(value = CREATE_PROGRAM_MODEL_YEAR_URI, notes = "Creates a ProgramModel year with the given programCode and modelYear")
	@GetMapping(CREATE_PROGRAM_MODEL_YEAR_URI)
	public @ResponseBody com.djt.cvpp.ota.orfin.program.mapper.dto.ProgramModelYear createProgramModelYear(
		@ApiParam("The parent program code of the ProgramModelYear to create")
		@RequestParam
		String programCode,

		@ApiParam("The parent model year of the ProgramModelYear to create")
		@RequestParam
		Integer modelYear) 
	throws 
		EntityAlreadyExistsException, 
		ValidationException {
		
		return this.programModelYearService.getDtoMapper().mapEntityToDto(this.programModelYearService.createProgramModelYear(programCode, modelYear));
	}

	
	@ApiOperation(value = GET_ALL_PROGRAM_MODEL_YEARS_URI, notes = "Gets all defined ProgramModelYears")
	@GetMapping(GET_ALL_PROGRAM_MODEL_YEARS_URI)
	public @ResponseBody ProgramModelYearList getAllProgramModelYears() {
		
		List<com.djt.cvpp.ota.orfin.program.mapper.dto.ProgramModelYear> list = new ArrayList<>();
		
		Iterator<com.djt.cvpp.ota.orfin.program.model.ProgramModelYear> iterator = this.programModelYearService.getAllProgramModelYears().iterator();
		while (iterator.hasNext()) {
		
			list.add(this.programModelYearService.getDtoMapper().mapEntityToDto(iterator.next()));
		}
		
		ProgramModelYearList dto = new ProgramModelYearList();
		dto.setProgramModelYears(list);
		return dto;
	}

	
	@ApiOperation(value = GET_PROGRAM_MODEL_YEAR_BY_PROGRAM_CODE_AND_MODEL_YEAR_URI, notes = "The ProgramModelYear specified by programCode and modelYear")
	@GetMapping(GET_PROGRAM_MODEL_YEAR_BY_PROGRAM_CODE_AND_MODEL_YEAR_URI)
	public @ResponseBody com.djt.cvpp.ota.orfin.program.mapper.dto.ProgramModelYear getProgramModelYearByProgramCodeAndModelYear(
		@ApiParam("The parent program code of the ProgramModelYear to retrieve")
		@RequestParam
		String programCode,

		@ApiParam("The parent model year of the ProgramModelYear to retrieve")
		@RequestParam
		Integer modelYear) 
	throws 
		EntityDoesNotExistException,
		ValidationException {
	
		return this.programModelYearService.getDtoMapper().mapEntityToDto(this.programModelYearService.getProgramModelYearByProgramCodeAndModelYear(programCode, modelYear));
	}
	
	
	@ApiOperation(value = GET_ODL_BY_PROGRAM_CODE_AND_MODEL_YEAR_URI, notes = "getOdlByProgramCodeAndModelYear")
	@GetMapping(GET_ODL_BY_PROGRAM_CODE_AND_MODEL_YEAR_URI)
	public com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl getOdlByProgramCodeAndModelYear(
		@ApiParam("The parent program code of the ODL to retrieve")
		@RequestParam
		String programCode,

		@ApiParam("The parent model year of the ODL to retrieve")
		@RequestParam
		Integer modelYear) 
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		return this.optimizedDataListService.getDtoMapper().mapEntityToDto(this.programModelYearService.getOdlByProgramCodeAndModelYear(programCode, modelYear));
	}

	
	@ApiOperation(value = GET_POLICY_SET_BY_PROGRAM_CODE_AND_MODEL_YEAR_URI, notes = "getPolicySetByProgramCodeAndModelYear")
	@GetMapping(GET_POLICY_SET_BY_PROGRAM_CODE_AND_MODEL_YEAR_URI)
	public com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet getPolicySetByProgramCodeAndModelYear(
		@ApiParam("The parent program code of the PolicySet to retrieve")
		@RequestParam
		String programCode,

		@ApiParam("The parent model year of the PolicySet to retrieve")
		@RequestParam
		Integer modelYear) 
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		return this.policySetService.getDtoMapper().mapEntityToDto(this.programModelYearService.getPolicySetByProgramCodeAndModelYear(programCode, modelYear));
	}

	// TODO: TDM: This should return a list.
	// May want to have a signature to return the "default" delivery rule set to be used for automatic rollouts
	@ApiOperation(value = GET_DELIVERY_SET_BY_PROGRAM_CODE_AND_MODEL_YEAR_URI, notes = "getDeliveryRuleSetByProgramCodeAndModelYear")
	@GetMapping(GET_DELIVERY_SET_BY_PROGRAM_CODE_AND_MODEL_YEAR_URI)
	public com.djt.cvpp.ota.orfin.delivery.mapper.dto.DeliveryRuleSet getDeliveryRuleSetByProgramCodeAndModelYear(
		@ApiParam("The parent program code of the DeliveryRuleSet to retrieve")
		@RequestParam
		String programCode,

		@ApiParam("The parent model year of the DeliveryRuleSet to retrieve")
		@RequestParam
		Integer modelYear) 
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		return this.deliveryRuleSetService.getDtoMapper().mapEntityToDto(this.programModelYearService.getDeliveryRuleSetByProgramCodeAndModelYear(programCode, modelYear));
	}

	
	@ApiOperation(value = ASSOCIATE_ODL_TO_PROGRAM_MODEL_YEAR_URI, notes = "associateOdlToProgramModelYear")
	@GetMapping(ASSOCIATE_ODL_TO_PROGRAM_MODEL_YEAR_URI)
	public com.djt.cvpp.ota.orfin.program.mapper.dto.ProgramModelYear associateOdlToProgramModelYear(
		@ApiParam("The name of the ODL to associate to the given ProgramModelYear")
		@RequestParam
		String odlName,	
		
		@ApiParam("The parent program code of the ProgramModelYear")
		@RequestParam
		String programCode,

		@ApiParam("The parent model year of the ProgramModelYear")
		@RequestParam
		Integer modelYear) 
	throws 
		EntityDoesNotExistException,
		ValidationException,
		EntityAlreadyExistsException {
		
		return this.programModelYearService.getDtoMapper().mapEntityToDto(this.programModelYearService.associateOdlToProgramModelYear(odlName, programCode, modelYear));
	}

	
	@ApiOperation(value = "disassociateOdlFromProgramModelYear()", notes = "disassociateOdlFromProgramModelYear")
	@GetMapping("/disassociateOdlFromProgramModelYear")
	public com.djt.cvpp.ota.orfin.program.mapper.dto.ProgramModelYear disassociateOdlFromProgramModelYear(
		String odlName,	
		String programCode,
		Integer modelYear)
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		return this.programModelYearService.getDtoMapper().mapEntityToDto(this.programModelYearService.disassociateOdlFromProgramModelYear(odlName, programCode, modelYear));
	}

	
	@ApiOperation(value = "associatePolicySetToProgramModelYear()", notes = "associatePolicySetToProgramModelYear")
	@GetMapping("/associatePolicySetToProgramModelYear")
	public com.djt.cvpp.ota.orfin.program.mapper.dto.ProgramModelYear associatePolicySetToProgramModelYear(
		String policySetName,	
		String programCode,
		Integer modelYear)
	throws 
		EntityDoesNotExistException,
		ValidationException,
		EntityAlreadyExistsException {
		
		return this.programModelYearService.getDtoMapper().mapEntityToDto(this.programModelYearService.associatePolicySetToProgramModelYear(policySetName, programCode, modelYear));
	}

	
	@ApiOperation(value = "disassociatePolicySetFromProgramModelYear()", notes = "disassociatePolicySetFromProgramModelYear")
	@GetMapping("/disassociatePolicySetFromProgramModelYear")
	public com.djt.cvpp.ota.orfin.program.mapper.dto.ProgramModelYear disassociatePolicySetFromProgramModelYear(
		String policySetName,	
		String programCode,
		Integer modelYear)
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		return this.programModelYearService.getDtoMapper().mapEntityToDto(this.programModelYearService.disassociatePolicySetFromProgramModelYear(policySetName, programCode, modelYear));
	}

	
	@ApiOperation(value = "addDeliveryRuleSetToProgramModelYear()", notes = "addDeliveryRuleSetToProgramModelYear")
	@GetMapping("/addDeliveryRuleSetToProgramModelYear")
	public com.djt.cvpp.ota.orfin.program.mapper.dto.ProgramModelYear addDeliveryRuleSetToProgramModelYear(
		String deliveryRuleSetName,	
		String programCode,
		Integer modelYear)
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		return this.programModelYearService.getDtoMapper().mapEntityToDto(this.programModelYearService.addDeliveryRuleSetToProgramModelYear(deliveryRuleSetName, programCode, modelYear));
	}

	
	@ApiOperation(value = "removeDeliveryRuleSetFromProgramModelYear()", notes = "removeDeliveryRuleSetFromProgramModelYear")
	@GetMapping("/removeDeliveryRuleSetFromProgramModelYear")
	public com.djt.cvpp.ota.orfin.program.mapper.dto.ProgramModelYear removeDeliveryRuleSetFromProgramModelYear(
		String deliveryRuleSetName,	
		String programCode,
		Integer modelYear)
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		return this.programModelYearService.getDtoMapper().mapEntityToDto(this.programModelYearService.removeDeliveryRuleSetFromProgramModelYear(deliveryRuleSetName, programCode, modelYear));
	}
}
