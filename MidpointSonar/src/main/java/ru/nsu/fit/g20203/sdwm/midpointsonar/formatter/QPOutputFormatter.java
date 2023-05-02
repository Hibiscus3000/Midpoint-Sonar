package ru.nsu.fit.g20203.sdwm.midpointsonar.formatter;

import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.QualityProfile;
import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.Rule;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPAndRuleOperationResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.RuleOperationResult;

import java.util.Collection;

public interface QPOutputFormatter {

    String lsQP(QualityProfile qualityProfile);

    String lsQPs(Collection<QualityProfile> qualityProfiles);

    String lsRules(Collection<Rule> rules);

    String formatQPOperationResult(QPOperationResult qpOperationResult);

    String formatRuleOperationResult(RuleOperationResult ruleOperationResult);

    String formatQpAndRuleOpResult(QPAndRuleOperationResult qpAndRuleOperationResult);
}
