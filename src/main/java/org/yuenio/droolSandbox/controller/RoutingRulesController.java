package org.yuenio.droolSandbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.yuenio.droolSandbox.model.RoutingRule;
import org.yuenio.droolSandbox.repo.DroolRoutingRulesRepo;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RoutingRulesController {

    @Autowired
    private DroolRoutingRulesRepo routingRulesRepo;

    @PostMapping("/routingRule")
    public void addRule (@RequestBody RoutingRule rule) {
        routingRulesRepo.save(rule);
    }

    @GetMapping("/routingRules")
    public List<RoutingRule> getRoutingRules () {
        List<RoutingRule> routingRules = new ArrayList<RoutingRule>();
        routingRulesRepo.findAll().forEach(routingRules::add);
        return routingRules;
    }
}
