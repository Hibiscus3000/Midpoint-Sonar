package ru.nsu.fit.g20203.sdwm.midpointsonar.authentication;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint.MidPoint;

import java.util.ArrayList;

@Component
public class MidpointAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();

        try {
            if (200 == MidPoint.getInstance().attemptAuth(username, password)) {
                return UsernamePasswordAuthenticationToken.authenticated(username, password, new ArrayList<>());
            } else {
                throw new AuthenticationCredentialsNotFoundException("Wrong credentials");
            }
        } catch (Exception e) {
            throw new AuthenticationServiceException("Unable to connect to MidPoint", e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}