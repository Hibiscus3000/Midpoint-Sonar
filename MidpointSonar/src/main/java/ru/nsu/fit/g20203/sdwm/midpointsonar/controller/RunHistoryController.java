package ru.nsu.fit.g20203.sdwm.midpointsonar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.g20203.sdwm.midpointsonar.RunHistory;
import ru.nsu.fit.g20203.sdwm.midpointsonar.entity.QPRunResultEntity;
import ru.nsu.fit.g20203.sdwm.midpointsonar.entity.RuleLoadResultEntity;
import ru.nsu.fit.g20203.sdwm.midpointsonar.mapper.QPRunResultMapper;
import ru.nsu.fit.g20203.sdwm.midpointsonar.mapper.RuleLoadResultMapper;
import ru.nsu.fit.g20203.sdwm.midpointsonar.repositories.QPRunResultRepository;
import ru.nsu.fit.g20203.sdwm.midpointsonar.repositories.RuleLoadResultRepo;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.QPRunResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleLoadResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/runhistory")
@RequiredArgsConstructor
public class RunHistoryController implements RunHistory {
    private final QPRunResultRepository qpRunResultRepo;
    private final QPRunResultMapper qpRunResultMapper;
    private final RuleLoadResultRepo ruleLoadResultRepo;
    private final RuleLoadResultMapper ruleLoadResultMapper;

    @Override
    @GetMapping("/qp")
    public QPRunResult getQPRunResult(@RequestParam Integer runId) {
        Optional<QPRunResultEntity> result = qpRunResultRepo.findById(runId);
        return result.map(qpRunResultMapper::map).orElse(null);
    }

    @Override
    @GetMapping("/rule")
    public RuleLoadResult getLoadResult(Integer loadId) {
        Optional<RuleLoadResultEntity> result = ruleLoadResultRepo.findById(loadId);

        return result.map(ruleLoadResultMapper::map).orElse(null);
    }

    @Override
    @GetMapping("allqps")
    public Iterable<QPRunResult> getRunHistory() {
        var qps =  qpRunResultRepo.findAll();
        List<QPRunResult> results = new ArrayList<>();
        for(var it : qps){
            results.add(qpRunResultMapper.map(it));
        }
        return results;
    }

    @Override
    @GetMapping("allrules")
    public Iterable<RuleLoadResult> getLoadHistory() {
        var entities =  ruleLoadResultRepo.findAll();
        List<RuleLoadResult> results = new ArrayList<>();
        for(var it : entities){
            results.add(ruleLoadResultMapper.map(it));
        }
        return results;
    }
}
