package org.yuenio.droolSandbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.yuenio.droolSandbox.model.Email;
import org.yuenio.droolSandbox.service.EmailService;

import java.io.FileNotFoundException;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/getAssignment")
    public Email getAssignment (@RequestBody Email email) throws FileNotFoundException{
        return emailService.getAssignmentForEmailV2(email);
    }
}
