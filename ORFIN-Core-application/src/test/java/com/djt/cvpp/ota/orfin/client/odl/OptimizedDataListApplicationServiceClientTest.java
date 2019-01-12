/*
 * 
 * 
 * 
 * 
 * 
 * 
 */
package com.djt.cvpp.ota.orfin.client.odl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.djt.cvpp.ota.common.exception.EntityAlreadyExistsException;
import com.djt.cvpp.ota.common.exception.EntityDoesNotExistException;
import com.djt.cvpp.ota.common.exception.ValidationException;
import com.djt.cvpp.ota.orfin.api.odl.OdlList;
import com.djt.cvpp.ota.orfin.client.BaseOrfinApplicationServiceClientTest;
import com.djt.cvpp.ota.orfin.client.program.ProgramModelYearApplicationServiceClient;
import com.djt.cvpp.ota.orfin.odl.repository.impl.MockOptimizedDataListRepositoryImpl;
import com.djt.cvpp.ota.orfin.program.repository.impl.MockProgramModelYearRepositoryImpl;

/**
 *
 * @author tmyers1@yahoo.com (Tom Myers)
 *
 */
public class OptimizedDataListApplicationServiceClientTest extends BaseOrfinApplicationServiceClientTest {

	@Autowired
	private ProgramModelYearApplicationServiceClient programModelYearApplicationServiceClient;
	
	@Autowired
	private OptimizedDataListApplicationServiceClient optimizedDataListApplicationServiceClient;
	
	private OptimizedDataListApplicationServiceClientTestHelper optimizedDataListApplicationServiceClientTestHelper = new OptimizedDataListApplicationServiceClientTestHelper();
	
	@Before
	public void before() {
		
		MockOptimizedDataListRepositoryImpl.getInstance().reset();
		MockProgramModelYearRepositoryImpl.getInstance().reset();
	}
	
	@Test
	public void createOdl() throws EntityAlreadyExistsException, ValidationException {

		// STEP 1: ARRANGE
		String odlName = "test_odl_name";
		
		
		// STEP 2: ACT
		com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl odl = optimizedDataListApplicationServiceClient.createOdl(odlName);
		
		
		// STEP 3: ASSERT
		Assert.assertNotNull("odl is null", odl);
		Assert.assertEquals("odl is incorrect", odlName, odl.getOdlName());
	}
	
	@Test
	public void getAllOdls() throws EntityAlreadyExistsException, ValidationException {
		
		// STEP 1: ARRANGE
		String odlName = "test_odl_name";
		optimizedDataListApplicationServiceClient.createOdl(odlName);
		
		
		// STEP 2: ACT
		OdlList odlList = optimizedDataListApplicationServiceClient.getAllOdls();
		
		
		// STEP 3: ASSERT
		Assert.assertNotNull("odlList is null", odlList);
		Assert.assertEquals("odlList size is incorrect", "1", Integer.toString(odlList.getOdls().size()));
		com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl odl = odlList.getOdls().get(0);
		Assert.assertEquals("odlName is incorrect", odlName, odl.getOdlName());
	}

	@Test
	public void getOptimizedDataListByName() throws EntityDoesNotExistException, ValidationException, EntityAlreadyExistsException {

		// STEP 1: ARRANGE
		String odlName = "test_odl_name";
		optimizedDataListApplicationServiceClient.createOdl(odlName);
		
		
		// STEP 2: ACT
		com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl odl = optimizedDataListApplicationServiceClient.getOdlByName(odlName);
		
		
		// STEP 3: ASSERT
		Assert.assertNotNull("odl is null", odl);
		Assert.assertEquals("odlName is incorrect", odlName, odl.getOdlName());
	}
	
	@Test
	public void getOdlByProgramCodeAndModelYear() throws EntityDoesNotExistException, ValidationException, EntityAlreadyExistsException {

		// STEP 1: ARRANGE
		String programCode = "CD391N";
		Integer modelYear = Integer.valueOf(2018);
		this.programModelYearApplicationServiceClient.createProgramModelYear(programCode, modelYear);
		 
		String odlName = "CD391N_2018_ODL_Full";
		this.optimizedDataListApplicationServiceClient.createOdl(odlName);
		
		this.programModelYearApplicationServiceClient.associateOdlToProgramModelYear(odlName, programCode, modelYear);
		

		
		// STEP 2: ACT
		com.djt.cvpp.ota.orfin.odl.mapper.dto.Odl odl = optimizedDataListApplicationServiceClient.getOdlByProgramCodeAndModelYear(programCode, modelYear);
		
		
		
		// STEP 3: ASSERT
		Assert.assertNotNull("odl is null", odl);
		Assert.assertEquals("odlName is incorrect", odlName, odl.getOdlName());
	}

	@Test
	public void renderFullOdlWithEcgSignalsForProgram() throws EntityDoesNotExistException, ValidationException, EntityAlreadyExistsException {

		// STEP 1: ARRANGE
		String programCode = "CD391N";
		Integer modelYear = Integer.valueOf(2018);
		String odlName = "CD391N_2018_ODL_Full";
		String customOdlName = "SYNC_TCU_ECG";		
		this.optimizedDataListApplicationServiceClientTestHelper.buildOdlWithFiveNodesAndOneCustomOdl(
			programCode,
			modelYear,
			odlName,
			customOdlName,
			this.programModelYearApplicationServiceClient, 
			this.optimizedDataListApplicationServiceClient);
		
		
		
		// STEP 2: ACT
		String renderedOdlJson = optimizedDataListApplicationServiceClient.renderFullOdlWithEcgSignalsForProgram(programCode, modelYear);
		
		
		
		// STEP 3: ASSERT
		Assert.assertNotNull("renderedOdlJson is null", renderedOdlJson);
		System.out.println("Full ODL: [" + odlName + "] with ECG Signals - Vehicle JSON:");
		System.out.println(renderedOdlJson);
	}
	
	@Test
	public void renderOdlForProgram_customOdl_with_EcgSignals() throws EntityDoesNotExistException, ValidationException, EntityAlreadyExistsException {

		// STEP 1: ARRANGE
		String programCode = "CD391N";
		Integer modelYear = Integer.valueOf(2018);
		String odlName = "CD391N_2018_ODL_Full";
		String customOdlName = "SYNC_TCU_ECG";
		this.optimizedDataListApplicationServiceClientTestHelper.buildOdlWithFiveNodesAndOneCustomOdl(
			programCode,
			modelYear,
			odlName,
			customOdlName,
			this.programModelYearApplicationServiceClient, 
			this.optimizedDataListApplicationServiceClient);
		Boolean includeEcgSignals = Boolean.TRUE;
		

		
		// STEP 2: ACT
		String renderedOdlJson = optimizedDataListApplicationServiceClient.renderOdlForProgram(programCode, modelYear, customOdlName, includeEcgSignals);
		
		
		
		// STEP 3: ASSERT
		Assert.assertNotNull("renderedOdlJson is null", renderedOdlJson);
		System.out.println("Custom ODL: [" + customOdlName + "] with ECG Signals - Vehicle JSON:");
		System.out.println(renderedOdlJson);
	}
	
	@Test
	public void renderOdlForProgram_customOdl_without_EcgSignals() throws EntityDoesNotExistException, ValidationException, EntityAlreadyExistsException {

		// STEP 1: ARRANGE
		String programCode = "CD391N";
		Integer modelYear = Integer.valueOf(2018);
		String odlName = "CD391N_2018_ODL_Full";
		String customOdlName = "SYNC_TCU_ECG";
		this.optimizedDataListApplicationServiceClientTestHelper.buildOdlWithFiveNodesAndOneCustomOdl(
			programCode,
			modelYear,
			odlName,
			customOdlName,
			this.programModelYearApplicationServiceClient, 
			this.optimizedDataListApplicationServiceClient);
		Boolean includeEcgSignals = Boolean.FALSE;
		
		
		
		// STEP 2: ACT
		String renderedOdlJson = optimizedDataListApplicationServiceClient.renderOdlForProgram(programCode, modelYear, customOdlName, includeEcgSignals);
		
		
		
		// STEP 3: ASSERT
		Assert.assertNotNull("renderedOdlJson is null", renderedOdlJson);
		System.out.println("Custom ODL: [" + customOdlName + "] *without* ECG Signals - Vehicle JSON:");
		System.out.println(renderedOdlJson);
	}
}
