package ru.nsu.fit.g20203.sdwm.midpointsonar.runhistory;

import ru.nsu.fit.g20203.sdwm.midpointsonar.entity.QPRunResultEntity;
import ru.nsu.fit.g20203.sdwm.midpointsonar.entity.RuleLoadResultEntity;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.QPRunResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleLoadResult;

import java.util.Map;


public interface RunHistory {

    QPRunResult getQPRunResult(Integer runId);

    RuleLoadResult getLoadResult(Integer loadId);

    Map<Integer, QPRunResult> getRunHistory();

    Map<Integer, RuleLoadResult> getLoadHistory();

    QPRunResultEntity saveQpRunResult(QPRunResult qpRunResult);

    RuleLoadResultEntity saveRuleLoadResult(RuleLoadResult result);

    void clearRunHistory();

    void clearLoadHistory();
}
