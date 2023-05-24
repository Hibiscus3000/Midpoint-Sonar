package ru.nsu.fit.g20203.sdwm.midpointsonar.formatter;

import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.QPRunResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleLoadResult;

import java.util.Collection;
import java.util.Map;

public interface TaskRunResultFormatter {

    String formatRuleLoadResult(RuleLoadResult ruleLoadResult);

    String formatRuleLoadHistory(Collection<RuleLoadResult> ruleLoadResults);

    String formatQPRunResult(QPRunResult qpRunResult);

    String formatRunHistory(Collection<QPRunResult> qpRunResults);

    String formatRuleLoadHistoryWId(Map<Integer, RuleLoadResult> ruleLoadResultMap);

    String formatRunHistoryWId(Map<Integer, QPRunResult> qpRunResultMap);
}
