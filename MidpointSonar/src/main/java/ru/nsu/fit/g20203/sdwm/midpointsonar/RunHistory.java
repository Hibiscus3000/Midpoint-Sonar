package ru.nsu.fit.g20203.sdwm.midpointsonar;

import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.QPRunResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleLoadResult;


public interface RunHistory {

    QPRunResult getQPRunResult(Integer runId);

    RuleLoadResult getLoadResult(Integer loadId);

    Iterable<QPRunResult> getRunHistory();

    Iterable<RuleLoadResult> getLoadHistory();
}
