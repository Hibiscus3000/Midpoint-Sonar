package ru.nsu.fit.g20203.sdwm.midpointsonar.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.nsu.fit.g20203.sdwm.midpointsonar.entity.QualityProfileEntity;
import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.QualityProfile;

@Mapper(componentModel = "spring")
public interface QualityProfileMapper {
    QualityProfile map(QualityProfileEntity entity);

    QualityProfileEntity map(QualityProfile profile);
}
