/*
 * 
 * 
 * 
 * 
 * 
 * 
 */
package com.djt.cvpp.ota.orfin.app.vadrevent.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.djt.cvpp.ota.common.exception.EntityAlreadyExistsException;
import com.djt.cvpp.ota.common.exception.FenixRuntimeException;
import com.djt.cvpp.ota.common.exception.ValidationException;
import com.djt.cvpp.ota.orfin.api.vadrevent.OrfinVadrReleaseEventApplicationService;
import com.djt.cvpp.ota.orfin.api.vadrevent.OrfinVadrReleaseEventList;
import com.djt.cvpp.ota.orfin.vadrevent.repository.OrfinVadrReleaseEventRepository;
import com.djt.cvpp.ota.orfin.vadrevent.repository.impl.MockOrfinVadrReleaseEventRepositoryImpl;
import com.djt.cvpp.ota.orfin.vadrevent.service.OrfinVadrReleaseEventService;
import com.djt.cvpp.ota.orfin.vadrevent.service.impl.OrfinVadrReleaseEventServiceImpl;

import io.swagger.annotations.ApiOperation;

/**
 *
 * @author tmyers1@yahoo.com (Tom Myers)
 *
 */
@RestController
@RequestMapping(path = OrfinVadrReleaseEventApplicationService.VADR_RELEASE_EVENT_URI)
public class OrfinVadrReleaseEventController implements OrfinVadrReleaseEventApplicationService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrfinVadrReleaseEventController.class);
	
	
	public static final String VADR_RELEASE_EVENT_PREFIX = "VADR_RELEASE_EVENT_";
	public static final String VADR_RELEASE_EVENT_SUFFIX = ".txt";
	
	
	public static final String DOMAIN_NAME = "domainName";
	public static final String DOMAIN_INSTANCE_NAME = "domainInstanceName";
	public static final String DOMAIN_INSTANCE_DESCRIPTION = "domainInstanceDescription";
	public static final String DOMAIN_INSTANCE_VERSION = "domainInstanceVersion";
	public static final String APP_ID = "appId";
	public static final String APP_VERSION = "appVersion";
	public static final String PRODUCTION_STATE = "productionState";
	public static final String RELEASE_DATE = "releaseDate";
	public static final String SOFTWARE_PRIORITY_LEVEL = "softwarePriorityLevel";
	public static final String HAS_BEEN_PROCESSED = "hasBeenProcessed";
	
	
	// ORFIN dependencies
	private OrfinVadrReleaseEventRepository orfinVadrReleaseEventRepository;
	private OrfinVadrReleaseEventService orfinVadrReleaseEventService;
	
	public OrfinVadrReleaseEventController() {
		
		// ORFIN dependencies
		this.orfinVadrReleaseEventRepository = new MockOrfinVadrReleaseEventRepositoryImpl();
		this.orfinVadrReleaseEventService = new OrfinVadrReleaseEventServiceImpl(this.orfinVadrReleaseEventRepository);
	}

	
	// ************************************************************************************************************************************
	// CONTROLLER METHODS MAP TO SERVICE METHODS ONE-TO-ONE.  ALL SWAGGER DOC INFO COMES FROM JAVADOC IN SERVICE INTERFACE.
	// ************************************************************************************************************************************

	/*
	@ApiOperation(value = PUBLISH_VADR_RELEASE_EVENT_URI, notes = "Publishes a VADR Release Event to be consumed by ORFIN")
	@GetMapping(PUBLISH_VADR_RELEASE_EVENT_URI)
	public com.djt.cvpp.ota.orfin.vadrevent.mapper.dto.VadrRelease publishVadrReleaseEvent(
		@ApiParam("domainName e.g. MayHackathon-ECG")
		@RequestParam
		String domainName,

		@ApiParam("domainInstanceName e.g. ECG_Hack_02")
		@RequestParam
		String domainInstanceName,
		
		@ApiParam("domainInstanceDescription (could be anything)")
		@RequestParam
		String domainInstanceDescription,
		
		@ApiParam("domainInstanceVersion e.g. 01.01.02")
		@RequestParam
		String domainInstanceVersion,

		@ApiParam("appId (not needed for a software update)")
		@RequestParam
		String appId,
		
		@ApiParam("appVersion (not needed for a software update)")
		@RequestParam
		String appVersion,
		
		@ApiParam("productionState (For FENIX-FLARE, always use PRODUCTION)")
		@RequestParam
		String productionState,

		@ApiParam("releaseDate (any date related string, such as 10-28-2018)")
		@RequestParam
		String releaseDate,
		
		@ApiParam("softwarePriorityLevel e.g. CRITICAL_PRIORITY")
		@RequestParam
		String softwarePriorityLevel) 
	throws 
		EntityAlreadyExistsException, 
		ValidationException {
 
	 */
	
	@ApiOperation(value = PUBLISH_VADR_RELEASE_EVENT_URI, notes = "Publishes a VADR Release Event to be consumed by ORFIN")
	@GetMapping(PUBLISH_VADR_RELEASE_EVENT_URI)
	public com.djt.cvpp.ota.orfin.vadrevent.mapper.dto.VadrRelease publishVadrReleaseEvent() 
	throws 
		EntityAlreadyExistsException, 
		ValidationException {
		
		String domainName = "MayHackathon-ECG";
		String domainInstanceName = "ECG_Hack_02";
		String domainInstanceDescription = "This is the second round of testing with the ECG Image file received on 6-18";
		String domainInstanceVersion = "01.01.02";
		String appId = "";
		String appVersion = "";
		String productionState = "PRODUCTION";
		String releaseDate = "10-28-2018";
		String softwarePriorityLevel = "CRITICAL_PRIORITY";
		String hasBeenProcessed = "false";
		
		com.djt.cvpp.ota.orfin.vadrevent.mapper.dto.VadrRelease dto = null;
		OutputStream outputStream = null;
		
		try {
			File dataDirectory = new File(System.getProperty("java.io.tmpdir") + "/testdata/orfin/vadrevent/");
			
			String filename = VADR_RELEASE_EVENT_PREFIX + "_" + new Random().nextLong() + VADR_RELEASE_EVENT_SUFFIX;
			File eventFile = new File(dataDirectory, filename);
					
			Properties properties = new Properties();
			outputStream = new FileOutputStream(eventFile);
			
			properties.setProperty(DOMAIN_NAME, domainName);
			properties.setProperty(DOMAIN_INSTANCE_NAME, domainInstanceName);
			properties.setProperty(DOMAIN_INSTANCE_DESCRIPTION, domainInstanceDescription);
			properties.setProperty(DOMAIN_INSTANCE_VERSION, domainInstanceVersion);
			properties.setProperty(APP_ID, appId);
			properties.setProperty(APP_VERSION, appVersion);
			properties.setProperty(PRODUCTION_STATE, productionState);
			properties.setProperty(RELEASE_DATE, releaseDate);
			properties.setProperty(SOFTWARE_PRIORITY_LEVEL, softwarePriorityLevel);
			properties.setProperty(HAS_BEEN_PROCESSED, hasBeenProcessed);
			
			properties.store(outputStream, null);
			
			LOGGER.warn(properties.toString());
			LOGGER.warn("Writing VADR Release Event file to: " + eventFile.getAbsolutePath());
			
			dto = new com.djt.cvpp.ota.orfin.vadrevent.mapper.dto.VadrRelease();
			dto.setDomainName(domainName);
			dto.setDomainInstanceName(domainInstanceName);
			dto.setDomainInstanceDescription(domainInstanceDescription);
			dto.setDomainInstanceVersion(domainInstanceVersion);
			dto.setAppId(appId);
			dto.setAppVersion(appVersion);
			dto.setProductionState(productionState);
			dto.setReleaseDate(releaseDate);
			dto.setSoftwarePriorityLevel(softwarePriorityLevel);
			
		} catch (IOException ioe) {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					throw new FenixRuntimeException("Unable to close output stream, error: " + ioe.getMessage(), ioe);
				}
			}		
		}
		
		return dto;
	}
	
	
	@ApiOperation(value = GET_ALL_VADR_RELEASE_EVENTS_URI, notes = "Gets all defined VADR Release Events")
	@GetMapping(GET_ALL_VADR_RELEASE_EVENTS_URI)
	public @ResponseBody OrfinVadrReleaseEventList getAllVadrReleaseEvents() {
		
		List<com.djt.cvpp.ota.orfin.vadrevent.mapper.dto.VadrRelease> list = new ArrayList<>();
		
		Iterator<com.djt.cvpp.ota.orfin.vadrevent.model.VadrRelease> iterator = this.orfinVadrReleaseEventService.getAllVadrReleases().iterator();
		while (iterator.hasNext()) {
		
			list.add(this.orfinVadrReleaseEventService.getDtoMapper().mapEntityToDto(iterator.next()));
		}
		
		OrfinVadrReleaseEventList dto = new OrfinVadrReleaseEventList();
		dto.setVadrReleases(list);
		return dto;
	}
}
