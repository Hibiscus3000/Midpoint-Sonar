package ru.nsu.fit.g20203.sdwm.midpointsonar.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.nsu.fit.g20203.sdwm.midpointsonar.formatter.TaskRunResultFormatter;
import ru.nsu.fit.g20203.sdwm.midpointsonar.runhistory.RunHistory;

//@RestController
//@RequestMapping(value = "/history/",produces = MediaType.TEXT_PLAIN_VALUE)
//@Tag(name="Run history controller", description="Посмотреть историю запуска")
public class RunHistoryController {

    //@Autowired
    private TaskRunResultFormatter taskRunResultFormatter;
    //@Autowired
    private RunHistory runHistory;

    @GetMapping("run/{id}")
    @Operation(summary = "Посмотреть информацию о запуске Quality profile")
    public String getQPRunResult(@PathVariable Integer id) {
        return taskRunResultFormatter.formatQPRunResult(runHistory.getQPRunResult(id));
    }

    @GetMapping("run/all")
    @Operation(summary = "Посмотреть информацию о запуске всех Quality profiles")
    public String getRunHistory() {
        return taskRunResultFormatter.formatRunHistoryWId(runHistory.getRunHistory());
    }

    @DeleteMapping("run/clear")
    @Operation(summary = "Очистить историю запусков")
    public String clearRunHistory() {
        runHistory.clearRunHistory();
        return "Run history cleared";
    }

//    @GetMapping("load/{id}")
//    @Operation(summary = "Посмотреть информацию о загрузке Quality profile")
//    public String getRuleLoadResult(@PathVariable Integer id) {
//        return taskRunResultFormatter.formatRuleLoadResult(runHistory.getLoadResult(id));
//    }
//
//    @GetMapping("load/all")
//    @Operation(summary = "Посмотреть информацию о загрузке всех Quality profiles")
//    public String getRuleLoadHistory() {
//        return taskRunResultFormatter.formatRuleLoadHistoryWId(runHistory.getLoadHistory());
//    }
}
