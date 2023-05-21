package ru.nsu.fit.g20203.sdwm.midpointsonar.runhistory;

import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.QPRunResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleLoadResult;

import java.util.Collection;


public interface RunHistory {

    QPRunResult getQPRunResult(Integer runId);

    RuleLoadResult getLoadResult(Integer loadId);

    Collection<QPRunResult> getRunHistory();

    Collection<RuleLoadResult> getLoadHistory();
}
