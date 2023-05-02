package ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile;

import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPAndRuleOperationResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult;

import java.util.Collection;

public interface RulesAndQPManager {

    QPOperationResult createNewQualityProfile(String profileName);

    QPOperationResult renameQualityProfile(String oldName, String newName);

    QPOperationResult removeQualityProfile(String profileName);

    QPAndRuleOperationResult addRuleToQualityProfile(String ruleName, String profileName);

    QPAndRuleOperationResult removeRuleFromQualityProfile(String ruleName, String profileName);

    QualityProfile getQualityProfile(String profileName);

    Collection<QualityProfile> getAllQualityProfiles();

    Rule getRule(String ruleName);

    Collection<Rule> getAllRules();
}
