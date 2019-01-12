/*
 * 
 * 
 * 
 * 
 * 
 * 
 */
package com.djt.cvpp.ota.orfin.client.delivery;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.djt.cvpp.ota.common.exception.EntityAlreadyExistsException;
import com.djt.cvpp.ota.common.exception.EntityDoesNotExistException;
import com.djt.cvpp.ota.common.exception.ValidationException;
import com.djt.cvpp.ota.orfin.api.delivery.DeliveryRuleSetApplicationService;
import com.djt.cvpp.ota.orfin.api.delivery.DeliveryRuleSetList;
import com.djt.cvpp.ota.orfin.client.AbstractOrfinServiceClient;
import com.djt.cvpp.ota.orfin.delivery.event.OrfinDeliveryRuleSetEvent;

/**
 *
 * @author tmyers1@yahoo.com (Tom Myers)
 *
 */
@Component
public class DeliveryRuleSetApplicationServiceClient extends AbstractOrfinServiceClient implements DeliveryRuleSetApplicationService {
	
	private RestTemplate restTemplate;
	
	public DeliveryRuleSetApplicationServiceClient() {
		
		this.restTemplate = super.restTemplate();
	}
	
	public com.djt.cvpp.ota.orfin.delivery.mapper.dto.DeliveryRuleSet createDeliveryRuleSet(
		String deliveryRuleSetName,
		String authorizedBy,
		String messageToConsumer,
		String consentType,
		String scheduledRolloutDate)
	throws 
		EntityAlreadyExistsException, 
		ValidationException {
		
		String uri = buildEndpointUri(DELIVERY_RULE_SET_URI, CREATE_DELIVERY_RULE_SET_URI);
		uri =  UriComponentsBuilder
			.fromHttpUrl(uri)
			.queryParam(DELIVERY_RULE_SET_NAME, deliveryRuleSetName)
			.queryParam(AUTHORIZED_BY, authorizedBy)
			.queryParam(MESSAGE_TO_CONSUMER, messageToConsumer)
			.queryParam(CONSENT_TYPE, consentType)
			.queryParam(SCHEDULED_ROLLOUT_DATE, scheduledRolloutDate)
			.toUriString();
		
		return this.restTemplate.getForObject(uri, com.djt.cvpp.ota.orfin.delivery.mapper.dto.DeliveryRuleSet.class);
	}

	public com.djt.cvpp.ota.orfin.delivery.mapper.dto.DeliveryRuleSet createDeliveryRule(
		String deliveryRuleSetName,
		Boolean allowable,
		Integer precedenceLevel,
		String deliveryAudience,
		String deliveryMethod,
		String connectionType)
	throws 
		EntityAlreadyExistsException,
		EntityDoesNotExistException,
		ValidationException {

		String uri = buildEndpointUri(DELIVERY_RULE_SET_URI, CREATE_DELIVERY_RULE_URI);
		uri =  UriComponentsBuilder
			.fromHttpUrl(uri)
			.queryParam(DELIVERY_RULE_SET_NAME, deliveryRuleSetName)
			.queryParam(ALLOWABLE, allowable)
			.queryParam(PRECEDENCE_LEVEL, precedenceLevel)
			.queryParam(DELIVERY_AUDIENCE, deliveryAudience)
			.queryParam(DELIVERY_METHOD, deliveryMethod)
			.queryParam(CONNECTION_TYPE, connectionType)
			.toUriString();
		
		return this.restTemplate.getForObject(uri, com.djt.cvpp.ota.orfin.delivery.mapper.dto.DeliveryRuleSet.class);
	}

	public com.djt.cvpp.ota.orfin.delivery.mapper.dto.DeliveryRuleSet createComplexCondition(
		String deliveryRuleSetName,
		String complexConditionName,
		String complexConditionValue)
	throws 
		EntityAlreadyExistsException,
		EntityDoesNotExistException,
		ValidationException {

		String uri = buildEndpointUri(DELIVERY_RULE_SET_URI, CREATE_COMPLEX_CONDITION_URI);
		uri =  UriComponentsBuilder
			.fromHttpUrl(uri)
			.queryParam(DELIVERY_RULE_SET_NAME, deliveryRuleSetName)
			.queryParam(COMPLEX_CONDITION_NAME, complexConditionName)
			.queryParam(COMPLEX_CONDITION_VALUE, complexConditionValue)
			.toUriString();
		
		return this.restTemplate.getForObject(uri, com.djt.cvpp.ota.orfin.delivery.mapper.dto.DeliveryRuleSet.class);
	}
	
	public com.djt.cvpp.ota.orfin.delivery.mapper.dto.DeliveryRuleSet associateVadrReleaseToDeliveryRuleSet(
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
		ValidationException {
		
		String uri = buildEndpointUri(DELIVERY_RULE_SET_URI, ASSOCIATE_VADR_RELEASE_TO_DELIVERY_RULE_SET_URI);
		uri =  UriComponentsBuilder
			.fromHttpUrl(uri)
			.queryParam(DELIVERY_RULE_SET_NAME, deliveryRuleSetName)
			.queryParam(DOMAIN_NAME, domainName)
			.queryParam(DOMAIN_INSTANCE_NAME, domainInstanceName)
			.queryParam(DOMAIN_INSTANCE_DESCRIPTION, domainInstanceDescription)
			.queryParam(DOMAIN_INSTANCE_VERSION, domainInstanceVersion)
			.queryParam(APP_ID, appId)
			.queryParam(APP_VERSION, appVersion)
			.queryParam(PRODUCTION_STATE, productionState)
			.queryParam(RELEASE_DATE, releaseDate)
			.toUriString();
		
		return this.restTemplate.getForObject(uri, com.djt.cvpp.ota.orfin.delivery.mapper.dto.DeliveryRuleSet.class);
	}
	
	public DeliveryRuleSetList getAllDeliveryRuleSets() {
		
		String uri = buildEndpointUri(DELIVERY_RULE_SET_URI, GET_ALL_DELIVERY_RULE_SETS_URI);
		return this.restTemplate.getForObject(uri, DeliveryRuleSetList.class);
	}
	
	public com.djt.cvpp.ota.orfin.delivery.mapper.dto.DeliveryRuleSet getDeliveryRuleSetByName(
		String deliveryRuleSetName) 
	throws 
		EntityDoesNotExistException, 
		ValidationException {
		
		String uri = buildEndpointUri(DELIVERY_RULE_SET_URI, GET_DELIVERY_RULE_SET_BY_NAME_URI);

		uri =  UriComponentsBuilder
			.fromHttpUrl(uri)
			.queryParam(DELIVERY_RULE_SET_NAME, deliveryRuleSetName)
			.toUriString();
		
		return this.restTemplate.getForObject(uri, com.djt.cvpp.ota.orfin.delivery.mapper.dto.DeliveryRuleSet.class);
	}

	public OrfinDeliveryRuleSetEvent publishOrfinDeliveryRuleSetEvent(
		String owner, 
		String updateAction,
		String deliveryRuleSetName,
		String nodeAcronym,
		String nodeAddress)
	throws 
		ValidationException {
		
		String uri = buildEndpointUri(DELIVERY_RULE_SET_URI, PUBLISH_DELIVERY_RULE_SET_EVENT_URI);
		uri =  UriComponentsBuilder
			.fromHttpUrl(uri)
			.queryParam(DELIVERY_RULE_SET_NAME, deliveryRuleSetName)
			.queryParam(OWNER, owner)
			.queryParam(NODE_ACRONYM, nodeAcronym)
			.queryParam(NODE_ADDRESS, nodeAddress)
			.toUriString();
		
		return this.restTemplate.getForObject(uri, OrfinDeliveryRuleSetEvent.class);
	}
}
