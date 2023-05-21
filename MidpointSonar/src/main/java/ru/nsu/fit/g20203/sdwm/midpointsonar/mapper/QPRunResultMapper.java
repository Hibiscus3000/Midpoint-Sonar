package ru.nsu.fit.g20203.sdwm.midpointsonar.mapper;

import org.checkerframework.checker.units.qual.C;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.nsu.fit.g20203.sdwm.midpointsonar.entity.QPRunResultEntity;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.QPRunResult;

@Mapper(componentModel = "spring")
@Component("qpRunResultMapper")
public interface QPRunResultMapper {
    QPRunResult map(QPRunResultEntity entity);
    QPRunResultEntity map(QPRunResult result);
}
