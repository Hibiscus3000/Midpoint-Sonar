package ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile;

import org.springframework.beans.factory.annotation.Autowired;
import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.Repositories.QPRepository;
import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.Repositories.RuleRepository;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPAndRuleOperationResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult;

import java.util.Collection;

import static ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult.QPOperationStatus.NO_SUCH_QUALITY_PROFILE;

public class RulesAndQPManagerClass implements RulesAndQPManager {
    private final QPRepository qpRepository;
    private final RuleRepository ruleRepository;
    @Autowired
    public RulesAndQPManagerClass(QPRepository qpRepository,RuleRepository ruleRepository){
        this.qpRepository = qpRepository;
        this.ruleRepository = ruleRepository;
    }
    @Override
    public QPOperationResult createNewQualityProfile(String profileName) {
        return null;
    }
    @Override
    public QPOperationResult renameQualityProfile(String oldName, String newName) {
        return null;
    }

    @Override
    public QPOperationResult removeQualityProfile(String profileName) {
        QualityProfile qp = qpRepository.findByName(profileName);
        if (qp == null){
            return new QPOperationResult(NO_SUCH_QUALITY_PROFILE, null);
        }
        qpRepository.delete(qp);
        return new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS, null);
    }

    @Override
    public QPAndRuleOperationResult addRuleToQualityProfile(String ruleName, String profileName) {
        return null;
    }

    @Override
    public QPAndRuleOperationResult removeRuleFromQualityProfile(String ruleName, String profileName) {
        return null;
    }

    @Override
    public QualityProfile getQualityProfile(String profileName) {
        return qpRepository.findByName(profileName);
    }

    @Override
    public Collection<QualityProfile> getAllQualityProfiles() {
        return qpRepository.findAll();
    }

    @Override
    public Rule getRule(String ruleName) {
        return ruleRepository.findByName(ruleName);
    }

    @Override
    public Collection<Rule> getAllRules() {
        return ruleRepository.findAll();
    }
}
