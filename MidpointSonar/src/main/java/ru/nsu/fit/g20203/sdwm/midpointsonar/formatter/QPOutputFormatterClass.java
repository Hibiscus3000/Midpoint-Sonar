package ru.nsu.fit.g20203.sdwm.midpointsonar.formatter;

import org.springframework.stereotype.Component;
import ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint.ServerTask;
import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.QualityProfile;
import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.Rule;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPAndRuleOperationResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.RuleOperationResult;

import java.util.Collection;

import static ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.RuleOperationResult.RuleOperationStatus.NO_SUCH_RULE;

@Component
public class QPOutputFormatterClass implements QPOutputFormatter{
    public String lsQP(QualityProfile qualityProfile) {
        if (null != qualityProfile) {
            StringBuilder formattedQp = new StringBuilder();
            String name = qualityProfile.getName();
            formattedQp.append("Quality profile with name \"").append(name).append("\" contains:\n");
            for (Rule rule : qualityProfile.getAllRules()) {
                formattedQp.append(formatRule(rule));
            }
            return formattedQp.toString();
        } else {
            return "No quality profile with given name exists\n";
        }
    }

    public String lsQPs(Collection<QualityProfile> qualityProfiles) {
        if (qualityProfiles.isEmpty()) {
            return "No quality profile has been created yet\n";
        }
        StringBuilder formattedQps = new StringBuilder("All quality profiles:\n");
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
        if (null != qpOperationResult.getQualityProfile()) {
            switch (qpOperationResult.getStatus()) {
                case NO_SUCH_QUALITY_PROFILE:
                    formattedQPOperationResult
                            .append("There is no Quality Profile with name \"")
                            .append(qpOperationResult.getQualityProfile().getName())
                            .append("\"\n");
                    break;
                case QP_WITH_GIVEN_NAME_ALREADY_EXISTS:
                    formattedQPOperationResult
                            .append("Quality Profile with name \"")
                            .append(qpOperationResult.getQualityProfile().getName())
                            .append("\" already exists.\n");
                    break;
                case SUCCESS:
                    formattedQPOperationResult
                            .append("Success on Quality Profile with name \"")
                            .append(qpOperationResult.getQualityProfile().getName())
                            .append("\"\n");
                    break;
            }
        }
        return formattedQPOperationResult.toString();
    }

    public String formatRuleOperationResult(RuleOperationResult ruleOperationResult) {
        StringBuilder formattedRuleOperationResult = new StringBuilder();
        if (null != ruleOperationResult.getRule()) {
            if (ruleOperationResult.getStatus() == NO_SUCH_RULE) {
                formattedRuleOperationResult
                        .append("There is no rule with name \"")
                        .append(ruleOperationResult.getRule().getName())
                        .append("\"");
            } else {
                formattedRuleOperationResult
                        .append("Success on \"")
                        .append(formatRule(ruleOperationResult.getRule()))
                        .append("\"");
            }
        }
        return formattedRuleOperationResult.toString();
    }

    public String formatQPAndRuleOpResult(QPAndRuleOperationResult qpAndRuleOperationResult) {
        StringBuilder formattedQpAndRuleOpResult = new StringBuilder();
        switch (qpAndRuleOperationResult.getStatus()) {
            case RULE_ALREADY_IN_QP:
                formattedQpAndRuleOpResult
                        .append(formatRule(qpAndRuleOperationResult.getRuleOperationResult().getRule()))
                        .append("already in Quality profile with name \"")
                        .append(qpAndRuleOperationResult.getQPOperationResult().getQualityProfile().getName())
                        .append("\"\n");
                break;
            case RULE_NOT_IN_QP:
                formattedQpAndRuleOpResult
                        .append(formatRule(qpAndRuleOperationResult.getRuleOperationResult().getRule()))
                        .append("not in Quality profile with name \"")
                        .append(qpAndRuleOperationResult.getQPOperationResult().getQualityProfile().getName())
                        .append("\"\n");
                break;
            case SUCCESS:
                formattedQpAndRuleOpResult
                        .append("Success on operation on ")
                        .append(formatRule(qpAndRuleOperationResult.getRuleOperationResult().getRule()))
                        .append("and Quality profile with name \"")
                        .append(qpAndRuleOperationResult.getQPOperationResult().getQualityProfile().getName())
                        .append("\"\n");
                break;
        }

        return formattedQpAndRuleOpResult.toString();
    }

    @Override
    public String formatRule(Rule rule) {
        if (null == rule) {
            return "";
        }
        StringBuilder formattedRule = new StringBuilder();
        String name = rule.getName();
        ServerTask task = rule.getServerTask();
        formattedRule
                .append("Rule with name \"")
                .append(name).append("\" with server task \"")
                .append(task)
                .append("\"\n");
        return formattedRule.toString();
    }
}
