package ru.nsu.fit.g20203.sdwm.midpointsonar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.g20203.sdwm.midpointsonar.formatter.TaskRunResultFormatter;
import ru.nsu.fit.g20203.sdwm.midpointsonar.runhistory.RunHistory;

@RestController
@RequestMapping(value = "/history/",produces = MediaType.TEXT_PLAIN_VALUE)
public class RunHistoryController {

    @Autowired
    private TaskRunResultFormatter taskRunResultFormatter;
    @Autowired
    private RunHistory runHistory;

    @GetMapping("run/{id}")
    public String getQPRunResult(@PathVariable Integer id) {
        return taskRunResultFormatter.formatQPRunResult(runHistory.getQPRunResult(id));
    }

    @GetMapping("run/all")
    public String getRunHistory() {
        return taskRunResultFormatter.formatRunHistory(runHistory.getRunHistory());
    }

    @GetMapping("load/{id}")
    public String getRuleLoadResult(@PathVariable Integer id) {
        return taskRunResultFormatter.formatRuleLoadResult(runHistory.getLoadResult(id));
    }

    @GetMapping("load/all")
    public String getRuleLoadHistory() {
        return taskRunResultFormatter.formatRuleLoadHistory(runHistory.getLoadHistory());
    }
}
