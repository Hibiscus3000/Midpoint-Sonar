package ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync;

import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.Rule;

public class RuleOperationResult {

    public enum RuleOperationStatus {
        NO_SUCH_RULE,
        SUCCESS
    }

    private final RuleOperationStatus ruleOperationStatus;
    private final Rule rule;

    public RuleOperationResult(RuleOperationStatus ruleOperationStatus,
                               Rule rule) {
        this.ruleOperationStatus = ruleOperationStatus;
        this.rule = rule;
    }

    public RuleOperationStatus getStatus() {
        return ruleOperationStatus;
    }

    public Rule getRule() {
        return rule;
    }

}
