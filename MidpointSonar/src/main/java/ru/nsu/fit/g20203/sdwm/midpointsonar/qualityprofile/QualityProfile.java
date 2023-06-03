package ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile;

import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.RuleOperationResult;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class QualityProfile {

    private String name;
    private final Map<String, Rule> rules = new HashMap<>();

    public QualityProfile(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RuleOperationResult addRule(Rule rule) {
        if (null == rule) {
            return new RuleOperationResult(RuleOperationResult.RuleOperationStatus.NO_SUCH_RULE, null);
        }
        rules.put(rule.getName(), rule);
        return new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS, rule);
    }

    public RuleOperationResult removeRule(String ruleName) {
        Rule rule;
        if (null != (rule = rules.remove(ruleName))) {
            return new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS, rule);
        } else {
            return new RuleOperationResult(RuleOperationResult.RuleOperationStatus.NO_SUCH_RULE, null);
        }
    }

    public Collection<Rule> getAllRules() {
        return rules.values();
    }
}