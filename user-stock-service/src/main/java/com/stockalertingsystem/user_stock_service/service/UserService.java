package com.stockalertingsystem.user_stock_service.service;

import com.stockalertingsystem.user_stock_service.model.User;
import com.stockalertingsystem.user_stock_service.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired private UserRepository userRepository;

  public void processOAuthPostLogin(OAuth2User oAuth2User) {
    String email = oAuth2User.getAttribute("email");
    Optional<User> existingUser = userRepository.findByEmail(email);
    if (existingUser.isEmpty()) {
      User newUser = new User();
      newUser.setName(oAuth2User.getAttribute("name"));
      newUser.setEmail(email);
      userRepository.save(newUser);
    }
  }
}
