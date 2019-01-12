/*
 * 
 * 
 * 
 * 
 * 
 * 
 */
package com.djt.cvpp.ota.orfin.client.delivery;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.djt.cvpp.ota.common.exception.EntityAlreadyExistsException;
import com.djt.cvpp.ota.common.exception.EntityDoesNotExistException;
import com.djt.cvpp.ota.common.exception.ValidationException;
import com.djt.cvpp.ota.common.model.AbstractEntity;
import com.djt.cvpp.ota.orfin.api.delivery.DeliveryRuleSetList;
import com.djt.cvpp.ota.orfin.client.BaseOrfinApplicationServiceClientTest;
import com.djt.cvpp.ota.orfin.delivery.model.enums.ConsentType;
import com.djt.cvpp.ota.orfin.delivery.repository.impl.MockDeliveryRuleSetRepositoryImpl;
import com.djt.cvpp.ota.orfin.program.repository.impl.MockProgramModelYearRepositoryImpl;

/**
 *
 * @author tmyers1@yahoo.com (Tom Myers)
 *
 */
public class DeliveryRuleSetApplicationServiceClientTest extends BaseOrfinApplicationServiceClientTest {

	//@Autowired
	//private ProgramModelYearApplicationServiceClient programModelYearApplicationServiceClient;
	
	@Autowired
	private DeliveryRuleSetApplicationServiceClient deliveryRuleSetApplicationServiceClient;
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.getDefault());
	
	@Before
	public void before() {
		MockDeliveryRuleSetRepositoryImpl.getInstance().reset();
		MockProgramModelYearRepositoryImpl.getInstance().reset();
	}
	
	@Test
	public void createDeliveryRuleSet() throws EntityAlreadyExistsException, ValidationException {

		// STEP 1: ARRANGE
		String deliveryRuleSetName = "test_delivery_rule_set_name";
		String authorizedBy = "authorizedBy";
		String messageToConsumer = "message to consumer";
		String consentType = ConsentType.SAFETY_UPDATE_NOTICE.toString();
		String scheduledRolloutDate = dateFormat.format(AbstractEntity.getTimeKeeper().getTimestampForDaysFromCurrent(5));
		
		
		// STEP 2: ACT
		com.djt.cvpp.ota.orfin.delivery.mapper.dto.DeliveryRuleSet deliveryRuleSet = deliveryRuleSetApplicationServiceClient.createDeliveryRuleSet(deliveryRuleSetName, authorizedBy, messageToConsumer, consentType, scheduledRolloutDate);
		
		
		// STEP 3: ASSERT
		Assert.assertNotNull("deliveryRuleSet is null", deliveryRuleSet);
		Assert.assertEquals("deliveryRuleSetName is incorrect", deliveryRuleSetName, deliveryRuleSet.getDeliveryRuleSetName());
	}
	
	@Test
	public void getAllDeliveryRuleSets() throws EntityAlreadyExistsException, ValidationException {
		
		// STEP 1: ARRANGE
		String deliveryRuleSetName = "test_delivery_rule_set_name";
		String authorizedBy = "authorizedBy";
		String messageToConsumer = "message to consumer";
		String consentType = ConsentType.SAFETY_UPDATE_NOTICE.toString();
		String scheduledRolloutDate = dateFormat.format(AbstractEntity.getTimeKeeper().getTimestampForDaysFromCurrent(5));
		deliveryRuleSetApplicationServiceClient.createDeliveryRuleSet(deliveryRuleSetName, authorizedBy, messageToConsumer, consentType, scheduledRolloutDate);
		
		
		// STEP 2: ACT
		DeliveryRuleSetList deliveryRuleSetList = deliveryRuleSetApplicationServiceClient.getAllDeliveryRuleSets();
		
		
		// STEP 3: ASSERT
		Assert.assertNotNull("deliveryRuleSetList is null", deliveryRuleSetList);
		Assert.assertEquals("deliveryRuleSetList size is incorrect", "1", Integer.toString(deliveryRuleSetList.getDeliveryRuleSets().size()));
		com.djt.cvpp.ota.orfin.delivery.mapper.dto.DeliveryRuleSet deliveryRuleSet = deliveryRuleSetList.getDeliveryRuleSets().get(0);
		Assert.assertNotNull("deliveryRuleSet is null", deliveryRuleSet);
		Assert.assertEquals("deliveryRuleSetName is incorrect", deliveryRuleSetName, deliveryRuleSet.getDeliveryRuleSetName());
	}

	@Test
	public void getDeliveryRuleSetByName() throws EntityDoesNotExistException, ValidationException, EntityAlreadyExistsException {

		// STEP 1: ARRANGE
		String deliveryRuleSetName = "test_delivery_rule_set_name";
		String authorizedBy = "authorizedBy";
		String messageToConsumer = "message to consumer";
		String consentType = ConsentType.SAFETY_UPDATE_NOTICE.toString();
		String scheduledRolloutDate = dateFormat.format(AbstractEntity.getTimeKeeper().getTimestampForDaysFromCurrent(5));
		deliveryRuleSetApplicationServiceClient.createDeliveryRuleSet(deliveryRuleSetName, authorizedBy, messageToConsumer, consentType, scheduledRolloutDate);
		
		
		// STEP 2: ACT
		com.djt.cvpp.ota.orfin.delivery.mapper.dto.DeliveryRuleSet deliveryRuleSet = deliveryRuleSetApplicationServiceClient.getDeliveryRuleSetByName(deliveryRuleSetName);
		
		
		// STEP 3: ASSERT
		Assert.assertNotNull("deliveryRuleSet is null", deliveryRuleSet);
		Assert.assertEquals("deliveryRuleSet name is incorrect", deliveryRuleSetName, deliveryRuleSet.getDeliveryRuleSetName());
	}
}
