/*
 * 
 * 
 * 
 * 
 * 
 * 
 */
package com.djt.cvpp.ota.orfin.api.delivery;

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
public class DeliveryRuleSetList {
	
	private List<com.djt.cvpp.ota.orfin.delivery.mapper.dto.DeliveryRuleSet> deliveryRuleSets = new ArrayList<>();
}
