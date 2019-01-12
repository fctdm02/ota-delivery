/*
 * 
 * 
 * 
 * 
 * 
 * 
 */
package com.djt.cvpp.ota.orfin.app.odl.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.djt.cvpp.ota.common.exception.EntityAlreadyExistsException;
import com.djt.cvpp.ota.common.exception.EntityDoesNotExistException;
import com.djt.cvpp.ota.common.exception.ValidationException;
import com.djt.cvpp.ota.common.model.AbstractEntity;
import com.djt.cvpp.ota.orfin.odl.model.Odl;
import com.djt.cvpp.ota.orfin.odl.repository.OptimizedDataListRepository;

/**
 *
 * @author tmyers1@yahoo.com (Tom Myers)
 *
 */
@Repository
public class OptimizedDataListRepositoryHibernateImpl implements OptimizedDataListRepository {
	
	@PersistenceContext
	private EntityManager entityManager;	
	
	public Odl createOdl(
		String odlName)
	throws 
		EntityAlreadyExistsException,
		ValidationException {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public List<Odl> getAllOdls() {
		
		return this.entityManager.createQuery("select r from rollout r", Odl.class).getResultList();
	}
	
	public Odl getOdlByName(
        String odlName)
	throws 
		EntityDoesNotExistException {

		throw new RuntimeException("Not implemented yet.");
	}

	public Odl getOdlByProgramCodeAndModelYear(
        String programCode,
        Integer modelYear)
	throws 
		EntityDoesNotExistException {
		
		throw new RuntimeException("Not implemented yet.");
	}	
	
	public Odl renameOdl(
			String oldOdlName, 
		String newOdlName)
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
