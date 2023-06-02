package ru.nsu.fit.g20203.sdwm.midpointsonar.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.ArrayList;

public class MidPointAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final Object uri;

    public MidPointAuthenticationToken(Object uri, Object principal, Object credentials) {
        super(principal, credentials, new ArrayList<>());
        this.uri = uri;
    }

    public Object getURI() {
        return uri;
    }
}
