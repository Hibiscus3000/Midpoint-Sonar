package ru.nsu.fit.g20203.sdwm.midpointsonar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.g20203.sdwm.midpointsonar.formatter.QPOutputFormatter;
import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.RulesAndQPManager;

@RestController
@RequestMapping(value = "/qp/",produces = MediaType.TEXT_PLAIN_VALUE)
public class QPController {

    @Autowired
    private QPOutputFormatter qpOutputFormatter;
    @Autowired
    private RulesAndQPManager rulesAndQPManager;

    @GetMapping("ls")
    public String lsQp(@RequestParam(name="name") String name) {
        return qpOutputFormatter.lsQP(rulesAndQPManager.getQualityProfile(name));
    }

    @GetMapping("ls-all")
    public String lsAllQPs() {
        return qpOutputFormatter.lsQPs(rulesAndQPManager.getAllQualityProfiles());
    }

    @PostMapping("create")
    public String createQP(@RequestParam(name="name") String name) {
        return qpOutputFormatter.formatQPOperationResult(
                rulesAndQPManager.createNewQualityProfile(name));
    }

    @PutMapping("rename")
    public String renameQP(@RequestParam(name="old-name") String oldName,
                           @RequestParam(name="new-name") String newName) {
        return qpOutputFormatter.formatQPOperationResult(
                rulesAndQPManager.renameQualityProfile(oldName, newName));
    }

    @DeleteMapping("removeQualityProfile")
    public String removeQP(@RequestParam(name="name") String name) {
        return qpOutputFormatter.formatQPOperationResult(
                rulesAndQPManager.removeQualityProfile(name));
    }
}
