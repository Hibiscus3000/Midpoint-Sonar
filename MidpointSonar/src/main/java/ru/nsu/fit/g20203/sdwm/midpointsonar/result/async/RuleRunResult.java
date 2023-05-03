package ru.nsu.fit.g20203.sdwm.midpointsonar.result.async;

import ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint.MidPointSonarObject;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.Status;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.RuleOperationResult;

import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class RuleRunResult extends AsyncResult {

    private RuleOperationResult ruleOperationResult;
    private final RuleLoadResult ruleLoadResult;
    private final Future<Status> ruleRunStatus;
    private final Future<Collection<MidPointSonarObject>> objects;

    public RuleRunResult(Long id, RuleLoadResult ruleLoadResult, Future<Status> ruleRunStatus,
                         Future<Collection<MidPointSonarObject>> objects) {
        super(id);
        this.ruleLoadResult = ruleLoadResult;
        this.ruleRunStatus = ruleRunStatus;
        this.objects = objects;
    }

    public void setRuleOperationResult(RuleOperationResult ruleOperationResult) {
        this.ruleOperationResult = ruleOperationResult;
    }

    public RuleOperationResult getRuleOperationResult() {
        return ruleOperationResult;
    }

    public RuleLoadResult getRuleLoadResult() {
        return ruleLoadResult;
    }

    public Future<Status> getRuleRunStatus() {
        return ruleRunStatus;
    }

    public Future<Collection<MidPointSonarObject>> getObjects(){
        return objects;
    }
}
