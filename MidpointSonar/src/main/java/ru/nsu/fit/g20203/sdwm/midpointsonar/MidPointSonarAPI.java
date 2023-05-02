package ru.nsu.fit.g20203.sdwm.midpointsonar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.nsu.fit.g20203.sdwm.midpointsonar.formatter.QPOutputFormatter;
import ru.nsu.fit.g20203.sdwm.midpointsonar.formatter.TaskRunResultFormatter;
import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.RulesAndQPManager;

@SpringBootApplication
public class MidPointSonarAPI {

    @Autowired
    private QPOutputFormatter qpOutputFormatter;

    @Autowired
    private TaskRunResultFormatter taskRunResultFormatter;

    @Autowired
    private RunHistory runHistory;

    @Autowired
    private RulesAndQPManager rulesAndQPManager;

    public static void main(String[] args) {
        SpringApplication.run(MidPointSonarAPI.class, args);
    }
}
