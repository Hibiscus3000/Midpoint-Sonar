package ru.nsu.fit.g20203.sdwm.midpointsonar.runhistory;

import ru.nsu.fit.g20203.sdwm.midpointsonar.entity.QPRunResultEntity;
import ru.nsu.fit.g20203.sdwm.midpointsonar.entity.RuleLoadResultEntity;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.QPRunResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleLoadResult;

import java.util.Collection;
import java.util.Map;


public interface RunHistory {

    QPRunResult getQPRunResult(Integer runId);

    RuleLoadResult getLoadResult(Integer loadId);

    Collection<QPRunResult> getRunHistory();

    Collection<RuleLoadResult> getLoadHistory();

    Map<Integer, QPRunResult> getQpRunResultMap();

    QPRunResultEntity saveQpRunResult(QPRunResult qpRunResult);

    RuleLoadResultEntity saveRuleLoadResult(RuleLoadResult result);
    void deleteAll();
}
