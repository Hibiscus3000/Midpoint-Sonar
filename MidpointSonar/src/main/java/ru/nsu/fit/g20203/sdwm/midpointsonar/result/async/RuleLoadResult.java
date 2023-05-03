package ru.nsu.fit.g20203.sdwm.midpointsonar.result.async;

import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.RuleOperationResult;

import java.util.concurrent.Future;

public class RuleLoadResult extends AsyncResult {

    public enum RuleLoadStatus {
        NOT_NEEDED,
        SUCCESS,
        ERROR
    }

    private final Future<RuleLoadStatus> ruleLoadStatus;
    private RuleOperationResult ruleOperationResult;

    public RuleLoadResult(Long id, Future<RuleLoadStatus> ruleLoadStatus) {
        super(id);
        this.ruleLoadStatus = ruleLoadStatus;
    }

    public void setRuleOperationResult(RuleOperationResult ruleOperationResult) {
        this.ruleOperationResult = ruleOperationResult;
    }

    public Future<RuleLoadStatus> getRuleLoadStatus() {
        return ruleLoadStatus;
    }

    public RuleOperationResult getRuleOperationResult() {
        return ruleOperationResult;
    }
}
