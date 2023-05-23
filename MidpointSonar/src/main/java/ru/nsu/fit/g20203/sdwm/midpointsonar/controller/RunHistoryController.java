package ru.nsu.fit.g20203.sdwm.midpointsonar.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="Run history controller", description="Посмотреть информацию об истории запуска")
public class RunHistoryController {

    @Autowired
    private TaskRunResultFormatter taskRunResultFormatter;
    @Autowired
    private RunHistory runHistory;

    @GetMapping("run/{id}")
    @Operation(summary = "Посмотреть информацию о запуске Quality profile")
    public String getQPRunResult(@PathVariable Integer id) {
        return taskRunResultFormatter.formatQPRunResult(runHistory.getQPRunResult(id));
    }

    @GetMapping("run/all")
    @Operation(summary = "Посмотреть информацию о запуске всех Quality profiles")
    public String getRunHistory() {
        return taskRunResultFormatter.formatRunHistory(runHistory.getRunHistory());
    }

    @GetMapping("load/{id}")
    @Operation(summary = "Посмотреть информацию о загрузке Quality profile")
    public String getRuleLoadResult(@PathVariable Integer id) {
        return taskRunResultFormatter.formatRuleLoadResult(runHistory.getLoadResult(id));
    }

    @GetMapping("load/all")
    @Operation(summary = "Посмотреть информацию о загрузке всех Quality profiles")
    public String getRuleLoadHistory() {
        return taskRunResultFormatter.formatRuleLoadHistory(runHistory.getLoadHistory());
    }
}
