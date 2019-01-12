/*
 * 
 * 
 * 
 * 
 * 
 * 
 */
package com.djt.cvpp.ota.orfin.client;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.djt.cvpp.ota.orfin.OrfinCoreApplication;
import com.djt.cvpp.ota.orfin.client.delivery.DeliveryRuleSetApplicationServiceClient;
import com.djt.cvpp.ota.orfin.client.odl.OptimizedDataListApplicationServiceClient;
import com.djt.cvpp.ota.orfin.client.policy.PolicySetApplicationServiceClient;
import com.djt.cvpp.ota.orfin.client.program.ProgramModelYearApplicationServiceClient;

/**
*
* @author tmyers1@yahoo.com (Tom Myers)
*
*/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OrfinCoreApplication.class, DeliveryRuleSetApplicationServiceClient.class, OptimizedDataListApplicationServiceClient.class, PolicySetApplicationServiceClient.class, ProgramModelYearApplicationServiceClient.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public abstract class BaseOrfinApplicationServiceClientTest {

	
}
