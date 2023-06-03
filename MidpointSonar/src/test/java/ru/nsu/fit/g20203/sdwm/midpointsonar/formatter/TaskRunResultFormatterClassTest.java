package ru.nsu.fit.g20203.sdwm.midpointsonar.formatter;

class TaskRunResultFormatterClassTest {

//    @Test
//    void formatRuleLoadResultNoSuch() {
//        Rule rule = new Rule("Name", null);
//        RuleOperationResult ruleOperationResult = new RuleOperationResult(RuleOperationResult.RuleOperationStatus.NO_SUCH_RULE, rule);
//        Future<RuleLoadResult.RuleLoadStatus> ruleLoadStatus = CompletableFuture.completedFuture(ERROR);
//        RuleLoadResult ruleLoadResult = new RuleLoadResult(1L, ruleLoadStatus);
//        TaskRunResultFormatterClass taskRunResultFormatterClass = new TaskRunResultFormatterClass();
//        ruleLoadResult.setRuleOperationResult(ruleOperationResult);
//
//        String expected = "There is no rule with name Name.\n";
//        String actual = taskRunResultFormatterClass.formatRuleLoadResult(ruleLoadResult);
//
//        assertEquals(actual, expected);
//    }
//
//    @Test
//    void formatRuleLoadResultSuccess() {
//        Rule rule = new Rule("Name");
//        RuleOperationResult ruleOperationResult = new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS, rule);
//        Future<RuleLoadResult.RuleLoadStatus> ruleLoadStatus = CompletableFuture.completedFuture(SUCCESS);
//        RuleLoadResult ruleLoadResult = new RuleLoadResult(1L, ruleLoadStatus);
//        TaskRunResultFormatterClass taskRunResultFormatterClass = new TaskRunResultFormatterClass();
//        ruleLoadResult.setRuleOperationResult(ruleOperationResult);
//
//        String expected = "Rule named Name with server task null loaded successfully.\n";
//        String actual = taskRunResultFormatterClass.formatRuleLoadResult(ruleLoadResult);
//
//        assertEquals(actual, expected);
//    }
//
//    @Test
//    void formatRuleRunResultNoSuch() {
//        Rule rule = new Rule("Name");
//        RuleOperationResult ruleOperationResult = new RuleOperationResult(RuleOperationResult.RuleOperationStatus.NO_SUCH_RULE, rule);
//        Future<RuleLoadResult.RuleLoadStatus> ruleLoadStatus = CompletableFuture.completedFuture(SUCCESS);
//        RuleLoadResult ruleLoadResult = new RuleLoadResult(1L, ruleLoadStatus);
//        TaskRunResultFormatterClass taskRunResultFormatterClass = new TaskRunResultFormatterClass();
//        ruleLoadResult.setRuleOperationResult(ruleOperationResult);
//        Future<Status> ruleRunStatus = CompletableFuture.completedFuture(Status.SUCCESS);
//        Collection<MidPointSonarObject> midPointSonarObjectCollection = null;
//        Future<Collection<MidPointSonarObject>> objects = CompletableFuture.completedFuture(midPointSonarObjectCollection);
//        RuleRunResult ruleRunResult = new RuleRunResult(1L, ruleLoadResult, ruleRunStatus, objects);
//        ruleRunResult.setRuleOperationResult(ruleOperationResult);
//
//        String expected = "There is no rule with name Name.\nCannot be run.\n";
//        String actual = taskRunResultFormatterClass.formatRuleRunResult(ruleRunResult);
//
//        assertEquals(actual, expected);
//    }
//
//    @Test
//    void formatRuleRunResultSuccess() {
//        Rule rule = new Rule("Name");
//        RuleOperationResult ruleOperationResult = new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS, rule);
//        Future<RuleLoadResult.RuleLoadStatus> ruleLoadStatus = CompletableFuture.completedFuture(SUCCESS);
//        RuleLoadResult ruleLoadResult = new RuleLoadResult(1L, ruleLoadStatus);
//        TaskRunResultFormatterClass taskRunResultFormatterClass = new TaskRunResultFormatterClass();
//        ruleLoadResult.setRuleOperationResult(ruleOperationResult);
//        Future<Status> ruleRunStatus = CompletableFuture.completedFuture(Status.SUCCESS);
//        Collection<MidPointSonarObject> midPointSonarObjectCollection = new ArrayList<>();
//        MidPointSonarObject midPointSonarObject = new MidPointSonarObjectClass();
//        midPointSonarObject.addInValues("key", "value");
//        midPointSonarObjectCollection.add(midPointSonarObject);
//        Future<Collection<MidPointSonarObject>> objects = CompletableFuture.completedFuture(midPointSonarObjectCollection);
//        RuleRunResult ruleRunResult = new RuleRunResult(1L, ruleLoadResult, ruleRunStatus, objects);
//        ruleRunResult.setRuleOperationResult(ruleOperationResult);
//
//        String expected = "Rule named Name with server task null loaded successfully.\nRule named Name with server task null ran successfully.\nkey value\n";
//        String actual = taskRunResultFormatterClass.formatRuleRunResult(ruleRunResult);
//
//        assertEquals(actual, expected);
//    }
//
//    @Test
//    void formatQPRunResultSuccess() {
//        Rule rule = new Rule("Name");
//        RuleOperationResult ruleOperationResult = new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS, rule);
//        Future<RuleLoadResult.RuleLoadStatus> ruleLoadStatus = CompletableFuture.completedFuture(SUCCESS);
//        RuleLoadResult ruleLoadResult = new RuleLoadResult(1L, ruleLoadStatus);
//        TaskRunResultFormatterClass taskRunResultFormatterClass = new TaskRunResultFormatterClass();
//        ruleLoadResult.setRuleOperationResult(ruleOperationResult);
//        Future<Status> ruleRunStatus = CompletableFuture.completedFuture(Status.SUCCESS);
//        Collection<MidPointSonarObject> midPointSonarObjectCollection = new ArrayList<>();
//        MidPointSonarObject midPointSonarObject = new MidPointSonarObjectClass();
//        midPointSonarObject.addInValues("key", "value");
//        midPointSonarObjectCollection.add(midPointSonarObject);
//        Future<Collection<MidPointSonarObject>> objects = CompletableFuture.completedFuture(midPointSonarObjectCollection);
//        RuleRunResult ruleRunResult = new RuleRunResult(1L, ruleLoadResult, ruleRunStatus, objects);
//        ruleRunResult.setRuleOperationResult(ruleOperationResult);
//        List<RuleRunResult> ruleRunResults = new ArrayList<>() {{
//            add(ruleRunResult);
//        }};
//        QualityProfile qualityProfile = new QualityProfile("null");
//        QPOperationResult qpOperationResult = new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS, qualityProfile);
//        QPRunResult qpRunResult = new QPRunResult(1L, ruleRunResults, qpOperationResult);
//
//        String expected = "Run results for Rules ih Quality Profile named null:\n" +
//                            "Rule named Name with server task null loaded successfully.\n" +
//                            "Rule named Name with server task null ran successfully.\n" +
//                            "key value\n";
//        String actual = taskRunResultFormatterClass.formatQPRunResult(qpRunResult);
//
//        assertEquals(actual, expected);
//    }
//
//    @Test
//    void formatRunHistoryWId() {
//        Rule rule = new Rule("Name");
//        RuleOperationResult ruleOperationResult = new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS, rule);
//        Future<RuleLoadResult.RuleLoadStatus> ruleLoadStatus = CompletableFuture.completedFuture(SUCCESS);
//        RuleLoadResult ruleLoadResult = new RuleLoadResult(1L, ruleLoadStatus);
//        TaskRunResultFormatterClass taskRunResultFormatterClass = new TaskRunResultFormatterClass();
//        ruleLoadResult.setRuleOperationResult(ruleOperationResult);
//        Future<Status> ruleRunStatus = CompletableFuture.completedFuture(Status.SUCCESS);
//        Collection<MidPointSonarObject> midPointSonarObjectCollection = new ArrayList<>();
//        MidPointSonarObject midPointSonarObject = new MidPointSonarObjectClass();
//        midPointSonarObject.addInValues("key", "value");
//        midPointSonarObjectCollection.add(midPointSonarObject);
//        Future<Collection<MidPointSonarObject>> objects = CompletableFuture.completedFuture(midPointSonarObjectCollection);
//        RuleRunResult ruleRunResult = new RuleRunResult(1L, ruleLoadResult, ruleRunStatus, objects);
//        ruleRunResult.setRuleOperationResult(ruleOperationResult);
//        List<RuleRunResult> ruleRunResults = new ArrayList<>() {{
//            add(ruleRunResult);
//        }};
//        QualityProfile qualityProfile = new QualityProfile("null");
//        QPOperationResult qpOperationResult = new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS, qualityProfile);
//        QPRunResult qpRunResult = new QPRunResult(1L, ruleRunResults, qpOperationResult);
//        Map<Integer, QPRunResult> qpRunResultMap = new HashMap<>();
//        qpRunResultMap.put(1, qpRunResult);
//
//        String expected = "Run history:\n" + "1: Run results for Rules ih Quality Profile named null:\n" +
//                "Rule named Name with server task null loaded successfully.\n" +
//                "Rule named Name with server task null ran successfully.\n" +
//                "key value\n";
//        String actual = taskRunResultFormatterClass.formatRunHistoryWId(qpRunResultMap);
//
//        assertEquals(actual, expected);
//    }
//
//    @Test
//    void formatRuleLoadHistoryWId(){
//        Rule rule = new Rule("Name");
//        RuleOperationResult ruleOperationResult = new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS, rule);
//        Future<RuleLoadResult.RuleLoadStatus> ruleLoadStatus = CompletableFuture.completedFuture(SUCCESS);
//        RuleLoadResult ruleLoadResult = new RuleLoadResult(1L, ruleLoadStatus);
//        TaskRunResultFormatterClass taskRunResultFormatterClass = new TaskRunResultFormatterClass();
//        ruleLoadResult.setRuleOperationResult(ruleOperationResult);
//        Map<Integer, RuleLoadResult> ruleLoadResultMap = new HashMap<>();
//        ruleLoadResultMap.put(1, ruleLoadResult);
//
//        String expected = "Load history:\n" +
//                "1: Rule named Name with server task null loaded successfully.\n";
//        String actual = taskRunResultFormatterClass.formatRuleLoadHistoryWId(ruleLoadResultMap);
//
//        assertEquals(actual, expected);
//    }
}