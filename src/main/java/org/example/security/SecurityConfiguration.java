package org.example.security;

import org.example.constant.UserConstant;
import org.example.model.RoleType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    private final AuthorizationFilter authorizationFilter;
    private final AuthenticationProvider authenticationProvider;

    public SecurityConfiguration(AuthorizationFilter authorizationFilter, AuthenticationProvider authenticationProvider) {
        this.authorizationFilter = authorizationFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .formLogin().disable()
                .logout().disable()
                .csrf().disable()
                .cors().disable()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((auth) -> {
                    auth.antMatchers("/api/auth/**").permitAll();
                    //<------ USER END-POINTS ------->
                    auth.antMatchers(HttpMethod.GET, UserConstant.CONTROLLER_PREFIX + "/**")
                            .hasAnyAuthority(
                                    RoleType.ADMIN.getRoleName(),
                                    RoleType.NORMAL.getRoleName()
                            );
                    auth.antMatchers(HttpMethod.GET, UserConstant.CONTROLLER_PREFIX)
                            .hasAnyAuthority(RoleType.ADMIN.getRoleName());
                    auth.antMatchers(HttpMethod.POST, UserConstant.CONTROLLER_PREFIX)
                            .hasAnyAuthority(RoleType.ADMIN.getRoleName());
                    auth.antMatchers(HttpMethod.PUT, UserConstant.CONTROLLER_PREFIX + "/**")
                            .hasAnyAuthority(RoleType.ADMIN.getRoleName());
                    //<------ END USER END-POINTS ------>
                    auth.anyRequest().authenticated();
                })
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
