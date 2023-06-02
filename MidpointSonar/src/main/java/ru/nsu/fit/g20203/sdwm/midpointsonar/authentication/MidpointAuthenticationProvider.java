package ru.nsu.fit.g20203.sdwm.midpointsonar.authentication;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint.MidPoint;

@Component
public class MidpointAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        final MidPointAuthenticationToken auth = (MidPointAuthenticationToken) authentication;

        final String uri = auth.getURI().toString();
        final String username = auth.getName();
        final String password = auth.getCredentials().toString();

        try {
            if (200 == MidPoint.getInstance().init(uri, username, password)) {
                return new MidPointAuthenticationToken(uri, username, password);
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