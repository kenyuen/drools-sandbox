package org.yuenio.droolSandbox.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Email {

    private String mailbox; // destination
    private String senderEmail;
    private String subject;

    // following attributes would be affected by rule outcome
    private String teamAssigned;

}
