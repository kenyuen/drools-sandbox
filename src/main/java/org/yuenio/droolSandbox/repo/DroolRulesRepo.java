package org.yuenio.droolSandbox.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yuenio.droolSandbox.model.Rule;


public interface DroolRulesRepo extends JpaRepository<Rule, Integer> {
}
