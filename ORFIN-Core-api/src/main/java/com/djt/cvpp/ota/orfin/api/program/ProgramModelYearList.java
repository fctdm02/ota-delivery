/*
 * 
 * 
 * 
 * 
 * 
 * 
 */
package com.djt.cvpp.ota.orfin.api.program;

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
public class ProgramModelYearList {
	
	private List<com.djt.cvpp.ota.orfin.program.mapper.dto.ProgramModelYear> programModelYears = new ArrayList<>();
}
