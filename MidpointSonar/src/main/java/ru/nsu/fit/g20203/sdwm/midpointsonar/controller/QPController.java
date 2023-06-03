package ru.nsu.fit.g20203.sdwm.midpointsonar.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.g20203.sdwm.midpointsonar.formatter.QPOutputFormatter;
import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.QualityProfile;
import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.Rule;
import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.RulesAndQPManager;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.RuleOperationResult;

@RestController
@RequestMapping(value = "/qp/", produces = MediaType.TEXT_PLAIN_VALUE)
@Tag(name = "Quality profiles controller", description = "Операции, связанные с профилями качества и правилами")
public class QPController {

    @Autowired
    private QPOutputFormatter qpOutputFormatter;
    @Autowired
    private RulesAndQPManager rulesAndQPManager;

    @GetMapping("ls")
    @Operation(summary = "Вывести информацию о Quality profile")
    public String lsQp(@RequestParam(name="name") String name) {
        return qpOutputFormatter.lsQP(rulesAndQPManager.getQualityProfile(name));
    }

    @GetMapping("ls-all")
    @Operation(summary = "Вывести информацию обо всех Quality profiles")
    public String lsAllQPs() {
        return qpOutputFormatter.lsQPs(rulesAndQPManager.getAllQualityProfiles());
    }

    @PostMapping("create")
    @Operation(summary = "Создать новый Quality profile")
    public String createQP(@RequestParam(name="name") String name) {
        return qpOutputFormatter.formatQPOperationResult(
                rulesAndQPManager.createNewQualityProfile(name));
    }

    @PutMapping("rename")
    @Operation(summary = "Переименовать Quality profile")
    public String renameQP(@RequestParam(name="old-name") String oldName,
                           @RequestParam(name="new-name") String newName) {
        return qpOutputFormatter.formatQPOperationResult(
                rulesAndQPManager.renameQualityProfile(oldName, newName));
    }

    @DeleteMapping("removeQualityProfile")
    @Operation(summary = "Удалить Quality profile")
    public String removeQP(@RequestParam(name = "name") String name) {
        return qpOutputFormatter.formatQPOperationResult(
                rulesAndQPManager.removeQualityProfile(name));
    }

    @GetMapping("ls-rules")
    @Operation(summary = "Просмотр всех доступных правил")
    public String lsAllRules() {
        return qpOutputFormatter.lsRules(rulesAndQPManager.getAllRules());
    }

    @PostMapping("add-rule")
    @Operation(summary = "Добавить правило в профиль качества")
    public String addRule(@RequestParam(name = "qp-name") String qpName,
                          @RequestParam(name = "rule-name") String ruleName) {
        final QualityProfile qualityProfile = rulesAndQPManager.getQualityProfile(qpName);
        if (null == qualityProfile) {
            return qpOutputFormatter.formatQPOperationResult(
                    new QPOperationResult(
                            QPOperationResult.QPOperationStatus.NO_SUCH_QUALITY_PROFILE, null));
        }
        final Rule rule = rulesAndQPManager.getRule(ruleName);

        final RuleOperationResult ruleOperationResult = qualityProfile.addRule(rule);
        return qpOutputFormatter.formatRuleOperationResult(ruleOperationResult);
    }

    @DeleteMapping("remove-rule")
    @Operation(summary = "Удалить правило из профиля качества")
    public String removeRule(@RequestParam(name = "qp-name") String qpName,
                             @RequestParam(name = "rule-name") String ruleName) {
        final QualityProfile qualityProfile = rulesAndQPManager.getQualityProfile(qpName);
        if (null == qualityProfile) {
            return qpOutputFormatter.formatQPOperationResult(
                    new QPOperationResult(
                            QPOperationResult.QPOperationStatus.NO_SUCH_QUALITY_PROFILE, null));
        }

        final RuleOperationResult ruleOperationResult = qualityProfile.removeRule(ruleName);
        return qpOutputFormatter.formatRuleOperationResult(ruleOperationResult);
    }

    @DeleteMapping("delete-all")
    @Operation(summary = "Удалить все профили качества")
    public void deleteAllQPs() {
        rulesAndQPManager.deleteAllQPs();
    }
}
