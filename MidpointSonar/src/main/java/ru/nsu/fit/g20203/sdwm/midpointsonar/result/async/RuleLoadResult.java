package ru.nsu.fit.g20203.sdwm.midpointsonar.result.async;

import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.RuleOperationResult;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class RuleLoadResult {

    public enum RuleLoadStatus {
        NOT_NEEDED,
        SUCCESS,
        ERROR
    }

    private CompletableFuture<RuleLoadStatus> ruleLoadStatus;
    private RuleOperationResult ruleOperationResult;

    public RuleLoadResult() {

    }

    public RuleLoadResult(CompletableFuture<RuleLoadStatus> ruleLoadStatus,
                          RuleOperationResult ruleOperationResult) {
        this.ruleLoadStatus = ruleLoadStatus;
        this.ruleOperationResult = ruleOperationResult;
    }

    public RuleLoadResult(CompletableFuture<RuleLoadStatus> ruleLoadStatus) {
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

    public static RuleLoadResult createSuccessful() {
        return new RuleLoadResult(CompletableFuture.completedFuture(RuleLoadStatus.NOT_NEEDED),
                new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS, null));
    }
}
