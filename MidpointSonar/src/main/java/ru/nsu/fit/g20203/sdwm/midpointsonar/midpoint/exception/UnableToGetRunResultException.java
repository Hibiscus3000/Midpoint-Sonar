package ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint.exception;

public class UnableToGetRunResultException extends Exception {

    public UnableToGetRunResultException(String ruleName) {
        super("Unable to get \"" + ruleName + "\" result from MidPoint");
    }
}
