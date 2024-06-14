package com.stockalertingsystem.user_stock_service.security;

import com.stockalertingsystem.user_stock_service.handlers.CustomOidcLoginSuccessHandler;
import com.stockalertingsystem.user_stock_service.service.CustomOidcUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

  private final CustomOidcUserService customOidcUserService;
  private final CustomOidcLoginSuccessHandler customOidcLoginSuccessHandler;

  public SecurityConfiguration(
      CustomOidcUserService customOidcUserService,
      CustomOidcLoginSuccessHandler customOidcLoginSuccessHandler) {
    this.customOidcUserService = customOidcUserService;
    this.customOidcLoginSuccessHandler = customOidcLoginSuccessHandler;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
            authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/", "/login")
                    .permitAll()
                    .anyRequest()
                    .authenticated())
        .oauth2Login(
            oauth2Login ->
                oauth2Login
                    .userInfoEndpoint(
                        userInfoEndpoint -> userInfoEndpoint.oidcUserService(customOidcUserService))
                    .successHandler(customOidcLoginSuccessHandler))
        .exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedPage("/error"));

    return http.build();
  }
}
