package org.yuenio.droolSandbox.service;

import org.drools.template.ObjectDataCompiler;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.stereotype.Service;
import org.yuenio.droolSandbox.config.DroolConfig;
import org.yuenio.droolSandbox.model.Email;
import org.yuenio.droolSandbox.model.RoutingRule;
import org.yuenio.droolSandbox.repo.DroolRoutingRulesRepo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmailService {

    private final KieContainer kieContainer;
    private final DroolRoutingRulesRepo routingRulesRepo;

    public EmailService(KieContainer kieContainer, DroolRoutingRulesRepo routingRulesRepo){
        this.kieContainer = kieContainer;
        this.routingRulesRepo = routingRulesRepo;
    }

    public Email getAssignmentForEmail(Email email) {
        KieSession session = kieContainer.newKieSession();
        session.insert(email);
        session.fireAllRules();
        session.dispose();
        return email;
    }

    public Email getAssignmentForEmailV2(Email email) throws FileNotFoundException{
        List<RoutingRule> ruleAttributes = new ArrayList<>(routingRulesRepo.findAll());

        ObjectDataCompiler compiler = new ObjectDataCompiler();
        String generatedDRL = compiler.compile(ruleAttributes, Thread.currentThread().getContextClassLoader().getResourceAsStream(DroolConfig.RULES_TEMPLATE_FILE));
        KieServices kieServices = KieServices.Factory.get();

        KieHelper kieHelper = new KieHelper();

        //multiple such resoures/rules can be added
        byte[] b1 = generatedDRL.getBytes();
        Resource resource1 = kieServices.getResources().newByteArrayResource(b1);
        kieHelper.addResource(resource1, ResourceType.DRL);

        KieBase kieBase = kieHelper.build();

        KieSession kieSession = kieBase.newKieSession();
        kieSession.insert(email);
        int numberOfRulesFired = kieSession.fireAllRules();
        kieSession.dispose();

        return email;
    }
}
