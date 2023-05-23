package ru.nsu.fit.g20203.sdwm.midpointsonar.runhistory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.fit.g20203.sdwm.midpointsonar.entity.RuleRunResultEntity;
import ru.nsu.fit.g20203.sdwm.midpointsonar.mapper.QualityProfileMapper;
import ru.nsu.fit.g20203.sdwm.midpointsonar.mapper.RuleRunResultMapper;
import ru.nsu.fit.g20203.sdwm.midpointsonar.repositories.RuleRunResultRepo;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.Status;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleRunResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.runhistory.RunHistory;
import ru.nsu.fit.g20203.sdwm.midpointsonar.entity.QPRunResultEntity;
import ru.nsu.fit.g20203.sdwm.midpointsonar.entity.RuleLoadResultEntity;
import ru.nsu.fit.g20203.sdwm.midpointsonar.mapper.QPRunResultMapper;
import ru.nsu.fit.g20203.sdwm.midpointsonar.mapper.RuleLoadResultMapper;
import ru.nsu.fit.g20203.sdwm.midpointsonar.repositories.QPRunResultRepository;
import ru.nsu.fit.g20203.sdwm.midpointsonar.repositories.RuleLoadResultRepo;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.QPRunResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleLoadResult;

import java.util.*;

@Component
@RequiredArgsConstructor
public class RunHistoryImpl implements RunHistory {
    private final QPRunResultRepository qpRunResultRepo;
    private final QPRunResultMapper qpRunResultMapper;
    private final RuleLoadResultRepo ruleLoadResultRepo;
    private final RuleLoadResultMapper ruleLoadResultMapper;
    private final RuleRunResultRepo ruleRunResultRepo;
    private final RuleRunResultMapper ruleRunResultMapper;
    private final QualityProfileMapper qualityProfileMapper;
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
    @Override
    public Map<Integer, QPRunResult> getQpRunResultMap(){
        List<QPRunResultEntity> qpRunResultEntityList = qpRunResultRepo.findAll();
        Map<Integer, QPRunResult> qpRunResultMap = new HashMap<>();
        for(var it : qpRunResultEntityList){
            List<RuleRunResultEntity> results = ruleRunResultRepo.findAll();
            List<RuleRunResult> ruleRunResults = new ArrayList<>();

            for(var rule : results){
                ruleRunResults.add(ruleRunResultMapper.map(rule));
            }


            QPOperationResult.QPOperationStatus status;
            String stringStatus = it.getQpOperationResultEntity().getStatus();
            if(stringStatus.equals("SUCCESS")){
                status = QPOperationResult.QPOperationStatus.SUCCESS;
            } else if (stringStatus.equals("NO SUCH QUALITY PROFILE")) {
                status = QPOperationResult.QPOperationStatus.NO_SUCH_QUALITY_PROFILE;
            }else {
                status = QPOperationResult.QPOperationStatus.QP_WITH_GIVEN_NAME_ALREADY_EXISTS;
            }
            qpRunResultMap.put(it.getQpRunResult(), new QPRunResult(it.getQpRunResult().longValue(),
                    ruleRunResults
                    ,new QPOperationResult(status,
                        qualityProfileMapper.map(
                                it.getQpOperationResultEntity().getQualityProfileEntity()))));

        }
        return qpRunResultMap;
    }

    @Override
    public QPRunResultEntity saveQpRunResult(QPRunResult qpRunResult){
        return qpRunResultRepo.save(qpRunResultMapper.map(qpRunResult));
    }

    @Override
    public RuleLoadResultEntity saveRuleLoadResult(RuleLoadResult result){
        return ruleLoadResultRepo.save(ruleLoadResultMapper.map(result));
    }
    @Override
    public void deleteAll(){
        qpRunResultRepo.deleteAll();
        ruleLoadResultRepo.deleteAll();
    };
}
