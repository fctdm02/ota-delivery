/*
 * 
 * 
 * 
 * 
 * 
 * 
 */
package com.djt.cvpp.ota.orfin.app.delivery.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.djt.cvpp.ota.common.exception.EntityAlreadyExistsException;
import com.djt.cvpp.ota.common.exception.EntityDoesNotExistException;
import com.djt.cvpp.ota.common.exception.FenixRuntimeException;
import com.djt.cvpp.ota.common.exception.ValidationException;
import com.djt.cvpp.ota.orfin.api.delivery.DeliveryRuleSetApplicationService;
import com.djt.cvpp.ota.orfin.api.delivery.DeliveryRuleSetList;
import com.djt.cvpp.ota.orfin.delivery.event.OrfinDeliveryRuleSetEvent;
import com.djt.cvpp.ota.orfin.delivery.event.OrfinDeliveryRuleSetEventPublisher;
import com.djt.cvpp.ota.orfin.delivery.event.impl.MockOrfinDeliveryRuleSetEventPublisher;
import com.djt.cvpp.ota.orfin.delivery.model.enums.ConnectionType;
import com.djt.cvpp.ota.orfin.delivery.model.enums.ConsentType;
import com.djt.cvpp.ota.orfin.delivery.model.enums.DeliveryAudience;
import com.djt.cvpp.ota.orfin.delivery.model.enums.DeliveryMethod;
import com.djt.cvpp.ota.orfin.delivery.repository.DeliveryRuleSetRepository;
import com.djt.cvpp.ota.orfin.delivery.repository.impl.MockDeliveryRuleSetRepositoryImpl;
import com.djt.cvpp.ota.orfin.delivery.service.DeliveryRuleSetService;
import com.djt.cvpp.ota.orfin.delivery.service.impl.DeliveryRuleSetServiceImpl;
import com.djt.cvpp.ota.orfin.vadrevent.event.impl.MockOrfinVadrReleaseEventPublisher;
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
@RequestMapping(path = DeliveryRuleSetApplicationService.DELIVERY_RULE_SET_URI)
public class DeliveryRuleSetController implements DeliveryRuleSetApplicationService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryRuleSetController.class);
	
	
	private OrfinDeliveryRuleSetEventPublisher orfinDeliveryRuleSetEventPublisher;
	private DeliveryRuleSetRepository deliveryRuleSetRepository;
	private DeliveryRuleSetService deliveryRuleSetService;
	private OrfinVadrReleaseEventRepository orfinVadrReleaseEventRepository;
	private OrfinVadrReleaseEventService orfinVadrReleaseEventService;
	
	public DeliveryRuleSetController() {
		
		this.orfinVadrReleaseEventRepository = new MockOrfinVadrReleaseEventRepositoryImpl();
		this.orfinVadrReleaseEventService = new OrfinVadrReleaseEventServiceImpl(this.orfinVadrReleaseEventRepository);
		
		this.orfinDeliveryRuleSetEventPublisher = MockOrfinDeliveryRuleSetEventPublisher.getInstance();
		this.deliveryRuleSetRepository = MockDeliveryRuleSetRepositoryImpl.getInstance();
		this.deliveryRuleSetRepository.reset();
		this.deliveryRuleSetService = new DeliveryRuleSetServiceImpl(
			this.deliveryRuleSetRepository,
			this.orfinVadrReleaseEventService,
			this.orfinDeliveryRuleSetEventPublisher);
		
		
		MockOrfinVadrReleaseEventPublisher.getInstance().subscribe(this.deliveryRuleSetService);
	}

	
	// ************************************************************************************************************************************
	// CONTROLLER METHODS MAP TO SERVICE METHODS ONE-TO-ONE.  ALL SWAGGER DOC INFO COMES FROM JAVADOC IN SERVICE INTERFACE.
	// ************************************************************************************************************************************
	
	
	@ApiOperation(value = CREATE_DELIVERY_RULE_SET_URI, notes = "Creates a DeliveryRuleSet with the specified values")
	@GetMapping(CREATE_DELIVERY_RULE_SET_URI)
	public @ResponseBody com.djt.cvpp.ota.orfin.delivery.mapper.dto.DeliveryRuleSet createDeliveryRuleSet(
		@ApiParam(DELIVERY_RULE_SET_NAME)
		@RequestParam
		String deliveryRuleSetName,

		@ApiParam("authorizedBy")
		@RequestParam
		String authorizedBy,
		
		@ApiParam("messageToConsumer")
		@RequestParam
		String messageToConsumer,
		
		@ApiParam("consentType")
		@RequestParam
		String consentType,
		
		@ApiParam("scheduledRolloutDate")
		@RequestParam
		String scheduledRolloutDate)
	throws 
		EntityAlreadyExistsException, 
		ValidationException {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.getDefault());
		Timestamp timestamp = null;
		try {
			timestamp = new Timestamp(dateFormat.parse(scheduledRolloutDate).getTime()); 
		} catch (ParseException pe) {
			throw new FenixRuntimeException("Could not parse scheduled rollout date, error: " + pe.getMessage(), pe);
		}
		
		return this.deliveryRuleSetService.getDtoMapper().mapEntityToDto(this.deliveryRuleSetService.createDeliveryRuleSet(
			deliveryRuleSetName, 
			authorizedBy, 
			messageToConsumer, 
			ConsentType.valueOf(consentType), 
			timestamp));
	}

	@ApiOperation(value = CREATE_DELIVERY_RULE_URI, notes = "Creates a DeliveryRule with the specified values")
	@GetMapping(CREATE_DELIVERY_RULE_URI)
	public @ResponseBody com.djt.cvpp.ota.orfin.delivery.mapper.dto.DeliveryRuleSet createDeliveryRule(
		@ApiParam(DELIVERY_RULE_SET_NAME)
		@RequestParam
		String deliveryRuleSetName,

		@ApiParam(ALLOWABLE)
		@RequestParam
		Boolean allowable,
		
		@ApiParam(PRECEDENCE_LEVEL)
		@RequestParam
		Integer precedenceLevel,
		
		@ApiParam(DELIVERY_AUDIENCE)
		@RequestParam
		String deliveryAudience,
		
		@ApiParam(DELIVERY_METHOD)
		@RequestParam
		String deliveryMethod,
		
		@ApiParam(CONNECTION_TYPE)
		@RequestParam
		String connectionType)
	throws 
		EntityAlreadyExistsException,
		EntityDoesNotExistException,
		ValidationException {
		
		return this.deliveryRuleSetService.getDtoMapper().mapEntityToDto(this.deliveryRuleSetService.createDeliveryRule(
			deliveryRuleSetName, 
			allowable, 
			precedenceLevel,
			DeliveryAudience.valueOf(deliveryAudience), 
			DeliveryMethod.valueOf(deliveryMethod), 
			ConnectionType.valueOf(connectionType)));
	}

	
	@ApiOperation(value = CREATE_COMPLEX_CONDITION_URI, notes = "Creates a ComplexCondition with the specified values")
	@GetMapping(CREATE_COMPLEX_CONDITION_URI)
	public @ResponseBody com.djt.cvpp.ota.orfin.delivery.mapper.dto.DeliveryRuleSet createComplexCondition(
		@ApiParam(DELIVERY_RULE_SET_NAME)
		@RequestParam
		String deliveryRuleSetName,

		@ApiParam(COMPLEX_CONDITION_NAME)
		@RequestParam
		String complexConditionName,
		
		@ApiParam(COMPLEX_CONDITION_VALUE)
		@RequestParam
		String complexConditionValue)
	throws 
		EntityAlreadyExistsException,
		EntityDoesNotExistException,
		ValidationException {
		
		return this.deliveryRuleSetService.getDtoMapper().mapEntityToDto(this.deliveryRuleSetService.createComplexCondition(deliveryRuleSetName, complexConditionName, complexConditionValue));
	}

	
	@ApiOperation(value = ASSOCIATE_VADR_RELEASE_TO_DELIVERY_RULE_SET_URI, notes = "Associates a VADR Release to a DeliveryRuleSet")
	@GetMapping(ASSOCIATE_VADR_RELEASE_TO_DELIVERY_RULE_SET_URI)
	public @ResponseBody com.djt.cvpp.ota.orfin.delivery.mapper.dto.DeliveryRuleSet associateVadrReleaseToDeliveryRuleSet(
		@ApiParam(DELIVERY_RULE_SET_NAME)
		@RequestParam
		String deliveryRuleSetName,

		@ApiParam(DOMAIN_NAME)
		@RequestParam
		String domainName,
		
		@ApiParam(DOMAIN_INSTANCE_NAME)
		@RequestParam
		String domainInstanceName,
		
		@ApiParam(DOMAIN_INSTANCE_DESCRIPTION)
		@RequestParam
		String domainInstanceDescription,
		
		@ApiParam(DOMAIN_INSTANCE_VERSION)
		@RequestParam
		String domainInstanceVersion,
		
		@ApiParam(APP_ID)
		@RequestParam
		String appId,
		
		@ApiParam(APP_VERSION)
		@RequestParam
		String appVersion,
		
		@ApiParam(PRODUCTION_STATE)
		@RequestParam
		String productionState,
		
		@ApiParam(RELEASE_DATE)
		@RequestParam
		String releaseDate)
	throws 
		EntityAlreadyExistsException,
		EntityDoesNotExistException,
		ValidationException {
		
		return this.deliveryRuleSetService.getDtoMapper().mapEntityToDto(this.deliveryRuleSetService.associateVadrReleaseToDeliveryRuleSet(deliveryRuleSetName, domainName, domainInstanceName, domainInstanceDescription, domainInstanceVersion, appId, appVersion, productionState, releaseDate));
	}
	
		
	@ApiOperation(value = GET_ALL_DELIVERY_RULE_SETS_URI, notes = "All delivery rule sets that have been created")
	@GetMapping(GET_ALL_DELIVERY_RULE_SETS_URI)
	public @ResponseBody DeliveryRuleSetList getAllDeliveryRuleSets() {
		
		List<com.djt.cvpp.ota.orfin.delivery.mapper.dto.DeliveryRuleSet> list = new ArrayList<>();
		
		Iterator<com.djt.cvpp.ota.orfin.delivery.model.DeliveryRuleSet> iterator = this.deliveryRuleSetService.getAllDeliveryRuleSets().iterator();
		while (iterator.hasNext()) {
		
			list.add(this.deliveryRuleSetService.getDtoMapper().mapEntityToDto(iterator.next()));
		}
		
		DeliveryRuleSetList dto = new DeliveryRuleSetList();
		dto.setDeliveryRuleSets(list);
		return dto;
	}

	
	@ApiOperation(value = GET_DELIVERY_RULE_SET_BY_NAME_URI, notes = "Retrieves the delivery rule set by deliveryRuleSetName")
	@GetMapping(GET_DELIVERY_RULE_SET_BY_NAME_URI)
	public @ResponseBody com.djt.cvpp.ota.orfin.delivery.mapper.dto.DeliveryRuleSet getDeliveryRuleSetByName(
		@ApiParam(DELIVERY_RULE_SET_NAME)
		@RequestParam
		String deliveryRuleSetName) 
	throws 
		EntityDoesNotExistException, 
		ValidationException {
		
		LOGGER.info("deliveryRuleSetName: " + deliveryRuleSetName);
		return this.deliveryRuleSetService.getDtoMapper().mapEntityToDto(this.deliveryRuleSetService.getDeliveryRuleSetByName(deliveryRuleSetName));
	}

	
	@ApiOperation(value = PUBLISH_DELIVERY_RULE_SET_EVENT_URI, notes = "Publishes the delivery rule set")
	@GetMapping(PUBLISH_DELIVERY_RULE_SET_EVENT_URI)
	public @ResponseBody OrfinDeliveryRuleSetEvent publishOrfinDeliveryRuleSetEvent(
		@ApiParam("The ID of the user that is approving of the publish event") 
		@RequestParam 
		String owner,
		
		@ApiParam("The type of rollout that will be created for this event: SOFTWARE_UPDATE, APPLICATION_UPDATE, ADD_APPLICATION, REMOVE_APPLICATION_UPDATE, DIRECT_CONFIGURATION_ONLY_UPDATE, SOFTWARE_WITH_DIRECT_CONFIGURATION_UPDATE, SOFTWARE_SUSPEND") 
		@RequestParam 
		String updateAction,
		
		@ApiParam("The unique name of the delivery rule set to use.  NOTE: It is expected that VADR Releases are associated with the delivery rule set beforehand") 
		@RequestParam 
		String deliveryRuleSetName,
		
		@ApiParam("The node address, used for direct configuration only") 
		@RequestParam 
		String nodeAcronym,
		
		@ApiParam("The node address, used for direct configuration only") 
		@RequestParam 
		String nodeAddress)
	throws 
		ValidationException {
		
		return this.deliveryRuleSetService.publishOrfinDeliveryRuleSetEvent(owner, updateAction, deliveryRuleSetName, nodeAcronym, nodeAddress);
	}
}
