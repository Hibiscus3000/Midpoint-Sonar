package ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile;

import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult;

public interface QualityProfile {

    String getName();

    QPOperationResult addRule(Rule rule);

    QPOperationResult removeRule(String ruleName);
}
