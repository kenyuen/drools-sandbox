package org.yuenio.droolSandbox;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.yuenio.droolSandbox.model.Email;
import org.yuenio.droolSandbox.service.EmailService;

import static org.junit.Assert.assertEquals;


public class DroolUnitTests {
    private KieSession kieSession;

    private EmailService emailService;

    @Before
    public void setUp() {
        // Load the rules?
        //EmailService
    }

    @Test
    public void testAssignment() {
        // Given
        Email email = new Email();
        email.setSubject("Test A");
        email.setTeamAssigned("No One Yet");

        // When
        Email assignedEmail = emailService.getAssignmentForEmail(email);

        // Then
        assertEquals("Team A",assignedEmail.getTeamAssigned());
    }
}