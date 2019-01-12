/*
 * 
 * 
 * 
 * 
 * 
 * 
 */
package com.djt.cvpp.ota.orfin.api.vadrevent;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 *
 * @author tmyers1@yahoo.com (Tom Myers)
 *
 */
@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class OrfinVadrReleaseEventList {
	
	private List<com.djt.cvpp.ota.orfin.vadrevent.mapper.dto.VadrRelease> vadrReleases = new ArrayList<>();
}
