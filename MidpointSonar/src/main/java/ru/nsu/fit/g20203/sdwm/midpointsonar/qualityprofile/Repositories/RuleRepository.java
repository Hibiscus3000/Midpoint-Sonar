package ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.Rule;

public interface RuleRepository extends JpaRepository<Rule,Long> {
    Rule findByName(String name);
}
