package ru.nsu.fit.g20203.sdwm.midpointsonar.authentication;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint.MidPoint;

import java.io.IOException;

public class MidPointAuthenticationFilter
        extends UsernamePasswordAuthenticationFilter {

    private final String uriParameter = "uri";

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response)
            throws AuthenticationException {

        UsernamePasswordAuthenticationToken authRequest = getAuthRequest(request);
        setDetails(request, authRequest);

        return getAuthenticationManager().authenticate(authRequest);
        //return authRequest;
    }

    private UsernamePasswordAuthenticationToken getAuthRequest(
            HttpServletRequest request) {

        String midPointURI = obtainMidPointURI(request);
        MidPoint.getInstance().setURI(midPointURI);

        String username = obtainUsername(request);
        String password = obtainPassword(request);

        return UsernamePasswordAuthenticationToken.unauthenticated(username, password);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        super.doFilter(request, response, chain);
    }

    private String obtainMidPointURI(HttpServletRequest request) {
        return request.getParameter(uriParameter);
    }
}