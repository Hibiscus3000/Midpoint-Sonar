package ru.nsu.fit.g20203.sdwm.midpointsonar.result.async;

import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.RuleOperationResult;

public class RuleLoadResult extends AsyncResult {

    public enum RuleLoadStatus {
        NOT_NEEDED,
        SUCCESS,
        ERROR
    }

    private final RuleLoadStatus ruleLoadStatus;
    private final RuleOperationResult ruleOperationResult;

    public RuleLoadResult(Long id, RuleLoadStatus ruleLoadStatus,
                          RuleOperationResult ruleOperationResult) {
        super(id);
        this.ruleLoadStatus = ruleLoadStatus;
        this.ruleOperationResult = ruleOperationResult;
    }

}
