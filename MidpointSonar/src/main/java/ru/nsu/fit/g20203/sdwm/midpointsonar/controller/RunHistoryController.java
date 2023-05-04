package ru.nsu.fit.g20203.sdwm.midpointsonar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.g20203.sdwm.midpointsonar.Repositories.QPRunResultRepository;
import ru.nsu.fit.g20203.sdwm.midpointsonar.Repositories.RuleLoadResultRepository;
import ru.nsu.fit.g20203.sdwm.midpointsonar.RunHistory;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.QPRunResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleLoadResult;
import java.util.Optional;

@RestController
@RequestMapping("/runhistory")
public class RunHistoryController implements RunHistory {
    private final QPRunResultRepository qpRunResultRepo;
    private final RuleLoadResultRepository ruleLoadResultRepo;

    @Autowired
    public RunHistoryController(QPRunResultRepository qpRepo, RuleLoadResultRepository ruleRepo){
        this.qpRunResultRepo = qpRepo;
        this.ruleLoadResultRepo = ruleRepo;
    }

    @Override
    @GetMapping("/qp")
    public QPRunResult getQPRunResult(@RequestParam Long runId) {
        Optional<QPRunResult> result = qpRunResultRepo.findById(runId);
        return result.orElse(null);
    }

    @Override
    @GetMapping("/rule")
    public RuleLoadResult getLoadResult(Long loadId) {
        Optional<RuleLoadResult> result = ruleLoadResultRepo.findById(loadId);
        return result.orElse(null);
    }

    @Override
    @GetMapping("allqps")
    public Iterable<QPRunResult> getRunHistory() {
        return qpRunResultRepo.findAll();
    }

    @Override
    @GetMapping("allrules")
    public Iterable<RuleLoadResult> getLoadHistory() {
        return ruleLoadResultRepo.findAll();
    }
}
