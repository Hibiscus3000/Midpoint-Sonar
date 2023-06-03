package ru.nsu.fit.g20203.sdwm.midpointsonar.runhistory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.fit.g20203.sdwm.midpointsonar.entity.QPRunResultEntity;
import ru.nsu.fit.g20203.sdwm.midpointsonar.entity.RuleLoadResultEntity;
import ru.nsu.fit.g20203.sdwm.midpointsonar.mapper.QPRunResultMapper;
import ru.nsu.fit.g20203.sdwm.midpointsonar.mapper.RuleLoadResultMapper;
import ru.nsu.fit.g20203.sdwm.midpointsonar.repositories.QPRunResultRepository;
import ru.nsu.fit.g20203.sdwm.midpointsonar.repositories.RuleLoadResultRepository;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.QPRunResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleLoadResult;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RunHistoryImpl implements RunHistory {
    private final QPRunResultRepository qpRunResultRepo;
    private final QPRunResultMapper qpRunResultMapper;
    private final RuleLoadResultRepository ruleLoadResultRepo;
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
    public Map<Integer, QPRunResult> getRunHistory() {
        return qpRunResultRepo.findAll().stream().collect(Collectors.toMap(qpRunResultEntity -> qpRunResultEntity.getId(),
                qpRunResultEntity -> qpRunResultMapper.map(qpRunResultEntity)));
    }

    @Override
    public Map<Integer, RuleLoadResult> getLoadHistory() {
        return ruleLoadResultRepo.findAll().stream().collect(Collectors.toMap(ruleLoadResultEntity -> ruleLoadResultEntity.getId(),
                ruleLoadResultEntity -> ruleLoadResultMapper.map(ruleLoadResultEntity)));
    }

    @Override
    public QPRunResultEntity saveQpRunResult(QPRunResult qpRunResult) {
        return qpRunResultRepo.save(qpRunResultMapper.map(qpRunResult));
    }

    @Override
    public RuleLoadResultEntity saveRuleLoadResult(RuleLoadResult result) {
        return ruleLoadResultRepo.save(ruleLoadResultMapper.map(result));
    }

    @Override
    public void clearRunHistory() {
        qpRunResultRepo.deleteAll();
    }

    @Override
    public void clearLoadHistory() {
        ruleLoadResultRepo.deleteAll();
    }
}
