package ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile;

import ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint.ServerTask;

public class Rule {

    private String name;
    private ServerTask serverTask;

    public Rule(String name, String oid) {
        this.name = name;
        serverTask = new ServerTaskImpl(oid);
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

    private class ServerTaskImpl implements ServerTask {

        private final String oid;

        public ServerTaskImpl(String oid) {
            this.oid = oid;
        }

        @Override
        public String getOid() {
            return oid;
        }

        @Override
        public String toString() {
            return oid;
        }
    }
}
