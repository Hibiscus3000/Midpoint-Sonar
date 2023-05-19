package ru.nsu.fit.g20203.sdwm.midpointsonar.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.nsu.fit.g20203.sdwm.midpointsonar.entity.RuleEntity;
import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.Rule;

@Mapper(componentModel = "spring")
public interface RuleMapper {
    Rule map(RuleEntity entity);
    RuleEntity map(Rule rule);
}
