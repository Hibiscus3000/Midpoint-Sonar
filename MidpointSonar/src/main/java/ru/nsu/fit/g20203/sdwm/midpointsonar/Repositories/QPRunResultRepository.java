package ru.nsu.fit.g20203.sdwm.midpointsonar.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.QPRunResult;

import java.util.List;
import java.util.Optional;

@Repository
public interface QPRunResultRepository extends CrudRepository<QPRunResult, Long> {
}
