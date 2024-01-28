package org.yuenio.droolSandbox.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yuenio.droolSandbox.model.RoutingRule;


public interface DroolRoutingRulesRepo extends JpaRepository<RoutingRule, Integer> {
}
