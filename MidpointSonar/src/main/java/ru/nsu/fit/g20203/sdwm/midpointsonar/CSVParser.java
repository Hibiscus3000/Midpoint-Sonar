package ru.nsu.fit.g20203.sdwm.midpointsonar;

import ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint.MidPointSonarObject;

import java.util.Collection;

public interface CSVParser<O extends MidPointSonarObject> {

    Collection<O> parseReport(String reportPath);
}
