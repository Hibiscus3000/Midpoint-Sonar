package ru.nsu.fit.g20203.sdwm.midpointsonar.formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint.MidPointSonarObject;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.Status;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.QPRunResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleLoadResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleRunResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.RuleOperationResult;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult.QPOperationStatus.NO_SUCH_QUALITY_PROFILE;
import static ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.RuleOperationResult.RuleOperationStatus.NO_SUCH_RULE;

@Component
public class TaskRunResultFormatterClass implements TaskRunResultFormatter {

    @Autowired
    private QPOutputFormatter qpOutputFormatter;

    public String formatRuleLoadResult(RuleLoadResult ruleLoadResult) {
//        StringBuilder formattedRuleLoadResult = new StringBuilder();
//
//        if (!ruleLoadResult.getRuleLoadStatus().isDone()) {
//            formattedRuleLoadResult.append(qpOutputFormatter.formatRule(ruleLoadResult.getRuleOperationResult().getRule())).append("hasn't loaded yet.\n");
//        } else {
//            formattedRuleLoadResult.append(qpOutputFormatter.formatRuleOperationResult(ruleLoadResult.getRuleOperationResult()));
//
//            RuleLoadResult.RuleLoadStatus ruleLoadStatus = null;
//            try {
//                ruleLoadStatus = ruleLoadResult.getRuleLoadStatus().get();
//            } catch (InterruptedException | ExecutionException e) {
//                throw new RuntimeException(e);
//            }
//
//            switch (ruleLoadStatus) {
//                case NOT_NEEDED:
//                    formattedRuleLoadResult.append(" doesn't need to be loaded.\n");
//                    break;
//                case SUCCESS:
//                    formattedRuleLoadResult.append(" loaded successfully.\n");
//                    break;
//                case ERROR:
//                    formattedRuleLoadResult.append(" failed to load.\n");
//                    break;
//            }
//        }
//
//        return formattedRuleLoadResult.toString();
        return "";
    }

    @Override
    public String formatRuleLoadHistory(Collection<RuleLoadResult> ruleLoadResults) {
        StringBuilder ruleLoadHistoryBuilder = new StringBuilder("Load history:\n");
        for (RuleLoadResult ruleLoadResult : ruleLoadResults) {
            ruleLoadHistoryBuilder.append(formatRuleLoadResult(ruleLoadResult));
        }
        return ruleLoadHistoryBuilder.toString();
    }

    @Override
    public String formatRuleRunResult(RuleRunResult ruleRunResult) {
        StringBuilder formattedRuleRunResult = new StringBuilder();

        formattedRuleRunResult.append(formatRuleLoadResult(ruleRunResult.getRuleLoadResult()));

        if (ruleRunResult.getRuleOperationResult().getStatus() == NO_SUCH_RULE) {
            formattedRuleRunResult.append("No such rule.\n");
        } else {
            formattedRuleRunResult.append(qpOutputFormatter.formatRule(ruleRunResult.getRuleOperationResult().getRule()));

            try {
                formattedRuleRunResult.append(formatFutureStatus(ruleRunResult.getRuleRunStatus()));
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            try {
                int i = 1;
                for (MidPointSonarObject midPointSonarObject : ruleRunResult.getObjects().get()) {
                    formattedRuleRunResult
                            .append(i++)
                            .append(") ")
                            .append(formatMidPointSonarObject(midPointSonarObject));
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        return formattedRuleRunResult.toString();
    }

    public String formatQPRunResult(QPRunResult qpRunResult) {
        StringBuilder formattedQPRunResult = new StringBuilder();

        QPOperationResult qpOperationResult = qpRunResult.getQpOperationResult();
        if (null != qpOperationResult) {
            if (qpOperationResult.getStatus() == NO_SUCH_QUALITY_PROFILE) {
                formattedQPRunResult.append("There is no Quality Profile with name ").append(qpOperationResult.getQualityProfile().getName()).append(".\n");
            } else {
                formattedQPRunResult.append("Run results for Rules ih Quality Profile named ").append(qpOperationResult.getQualityProfile().getName()).append(":\n");
                for (RuleRunResult ruleRunResult : qpRunResult.getRuleRunResults()) {
                    formattedQPRunResult.append(formatRuleRunResult(ruleRunResult));
                }
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

    public String formatMidPointSonarObject(MidPointSonarObject midPointSonarObject) {
        StringBuilder formattedMidPointSonarObject = new StringBuilder();

        for (Map.Entry<String, String> e : midPointSonarObject.getValues().entrySet()) {
            String key = e.getKey();
            String value = e.getValue();
            formattedMidPointSonarObject.append(key).append(": ").append(value).append('\n');
        }

        return formattedMidPointSonarObject.toString();
    }

    private String formatRuleOperationResult(RuleOperationResult ruleOperationResult) {
        return new StringBuilder(qpOutputFormatter.formatRule(ruleOperationResult.getRule()))
                .append(" - ")
                .append(formatRuleOperationStatus(ruleOperationResult.getStatus())).toString();
    }

    private String formatRuleOperationStatus(RuleOperationResult.RuleOperationStatus operationStatus) {
        switch (operationStatus) {
            case NO_SUCH_RULE -> {
                return "No such rule";
            }
            case SUCCESS -> {
                return "Success";
            }
            default -> throw new IllegalArgumentException();
        }
    }

    private String formatFutureStatus(Future<Status> future) throws ExecutionException, InterruptedException {
        if (null == future) {
            return "";
        }
        if (!future.isDone()) {
            return "Status has not been filled yet\n";
        }
        return formatStatus(future.get());
    }

    private String formatStatus(Status status) {
        if (null == status) {
            return "";
        }
        switch (status) {
            case SUCCESS -> {
                return "Success\n";
            }
            case ERROR -> {
                return "Error\n";
            }
            default -> {
                throw new IllegalArgumentException();
            }
        }
    }
}
