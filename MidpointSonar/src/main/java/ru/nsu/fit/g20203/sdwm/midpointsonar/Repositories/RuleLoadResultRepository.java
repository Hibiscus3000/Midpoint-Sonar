package ru.nsu.fit.g20203.sdwm.midpointsonar.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleLoadResult;


@Repository
public interface RuleLoadResultRepository extends CrudRepository<RuleLoadResult, Long> {

}
