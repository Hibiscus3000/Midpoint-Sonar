package ru.nsu.fit.g20203.sdwm.midpointsonar.runhistory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.fit.g20203.sdwm.midpointsonar.runhistory.RunHistory;
import ru.nsu.fit.g20203.sdwm.midpointsonar.entity.QPRunResultEntity;
import ru.nsu.fit.g20203.sdwm.midpointsonar.entity.RuleLoadResultEntity;
import ru.nsu.fit.g20203.sdwm.midpointsonar.mapper.QPRunResultMapper;
import ru.nsu.fit.g20203.sdwm.midpointsonar.mapper.RuleLoadResultMapper;
import ru.nsu.fit.g20203.sdwm.midpointsonar.repositories.QPRunResultRepository;
import ru.nsu.fit.g20203.sdwm.midpointsonar.repositories.RuleLoadResultRepo;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.QPRunResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleLoadResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RunHistoryImpl implements RunHistory {
    private final QPRunResultRepository qpRunResultRepo;
    private final QPRunResultMapper qpRunResultMapper;
    private final RuleLoadResultRepo ruleLoadResultRepo;
    private final RuleLoadResultMapper ruleLoadResultMapper;

    @Override
    public QPRunResult getQPRunResult(Integer runId) {
        Optional<QPRunResultEntity> result = qpRunResultRepo.findById(runId);
        return result.map(qpRunResultMapper::map).orElse(null);
    }

    @Override
    public RuleLoadResult getLoadResult(Integer loadId) {
        Optional<RuleLoadResultEntity> result = ruleLoadResultRepo.findById(loadId);

        return result.map(ruleLoadResultMapper::map).orElse(null);
    }

    @Override
    public Collection<QPRunResult> getRunHistory() {
        var qps =  qpRunResultRepo.findAll();
        List<QPRunResult> results = new ArrayList<>();
        for(var it : qps){
            results.add(qpRunResultMapper.map(it));
        }
        return results;
    }

    @Override
    public Collection<RuleLoadResult> getLoadHistory() {
        var entities =  ruleLoadResultRepo.findAll();
        List<RuleLoadResult> results = new ArrayList<>();
        for(var it : entities){
            results.add(ruleLoadResultMapper.map(it));
        }
        return results;
    }
}
