/*
 * 
 * 
 * 
 * 
 * 
 * 
 */
package com.djt.cvpp.ota.orfin.api.vadrevent;

import com.djt.cvpp.ota.common.exception.EntityAlreadyExistsException;
import com.djt.cvpp.ota.common.exception.ValidationException;

/**
 * 
 * @author tmyers1@yahoo.com (Tom Myers)
 *
 */
public interface OrfinVadrReleaseEventApplicationService {
	
	String VADR_RELEASE_EVENT_URI = "/api/v1/vadrevent";
	
	
	String DOMAIN_NAME = "domainName";
	String DOMAIN_INSTANCE_NAME = "domainInstanceName";
	String DOMAIN_INSTANCE_DESCRIPTION = "domainInstanceDescription";
	String DOMAIN_INSTANCE_VERSION = "domainInstanceVersion";
	String APP_ID = "appId";
	String APP_VERSION = "appVersion";
	String PRODUCTION_STATE = "productionState";
	String RELEASE_DATE = "releaseDate";
	String SOFTWARE_PRIORITY_LEVEL = "softwarePriorityLevel";
	
	
	/**
	 * 
	 * @param domainName
	 * @param domainInstanceName
	 * @param domainInstanceDescription
	 * @param domainInstanceVersion
	 * @param appId
	 * @param appVersion
	 * @param productionState
	 * @param releaseDate
	 * @param softwarePriorityLevel
	 * @return
	 * @throws EntityAlreadyExistsException
	 * @throws ValidationException
	 */
	String PUBLISH_VADR_RELEASE_EVENT_URI = "/publishVadrReleaseEvent";
	/*
	com.djt.cvpp.ota.orfin.vadrevent.mapper.dto.VadrRelease publishVadrReleaseEvent(
		String domainName,
		String domainInstanceName,
		String domainInstanceDescription,
		String domainInstanceVersion,
		String appId,
		String appVersion,
		String productionState,
		String releaseDate,
		String softwarePriorityLevel) 
	throws 
		EntityAlreadyExistsException, 
		ValidationException;
	*/	
	com.djt.cvpp.ota.orfin.vadrevent.mapper.dto.VadrRelease publishVadrReleaseEvent() 
	throws 
		EntityAlreadyExistsException, 
		ValidationException;

	
	/**
	 * 
	 * @return
	 */
	String GET_ALL_VADR_RELEASE_EVENTS_URI = "/getAllVadrReleaseEvents";
	OrfinVadrReleaseEventList getAllVadrReleaseEvents();
}
