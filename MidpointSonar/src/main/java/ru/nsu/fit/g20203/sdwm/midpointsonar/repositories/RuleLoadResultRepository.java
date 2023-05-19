package ru.nsu.fit.g20203.sdwm.midpointsonar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleLoadResult;


@Repository
public interface RuleLoadResultRepository extends JpaRepository<RuleLoadResult, Long> {

}
