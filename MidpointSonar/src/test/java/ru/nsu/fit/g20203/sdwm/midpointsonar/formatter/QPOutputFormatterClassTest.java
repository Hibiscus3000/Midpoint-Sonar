package ru.nsu.fit.g20203.sdwm.midpointsonar.formatter;

class QPOutputFormatterClassTest {

//    @Test
//    void lsQP() {
//        QPOutputFormatter qpOutputFormatter = new QPOutputFormatterClass();
//        QualityProfile qualityProfile = new QualityProfile("Name");
//        Rule rule = new Rule("Rule", null);
//        qualityProfile.addRule(rule);
//        String expected = "Quality profile with name Name contains:\n" +
//                "Rule with name " + rule.getName() + " with server task null\n";
//        String actual = qpOutputFormatter.lsQP(qualityProfile);
//
//        assertEquals(actual, expected);
//    }
//
//    @Test
//    void lsQPs() {
//        QPOutputFormatter qpOutputFormatter = new QPOutputFormatterClass();
//        QualityProfile qualityProfile1 = new QualityProfile("Name1");
//        QualityProfile qualityProfile2 = new QualityProfile("Name2");
//        Rule rule = new Rule("Rule", null);
//        qualityProfile1.addRule(rule);
//        qualityProfile2.addRule(rule);
//        List<QualityProfile> qps = new ArrayList<>() {{
//            add(qualityProfile1);
//            add(qualityProfile2);
//        }};
//        String expected = "All quality profiles:\nQuality profile with name Name1 contains:\n" +
//                "Rule with name " + rule.getName() + " with server task null\n" +
//                "Quality profile with name Name2 contains:\n" +
//                "Rule with name " + rule.getName() + " with server task null\n";
//        String actual = qpOutputFormatter.lsQPs(qps);
//
//        assertEquals(actual, expected);
//    }
//
//    @Test
//    void lsRules() {
//        Rule rule1 = new Rule("Name1", null);
//        Rule rule2 = new Rule("Name2", null);
//        QPOutputFormatterClass qpOutputFormatterClass = new QPOutputFormatterClass();
//        List<Rule> rules = new ArrayList<>() {{add(rule1); add(rule2);}};
//
//        String expected = "Rule with name " + rule1.getName() + " with server task null\n" +
//                "Rule with name " + rule2.getName() + " with server task null\n";
//        String actual = qpOutputFormatterClass.lsRules(rules);
//
//        assertEquals(actual, expected);
//    }
//
//    @Test
//    void formatQPOperationResultNoSuch() {
//        QPOutputFormatter qpOutputFormatter = new QPOutputFormatterClass();
//        QualityProfile qualityProfile = new QualityProfile("null");
//        QPOperationResult qpOperationResult = new QPOperationResult(NO_SUCH_QUALITY_PROFILE, qualityProfile);
//
//        String expected = "There is no Quality Profile with name null\n";
//        String actual = qpOutputFormatter.formatQPOperationResult(qpOperationResult);
//
//        assertEquals(actual, expected);
//    }
//
//    @Test
//    void formatQPOperationResultSuccess() {
//        QPOutputFormatter qpOutputFormatter = new QPOutputFormatterClass();
//        QualityProfile qualityProfile = new QualityProfile("null");
//        QPOperationResult qpOperationResult = new QPOperationResult(SUCCESS, qualityProfile);
//
//        String expected = "Success on Quality Profile with name null\n";
//        String actual = qpOutputFormatter.formatQPOperationResult(qpOperationResult);
//
//        assertEquals(actual, expected);
//    }
//
//    @Test
//    void formatRuleOperationResultSuccess() {
//        QPOutputFormatter qpOutputFormatter = new QPOutputFormatterClass();
//        Rule rule = new Rule("Name", null);
//        RuleOperationResult ruleOperationResult = new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS, rule);
//
//        String expected = "Success on Rule with name Name with server task null\n";
//        String actual = qpOutputFormatter.formatRuleOperationResult(ruleOperationResult);
//
//        assertEquals(actual, expected);
//    }
//
//    @Test
//    void formatQpAndRuleOpResultNotIn() {
//        QPOutputFormatter qpOutputFormatter = new QPOutputFormatterClass();
//        QualityProfile qualityProfile = new QualityProfile("null");
//        QPOperationResult qpOperationResult = new QPOperationResult(SUCCESS, qualityProfile);
//        Rule rule = new Rule("Name", null);
//        RuleOperationResult ruleOperationResult = new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS, rule);
//        QPAndRuleOperationResult qpAndRuleOperationResult = new QPAndRuleOperationResult(RULE_NOT_IN_QP, qpOperationResult,
//                ruleOperationResult);
//
//        String expected = "Rule with name Name with server task null\n" +
//                "not in Quality profile with name null\n";
//        String actual = qpOutputFormatter.formatQPAndRuleOpResult(qpAndRuleOperationResult);
//
//        assertEquals(actual, expected);
//    }
//
//    @Test
//    void formatRule() {
//        Rule rule = new Rule("Name", null);
//        QPOutputFormatterClass qpOutputFormatterClass = new QPOutputFormatterClass();
//
//        String expected = "Rule with name " + rule.getName() + " with server task null\n";
//        String actual = qpOutputFormatterClass.formatRule(rule);
//
//        assertEquals(actual, expected);
//    }
}