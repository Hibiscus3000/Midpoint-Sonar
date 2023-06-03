package ru.nsu.fit.g20203.sdwm.midpointsonar.result.async;

import ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint.MidPointSonarObject;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.Status;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.RuleOperationResult;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class RuleRunResult {

    private RuleOperationResult ruleOperationResult;
    private RuleLoadResult ruleLoadResult;
    private CompletableFuture<Status> ruleRunStatus;
    private CompletableFuture<Collection<MidPointSonarObject>> objects;

    public void setRuleOperationResult(RuleOperationResult ruleOperationResult) {
        this.ruleOperationResult = ruleOperationResult;
    }

    public void setRuleLoadResult(RuleLoadResult ruleLoadResult) {
        this.ruleLoadResult = ruleLoadResult;
    }

    public void setRuleRunStatus(CompletableFuture<Status> ruleRunStatus) {
        this.ruleRunStatus = ruleRunStatus;
    }

    public void setObjects(CompletableFuture<Collection<MidPointSonarObject>> objects) {
        this.objects = objects;
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

    public CompletableFuture<Collection<MidPointSonarObject>> getObjects() {
        return objects;
    }
}
