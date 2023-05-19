package ru.nsu.fit.g20203.sdwm.midpointsonar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.g20203.sdwm.midpointsonar.entity.RuleEntity;
import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.Rule;

import java.util.Optional;

@Repository
public interface RuleRepository extends JpaRepository<RuleEntity,Integer> {
    Optional<RuleEntity> findByName(String name);
}
