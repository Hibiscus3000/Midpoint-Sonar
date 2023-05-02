package ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync;

public class QPAndRuleOperationResult {

    public enum QPAndRuleOperationStatus {
        RULE_ALREADY_IN_QP,
        RULE_NOT_IN_QP,
        SUCCESS
    }

    private final QPAndRuleOperationStatus qpAndRuleOperationStatus;
    private final QPOperationResult qpOperationResult;
    private final RuleOperationResult ruleOperationResul;

    public QPAndRuleOperationResult(QPAndRuleOperationStatus qpAndRuleOperationStatus,
                                    QPOperationResult qpOperationResult,
                                    RuleOperationResult ruleOperationResult) {
        this.qpAndRuleOperationStatus = qpAndRuleOperationStatus;
        this.qpOperationResult = qpOperationResult;
        this.ruleOperationResul = ruleOperationResult;
    }

    public QPAndRuleOperationStatus getStatus() {
        return qpAndRuleOperationStatus;
    }

    public QPOperationResult getQPOperationResult() {
        return qpOperationResult;
    }

    public RuleOperationResult getRuleOperationResult() {
        return ruleOperationResul;
    }
}
