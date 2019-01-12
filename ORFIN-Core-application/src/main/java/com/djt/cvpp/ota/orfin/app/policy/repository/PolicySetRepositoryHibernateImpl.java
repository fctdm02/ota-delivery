/*
 * 
 * 
 * 
 * 
 * 
 * 
 */
package com.djt.cvpp.ota.orfin.app.policy.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.djt.cvpp.ota.common.exception.EntityAlreadyExistsException;
import com.djt.cvpp.ota.common.exception.EntityDoesNotExistException;
import com.djt.cvpp.ota.common.exception.ValidationException;
import com.djt.cvpp.ota.common.model.AbstractEntity;
import com.djt.cvpp.ota.orfin.policy.model.PolicySet;
import com.djt.cvpp.ota.orfin.policy.model.region.Region;
import com.djt.cvpp.ota.orfin.policy.repository.PolicySetRepository;

/**
 *
 * @author tmyers1@yahoo.com (Tom Myers)
 *
 */
@Repository
public class PolicySetRepositoryHibernateImpl implements PolicySetRepository {
	
	@PersistenceContext
	private EntityManager entityManager;	
	
	public PolicySet createPolicySet(
		String policySetName)
	throws 
		EntityAlreadyExistsException,
		ValidationException {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public List<PolicySet> getAllPolicySets() {
		
		throw new RuntimeException("Not implemented yet.");
	}
		
	public PolicySet getPolicySetByName(
        String policySetName)
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public PolicySet renamePolicySet(
		String oldPolicySetName, 
		String newPolicySetName)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException,
		ValidationException {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public Region createRegion(
		String regionCode,
		String countryName)
	throws 
		EntityAlreadyExistsException,
		ValidationException {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public List<Region> getAllRegions() {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public Region getRegionByCode(
        String regionCode)
	throws 
		EntityDoesNotExistException {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public Region renameRegion(
		String oldRegionCode, 
		String newRegionCode)
	throws 
		EntityDoesNotExistException,
		EntityAlreadyExistsException {
		
		throw new RuntimeException("Not implemented yet.");
	}

	public PolicySet updatePolicySet(PolicySet entity) throws ValidationException {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public PolicySet deletePolicySet(PolicySet entity) {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public Region updateRegion(Region entity) throws ValidationException {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public Region deleteRegion(Region entity) {
		
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
