package ru.nsu.fit.g20203.sdwm.midpointsonar.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleLoadResult;

import java.util.List;
import java.util.Optional;

@Repository
public interface RuleLoadResultRepository extends CrudRepository<RuleLoadResult, Long> {

}
