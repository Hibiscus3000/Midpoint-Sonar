package ru.nsu.fit.g20203.sdwm.midpointsonar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.nsu.fit.g20203.sdwm.midpointsonar.formatter.QPOutputFormatter;
import ru.nsu.fit.g20203.sdwm.midpointsonar.formatter.QPOutputFormatterClass;
import ru.nsu.fit.g20203.sdwm.midpointsonar.formatter.TaskRunResultFormatter;
import ru.nsu.fit.g20203.sdwm.midpointsonar.formatter.TaskRunResultFormatterClass;
import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.RulesAndQPManager;
import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.RulesAndQPManagerClass;

@SpringBootApplication
public class MidPointSonarAPI {



    public static void main(String[] args) {
        try {
            SpringApplication.run(MidPointSonarAPI.class, args);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
