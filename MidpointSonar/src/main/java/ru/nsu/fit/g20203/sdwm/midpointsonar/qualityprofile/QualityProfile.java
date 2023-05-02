package ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile;

import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult;

import java.util.Collection;

public abstract class QualityProfile {

    private String name;
    
    public String getName() {
        return name;
    }

    public abstract QPOperationResult addRule(Rule rule);

    public abstract QPOperationResult removeRule(String ruleName);

    public abstract Collection<Rule> getAllRules();
}