package ru.nsu.fit.g20203.sdwm.midpointsonar.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.nsu.fit.g20203.sdwm.midpointsonar.entity.RuleRunResultEntity;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleRunResult;

@Mapper(componentModel = "spring")
@Component("ruleRunResultMapper")
public interface RuleRunResultMapper {
    RuleRunResult map(RuleRunResultEntity entity);

    RuleRunResultEntity map(RuleRunResult result);
}
