/*
 * 
 * 
 * 
 * 
 * 
 * 
 */
package com.djt.cvpp.ota.orfin.client.program;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.djt.cvpp.ota.common.exception.EntityAlreadyExistsException;
import com.djt.cvpp.ota.common.exception.EntityDoesNotExistException;
import com.djt.cvpp.ota.common.exception.ValidationException;
import com.djt.cvpp.ota.orfin.api.program.ProgramModelYearList;
import com.djt.cvpp.ota.orfin.client.BaseOrfinApplicationServiceClientTest;
import com.djt.cvpp.ota.orfin.program.repository.impl.MockProgramModelYearRepositoryImpl;

/**
 *
 * @author tmyers1@yahoo.com (Tom Myers)
 *
 */
public class ProgramModelYearApplicationServiceClientTest extends BaseOrfinApplicationServiceClientTest {

	@Autowired
	private ProgramModelYearApplicationServiceClient programModelYearApplicationServiceClient;
	
	@Before
	public void before() {
		MockProgramModelYearRepositoryImpl.getInstance().reset();
	}
	
	@Test
	public void createProgramModelYear() throws EntityAlreadyExistsException, ValidationException {

		// STEP 1: ARRANGE
		String programCode = "ABCDE";
		Integer modelYear = Integer.valueOf(2020);
		
		
		// STEP 2: ACT
		com.djt.cvpp.ota.orfin.program.mapper.dto.ProgramModelYear programModelYear = programModelYearApplicationServiceClient.createProgramModelYear(programCode, modelYear);
		
		
		// STEP 3: ASSERT
		Assert.assertNotNull("programModelYear is null", programModelYear);
		Assert.assertEquals("programCode is incorrect", programCode, programModelYear.getParentProgram().getProgramCode());
		Assert.assertEquals("modelYear is incorrect", modelYear.toString(), Integer.toString(programModelYear.getParentModelYear().getModelYearValue()));
	}
	
	@Test
	public void getAllPolicySets() throws EntityAlreadyExistsException, ValidationException {
		
		// STEP 1: ARRANGE
		String programCode = "ABCDE";
		Integer modelYear = Integer.valueOf(2020);
		programModelYearApplicationServiceClient.createProgramModelYear(programCode, modelYear);
		
		
		// STEP 2: ACT
		ProgramModelYearList programModelYearList = programModelYearApplicationServiceClient.getAllProgramModelYears();
		
		
		// STEP 3: ASSERT
		Assert.assertNotNull("programModelYearList is null", programModelYearList);
		Assert.assertEquals("programModelYearList size is incorrect", "1", Integer.toString(programModelYearList.getProgramModelYears().size()));
		com.djt.cvpp.ota.orfin.program.mapper.dto.ProgramModelYear programModelYear = programModelYearList.getProgramModelYears().get(0);
		Assert.assertNotNull("programModelYear is null", programModelYear);
		Assert.assertEquals("programCode is incorrect", programCode, programModelYear.getParentProgram().getProgramCode());
		Assert.assertEquals("modelYear is incorrect", modelYear.toString(), Integer.toString(programModelYear.getParentModelYear().getModelYearValue()));
	}

	@Test
	public void getPolicySetByName() throws EntityDoesNotExistException, ValidationException, EntityAlreadyExistsException {

		// STEP 1: ARRANGE
		String programCode = "ABCDE";
		Integer modelYear = Integer.valueOf(2020);
		programModelYearApplicationServiceClient.createProgramModelYear(programCode, modelYear);
		
		
		// STEP 2: ACT
		com.djt.cvpp.ota.orfin.program.mapper.dto.ProgramModelYear programModelYear = programModelYearApplicationServiceClient.getProgramModelYearByProgramCodeAndModelYear(programCode, modelYear);
		
		
		// STEP 3: ASSERT
		Assert.assertNotNull("programModelYear is null", programModelYear);
		Assert.assertEquals("programCode is incorrect", programCode, programModelYear.getParentProgram().getProgramCode());
		Assert.assertEquals("modelYear is incorrect", modelYear.toString(), Integer.toString(programModelYear.getParentModelYear().getModelYearValue()));
	}
}
