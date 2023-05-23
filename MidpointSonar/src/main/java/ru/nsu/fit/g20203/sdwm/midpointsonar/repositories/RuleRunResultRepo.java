package ru.nsu.fit.g20203.sdwm.midpointsonar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.g20203.sdwm.midpointsonar.entity.RuleRunResultEntity;

import java.util.List;

@Repository
public interface RuleRunResultRepo extends JpaRepository<RuleRunResultEntity, Integer> {

}
