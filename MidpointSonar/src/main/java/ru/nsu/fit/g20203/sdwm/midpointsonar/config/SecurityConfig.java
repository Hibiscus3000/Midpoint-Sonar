package ru.nsu.fit.g20203.sdwm.midpointsonar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.nsu.fit.g20203.sdwm.midpointsonar.authentication.MidPointAuthenticationFilter;
import ru.nsu.fit.g20203.sdwm.midpointsonar.authentication.MidpointAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private MidpointAuthenticationProvider authProvider;

    @Bean
    protected AuthenticationManager authenticationManager() {
        final ProviderManager authManager = new ProviderManager(authProvider);
        return authManager;
    }

    @Bean
    protected MidPointAuthenticationFilter authenticationFilter() {
        final MidPointAuthenticationFilter authFilter = new MidPointAuthenticationFilter();
        authFilter.setAuthenticationManager(authenticationManager());
        return authFilter;
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .addFilterAt(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth ->
                        auth.anyRequest().authenticated())
                .formLogin(form ->
                        form
                                .loginPage("/login")
                                .permitAll()
                                .defaultSuccessUrl("/", true))
                .build();
    }
}
