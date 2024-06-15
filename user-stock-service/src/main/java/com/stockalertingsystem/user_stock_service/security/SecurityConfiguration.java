package com.stockalertingsystem.user_stock_service.security;

import com.stockalertingsystem.user_stock_service.service.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

  private final CustomOAuth2UserService customOAuth2UserService;

  public SecurityConfiguration(CustomOAuth2UserService customOAuth2UserService) {
    this.customOAuth2UserService = customOAuth2UserService;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
            authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/", "/login")
                    .permitAll()
                    .requestMatchers("/api/**")
                    .hasAnyRole("USER")
                    .anyRequest()
                    .authenticated())
        .oauth2Login(
            oauth2Login ->
                oauth2Login
                    .userInfoEndpoint(
                        userInfoEndpoint -> userInfoEndpoint.userService(customOAuth2UserService))
                    .defaultSuccessUrl("/"))
        .exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedPage("/error-page"));
    http.csrf(AbstractHttpConfigurer::disable);
    return http.build();
  }
}
