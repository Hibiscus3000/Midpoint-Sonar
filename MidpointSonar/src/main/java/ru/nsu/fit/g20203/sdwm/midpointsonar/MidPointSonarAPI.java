package ru.nsu.fit.g20203.sdwm.midpointsonar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MidPointSonarAPI {


    public static void main(String[] args) {
        try {
            SpringApplication.run(MidPointSonarAPI.class, args);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
