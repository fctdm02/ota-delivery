/*
 * 
 * 
 * 
 * 
 * 
 * 
 */
package com.djt.cvpp.ota.orfin.api.policy;

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
public class PolicySetList {
	
	private List<com.djt.cvpp.ota.orfin.policy.mapper.dto.PolicySet> policies = new ArrayList<>();
}
