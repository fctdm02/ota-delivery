/*
 * 
 * 
 * 
 * 
 * 
 * 
 */
package com.djt.cvpp.ota.orfin.client.delivery;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Iterator;

import com.djt.cvpp.ota.common.exception.EntityAlreadyExistsException;
import com.djt.cvpp.ota.common.exception.EntityDoesNotExistException;
import com.djt.cvpp.ota.common.exception.ValidationException;
import com.djt.cvpp.ota.orfin.api.delivery.DeliveryRuleSetApplicationService;
import com.djt.cvpp.ota.orfin.api.delivery.DeliveryRuleSetList;
import com.djt.cvpp.ota.orfin.delivery.event.OrfinDeliveryRuleSetEvent;
import com.djt.cvpp.ota.orfin.delivery.mapper.dto.DeliveryRuleSet;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * This client is only used for retrieving information, all create/update/delete operations are not supported
 *
 * @author tmyers1@yahoo.com (Tom Myers)
 *
 */
public class MockDeliveryRuleSetApplicationServiceClient implements DeliveryRuleSetApplicationService {
	
	private File repositoryLocation;
	private ObjectMapper mapper = new ObjectMapper();
	
	public MockDeliveryRuleSetApplicationServiceClient(File repositoryLocation) {
		
		this.repositoryLocation = repositoryLocation;
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

		throw new RuntimeException("Not supported.");
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

		throw new RuntimeException("Not supported.");
	}

	public com.djt.cvpp.ota.orfin.delivery.mapper.dto.DeliveryRuleSet createComplexCondition(
		String deliveryRuleSetName,
		String complexConditionName,
		String complexConditionValue)
	throws 
		EntityAlreadyExistsException,
		EntityDoesNotExistException,
		ValidationException {

		throw new RuntimeException("Not supported.");
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

		throw new RuntimeException("Not supported.");
	}
	
	public DeliveryRuleSetList getAllDeliveryRuleSets() {

		DeliveryRuleSetList deliveryRuleSetList = new DeliveryRuleSetList();
		File[] files = this.repositoryLocation.listFiles();
		for (int i=0; i < files.length; i++) {
			
			File file = files[i];
			if (file.getName().endsWith(".json")) {
				deliveryRuleSetList.getDeliveryRuleSets().add(loadDeliveryRuleSetDto(file));
			}
		}
		return deliveryRuleSetList;
	}
	
	public com.djt.cvpp.ota.orfin.delivery.mapper.dto.DeliveryRuleSet getDeliveryRuleSetByName(
		String deliveryRuleSetName) 
	throws 
		EntityDoesNotExistException, 
		ValidationException {
		
		return loadDeliveryRuleSetDto(new File(this.repositoryLocation, deliveryRuleSetName + ".json"));
	}

	public OrfinDeliveryRuleSetEvent publishOrfinDeliveryRuleSetEvent(
		String owner, 
		String updateAction,
		String deliveryRuleSetName,
		String nodeAcronym,
		String nodeAddress)
	throws 
		ValidationException {

		throw new RuntimeException("Not supported.");
	}
	
	private DeliveryRuleSet loadDeliveryRuleSetDto(File file) {
		
		InputStream inputStream = null;
		try {
			return mapper.readValue(loadTestData(new FileInputStream(file)), new TypeReference<DeliveryRuleSet>(){});
        } catch (IOException ioe) {
            throw new RuntimeException("Unable to load file: [" + file.getAbsolutePath() + "], error: " + ioe.getMessage(), ioe);
        } finally {
        	if (inputStream != null) {
        		try {
        			inputStream.close();
                } catch (IOException ioe) {
                    throw new RuntimeException("Unable to close input stream: [" + file.getAbsolutePath() + "], error: " + ioe.getMessage(), ioe);
                }
        	}
        }
	}
	
	private String loadTestData(InputStream inputStream) throws IOException {

        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")))) {
            Iterator<String> iterator = reader.lines().iterator();
            while (iterator.hasNext()) {
                sb.append(iterator.next());
            }
        }
        return sb.toString();
    }	    
}