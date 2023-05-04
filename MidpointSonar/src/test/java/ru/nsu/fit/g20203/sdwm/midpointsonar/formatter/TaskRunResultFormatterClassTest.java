package ru.nsu.fit.g20203.sdwm.midpointsonar.formatter;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint.MidPointSonarObject;
import ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint.ServerTask;
import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.QualityProfile;
import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.Rule;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.Status;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.QPRunResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleLoadResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleRunResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.RuleOperationResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleLoadResult.RuleLoadStatus.ERROR;
import static ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleLoadResult.RuleLoadStatus.SUCCESS;

class TaskRunResultFormatterClassTest {

    @Test
    void formatRuleLoadResultNoSuch() {
        Rule rule = new Rule("example") {
            @Override
            public ServerTask getServerTask() {
                return null;
            }
        };
        rule.setName("Name");
        RuleOperationResult ruleOperationResult = new RuleOperationResult(RuleOperationResult.RuleOperationStatus.NO_SUCH_RULE, rule);
        Future<RuleLoadResult.RuleLoadStatus> ruleLoadStatus = CompletableFuture.completedFuture(ERROR);
        RuleLoadResult ruleLoadResult = new RuleLoadResult(Long.valueOf(1), ruleLoadStatus);
        TaskRunResultFormatterClass taskRunResultFormatterClass = new TaskRunResultFormatterClass() {
            @Override
            public String formatMidPointSonarObject(MidPointSonarObject midPointSonarObject) {
                return null;
            }
        };
        ruleLoadResult.setRuleOperationResult(ruleOperationResult);

        String expected = "There is no rule with name Name. ";
        String actual = taskRunResultFormatterClass.formatRuleLoadResult(ruleLoadResult);

        assertTrue(actual.equals(expected));
    }

    @Test
    void formatRuleLoadResultSuccess() throws ExecutionException, InterruptedException {
        Rule rule = new Rule("example") {
            @Override
            public ServerTask getServerTask() {
                return null;
            }
        };
        rule.setName("Name");
        RuleOperationResult ruleOperationResult = new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS, rule);
        Future<RuleLoadResult.RuleLoadStatus> ruleLoadStatus = CompletableFuture.completedFuture(SUCCESS);
        RuleLoadResult ruleLoadResult = new RuleLoadResult(Long.valueOf(1), ruleLoadStatus);
        TaskRunResultFormatterClass taskRunResultFormatterClass = new TaskRunResultFormatterClass() {
            @Override
            public String formatMidPointSonarObject(MidPointSonarObject midPointSonarObject) {
                return null;
            }
        };
        ruleLoadResult.setRuleOperationResult(ruleOperationResult);

        String expected = "Rule named Name with server task null loaded successfully. ";
        String actual = taskRunResultFormatterClass.formatRuleLoadResult(ruleLoadResult);

        assertTrue(actual.equals(expected));
    }

    @Test
    void formatRuleRunResultNoSuch() {
        Rule rule = new Rule("example") {
            @Override
            public ServerTask getServerTask() {
                return null;
            }
        };
        rule.setName("Name");
        RuleOperationResult ruleOperationResult = new RuleOperationResult(RuleOperationResult.RuleOperationStatus.NO_SUCH_RULE, rule);
        Future<RuleLoadResult.RuleLoadStatus> ruleLoadStatus = CompletableFuture.completedFuture(SUCCESS);
        RuleLoadResult ruleLoadResult = new RuleLoadResult(Long.valueOf(1), ruleLoadStatus);
        TaskRunResultFormatterClass taskRunResultFormatterClass = new TaskRunResultFormatterClass() {
            @Override
            public String formatMidPointSonarObject(MidPointSonarObject midPointSonarObject) {
                return null;
            }
        };
        ruleLoadResult.setRuleOperationResult(ruleOperationResult);
        Future<Status> ruleRunStatus = CompletableFuture.completedFuture(Status.SUCCESS);
        Collection<MidPointSonarObject> midPointSonarObjectCollection = null;
        Future<Collection<MidPointSonarObject>> objects = CompletableFuture.completedFuture(midPointSonarObjectCollection);
        RuleRunResult ruleRunResult = new RuleRunResult(Long.valueOf(1), ruleLoadResult, ruleRunStatus, objects);
        ruleRunResult.setRuleOperationResult(ruleOperationResult);

        String expected = "There is no rule with name Name. Cannot be run. \n";
        String actual = taskRunResultFormatterClass.formatRuleRunResult(ruleRunResult);

        assertTrue(actual.equals(expected));
    }

    @Test
    void formatRuleRunResultSuccess() {
        Rule rule = new Rule("example") {
            @Override
            public ServerTask getServerTask() {
                return null;
            }
        };
        rule.setName("Name");
        RuleOperationResult ruleOperationResult = new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS, rule);
        Future<RuleLoadResult.RuleLoadStatus> ruleLoadStatus = CompletableFuture.completedFuture(SUCCESS);
        RuleLoadResult ruleLoadResult = new RuleLoadResult(Long.valueOf(1), ruleLoadStatus);
        TaskRunResultFormatterClass taskRunResultFormatterClass = new TaskRunResultFormatterClass() {
            @Override
            public String formatMidPointSonarObject(MidPointSonarObject midPointSonarObject) {
                return null;
            }
        };
        ruleLoadResult.setRuleOperationResult(ruleOperationResult);
        Future<Status> ruleRunStatus = CompletableFuture.completedFuture(Status.SUCCESS);
        Collection<MidPointSonarObject> midPointSonarObjectCollection = null;
        Future<Collection<MidPointSonarObject>> objects = CompletableFuture.completedFuture(midPointSonarObjectCollection);
        RuleRunResult ruleRunResult = new RuleRunResult(Long.valueOf(1), ruleLoadResult, ruleRunStatus, objects);
        ruleRunResult.setRuleOperationResult(ruleOperationResult);

        String expected = "Rule named Name with server task null loaded successfully. Rule named Name with server task null ran successfully. \n";
        String actual = taskRunResultFormatterClass.formatRuleRunResult(ruleRunResult);

        assertTrue(actual.equals(expected));
    }

    @Test
    void formatQPRunResultSuccess() {
        Rule rule = new Rule("example") {
            @Override
            public ServerTask getServerTask() {
                return null;
            }
        };
        rule.setName("Name");
        RuleOperationResult ruleOperationResult = new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS, rule);
        Future<RuleLoadResult.RuleLoadStatus> ruleLoadStatus = CompletableFuture.completedFuture(SUCCESS);
        RuleLoadResult ruleLoadResult = new RuleLoadResult(Long.valueOf(1), ruleLoadStatus);
        TaskRunResultFormatterClass taskRunResultFormatterClass = new TaskRunResultFormatterClass() {
            @Override
            public String formatMidPointSonarObject(MidPointSonarObject midPointSonarObject) {
                return null;
            }
        };
        ruleLoadResult.setRuleOperationResult(ruleOperationResult);
        Future<Status> ruleRunStatus = CompletableFuture.completedFuture(Status.SUCCESS);
        Collection<MidPointSonarObject> midPointSonarObjectCollection = null;
        Future<Collection<MidPointSonarObject>> objects = CompletableFuture.completedFuture(midPointSonarObjectCollection);
        RuleRunResult ruleRunResult = new RuleRunResult(Long.valueOf(1), ruleLoadResult, ruleRunStatus, objects);
        ruleRunResult.setRuleOperationResult(ruleOperationResult);
        List<RuleRunResult> ruleRunResults = new ArrayList<>() {{
            add(ruleRunResult);
        }};
        QualityProfile qualityProfile = new QualityProfile("null");
        QPOperationResult qpOperationResult = new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS, qualityProfile);
        QPRunResult qpRunResult = new QPRunResult(Long.valueOf(1), ruleRunResults, qpOperationResult);

        String expected = "Run results for Rules ih Quality Profile named null: \n" +
                            "Rule named Name with server task null loaded successfully. " +
                            "Rule named Name with server task null ran successfully. \n";
        String actual = taskRunResultFormatterClass.formatQPRunResult(qpRunResult);

        assertTrue(actual.equals(expected));
    }
}