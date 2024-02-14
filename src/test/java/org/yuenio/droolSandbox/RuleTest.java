package org.yuenio.droolSandbox;


import org.junit.Before;
import org.junit.Test;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.yuenio.droolSandbox.config.DroolConfig;
import org.yuenio.droolSandbox.model.GrievanceRule;
import org.yuenio.droolSandbox.service.GrievanceRuleService;

import static org.junit.Assert.assertNotNull;

//  https://stackoverflow.com/questions/69950261/how-to-call-a-drools-rule-using-java-integration-tests
public class RuleTest {

    @Autowired
    private GrievanceRuleService grievanceRuleService;

    @Autowired
    public KieContainer kieContainer;

    @Before
    public void setUp() {
        // setup session
        KieContainer kieContainer = new DroolConfig().kieContainer();
        this.grievanceRuleService = new GrievanceRuleService();
    }
    @Test
    public void calculateEligibility() {
        int id = 1;
        GrievanceRule grievanceRule = new GrievanceRule();
        grievanceRule.setCategory("Access/Availability");
        grievanceRule.setVersion(id);

        String serviceType = this.grievanceRuleService.determineGrievance(grievanceRule);
        assertNotNull(serviceType);
    }
}








