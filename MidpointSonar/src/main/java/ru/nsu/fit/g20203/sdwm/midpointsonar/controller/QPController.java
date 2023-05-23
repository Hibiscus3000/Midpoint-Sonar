package ru.nsu.fit.g20203.sdwm.midpointsonar.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.g20203.sdwm.midpointsonar.formatter.QPOutputFormatter;
import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.RulesAndQPManager;

@RestController
@RequestMapping(value = "/qp/",produces = MediaType.TEXT_PLAIN_VALUE)
@Tag(name="Quality profiles controller", description="Операции, связанные с Quality profiles")
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
    public String removeQP(@RequestParam(name="name") String name) {
        return qpOutputFormatter.formatQPOperationResult(
                rulesAndQPManager.removeQualityProfile(name));
    }
}
