package ru.nsu.fit.g20203.sdwm.midpointsonar.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.nsu.fit.g20203.sdwm.midpointsonar.entity.RuleLoadResultEntity;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleLoadResult;

@Mapper(componentModel = "spring")
@Component("ruleLoadResultMapper")
public interface RuleLoadResultMapper {
    RuleLoadResult map(RuleLoadResultEntity entity);
    RuleLoadResultEntity map(RuleLoadResult result);
}
