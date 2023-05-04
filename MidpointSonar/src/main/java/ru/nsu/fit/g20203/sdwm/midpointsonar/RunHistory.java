package ru.nsu.fit.g20203.sdwm.midpointsonar;

import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.QPRunResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleLoadResult;

import java.util.Collection;

public interface RunHistory {

    QPRunResult getQPRunResult(Long runId);

    RuleLoadResult getLoadResult(Long loadId);

    Iterable<QPRunResult> getRunHistory();

    Iterable<RuleLoadResult> getLoadHistory();
}
