/*
 * 
 * 
 * 
 * 
 * 
 * 
 */
package com.djt.cvpp.ota.orfin.api.delivery;

import com.djt.cvpp.ota.common.exception.EntityAlreadyExistsException;
import com.djt.cvpp.ota.common.exception.EntityDoesNotExistException;
import com.djt.cvpp.ota.common.exception.ValidationException;
import com.djt.cvpp.ota.orfin.delivery.event.OrfinDeliveryRuleSetEvent;

/**
 *
 * @author tmyers1@yahoo.com (Tom Myers)
 *
 */
public interface DeliveryRuleSetApplicationService {
	
	String DELIVERY_RULE_SET_URI = "/api/v1/delivery";
	
	String DELIVERY_RULE_SET_NAME = "deliveryRuleSetName";
	String AUTHORIZED_BY = "authorizedBy";
	String MESSAGE_TO_CONSUMER = "messageToConsumer";
	String CONSENT_TYPE = "consentType";
	String SCHEDULED_ROLLOUT_DATE = "scheduledRolloutDate";
	String ALLOWABLE = "allowable";
	String PRECEDENCE_LEVEL = "precedenceLevel";
	String DELIVERY_AUDIENCE = "deliveryAudience";
	String DELIVERY_METHOD = "deliveryMethod";
	String CONNECTION_TYPE = "connectionType";
	String COMPLEX_CONDITION_NAME = "complexConditionName";
	String COMPLEX_CONDITION_VALUE = "complexConditionValue";
	String DOMAIN_NAME = "domainName";
	String DOMAIN_INSTANCE_NAME = "domainInstanceName";
	String DOMAIN_INSTANCE_DESCRIPTION = "domainInstanceDescription";
	String DOMAIN_INSTANCE_VERSION = "domainInstanceVersion";
	String APP_ID = "appId";
	String APP_VERSION = "appVersion";
	String PRODUCTION_STATE = "productionState";
	String RELEASE_DATE = "releaseDate";
	String OWNER = "owner";
	String UPDATE_ACTION = "updateAction";
	String NODE_ACRONYM = "nodeAcronym";
	String NODE_ADDRESS = "nodeAddress";
	String SOFTWARE_UPDATE = "SOFTWARE_UPDATE";
	String APPLICATION_UPDATE = "APPLICATION_UPDATE";
	String ADD_APPLICATION = "ADD_APPLICATION";
	String REMOVE_APPLICATION_UPDATE = "REMOVE_APPLICATION_UPDATE";
	String DIRECT_CONFIGURATION_ONLY_UPDATE = "DIRECT_CONFIGURATION_ONLY_UPDATE";
	String SOFTWARE_WITH_DIRECT_CONFIGURATION_UPDATE = "SOFTWARE_WITH_DIRECT_CONFIGURATION_UPDATE";
	String SOFTWARE_SUSPEND = "SOFTWARE_SUSPEND";
	

	/**
	 * 
	 * @param deliveryRuleSetName
	 * @param authorizedBy
	 * @param messageToConsumer
	 * @param consentType
	 * @param scheduledRolloutDate
	 * @return
	 * @throws EntityAlreadyExistsException
	 * @throws ValidationException
	 */
	String CREATE_DELIVERY_RULE_SET_URI = "/createDeliveryRuleSet";
	com.djt.cvpp.ota.orfin.delivery.mapper.dto.DeliveryRuleSet createDeliveryRuleSet(
		String deliveryRuleSetName,
		String authorizedBy,
		String messageToConsumer,
		String consentType,
		String scheduledRolloutDate)
	throws 
		EntityAlreadyExistsException, 
		ValidationException;
	
	
	/**
	 * 
	 * @param deliveryRuleSetName
	 * @param allowable
	 * @param precedenceLevel
	 * @param deliveryAudience
	 * @param deliveryMethod
	 * @param connectionType
	 * @return
	 * @throws EntityAlreadyExistsException
	 * @throws EntityDoesNotExistException
	 * @throws ValidationException
	 */
	String CREATE_DELIVERY_RULE_URI = "/createDeliveryRule";
	com.djt.cvpp.ota.orfin.delivery.mapper.dto.DeliveryRuleSet createDeliveryRule(
		String deliveryRuleSetName,
		Boolean allowable,
		Integer precedenceLevel,
		String deliveryAudience,
		String deliveryMethod,
		String connectionType)
	throws 
		EntityAlreadyExistsException,
		EntityDoesNotExistException,
		ValidationException;

	
	/**
	 * 
	 * @param deliveryRuleSetName
	 * @param complexConditionName
	 * @param complexConditionValue
	 * @return
	 * @throws EntityAlreadyExistsException
	 * @throws EntityDoesNotExistException
	 * @throws ValidationException
	 */
	String CREATE_COMPLEX_CONDITION_URI = "/createComplexCondition";
	com.djt.cvpp.ota.orfin.delivery.mapper.dto.DeliveryRuleSet createComplexCondition(
		String deliveryRuleSetName,
		String complexConditionName,
		String complexConditionValue)
	throws 
		EntityAlreadyExistsException,
		EntityDoesNotExistException,
		ValidationException;
	
	
	/**
	 * 
	 * @param deliveryRuleSetName
	 * @param domainName
	 * @param domainInstanceName
	 * @param domainInstanceDescription
	 * @param domainInstanceVersion
	 * @param appId
	 * @param appVersion
	 * @param productionState
	 * @param releaseDate in MM-DD-YYYY format
	 * @return
	 * @throws EntityAlreadyExistsException
	 * @throws EntityDoesNotExistException
	 * @throws ValidationException
	 */
	String ASSOCIATE_VADR_RELEASE_TO_DELIVERY_RULE_SET_URI = "/associateVadrReleaseToDeliveryRuleSet";
	com.djt.cvpp.ota.orfin.delivery.mapper.dto.DeliveryRuleSet associateVadrReleaseToDeliveryRuleSet(
		String deliveryRuleSetName,
		String domainName,
		String domainInstanceName,
		String domainInstanceDescription,
		String domainInstanceVersion,
		String appId,
		String appVersion,
		String productionState,
		String releaseDate)
	throws 
		EntityAlreadyExistsException,
		EntityDoesNotExistException,
		ValidationException;
	
	
	/**
	 * 
	 * @return
	 */
	String GET_ALL_DELIVERY_RULE_SETS_URI = "/getAllDeliveryRuleSets";
	DeliveryRuleSetList getAllDeliveryRuleSets();

	
	/**
	 * 
	 * @param deliveryRuleSetName
	 * @return
	 * @throws EntityDoesNotExistException
	 * @throws ValidationException
	 */
	String GET_DELIVERY_RULE_SET_BY_NAME_URI = "/getDeliveryRuleSetByName";
	com.djt.cvpp.ota.orfin.delivery.mapper.dto.DeliveryRuleSet getDeliveryRuleSetByName(
		String deliveryRuleSetName) 
	throws 
		EntityDoesNotExistException, 
		ValidationException;

	
	/**
	 * 
	 * @param owner
	 * @param updateAction
	 * @param deliveryRuleSetName
	 * @param nodeAcronym
	 * @param nodeAddress
	 * @return
	 * @throws ValidationException
	 */
	String PUBLISH_DELIVERY_RULE_SET_EVENT_URI = "/publishOrfinDeliveryRuleSetEvent";
	OrfinDeliveryRuleSetEvent publishOrfinDeliveryRuleSetEvent(
		String owner, 
		String updateAction,
		String deliveryRuleSetName,
		String nodeAcronym,
		String nodeAddress)
	throws 
		ValidationException;
}
