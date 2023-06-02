package ru.nsu.fit.g20203.sdwm.midpointsonar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import ru.nsu.fit.g20203.sdwm.midpointsonar.authentication.MidPointAuthenticationFilter;
import ru.nsu.fit.g20203.sdwm.midpointsonar.authentication.MidpointAuthenticationProvider;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private MidpointAuthenticationProvider authProvider;

    protected AuthenticationManager authenticationManager() {
        final ProviderManager authManager = new ProviderManager(authProvider);
        return authManager;
    }

    protected MidPointAuthenticationFilter authenticationFilter() {
        final MidPointAuthenticationFilter authFilter = new MidPointAuthenticationFilter();
        authFilter.setAuthenticationManager(authenticationManager());
        return authFilter;
    }

    @SuppressWarnings("removal")
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        final SecurityFilterChain securityFilterChain = http
                .csrf().disable()
                //.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth ->
                        auth.anyRequest().authenticated())
                .authenticationManager(authenticationManager())
                .formLogin(form ->
                        form
                                .loginPage("/login")
                                .permitAll()
                                .defaultSuccessUrl("/", true))
                .build();
        return securityFilterChain;
    }
}
