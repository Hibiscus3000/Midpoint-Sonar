package ru.nsu.fit.g20203.sdwm.midpointsonar.formatter;

import org.springframework.stereotype.Component;
import ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint.MidPointSonarObject;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.Status;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.QPRunResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleLoadResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleRunResult;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult.QPOperationStatus.NO_SUCH_QUALITY_PROFILE;
import static ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.RuleOperationResult.RuleOperationStatus.NO_SUCH_RULE;
import static ru.nsu.fit.g20203.sdwm.midpointsonar.result.Status.SUCCESS;

@Component
public class TaskRunResultFormatterClass implements TaskRunResultFormatter {
    public String formatRuleLoadResult(RuleLoadResult ruleLoadResult) {
        StringBuilder formattedRuleLoadResult = new StringBuilder();

        if (!ruleLoadResult.getRuleLoadStatus().isDone()) {
            formattedRuleLoadResult.append("Rule with name ").append(ruleLoadResult.getRuleOperationResult().getRule().getName()).append("hasn't loaded yet.\n");
        } else if (ruleLoadResult.getRuleOperationResult().getStatus() == NO_SUCH_RULE) {
            formattedRuleLoadResult.append("There is no rule with name ").append(ruleLoadResult.getRuleOperationResult().getRule().getName()).append(".\n");
        } else {
            formattedRuleLoadResult.append("Rule named ").append(ruleLoadResult.getRuleOperationResult().getRule().getName()).append(" with server task ").append(ruleLoadResult.getRuleOperationResult().getRule().getServerTask());

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
    }

    @Override
    public String formatRuleLoadHistory(Collection<RuleLoadResult> ruleLoadResults) {
        StringBuilder ruleLoadHistoryBuilder = new StringBuilder("Load history:\n");
        for (RuleLoadResult ruleLoadResult : ruleLoadResults) {
            ruleLoadHistoryBuilder.append(formatRuleLoadResult(ruleLoadResult));
        }
        return ruleLoadHistoryBuilder.toString();
    }

    public String formatRuleRunResult(RuleRunResult ruleRunResult){
        StringBuilder formattedRuleRunResult = new StringBuilder();

        formattedRuleRunResult.append(formatRuleLoadResult(ruleRunResult.getRuleLoadResult()));

        if (ruleRunResult.getRuleOperationResult().getStatus() == NO_SUCH_RULE) {
            formattedRuleRunResult.append("Cannot be run.\n");
        } else {
            formattedRuleRunResult.append("Rule named ").append(ruleRunResult.getRuleOperationResult().getRule().getName()).append(" with server task ").append(ruleRunResult.getRuleOperationResult().getRule().getServerTask());

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
    }

    @Override
    public String formatRunHistory(Collection<QPRunResult> qpRunResults) {
        StringBuilder runHistoryBuilder = new StringBuilder("Run history:\n");
        for (QPRunResult qpRunResult : qpRunResults) {
            runHistoryBuilder.append(formatQPRunResult(qpRunResult));
        }
        return runHistoryBuilder.toString();
    }

    public String formatRuleLoadHistoryWId(Map<Integer, RuleLoadResult> ruleLoadResultMap) {
        StringBuilder ruleLoadHistoryBuilder = new StringBuilder("Load history:\n");
        for (Map.Entry<Integer, RuleLoadResult> e : ruleLoadResultMap.entrySet()) {
            ruleLoadHistoryBuilder.append(e.getKey()).append(": ").append(formatRuleLoadResult(e.getValue()));
        }
        return ruleLoadHistoryBuilder.toString();
    }

    public String formatRunHistoryWId(Map<Integer, QPRunResult> qpRunResultMap) {
        StringBuilder runHistoryBuilder = new StringBuilder("Run history:\n");
        for (Map.Entry<Integer, QPRunResult> e : qpRunResultMap.entrySet()) {
            runHistoryBuilder.append(e.getKey()).append(": ").append(formatQPRunResult(e.getValue()));
        }
        return runHistoryBuilder.toString();
    }

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
