package ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile;

import ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint.ServerTask;

public class Rule {

    private String name;
    private ServerTask serverTask;

    public Rule(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ServerTask getServerTask() {
        return serverTask;
    }
}
