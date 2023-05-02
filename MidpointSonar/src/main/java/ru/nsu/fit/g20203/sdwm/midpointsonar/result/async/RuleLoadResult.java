package ru.nsu.fit.g20203.sdwm.midpointsonar.result.async;

import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.RuleOperationResult;

public class RuleLoadResult {

    public enum RuleLoadStatus {
        NOT_NEEDED,
        SUCCESS,
        ERROR
    }

    private final RuleLoadStatus ruleLoadStatus;
    private final RuleOperationResult ruleOperationResult;

    public RuleLoadResult(RuleLoadStatus ruleLoadStatus,
                          RuleOperationResult ruleOperationResult) {
        this.ruleLoadStatus = ruleLoadStatus;
        this.ruleOperationResult = ruleOperationResult;
    }

}
