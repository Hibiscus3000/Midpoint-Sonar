package ru.nsu.fit.g20203.sdwm.midpointsonar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.g20203.sdwm.midpointsonar.entity.QualityProfileEntity;
import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.QualityProfile;

import java.util.Optional;

@Repository
public interface QPRepository extends JpaRepository<QualityProfileEntity,Integer> {
    Optional<QualityProfileEntity> findByName(String name);

    boolean existsByName(String name);
}
