package ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint;

import ru.nsu.fit.g20203.sdwm.midpointsonar.CSVParser;
import ru.nsu.fit.g20203.sdwm.midpointsonar.CSVParserClass;
import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.Rule;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.Status;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleRunResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.RuleOperationResult;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class RuleRunner {

    private final CSVParser csvParser;

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
        } catch (Exception e) {
            result.setRuleRunStatus(CompletableFuture.completedFuture(Status.ERROR));
        }
        return result;
    }

    private void registerResultWaiting(Rule rule, RuleRunResult result) throws IOException, ExecutionException, InterruptedException {
        result.setObjects(MidPoint
                .getInstance()
                .getTaskRunResult(rule.getServerTask().getOid(), "tasks")
                .thenComposeAsync(xmlWithFilePathReportDataRef -> {
                    try {
                        if (null != xmlWithFilePathReportDataRef) {
                            return MidPoint
                                    .getInstance()
                                    .getTaskRunResult(MidPointXMLParser
                                                    .getReportDataRefOID(xmlWithFilePathReportDataRef),
                                            "reportData");
                        } else {
                            return CompletableFuture.completedFuture(null);
                        }
                    } catch (Exception e) {
                        return CompletableFuture.completedFuture(null);
                    }
                })
                .thenApplyAsync(filePathXml ->
                {
                    try {
                        if (null != filePathXml) {
                            String filePath = MidPointXMLParser.getFilePath(filePathXml);
                            if (null != filePath) {
                                return csvParser.parseReport(MidPoint.getInstance().getFilePath(filePath));
                            } else {
                                return null;
                            }
                        } else {
                            return null;
                        }
                    } catch (Exception e) {
                        return null;
                    }
                }));
        result.getObjects().get();
        System.out.println("hello");
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
