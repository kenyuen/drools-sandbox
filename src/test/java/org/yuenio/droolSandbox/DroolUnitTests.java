package org.yuenio.droolSandbox;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.yuenio.droolSandbox.config.DroolConfig;
import org.yuenio.droolSandbox.model.Email;
import org.yuenio.droolSandbox.model.RoutingRule;
import org.yuenio.droolSandbox.repo.DroolRoutingRulesRepo;
import org.yuenio.droolSandbox.service.EmailService;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class DroolUnitTests {
    private EmailService emailService;
    @Autowired
    private DroolRoutingRulesRepo routingRulesRepo;

    @Before
    public void setUp() {
        // setup session
        KieContainer kieContainer = new DroolConfig().kieContainer();
        // Load the rules?
        RoutingRule routingRule = new RoutingRule();
        routingRule.setIfcondition("Team A");
        routingRule.setThencondition("Team A");
        // initiate the repo
//        this.routingRulesRepo = new DroolRoutingRulesRepo();
        this.routingRulesRepo.save(routingRule);
        // setup email service
        this.emailService = new EmailService(kieContainer,this.routingRulesRepo);
    }

    @Test
    public void testAssignment() throws FileNotFoundException {
        // Given
        Email email = new Email();
        email.setSubject("Test A");
        email.setTeamAssigned("No One Yet");

        // When
 //       Email assignedEmail = emailService.getAssignmentForEmail(email);
        Email assignedEmail = emailService.getAssignmentForEmailV2(email);

        // Then
        assertEquals("Test A",assignedEmail.getTeamAssigned());
    }
}