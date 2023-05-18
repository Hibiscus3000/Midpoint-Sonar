package ru.nsu.fit.g20203.sdwm.midpointsonar.formatter;

import ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint.MidPointSonarObject;
import ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint.MidPointSonarObjectClass;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.Status;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.QPRunResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleLoadResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleRunResult;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import static ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleLoadResult.RuleLoadStatus.*;
import static ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult.QPOperationStatus.NO_SUCH_QUALITY_PROFILE;
import static ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.RuleOperationResult.RuleOperationStatus.NO_SUCH_RULE;
import static ru.nsu.fit.g20203.sdwm.midpointsonar.result.Status.SUCCESS;

public class TaskRunResultFormatterClass implements TaskRunResultFormatter {
    public String formatRuleLoadResult(RuleLoadResult ruleLoadResult) {
        StringBuilder formattedRuleLoadResult = new StringBuilder();

        if (!ruleLoadResult.getRuleLoadStatus().isDone()) {
            formattedRuleLoadResult.append("Rule with name " +
                    ruleLoadResult.getRuleOperationResult().getRule().getName() +
                    "hasn't loaded yet.\n");
        } else if (ruleLoadResult.getRuleOperationResult().getStatus() == NO_SUCH_RULE) {
            formattedRuleLoadResult.append("There is no rule with name " +
                                            ruleLoadResult.getRuleOperationResult().getRule().getName() + ".\n");
        } else {
            formattedRuleLoadResult.append("Rule named "
                                            + ruleLoadResult.getRuleOperationResult().getRule().getName() +
                                            " with server task " +
                                            ruleLoadResult.getRuleOperationResult().getRule().getServerTask());

            RuleLoadResult.RuleLoadStatus ruleLoadStatus = null;
            try {
                ruleLoadStatus = ruleLoadResult.getRuleLoadStatus().get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }

            switch (ruleLoadStatus) {
                case NOT_NEEDED:
                    formattedRuleLoadResult.append(" doesn't need to be loaded.\n");
                    break;
                case SUCCESS:
                    formattedRuleLoadResult.append(" loaded successfully.\n");
                    break;
                case ERROR:
                    formattedRuleLoadResult.append(" failed to load.\n");
                    break;
            }
        }

        return formattedRuleLoadResult.toString();
    };

    public String formatRuleRunResult(RuleRunResult ruleRunResult){
        StringBuilder formattedRuleRunResult = new StringBuilder();

        formattedRuleRunResult.append(formatRuleLoadResult(ruleRunResult.getRuleLoadResult()));

        if (ruleRunResult.getRuleOperationResult().getStatus() == NO_SUCH_RULE) {
            formattedRuleRunResult.append("Cannot be run.\n");
        } else {
            formattedRuleRunResult.append("Rule named "
                    + ruleRunResult.getRuleOperationResult().getRule().getName() +
                    " with server task " +
                    ruleRunResult.getRuleOperationResult().getRule().getServerTask());

            Status ruleRunStatus = null;
            try {
                ruleRunStatus = ruleRunResult.getRuleRunStatus().get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            if (ruleRunStatus.equals(SUCCESS)) {
                formattedRuleRunResult.append(" ran successfully.\n");
            } else {
                formattedRuleRunResult.append(" failed to run.\n");
            }

            try {
                for (MidPointSonarObject midPointSonarObject: ruleRunResult.getObjects().get()) {
                    formattedRuleRunResult.append(formatMidPointSonarObject(midPointSonarObject));
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        return formattedRuleRunResult.toString();
    }

    public String formatQPRunResult(QPRunResult qpRunResult) {
        StringBuilder formattedQPRunResult = new StringBuilder();

        if (qpRunResult.getQpOperationResult().getStatus() == NO_SUCH_QUALITY_PROFILE) {
            formattedQPRunResult.append("There is no Quality Profile with name ").append(qpRunResult.getQpOperationResult().getQualityProfile().getName()).append(".\n");
        } else {
            formattedQPRunResult.append("Run results for Rules ih Quality Profile named ").append(qpRunResult.getQpOperationResult().getQualityProfile().getName()).append(":\n");
            for (RuleRunResult ruleRunResult: qpRunResult.getRuleRunResults()) {
                formattedQPRunResult.append(formatRuleRunResult(ruleRunResult));
            }
        }

        return formattedQPRunResult.toString();
    };

    public String formatMidPointSonarObject (MidPointSonarObject midPointSonarObject) {
        StringBuilder formattedMidPointSonarObject = new StringBuilder();

        for (Map.Entry<String, String> e : midPointSonarObject.getValues().entrySet()) {
            String key = e.getKey();
            String value = e.getValue();
            formattedMidPointSonarObject.append(key).append(' ').append(value).append('\n');
        }

        return formattedMidPointSonarObject.toString();
    }
}
