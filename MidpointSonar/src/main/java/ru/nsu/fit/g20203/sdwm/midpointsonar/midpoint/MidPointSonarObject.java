package ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint;

import java.util.Map;
import java.util.Set;

public interface MidPointSonarObject {

    void addInValues(String key, String value);

    String removeFromValues(String key);

    Set<String> showAllKeys();

    Map<String, String> getValues();

    String getValueByKey(String key);
}
