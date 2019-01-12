/*
 * 
 * 
 * 
 * 
 * 
 * 
 */
package com.djt.cvpp.ota.orfin.app.program.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.djt.cvpp.ota.common.exception.EntityAlreadyExistsException;
import com.djt.cvpp.ota.common.exception.EntityDoesNotExistException;
import com.djt.cvpp.ota.common.exception.ValidationException;
import com.djt.cvpp.ota.common.model.AbstractEntity;
import com.djt.cvpp.ota.orfin.program.model.ProgramModelYear;
import com.djt.cvpp.ota.orfin.program.repository.ProgramModelYearRepository;

/**
 *
 * @author tmyers1@yahoo.com (Tom Myers)
 *
 */
@Repository
public class ProgramModelYearRepositoryHibernateImpl implements ProgramModelYearRepository {
	
	@PersistenceContext
	private EntityManager entityManager;	
	
	public ProgramModelYear createProgramModelYear(
		String programCode,
		Integer modelYear)
	throws 
		EntityAlreadyExistsException,
		ValidationException {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public List<ProgramModelYear> getAllProgramModelYears() {
		
		throw new RuntimeException("Not implemented yet.");
	}
	
	public ProgramModelYear getProgramModelYearByNaturalIdentity(
		String programCode,
		Integer modelYear)
	throws 
		EntityDoesNotExistException,
		ValidationException {
		
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
