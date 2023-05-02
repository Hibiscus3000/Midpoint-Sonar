package ru.nsu.fit.g20203.sdwm.midpointsonar.formatter;

import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.QPRunResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleLoadResult;

public interface TaskRunResultFormatter {

    String formatRuleLoadResult(RuleLoadResult ruleLoadResult);

    String formatQPRunResult(QPRunResult qpRunResult);
}
