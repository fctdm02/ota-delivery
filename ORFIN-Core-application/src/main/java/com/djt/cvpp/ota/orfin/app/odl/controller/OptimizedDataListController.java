/*
 * 
 * 
 * 
 * 
 * 
 * 
 */
package com.djt.cvpp.ota.orfin.app.odl.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.djt.cvpp.ota.common.exception.EntityAlreadyExistsException;
import com.djt.cvpp.ota.common.exception.EntityDoesNotExistException;
import com.djt.cvpp.ota.common.exception.ValidationException;
import com.djt.cvpp.ota.orfin.api.odl.OdlList;
import com.djt.cvpp.ota.orfin.api.odl.OptimizedDataListApplicationService;
import com.djt.cvpp.ota.orfin.client.program.ProgramModelYearApplicationServiceClient;
import com.djt.cvpp.ota.orfin.odl.event.OrfinOdlEvent;
import com.djt.cvpp.ota.orfin.odl.event.OrfinOdlEventPublisher;
import com.djt.cvpp.ota.orfin.odl.event.impl.MockOrfinOdlEventPublisher;
import com.djt.cvpp.ota.orfin.odl.repository.OptimizedDataListRepository;
import com.djt.cvpp.ota.orfin.odl.repository.impl.MockOptimizedDataListRepositoryImpl;
import com.djt.cvpp.ota.orfin.odl.service.OptimizedDataListService;
import com.djt.cvpp.ota.orfin.odl.service.impl.OptimizedDataListServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 *
 * @author tmyers1@yahoo.com (Tom Myers)
 *
 */
@RestController
@RequestMapping(path = OptimizedDataListApplicationService.ODL_URI)
public class OptimizedDataListController implements OptimizedDataListApplicationService {
	
	@Autowired
	private ProgramModelYearApplicationServiceClient programModelYearApplicationServiceClient;
	
	private OrfinOdlEventPublisher orfinOdlEventPublisher;
	private OptimizedDataListRepository optimizedDataListRepository;
	private OptimizedDataListService optimizedDataListService;
	
	public OptimizedDataListController() {
		
		this.orfinOdlEventPublisher = MockOrfinOdlEventPublisher.getInstance();
		this.optimizedDataListRepository = MockOptimizedDataListRepositoryImpl.getInstance();
		this.optimizedDataListRepository.reset();
		this.optimizedDataListService = new OptimizedDataListServiceImpl(
			this.optimizedDataListRepository,
			this.orfinOdlEventPublisher);
	}

	
	// ************************************************************************************************************************************
	// CONTROLLER METHODS MAP TO SERVICE METHODS ONE-TO-ONE.  ALL SWAGGER DOC INFO COMES FROM JAVADOC IN SERVICE INTERFACE.
	// ************************************************************************************************************************************
	
	
	@ApiOperation(value = CREATE_ODL_URI, notes = "Creates an Optimized Data List with the specified name")
	@GetMapping(CREATE_ODL_URI)
	public @ResponseBody com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl createOdl(
		@ApiParam(ODL_NAME)
		@RequestParam
		String odlName) 
	throws 
		EntityAlreadyExistsException, 
		ValidationException {
		
		return this.optimizedDataListService.getDtoMapper().mapEntityToDto(this.optimizedDataListService.createOdl(odlName));
	}
	

	@ApiOperation(value = CREATE_NETWORK_URI, notes = "Creates a child Network for the given ODL with the specified name")
	@GetMapping(CREATE_NETWORK_URI)
	public @ResponseBody com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl createNetwork(
		@ApiParam(ODL_NAME)
		@RequestParam
		String odlName,

		@ApiParam(NETWORK_NAME)
		@RequestParam
		String networkName,
		
		@ApiParam(PROTOCOL)
		@RequestParam
		String protocol,
		
		@ApiParam(DATA_RATE)
		@RequestParam
		String dataRate,
		
		@ApiParam(DCL_NAME)
		@RequestParam
		String dclName,
		
		@ApiParam(NETWORK_PINS)
		@RequestParam
		String networkPins)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException,
		ValidationException {
		
		return this.optimizedDataListService.getDtoMapper().mapEntityToDto(this.optimizedDataListService.createNetwork(odlName, networkName, protocol, dataRate, dclName, networkPins));
	}
	
	@ApiOperation(value = CREATE_NODE_URI, notes = "Creates a child Node for the given ODL with the specified name")
	@GetMapping(CREATE_NODE_URI)
	public @ResponseBody com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl createNode(
		@ApiParam(ODL_NAME)
		@RequestParam
		String odlName,

		@ApiParam(NETWORK_NAME)
		@RequestParam
		String networkName,

		@ApiParam(NODE_ACRONYM)
		@RequestParam
		String nodeAcronym,
		
		@ApiParam(NODE_ADDRESS)
		@RequestParam
		String nodeAddress,
		
		@ApiParam(GATEWAY_NODE_ID)
		@RequestParam
		String gatewayNodeId,

		@ApiParam(GATEWAY_TYPE)
		@RequestParam
		String gatewayType,
		
		@ApiParam(HAS_CONDITION_BASED_ON_DTC)
		@RequestParam
		Boolean hasConditionBasedOnDtc,
		
		@ApiParam(IS_OVTP)
		@RequestParam
		Boolean isOvtp,
		
		@ApiParam(OVTP_DESTINATION_ADDRESS)
		@RequestParam
		String ovtpDestinationAddress,
		
		@ApiParam(SPECIFICATION_CATEGORY_TYPE)
		@RequestParam
		String specificationCategoryType,
		
		@ApiParam(DIAGNOSTIC_SPECIFICATION_RESPONSE)
		@RequestParam
		Integer diagnosticSpecificationResponse,
		
		@ApiParam(ACTIVATION_TIME)
		@RequestParam
		Integer activationTime)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException,
		ValidationException {
		
		return this.optimizedDataListService.getDtoMapper().mapEntityToDto(this.optimizedDataListService.createNode(odlName, networkName, nodeAcronym, nodeAddress, gatewayNodeId, gatewayType, hasConditionBasedOnDtc, isOvtp, ovtpDestinationAddress, specificationCategoryType, diagnosticSpecificationResponse, activationTime));
	}
	
	@ApiOperation(value = ADD_IGNORED_DIDS_TO_NODE_URI, notes = "Adds the list of did addresses as ignored for the specified node")
	@GetMapping(ADD_IGNORED_DIDS_TO_NODE_URI)
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

		String[] array = ignoredDids.split(",");
		List<String> list = new ArrayList<>();
		for (int i=0; i < array.length; i++) {
			
			list.add(array[i]);
		}
		
		return this.optimizedDataListService.getDtoMapper().mapEntityToDto(this.optimizedDataListService.addIgnoredDidsToNode(odlName, networkName, nodeAcronym, nodeAddress, list));
	}
	
	@ApiOperation(value = CREATE_NODE_URI, notes = "Creates a child Did for the given ODL with the specified name")
	@GetMapping(CREATE_DID_URI)
	public @ResponseBody com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl createDid(
		@ApiParam(ODL_NAME)
		@RequestParam
		String odlName,

		@ApiParam(NETWORK_NAME)
		@RequestParam
		String networkName,

		@ApiParam(NODE_ACRONYM)
		@RequestParam
		String nodeAcronym,
		
		@ApiParam(NODE_ADDRESS)
		@RequestParam
		String nodeAddress,

		@ApiParam(DID_NAME)
		@RequestParam
		String didName,
		
		@ApiParam(DESCRIPTION)
		@RequestParam
		String description,
		
		@ApiParam(VIN_SPECIFIC_FLAG)
		@RequestParam
		Boolean vinSpecificDidFlag,
		
		@ApiParam(DIRECT_CONFIGURATION_DID_FLAG)
		@RequestParam
		Boolean directConfigurationDidFlag,
		
		@ApiParam(PRIVATE_NETWORK_DID_FLAG)
		@RequestParam
		Boolean privateNetworkDidFlag)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException,
		ValidationException {
		
		return this.optimizedDataListService.getDtoMapper().mapEntityToDto(this.optimizedDataListService.createDid(odlName, networkName, nodeAcronym, nodeAddress, didName, description, vinSpecificDidFlag, directConfigurationDidFlag, privateNetworkDidFlag));		
	}
				
	@ApiOperation(value = ADD_ECG_SIGNAL_TO_ODL_URI, notes = "Adds an ECG Signal with the given name to the ODL with the given ODL name")
	@GetMapping(ADD_ECG_SIGNAL_TO_ODL_URI)
	public @ResponseBody com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl addEcgSignalToOdl(
		@ApiParam(ODL_NAME)
		@RequestParam
		String odlName,

		@ApiParam(ECG_SIGNAL_NAME)
		@RequestParam
		String ecgSignalName)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException,
		ValidationException {
		
		return this.optimizedDataListService.getDtoMapper().mapEntityToDto(this.optimizedDataListService.addEcgSignalToOdl(odlName, ecgSignalName));
	}

	
	@ApiOperation(value = ADD_CUSTOM_ODL_TO_ODL_URI, notes = "Adds a custom ODL with the given name and associated sub-set of nodes from the parent ODL with the given name")
	@GetMapping(ADD_CUSTOM_ODL_TO_ODL_URI)
	public @ResponseBody com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl addCustomOdlToOdl(
		@ApiParam(ODL_NAME)
		@RequestParam
		String odlName,
		
		@ApiParam(CUSTOM_ODL_NAME)
		@RequestParam
		String customOdlName,
		
		@ApiParam(CUSTOM_ODL_NODE_LIST)
		@RequestParam
		String customOdlNodeList)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException,
		ValidationException {
		
		String[] array = customOdlNodeList.split(",");
		List<String> list = new ArrayList<>();
		for (int i=0; i < array.length; i++) {
			
			list.add(array[i]);
		}
		
		return this.optimizedDataListService.getDtoMapper().mapEntityToDto(this.optimizedDataListService.addCustomOdlToOdl(odlName, customOdlName, list));
	}
	
	
	@ApiOperation(value = GET_ALL_ODLS_URI, notes = "All ODLs that have been created")
	@GetMapping(GET_ALL_ODLS_URI)
	public @ResponseBody OdlList getAllOdls() {
		
		List<com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl> list = new ArrayList<>();
		
		Iterator<com.djt.cvpp.ota.orfin.odl.model.Odl> iterator = this.optimizedDataListService.getAllOdls().iterator();
		while (iterator.hasNext()) {
		
			list.add(this.optimizedDataListService.getDtoMapper().mapEntityToDto(iterator.next()));
		}
		
		OdlList dto = new OdlList();
		dto.setOdls(list);
		return dto;
	}

	
	@ApiOperation(value = GET_ODL_BY_NAME_URI, notes = "The Optimized Data List by odlName")
	@GetMapping(GET_ODL_BY_NAME_URI)
	public @ResponseBody com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl getOdlByName(
		@ApiParam("The unique name of the ODL") 
		@RequestParam 
		String odlName) 
	throws 
		EntityDoesNotExistException, 
		ValidationException {
		
		return this.optimizedDataListService.getDtoMapper().mapEntityToDto(this.optimizedDataListService.getOdlByName(odlName));
	}
	
	
	@ApiOperation(value = GET_ODL_BY_PROGRAM_CODE_AND_MODEL_YEAR_URI, notes = "The Optimized Data List by program code and model year")
	@GetMapping(GET_ODL_BY_PROGRAM_CODE_AND_MODEL_YEAR_URI)
	public @ResponseBody com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl getOdlByProgramCodeAndModelYear(
		@ApiParam("The program code for the parent program") 
		@RequestParam 
        String programCode,
        
		@ApiParam("The model year for the parent program") 
		@RequestParam 
        Integer modelYear)
	throws 
		EntityDoesNotExistException, 
		ValidationException {
		
		return this.optimizedDataListService.getDtoMapper().mapEntityToDto(this.optimizedDataListService.getOdlByProgramCodeAndModelYear(programCode, modelYear));
	}
	
	
	@ApiOperation(value = RENDER_FULL_ODL_WITH_ECG_SIGNALS_FOR_PROGRAM_URI, notes = "Renders the vehicle JSON for the ODL to have all defined modules and ECG signals")
	@GetMapping(RENDER_FULL_ODL_WITH_ECG_SIGNALS_FOR_PROGRAM_URI)
	public @ResponseBody String renderFullOdlWithEcgSignalsForProgram(
		@ApiParam("The program code for the parent program") 
		@RequestParam 
        String programCode,
        
		@ApiParam("The model year for the parent program") 
		@RequestParam 
        Integer modelYear)
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		return this.optimizedDataListService.renderFullOdlWithEcgSignalsForProgram(this.programModelYearApplicationServiceClient.getProgramModelYearByProgramCodeAndModelYear(programCode, modelYear));
	}

	
	@ApiOperation(value = RENDER_ODL_FOR_PROGRAM_URI, notes = "Renders the vehicle JSON for the ODL to only have the modules that are associated with the given custom ODL, and includes ECG signals only if include ECG signals is true")
	@GetMapping(RENDER_ODL_FOR_PROGRAM_URI)
	public @ResponseBody String renderOdlForProgram(
		@ApiParam("The program code for the parent program") 
		@RequestParam 
        String programCode,
        
		@ApiParam("The model year for the parent program") 
		@RequestParam 
        Integer modelYear,
        
		@ApiParam("The name of the custom ODL to use for rendering the vehicle JSON") 
		@RequestParam 
		String customOdlName,
		
		@ApiParam("Whether or not to include any defined ECG signals when rendering the vehicle JSON") 
		@RequestParam 
		Boolean includeEcgSignals)
	throws 
		EntityDoesNotExistException,
		ValidationException {
	
		return this.optimizedDataListService.renderOdlForProgram(this.programModelYearApplicationServiceClient.getProgramModelYearByProgramCodeAndModelYear(programCode, modelYear), customOdlName, includeEcgSignals);
	}

	@ApiOperation(value = PUBLISH_ODL_EVENT_URI, notes = "Publishes the ODL with the specified name")
	@GetMapping(PUBLISH_ODL_EVENT_URI)
	public @ResponseBody OrfinOdlEvent publishOrfinOdlEvent(
		@ApiParam("The ID of the user that is approving of the publish event") 
		@RequestParam 
		String owner,
		
		@ApiParam("The program code") 
		@RequestParam 
        String programCode,
        
		@ApiParam("The model year") 
		@RequestParam 
        Integer modelYear,
        
		@ApiParam("The name of the ODL to use") 
		@RequestParam
		String odlName)
	throws 
		ValidationException {

		return this.optimizedDataListService.publishOrfinOdlEvent(owner, programCode, modelYear, odlName);
	}	
}
