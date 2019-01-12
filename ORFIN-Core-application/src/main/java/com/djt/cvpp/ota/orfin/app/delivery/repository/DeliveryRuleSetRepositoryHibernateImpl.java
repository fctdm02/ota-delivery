/*
 * 
 * 
 * 
 * 
 * 
 * 
 */
package com.djt.cvpp.ota.orfin.app.delivery.repository;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.djt.cvpp.ota.common.exception.EntityAlreadyExistsException;
import com.djt.cvpp.ota.common.exception.EntityDoesNotExistException;
import com.djt.cvpp.ota.common.exception.ValidationException;
import com.djt.cvpp.ota.common.model.AbstractEntity;
import com.djt.cvpp.ota.orfin.delivery.model.DeliveryRuleSet;
import com.djt.cvpp.ota.orfin.delivery.model.enums.ConsentType;
import com.djt.cvpp.ota.orfin.delivery.repository.DeliveryRuleSetRepository;

/**
 *
 * @author tmyers1@yahoo.com (Tom Myers)
 *
 */
@Repository
public class DeliveryRuleSetRepositoryHibernateImpl implements DeliveryRuleSetRepository {
	
	@PersistenceContext
	private EntityManager entityManager;	
	
	public DeliveryRuleSet createDeliveryRuleSet(
		String deliveryRuleSetName,
		String authorizedBy,
		String messageToConsumer,
		ConsentType consentType,
		Timestamp scheduledRolloutDate)
	throws 
		EntityAlreadyExistsException,
		ValidationException {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public List<DeliveryRuleSet> getAllDeliveryRuleSets() {
		
		return this.entityManager.createQuery("select r from rollout r", DeliveryRuleSet.class).getResultList();
	}
	
	public DeliveryRuleSet getDeliveryRuleSetByName(
        String deliveryRuleSetName)
	throws 
		EntityDoesNotExistException {

		throw new RuntimeException("Not implemented yet.");
	}
	
	public DeliveryRuleSet getDefaultDeliveryRuleSet()
	throws 
		EntityDoesNotExistException,
		ValidationException {

		throw new RuntimeException("Not implemented yet.");
	}
	
	public DeliveryRuleSet renameDeliveryRuleSet(
		String oldDeliveryRuleSetName, 
		String newDeliveryRuleSetName)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException {
		
		throw new RuntimeException("Not implemented yet.");
	}
		
	public AbstractEntity getEntityByNaturalIdentityNullIfNotFound(String naturalIdentity) {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public AbstractEntity updateEntity(
		AbstractEntity entity)
	throws 
		ValidationException {

		throw new RuntimeException("Not implemented yet.");
	}
	
	public AbstractEntity deleteEntity(AbstractEntity entity) {

		throw new RuntimeException("Not implemented yet.");
	}
	
	public void reset() {
		throw new UnsupportedOperationException("Not supported");
	}
}
