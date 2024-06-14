package com.stockalertingsystem.user_stock_service.handlers;

import com.stockalertingsystem.user_stock_service.model.AuthProvider;
import com.stockalertingsystem.user_stock_service.model.User;
import com.stockalertingsystem.user_stock_service.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomOidcLoginSuccessHandler implements AuthenticationSuccessHandler {

  private final UserRepository userRepository;

  public CustomOidcLoginSuccessHandler(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException, IOException {
    DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();

    String email = (String) oAuth2User.getAttributes().get("email");
    String name = (String) oAuth2User.getAttributes().get("name");

    Optional<User> existingUser = userRepository.findByEmail(email);
    if (existingUser.isEmpty()) {
      User newUser = new User();
      newUser.setEmail(email);
      newUser.setName(name);
      newUser.setProvider(AuthProvider.valueOf(AuthProvider.GOOGLE.name()));
      userRepository.save(newUser);
    }
    response.sendRedirect("/");
  }
}
