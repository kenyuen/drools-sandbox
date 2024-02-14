package org.yuenio.droolSandbox.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yuenio.droolSandbox.model.GrievanceRule;
import org.yuenio.droolSandbox.repo.GrievanceRulesRepo;

@Service
public class GrievanceRuleService {

    @Autowired
    public KieContainer kieContainer;

    private GrievanceRulesRepo grievanceRulesRepo;

    public void GrievanceService(KieContainer kieContainer, GrievanceRulesRepo grievanceRulesRepo){
        this.kieContainer = kieContainer;
        this.grievanceRulesRepo = grievanceRulesRepo;
    }

    public String determineGrievance(GrievanceRule grievanceDTO) {

        KieSession kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("grievanceDTO", grievanceDTO);
        kieSession.insert(grievanceDTO);
        kieSession.fireAllRules();
        kieSession.dispose();

        return grievanceDTO.getServiceType();

    }
}