package ru.nsu.fit.g20203.sdwm.midpointsonar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.g20203.sdwm.midpointsonar.formatter.QPOutputFormatter;
import ru.nsu.fit.g20203.sdwm.midpointsonar.formatter.TaskRunResultFormatter;
import ru.nsu.fit.g20203.sdwm.midpointsonar.runhistory.RunHistory;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class})

public class MidPointSonarAPI {


    public static void main(String[] args) {
        try {
            SpringApplication.run(MidPointSonarAPI.class, args);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
