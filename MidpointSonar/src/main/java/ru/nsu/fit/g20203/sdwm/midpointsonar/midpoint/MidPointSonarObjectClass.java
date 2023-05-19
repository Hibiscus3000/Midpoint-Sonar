package ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MidPointSonarObjectClass implements MidPointSonarObject {
    private HashMap<String,String> values = new HashMap<>();

    @Override
    public void addInValues(String key, String value){
        values.put(key, value);
    }

    @Override
    public String removeFromValues(String key){
        return values.remove(key);
    }

    @Override
    public Set<String> showAllKeys(){
        Set<String> setOfKeys = values.keySet();
        return setOfKeys;
    }

    @Override
    public Map<String,String> getValues(){
        return values;
    }

    @Override
    public String getValueByKey(String key){
        return values.get(key);
    }
}
