package ru.nsu.fit.g20203.sdwm.midpointsonar.formatter;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint.ServerTask;
import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.QualityProfile;
import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.Rule;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPAndRuleOperationResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.RuleOperationResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPAndRuleOperationResult.QPAndRuleOperationStatus.RULE_NOT_IN_QP;
import static ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult.QPOperationStatus.NO_SUCH_QUALITY_PROFILE;
import static ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult.QPOperationStatus.SUCCESS;

class QPOutputFormatterClassTest {

    @Test
    void lsQP() {
    }

    @Test
    void lsQPs() {
    }

    @Test
    void lsRules() {
        Rule rule1 = new Rule() {
            @Override
            public ServerTask getServerTask() {
                return null;
            }
        };
        Rule rule2 = new Rule() {
            @Override
            public ServerTask getServerTask() {
                return null;
            }
        };
        QPOutputFormatterClass qpOutputFormatterClass = new QPOutputFormatterClass();
        rule1.setName("Name1");
        rule2.setName("Name2");
        List<Rule> rules = new ArrayList<>() {{add(rule1); add(rule2);}};

        String expected = "Rule with name " + rule1.getName() + " with server task null\n" +
                "Rule with name " + rule2.getName() + " with server task null\n";
        String actual = qpOutputFormatterClass.lsRules(rules);

        assertTrue(actual.equals(expected));
    }

    @Test
    void formatQPOperationResultNoSuch() {
        QPOutputFormatter qpOutputFormatter = new QPOutputFormatterClass();
        QualityProfile qualityProfile = new QualityProfile() {
            @Override
            public QPOperationResult addRule(Rule rule) {
                return null;
            }

            @Override
            public QPOperationResult removeRule(String ruleName) {
                return null;
            }

            @Override
            public Collection<Rule> getAllRules() {
                return null;
            }
        };
        QPOperationResult qpOperationResult = new QPOperationResult(NO_SUCH_QUALITY_PROFILE, qualityProfile);

        String expected = "There is no Quality Profile with name null\n";
        String actual = qpOutputFormatter.formatQPOperationResult(qpOperationResult);

        assertTrue(actual.equals(expected));
    }

    @Test
    void formatQPOperationResultSuccess() {
        QPOutputFormatter qpOutputFormatter = new QPOutputFormatterClass();
        QualityProfile qualityProfile = new QualityProfile() {
            @Override
            public QPOperationResult addRule(Rule rule) {
                return null;
            }

            @Override
            public QPOperationResult removeRule(String ruleName) {
                return null;
            }

            @Override
            public Collection<Rule> getAllRules() {
                return null;
            }
        };
        QPOperationResult qpOperationResult = new QPOperationResult(SUCCESS, qualityProfile);

        String expected = "Success on Quality Profile with name null\n";
        String actual = qpOutputFormatter.formatQPOperationResult(qpOperationResult);

        assertTrue(actual.equals(expected));
    }

    @Test
    void formatRuleOperationResultSuccess() {
        QPOutputFormatter qpOutputFormatter = new QPOutputFormatterClass();
        Rule rule = new Rule() {
            @Override
            public ServerTask getServerTask() {
                return null;
            }
        };
        rule.setName("Name");
        RuleOperationResult ruleOperationResult = new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS, rule);

        String expected = "Success on Rule with name Name with server task null\n";
        String actual = qpOutputFormatter.formatRuleOperationResult(ruleOperationResult);

        assertTrue(actual.equals(expected));
    }

    @Test
    void formatQpAndRuleOpResultNotIn() {
        QPOutputFormatter qpOutputFormatter = new QPOutputFormatterClass();
        QualityProfile qualityProfile = new QualityProfile() {
            @Override
            public QPOperationResult addRule(Rule rule) {
                return null;
            }

            @Override
            public QPOperationResult removeRule(String ruleName) {
                return null;
            }

            @Override
            public Collection<Rule> getAllRules() {
                return null;
            }
        };
        QPOperationResult qpOperationResult = new QPOperationResult(SUCCESS, qualityProfile);
        Rule rule = new Rule() {
            @Override
            public ServerTask getServerTask() {
                return null;
            }
        };
        rule.setName("Name");
        RuleOperationResult ruleOperationResult = new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS, rule);
        QPAndRuleOperationResult qpAndRuleOperationResult = new QPAndRuleOperationResult(RULE_NOT_IN_QP, qpOperationResult,
                ruleOperationResult);

        String expected = "Rule with name Name with server task null\n" +
                "not in Quality profile with name null\n";
        String actual = qpOutputFormatter.formatQpAndRuleOpResult(qpAndRuleOperationResult);
        System.out.println(expected);
        System.out.println(actual);

        assertTrue(actual.equals(expected));
    }

    @Test
    void formatRule() {
        Rule rule = new Rule() {
            @Override
            public ServerTask getServerTask() {
                return null;
            }
        };
        QPOutputFormatterClass qpOutputFormatterClass = new QPOutputFormatterClass();
        rule.setName("Name");

        String expected = "Rule with name " + rule.getName() + " with server task null\n";
        String actual = qpOutputFormatterClass.formatRule(rule);

        assertTrue(actual.equals(expected));
    }
}