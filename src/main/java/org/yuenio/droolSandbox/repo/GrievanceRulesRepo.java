package org.yuenio.droolSandbox.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yuenio.droolSandbox.model.GrievanceRule;

public interface GrievanceRulesRepo extends JpaRepository<GrievanceRule, Integer> {
}
