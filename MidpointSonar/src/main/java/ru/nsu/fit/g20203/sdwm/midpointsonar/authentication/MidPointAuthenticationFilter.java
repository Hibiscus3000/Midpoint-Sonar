package ru.nsu.fit.g20203.sdwm.midpointsonar.authentication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class MidPointAuthenticationFilter
        extends UsernamePasswordAuthenticationFilter {

    private final String uriParameter = "uri";

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response)
            throws AuthenticationException {

        MidPointAuthenticationToken authRequest = getAuthRequest(request);
        setDetails(request, authRequest);

        return getAuthenticationManager().authenticate(authRequest);
    }

    private MidPointAuthenticationToken getAuthRequest(
            HttpServletRequest request) {

        String midPointURI = obtainMidPointURI(request);
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        return new MidPointAuthenticationToken(midPointURI, username, password);
    }

    private String obtainMidPointURI(HttpServletRequest request) {
        return request.getParameter(uriParameter);
    }
}