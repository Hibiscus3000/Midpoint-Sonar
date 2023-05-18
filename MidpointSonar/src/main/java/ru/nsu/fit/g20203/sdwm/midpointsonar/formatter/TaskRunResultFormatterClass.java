package ru.nsu.fit.g20203.sdwm.midpointsonar.formatter;

import org.springframework.stereotype.Component;
import ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint.MidPointSonarObject;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.Status;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.QPRunResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleLoadResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleRunResult;

import java.util.concurrent.ExecutionException;

import static ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleLoadResult.RuleLoadStatus.*;
import static ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult.QPOperationStatus.NO_SUCH_QUALITY_PROFILE;
import static ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.RuleOperationResult.RuleOperationStatus.NO_SUCH_RULE;
import static ru.nsu.fit.g20203.sdwm.midpointsonar.result.Status.SUCCESS;

@Component
public abstract class TaskRunResultFormatterClass implements TaskRunResultFormatter {
    public String formatRuleLoadResult(RuleLoadResult ruleLoadResult) {
        StringBuilder formattedRuleLoadResult = new StringBuilder();

        if (!ruleLoadResult.getRuleLoadStatus().isDone()) {
            formattedRuleLoadResult.append("Rule with name " +
                    ruleLoadResult.getRuleOperationResult().getRule().getName() +
                    "hasn't loaded yet. ");
        } else if (ruleLoadResult.getRuleOperationResult().getStatus() == NO_SUCH_RULE) {
            formattedRuleLoadResult.append("There is no rule with name " +
                                            ruleLoadResult.getRuleOperationResult().getRule().getName() + ". ");
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
                    formattedRuleLoadResult.append(" doesn't need to be loaded. ");
                    break;
                case SUCCESS:
                    formattedRuleLoadResult.append(" loaded successfully. ");
                    break;
                case ERROR:
                    formattedRuleLoadResult.append(" failed to load. ");
                    break;
            }
        }

        return formattedRuleLoadResult.toString();
    };

    public String formatRuleRunResult(RuleRunResult ruleRunResult){
        StringBuilder formattedRuleRunResult = new StringBuilder();

        formattedRuleRunResult.append(formatRuleLoadResult(ruleRunResult.getRuleLoadResult()));

        if (ruleRunResult.getRuleOperationResult().getStatus() == NO_SUCH_RULE) {
            formattedRuleRunResult.append("Cannot be run. \n");
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
                formattedRuleRunResult.append(" ran successfully. \n");
            } else {
                formattedRuleRunResult.append(" failed to run. \n");
            }

            /*try {
                for (MidPointSonarObject midPointSonarObject: ruleRunResult.getObjects().get()) {
                    formatMidPointSonarObject(midPointSonarObject);
                }
            } catch (InterruptedException | ExecutionException ignore) {

            }*/
        }

        return formattedRuleRunResult.toString();
    }

    public String formatQPRunResult(QPRunResult qpRunResult) {
        StringBuilder formattedQPRunResult = new StringBuilder();

        if (qpRunResult.getQpOperationResult().getStatus() == NO_SUCH_QUALITY_PROFILE) {
            formattedQPRunResult.append("There is no Quality Profile with name " +
                                        qpRunResult.getQpOperationResult().getQualityProfile().getName() + ". \n");
        } else {
            formattedQPRunResult.append("Run results for Rules ih Quality Profile named " +
                                        qpRunResult.getQpOperationResult().getQualityProfile().getName() + ": \n");
            for (RuleRunResult ruleRunResult: qpRunResult.getRuleRunResults()) {
                formattedQPRunResult.append(formatRuleRunResult(ruleRunResult));
            }
        }

        return formattedQPRunResult.toString();
    };

    public abstract String formatMidPointSonarObject (MidPointSonarObject midPointSonarObject);
}
