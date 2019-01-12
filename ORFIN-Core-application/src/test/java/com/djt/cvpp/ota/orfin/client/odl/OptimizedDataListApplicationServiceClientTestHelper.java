/*
 * 
 * 
 * 
 * 
 * 
 * 
 */
package com.djt.cvpp.ota.orfin.client.odl;

import com.djt.cvpp.ota.common.exception.EntityAlreadyExistsException;
import com.djt.cvpp.ota.common.exception.EntityDoesNotExistException;
import com.djt.cvpp.ota.common.exception.ValidationException;
import com.djt.cvpp.ota.orfin.client.program.ProgramModelYearApplicationServiceClient;
import com.djt.cvpp.ota.orfin.odl.model.enums.SpecificationCategoryType;

/**
 *
 * @author tmyers1@yahoo.com (Tom Myers)
 *
 */
public class OptimizedDataListApplicationServiceClientTestHelper {
	
	public void buildOdlWithFiveNodesAndOneCustomOdl(
		String programCode,
		Integer modelYear,
		String odlName,	
		String customOdlName,
		ProgramModelYearApplicationServiceClient programModelYearApplicationServiceClient,
		OptimizedDataListApplicationServiceClient optimizedDataListApplicationServiceClient) throws EntityAlreadyExistsException, ValidationException, EntityDoesNotExistException {
	
		programModelYearApplicationServiceClient.createProgramModelYear(programCode, modelYear);
		optimizedDataListApplicationServiceClient.createOdl(odlName);
		programModelYearApplicationServiceClient.associateOdlToProgramModelYear(odlName, programCode, modelYear);
				
		String networkName = "HS1";
		String protocol = "CAN";
		String dataRate = "500";
		String dclName = "SAE J1962";
		String networkPins = "6,14";		
		optimizedDataListApplicationServiceClient.createNetwork(odlName, networkName, protocol, dataRate, dclName, networkPins);

		// SYNC
		String nodeAcronym = "APIM";
		String nodeAddress = "7D0";
		String gatewayNodeId = null;
		String gatewayType = "NONE";
		Boolean hasConditionBasedOnDtc = Boolean.FALSE;
		Boolean isOvtp = Boolean.FALSE;
		String ovtpDestinationAddress = "https://autonomic.ai/bytestream/ovtp_destination_address";
		String specificationCategoryType = SpecificationCategoryType.PART2_SPEC.toString();
		Integer diagnosticSpecificationResponse = Integer.valueOf(3);
		Integer activationTime = Integer.valueOf(15);
		optimizedDataListApplicationServiceClient.createNode(odlName, networkName, nodeAcronym, nodeAddress, gatewayNodeId, gatewayType, hasConditionBasedOnDtc, isOvtp, ovtpDestinationAddress, specificationCategoryType, diagnosticSpecificationResponse, activationTime);
		
		String ignoredDids = "F10A,F16B";
		optimizedDataListApplicationServiceClient.addIgnoredDidsToNode(odlName, networkName, nodeAcronym, nodeAddress, ignoredDids);
		
		String didName = "8033";
		String description = "Embedded Consumer Operating System Part Number";
		Boolean vinSpecificDidFlag = Boolean.FALSE;
		Boolean directConfigurationDidFlag = Boolean.FALSE;
		Boolean privateNetworkDidFlag = Boolean.FALSE;
		optimizedDataListApplicationServiceClient.createDid(odlName, networkName, nodeAcronym, nodeAddress, didName, description, vinSpecificDidFlag, directConfigurationDidFlag, privateNetworkDidFlag);

		didName = "8060";
		description = "Embedded Consumer Applications Part Numbers 1";
		vinSpecificDidFlag = Boolean.TRUE;
		optimizedDataListApplicationServiceClient.createDid(odlName, networkName, nodeAcronym, nodeAddress, didName, description, vinSpecificDidFlag, directConfigurationDidFlag, privateNetworkDidFlag);

		didName = "8061";
		description = "Embedded Consumer Applications Part Numbers 2";
		optimizedDataListApplicationServiceClient.createDid(odlName, networkName, nodeAcronym, nodeAddress, didName, description, vinSpecificDidFlag, directConfigurationDidFlag, privateNetworkDidFlag);

		didName = "F111";
		description = "ECU Core Assembly Number";
		vinSpecificDidFlag = Boolean.FALSE;
		optimizedDataListApplicationServiceClient.createDid(odlName, networkName, nodeAcronym, nodeAddress, didName, description, vinSpecificDidFlag, directConfigurationDidFlag, privateNetworkDidFlag);
		
		// TCU
		networkName = "HS2";
		networkPins = "3,11";		
		optimizedDataListApplicationServiceClient.createNetwork(odlName, networkName, protocol, dataRate, dclName, networkPins);
		
		nodeAcronym = "TCU";
		nodeAddress = "754";
		gatewayNodeId = "754";
		gatewayType = "Transparent";
		hasConditionBasedOnDtc = Boolean.FALSE;
		isOvtp = Boolean.FALSE;
		ovtpDestinationAddress = "https://autonomic.ai/bytestream/ovtp_destination_address";
		specificationCategoryType = SpecificationCategoryType.PART2_SPEC.toString();
		diagnosticSpecificationResponse = Integer.valueOf(3);
		optimizedDataListApplicationServiceClient.createNode(odlName, networkName, nodeAcronym, nodeAddress, gatewayNodeId, gatewayType, hasConditionBasedOnDtc, isOvtp, ovtpDestinationAddress, specificationCategoryType, diagnosticSpecificationResponse, activationTime);
		
		ignoredDids = "F120";
		optimizedDataListApplicationServiceClient.addIgnoredDidsToNode(odlName, networkName, nodeAcronym, nodeAddress, ignoredDids);
		
		vinSpecificDidFlag = Boolean.FALSE;
		directConfigurationDidFlag = Boolean.FALSE;
		privateNetworkDidFlag = Boolean.FALSE;
		didName = "F10A";
		description = "ECU Cal-Config Part Number";
		optimizedDataListApplicationServiceClient.createDid(odlName, networkName, nodeAcronym, nodeAddress, didName, description, vinSpecificDidFlag, directConfigurationDidFlag, privateNetworkDidFlag);

		didName = "F188";
		description = "Vehicle Manufacturer ECU Software Number";
		optimizedDataListApplicationServiceClient.createDid(odlName, networkName, nodeAcronym, nodeAddress, didName, description, vinSpecificDidFlag, directConfigurationDidFlag, privateNetworkDidFlag);

		didName = "F111";
		description = "ECU Core Assembly Number";
		vinSpecificDidFlag = Boolean.FALSE;
		optimizedDataListApplicationServiceClient.createDid(odlName, networkName, nodeAcronym, nodeAddress, didName, description, vinSpecificDidFlag, directConfigurationDidFlag, privateNetworkDidFlag);
		
		// ECG
		networkName = "HS1";
		nodeAcronym = "ECG";
		nodeAddress = "716";
		gatewayNodeId = null;
		gatewayType = "NONE";
		hasConditionBasedOnDtc = Boolean.FALSE;
		isOvtp = Boolean.FALSE;
		ovtpDestinationAddress = "https://autonomic.ai/bytestream/ovtp_destination_address";
		specificationCategoryType = SpecificationCategoryType.PART2_SPEC.toString();
		diagnosticSpecificationResponse = Integer.valueOf(3);
		optimizedDataListApplicationServiceClient.createNode(odlName, networkName, nodeAcronym, nodeAddress, gatewayNodeId, gatewayType, hasConditionBasedOnDtc, isOvtp, ovtpDestinationAddress, specificationCategoryType, diagnosticSpecificationResponse, activationTime);
		
		ignoredDids = "F10A,F16B";
		optimizedDataListApplicationServiceClient.addIgnoredDidsToNode(odlName, networkName, nodeAcronym, nodeAddress, ignoredDids);
		
		vinSpecificDidFlag = Boolean.FALSE;
		directConfigurationDidFlag = Boolean.FALSE;
		privateNetworkDidFlag = Boolean.FALSE;
		didName = "8033";
		description = "Embedded Consumer Operating System Part Number";
		optimizedDataListApplicationServiceClient.createDid(odlName, networkName, nodeAcronym, nodeAddress, didName, description, vinSpecificDidFlag, directConfigurationDidFlag, privateNetworkDidFlag);

		didName = "8060";
		description = "Embedded Consumer Applications Part Numbers 1";
		optimizedDataListApplicationServiceClient.createDid(odlName, networkName, nodeAcronym, nodeAddress, didName, description, vinSpecificDidFlag, directConfigurationDidFlag, privateNetworkDidFlag);

		didName = "8061";
		description = "Embedded Consumer Applications Part Numbers 2";
		optimizedDataListApplicationServiceClient.createDid(odlName, networkName, nodeAcronym, nodeAddress, didName, description, vinSpecificDidFlag, directConfigurationDidFlag, privateNetworkDidFlag);

		didName = "F111";
		description = "ECU Core Assembly Number";
		vinSpecificDidFlag = Boolean.FALSE;
		optimizedDataListApplicationServiceClient.createDid(odlName, networkName, nodeAcronym, nodeAddress, didName, description, vinSpecificDidFlag, directConfigurationDidFlag, privateNetworkDidFlag);

		// PCM
		networkName = "HS1";
		nodeAcronym = "PCM";
		nodeAddress = "7E0";
		gatewayNodeId = null;
		gatewayType = "NONE";
		hasConditionBasedOnDtc = Boolean.FALSE;
		isOvtp = Boolean.FALSE;
		ovtpDestinationAddress = "https://autonomic.ai/bytestream/ovtp_destination_address";
		specificationCategoryType = SpecificationCategoryType.PART2_SPEC.toString();
		diagnosticSpecificationResponse = Integer.valueOf(3);
		optimizedDataListApplicationServiceClient.createNode(odlName, networkName, nodeAcronym, nodeAddress, gatewayNodeId, gatewayType, hasConditionBasedOnDtc, isOvtp, ovtpDestinationAddress, specificationCategoryType, diagnosticSpecificationResponse, activationTime);
		
		vinSpecificDidFlag = Boolean.FALSE;
		directConfigurationDidFlag = Boolean.FALSE;
		privateNetworkDidFlag = Boolean.FALSE;
		vinSpecificDidFlag = Boolean.FALSE;
		directConfigurationDidFlag = Boolean.FALSE;
		privateNetworkDidFlag = Boolean.FALSE;

		didName = "F188";
		description = "Vehicle Manufacturer ECU Software Number";
		optimizedDataListApplicationServiceClient.createDid(odlName, networkName, nodeAcronym, nodeAddress, didName, description, vinSpecificDidFlag, directConfigurationDidFlag, privateNetworkDidFlag);

		didName = "F111";
		description = "ECU Core Assembly Number";
		vinSpecificDidFlag = Boolean.FALSE;
		optimizedDataListApplicationServiceClient.createDid(odlName, networkName, nodeAcronym, nodeAddress, didName, description, vinSpecificDidFlag, directConfigurationDidFlag, privateNetworkDidFlag);

		// BCM
		networkName = "HS1";
		nodeAcronym = "PCM";
		nodeAddress = "726";
		gatewayNodeId = null;
		gatewayType = "NONE";
		hasConditionBasedOnDtc = Boolean.FALSE;
		isOvtp = Boolean.FALSE;
		ovtpDestinationAddress = "https://autonomic.ai/bytestream/ovtp_destination_address";
		specificationCategoryType = SpecificationCategoryType.PART2_SPEC.toString();
		diagnosticSpecificationResponse = Integer.valueOf(3);
		optimizedDataListApplicationServiceClient.createNode(odlName, networkName, nodeAcronym, nodeAddress, gatewayNodeId, gatewayType, hasConditionBasedOnDtc, isOvtp, ovtpDestinationAddress, specificationCategoryType, diagnosticSpecificationResponse, activationTime);
		
		vinSpecificDidFlag = Boolean.FALSE;
		directConfigurationDidFlag = Boolean.FALSE;
		privateNetworkDidFlag = Boolean.FALSE;
		vinSpecificDidFlag = Boolean.FALSE;
		directConfigurationDidFlag = Boolean.FALSE;
		privateNetworkDidFlag = Boolean.FALSE;

		didName = "F10A";
		description = "ECU Cal-Config Part Number";
		optimizedDataListApplicationServiceClient.createDid(odlName, networkName, nodeAcronym, nodeAddress, didName, description, vinSpecificDidFlag, directConfigurationDidFlag, privateNetworkDidFlag);

		didName = "F16B";
		description = "ECU Cal-Config #2 Part Number";
		optimizedDataListApplicationServiceClient.createDid(odlName, networkName, nodeAcronym, nodeAddress, didName, description, vinSpecificDidFlag, directConfigurationDidFlag, privateNetworkDidFlag);

		didName = "F16C";
		description = "ECU Cal-Config #3 Part Number";
		optimizedDataListApplicationServiceClient.createDid(odlName, networkName, nodeAcronym, nodeAddress, didName, description, vinSpecificDidFlag, directConfigurationDidFlag, privateNetworkDidFlag);

		didName = "F16D";
		description = "ECU Cal-Config #4 Part Number";
		optimizedDataListApplicationServiceClient.createDid(odlName, networkName, nodeAcronym, nodeAddress, didName, description, vinSpecificDidFlag, directConfigurationDidFlag, privateNetworkDidFlag);

		didName = "F16E";
		description = "ECU Cal-Config #5 Part Number";
		optimizedDataListApplicationServiceClient.createDid(odlName, networkName, nodeAcronym, nodeAddress, didName, description, vinSpecificDidFlag, directConfigurationDidFlag, privateNetworkDidFlag);

		didName = "F17D";
		description = "ECU Cal-Config #6 Part Number";
		optimizedDataListApplicationServiceClient.createDid(odlName, networkName, nodeAcronym, nodeAddress, didName, description, vinSpecificDidFlag, directConfigurationDidFlag, privateNetworkDidFlag);
				
		didName = "F188";
		description = "Vehicle Manufacturer ECU Software Number";
		optimizedDataListApplicationServiceClient.createDid(odlName, networkName, nodeAcronym, nodeAddress, didName, description, vinSpecificDidFlag, directConfigurationDidFlag, privateNetworkDidFlag);

		didName = "F111";
		description = "ECU Core Assembly Number";
		vinSpecificDidFlag = Boolean.FALSE;
		optimizedDataListApplicationServiceClient.createDid(odlName, networkName, nodeAcronym, nodeAddress, didName, description, vinSpecificDidFlag, directConfigurationDidFlag, privateNetworkDidFlag);
		
		// Custom ODL (TDK nodes only)
		String customOdlNodeList = "APIM_7D0,TCU_754,ECG_716";
		optimizedDataListApplicationServiceClient.addCustomOdlToOdl(odlName, customOdlName, customOdlNodeList);
		
		// ECG Signals
		String ecgSignalName = "OfbChrgGoTHr_T_Rq";
		optimizedDataListApplicationServiceClient.addEcgSignalToOdl(odlName, ecgSignalName);
		ecgSignalName = "ChrgGoTAllOn_B_Stat";
		optimizedDataListApplicationServiceClient.addEcgSignalToOdl(odlName, ecgSignalName);
	}	
}
