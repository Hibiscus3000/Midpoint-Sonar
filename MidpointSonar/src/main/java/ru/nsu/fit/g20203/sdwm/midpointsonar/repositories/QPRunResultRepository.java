package ru.nsu.fit.g20203.sdwm.midpointsonar.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.QPRunResult;


@Repository
public interface QPRunResultRepository extends CrudRepository<QPRunResult, Long> {
}
