package com.techinnovation.arrangingseats.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techinnovation.arrangingseats.model.User;
import com.techinnovation.arrangingseats.repository.UserRepository;
import com.techinnovation.arrangingseats.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
    User user = userRepository.findById(loginId)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with userId: " + loginId));
    return CustomUserDetails.build(user);
  }

}
