package ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nsu.fit.g20203.sdwm.midpointsonar.repositories.QPRepository;
import ru.nsu.fit.g20203.sdwm.midpointsonar.repositories.RuleRepository;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPAndRuleOperationResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.RuleOperationResult;

import java.util.Collection;

import static ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult.QPOperationStatus.NO_SUCH_QUALITY_PROFILE;
import static ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.RuleOperationResult.RuleOperationStatus.SUCCESS;


@Service
@RequestMapping("/RAndQPManger")
public class RulesAndQPManagerClass implements RulesAndQPManager {
    private final QPRepository qpRepository;
    private final RuleRepository ruleRepository;

    @Autowired
    public RulesAndQPManagerClass(QPRepository qpRepository,RuleRepository ruleRepository){
        this.qpRepository = qpRepository;
        this.ruleRepository = ruleRepository;
    }

    @Override
    @GetMapping("/CreateQP")
    public QPOperationResult createNewQualityProfile(String profileName) {
        QualityProfile oldQ = qpRepository.findByName(profileName);
        if (oldQ != null) {
            return new QPOperationResult(QPOperationResult.QPOperationStatus.QP_WITH_GIVEN_NAME_ALREADY_EXISTS, oldQ);
        }
        QualityProfile qp = new QualityProfile(profileName);
        qpRepository.save(qp);
        return new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS, qp);
    }

    @Override
    @GetMapping("/RenameQP")
    public QPOperationResult renameQualityProfile(String oldName, String newName) {
        QualityProfile qp = qpRepository.findByName(oldName);
        if (qp == null){
            return new QPOperationResult(NO_SUCH_QUALITY_PROFILE, new QualityProfile(oldName));
        }
        qp.setName(newName);
        if (qpRepository.findByName(newName) != null) {
            return new QPOperationResult(QPOperationResult.QPOperationStatus.QP_WITH_GIVEN_NAME_ALREADY_EXISTS,
                                        new QualityProfile(newName));
        }
        qpRepository.save(qp);
        return new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS, qp);
    }


    @Override
    @GetMapping("/RemoveQP")
    public QPOperationResult removeQualityProfile(String profileName) {
        QualityProfile qp = qpRepository.findByName(profileName);
        if (qp == null){
            return new QPOperationResult(NO_SUCH_QUALITY_PROFILE, new QualityProfile(profileName));
        }
        qpRepository.delete(qp);
        return new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS, null);
    }

    @Override
    @GetMapping("/AddRuleToQP")
    public QPAndRuleOperationResult addRuleToQualityProfile(String ruleName, String profileName) {
        QualityProfile qp =  qpRepository.findByName(profileName);
        if (qp == null){
            return new QPAndRuleOperationResult(QPAndRuleOperationResult.QPAndRuleOperationStatus.SUCCESS,
                    new QPOperationResult(NO_SUCH_QUALITY_PROFILE, new QualityProfile(profileName)),
                    new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS,
                            new Rule(ruleName)));
        }
        Rule rule = ruleRepository.findByName(ruleName);
        if (rule == null){
            return new QPAndRuleOperationResult(QPAndRuleOperationResult.QPAndRuleOperationStatus.SUCCESS,
                    new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS,qp),
                    new RuleOperationResult(RuleOperationResult.RuleOperationStatus.NO_SUCH_RULE,
                            new Rule(ruleName)));
        }
        RuleOperationResult qpr = qp.addRule(rule);
        if (qpr.getStatus() == SUCCESS){
            qpRepository.save(qp);
            return new QPAndRuleOperationResult(QPAndRuleOperationResult.QPAndRuleOperationStatus.SUCCESS,
                    new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS,qp),
                    new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS,rule));
        }
        else {
            return new QPAndRuleOperationResult(QPAndRuleOperationResult.QPAndRuleOperationStatus.RULE_ALREADY_IN_QP,
                    new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS, qp),
                    new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS, rule));
        }

    }

    @Override
    @GetMapping("/RemoveRuleFromQP")
    public QPAndRuleOperationResult removeRuleFromQualityProfile(String ruleName, String profileName) {
        QualityProfile qp =  qpRepository.findByName(profileName);
        if (qp == null) {
            return new QPAndRuleOperationResult(QPAndRuleOperationResult.QPAndRuleOperationStatus.SUCCESS,
                    new QPOperationResult(NO_SUCH_QUALITY_PROFILE, new QualityProfile(profileName)),
                    new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS,
                            new Rule(ruleName)));
        }
        Rule rule = ruleRepository.findByName(ruleName);
        if (rule == null) {
            return new QPAndRuleOperationResult(QPAndRuleOperationResult.QPAndRuleOperationStatus.SUCCESS,
                    new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS, qp),
                    new RuleOperationResult(RuleOperationResult.RuleOperationStatus.NO_SUCH_RULE,
                            new Rule(ruleName)));
        }
        RuleOperationResult qpr = qp.removeRule(ruleName);
        if (qpr.getStatus() == SUCCESS){
            qpRepository.save(qp);
            return new QPAndRuleOperationResult(QPAndRuleOperationResult.QPAndRuleOperationStatus.SUCCESS,
                    new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS,qp),
                    new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS,rule));
        }
        else {
            return new QPAndRuleOperationResult(QPAndRuleOperationResult.QPAndRuleOperationStatus.RULE_NOT_IN_QP,
                    new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS, qp),
                    new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS, rule));
        }

    }

    @Override
    @GetMapping("/GetQP")
    public QualityProfile getQualityProfile(String profileName) {
        return qpRepository.findByName(profileName);
    }

    @Override
    @GetMapping("/GetAllQP")
    public Collection<QualityProfile> getAllQualityProfiles() {
        return qpRepository.findAll();
    }

    @Override
    @GetMapping("/GetRule")
    public Rule getRule(String ruleName) {
        return ruleRepository.findByName(ruleName);
    }

    @Override
    @GetMapping("/GetAllRule")
    public Collection<Rule> getAllRules() {
        return ruleRepository.findAll();
    }
}
