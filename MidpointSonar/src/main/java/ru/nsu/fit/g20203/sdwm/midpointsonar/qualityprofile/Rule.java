package ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile;

import ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint.ServerTask;

public interface Rule {

    String getName();

    ServerTask getServerTask();
}
