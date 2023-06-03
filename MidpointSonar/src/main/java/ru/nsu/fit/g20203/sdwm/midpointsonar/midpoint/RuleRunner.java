package ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint;

import ru.nsu.fit.g20203.sdwm.midpointsonar.CSVParser;
import ru.nsu.fit.g20203.sdwm.midpointsonar.CSVParserClass;
import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.Rule;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.Status;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleLoadResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleRunResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.RuleOperationResult;

import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class RuleRunner {

    private final CSVParser csvParser;

    private final static int sleepInterval = 500;

    private RuleRunner() {
        csvParser = new CSVParserClass();
    }

    private static RuleRunner instance;

    public RuleRunResult runRule(Rule rule) {
        final RuleRunResult result = new RuleRunResult();
        if (null == rule) {
            result.setRuleOperationResult(new RuleOperationResult(RuleOperationResult.RuleOperationStatus.NO_SUCH_RULE,
                    null));
            return result;
        }
        result.setRuleOperationResult(new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS,
                null));
        try {
            MidPoint.getInstance().runTask(rule.getServerTask().getOid());
            registerResultWaiting(rule, result);
            result.setRuleLoadResult(RuleLoadResult.createSuccessful());
        } catch (Exception e) {
            result.setRuleRunStatus(CompletableFuture.completedFuture(Status.ERROR));
        }
        return result;
    }

    private void registerResultWaiting(Rule rule, RuleRunResult result) throws IOException, ExecutionException, InterruptedException {
        result.setObjects(CompletableFuture.supplyAsync(() -> {
                    try {
                        String dataRefOID = null;
                        while (null == dataRefOID) {
                            final String xmlFileWithDataRef = MidPoint
                                    .getInstance()
                                    .getTaskRunResult(rule.getServerTask().getOid(), "tasks");
                            dataRefOID = MidPointXMLParser.getReportDataRefOID(xmlFileWithDataRef);
                            if (null == dataRefOID) {
                                Thread.sleep(sleepInterval);
                            }
                        }
                        return dataRefOID;
                    } catch (Exception e) {
                        return null;
                    }
                })
                .thenApplyAsync(dataRefOID -> {
                    try {
                        if (null == dataRefOID) {
                            return null;
                        }
                        String filePath = null;
                        while (null == filePath) {
                            final String xmlFileWithDataRef = MidPoint
                                    .getInstance()
                                    .getTaskRunResult(dataRefOID, "reportData");
                            filePath = MidPointXMLParser.getFilePath(xmlFileWithDataRef);
                            if (null == filePath) {
                                Thread.sleep(sleepInterval);
                            }
                        }
                        return filePath;
                    } catch (Exception e) {
                        return null;
                    }
                })
                .thenApplyAsync(filePath ->
                {
                    try {
                        if (null != filePath) {
                            final Collection<MidPointSonarObject> midPointSonarObjs = csvParser.parseReport(MidPoint.getInstance().getFilePath(filePath));
                            result.setRuleRunStatus(CompletableFuture.completedFuture(Status.ERROR));
                            return midPointSonarObjs;
                        } else {
                            result.setRuleRunStatus(CompletableFuture.completedFuture(Status.ERROR));
                            return null;
                        }
                    } catch (Exception e) {
                        result.setRuleRunStatus(CompletableFuture.completedFuture(Status.ERROR));
                        return null;
                    }
                }));
    }

    public static RuleRunner getInstance() {
        if (null == instance) {
            synchronized (RuleRunner.class) {
                if (null == instance) {
                    instance = new RuleRunner();
                }
            }
        }
        return instance;
    }

}
