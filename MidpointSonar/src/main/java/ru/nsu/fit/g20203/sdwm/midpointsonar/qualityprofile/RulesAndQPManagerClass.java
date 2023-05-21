package ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nsu.fit.g20203.sdwm.midpointsonar.entity.QualityProfileEntity;
import ru.nsu.fit.g20203.sdwm.midpointsonar.mapper.QualityProfileMapper;
import ru.nsu.fit.g20203.sdwm.midpointsonar.mapper.RuleMapper;
import ru.nsu.fit.g20203.sdwm.midpointsonar.repositories.QPRepository;
import ru.nsu.fit.g20203.sdwm.midpointsonar.repositories.RuleRepository;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPAndRuleOperationResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.RuleOperationResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult.QPOperationStatus.NO_SUCH_QUALITY_PROFILE;
import static ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.RuleOperationResult.RuleOperationStatus.SUCCESS;


@Service
public class RulesAndQPManagerClass implements RulesAndQPManager {
    private final QPRepository qpRepository;
    private final RuleRepository ruleRepository;
    private final QualityProfileMapper qualityProfileMapper;
    private final RuleMapper ruleMapper;
    @Autowired
    public RulesAndQPManagerClass(QPRepository qpRepository,
                                  RuleRepository ruleRepository,
                                  QualityProfileMapper qualityProfileMapper,
                                  RuleMapper ruleMapper){
        this.qpRepository = qpRepository;
        this.ruleRepository = ruleRepository;
        this.qualityProfileMapper = qualityProfileMapper;
        this.ruleMapper = ruleMapper;
    }

    @Override
    public QPOperationResult createNewQualityProfile(String profileName) {
        var oldQ = qpRepository.findByName(profileName);
        if (oldQ.isPresent()) {
            return new QPOperationResult(QPOperationResult.QPOperationStatus.QP_WITH_GIVEN_NAME_ALREADY_EXISTS, qualityProfileMapper.map(oldQ.get()));
        }
        QualityProfile qp = new QualityProfile(profileName);
        qpRepository.save(qualityProfileMapper.map(qp));
        return new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS, qp);
    }

    @Override
    public QPOperationResult renameQualityProfile(String oldName, String newName) {
        var qp = qpRepository.findByName(oldName);
        if (qp.isEmpty()){
            return new QPOperationResult(NO_SUCH_QUALITY_PROFILE, new QualityProfile(oldName));
        }
        if (qpRepository.existsByName(newName)) {
            return new QPOperationResult(QPOperationResult.QPOperationStatus.QP_WITH_GIVEN_NAME_ALREADY_EXISTS,
                    new QualityProfile(newName));
        }
        qp.get().setName(newName);

        qpRepository.save(qp.get());
        return new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS, qualityProfileMapper.map(qp.get()));
    }


    @Override
    public QPOperationResult removeQualityProfile(String profileName) {
        var qp = qpRepository.findByName(profileName);
        if (qp.isEmpty()){
            return new QPOperationResult(NO_SUCH_QUALITY_PROFILE, new QualityProfile(profileName));
        }
        qpRepository.delete(qp.get());
        return new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS, null);
    }

    @Override
    public QPAndRuleOperationResult addRuleToQualityProfile(String ruleName, String profileName) {
        var qp =  qpRepository.findByName(profileName);
        if (qp.isEmpty()){
            return new QPAndRuleOperationResult(QPAndRuleOperationResult.QPAndRuleOperationStatus.SUCCESS,
                    new QPOperationResult(NO_SUCH_QUALITY_PROFILE, new QualityProfile(profileName)),
                    new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS,
                            new Rule(ruleName)));
        }
        var rule = ruleRepository.findByName(ruleName);
        QualityProfile qualityProfile = qualityProfileMapper.map(qp.get());
        if (rule.isEmpty()){
            return new QPAndRuleOperationResult(QPAndRuleOperationResult.QPAndRuleOperationStatus.SUCCESS,
                    new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS,qualityProfile),
                    new RuleOperationResult(RuleOperationResult.RuleOperationStatus.NO_SUCH_RULE,
                            new Rule(ruleName)));
        }

        RuleOperationResult qpr = qualityProfile.addRule(ruleMapper.map(rule.get()));
        if (qpr.getStatus() == SUCCESS){
            qpRepository.save(qualityProfileMapper.map(qualityProfile));
            return new QPAndRuleOperationResult(QPAndRuleOperationResult.QPAndRuleOperationStatus.SUCCESS,
                    new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS,qualityProfile),
                    new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS,ruleMapper.map(rule.get())));
        }
        else {
            return new QPAndRuleOperationResult(QPAndRuleOperationResult.QPAndRuleOperationStatus.RULE_ALREADY_IN_QP,
                    new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS, qualityProfile),
                    new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS, ruleMapper.map(rule.get())));
        }

    }

    @Override
    public QPAndRuleOperationResult removeRuleFromQualityProfile(String ruleName, String profileName) {
        var qp =  qpRepository.findByName(profileName);
        if (qp.isEmpty()) {
            return new QPAndRuleOperationResult(QPAndRuleOperationResult.QPAndRuleOperationStatus.SUCCESS,
                    new QPOperationResult(NO_SUCH_QUALITY_PROFILE, new QualityProfile(profileName)),
                    new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS,
                            new Rule(ruleName)));
        }
        QualityProfile qualityProfile = qualityProfileMapper.map(qp.get());
        var rule = ruleRepository.findByName(ruleName);
        if (rule.isEmpty()) {
            return new QPAndRuleOperationResult(QPAndRuleOperationResult.QPAndRuleOperationStatus.SUCCESS,
                    new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS, qualityProfile),
                    new RuleOperationResult(RuleOperationResult.RuleOperationStatus.NO_SUCH_RULE,
                            new Rule(ruleName)));
        }
        RuleOperationResult qpr = qualityProfile.removeRule(ruleName);
        if (qpr.getStatus() == SUCCESS){
            qpRepository.save(qualityProfileMapper.map(qualityProfile));
            return new QPAndRuleOperationResult(QPAndRuleOperationResult.QPAndRuleOperationStatus.SUCCESS,
                    new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS,qualityProfile),
                    new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS,ruleMapper.map(rule.get())));
        }
        else {
            return new QPAndRuleOperationResult(QPAndRuleOperationResult.QPAndRuleOperationStatus.RULE_NOT_IN_QP,
                    new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS, qualityProfile),
                    new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS, ruleMapper.map(rule.get())));
        }

    }

    @Override
    public QualityProfile getQualityProfile(String profileName) {
        var entity = qpRepository.findByName(profileName);
        return entity.map(qualityProfileMapper::map).orElse(null);
    }

    @Override
    public Collection<QualityProfile> getAllQualityProfiles() {
        List<QualityProfileEntity> qualityProfileEntityList = qpRepository.findAll();
        List<QualityProfile> qualityProfiles = new ArrayList<>();
        for(var it : qualityProfileEntityList){
            qualityProfiles.add(qualityProfileMapper.map(it));
        }
        return qualityProfiles;
    }

    @Override
    public Rule getRule(String ruleName) {
        var ruleEntity = ruleRepository.findByName(ruleName);
        return ruleMapper.map(ruleEntity.get());
    }

    @Override
    public Collection<Rule> getAllRules() {
        var entityList = ruleRepository.findAll();
        List<Rule> rules = new ArrayList<>();

        for(var it : entityList){
            rules.add(ruleMapper.map(it));
        }
        return rules;
    }
}
