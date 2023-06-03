package ru.nsu.fit.g20203.sdwm.midpointsonar.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.g20203.sdwm.midpointsonar.formatter.TaskRunResultFormatter;
import ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint.QPRunner;
import ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint.RuleRunner;
import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.RulesAndQPManager;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.QPRunResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleRunResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.runhistory.RunHistory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/run/", produces = MediaType.TEXT_PLAIN_VALUE)
@Tag(name = "Run controller", description = "Запуск профилей качеств и правил")
public class RunController {

    @Autowired
    private QPRunner qpRunner;
    @Autowired
    private RulesAndQPManager rulesAndQPManager;
    @Autowired
    private TaskRunResultFormatter taskRunResultFormatter;
    @Autowired
    private RunHistory runHistory;

    @PutMapping("qp")
    public String runQP(@RequestParam String name) {
        final QPRunResult result =
                qpRunner.runQP(rulesAndQPManager.getQualityProfile(name));
        procQPRunResult(result);
        return taskRunResultFormatter.formatQPRunResult(result);
    }

    @PutMapping("rule")
    public String runRule(@RequestParam String name) {
        final RuleRunResult result = RuleRunner.getInstance().runRule(
                rulesAndQPManager.getRule(name));
        procQPRunResult(wrapInQPRunResult(result));
        return taskRunResultFormatter.formatRuleRunResult(result);
    }

    private QPRunResult wrapInQPRunResult(RuleRunResult ruleRunResult) {
        final List<RuleRunResult> ruleRunResultList = new ArrayList<>();
        ruleRunResultList.add(ruleRunResult);
        return new QPRunResult(ruleRunResultList,
                new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS, null));
    }

    private void procQPRunResult(QPRunResult qpRunResult) {
        runHistory.saveQpRunResult(qpRunResult);
        List<RuleRunResult> ruleRunResults = qpRunResult.getRuleRunResults().stream().toList();
        int numberOfRules = ruleRunResults.size();
        final CompletableFuture futures[] = new CompletableFuture[numberOfRules];
        for (int i = 0; i < numberOfRules; ++i) {
            futures[i] = ruleRunResults.get(i).getObjects();
        }
        CompletableFuture<Void> combined = CompletableFuture.allOf(futures);
        combined.thenRunAsync(() -> runHistory.saveQpRunResult(qpRunResult));
    }
}
