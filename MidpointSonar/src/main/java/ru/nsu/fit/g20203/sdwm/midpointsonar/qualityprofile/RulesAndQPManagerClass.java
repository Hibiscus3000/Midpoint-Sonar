package ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.fit.g20203.sdwm.midpointsonar.entity.QualityProfileEntity;
import ru.nsu.fit.g20203.sdwm.midpointsonar.mapper.QualityProfileMapper;
import ru.nsu.fit.g20203.sdwm.midpointsonar.mapper.RuleMapper;
import ru.nsu.fit.g20203.sdwm.midpointsonar.repositories.QPRepository;
import ru.nsu.fit.g20203.sdwm.midpointsonar.repositories.RuleRepository;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPAndRuleOperationResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.RuleOperationResult;

import java.nio.charset.StandardCharsets;
import java.util.*;

import static ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult.QPOperationStatus.NO_SUCH_QUALITY_PROFILE;
import static ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.RuleOperationResult.RuleOperationStatus.SUCCESS;


@Service
public class RulesAndQPManagerClass implements RulesAndQPManager {
    @Autowired
    private QPRepository qpRepository;
    @Autowired
    private QualityProfileMapper qualityProfileMapper;
    @Autowired
    private RuleRepository ruleRepository;
    @Autowired
    private RuleMapper ruleMapper;

    private Map<String, Rule> rules;

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
        if (qp.isEmpty()) {
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
        if (qp.isEmpty()) {
            return new QPOperationResult(NO_SUCH_QUALITY_PROFILE, new QualityProfile(profileName));
        }
        qpRepository.delete(qp.get());
        return new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS, null);
    }

    @Override
    public QPAndRuleOperationResult addRuleToQualityProfile(String ruleName, String profileName) {
        var qp = qpRepository.findByName(profileName);
        if (qp.isEmpty()) {
            return new QPAndRuleOperationResult(QPAndRuleOperationResult.QPAndRuleOperationStatus.SUCCESS,
                    new QPOperationResult(NO_SUCH_QUALITY_PROFILE, new QualityProfile(profileName)),
                    new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS,
                            null));
        }
        Rule rule = rules.get(ruleName);
        QualityProfile qualityProfile = qualityProfileMapper.map(qp.get());
        if (null == rule) {
            return new QPAndRuleOperationResult(QPAndRuleOperationResult.QPAndRuleOperationStatus.SUCCESS,
                    new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS, qualityProfile),
                    new RuleOperationResult(RuleOperationResult.RuleOperationStatus.NO_SUCH_RULE,
                            null));
        }

        RuleOperationResult qpr = qualityProfile.addRule(rules.get(ruleName));
        if (qpr.getStatus() == SUCCESS) {
            qpRepository.save(qualityProfileMapper.map(qualityProfile));
            return new QPAndRuleOperationResult(QPAndRuleOperationResult.QPAndRuleOperationStatus.SUCCESS,
                    new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS, qualityProfile),
                    new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS, rule));
        } else {
            return new QPAndRuleOperationResult(QPAndRuleOperationResult.QPAndRuleOperationStatus.RULE_ALREADY_IN_QP,
                    new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS, qualityProfile),
                    new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS, rule));
        }
    }

    @Override
    public QPAndRuleOperationResult removeRuleFromQualityProfile(String ruleName, String profileName) {
        var qp = qpRepository.findByName(profileName);
        if (qp.isEmpty()) {
            return new QPAndRuleOperationResult(QPAndRuleOperationResult.QPAndRuleOperationStatus.SUCCESS,
                    new QPOperationResult(NO_SUCH_QUALITY_PROFILE, new QualityProfile(profileName)),
                    new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS,
                            null));
        }
        QualityProfile qualityProfile = qualityProfileMapper.map(qp.get());
        var rule = rules.get(ruleName);
        if (null == rule) {
            return new QPAndRuleOperationResult(QPAndRuleOperationResult.QPAndRuleOperationStatus.SUCCESS,
                    new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS, qualityProfile),
                    new RuleOperationResult(RuleOperationResult.RuleOperationStatus.NO_SUCH_RULE,
                            null));
        }
        RuleOperationResult qpr = qualityProfile.removeRule(ruleName);
        if (qpr.getStatus() == SUCCESS) {
            qpRepository.save(qualityProfileMapper.map(qualityProfile));
            return new QPAndRuleOperationResult(QPAndRuleOperationResult.QPAndRuleOperationStatus.SUCCESS,
                    new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS, qualityProfile),
                    new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS, rule));
        } else {
            return new QPAndRuleOperationResult(QPAndRuleOperationResult.QPAndRuleOperationStatus.RULE_NOT_IN_QP,
                    new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS, qualityProfile),
                    new RuleOperationResult(RuleOperationResult.RuleOperationStatus.SUCCESS, rule));
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
        for (var it : qualityProfileEntityList) {
            qualityProfiles.add(qualityProfileMapper.map(it));
        }
        return qualityProfiles;
    }

    @Override
    public Rule getRule(String ruleName) {
        if (null == rules) {
            readRulesFromJson();
        }
        return rules.get(ruleName);
    }

    private void readRulesFromJson() {
        try {
            rules = new HashMap<>();
            final JSONParser parser = new JSONParser();
            final String json = new String(getClass().getResourceAsStream("rules.json").readAllBytes(),
                    StandardCharsets.UTF_8);
            final JSONArray rulesObject = (JSONArray) parser.parse(json);
            for (Object ruleObj : rulesObject) {
                final JSONObject jsonObject = (JSONObject) ruleObj;
                final String ruleName = (String) jsonObject.get("name");
                final Rule rule = new Rule(ruleName, (String) jsonObject.get("oid"));
                rules.put(ruleName, rule);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Rule> getAllRules() {
        if (null == rules) {
            readRulesFromJson();
        }
        return rules.values();
    }

    @Override
    public void deleteAllQPs() {
        qpRepository.deleteAll();
    }
}
