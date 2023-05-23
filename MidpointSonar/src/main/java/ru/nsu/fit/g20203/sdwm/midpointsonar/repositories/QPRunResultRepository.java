package ru.nsu.fit.g20203.sdwm.midpointsonar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.g20203.sdwm.midpointsonar.entity.QPRunResultEntity;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.QPRunResult;

import java.util.List;


@Repository
public interface QPRunResultRepository extends JpaRepository<QPRunResultEntity, Integer> {
}
