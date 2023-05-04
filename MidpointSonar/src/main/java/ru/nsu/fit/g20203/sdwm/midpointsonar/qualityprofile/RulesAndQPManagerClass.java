package ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile;

import org.springframework.beans.factory.annotation.Autowired;
import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.Repositories.QPRepository;
import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.Repositories.RuleRepository;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPAndRuleOperationResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.RuleOperationResult;

import java.util.Collection;

import static ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult.QPOperationStatus.NO_SUCH_QUALITY_PROFILE;
import static ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult.QPOperationStatus.SUCCESS;

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
        if (qpRepository.findByName(profileName) != null) {
            return new QPOperationResult(QPOperationResult.QPOperationStatus.QP_WITH_GIVEN_NAME_ALREADY_EXISTS, null);
        }
        QualityProfile qp = new QualityProfile(profileName);
        qpRepository.save(qp);
        return new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS, qp);
    }
    @Override
    public QPOperationResult renameQualityProfile(String oldName, String newName) {
        QualityProfile qp = qpRepository.findByName(oldName);
        if (qp == null){
            return new QPOperationResult(NO_SUCH_QUALITY_PROFILE,null);
        }
        qp.setName(newName);
        if (qpRepository.findByName(newName) != null) {
            return new QPOperationResult(QPOperationResult.QPOperationStatus.QP_WITH_GIVEN_NAME_ALREADY_EXISTS, null);
        }
        qpRepository.save(qp);
        return new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS, qp);
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
        QualityProfile qp =  qpRepository.findByName(profileName);
        if (qp == null){
            return new QPAndRuleOperationResult(QPAndRuleOperationResult.QPAndRuleOperationStatus.SUCCESS,
                    new QPOperationResult(NO_SUCH_QUALITY_PROFILE,null),
                    new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS,
                            new QualityProfile(profileName)));
        }
        Rule rule = ruleRepository.findByName(ruleName);
        if (rule == null){
            return new QPAndRuleOperationResult(QPAndRuleOperationResult.QPAndRuleOperationStatus.SUCCESS,
                    new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS,qp),
                    new RuleOperationResult(RuleOperationResult.RuleOperationStatus.NO_SUCH_RULE,
                            new Rule(ruleName)));
        }
        QPOperationResult qpr = qp.addRule(rule);
        if (qpr.getStatus() == SUCCESS){
            qpRepository.save(qp);
            return new QPAndRuleOperationResult(QPAndRuleOperationResult.QPAndRuleOperationStatus.SUCCESS,
                    new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS,qp),
                    new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS,rule));
        }
        else {
            qpRepository.save(qp);
            return new QPAndRuleOperationResult(QPAndRuleOperationResult.QPAndRuleOperationStatus.RULE_ALREADY_IN_QP,
                    new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS, qp),
                    new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS, rule));
        }

    }

    @Override
    public QPAndRuleOperationResult removeRuleFromQualityProfile(String ruleName, String profileName) {
        QualityProfile qp =  qpRepository.findByName(profileName);
        if (qp == null) {
            return new QPAndRuleOperationResult(QPAndRuleOperationResult.QPAndRuleOperationStatus.SUCCESS,
                    new QPOperationResult(NO_SUCH_QUALITY_PROFILE, null),
                    new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS,
                            new QualityProfile(profileName)));
        }
        Rule rule = ruleRepository.findByName(ruleName);
        if (rule == null) {
            return new QPAndRuleOperationResult(QPAndRuleOperationResult.QPAndRuleOperationStatus.SUCCESS,
                    new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS, qp),
                    new RuleOperationResult(RuleOperationResult.RuleOperationStatus.NO_SUCH_RULE,
                            new Rule(ruleName)));
        }
        QPOperationResult qpr = qp.removeRule(ruleName);
        if (qpr.getStatus() == SUCCESS){
            qpRepository.save(qp);
            return new QPAndRuleOperationResult(QPAndRuleOperationResult.QPAndRuleOperationStatus.SUCCESS,
                    new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS,qp),
                    new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS,rule));
        }
        else {
            qpRepository.save(qp);
            return new QPAndRuleOperationResult(QPAndRuleOperationResult.QPAndRuleOperationStatus.RULE_NOT_IN_QP,
                    new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS, qp),
                    new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS, rule));
        }

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
