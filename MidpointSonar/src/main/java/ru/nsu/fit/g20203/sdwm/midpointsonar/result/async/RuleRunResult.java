package ru.nsu.fit.g20203.sdwm.midpointsonar.result.async;

import ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint.MidPointSonarObject;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.Status;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.RuleOperationResult;

import java.util.Collection;

public class RuleRunResult {

    private final RuleOperationResult ruleOperationResult;
    private final RuleLoadResult ruleLoadResult;
    private final Status ruleRunStatus;
    private final Collection<MidPointSonarObject> objects;

    public RuleRunResult(RuleOperationResult ruleOperationResult,
                         RuleLoadResult ruleLoadResult, Status ruleRunStatus,
                         Collection<MidPointSonarObject> objects) {
        this.ruleOperationResult = ruleOperationResult;
        this.ruleLoadResult = ruleLoadResult;
        this.ruleRunStatus = ruleRunStatus;
        this.objects = objects;
    }

    public RuleOperationResult getRuleOperationResult() {
        return ruleOperationResult;
    }

    public RuleLoadResult getRuleLoadResult() {
        return ruleLoadResult;
    }

    public Status getRuleRunStatus() {
        return ruleRunStatus;
    }

    public Collection<MidPointSonarObject> getObjects() {
        return objects;
    }
}
