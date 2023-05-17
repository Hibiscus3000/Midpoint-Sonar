package ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint;

import java.util.HashMap;
import java.util.Set;

public class MidPointSonarObjectClass implements MidPointSonarObject {
    private HashMap<String,String> values = new HashMap<>();

    public void addInValues(String key, String value){
        values.put(key, value);
    }
    public String RemoveFromValues(String key){
        return (String)values.remove(key);
    }
    public Set<String> showAllKeys(){
        Set<String> setOfKeys = values.keySet();
        System.out.println(setOfKeys);
        return setOfKeys;
    }
    public HashMap<String,String> getValues(){
        return values;
    }
    public String getValueByKey(String key){
        return values.get(key);
    }
}
