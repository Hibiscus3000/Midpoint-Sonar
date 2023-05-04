package ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.QualityProfile;

public interface QPRepository extends JpaRepository<QualityProfile,Long> {
    QualityProfile findByName(String name);

}
