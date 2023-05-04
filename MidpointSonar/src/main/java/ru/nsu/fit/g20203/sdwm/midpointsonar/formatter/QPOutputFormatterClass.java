package ru.nsu.fit.g20203.sdwm.midpointsonar.formatter;

import ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint.ServerTask;
import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.QualityProfile;
import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.Rule;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPAndRuleOperationResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.RuleOperationResult;

import java.util.Collection;

import static ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPAndRuleOperationResult.QPAndRuleOperationStatus.RULE_ALREADY_IN_QP;
import static ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPAndRuleOperationResult.QPAndRuleOperationStatus.RULE_NOT_IN_QP;
import static ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult.QPOperationStatus.NO_SUCH_QUALITY_PROFILE;
import static ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult.QPOperationStatus.QP_WITH_GIVEN_NAME_ALREADY_EXISTS;
import static ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.RuleOperationResult.RuleOperationStatus.NO_SUCH_RULE;

public class QPOutputFormatterClass implements QPOutputFormatter{
    public String lsQP(QualityProfile qualityProfile) {
        StringBuilder formattedQp = new StringBuilder();
        String name = qualityProfile.getName();
        formattedQp.append("Quality profile with name " + name + " contains:\n");
        for (Rule rule: qualityProfile.getAllRules()) {
            formattedQp.append(formatRule(rule));
        }
        return formattedQp.toString();
    }

    public String lsQPs(Collection<QualityProfile> qualityProfiles) {
        StringBuilder formattedQps = new StringBuilder();
        for (QualityProfile qualityProfile: qualityProfiles) {
            formattedQps.append(lsQP(qualityProfile));
        }
        return formattedQps.toString();
    }

    public String lsRules(Collection<Rule> rules) {
        StringBuilder formattedRules = new StringBuilder();
        for (Rule rule: rules) {
            formattedRules.append(formatRule(rule));
        }
        return formattedRules.toString();
    }

    public String formatQPOperationResult(QPOperationResult qpOperationResult) {
        StringBuilder formattedQPOperationResult = new StringBuilder();
        switch (qpOperationResult.getStatus()) {
            case NO_SUCH_QUALITY_PROFILE:
                formattedQPOperationResult.append("There is no Quality Profile with name " +
                        qpOperationResult.getQualityProfile().getName() + "\n");
                break;
            case QP_WITH_GIVEN_NAME_ALREADY_EXISTS:
                formattedQPOperationResult.append("Quality Profile with name " +
                        qpOperationResult.getQualityProfile().getName() + " already exists.\n");
                break;
            case SUCCESS:
                formattedQPOperationResult.append("Success on Quality Profile with name " +
                        qpOperationResult.getQualityProfile().getName() + "\n");
                break;
        }
        return formattedQPOperationResult.toString();
    }

    public String formatRuleOperationResult(RuleOperationResult ruleOperationResult) {
        StringBuilder formattedRuleOperationResult = new StringBuilder();
        if (ruleOperationResult.getStatus() == NO_SUCH_RULE) {
            formattedRuleOperationResult.append("There is no rule with name " +
                    ruleOperationResult.getRule().getName());
        } else {
            formattedRuleOperationResult.append("Success on " + formatRule(ruleOperationResult.getRule()));
        }
        return formattedRuleOperationResult.toString();
    }

    public String formatQpAndRuleOpResult(QPAndRuleOperationResult qpAndRuleOperationResult) {
        StringBuilder formattedQpAndRuleOpResult = new StringBuilder();
        switch (qpAndRuleOperationResult.getStatus()) {
            case RULE_ALREADY_IN_QP:
                formattedQpAndRuleOpResult.append(formatRule(qpAndRuleOperationResult.getRuleOperationResult().getRule()) +
                        "already in Quality profile with name " +
                        qpAndRuleOperationResult.getQPOperationResult().getQualityProfile().getName() + "\n");
                break;
            case RULE_NOT_IN_QP:
                formattedQpAndRuleOpResult.append(formatRule(qpAndRuleOperationResult.getRuleOperationResult().getRule()) +
                        "not in Quality profile with name " +
                        qpAndRuleOperationResult.getQPOperationResult().getQualityProfile().getName() + "\n");
                break;
            case SUCCESS:
                formattedQpAndRuleOpResult.append("Success on operation on " +
                        formatRule(qpAndRuleOperationResult.getRuleOperationResult().getRule()) +
                        "and Quality profile with name " +
                        qpAndRuleOperationResult.getQPOperationResult().getQualityProfile().getName() + "\n");
                break;
        }

        return formattedQpAndRuleOpResult.toString();
    }

    public String formatRule(Rule rule) {
        StringBuilder formattedRule = new StringBuilder();
        String name = rule.getName();
        ServerTask task = rule.getServerTask();
        formattedRule.append("Rule with name " + name + " with server task " + task + "\n");
        return formattedRule.toString();
    }
}
