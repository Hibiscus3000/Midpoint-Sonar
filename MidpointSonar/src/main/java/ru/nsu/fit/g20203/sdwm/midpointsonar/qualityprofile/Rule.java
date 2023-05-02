package ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile;

import ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint.ServerTask;

public abstract class Rule {

    public String name;

    public String getName() {
        return name;
    }

    public abstract ServerTask getServerTask();
}
