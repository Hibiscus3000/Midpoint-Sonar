package ru.nsu.fit.g20203.sdwm.midpointsonar.result.async;

import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult;

import java.util.Collection;
import java.util.concurrent.Future;

public class QPRunResult extends AsyncResult {

    private final Collection<RuleRunResult> ruleRunResults;
    private final QPOperationResult qpOperationResult;

    public QPRunResult(Long id,
                       Collection<RuleRunResult> ruleRunResults, QPOperationResult qpOperationResult) {
        super(id);
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
