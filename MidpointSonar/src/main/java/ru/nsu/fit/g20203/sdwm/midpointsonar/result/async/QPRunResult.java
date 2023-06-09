package ru.nsu.fit.g20203.sdwm.midpointsonar.result.async;

import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult;

import java.util.Collection;

public class QPRunResult {

    private final Collection<RuleRunResult> ruleRunResults;
    private final QPOperationResult qpOperationResult;

    public QPRunResult(Collection<RuleRunResult> ruleRunResults, QPOperationResult qpOperationResult) {
        this.ruleRunResults = ruleRunResults;
        this.qpOperationResult = qpOperationResult;
    }

    public Collection<RuleRunResult> getRuleRunResults() {
        return ruleRunResults;
    }

    public QPOperationResult getQpOperationResult() {
        return qpOperationResult;
    }
}
